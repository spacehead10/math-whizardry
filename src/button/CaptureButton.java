package button;

import combat.Battle;

public class CaptureButton extends Button {
    private Battle battle;

    public CaptureButton(int x, int y, Battle battle) {
        super(x, y, LARGE_SQUARE_BUTTON_SIZE, LARGE_SQUARE_BUTTON_SIZE);
        this.battle = battle;
        text = "Capture";
    }

    @Override public void onClick() {
        battle.chooseAttack(5);
    }
}
