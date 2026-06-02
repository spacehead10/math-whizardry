package world;

import button.MapTeleportButton;
import core.Media;
import core.Values;
import core.state.WorldState;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import static core.Main.getScreenHeight;
import static core.Main.getScreenWidth;

public class TeleportMenu implements Values {
    private MapTeleportButton calderaCastleButton;
    private MapTeleportButton permafrostGlaciersButton;
    private MapTeleportButton cumulusAcropolisButton;
    private MapTeleportButton pearlescentReefButton;
    private MapTeleportButton overgrowthGardensButton;

    public TeleportMenu(WorldState ws) {
        calderaCastleButton = new MapTeleportButton(getScreenWidth() / 2 - TELEPORT_BUTTON_WIDTH / 2, 20 + Media.defaultFontLarge.getHeight() + 10, World.calderaCastle(), ws);
        permafrostGlaciersButton = new MapTeleportButton(getScreenWidth() / 2 - TELEPORT_BUTTON_WIDTH / 2, 20 + Media.defaultFontLarge.getHeight() + 10 + TELEPORT_BUTTON_HEIGHT + 10, World.permafrostGlaciers(), ws);
        cumulusAcropolisButton = new MapTeleportButton(getScreenWidth() / 2 - TELEPORT_BUTTON_WIDTH / 2, 20 + Media.defaultFontLarge.getHeight() + 10 + 2 * (TELEPORT_BUTTON_HEIGHT + 10), World.cumulusAcropolis(), ws);
    }

    public void render(Graphics g, GameContainer gc) {
        g.setColor(Color.white);
        g.fillRect(10, 10, getScreenWidth() - 20, getScreenHeight() - 20);
        g.setColor(Color.black);
        Media.drawAlignedString("Travel to Biome", getScreenWidth() / 2, 20, Media.CENTER, Media.TOP, Media.defaultFontLarge, g);
        calderaCastleButton.render(g, gc);
        permafrostGlaciersButton.render(g, gc);
        cumulusAcropolisButton.render(g, gc);
    }

    public void mousePressed(int button, int x, int y) {
        calderaCastleButton.mousePressed(x, y);
        permafrostGlaciersButton.mousePressed(x, y);
        cumulusAcropolisButton.mousePressed(x, y);
    }
}
