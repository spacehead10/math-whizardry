package button;

import combat.math.MathQuestion;
import core.Main;
import core.Media;
import core.state.WorldState;
import org.newdawn.slick.state.StateBasedGame;

public class NormalModeButton extends Button {
    private StateBasedGame sbg;

    public NormalModeButton(int x, int y, StateBasedGame sbg) {
        super(x, y, MODE_SELECTION_BUTTON_WIDTH, MODE_SELECTION_BUTTON_HEIGHT);
        this.sbg = sbg;
        body = Media.imgButton;
        text = "Normal";
        font = Media.defaultFontLarge;
    }

    @Override public void onClick() {
        MathQuestion.setEasyMode(false);
        WorldState.loadGame(false);
        sbg.enterState(Main.WORLD_ID);
    }
}
