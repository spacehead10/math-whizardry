package world;

import core.Media;
import core.Values;
import core.state.WorldState;
import entities.creature.Creature;
import entities.creature.earth.GardenLutin;
import entities.creature.fire.FlameLutin;
import entities.creature.fire.Terrapyre;
import entities.creature.ice.FrostLutin;
import entities.creature.ice.Frostbite;
import entities.creature.storm.CloudLutin;
import entities.creature.water.RiverLutin;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import popup.message.DebugMessage;
import world.biome.Biome;
import world.terrain.Ground;
import world.terrain.Terrain;
import world.terrain.Wall;
import world.worldUnit.CreatureWorldUnit;
import world.worldUnit.PlayerWorldUnit;

import static core.Main.getScreenWidth;
import static core.Main.getScreenHeight;
import static core.Utils.dist;
import static core.state.BattleState.developerMode;
import static popup.PopupManager.addPopup;

public class Cell implements Values {
    private int gridX;
    private int gridY;
    private Terrain terrain;
    private int spawnTimer;
    private boolean hasCreature;
    private Creature spawnedCreature;
    private CreatureWorldUnit spawnedCreatureUnit;
    private Biome biome;
    private Room room;

    public Cell(int gridX, int gridY, Biome biome, Room room) {
        this.gridX = gridX;
        this.gridY = gridY;
        this.biome = biome;
        this.room = room;
        spawnTimer = 0;
        hasCreature = false;
    }

    public void setTerrain(Terrain t) {
        terrain = t;
        if (terrain != null) {
            terrain.setCell(this);
        }
    }

    public void update() {
        if (!WorldState.isEnteringBattle()) {
            if (spawnTimer <= 0) { //spawn timer is updated while unloaded, but the actual spawning triggers upon loading the room containing this cell; this is to avoid spawning lvl 1 enemies EVERYWHERE at the start of the game
                spawnEnemy();
            }

            if (hasAggro()) {
                World.enterBattle(spawnedCreature, this);
            }

            if (terrain instanceof Ground && ((Ground) terrain).isTPStart()) {
                PlayerWorldUnit player = World.getPlayerUnit();
                if (player.getX() >= getX() && player.getX() <= getX() + getWidth() && player.getY() >= getY() && player.getY() <= getY() + getHeight()) {
                    World.getCurrentBiome().setCurrentRoom(((Ground) terrain).getTPZoneType());
                }
            }
        }
    }

    //update the spawn timer even when the room containing this cell is not loaded
    public void updateUnloaded() {
        if (spawnTimer > 0) {
            if (!hasCreature) {
                spawnTimer--;
                if (developerMode()) {
                    addPopup(new DebugMessage("Spawn: " + spawnTimer, 0));
                }
            }
        }
    }

    public void render(Graphics g) {
        terrain.render(g, biome);
    }

    public void renderCreature(Graphics g) {
        if (spawnedCreatureUnit != null) {
            spawnedCreatureUnit.draw(g);
        }

        if (WorldState.isEnteringBattle() && hasAggro()) {
            Image aggroAlert = Media.imgAggro.getScaledCopy(1.5f);
            aggroAlert.draw(spawnedCreatureUnit.getX() - aggroAlert.getWidth() / 2, spawnedCreatureUnit.getY() - spawnedCreatureUnit.getHeight() - aggroAlert.getHeight());
        }
    }

    public void spawnEnemy() {
        if (terrain instanceof Ground && ((Ground) terrain).isEnemySpawn()) {
            double rng = Math.random();
            if (biome == World.calderaCastle()) {
                if (rng < 0.6) {
                    spawnedCreature = new FlameLutin();
                }
                else if (rng < 0.9) {
                    spawnedCreature = new CloudLutin();
                }
                else {
                    spawnedCreature = new Terrapyre();
                }
            }
            else if (biome == World.permafrostGlaciers()) {
                if (rng < 0.6) {
                    spawnedCreature = new FrostLutin();
                }
                else if (rng < 0.9) {
                    spawnedCreature = new RiverLutin();
                }
                else {
                    spawnedCreature = new Frostbite();
                }
            }
            else {
                if (rng < 0.2) {
                    spawnedCreature = new FlameLutin();
                }
                else if (rng < 0.4) {
                    spawnedCreature = new RiverLutin();
                }
                else if (rng < 0.6) {
                    spawnedCreature = new CloudLutin();
                }
                else if (rng < 0.8) {
                    spawnedCreature = new FrostLutin();
                }
                else {
                    spawnedCreature = new GardenLutin();
                }
            }
            spawnedCreatureUnit = spawnedCreature.getNewWorldUnit();
            spawnedCreatureUnit.setLocation(getX() + getWidth() / 2, getY() + getHeight() / 2);
            spawnTimer = SPAWN_COOLDOWN;
            hasCreature = true;
        }
    }

    public void removeEnemy() {
        spawnedCreature = null;
        spawnedCreatureUnit = null;
        hasCreature = false;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public boolean collisionLeft(PlayerWorldUnit player, float dx) {
        if (developerMode()) {
            return false;
        }

        boolean enteringFromLeft = player.getX() + player.getWidth() / 2 < getX() && player.getX() + player.getWidth() / 2 + dx >= getX();
        boolean matchingY = (player.getY() > getY() && player.getY() - player.getHeight() < getY() + getHeight());
        return enteringFromLeft && matchingY;
    }

    public boolean collisionRight(PlayerWorldUnit player, float dx) {
        if (developerMode()) {
            return false;
        }

        boolean enteringFromRight = player.getX() - player.getWidth() / 2 > getX() + getWidth() && player.getX() - player.getWidth() / 2 - dx <= getX() + getWidth();
        boolean matchingY = (player.getY() > getY() && player.getY() - player.getHeight() < getY() + getHeight());
        return enteringFromRight && matchingY;
    }

    public boolean collisionTop(PlayerWorldUnit player, float dy) {
        if (developerMode()) {
            return false;
        }

        boolean enteringFromTop = player.getY() < getY() + 1 && player.getY() + dy >= getY();
        boolean matchingX = (player.getX() + player.getWidth() / 2 > getX() && player.getX() - player.getWidth() / 2 < getX() + getWidth());
        return enteringFromTop && matchingX;
    }

    public boolean collisionBottom(PlayerWorldUnit player, float dy) {
        if (developerMode()) {
            return false;
        }

        boolean enteringFromBottom = player.getY() - player.getHeight() > getY() + getHeight() && player.getY() - player.getHeight() - dy <= getY() + getHeight();
        boolean matchingX = (player.getX() + player.getWidth() / 2 > getX() && player.getX() - player.getWidth() / 2 < getX() + getWidth());
        return enteringFromBottom && matchingX;
    }

    public boolean hasAggro() {
        return (spawnedCreatureUnit != null) && (dist(spawnedCreatureUnit.getX(), spawnedCreatureUnit.getY(), World.getPlayerUnit().getX(), World.getPlayerUnit().getY()) < AGGRO_RADIUS);
    }

    public float getX() {
        return gridX * getWidth();
    }

    public float getY() {
        return gridY * getHeight();
    }

    public static float toPixelX(int gridX) {
        return gridX * getWidth();
    }

    public static float toPixelY(int gridY) {
        return gridY * getHeight();
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public static int getWidth() {
        return getScreenWidth() / ROOM_GRID_WIDTH;
    }

    public static int getHeight() {
        return getScreenHeight() / ROOM_GRID_HEIGHT;
    }

    public final boolean topTouchingWall() {
        if (gridY == 0) {
            return true;
        }
        return (room.getCells()[gridX][gridY - 1].getTerrain() instanceof Wall);
    }

    public final boolean bottomTouchingWall() {
        if (gridY == ROOM_GRID_HEIGHT - 1) {
            return true;
        }
        return (room.getCells()[gridX][gridY + 1].getTerrain() instanceof Wall);
    }

    public final boolean leftTouchingWall() {
        if (gridX == 0) {
            return true;
        }
        return (room.getCells()[gridX - 1][gridY].getTerrain() instanceof Wall);
    }

    public final boolean rightTouchingWall() {
        if (gridX == ROOM_GRID_WIDTH - 1) {
            return true;
        }
        return (room.getCells()[gridX + 1][gridY].getTerrain() instanceof Wall);
    }
}
