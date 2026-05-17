package button;

import combat.Battle;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import static core.Main.getScreenHeight;
import static core.Main.getScreenWidth;

public class SwitchChoiceButton extends Button {
    private int position;
    private Battle battle;

    public SwitchChoiceButton(int position, Battle battle) {
        super(0, 0, SWITCH_CHOICE_BUTTON_SIZE, SWITCH_CHOICE_BUTTON_SIZE);
        this.position = position;
        this.battle = battle;
        x = (int) (getScreenWidth() * 0.075f) - SWITCH_CHOICE_BUTTON_SIZE / 2;
        float yPercent = switch (position) {
            case 0 -> 0.35f;
            case 1 -> 0.55f;
            default -> 0;
        };
        y = (int) (getScreenHeight() * yPercent - SWITCH_CHOICE_BUTTON_SIZE / 2 + 50);
    }

    @Override public void render(Graphics g, GameContainer gc) {
        g.setColor(Color.white);
        g.drawRect(x, y, w, h);
        g.setColor(Color.black);
        g.drawRect(x - 1, y - 1, w + 2, h + 2);
    }

    @Override public void onClick() {
        battle.chooseSwitch(position);
    }
}
