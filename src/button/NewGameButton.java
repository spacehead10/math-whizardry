package button;

import core.Main;
import core.Media;
import core.state.TitleState;
import core.state.WorldState;
import org.newdawn.slick.state.StateBasedGame;

public class NewGameButton extends Button {
    private TitleState ts;

    public NewGameButton(int x, int y, TitleState ts) {
        super(x, y, START_BUTTON_WIDTH, START_BUTTON_HEIGHT);
        this.ts = ts;
        body = Media.imgButtonFancy;
        text = "New Game";
        font = Media.defaultFontLarge;
    }

    @Override public void onClick() {
        ts.startNewGame();
    }
}
