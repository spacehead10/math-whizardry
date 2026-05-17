package combat.combatUnit;

import combat.Battle;
import combat.Elements;
import combat.Team;
import combat.attack.Attack;
import core.Values;
import entities.Entity;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import popup.message.FloatMessage;

import static core.Main.getScreenWidth;
import static core.Main.getScreenHeight;
import static popup.PopupManager.addPopup;

public abstract class CombatUnit implements Values {
    protected double curHealth, maxHealth;
    protected Entity entity;
    protected boolean isOnLeftSide;
    protected int position;
    protected double animationTimer;
    protected int animationTick;

    public CombatUnit(Entity entity) {
        this.entity = entity;
        maxHealth = entity.getMaxHealth();
        curHealth = maxHealth;

        animationTimer = 0;
        animationTick = 0;
    }

    public void draw(Graphics g) {
        animationTimer += ANIMATION_TIMER_RATE;
        if (animationTimer >= 6) {
            animationTimer = 0;
        }
        animationTick = (int) animationTimer;
        if (isOnLeftSide) {
            float x = switch (position) {
                case 0 -> getScreenWidth() * 0.15f;
                default -> getScreenWidth() * 0.075f;
            };
            float yPercent = switch (position) {
                case 0 -> 0.45f;
                case 1 -> 0.35f;
                case 2 -> 0.55f;
                default -> 0;
            };
            SpriteSheet sheet = entity.getSheetLeft();
            if (sheet != null) {
                Image image = sheet.getSprite(animationTick, 0);
                if (image != null) {
                    image.draw(x - image.getWidth() / 2, getScreenHeight() * yPercent - image.getHeight() / 2);
                }
            }
        }
        else {
            float x = switch (position) {
                case 0 -> getScreenWidth() * 0.85f;
                default -> getScreenWidth() * 0.925f;
            };
            float yPercent = switch (position) {
                case 0 -> 0.45f;
                case 1 -> 0.35f;
                case 2 -> 0.55f;
                default -> 0;
            };
            SpriteSheet sheet = entity.getSheetRight();
            if (sheet != null) {
                Image image = sheet.getSprite(animationTick, 0);
                if (image != null) {
                    image.draw(x - image.getWidth() / 2, getScreenHeight() * yPercent - image.getHeight() / 2);
                }
            }
        }
    }

    public void takeDamage(Attack attack, boolean missed) {
        boolean attackWasStrong = false;
        boolean attackWasWeak = false;
        boolean attackWasCritical = Math.random() < CRIT_CHANCE;

        double modifiedDamage = attack.getDamage();

        if (missed) {
            modifiedDamage = 0;
        }
        else {
            if (Battle.isStrong(attack, this)) {
                modifiedDamage *= STRONG_ATTACK_MULTIPLIER;
                attackWasStrong = true;
            }
            else if (Battle.isWeak(attack, this)) {
                modifiedDamage *= WEAK_ATTACK_MULTIPLIER;
                attackWasWeak = true;
            }

            if (attackWasCritical) {
                modifiedDamage *= CRIT_MULTIPLIER;
            }
        }

        curHealth -= modifiedDamage;
        if (curHealth < 0.5) {
            curHealth = 0;
        }

        float x;
        if (isOnLeftSide) {
            x = switch (position) {
                case 0 -> getScreenWidth() * 0.15f;
                default -> getScreenWidth() * 0.075f;
            };
        }
        else {
            x = switch (position) {
                case 0 -> getScreenWidth() * 0.85f;
                default -> getScreenWidth() * 0.925f;
            };
        }

        float yPercent = switch (position) {
            case 0 -> 0.45f;
            case 1 -> 0.35f;
            case 2 -> 0.55f;
            default -> 0;
        };
        float yOffset = 10;

        if (missed) {
            addPopup(new FloatMessage("Miss!", x, getScreenHeight() * yPercent - yOffset, Color.cyan, 150));
        }
        else {
            String displayedDamage;
            if (isOnLeftSide) { //round up displayed damage done to the player's side so the player doesn't think they can take more hits than they actually can
                displayedDamage = "" + (int) Math.ceil(modifiedDamage);
            }
            else { //round down displayed damage done to the enemy's side so the player doesn't think they can destroy the enemy in fewer hits than they actually can
                displayedDamage = "" + (int) modifiedDamage;
            }
            addPopup(new FloatMessage("-" + displayedDamage, x, getScreenHeight() * yPercent - yOffset, Color.yellow, 150));
            if (attackWasCritical) {
                addPopup(new FloatMessage("CRITICAL!", x, getScreenHeight() * (yPercent + 0.05f) - yOffset, new Color(255, 127, 0), 150));
            }
            else if (attackWasStrong) {
                addPopup(new FloatMessage("Strong!", x, getScreenHeight() * (yPercent + 0.05f) - yOffset, Color.white, 150));
            }
            else if (attackWasWeak) {
                addPopup(new FloatMessage("Weak...", x, getScreenHeight() * (yPercent + 0.05f) - yOffset, Color.white, 150));
            }
        }
    }

    public void remove() {
        curHealth = 0;
    }

    public void attack(int index, Team t, boolean missed) {
        getAttack(index).use(t, missed);
    }

    public void setSide(boolean left) {
        isOnLeftSide = left;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Elements getElement() {
        return entity.getElement();
    }

    public Entity getEntity() {
        return entity;
    }

    public Attack getAttack(int index) {
        return entity.getAttack(index);
    }

    public String getName() {
        return entity.getName();
    }

    public String getNameOfElement() {
        return entity.getNameOfElement();
    }

    public double getAttackCost(int index) {
        return switch (index) {
            case 0 -> MAIN_SINGLE_ATTACK_COST;
            case 1 -> MAIN_AREA_ATTACK_COST;
            case 2 -> ALT_SINGLE_ATTACK_COST;
            case 3 -> ALT_AREA_ATTACK_COST;
            default -> -1;
        };
    }

    public double getCurHealth() {
        return curHealth;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public double getPercentHealth() {
        return curHealth / maxHealth;
    }

    public int getLevel() {
        return entity.getLevel();
    }
}
