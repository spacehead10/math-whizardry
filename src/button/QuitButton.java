package button;

import core.state.WorldState;

public class QuitButton extends Button {
    private WorldState ws;

    public QuitButton(int x, int y, WorldState ws) {
        super(x, y, DEFAULT_SQUARE_BUTTON_SIZE, DEFAULT_SQUARE_BUTTON_SIZE);
        this.ws = ws;
        text = "Quit";
    }

    @Override public void onClick() {
        ws.quit();
    }
}
