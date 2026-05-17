package entities.creature.earth;

import combat.Elements;
import combat.attack.earth.*;
import combat.attack.fire.*;
import combat.attack.water.*;
import core.Media;
import entities.creature.Creature;

public class GardenLutin extends Creature {
    public GardenLutin(int level) {
        super(level);
        element = Elements.EARTH;
        maxHealth = calculateLevelHealth();
        assignAttacks();
        name = "Garden Lutin";
        sheetLeft = Media.sheetCreatureGardenLutinLeft;
        sheetRight = Media.sheetCreatureGardenLutinRight;
        cost = 300;
    }

    public GardenLutin() {
        this(getDefaultLevel());
    }

    @Override public void assignAttacks() {
        if (curLevel < CREATURE_MAIN_SINGLE_UPGRADE_ONE) {
            mainSingleAttack = new EarthSingleAttackOne(1, levelDamageBonus);
        }
        else {
            mainSingleAttack = new EarthSingleAttackTwo(1, levelDamageBonus);
        }

        if (curLevel < CREATURE_MAIN_AREA_UPGRADE_ONE) {
            mainAreaAttack = new EarthAreaAttackOne(1, levelDamageBonus);
        }
        else {
            mainAreaAttack = new EarthAreaAttackTwo(1, levelDamageBonus);
        }

        if (curLevel < CREATURE_ALT_SINGLE_UNLOCK) {
            altSingleAttack = null;
        }
        else if (curLevel < CREATURE_ALT_SINGLE_UPGRADE_ONE) {
            altSingleAttack = new WaterSingleAttackOne(1, levelDamageBonus);
        }
        else {
            altSingleAttack = new WaterSingleAttackTwo(1, levelDamageBonus);
        }

        if (curLevel < CREATURE_ALT_AREA_UNLOCK) {
            altAreaAttack = null;
        }
        else if (curLevel < CREATURE_ALT_AREA_UPGRADE_ONE) {
            altAreaAttack = new FireAreaAttackOne(1, levelDamageBonus);
        }
        else {
            altAreaAttack = new FireAreaAttackTwo(1, levelDamageBonus);
        }
    }

    @Override public double calculateLevelHealth() {
        return BASE_CREATURE_HEALTH + HEALTH_SCALING * Math.pow(curLevel - 1, 2);
    }
}
