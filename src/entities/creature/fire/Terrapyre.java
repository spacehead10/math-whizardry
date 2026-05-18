package entities.creature.fire;

import combat.Elements;
import combat.attack.earth.*;
import combat.attack.fire.*;
import combat.attack.water.*;
import core.Media;
import entities.creature.Creature;

public class Terrapyre extends Creature {
    public Terrapyre(int level) {
        super(level);
        element = Elements.FIRE;
        maxHealth = calculateLevelHealth();
        assignAttacks();
        name = "Terrapyre";
        sheetLeft = Media.sheetCreatureTerrapyreLeft;
        sheetRight = Media.sheetCreatureTerrapyreRight;
        cost = 450;
    }

    public Terrapyre() {
        this(getDefaultLevel());
    }

    @Override public void assignAttacks() {
        if (curLevel < CREATURE_MAIN_SINGLE_UPGRADE_ONE) {
            mainSingleAttack = new FireSingleAttackOne(0.67, levelDamageBonus);
        }
        else {
            mainSingleAttack = new FireSingleAttackTwo(0.67, levelDamageBonus);
        }

        if (curLevel < CREATURE_MAIN_AREA_UPGRADE_ONE){
            mainAreaAttack = new FireAreaAttackOne(0.67, levelDamageBonus);
        }
        else {
            mainAreaAttack = new FireAreaAttackTwo(0.67, levelDamageBonus);
        }

        if (curLevel < CREATURE_ALT_SINGLE_UNLOCK) {
            altSingleAttack = null;
        }
        else if (curLevel < CREATURE_ALT_SINGLE_UPGRADE_ONE){
            altSingleAttack = new EarthSingleAttackOne(0.67, levelDamageBonus);
        }
        else {
            altSingleAttack = new EarthSingleAttackTwo(0.67, levelDamageBonus);
        }

        if (curLevel < CREATURE_ALT_AREA_UNLOCK) {
            altAreaAttack = null;
        }
        else if (curLevel < CREATURE_ALT_AREA_UPGRADE_ONE) {
            altAreaAttack = new WaterAreaAttackOne(0.67, levelDamageBonus);
        }
        else {
            altAreaAttack = new WaterAreaAttackTwo(0.67, levelDamageBonus);
        }
    }

    @Override public double calculateLevelHealth() {
        return 1.5 * (BASE_CREATURE_HEALTH + HEALTH_SCALING * Math.pow(curLevel - 1, 2));
    }
}
