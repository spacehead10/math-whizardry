package world.biome;

import core.Values;
import org.newdawn.slick.Graphics;
import world.Cell;
import world.Room;
import world.World;
import world.terrain.Ground;
import world.terrain.Terrain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Biome implements Values {
    private Room[][] rooms;
    private int currentRoomX, currentRoomY;
    private Room currentRoom;
    private String name;
    private int spawnRoomX, spawnRoomY;
    private boolean roomHasChanged;
    private char lastSpawnpoint;

    public Biome(String name) {
        this.name = name;
        rooms = new Room[BIOME_MAX_ROOMS_X][BIOME_MAX_ROOMS_Y];
        readBiomeFile();
        lastSpawnpoint = 's';
    }

    public void update() {
        for (int i = 0; i < BIOME_MAX_ROOMS_X; i++) {
            for (int j = 0; j < BIOME_MAX_ROOMS_Y; j++) {
                if (rooms[i][j] != null) {
                    rooms[i][j].updateUnloaded();
                }
            }
        }
        roomHasChanged = false;
        currentRoom.update();
    }

    public void render(Graphics g) {
        currentRoom.render(g);
    }

    public void readBiomeFile() {
        String fileName = "";
        fileName = fileName + name.toLowerCase().charAt(0);
        for (int i = 1; i < name.length(); i++) {
            if (name.charAt(i) != ' ') {
                fileName = fileName + name.charAt(i);
            }
        }

        try {
            File biomeFile = new File("maps/biomes/" + fileName + ".txt");
            Scanner biomeScanner = new Scanner(biomeFile);
            for (int j = 0; j < BIOME_MAX_ROOMS_Y; j++) {
                for (int i = 0; i < BIOME_MAX_ROOMS_X; i++) {
                    if (biomeScanner.hasNextInt()) {
                        int id = biomeScanner.nextInt();
                        rooms[i][j] = new Room(id, this);
                        if (rooms[i][j].isSpawnRoom()) {
                            spawnRoomX = i;
                            spawnRoomY = j;
                        }
                    }
                    else {
                        biomeScanner.next();
                    }
                }
                biomeScanner.nextLine();
            }
            biomeScanner.close();
        }
        catch (FileNotFoundException fnfe) {
            System.out.println("\u001b[31mCould not find biome file " + "\"" + fileName + "\"" + "!\u001b[0m");
        }
        catch (StringIndexOutOfBoundsException sioobe) {
            System.out.println("\u001b[31mBiome file does not have the correct number of columns, or max rooms counting horizontally is set incorrectly!\u001b[0m");
        }
        catch (NoSuchElementException nsee) {
            System.out.println("\u001b[31mBiome file does not have the correct number of rows or is missing a blank line at the bottom, or max rooms counting vertically is set incorrectly!\u001b[0m");
        }
    }

    public void setCurrentRoom(int x, int y, char spawnpoint) {
        if (inBounds(x, y) && rooms[x][y] != null) {
            currentRoom = rooms[x][y];
            currentRoomX = x;
            currentRoomY = y;
            if (this == World.getCurrentBiome()){
                for (int i = 0; i < ROOM_GRID_WIDTH; i++) {
                    for (int j = 0; j < ROOM_GRID_HEIGHT; j++) {
                        Cell c = currentRoom.getCells()[i][j];
                        Terrain t = c.getTerrain();
                        if (t instanceof Ground && ((Ground) t).getTPZoneType() == spawnpoint) {
                            World.getPlayerUnit().setLocation(c.getX() + c.getWidth() / 2, c.getY() + c.getHeight() - 0.5f * (c.getHeight() - World.getPlayerUnit().getHeight()));
                        }
                    }
                }
            }
            roomHasChanged = true;
            lastSpawnpoint = spawnpoint;
        }
    }

    public void setCurrentRoom(char direction) {
        switch (direction) {
            case 'U':
                setCurrentRoom(currentRoomX, currentRoomY - 1, 'u');
                break;
            case 'D':
                setCurrentRoom(currentRoomX, currentRoomY + 1, 'd');
                break;
            case 'L':
                setCurrentRoom(currentRoomX - 1, currentRoomY, 'l');
                break;
            case 'R':
                setCurrentRoom(currentRoomX + 1, currentRoomY, 'r');
                break;
            default:
        }
    }

    public void toSpawnRoom() {
        setCurrentRoom(spawnRoomX, spawnRoomY, 's');
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public String getName() {
        return name;
    }

    public char getLastSpawnpoint() {
        return lastSpawnpoint;
    }

    public static boolean inBounds(int x, int y) {
        return (x >= 0 && x < BIOME_MAX_ROOMS_X) && (y >= 0 && y < BIOME_MAX_ROOMS_Y);
    }

    public boolean roomHasChanged() {
        return roomHasChanged;
    }

    public int getCurrentRoomX() {
        return currentRoomX;
    }

    public int getCurrentRoomY() {
        return currentRoomY;
    }
}
