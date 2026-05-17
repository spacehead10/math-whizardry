package save;

import core.Values;
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
import item.Inventory;
import item.Item;
import item.currency.Gold;
import item.relic.Relic;
import item.relic.earth.EarthRelicOne;
import item.relic.fire.FireRelicOne;
import item.relic.ice.IceRelicOne;
import item.relic.storm.StormRelicOne;
import item.relic.water.WaterRelicOne;
import item.wand.Wand;
import item.wand.earth.EnchantedRose;
import item.wand.storm.MysteriousFan;
import item.wand.water.LimestoneWand;
import item.wand.water.Trident;
import pet.PetSelector;
import world.World;
import world.biome.Biome;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SaveInterpreter implements Values {
    private Player player;
    private Inventory inventory;
    private PetSelector petSelector;

    public SaveInterpreter() {
        player = World.getPlayer();
        inventory = Player.getInventory();
        petSelector = Player.getPetSelector();
    }

    public void saveFile() {
        try {
            File saveFile = new File("saveData/save1.txt");
            FileWriter saveWriter = new FileWriter(saveFile);

            //Player info
            /*
                [level] [XP]
             */
            saveWriter.write(player.getLevel() + " ");
            saveWriter.write(player.getCurXP() + " ");
            saveWriter.write("\n");

            //Inventory info
            /*
                [item] [stack size] [item] [stack size] [item] [stack size] ... |
                [equipped Relic index] [equipped Wand index]
             */
            int equippedRelicIndex = -1; //-1 represents the player having no Relic equipped; same applies for Wand
            int equippedWandIndex = -1;
            for (int i = 0; i < inventory.getItems().size(); i++) {
                Item it = inventory.getItems().get(i);
                saveWriter.write(getID(it) + " ");
                saveWriter.write(it.getCurSize() + " ");
                if (it == inventory.getEquippedRelic()) {
                    equippedRelicIndex = i;
                }
                else if (it == inventory.getEquippedWand()) {
                    equippedWandIndex = i;
                }
            }
            saveWriter.write("|\n");
            saveWriter.write(equippedRelicIndex + " ");
            saveWriter.write(equippedWandIndex + " ");
            saveWriter.write("\n");

            //Pet info
            /*
                [pet] [level] [XP] [pet] [level] [XP] [pet] [level] [XP] ... |
                [equipped pet 1 index] [equipped pet 2 index]
             */
            int petOneIndex = -1;
            int petTwoIndex = -1;
            for (int i = 0; i < petSelector.getPets().size(); i++) {
                Creature p = petSelector.getPets().get(i);
                saveWriter.write(getID(p) + " ");
                saveWriter.write(p.getLevel() + " ");
                saveWriter.write(p.getCurXP() + " ");
                if (p == petSelector.getPetInTeam(0)) {
                    petOneIndex = i;
                }
                if (p == petSelector.getPetInTeam(1)) {
                    petTwoIndex = i;
                }
            }
            saveWriter.write("|\n");
            saveWriter.write(petOneIndex + " ");
            saveWriter.write(petTwoIndex + " ");
            saveWriter.write("\n");

            //Location info
            /*
                [biome] [room x] [room y] [spawn tile type]
             */
            Biome biome = World.getCurrentBiome();
            saveWriter.write(getID(biome) + " ");
            saveWriter.write(biome.getCurrentRoomX() + " ");
            saveWriter.write(biome.getCurrentRoomY() + " ");
            saveWriter.write(biome.getLastSpawnpoint() + " ");
            saveWriter.write("\n");

            saveWriter.close();
            System.out.println("\u001b[32mGame saved successfully.\u001b[0m");
        }
        catch (InterpretationException ie) {
            System.out.println("\u001b[31mItem/Pet " + ie.getMessage() + " does not have an assigned ID.\u001b[0m");
        }
        catch (FileNotFoundException fnfe) {
            System.out.println("\u001b[31mDesignated save file is read-only or has been renamed without updating the reference in saveFile().\u001b[0m");
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("\u001b[31mError in writing save file.\u001b[0m");
        }
    }

    public void loadGame(boolean saved) {
        try {
            if (saved) {
                loadSavedGame();
            }
            else {
                loadNewGame();
            }
        }
        catch (FileNotFoundException fnfe) {
            System.out.println("\u001b[31mCould not find save file.\u001b[0m");
        }
    }

    private void loadNewGame() {
//        Player.getPetSelector().addPet(new FlameLutin(DEFAULT_STARTING_LEVEL));
//        Player.getPetSelector().addPet(new CloudLutin(DEFAULT_STARTING_LEVEL));
//        Player.getPetSelector().addPet(new FrostLutin(DEFAULT_STARTING_LEVEL));
//        Player.getPetSelector().addPet(new RiverLutin(DEFAULT_STARTING_LEVEL));
//        Player.getPetSelector().addPet(new GardenLutin(DEFAULT_STARTING_LEVEL));
//        Player.getInventory().addItem(Gold.class, 30);
//        Player.getInventory().addItem(EarthRelicOne.class, 1);
//        Player.getInventory().addItem(FireRelicOne.class, 1);
//        Player.getInventory().addItem(EnchantedRose.class, 1);
//        Player.getInventory().addItem(LimestoneWand.class, 1);
    }

    private void loadSavedGame() throws FileNotFoundException {
        File saveFile = new File("saveData/save1.txt");
        Scanner saveScanner = new Scanner(saveFile);
        if (!saveScanner.hasNext()) {
            saveScanner.close();
            System.out.println("No save data found. Starting new game.");
            loadNewGame();
            return;
        }

        try {
            //Player info
            /*
                [level] [XP]
             */
            player.initFromSave(saveScanner.nextInt(), saveScanner.nextDouble());
            saveScanner.nextLine();

            //Inventory info
            /*
                [item] [stack size] [item] [stack size] [item] [stack size] ...
                [equipped Relic index] [equipped Wand index]
             */
            while (true) {
                String nextItemID = saveScanner.next();
                if (nextItemID.equals("|")) {
                    break;
                }
                inventory.addItem(getItem(nextItemID), saveScanner.nextInt());
            }
            saveScanner.nextLine();
            int equippedRelicIndex = saveScanner.nextInt();
            int equippedWandIndex = saveScanner.nextInt();
            if (equippedRelicIndex == -1) {
                inventory.setRelic(null);
            }
            else {
                inventory.setRelic((Relic) inventory.getItems().get(equippedRelicIndex));
            }
            if (equippedWandIndex == -1) {
                inventory.setWand(null);
            }
            else {
                inventory.setWand((Wand) inventory.getItems().get(equippedWandIndex));
            }
            saveScanner.nextLine();

            //Pet info
            /*
                [pet] [level] [XP] [pet] [level] [XP] [pet] [level] [XP] ...
                [equipped pet 1 index] [equipped pet 2 index]
             */
            while (true) {
                String nextPetID = saveScanner.next();
                if (nextPetID.equals("|")) {
                    break;
                }
                Creature pet = getPet(nextPetID);
                pet.initFromSave(saveScanner.nextInt(), saveScanner.nextDouble());
                petSelector.addPet(pet);
            }
            saveScanner.nextLine();
            int petOneIndex = saveScanner.nextInt();
            int petTwoIndex = saveScanner.nextInt();
            if (petOneIndex == -1) {
                petSelector.setPetInTeam(null, 0);
            }
            else {
                petSelector.setPetInTeam(petSelector.getPets().get(petOneIndex), 0);
            }
            if (petTwoIndex == -1) {
                petSelector.setPetInTeam(null, 1);
            }
            else {
                petSelector.setPetInTeam(petSelector.getPets().get(petTwoIndex), 1);
            }
            saveScanner.nextLine();

            //Location info
            /*
                [biome] [room x] [room y] [spawn tile type]
             */
            World.goToBiome(getBiome(saveScanner.next()));
            Biome biome = World.getCurrentBiome();
            biome.setCurrentRoom(saveScanner.nextInt(), saveScanner.nextInt(), saveScanner.next().charAt(0));
            saveScanner.nextLine();

            saveScanner.close();
            System.out.println("\u001b[32mSaved game loaded successfully.\u001b[0m");
        }
        catch (InterpretationException ie) {
            saveScanner.close();
            player.initFromSave(DEFAULT_STARTING_LEVEL, 0);
            inventory.clearItems();
            petSelector.clearPets();
            System.out.println("\u001b[31mThe item/pet corresponding to ID " + ie.getMessage() + " is unknown. Loading new game instead.\u001b[0m");
            loadNewGame();
        }
        catch (Exception e) {
            saveScanner.close();
            player.initFromSave(DEFAULT_STARTING_LEVEL, 0);
            inventory.clearItems();
            petSelector.clearPets();
            e.printStackTrace();
            System.out.println("\u001b[31mUnexpected error in reading save file. Loading new game instead.\u001b[0m");
            loadNewGame();
        }
    }

    private Class<? extends Item> getItem(String id) {
        return switch (id) {
            case "000" -> null;
            case "001" -> Gold.class;
            case "111" -> EarthRelicOne.class;
            case "121" -> FireRelicOne.class;
            case "131" -> IceRelicOne.class;
            case "141" -> StormRelicOne.class;
            case "151" -> WaterRelicOne.class;
            case "210" -> EnchantedRose.class;
            case "250" -> LimestoneWand.class;
            case "251" -> Trident.class;
            case "240" -> MysteriousFan.class;
            default -> throw new InterpretationException(id);
        };
    }

    private Creature getPet(String id) {
        return switch (id) {
            case "000" -> null;
            case "100" -> new GardenLutin();
            case "200" -> new FlameLutin();
            case "300" -> new FrostLutin();
            case "400" -> new CloudLutin();
            case "500" -> new RiverLutin();
            case "210" -> new Terrapyre();
            case "310" -> new Frostbite();
            case "410" -> new Voltiger();
            default -> throw new InterpretationException(id);
        };
    }

    private Biome getBiome(String id) {
        return switch (id) {
            case "1" -> World.calderaCastle();
            case "2" -> World.permafrostGlaciers();
            default -> World.testBiome();
        };
    }

    private String getID(Item item) {
        if (item == null) {
            return "000";
        }
        else if (item instanceof Gold) {
            return "001";
        }
        else if (item instanceof EarthRelicOne) { //Relics: 1 + element [1-5] + tier [1-3]
            return "111";
        }
        else if (item instanceof FireRelicOne) {
            return "121";
        }
        else if (item instanceof IceRelicOne) {
            return "131";
        }
        else if (item instanceof StormRelicOne) {
            return "141";
        }
        else if (item instanceof WaterRelicOne) {
            return "151";
        }
        else if (item instanceof EnchantedRose) { //Wands: 2 + element [1-5] + next free wand ID [0-9]
            return "210";
        }
        else if (item instanceof LimestoneWand) {
            return "250";
        }
        else if (item instanceof Trident) {
            return "251";
        }
        else if (item instanceof MysteriousFan) {
            return "240";
        }
        else {
            throw new InterpretationException(item.getClass().getName());
        }
    }

    private String getID(Creature pet) {
        if (pet == null) {
            return "000";
        }
        else if (pet instanceof GardenLutin) { //Pets: element [1-5] + next free pet ID [0-9] + 0
            return "100";
        }
        else if (pet instanceof FlameLutin) {
            return "200";
        }
        else if (pet instanceof FrostLutin) {
            return "300";
        }
        else if (pet instanceof CloudLutin) {
            return "400";
        }
        else if (pet instanceof RiverLutin) {
            return "500";
        }
        else if (pet instanceof Terrapyre) {
            return "210";
        }
        else if (pet instanceof Frostbite) {
            return "310";
        }
        else if (pet instanceof Voltiger) {
            return "410";
        }
        else {
            throw new InterpretationException(pet.getClass().getName());
        }
    }

    private String getID(Biome biome) {
        if (biome == World.calderaCastle()) {
            return "1";
        }
        else if (biome == World.permafrostGlaciers()) {
            return "2";
        }
        else {
            return "0";
        }
    }
}
