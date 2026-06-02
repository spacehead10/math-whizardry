package entities.player;

import combat.Elements;
import combat.attack.light.*;
import combat.combatUnit.CombatUnit;
import combat.combatUnit.PlayerCombatUnit;
import core.Media;
import entities.Entity;
import item.Inventory;
import item.relic.Relic;
import item.wand.Wand;
import pet.PetSelector;
import world.worldUnit.PlayerWorldUnit;

public class Player extends Entity {
    private static Inventory inventory;
    private static PetSelector petSelector;

    public Player() {
        super(DEFAULT_STARTING_LEVEL);

        inventory = new Inventory();

        element = Elements.LIGHT;
        assignAttacks();
        name = "Wizard";

        petSelector = new PetSelector();
        sheetLeft = Media.sheetWizardLeft;
        sheetRight = Media.sheetWizardRight;
    }

    @Override public void assignAttacks() {
        Relic relic = inventory.getEquippedRelic();
        Wand wand = inventory.getEquippedWand();

        double damageMultiplier = 1;
        if (wand != null) {
            damageMultiplier = wand.getWandDamageMultiplier();
        }

        if (curLevel < PLAYER_MAIN_SINGLE_UPGRADE_ONE) {
            mainSingleAttack = new LightSingleAttackOne(damageMultiplier, levelDamageBonus);
        }
        else if (curLevel < PLAYER_MAIN_SINGLE_UPGRADE_TWO) {
            mainSingleAttack = new LightSingleAttackTwo(damageMultiplier, levelDamageBonus);
        }
        else {
            mainSingleAttack = new LightSingleAttackThree(damageMultiplier, levelDamageBonus);
        }

        if (curLevel < PLAYER_MAIN_AREA_UPGRADE_ONE) {
            mainAreaAttack = new LightAreaAttackOne(damageMultiplier, levelDamageBonus);
        }
        else {
            mainAreaAttack = new LightAreaAttackTwo(damageMultiplier, levelDamageBonus);
        }

        if (relic != null) {
            try {
                altSingleAttack = relic.getAttack().getDeclaredConstructor(double.class, double.class).newInstance(damageMultiplier, levelDamageBonus);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (wand != null) {
            try {
                altAreaAttack = wand.getAttack().getDeclaredConstructor(double.class, double.class).newInstance(damageMultiplier, levelDamageBonus);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override public CombatUnit getNewCombatUnit() {
        return new PlayerCombatUnit(this);
    }

    @Override public PlayerWorldUnit getNewWorldUnit() {
        return new PlayerWorldUnit(this);
    }

    public static Inventory getInventory() {
        return inventory;
    }

    public static PetSelector getPetSelector() {
        return petSelector;
    }

    @Override public double calculateLevelHealth() {
        return BASE_PLAYER_HEALTH + HEALTH_SCALING * Math.pow(curLevel - 1, 2);
    }
}
