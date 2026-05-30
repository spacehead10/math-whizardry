package core.state;

import button.EasyModeButton;
import button.LoadSaveButton;
import button.NewGameButton;
import button.NormalModeButton;
import core.Media;
import core.Values;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import popup.PopupManager;
import static core.Main.getScreenWidth;
import static core.Main.getScreenHeight;

public class TitleState extends BasicGameState implements Values {
    private int id;

    public TitleState(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    private StateBasedGame sbg;

    private Image titleText;
    private Image[] backgrounds;
    private double backgroundTimer;
    private int backgroundIndex;
    private NewGameButton newGameButton;
    private LoadSaveButton loadSaveButton;
    private boolean newGameOptionsOpen;
    private EasyModeButton easyModeButton;
    private NormalModeButton normalModeButton;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.sbg = sbg;
        gc.setShowFPS(false);
        try {
            Media.loadImages();
            Media.loadSound();
        }
        catch (SlickException se) {
            se.printStackTrace();
            System.exit(-1);
        }
        PopupManager.init();
        titleText = Media.imgTitleText;
        backgrounds = Media.imgsTitleBG;
        backgroundTimer = 0;
        backgroundIndex = 0;
        newGameOptionsOpen = false;
        newGameButton = new NewGameButton((int) (getScreenWidth() * 0.33f - START_BUTTON_WIDTH / 2), getScreenHeight() / 2, this);
        loadSaveButton = new LoadSaveButton((int) (getScreenWidth() * 0.67f - START_BUTTON_WIDTH / 2), getScreenHeight() / 2, sbg);
        easyModeButton = new EasyModeButton((int) (getScreenWidth() * 0.4f - MODE_SELECTION_BUTTON_WIDTH / 2), getScreenHeight() / 2, sbg);
        normalModeButton = new NormalModeButton((int) (getScreenWidth() * 0.6f - MODE_SELECTION_BUTTON_WIDTH / 2), getScreenHeight() / 2, sbg);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        backgroundTimer += TITLE_TIMER_RATE;
        if (backgroundTimer >= backgrounds.length) {
            backgroundTimer = 0;
        }
        backgroundIndex = (int) backgroundTimer;
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        backgrounds[backgroundIndex].draw(0, 0, getScreenWidth(), getScreenHeight());
        g.setColor(new Color(0, 0, 0, 127));
        g.fillRect(0, 0, getScreenWidth(), getScreenHeight());

        if (newGameOptionsOpen) {
            g.setColor(new Color(255, 255, 255, 200));
            g.fillRect(10, 10, getScreenWidth() - 20, getScreenHeight() - 20);
            g.setColor(Color.black);
            Media.drawAlignedString("Select math difficulty", getScreenWidth() / 2, getScreenHeight() * 0.25f, Media.CENTER, Media.CENTER, Media.defaultFontLarge, g);
            easyModeButton.render(g, gc);
            normalModeButton.render(g, gc);
        }
        else {
            titleText.draw(getScreenWidth() / 2 - titleText.getWidth() / 2, getScreenHeight() * 0.25f - titleText.getHeight() / 2);
            newGameButton.render(g, gc);
            loadSaveButton.render(g, gc);
            Media.drawShadowedString("Warning: Starting a new game will overwrite existing save data.", getScreenWidth() / 2, getScreenHeight() / 2 + START_BUTTON_HEIGHT, Media.CENTER, Media.TOP, Media.defaultFontMedium, Color.white, Color.black, g);
        }
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    public void leave(GameContainer gc, StateBasedGame sbg) {
    }

    public void keyPressed(int key, char c) {
    }

    public void mousePressed(int button, int x, int y) {
        if (newGameOptionsOpen) {
            easyModeButton.mousePressed(x, y);
            normalModeButton.mousePressed(x, y);
        }
        else {
            newGameButton.mousePressed(x, y);
            loadSaveButton.mousePressed(x, y);
        }
    }

    public void startNewGame() {
        newGameOptionsOpen = true;
    }
}
