package button;

import core.Main;
import core.Media;
import core.state.WorldState;
import org.newdawn.slick.state.StateBasedGame;

public class NewGameButton extends Button {
    private StateBasedGame sbg;

    public NewGameButton(int x, int y, StateBasedGame sbg) {
        super(x, y, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
        this.sbg = sbg;
        body = Media.imgButtonFancy;
        text = "New Game";
        font = Media.defaultFontLarge;
    }

    @Override public void onClick() {
        WorldState.loadGame(false);
        sbg.enterState(Main.WORLD_ID);
    }
}
