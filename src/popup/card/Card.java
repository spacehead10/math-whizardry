package popup.card;

import core.Values;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import popup.PopupObject;

public class Card extends PopupObject implements Values {
    protected float w, h;
    protected Color backColor, textColor;

    public Card(float x, float y, float w, float h, Color backColor, Color textColor, int duration, boolean fading) {
        super(x, y, duration, fading);
        this.w = w;
        this.h = h;
        this.backColor = backColor;
        this.textColor = textColor;
    }

    public Card(float x, float y, float w, float h, Color backColor) {
        this(x, y, w, h, backColor, Color.white, DEFAULT_CARD_DURATION, true);
    }

    @Override public void render(Graphics g) {
        if (fading) {
            backColor = new Color(backColor.getRed(), backColor.getGreen(), backColor.getBlue(), (int) (getPercentTimeLeft() * 255));
        }
        g.setColor(backColor);
        g.fillRoundRect(x - w / 2, y, w, h, 7);
        if (fading) {
            g.setColor(new Color(0, 0, 0, (int) (getPercentTimeLeft() * 255)));
        }
        else {
            g.setColor(Color.black);
        }
        g.drawRoundRect(x - w / 2, y, w, h, 7);
    }
}
