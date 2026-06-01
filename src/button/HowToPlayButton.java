package button;

import core.Main;
import core.Media;
import core.state.TitleState;
import core.state.WorldState;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

public class HowToPlayButton extends Button {
    private StateBasedGame sbg;

    public HowToPlayButton(int x, int y, StateBasedGame sbg) {
        super(x, y, LARGE_SQUARE_BUTTON_SIZE, LARGE_SQUARE_BUTTON_SIZE);
        this.sbg = sbg;
        body = Media.imgButtonFancy;
        icon = Media.imgButtonIconHowToPlay.getScaledCopy(0.67f * w / Media.imgButtonIconInventory.getWidth());
    }

    @Override public void render(Graphics g, GameContainer gc) {
        super.render(g, gc);
        g.setColor(Color.white);
        Media.drawAlignedString("How To Play", x + w / 2, y + h, Media.CENTER, Media.BOTTOM, Media.defaultFontTiny, g);
    }

    @Override public void onClick() {
        sbg.enterState(Main.HOW_TO_PLAY_ID);
    }
}
