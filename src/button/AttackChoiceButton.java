package button;

import combat.Battle;
import combat.attack.Attack;
import core.Media;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import static core.Main.getScreenHeight;
import static core.Main.getScreenWidth;

public class AttackChoiceButton extends Button {
    private int xIndex, yIndex;
    private Attack attack;
    private Battle battle;
    private int spellIndex;

    public AttackChoiceButton(int xIndex, int yIndex, Attack attack, Battle battle) {
        super(0, 0, SPELL_BUTTON_WIDTH, SPELL_BUTTON_HEIGHT);
        this.xIndex = xIndex;
        this.yIndex = yIndex;
        this.attack = attack;
        this.battle = battle;
        if (xIndex == 0) {
            if (yIndex == 0) {
                spellIndex = 0;
            }
            else {
                spellIndex = 1;
            }
        }
        else {
            if (yIndex == 0) {
                spellIndex = 2;
            }
            else {
                spellIndex = 3;
            }
        }
    }

    @Override public void render(Graphics g, GameContainer gc) {
        super.render(g, gc);

        if (xIndex == 0) {
            x = getScreenWidth() / 2 - SPELL_BUTTON_WIDTH - 10;
        }
        else {
            x = getScreenWidth() / 2 + 10;
        }

        if (yIndex == 0) {
            y = getScreenHeight() / 2 - SPELL_BUTTON_HEIGHT - 10;
        }
        else {
            y = getScreenHeight() / 2 + 10;
        }

        if (attack != null) {
            g.setColor(Color.white);
            double curEnergy = battle.getLeft().getEnergy();
            double energyRequired = battle.getLeft().getActiveUnit().getAttackCost(spellIndex);

            Media.drawAlignedString("Energy: " + (int) curEnergy + "/" + (int) energyRequired, x + SPELL_BUTTON_WIDTH / 2, y, Media.CENTER, Media.TOP, Media.defaultFontMedium, g);
            Media.drawAlignedString(attack.getNameOfElement(), x + SPELL_BUTTON_WIDTH / 2, y + SPELL_BUTTON_HEIGHT / 2, Media.CENTER, Media.CENTER, Media.defaultFontMedium, g);
            Media.drawAlignedString(attack.getName(), x + SPELL_BUTTON_WIDTH / 2, y + SPELL_BUTTON_HEIGHT, Media.CENTER, Media.BOTTOM, Media.defaultFontLarge, g);

            if (curEnergy < energyRequired) {
                g.setColor(new Color(0, 0, 0, 127));
                float shadowHeight = h * (float) (1 - curEnergy / energyRequired);
                g.fillRect(x, y + h - shadowHeight, w, shadowHeight);
            }
        }
        else {
            g.setColor(new Color(0, 0, 0, 127));
            g.fillRect(x, y, w, h);
        }
    }

    @Override public void onClick() {
        battle.chooseAttack(spellIndex);
    }

    public void updateAttack(Attack attack) {
        this.attack = attack;
    }
}
