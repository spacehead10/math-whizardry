package button;

import core.Media;
import core.state.WorldState;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class OpenShopButton extends Button {
    private WorldState ws;

    public OpenShopButton(int x, int y, WorldState ws) {
        super(x, y, DEFAULT_SQUARE_BUTTON_SIZE, DEFAULT_SQUARE_BUTTON_SIZE);
        this.ws = ws;
        icon = Media.imgButtonIconShop.getScaledCopy(0.67f * w / Media.imgButtonIconShop.getWidth());
    }

    @Override public void render(Graphics g, GameContainer gc) {
        super.render(g, gc);
        g.setColor(Color.white);
        Media.drawAlignedString("Shop", x + w / 2, y + h, Media.CENTER, Media.BOTTOM, Media.defaultFontTiny, g);
    }

    @Override public void onClick() {
        ws.openShop();
        Media.sfxOpen.play();
    }
}
