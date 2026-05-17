package popup.message;

import core.Media;
import core.Values;
import org.newdawn.slick.Color;

public class FloatMessage extends Message {
    public FloatMessage(String text, float x, float y, Color color, int duration) {
        super(text, x, y, color, Media.defaultFontMedium, duration, true, Media.CENTER, Media.CENTER);
    }

    public void update() {
        super.update();
        y -= TEXT_FLOAT_SPEED;
    }
}
