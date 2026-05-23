package button;

import core.Media;
import core.Values;
import org.newdawn.slick.*;

public abstract class Button implements Values {
    protected int x, y, w, h;
    protected Image body, icon;
    protected String text;
    protected TrueTypeFont font;

    public Button(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        text = "";
        body = Media.imgButton;
        font = Media.defaultFontMedium;
    }

    public void render(Graphics g, GameContainer gc) {
        if (body != null) {
            body.draw(x, y, w, h);
        }
        if (icon != null) {
            icon.draw(x + w/2 - icon.getWidth()/2, y + h/2 - icon.getHeight()/2);
        }
        if (!text.isEmpty()) {
            g.setColor(Color.white);
            Media.drawAlignedString(text, x + w / 2, y + h / 2, Media.CENTER, Media.CENTER, font, g);
        }
        if (mouseOver(gc)) {
            g.setColor(new Color(0, 0, 0, 63));
            g.fillRect(x, y, w, h);
        }
    }

    public final void mousePressed(int mx, int my) {
        if (mouseOver(mx, my)) {
            Media.sfxButton.play();
            onClick();
        }
    }

    public abstract void onClick();

    public boolean mouseOver(int mx, int my) {
        return ((mx > x && mx < x + w) && (my > y && my < y + h));
    }

    public boolean mouseOver(GameContainer gc) {
        return mouseOver(gc.getInput().getMouseX(), gc.getInput().getMouseY());
    }
}
