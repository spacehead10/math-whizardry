package button;

import core.Main;
import core.Media;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class CheatNoteButton extends Button {
    private StateBasedGame sbg;

    public CheatNoteButton(int x, int y, StateBasedGame sbg) {
        super(x, y, DEFAULT_SQUARE_BUTTON_SIZE, DEFAULT_SQUARE_BUTTON_SIZE);
        this.sbg = sbg;
        text = "?";
    }

    @Override public void onClick() {
        sbg.enterState(Main.CHEAT_NOTE_ID);
    }
}
