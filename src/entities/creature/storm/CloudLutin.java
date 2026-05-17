package entities.creature.storm;

import combat.Elements;
import combat.attack.fire.*;
import combat.attack.ice.*;
import combat.attack.storm.*;
import core.Media;
import entities.creature.Creature;

public class CloudLutin extends Creature {
    public CloudLutin(int level) {
        super(level);
        element = Elements.STORM;
        maxHealth = calculateLevelHealth();
        assignAttacks();
        name = "Cloud Lutin";
        sheetLeft = Media.sheetCreatureCloudLutinLeft;
        sheetRight = Media.sheetCreatureCloudLutinRight;
        cost = 300;
    }

    public CloudLutin() {
        this(getDefaultLevel());
    }

    @Override public void assignAttacks() {
        if (curLevel < CREATURE_MAIN_SINGLE_UPGRADE_ONE) {
            mainSingleAttack = new StormSingleAttackOne(1, levelDamageBonus);
        }
        else {
            mainSingleAttack = new StormSingleAttackTwo(1, levelDamageBonus);
        }

        if (curLevel < CREATURE_MAIN_AREA_UPGRADE_ONE) {
            mainAreaAttack = new StormAreaAttackOne(1, levelDamageBonus);
        }
        else {
            mainAreaAttack = new StormAreaAttackTwo(1, levelDamageBonus);
        }

        if (curLevel < CREATURE_ALT_SINGLE_UNLOCK) {
            altSingleAttack = null;
        }
        else if (curLevel < CREATURE_ALT_SINGLE_UPGRADE_ONE) {
            altSingleAttack = new FireSingleAttackOne(1, levelDamageBonus);
        }
        else {
            altSingleAttack = new FireSingleAttackTwo(1, levelDamageBonus);
        }

        if (curLevel < CREATURE_ALT_AREA_UNLOCK) {
            altAreaAttack = null;
        }
        else if (curLevel < CREATURE_ALT_AREA_UPGRADE_ONE) {
            altAreaAttack = new IceAreaAttackOne(1, levelDamageBonus);
        }
        else {
            altAreaAttack = new IceAreaAttackTwo(1, levelDamageBonus);
        }
    }

    @Override public double calculateLevelHealth() {
        return BASE_CREATURE_HEALTH + HEALTH_SCALING * Math.pow(curLevel - 1, 2);
    }
}
