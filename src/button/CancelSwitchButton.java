package button;

import combat.Battle;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import static core.Main.getScreenHeight;
import static core.Main.getScreenWidth;

public class CancelSwitchButton extends Button {
    private Battle battle;

    public CancelSwitchButton(int x, int y, Battle battle) {
        super(x, y, CANCEL_SWITCH_BUTTON_WIDTH, CANCEL_SWITCH_BUTTON_HEIGHT);
        this.battle = battle;
        text = "Cancel";
    }

    @Override public void onClick() {
        battle.cancelSwitch();
    }
}
