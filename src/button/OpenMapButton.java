package button;

import core.state.WorldState;

public class OpenMapButton extends Button {
    private WorldState ws;

    public OpenMapButton(int x, int y, WorldState ws) {
        super(x, y, DEFAULT_SQUARE_BUTTON_SIZE, DEFAULT_SQUARE_BUTTON_SIZE);
        this.ws = ws;
        text = "tp";
    }

    @Override public void onClick() {
        ws.openMap();
    }
}
