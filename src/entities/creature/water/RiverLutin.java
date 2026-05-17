package entities.creature.water;

import combat.Elements;
import combat.attack.earth.*;
import combat.attack.ice.*;
import combat.attack.water.*;
import core.Media;
import entities.creature.Creature;

public class RiverLutin extends Creature {
    public RiverLutin(int level) {
        super(level);
        element = Elements.WATER;
        maxHealth = calculateLevelHealth();
        assignAttacks();
        name = "River Lutin";
        sheetLeft = Media.sheetCreatureRiverLutinLeft;
        sheetRight = Media.sheetCreatureRiverLutinRight;
        cost = 300;
    }

    public RiverLutin() {
        this(getDefaultLevel());
    }

    @Override public void assignAttacks() {
        if (curLevel < CREATURE_MAIN_SINGLE_UPGRADE_ONE) {
            mainSingleAttack = new WaterSingleAttackOne(1, levelDamageBonus);
        }
        else {
            mainSingleAttack = new WaterSingleAttackTwo(1, levelDamageBonus);
        }

        if (curLevel < CREATURE_MAIN_AREA_UPGRADE_ONE) {
            mainAreaAttack = new WaterAreaAttackOne(1, levelDamageBonus);
        }
        else {
            mainAreaAttack = new WaterAreaAttackTwo(1, levelDamageBonus);
        }

        if (curLevel < CREATURE_ALT_SINGLE_UNLOCK) {
            altSingleAttack = null;
        }
        else if (curLevel < CREATURE_ALT_SINGLE_UPGRADE_ONE) {
            altSingleAttack = new IceSingleAttackOne(1, levelDamageBonus);
        }
        else {
            altSingleAttack = new IceSingleAttackTwo(1, levelDamageBonus);
        }

        if (curLevel < CREATURE_ALT_AREA_UNLOCK) {
            altAreaAttack = null;
        }
        else if (curLevel < CREATURE_ALT_AREA_UPGRADE_ONE) {
            altAreaAttack = new EarthAreaAttackOne(1, levelDamageBonus);
        }
        else {
            altAreaAttack = new EarthAreaAttackTwo(1, levelDamageBonus);
        }
    }

    @Override public double calculateLevelHealth() {
        return BASE_CREATURE_HEALTH + HEALTH_SCALING * Math.pow(curLevel - 1, 2);
    }
}
