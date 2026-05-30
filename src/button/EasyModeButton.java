package button;

import combat.math.MathQuestion;
import core.Main;
import core.Media;
import core.state.WorldState;
import org.newdawn.slick.state.StateBasedGame;

public class EasyModeButton extends Button {
    private StateBasedGame sbg;

    public EasyModeButton(int x, int y, StateBasedGame sbg) {
        super(x, y, MODE_SELECTION_BUTTON_WIDTH, MODE_SELECTION_BUTTON_HEIGHT);
        this.sbg = sbg;
        body = Media.imgButton;
        text = "Easy";
        font = Media.defaultFontLarge;
    }

    @Override public void onClick() {
        MathQuestion.setEasyMode(true);
        WorldState.loadGame(false);
        sbg.enterState(Main.WORLD_ID);
    }
}
