package core.state;

import button.EasyModeButton;
import button.LoadSaveButton;
import button.NewGameButton;
import button.NormalModeButton;
import core.Main;
import core.Media;
import core.Values;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import popup.PopupManager;

import static core.Main.getScreenHeight;
import static core.Main.getScreenWidth;

public class HowToPlayState extends BasicGameState implements Values {
    private int id;

    public HowToPlayState(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    private StateBasedGame sbg;

    private Image[] slides;
    private int slideIndex;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.sbg = sbg;
        gc.setShowFPS(false);
        slides = Media.imgsInfoSlides;
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        slides[slideIndex].draw(0, 0, getScreenWidth(), getScreenHeight());
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        slideIndex = 0;
    }

    public void leave(GameContainer gc, StateBasedGame sbg) {
    }

    public void keyPressed(int key, char c) {
    }

    public void mousePressed(int button, int x, int y) {
        if (slideIndex < slides.length - 1){
            slideIndex++;
        }
        else {
            sbg.enterState(Main.TITLE_ID);
        }
    }
}
