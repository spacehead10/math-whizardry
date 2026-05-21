package combat;

import combat.attack.Attack;
import combat.combatUnit.CombatUnit;
import core.Media;
import core.Values;
import entities.Entity;
import entities.creature.Creature;
import entities.creature.earth.GardenLutin;
import entities.creature.fire.FlameLutin;
import entities.creature.fire.Terrapyre;
import entities.creature.ice.FrostLutin;
import entities.creature.ice.Frostbite;
import entities.creature.storm.CloudLutin;
import entities.creature.storm.Voltiger;
import entities.creature.water.RiverLutin;
import entities.player.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import world.World;

import java.text.DecimalFormat;

import static core.Utils.randomIntInRange;
import static core.Main.getScreenWidth;

public class Team implements Values {
    private CombatUnit activeUnit;
    private CombatUnit waitingUnitOne, waitingUnitTwo;
    private boolean lost;
    private double energy;
    private boolean isOnLeftSide;

    public Team(Entity mainEntity, Entity allyOne, Entity allyTwo) {
        activeUnit = mainEntity.getNewCombatUnit();
        if (allyOne != null) {
            waitingUnitOne = allyOne.getNewCombatUnit();
        }
        if (allyTwo != null) {
            waitingUnitTwo = allyTwo.getNewCombatUnit();
        }
        lost = false;
        energy = 0;
    }

    public Team(Player player) {
        this(player, Player.getPetSelector().getPetInTeam(0), Player.getPetSelector().getPetInTeam(1));
    }

    public Team(Creature creature) {
        this(creature, getAlly(creature), getAlly(creature));
    }

    public void drawMembers(Graphics g) {
        if (waitingUnitOne != null) {
            waitingUnitOne.draw(g);
        }
        if (activeUnit != null) {
            activeUnit.draw(g);
        }
        if (waitingUnitTwo != null) {
            waitingUnitTwo.draw(g);
        }
    }

    public void drawUI(Graphics g) {
        final float barCenterY = 50;

        //energy meter
        final float energyMeterWidth = 80;
        final float energyMeterMaxHeight = 80;
        final float energyMeterX = 10;
        final float energyMeterY = barCenterY - energyMeterMaxHeight / 2;
        float energyMeterHeight = energyMeterMaxHeight * (float) (energy / MAX_ENERGY);
        if (isOnLeftSide) {
            g.setColor(Color.gray.darker());
            g.fillRect(energyMeterX, energyMeterY, energyMeterWidth, energyMeterMaxHeight);
            g.setColor(Color.yellow);
            g.fillRect(energyMeterX, energyMeterY + energyMeterMaxHeight - energyMeterHeight, energyMeterWidth, energyMeterHeight);
        }
        else {
            g.setColor(Color.gray.darker());
            g.fillRect(getScreenWidth() - energyMeterWidth - energyMeterX, energyMeterY, energyMeterWidth, energyMeterMaxHeight);
            g.setColor(Color.yellow);
            g.fillRect(getScreenWidth() - energyMeterWidth - energyMeterX, energyMeterY + energyMeterMaxHeight - energyMeterHeight, energyMeterWidth, energyMeterHeight);
        }

        //healthbar
        DecimalFormat df = new DecimalFormat("0");
        final float healthbarHeight = 50;
        final float healthbarMaxWidth = 300;
        final float healthbarY = barCenterY - healthbarHeight / 2;
        final float healthbarX = energyMeterX + energyMeterWidth;
        float healthbarWidth;
        String healthText;
        if (activeUnit == null) {
            healthbarWidth = 0;
            healthText = "0";
        }
        else {
            healthbarWidth = healthbarMaxWidth * (float) (activeUnit.getCurHealth() / activeUnit.getMaxHealth());
            healthText = df.format(activeUnit.getCurHealth()) + "/" + df.format(activeUnit.getMaxHealth());
        }
        if (isOnLeftSide) {
            g.setColor(Color.gray.darker());
            g.fillRect(healthbarX, healthbarY, healthbarMaxWidth, healthbarHeight);
            g.setColor(Color.red);
            g.fillRect(healthbarX, healthbarY, healthbarWidth, healthbarHeight);
            g.setColor(Color.white);
            Media.drawAlignedString(healthText, healthbarX + 10, barCenterY, Media.LEFT, Media.CENTER, Media.defaultFontTiny, g);
        }
        else {
            g.setColor(Color.gray.darker());
            g.fillRect(getScreenWidth() - healthbarMaxWidth - healthbarX, healthbarY, healthbarMaxWidth, healthbarHeight);
            g.setColor(Color.red);
            g.fillRect(getScreenWidth() - healthbarWidth - healthbarX, healthbarY, healthbarWidth, healthbarHeight);
            g.setColor(Color.white);
            Media.drawAlignedString(healthText, getScreenWidth() - healthbarX - 10, barCenterY, Media.RIGHT, Media.CENTER, Media.defaultFontTiny, g);
        }

        //name and level
        if (activeUnit != null) {
            g.setColor(Color.white);
            if (isOnLeftSide) {
                Media.drawAlignedString(activeUnit.getName(), healthbarX + 20, healthbarY + healthbarHeight, Media.LEFT, Media.TOP, Media.defaultFontSmall, g);
                g.setColor(Color.green);
                Media.drawAlignedString("Lvl " + activeUnit.getLevel(), healthbarX + 20, healthbarY + healthbarHeight + Media.defaultFontSmall.getHeight(), Media.LEFT, Media.TOP, Media.defaultFontTiny, g);
            }
            else {
                Media.drawAlignedString(activeUnit.getName(), getScreenWidth() - healthbarX - 20, healthbarY + healthbarHeight, Media.RIGHT, Media.TOP, Media.defaultFontSmall, g);
                g.setColor(Color.green);
                Media.drawAlignedString("Lvl " + activeUnit.getLevel(), getScreenWidth() - healthbarX - 20, healthbarY + healthbarHeight + Media.defaultFontSmall.getHeight(), Media.RIGHT, Media.TOP, Media.defaultFontTiny, g);
            }
        }
    }

    public void takeDamage(Attack attack, boolean area, boolean missed) {
        activeUnit.takeDamage(attack, missed);
        if (area) {
            if (waitingUnitOne != null) {
                waitingUnitOne.takeDamage(attack, missed);
            }
            if (waitingUnitTwo != null) {
                waitingUnitTwo.takeDamage(attack, missed);
            }
        }

        cleanup();
    }

    public void cleanup() {
        if (activeUnit.getCurHealth() <= 0) {
            activeUnit = null;
        }
        if (waitingUnitOne != null && waitingUnitOne.getCurHealth() <= 0) {
            waitingUnitOne = null;
        }
        if (waitingUnitTwo != null && waitingUnitTwo.getCurHealth() <= 0) {
            waitingUnitTwo = null;
        }

        if (activeUnit == null) {
            if (waitingUnitOne != null) {
                swap(0);
            }
            else if (waitingUnitTwo != null) {
                swap(1);
            }
            else {
                lost = true;
            }
        }
    }

    public void attack(int index, Team t, boolean missed) {
        activeUnit.attack(index, t, missed);
    }

    public void swap(int index) {
        CombatUnit temp = activeUnit;
        if (index == 0) {
            activeUnit = waitingUnitOne;
            waitingUnitOne = temp;
        }
        else if (index == 1) {
            activeUnit = waitingUnitTwo;
            waitingUnitTwo = temp;
        }

        if (activeUnit != null) {
            activeUnit.setPosition(0);
        }
        if (waitingUnitOne != null) {
            waitingUnitOne.setPosition(1);
        }
        if (waitingUnitTwo != null) {
            waitingUnitTwo.setPosition(2);
        }
    }

    public void rechargeEnergy() {
        energy += RECHARGE_AMOUNT;
        if (energy > MAX_ENERGY) {
            energy = MAX_ENERGY;
        }
    }

    public void spendEnergy(double amount) {
        energy -= amount;
    }

    public void gainXP(double amount) {
        World.getPlayer().gainXP(amount);
        if (Player.getPetSelector().getPetInTeam(0) != null) {
            Player.getPetSelector().getPetInTeam(0).gainXP(amount);
        }
        if (Player.getPetSelector().getPetInTeam(1) != null) {
            Player.getPetSelector().getPetInTeam(1).gainXP(amount);
        }
    }

    public void setSide(boolean isLeft) {
        isOnLeftSide = isLeft;
    }

    public CombatUnit getActiveUnit() {
        return activeUnit;
    }

    public CombatUnit getWaitingUnitOne() {
        return waitingUnitOne;
    }

    public CombatUnit getWaitingUnitTwo() {
        return waitingUnitTwo;
    }

    public static Creature getAlly(Creature mainCreature) {
        if (Player.getPetSelector().getPets().isEmpty() && World.getPlayer().getLevel() < 3) {
            return null;
        }

        int nullChanceWeighting = 10 - 2 * Player.getPetSelector().getPets().size() - World.getPlayer().getLevel();
        if (nullChanceWeighting < 0) {
            nullChanceWeighting = 0;
        }

        int noAllyRNG = randomIntInRange(0, 5 + nullChanceWeighting);
        if (noAllyRNG > 4) {
            return null;
        }

        double allyTypeRNG = Math.random();
        if (mainCreature instanceof FlameLutin) {
            if (allyTypeRNG < 0.8) {
                return new FlameLutin();
            }
            else {
                return new CloudLutin();
            }
        }
        else if (mainCreature instanceof CloudLutin) {
            if (allyTypeRNG < 0.6) {
                return new CloudLutin();
            }
            else if (allyTypeRNG < 0.9) {
                return new FlameLutin();
            }
            else {
                return new GardenLutin();
            }
        }
        else if (mainCreature instanceof FrostLutin) {
            if (allyTypeRNG < 0.6) {
                return new FrostLutin();
            }
            else if (allyTypeRNG < 0.9) {
                return new RiverLutin();
            }
            else {
                return new GardenLutin();
            }
        }
        else if (mainCreature instanceof RiverLutin) {
            if (allyTypeRNG < 0.8) {
                return new RiverLutin();
            }
            else {
                return new FrostLutin();
            }
        }
        else if (mainCreature instanceof GardenLutin) {
            if (allyTypeRNG < 0.6) {
                return new GardenLutin();
            }
            else if (allyTypeRNG < 0.9) {
                return new CloudLutin();
            }
            else {
                return new FrostLutin();
            }
        }
        else if (mainCreature instanceof Terrapyre) {
            return new Terrapyre();
        }
        else if (mainCreature instanceof Frostbite) {
            return new Frostbite();
        }
        else if (mainCreature instanceof Voltiger) {
            return new Voltiger();
        }
        else {
            if (allyTypeRNG < 0.2) {
                return new GardenLutin();
            }
            else if (allyTypeRNG < 0.4) {
                return new FlameLutin();
            }
            else if (allyTypeRNG < 0.6) {
                return new RiverLutin();
            }
            else if (allyTypeRNG < 0.8) {
                return new CloudLutin();
            }
            else {
                return new FrostLutin();
            }
        }
    }

    public boolean hasLost() {
        return lost;
    }

    public boolean hasEnergy(double amount) {
        return energy >= amount;
    }

    public double getEnergy() {
        return energy;
    }
}
