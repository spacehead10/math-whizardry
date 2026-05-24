package world;

import core.Media;
import core.Values;
import core.state.WorldState;
import entities.creature.Creature;
import entities.player.Player;
import item.Inventory;
import item.relic.fire.FireRelicOne;
import item.relic.ice.IceRelicOne;
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
    private static Biome currentBiome;
    private static PlayerWorldUnit playerUnit;
    private static WorldState state;
    private static Music biomeMusic;
    private static boolean hasEntered;

    public World(WorldState state) {
        this.state = state;
        initPlayer();
        hasEntered = false;
        testBiome = new Biome("Biome");
        calderaCastle = new Biome("Caldera Castle");
        permafrostGlaciers = new Biome("Permafrost Glaciers");
        goToBiome(permafrostGlaciers);
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

    public static void enterWorld() {
        if (biomeMusic != null && !biomeMusic.playing()) {
            biomeMusic.loop(1, MUSIC_VOLUME);
        }
        hasEntered = true;
    }

    public static void enterBattle(Creature enemy) {
        state.enterBattle(enemy);
        if (biomeMusic != null) {
            biomeMusic.stop();
        }
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
            if (currentBiome == calderaCastle && !inv.hasItem(new FireRelicOne())) {
                inv.addItem(FireRelicOne.class, 1);
            }
            else if (currentBiome == permafrostGlaciers && !inv.hasItem(new IceRelicOne())) {
                inv.addItem(IceRelicOne.class, 1);
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
}
