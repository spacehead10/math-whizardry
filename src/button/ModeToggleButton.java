package button;

import combat.math.MathQuestion;
import core.Main;
import core.Media;
import core.state.WorldState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class ModeToggleButton extends Button {
    public ModeToggleButton(int x, int y) {
        super(x, y, OPTION_BUTTON_WIDTH, OPTION_BUTTON_HEIGHT);
        body = Media.imgButton;
        font = Media.defaultFontLarge;
    }

    @Override public void render(Graphics g, GameContainer gc) {
        if (MathQuestion.easyMode()) {
            text = "Easy";
        }
        else {
            text = "Normal";
        }
        super.render(g, gc);
    }

    @Override public void onClick() {
        if (MathQuestion.easyMode()) {
            MathQuestion.setEasyMode(false);
        }
        else {
            MathQuestion.setEasyMode(true);
        }
    }
}
