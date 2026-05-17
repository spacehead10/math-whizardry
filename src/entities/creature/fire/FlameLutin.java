package entities.creature.fire;

import combat.Elements;
import combat.attack.fire.*;
import combat.attack.storm.*;
import combat.attack.water.*;
import core.Media;
import entities.creature.Creature;

public class FlameLutin extends Creature {
    public FlameLutin(int level) {
        super(level);
        element = Elements.FIRE;
        maxHealth = calculateLevelHealth();
        assignAttacks();
        name = "Flame Lutin";
        sheetLeft = Media.sheetCreatureFlameLutinLeft;
        sheetRight = Media.sheetCreatureFlameLutinRight;
        cost = 300;
    }

    public FlameLutin() {
        this(getDefaultLevel());
    }

    @Override public void assignAttacks() {
        if (curLevel < CREATURE_MAIN_SINGLE_UPGRADE_ONE) {
            mainSingleAttack = new FireSingleAttackOne(1, levelDamageBonus);
        }
        else {
            mainSingleAttack = new FireSingleAttackTwo(1, levelDamageBonus);
        }

        if (curLevel < CREATURE_MAIN_AREA_UPGRADE_ONE){
            mainAreaAttack = new FireAreaAttackOne(1, levelDamageBonus);
        }
        else {
            mainAreaAttack = new FireAreaAttackTwo(1, levelDamageBonus);
        }

        if (curLevel < CREATURE_ALT_SINGLE_UNLOCK) {
            altSingleAttack = null;
        }
        else if (curLevel < CREATURE_ALT_SINGLE_UPGRADE_ONE){
            altSingleAttack = new StormSingleAttackOne(1, levelDamageBonus);
        }
        else {
            altSingleAttack = new StormSingleAttackTwo(1, levelDamageBonus);
        }

        if (curLevel < CREATURE_ALT_AREA_UNLOCK) {
            altAreaAttack = null;
        }
        else if (curLevel < CREATURE_ALT_AREA_UPGRADE_ONE) {
            altAreaAttack = new WaterAreaAttackOne(1, levelDamageBonus);
        }
        else {
            altAreaAttack = new WaterAreaAttackTwo(1, levelDamageBonus);
        }
    }

    @Override public double calculateLevelHealth() {
        return BASE_CREATURE_HEALTH + HEALTH_SCALING * Math.pow(curLevel - 1, 2);
    }
}
