package entities.creature.water;

import combat.Elements;
import combat.attack.storm.*;
import combat.attack.ice.*;
import combat.attack.water.*;
import core.Media;
import entities.creature.Creature;

public class Hydrocto extends Creature {
    public Hydrocto(int level) {
        super(level);
        element = Elements.WATER;
        maxHealth = calculateLevelHealth();
        assignAttacks();
        name = "Hydrocto";
        sheetLeft = Media.sheetCreatureHydroctoLeft;
        sheetRight = Media.sheetCreatureHydroctoRight;
        cost = 450;
    }

    public Hydrocto() {
        this(getDefaultLevel());
    }

    @Override public void assignAttacks() {
        if (curLevel < CREATURE_MAIN_SINGLE_UPGRADE_ONE) {
            mainSingleAttack = new WaterSingleAttackOne(1.5, levelDamageBonus);
        }
        else {
            mainSingleAttack = new WaterSingleAttackTwo(1.5, levelDamageBonus);
        }

        if (curLevel < CREATURE_MAIN_AREA_UPGRADE_ONE) {
            mainAreaAttack = new WaterAreaAttackOne(1.5, levelDamageBonus);
        }
        else {
            mainAreaAttack = new WaterAreaAttackTwo(1.5, levelDamageBonus);
        }

        if (curLevel < CREATURE_ALT_SINGLE_UNLOCK) {
            altSingleAttack = null;
        }
        else if (curLevel < CREATURE_ALT_SINGLE_UPGRADE_ONE) {
            altSingleAttack = new StormSingleAttackOne(1.5, levelDamageBonus);
        }
        else {
            altSingleAttack = new StormSingleAttackTwo(1.5, levelDamageBonus);
        }

        if (curLevel < CREATURE_ALT_AREA_UNLOCK) {
            altAreaAttack = null;
        }
        else if (curLevel < CREATURE_ALT_AREA_UPGRADE_ONE) {
            altAreaAttack = new IceAreaAttackOne(1.5, levelDamageBonus);
        }
        else {
            altAreaAttack = new IceAreaAttackTwo(1.5, levelDamageBonus);
        }
    }

    @Override public double calculateLevelHealth() {
        return 0.67 * (BASE_CREATURE_HEALTH + HEALTH_SCALING * Math.pow(curLevel - 1, 2));
    }
}
