package button;

import core.Media;
import core.state.WorldState;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class OpenPetSelectorButton extends Button {
    private WorldState ws;

    public OpenPetSelectorButton(int x, int y, WorldState ws) {
        super(x, y, DEFAULT_SQUARE_BUTTON_SIZE, DEFAULT_SQUARE_BUTTON_SIZE);
        this.ws = ws;
        icon = Media.imgButtonIconTeamSelector;
    }

    @Override public void render(Graphics g, GameContainer gc) {
        super.render(g, gc);
        g.setColor(Color.white);
        Media.drawAlignedString("Team", x + w / 2, y + h, Media.CENTER, Media.BOTTOM, Media.defaultFontTiny, g);
    }

    @Override public void onClick() {
        ws.openPetSelector();
    }
}
