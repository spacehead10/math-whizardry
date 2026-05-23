package popup.message;

import core.Media;
import org.newdawn.slick.Color;

import static core.Main.getScreenWidth;
import static core.Main.getScreenHeight;

public class BattleAnnouncement extends Message {
    public BattleAnnouncement(String text, float y, int duration) {
        super(text, getScreenWidth() * 0.5f, y, Color.white, Media.defaultFontLarge, duration, true, Media.CENTER, Media.CENTER);
    }

    public BattleAnnouncement(String text, float y) {
        this(text, y, BATTLE_ANNOUNCEMENT_DURATION);
    }

    public BattleAnnouncement(String text) {
        this(text, getScreenHeight() * 0.8f);
    }
}
