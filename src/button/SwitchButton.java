package button;

import combat.Battle;

public class SwitchButton extends Button {
    private Battle battle;

    public SwitchButton(int x, int y, Battle battle) {
        super(x, y, LARGE_SQUARE_BUTTON_SIZE, LARGE_SQUARE_BUTTON_SIZE);
        this.battle = battle;
        text = "Switch";
    }

    @Override public void onClick() {
        battle.chooseAttack(4);
    }
}
