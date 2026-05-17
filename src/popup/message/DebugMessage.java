package popup.message;

import core.Media;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import static core.Main.getScreenWidth;
import static core.Main.getScreenHeight;

public class DebugMessage extends Message {
    public DebugMessage(String text, int index) {
        super(text, 10, 30 + index * (Media.debugFont.getHeight()), Color.white, Media.debugFont, 2, false, Media.LEFT, Media.TOP);
    }
}
