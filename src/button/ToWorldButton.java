package button;

import core.Main;
import org.newdawn.slick.state.StateBasedGame;

public class ToWorldButton extends Button {
    private StateBasedGame sbg;

    public ToWorldButton(int x, int y, StateBasedGame sbg) {
        super(x, y, STATE_BUTTON_WIDTH, STATE_BUTTON_HEIGHT);
        this.sbg = sbg;
        text = "Back to world";
    }

    @Override public void onClick() {
        sbg.enterState(Main.WORLD_ID);
    }
}
