package button;

import core.state.WorldState;

public class CloseButton extends Button {
    private WorldState ws;

    public CloseButton(int x, int y, WorldState ws) {
        super(x, y, DEFAULT_SQUARE_BUTTON_SIZE, DEFAULT_SQUARE_BUTTON_SIZE);
        this.ws = ws;
        text = "x";
    }

    @Override public void onClick() {
        ws.closeInventory();
        ws.closePetSelector();
        ws.closeShop();
        ws.closeMap();
    }
}
