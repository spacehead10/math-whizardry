package entities.creature.ice;

import combat.Elements;
import combat.attack.storm.*;
import combat.attack.ice.*;
import combat.attack.earth.*;
import core.Media;
import entities.creature.Creature;

public class Frostbite extends Creature {
    public Frostbite(int level) {
        super(level);
        element = Elements.ICE;
        maxHealth = calculateLevelHealth();
        assignAttacks();
        name = "Frostbite";
        sheetLeft = null;
        sheetRight = null;
        cost = 450;
    }

    public Frostbite() {
        this(getDefaultLevel());
    }

    @Override public void assignAttacks() {
        if (curLevel < CREATURE_MAIN_SINGLE_UPGRADE_ONE) {
            mainSingleAttack = new IceSingleAttackOne(1.5, levelDamageBonus);
        }
        else {
            mainSingleAttack = new IceSingleAttackTwo(1.5, levelDamageBonus);
        }

        if (curLevel < CREATURE_MAIN_AREA_UPGRADE_ONE) {
            mainAreaAttack = new IceAreaAttackOne(1.5, levelDamageBonus);
        }
        else {
            mainAreaAttack = new IceAreaAttackTwo(1.5, levelDamageBonus);
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
            altAreaAttack = new EarthAreaAttackOne(1.5, levelDamageBonus);
        }
        else {
            altAreaAttack = new EarthAreaAttackTwo(1.5, levelDamageBonus);
        }
    }

    @Override public double calculateLevelHealth() {
        return 0.67 * (BASE_CREATURE_HEALTH + HEALTH_SCALING * Math.pow(curLevel - 1, 2));
    }
}
