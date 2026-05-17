package button;

import core.state.WorldState;

public class OpenInventoryButton extends Button {
    private WorldState ws;

    public OpenInventoryButton(int x, int y, WorldState ws) {
        super(x, y, DEFAULT_SQUARE_BUTTON_SIZE, DEFAULT_SQUARE_BUTTON_SIZE);
        this.ws = ws;
        text = "inv";
    }

    @Override public void onClick() {
        ws.openInventory();
    }
}
