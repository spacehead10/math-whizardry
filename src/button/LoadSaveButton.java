package button;

import core.Main;
import core.Media;
import core.state.WorldState;
import org.newdawn.slick.state.StateBasedGame;

public class LoadSaveButton extends Button {
    private StateBasedGame sbg;

    public LoadSaveButton(int x, int y, StateBasedGame sbg) {
        super(x, y, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
        this.sbg = sbg;
        body = Media.imgButtonFancy;
        text = "Load Save";
        font = Media.defaultFontLarge;
    }

    @Override public void onClick() {
        WorldState.loadGame(true);
        sbg.enterState(Main.WORLD_ID);
    }
}
