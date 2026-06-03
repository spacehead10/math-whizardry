package core.state;

import core.Main;
import core.Media;
import core.Values;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import static core.Main.getScreenHeight;
import static core.Main.getScreenWidth;

public class CheatNoteState extends BasicGameState implements Values {
    private int id;

    public CheatNoteState(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    private StateBasedGame sbg;

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        this.sbg = sbg;
        gc.setShowFPS(false);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.black);
        g.fillRect(0, 0, getScreenWidth(), getScreenHeight());
        g.setColor(Color.white);
        Media.drawAlignedString("Want to test things out faster? Go to the \"saveData\" folder and copy the contents of the \"sample\" text file into the \"save1\" text file.", getScreenWidth() / 2, getScreenHeight() / 2, Media.CENTER, Media.CENTER, Media.defaultFontSmall, g);
        Media.drawAlignedString("Alternatively, you can use our cheats. Toggle those on by pressing the ';' key once in-game, then experiment with the 'L', 'M', and 'K' keys, and walk through walls. ;)", getScreenWidth() / 2, getScreenHeight() / 2 + Media.defaultFontSmall.getHeight(), Media.CENTER, Media.CENTER, Media.defaultFontSmall, g);
        Media.drawAlignedString("Click anywhere to return to the title screen.", getScreenWidth() / 2, getScreenHeight() / 2 + 2 * Media.defaultFontSmall.getHeight(), Media.CENTER, Media.CENTER, Media.defaultFontSmall, g);
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    public void leave(GameContainer gc, StateBasedGame sbg) {
    }

    public void keyPressed(int key, char c) {
    }

    public void mousePressed(int button, int x, int y) {
        sbg.enterState(Main.TITLE_ID);
    }
}
