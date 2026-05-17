package entities.creature.ice;

import combat.Elements;
import combat.attack.fire.*;
import combat.attack.ice.*;
import combat.attack.water.*;
import core.Media;
import entities.creature.Creature;

public class FrostLutin extends Creature {
    public FrostLutin(int level) {
        super(level);
        element = Elements.ICE;
        maxHealth = calculateLevelHealth();
        assignAttacks();
        name = "Frost Lutin";
        sheetLeft = Media.sheetCreatureFrostLutinLeft;
        sheetRight = Media.sheetCreatureFrostLutinRight;
        cost = 300;
    }

    public FrostLutin() {
        this(getDefaultLevel());
    }

    @Override public void assignAttacks() {
        if (curLevel < CREATURE_MAIN_SINGLE_UPGRADE_ONE) {
            mainSingleAttack = new IceSingleAttackOne(1, levelDamageBonus);
        }
        else {
            mainSingleAttack = new IceSingleAttackTwo(1, levelDamageBonus);
        }

        if (curLevel < CREATURE_MAIN_AREA_UPGRADE_ONE) {
            mainAreaAttack = new IceAreaAttackOne(1, levelDamageBonus);
        }
        else {
            mainAreaAttack = new IceAreaAttackTwo(1, levelDamageBonus);
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
