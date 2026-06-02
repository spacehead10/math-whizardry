package world;

import core.Media;
import core.Values;
import core.state.WorldState;
import entities.creature.Creature;
import entities.player.Player;
import item.Inventory;
import item.relic.earth.*;
import item.relic.fire.*;
import item.relic.ice.*;
import item.relic.storm.*;
import item.relic.water.*;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import popup.PopupManager;
import world.biome.Biome;
import world.worldUnit.PlayerWorldUnit;

public class World implements Values {
    private static Player player;
    private static Biome testBiome;
    private static Biome calderaCastle;
    private static Biome permafrostGlaciers;
    private static Biome cumulusAcropolis;
    private static Biome pearlescentReef;
    private static Biome overgrowthGardens;
    private static Biome currentBiome;
    private static PlayerWorldUnit playerUnit;
    private static WorldState state;
    private static Music biomeMusic;
    private static boolean hasEntered;
    private static Cell lastAggroCell;

    public World(WorldState state) {
        this.state = state;
        initPlayer();
        hasEntered = false;
        testBiome = new Biome("Biome");
        calderaCastle = new Biome("Caldera Castle");
        permafrostGlaciers = new Biome("Permafrost Glaciers");
        cumulusAcropolis = new Biome("Cumulus Acropolis");
        pearlescentReef = new Biome("Pearlescent Reef");
        overgrowthGardens = new Biome("Overgrowth Gardens");
        goToBiome(calderaCastle);
        PopupManager.init();
    }

    public static void initPlayer() {
        player = new Player();
        playerUnit = player.getNewWorldUnit();
    }

    public void update(GameContainer gc) {
        currentBiome.update();
        if (!WorldState.isEnteringBattle())  {
            playerUnit.update(gc);
            PopupManager.update();
        }
    }

    public void render(Graphics g) {
        currentBiome.render(g);
        playerUnit.draw(g);
        PopupManager.render(g);
    }

    public static void enterWorld(boolean wonLastBattle) {
        if (hasEntered) { //the first time entering (i.e. from the title screen, not from a battle), wonLastBattle is meaningless, so this is skipped
            if (wonLastBattle) {
                lastAggroCell.removeEnemy();
            }
            else {
                currentBiome.setCurrentRoom(currentBiome.getCurrentRoomX(), currentBiome.getCurrentRoomY(), currentBiome.getLastSpawnpoint());
            }
        }

        if (biomeMusic != null && !biomeMusic.playing()) {
            biomeMusic.loop(1, MUSIC_VOLUME);
        }
        hasEntered = true;
    }

    public static void enterBattle(Creature enemy, Cell cell) {
        state.enterBattle(enemy);
        if (biomeMusic != null) {
            biomeMusic.stop();
        }
        lastAggroCell = cell;
        Media.sfxAggro.play();
    }

    public static void goToBiome(Biome biome) {
        currentBiome = biome;
        currentBiome.toSpawnRoom();
        if (currentBiome == calderaCastle) {
            biomeMusic = Media.musicCalderaCastle;
        }
        else if (currentBiome == permafrostGlaciers) {
            biomeMusic = Media.musicPermafrostGlaciers;
        }
        else if (currentBiome == cumulusAcropolis) {
            biomeMusic = Media.musicCumulusAcropolis;
        }
        else if (currentBiome == pearlescentReef) {
            biomeMusic = Media.musicPearlescentReef;
        }
        else if (currentBiome == overgrowthGardens) {
            biomeMusic = Media.musicOvergrowthGardens;
        }
        else {
            biomeMusic = null;
        }

        if (biomeMusic != null && !biomeMusic.playing() && hasEntered) {
            biomeMusic.loop(1, MUSIC_VOLUME);
        }
    }

    public static void giveBiomeLoot() {
        if (Math.random() < RELIC_DROP_CHANCE) {
            Inventory inv = Player.getInventory();
            if (currentBiome == calderaCastle) {
                if (!inv.hasItem(new FireRelicOne())) {
                    inv.addItem(FireRelicOne.class, 1);
                }
                else if (!inv.hasItem(new FireRelicTwo()) && player.getLevel() >= 20) {
                    inv.addItem(FireRelicTwo.class, 1);
                }
            }
            else if (currentBiome == permafrostGlaciers) {
                if (!inv.hasItem(new IceRelicOne())) {
                    inv.addItem(IceRelicOne.class, 1);
                }
                else if (!inv.hasItem(new IceRelicTwo()) && player.getLevel() >= 20) {
                    inv.addItem(IceRelicTwo.class, 1);
                }
            }
            else if (currentBiome == cumulusAcropolis) {
                if (!inv.hasItem(new StormRelicOne())) {
                    inv.addItem(StormRelicOne.class, 1);
                }
                else if (!inv.hasItem(new StormRelicTwo()) && player.getLevel() >= 20) {
                    inv.addItem(StormRelicTwo.class, 1);
                }
            }
            else if (currentBiome == pearlescentReef) {
                if (!inv.hasItem(new WaterRelicOne())) {
                    inv.addItem(WaterRelicOne.class, 1);
                }
                else if (!inv.hasItem(new WaterRelicTwo()) && player.getLevel() >= 20) {
                    inv.addItem(WaterRelicTwo.class, 1);
                }
            }
            else if (currentBiome == overgrowthGardens) {
                if (!inv.hasItem(new EarthRelicOne())) {
                    inv.addItem(EarthRelicOne.class, 1);
                }
                else if (!inv.hasItem(new EarthRelicTwo()) && player.getLevel() >= 20) {
                    inv.addItem(EarthRelicTwo.class, 1);
                }
            }
        }
    }

    public static Player getPlayer() {
        return player;
    }

    public static PlayerWorldUnit getPlayerUnit() {
        return playerUnit;
    }

    public static Biome getCurrentBiome() {
        return currentBiome;
    }

    public static Biome testBiome() {
        return testBiome;
    }

    public static Biome calderaCastle() {
        return calderaCastle;
    }

    public static Biome permafrostGlaciers() {
        return permafrostGlaciers;
    }

    public static Biome cumulusAcropolis() {
        return cumulusAcropolis;
    }

    public static Biome pearlescentReef() {
        return pearlescentReef;
    }

    public static Biome overgrowthGardens() {
        return overgrowthGardens;
    }
}
