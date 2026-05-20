package button;

import combat.Battle;
import core.Media;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class SwitchButton extends Button {
    private Battle battle;
    private boolean usable;

    public SwitchButton(int x, int y, Battle battle) {
        super(x, y, LARGE_SQUARE_BUTTON_SIZE, LARGE_SQUARE_BUTTON_SIZE);
        this.battle = battle;
        usable = false;
        text = "Switch";
    }

    public void updateUsable(boolean usable) {
        this.usable = usable;
    }

    @Override public void render(Graphics g, GameContainer gc) {
        super.render(g, gc);
        if (!usable) {
            g.setColor(new Color(0, 0, 0, 127));
            g.fillRect(x, y, w, h);
            g.setColor(Color.red);
            Media.drawAlignedString("No ally available", x + w / 2, y + h, Media.CENTER, Media.BOTTOM, Media.defaultFontTiny, g);
        }
    }

    @Override public void onClick() {
        battle.chooseAttack(4);
    }
}
