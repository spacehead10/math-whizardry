package button;

import core.state.WorldState;

public class OpenPetSelectorButton extends Button {
    private WorldState ws;

    public OpenPetSelectorButton(int x, int y, WorldState ws) {
        super(x, y, DEFAULT_SQUARE_BUTTON_SIZE, DEFAULT_SQUARE_BUTTON_SIZE);
        this.ws = ws;
        text = "team";
    }

    @Override public void onClick() {
        ws.openPetSelector();
    }
}
