package combat;

import button.*;
import combat.attack.light.LightAreaAttackOne;
import combat.combatUnit.CombatUnit;
import combat.math.MathQuestion;
import core.Media;
import core.Values;
import entities.player.Player;
import item.currency.Gold;
import org.newdawn.slick.*;
import combat.attack.Attack;
import org.newdawn.slick.state.StateBasedGame;
import popup.message.BattleAnnouncement;
import popup.message.DebugMessage;
import world.World;
import static core.Main.getScreenWidth;
import static core.Main.getScreenHeight;
import static core.state.BattleState.developerMode;
import static popup.PopupManager.addPopup;

public class Battle implements Values {
    private Team left, right;
    private BattleStep battleStep;
    private int lastChosenAttack;
    private int waitTimer;
    private boolean battleOver;
    private int lastChosenSwitch;
    private MathQuestion question;
    private boolean leftMissedAttack;
    private Player player;
    private int leftSize, rightSize;
    private StateBasedGame sbg;
    private final Image fireBackground = Media.imgBattleBGFire;
    private final Image iceBackground = Media.imgBattleBGIce;
    private ToWorldButton toWorldButton;
    private AttackChoiceButton[][] attackChoiceButtons;
    private SwitchButton switchButton;
    private CaptureButton captureButton;
    private SwitchChoiceButton switchToTopButton;
    private SwitchChoiceButton switchToBottomButton;
    private CancelSwitchButton cancelSwitchButton;
    private double animationTimer;
    private int animationTick;
    private int rightChosenAttack;
    private double rightCost;
    private int spellMovementTimer;

    public Battle(Team left, Team right, StateBasedGame sbg) {
        this.left = left;
        this.right = right;
        this.sbg = sbg;
        battleStep = BattleStep.LEFT_CHARGING;
        lastChosenAttack = 0;
        battleOver = false;

        left.setSide(true);
        right.setSide(false);

        left.getActiveUnit().setSide(true);
        left.getActiveUnit().setPosition(0);
        leftSize = 1;
        if (left.getWaitingUnitOne() != null) {
            left.getWaitingUnitOne().setSide(true);
            left.getWaitingUnitOne().setPosition(1);
            leftSize++;
        }
        if (left.getWaitingUnitTwo() != null) {
            left.getWaitingUnitTwo().setSide(true);
            left.getWaitingUnitTwo().setPosition(2);
            leftSize++;
        }

        right.getActiveUnit().setSide(false);
        right.getActiveUnit().setPosition(0);
        rightSize = 1;
        if (right.getWaitingUnitOne() != null) {
            right.getWaitingUnitOne().setSide(false);
            right.getWaitingUnitOne().setPosition(1);
            rightSize++;
        }
        if (right.getWaitingUnitTwo() != null) {
            right.getWaitingUnitTwo().setSide(false);
            right.getWaitingUnitTwo().setPosition(2);
            rightSize++;
        }
        toWorldButton = new ToWorldButton(getScreenWidth() - STATE_BUTTON_WIDTH - 100, 150, sbg);
        attackChoiceButtons = new AttackChoiceButton[2][2];
        attackChoiceButtons[0][0] = new AttackChoiceButton(0, 0, null, this);
        attackChoiceButtons[0][1] = new AttackChoiceButton(0, 1, null, this);
        attackChoiceButtons[1][0] = new AttackChoiceButton(1, 0, null, this);
        attackChoiceButtons[1][1] = new AttackChoiceButton(1, 1, null, this);
        switchButton = new SwitchButton(getScreenWidth() / 2 + 10 + SPELL_BUTTON_WIDTH + 20, getScreenHeight() / 2 - LARGE_SQUARE_BUTTON_SIZE - 50, this);
        captureButton = new CaptureButton(getScreenWidth() / 2 + 10 + SPELL_BUTTON_WIDTH + 20, getScreenHeight() / 2 + 50, this);
        switchToTopButton = new SwitchChoiceButton(0, this);
        switchToBottomButton = new SwitchChoiceButton(1, this);
        cancelSwitchButton = new CancelSwitchButton(getScreenWidth() - CANCEL_SWITCH_BUTTON_WIDTH - 100, 150, this);

        Media.musicBattle.loop(1, MUSIC_VOLUME);
    }

    public void update() {
        if (battleOver) {
            Media.musicBattle.stop();
        }
        else {
            if (waitTimer > 0) {
                waitTimer--;
            }

            switch (battleStep) {
                case LEFT_CHARGING:
                    left.rechargeEnergy();
                    battleStep = BattleStep.LEFT_DECIDING;
                    animationTimer = 0;
                    spellMovementTimer = 0;
                    break;
                case LEFT_DECIDING:
                    attackChoiceButtons[0][0].updateAttack(left.getActiveUnit().getAttack(0));
                    attackChoiceButtons[0][1].updateAttack(left.getActiveUnit().getAttack(1));
                    attackChoiceButtons[1][0].updateAttack(left.getActiveUnit().getAttack(2));
                    attackChoiceButtons[1][1].updateAttack(left.getActiveUnit().getAttack(3));
                    switchButton.updateUsable(left.getWaitingUnitOne() != null || left.getWaitingUnitTwo() != null);
                    captureButton.updateUsable(right.getActiveUnit().getPercentHealth() <= CAPTURE_REQUIREMENT);
                    break;
                case MATH_QUESTION:
                    if (question != null) {
                        if (question.correctAnswerSubmitted()) {
                            battleStep = BattleStep.LEFT_ATTACKING;
                            leftMissedAttack = false;
                            if (lastChosenAttack < 4) {
                                left.spendEnergy(left.getActiveUnit().getAttackCost(lastChosenAttack));
                                addPopup(new BattleAnnouncement(left.getActiveUnit().getName() + " used " + left.getActiveUnit().getAttack(lastChosenAttack).getName()));
                            }
                            waitTimer = SPELL_DELAY;
                            Media.sfxCastSpell.play(2, 0.2f);
                        }
                        else if (question.incorrectAnswerSubmitted()) {
                            battleStep = BattleStep.LEFT_ATTACKING;
                            leftMissedAttack = true;
                            if (lastChosenAttack < 4) {
                                left.spendEnergy(left.getActiveUnit().getAttackCost(lastChosenAttack));
                                addPopup(new BattleAnnouncement(left.getActiveUnit().getName() + " used " + left.getActiveUnit().getAttack(lastChosenAttack).getName()));
                            }
                            waitTimer = SPELL_DELAY;
                            Media.sfxCastSpell.play(2, 0.2f);
                        }
                    }
                    break;
                case LEFT_ATTACKING:
                    if (waitTimer <= 0) {
                        if (lastChosenAttack == 5) { //Capture
                            if (!leftMissedAttack) {
                                Player.getPetSelector().addPet(right.getActiveUnit().getEntity());
                                right.getActiveUnit().remove();
                                right.cleanup();
                                Media.sfxCapture.play();
                            }
                            else {
                                Media.sfxMiss.play();
                            }
                            waitTimer = 2 * SPELL_DELAY;
                            battleStep = BattleStep.RIGHT_CHARGING;
                        }
                        else {
                            left.attack(lastChosenAttack, right, leftMissedAttack);
                            if (!leftMissedAttack) {
                                Media.sfxDamage.play();
                            }
                            else {
                                Media.sfxMiss.play();
                            }
                            waitTimer = 2 * SPELL_DELAY;
                            battleStep = BattleStep.RIGHT_CHARGING;
                        }
                    }
                    else {
                        animationTimer += ANIMATION_TIMER_RATE;
                        if (lastChosenAttack < 4 && animationTimer >= left.getActiveUnit().getAttack(lastChosenAttack).getSheetSize()) {
                            animationTimer = 0;
                        }
                        spellMovementTimer++;
                    }
                    break;
                case RIGHT_CHARGING:
                    right.rechargeEnergy();
                    battleStep = BattleStep.RIGHT_DECIDING;
                    animationTimer = 0;
                    spellMovementTimer = 0;
                    break;
                case RIGHT_DECIDING:
                    if (waitTimer <= 0) {
                        if (!right.hasEnergy(MAIN_AREA_ATTACK_COST)) {
                            rightChosenAttack = 0;
                            rightCost = MAIN_SINGLE_ATTACK_COST;
                        }
                        else {
                            if (Math.random() < 0.5) {
                                rightChosenAttack = 0;
                                rightCost = MAIN_SINGLE_ATTACK_COST;
                            }
                            else {
                                rightChosenAttack = 1;
                                rightCost = MAIN_AREA_ATTACK_COST;
                            }
                        }
                        right.spendEnergy(rightCost);
                        addPopup(new BattleAnnouncement(right.getActiveUnit().getName() + " used " + right.getActiveUnit().getAttack(rightChosenAttack).getName()));
                        battleStep = BattleStep.RIGHT_ATTACKING;
                        waitTimer = SPELL_DELAY;
                        Media.sfxCastSpell.play(2, 0.2f);
                    }
                    break;
                case RIGHT_ATTACKING:
                    if (waitTimer <= 0) {
                        boolean miss = Math.random() < MISS_CHANCE;
                        right.attack(rightChosenAttack, left, miss);
                        if (!miss) {
                            Media.sfxDamage.play();
                        }
                        else {
                            Media.sfxMiss.play();
                        }
                        waitTimer = SPELL_DELAY;
                        battleStep = BattleStep.LEFT_CHARGING;
                    }
                    else {
                        animationTimer += ANIMATION_TIMER_RATE;
                        if (animationTimer >= right.getActiveUnit().getAttack(rightChosenAttack).getSheetSize()) {
                            animationTimer = 0;
                        }
                        spellMovementTimer++;
                    }
                    break;
            }

            if (right.hasLost()) {
                addPopup(new BattleAnnouncement("Victory!", getScreenHeight() * 0.5f, 300));
                Player.getInventory().addItem(Gold.class, BASE_GOLD_REWARD * rightSize);
                World.giveBiomeLoot();
                left.gainXP(BASE_XP_REWARD * rightSize);
                Media.sfxVictory.play();
                battleOver = true;
            }
            else if (left.hasLost()) {
                addPopup(new BattleAnnouncement("Defeat...", getScreenHeight() * 0.5f, 300));
                Media.sfxDefeat.play();
                battleOver = true;
            }
        }
    }

    public void render(Graphics g, GameContainer gc) {
        drawBackground(g);

        left.drawMembers(g);
        right.drawMembers(g);

        if (developerMode()) {
            addPopup(new DebugMessage("Battle state: " + battleStep, 5));

            String leftWaitingOne = "[none]";
            if (left.getWaitingUnitOne() != null) {
                leftWaitingOne = left.getWaitingUnitOne().getName();
            }
            String leftWaitingTwo = "[none]";
            if (left.getWaitingUnitTwo() != null) {
                leftWaitingTwo = left.getWaitingUnitTwo().getName();
            }
            if (left.getActiveUnit() != null) {
                CombatUnit u = left.getActiveUnit();
                addPopup(new DebugMessage("Left: " + u.getName() + ", " + leftWaitingOne + ", " + leftWaitingTwo, 6));
                addPopup(new DebugMessage("Active: " + u.getName() + " (" + u.getNameOfElement() + ")", 7));
                addPopup(new DebugMessage("HP: " + u.getCurHealth() + "/" + u.getMaxHealth(), 8));
                addPopup(new DebugMessage("Energy: " + left.getEnergy() + "/" + MAX_ENERGY, 9));
            }

            String rightWaitingOne = "[none]";
            if (right.getWaitingUnitOne() != null) {
                rightWaitingOne = right.getWaitingUnitOne().getName();
            }
            String rightWaitingTwo = "[none]";
            if (right.getWaitingUnitTwo() != null) {
                rightWaitingTwo = right.getWaitingUnitTwo().getName();
            }
            if (right.getActiveUnit() != null) {
                CombatUnit u = right.getActiveUnit();
                addPopup(new DebugMessage("Right: " + u.getName() + ", " + rightWaitingOne + ", " + rightWaitingTwo, 10));
                addPopup(new DebugMessage("Active: " + u.getName() + " (" + u.getNameOfElement() + ")", 11));
                addPopup(new DebugMessage("HP: " + u.getCurHealth() + "/" + u.getMaxHealth(), 12));
                addPopup(new DebugMessage("Energy: " + right.getEnergy() + "/" + MAX_ENERGY, 13));
            }

            addPopup(new DebugMessage("animationTimer: " + animationTimer, 15));
            addPopup(new DebugMessage("animationTick: " + animationTick, 16));
        }

        left.drawUI(g);
        right.drawUI(g);

        if (battleStep == BattleStep.MATH_QUESTION && question != null) {
            question.render(g, gc);
        }

        animationTick = (int) animationTimer;

        switch (battleStep) {
            case LEFT_DECIDING:
                attackChoiceButtons[0][0].render(g, gc);
                attackChoiceButtons[0][1].render(g, gc);
                attackChoiceButtons[1][0].render(g, gc);
                attackChoiceButtons[1][1].render(g, gc);
                switchButton.render(g, gc);
                captureButton.render(g, gc);
                break;
            case LEFT_SWITCHING:
                if (left.getWaitingUnitOne() != null) {
                    switchToTopButton.render(g, gc);
                }
                if (left.getWaitingUnitTwo() != null){
                    switchToBottomButton.render(g, gc);
                }
                cancelSwitchButton.render(g, gc);
                Media.drawShadowedString("Choose an ally to switch to", getScreenWidth() / 2, 150, Media.CENTER, Media.TOP, Media.defaultFontLarge, Color.white, Color.black, g);
                break;
            case LEFT_ATTACKING:
                if (waitTimer > SPELL_DELAY - SPELL_EFFECT_DURATION && lastChosenAttack < 4) {
                    left.getActiveUnit().getAttack(lastChosenAttack).render(g, true, animationTick, spellMovementTimer);
                }
                else if (waitTimer > 0 && lastChosenAttack == 5) { //Capture
                    float x = (getScreenWidth() * 0.15f + 284/2) + (spellMovementTimer * 20);
                    Image image = Media.sheetSpellCapture.getSprite(0, 0);
                    image.draw(x - image.getWidth() / 2, getScreenHeight() * 0.45f - image.getHeight() / 2);
                }
                break;
            case RIGHT_ATTACKING:
                if (waitTimer > SPELL_DELAY - SPELL_EFFECT_DURATION) {
                    right.getActiveUnit().getAttack(rightChosenAttack).render(g, false, animationTick, spellMovementTimer);
                }
                break;
        }

        if (battleOver) {
            toWorldButton.render(g, gc);
        }
    }

    public void keyPressed(int key) {
        if (!battleOver) {
            if (battleStep == BattleStep.LEFT_DECIDING) {
                if (developerMode() && key == Input.KEY_K) {
                    (new LightAreaAttackOne(10000, 0)).use(right, false);
                    battleStep = BattleStep.RIGHT_CHARGING;
                }
            }
        }
    }

    public void mousePressed(int button, int x, int y) {
        if (battleOver) {
            toWorldButton.mousePressed(x, y);
        }
        else {
            if (battleStep == BattleStep.LEFT_DECIDING) {
                attackChoiceButtons[0][0].mousePressed(x, y);
                attackChoiceButtons[0][1].mousePressed(x, y);
                attackChoiceButtons[1][0].mousePressed(x, y);
                attackChoiceButtons[1][1].mousePressed(x, y);
                switchButton.mousePressed(x, y);
                captureButton.mousePressed(x, y);
            }
            else if (battleStep == BattleStep.LEFT_SWITCHING) {
                if (left.getWaitingUnitOne() != null) {
                    switchToTopButton.mousePressed(x, y);
                }
                if (left.getWaitingUnitTwo() != null) {
                    switchToBottomButton.mousePressed(x, y);
                }
                cancelSwitchButton.mousePressed(x, y);
            }
            else if (battleStep == BattleStep.MATH_QUESTION) {
                question.mousePressed(x, y);
            }
        }
    }

    public void drawBackground(Graphics g) {
        if (World.getCurrentBiome() == World.calderaCastle()){
            fireBackground.draw(0, 0, getScreenWidth(), getScreenHeight());
        }
        else if (World.getCurrentBiome() == World.permafrostGlaciers()) {
            iceBackground.draw(0, 0, getScreenWidth(), getScreenHeight());
        }
    }

    public void chooseAttack(int index) {
        lastChosenAttack = index;
        if (lastChosenAttack > 5 || lastChosenAttack < 0) {
            Media.sfxInvalidAction.play();
            return;
        }

        if (lastChosenAttack == 4) {
            if (left.getWaitingUnitOne() != null || left.getWaitingUnitTwo() != null) {
                battleStep = BattleStep.LEFT_SWITCHING;
            }
            else {
                Media.sfxInvalidAction.play();
            }
        }
        else if (lastChosenAttack == 5) {
            if (right.getActiveUnit().getPercentHealth() <= CAPTURE_REQUIREMENT) {
                question = new MathQuestion();
                battleStep = BattleStep.MATH_QUESTION;
            }
            else {
                Media.sfxInvalidAction.play();
            }
        }
        else {
            if (left.getActiveUnit().getAttack(lastChosenAttack) != null && left.hasEnergy(left.getActiveUnit().getAttackCost(lastChosenAttack))) {
                question = new MathQuestion();
                battleStep = BattleStep.MATH_QUESTION;
            }
            else {
                Media.sfxInvalidAction.play();
            }
        }
    }

    public void chooseSwitch(int position) {
        if (position == 0 && left.getWaitingUnitOne() != null) {
            left.swap(0);
            waitTimer = 180;
            battleStep = BattleStep.RIGHT_CHARGING;
        }
        else if (position == 1 && left.getWaitingUnitTwo() != null) {
            left.swap(1);
            waitTimer = 180;
            battleStep = BattleStep.RIGHT_CHARGING;
        }
    }

    public void cancelSwitch() {
        if (battleStep == BattleStep.LEFT_SWITCHING) {
            battleStep = BattleStep.LEFT_DECIDING;
        }
    }

    public Team getLeft() {
        return left;
    }

    public static boolean isStrong(Attack a, CombatUnit e) {
        Elements attackElement = a.getElement();
        Elements entityElement = e.getElement();

        return switch (attackElement) {
            case LIGHT -> false;
            case FIRE -> entityElement == Elements.ICE || entityElement == Elements.EARTH;
            case WATER -> entityElement == Elements.FIRE;
            case STORM, EARTH -> entityElement == Elements.WATER;
            case ICE -> entityElement == Elements.STORM;
        };
    }

    public static boolean isWeak(Attack a, CombatUnit e) {
        Elements attackElement = a.getElement();
        Elements entityElement = e.getElement();

        return switch (attackElement) {
            case LIGHT -> false;
            case FIRE -> entityElement == Elements.WATER;
            case WATER -> entityElement == Elements.STORM || entityElement == Elements.EARTH;
            case STORM -> entityElement == Elements.ICE;
            case ICE, EARTH -> entityElement == Elements.FIRE;
        };
    }
}
