package core.state;

import button.*;
import core.Main;
import core.Settings;
import item.currency.Gold;
import save.SaveInterpreter;
import core.Values;
import entities.creature.Creature;
import entities.player.Player;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import shop.Shop;
import world.Cell;
import world.TeleportMenu;
import world.World;

import static core.Main.getScreenWidth;
import static core.state.BattleState.developerMode;

public class WorldState extends BasicGameState implements Values {
    private int id;

    public WorldState(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    private StateBasedGame sbg;

    private static Creature lastEnemyEncountered;
    private static boolean wonLastBattle;
    private boolean inventoryOpen;
    private boolean petSelectorOpen;
    private boolean shopOpen;
    private boolean mapOpen;
    private boolean settingsOpen;
    private static SaveInterpreter saveInterpreter;
    private World world;
    private Shop shop;
    private TeleportMenu map;
    private Settings settings;
    private OpenInventoryButton openInventoryButton;
    private OpenPetSelectorButton openPetSelectorButton;
    private OpenShopButton openShopButton;
    private OpenMapButton openMapButton;
    private CloseButton closeButton;
    private QuitButton quitButton;
    private OpenSettingsButton openSettingsButton;
    private ModeToggleButton modeToggleButton;
    private static boolean enteringBattle;
    private static int enterBattleTimer;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.sbg = sbg;
        gc.setShowFPS(false);

        world = new World(this);
        saveInterpreter = new SaveInterpreter();

        shop = new Shop();
        map = new TeleportMenu(this);
        settings = new Settings();

        inventoryOpen = false;
        petSelectorOpen = false;
        shopOpen = false;
        mapOpen = false;
        settingsOpen = false;
        enteringBattle = false;
        wonLastBattle = false;

        enterBattleTimer = ENTER_BATTLE_DELAY;

        openInventoryButton = new OpenInventoryButton(getScreenWidth() - DEFAULT_SQUARE_BUTTON_SIZE - 10, 10, this);
        openPetSelectorButton = new OpenPetSelectorButton(getScreenWidth() - DEFAULT_SQUARE_BUTTON_SIZE - 10, 10 + DEFAULT_SQUARE_BUTTON_SIZE + 10, this);
        openShopButton = new OpenShopButton(getScreenWidth() - DEFAULT_SQUARE_BUTTON_SIZE - 10, 10 + 2 * (DEFAULT_SQUARE_BUTTON_SIZE + 10), this);
        openMapButton = new OpenMapButton(getScreenWidth() - DEFAULT_SQUARE_BUTTON_SIZE - 10, 10 + 3 * (DEFAULT_SQUARE_BUTTON_SIZE + 10), this);
        closeButton = new CloseButton(getScreenWidth() - DEFAULT_SQUARE_BUTTON_SIZE - 10, 10, this);
        quitButton = new QuitButton(10, 10, this);
        openSettingsButton = new OpenSettingsButton(10 + DEFAULT_SQUARE_BUTTON_SIZE + 10, 10, this);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        world.update(gc);
        if (enteringBattle) {
            if (enterBattleTimer > 0) {
                enterBattleTimer--;
            }
            else {
                sbg.enterState(Main.BATTLE_ID);
            }
        }
        else {
            if (inventoryOpen) {
                Player.getInventory().cleanup();
            }
            else if (petSelectorOpen) {
                Player.getPetSelector().cleanup();
            }
            else if (shopOpen) {
                shop.cleanup();
            }
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        world.render(g);
        if (inventoryOpen) {
            Player.getInventory().render(g, gc);
            closeButton.render(g, gc);
        }
        else if (petSelectorOpen) {
            Player.getPetSelector().render(g, gc);
            closeButton.render(g, gc);
        }
        else if (shopOpen) {
            shop.render(g, gc);
            closeButton.render(g, gc);
        }
        else if (mapOpen) {
            map.render(g, gc);
            closeButton.render(g, gc);
        }
        else if (settingsOpen) {
            settings.render(g, gc);
            closeButton.render(g, gc);
        }
        else {
            openInventoryButton.render(g, gc);
            openPetSelectorButton.render(g, gc);
            openShopButton.render(g, gc);
            openMapButton.render(g, gc);
            quitButton.render(g, gc);
            openSettingsButton.render(g, gc);
        }
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        enteringBattle = false;
        enterBattleTimer = ENTER_BATTLE_DELAY;
        World.enterWorld(wonLastBattle);
    }

    public void leave(GameContainer gc, StateBasedGame sbg) {
    }

    public void keyPressed(int key, char c) {
        if (!enteringBattle) {
            switch (key) {
                case Input.KEY_SEMICOLON:
                    BattleState.toggleDeveloperMode();
                    break;
                case Input.KEY_M:
                    if (developerMode()) {
                        Player.getInventory().addItem(Gold.class, 30);
                    }
                    break;
                case Input.KEY_L:
                    if (developerMode()) {
                        World.getPlayer().gainXP(30);
                    }
                    break;
                default:
            }
        }
    }

    public void mousePressed(int button, int x, int y) {
        if (!enteringBattle) {
            if (inventoryOpen) {
                Player.getInventory().mousePressed(button, x, y);
                closeButton.mousePressed(x, y);
            }
            else if (petSelectorOpen) {
                Player.getPetSelector().mousePressed(button, x, y);
                closeButton.mousePressed(x, y);
            }
            else if (shopOpen) {
                shop.mousePressed(button, x, y);
                closeButton.mousePressed(x, y);
            }
            else if (mapOpen) {
                map.mousePressed(button, x, y);
                closeButton.mousePressed(x, y);
            }
            else if (settingsOpen) {
                settings.mousePressed(button, x, y);
                closeButton.mousePressed(x, y);
            }
            else {
                openInventoryButton.mousePressed(x, y);
                openPetSelectorButton.mousePressed(x, y);
                openShopButton.mousePressed(x, y);
                openMapButton.mousePressed(x, y);
                quitButton.mousePressed(x, y);
                openSettingsButton.mousePressed(x, y);
            }
        }
    }

    public void mouseWheelMoved(int change) {
        if (inventoryOpen) {
            Player.getInventory().mouseWheelMoved(change);
        }
        else if (petSelectorOpen) {
            Player.getPetSelector().mouseWheelMoved(change);
        }
        else if (shopOpen) {
            shop.mouseWheelMoved(change);
        }
    }

    public void enterBattle(Creature enemy) {
        closeInventory();
        closePetSelector();
        closeShop();
        closeMap();
        closeSettings();
        enteringBattle = true;
        lastEnemyEncountered = enemy;
    }

    public void openInventory() {
        inventoryOpen = true;
        closePetSelector();
        closeShop();
        closeMap();
        closeSettings();
    }

    public void closeInventory() {
        inventoryOpen = false;
        Player.getInventory().clearSelection();
    }

    public void openPetSelector() {
        petSelectorOpen = true;
        closeInventory();
        closeShop();
        closeMap();
        closeSettings();
    }

    public void closePetSelector() {
        petSelectorOpen = false;
        Player.getPetSelector().clearSelection();
    }

    public void openShop() {
        shopOpen = true;
        closeInventory();
        closePetSelector();
        closeMap();
        closeSettings();
    }

    public void closeShop() {
        shopOpen = false;
        shop.clearSelection();
    }

    public void openMap() {
        mapOpen = true;
        closeInventory();
        closePetSelector();
        closeShop();
        closeSettings();
    }

    public void closeMap() {
        mapOpen = false;
    }

    public void openSettings() {
        settingsOpen = true;
        closeInventory();
        closePetSelector();
        closeShop();
        closeMap();
    }

    public void closeSettings() {
        settingsOpen = false;
    }

    public void quit() {
        saveInterpreter.saveFile();
        System.exit(0);
    }

    public static void loadGame(boolean saved) {
        saveInterpreter.loadGame(saved);
    }

    public static void setLastBattleResult(boolean won) {
        wonLastBattle = won;
    }

    public static Creature getLastEnemyEncountered() {
        return lastEnemyEncountered;
    }

    public static boolean isEnteringBattle() {
        return enteringBattle;
    }

    public static int getEnterBattleTimer() {
        return enterBattleTimer;
    }
}
