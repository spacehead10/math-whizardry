package popup.message;

import core.Media;
import core.Values;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;
import popup.PopupObject;

public class Message extends PopupObject implements Values {
    protected String text;
    protected Color color;
    protected TrueTypeFont font;
    protected int xAlign, yAlign;

    public Message(String text, float x, float y, Color color, TrueTypeFont font, int duration, boolean fading, int xAlign, int yAlign) {
        super(x, y, duration, fading);
        this.text = text;
        this.color = color;
        this.font = font;
        this.xAlign = xAlign;
        this.yAlign = yAlign;
    }

    @Override public void render(Graphics g) {
        Color shadow;
        if (fading) {
            color = new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) (getPercentTimeLeft() * 255));
            shadow = new Color(0, 0, 0, (int) (getPercentTimeLeft() * 255));
        }
        else {
            shadow = new Color(0, 0, 0);
        }
        Media.drawShadowedString(text, x, y, xAlign, yAlign, font, color, shadow, g);
    }
}
