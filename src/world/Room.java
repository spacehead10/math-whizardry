package world;

import core.Values;
import org.newdawn.slick.Graphics;
import world.biome.Biome;
import world.terrain.Ground;
import world.terrain.Wall;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Room implements Values {
    private Cell[][] cells;
    private int id;
    private Biome biome;
    private boolean isSpawnRoom;

    public Room(int id, Biome biome) {
        this.biome = biome;
        cells = new Cell[ROOM_GRID_WIDTH][ROOM_GRID_HEIGHT];
        for (int i = 0; i < ROOM_GRID_WIDTH; i++) {
            for (int j = 0; j < ROOM_GRID_HEIGHT; j++) {
                cells[i][j] = new Cell(i, j, biome, this);
            }
        }
        this.id = id;
        isSpawnRoom = false;
        readRoomFile();
    }

    public void update() { //THIS IS THE PROBLEM; when the room changes, the next call to this method now uses the new room, but the old room's already-called update method still completes its for-loop and triggers aggro in subsequently updated cells
        for (int i = 0; i < ROOM_GRID_WIDTH; i++) {
            for (int j = 0; j < ROOM_GRID_HEIGHT; j++) {
                if (biome.roomHasChanged()) { //quick fix to cut this loop when the room changes; might try to find a cleaner fix later
                    return;
                }
                cells[i][j].update();
            }
        }
    }

    //this needs to be called even when this room is not loaded
    public void updateUnloaded() {
        for (int i = 0; i < ROOM_GRID_WIDTH; i++) {
            for (int j = 0; j < ROOM_GRID_HEIGHT; j++) {
                cells[i][j].updateUnloaded(biome);
            }
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < ROOM_GRID_WIDTH; i++) {
            for (int j = 0; j < ROOM_GRID_HEIGHT; j++) {
                cells[i][j].render(g);
            }
        }
        for (int i = 0; i < ROOM_GRID_WIDTH; i++) {
            for (int j = 0; j < ROOM_GRID_HEIGHT; j++) {
                cells[i][j].renderCreature(g);
            }
        }
    }

    public void setTerrain(Cell cell, char id) {
        switch (id) {
            case '.' -> cell.setTerrain(new Ground(false, ' '));
            case '#' -> cell.setTerrain(new Wall());
            case '&' -> cell.setTerrain(new Ground(true, ' '));
            case 'U', 'D', 'L', 'R', 'u', 'd', 'l', 'r', 's' -> cell.setTerrain(new Ground(false, id));
        }
    }

    public void readRoomFile() {
        String folderName = "";
        String biomeName = biome.getName();
        folderName = folderName + biomeName.toLowerCase().charAt(0);
        for (int i = 1; i < biomeName.length(); i++) {
            if (biomeName.charAt(i) != ' ') {
                folderName = folderName + biomeName.charAt(i);
            }
        }

        try {
            File roomFile = new File("maps/rooms/" + folderName + "/room" + id + ".txt");
            Scanner roomScanner = new Scanner(roomFile);
            for (int j = 0; j < ROOM_GRID_HEIGHT; j++) {
                for (int i = 0; i < ROOM_GRID_WIDTH; i++) {
                    char id = roomScanner.next().charAt(0);
                    setTerrain(cells[i][j], id);
                    if (id == 's') {
                        isSpawnRoom = true;
                    }
                }
                roomScanner.nextLine();
            }
            roomScanner.close();
        }
        catch (FileNotFoundException fnfe) {
            System.out.println("\u001b[31mCould not find room file " + "\"" + folderName + "/room" + id + "\"" + "!\u001b[0m");
        }
        catch (StringIndexOutOfBoundsException sioobe) {
            System.out.println("\u001b[31mRoom file does not have the correct number of columns, or room width is set incorrectly!\u001b[0m");
        }
        catch (NoSuchElementException nsee) {
            System.out.println("\u001b[31mRoom file does not have the correct number of rows or is missing a blank line at the bottom, or room height is set incorrectly!\u001b[0m");
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public boolean isSpawnRoom() {
        return isSpawnRoom;
    }
}
