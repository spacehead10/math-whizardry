package popup;

import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.List;

public class PopupManager {
    private static List<PopupObject> popups;

    public static void init() {
        popups = new ArrayList<>();
    }

    public static void update() {
        for (int i = 0; i < popups.size(); i++) {
            popups.get(i).update();
            if (popups.get(i).getTimeLeft() == 0) {
                popups.remove(i);
                i--;
            }
        }
    }

    public static void render(Graphics g) {
        for (PopupObject p : popups) {
            p.render(g);
        }
    }

    public static void addPopup(PopupObject p) {
        popups.add(p);
    }

    public static void clear() {
        popups.clear();
    }
}
