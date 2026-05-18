package entities.creature.storm;

import combat.Elements;
import combat.attack.fire.*;
import combat.attack.earth.*;
import combat.attack.storm.*;
import core.Media;
import entities.creature.Creature;

public class Voltiger extends Creature {
    public Voltiger(int level) {
        super(level);
        element = Elements.STORM;
        maxHealth = calculateLevelHealth();
        assignAttacks();
        name = "Voltiger";
        sheetLeft = Media.sheetCreatureVoltigerLeft;
        sheetRight = Media.sheetCreatureVoltigerRight;
        cost = 450;
    }

    public Voltiger() {
        this(getDefaultLevel());
    }

    @Override public void assignAttacks() {
        if (curLevel < CREATURE_MAIN_SINGLE_UPGRADE_ONE) {
            mainSingleAttack = new StormSingleAttackOne(1.5, levelDamageBonus);
        }
        else {
            mainSingleAttack = new StormSingleAttackTwo(1.5, levelDamageBonus);
        }

        if (curLevel < CREATURE_MAIN_AREA_UPGRADE_ONE) {
            mainAreaAttack = new StormAreaAttackOne(1.5, levelDamageBonus);
        }
        else {
            mainAreaAttack = new StormAreaAttackTwo(1.5, levelDamageBonus);
        }

        if (curLevel < CREATURE_ALT_SINGLE_UNLOCK) {
            altSingleAttack = null;
        }
        else if (curLevel < CREATURE_ALT_SINGLE_UPGRADE_ONE) {
            altSingleAttack = new EarthSingleAttackOne(1.5, levelDamageBonus);
        }
        else {
            altSingleAttack = new EarthSingleAttackTwo(1.5, levelDamageBonus);
        }

        if (curLevel < CREATURE_ALT_AREA_UNLOCK) {
            altAreaAttack = null;
        }
        else if (curLevel < CREATURE_ALT_AREA_UPGRADE_ONE) {
            altAreaAttack = new FireAreaAttackOne(1.5, levelDamageBonus);
        }
        else {
            altAreaAttack = new FireAreaAttackTwo(1.5, levelDamageBonus);
        }
    }

    @Override public double calculateLevelHealth() {
        return 0.67 * (BASE_CREATURE_HEALTH + HEALTH_SCALING * Math.pow(curLevel - 1, 2));
    }
}
