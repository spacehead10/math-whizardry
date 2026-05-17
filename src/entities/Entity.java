package entities;

import combat.Elements;
import combat.attack.Attack;
import combat.combatUnit.CombatUnit;
import core.Values;
import org.newdawn.slick.SpriteSheet;
import world.worldUnit.WorldUnit;

public abstract class Entity implements Values {
    protected Elements element;
    protected double maxHealth;
    protected Attack mainSingleAttack;
    protected Attack mainAreaAttack;
    protected Attack altSingleAttack;
    protected Attack altAreaAttack;
    protected String name;
    protected int curLevel, maxLevel;
    protected double curXP, maxXP;
    protected double levelDamageBonus;
    protected SpriteSheet sheetLeft, sheetRight;

    public Entity(int level) {
        curLevel = level;
        maxLevel = DEFAULT_MAX_LEVEL;
        curXP = 0;
        maxXP = calculateLevelXPRequirement();
        maxHealth = calculateLevelHealth();
        levelDamageBonus = calculateLevelDamageBonus();
    }

    public void initFromSave(int level, double xp) {
        curLevel = level;
        curXP = xp;
        maxXP = calculateLevelXPRequirement();
        maxHealth = calculateLevelHealth();
        levelDamageBonus = calculateLevelDamageBonus();
        assignAttacks();
    }

    public void gainXP(double amount) {
        curXP += amount;
        while (curXP >= maxXP) {
            curXP -= maxXP;
            levelUp();
        }
    }

    private void levelUp() {
        if (curLevel == maxLevel) {
            curXP = 0;
        }
        else {
            curLevel++;
            maxXP = calculateLevelXPRequirement();
            maxHealth = calculateLevelHealth();
            levelDamageBonus = calculateLevelDamageBonus();
            assignAttacks();
        }
    }

    public abstract void assignAttacks();

    public Elements getElement() {
        return element;
    }

    public Attack getAttack(int index) {
        Attack[] attacks = new Attack[] {mainSingleAttack, mainAreaAttack, altSingleAttack, altAreaAttack};
        return attacks[index];
    }

    public abstract CombatUnit getNewCombatUnit();

    public abstract WorldUnit getNewWorldUnit();

    public SpriteSheet getSheetLeft() {
        return sheetLeft;
    }

    public SpriteSheet getSheetRight() {
        return sheetRight;
    }

    public String getName() {
        return name;
    }

    public String getNameOfElement() {
        return switch (element) {
            case LIGHT -> "Light";
            case FIRE -> "Fire";
            case WATER -> "Water";
            case STORM -> "Storm";
            case ICE -> "Ice";
            case EARTH -> "Earth";
        };
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public double calculateLevelXPRequirement() {
        return BASE_XP_REQUIREMENT + XP_SCALING * (curLevel - 1);
    }

    public abstract double calculateLevelHealth();

    public double calculateLevelDamageBonus() {
        return POWER_SCALING * Math.pow(curLevel - 1, 2);
    }

    public double getCurXP() {
        return curXP;
    }

    public int getLevel() {
        return curLevel;
    }

    public String getXPRatio() {
        return curXP + "/" + maxXP;
    }
}
