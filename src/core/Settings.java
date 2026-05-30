package core;

import button.ModeToggleButton;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import static core.Main.getScreenWidth;
import static core.Main.getScreenHeight;

public class Settings implements Values {
    private ModeToggleButton modeToggleButton;

    public Settings() {
        modeToggleButton = new ModeToggleButton(20 + Media.defaultFontMedium.getWidth("Current math difficulty: "), (20 + Media.defaultFontLarge.getHeight() + 10) - OPTION_BUTTON_HEIGHT / 2);
    }

    public void render(Graphics g, GameContainer gc) {
        g.setColor(Color.white);
        g.fillRect(10, 10, getScreenWidth() - 20, getScreenHeight() - 20);
        g.setColor(Color.black);
        Media.drawAlignedString("Settings", getScreenWidth() / 2, 20, Media.CENTER, Media.TOP, Media.defaultFontLarge, g);
        Media.drawAlignedString("Current math difficulty: ", 20, 20 + Media.defaultFontLarge.getHeight() + 10, Media.LEFT, Media.CENTER, Media.defaultFontMedium, g);
        modeToggleButton.render(g, gc);
    }

    public void mousePressed(int button, int x, int y) {
        modeToggleButton.mousePressed(x, y);
    }
}
