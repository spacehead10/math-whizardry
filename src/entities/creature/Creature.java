package entities.creature;

import combat.combatUnit.CombatUnit;
import combat.combatUnit.CreatureCombatUnit;
import entities.Entity;
import entities.player.Player;
import world.World;
import world.worldUnit.CreatureWorldUnit;
import world.worldUnit.WorldUnit;

public abstract class Creature extends Entity {
    protected int cost;

    public Creature(int level) {
        super(level);
    }

    @Override public CombatUnit getNewCombatUnit() {
        return new CreatureCombatUnit(this);
    }

    @Override public CreatureWorldUnit getNewWorldUnit() {
        return new CreatureWorldUnit(this);
    }

    public static int getDefaultLevel() {
        Player player = World.getPlayer();
        if (player.getLevel() < DEFAULT_STARTING_LEVEL + ENEMY_LEVEL_DIFFERENCE) {
            return DEFAULT_STARTING_LEVEL;
        }
        return player.getLevel() - ENEMY_LEVEL_DIFFERENCE;
    }

    public int getCost() {
        return cost;
    }
}
