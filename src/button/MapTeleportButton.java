package button;

import core.state.WorldState;
import world.biome.Biome;
import world.World;

public class MapTeleportButton extends Button {
    private Biome biome;
    private WorldState ws;

    public MapTeleportButton(int x, int y, Biome biome, WorldState ws) {
        super(x, y, TELEPORT_BUTTON_WIDTH, TELEPORT_BUTTON_HEIGHT);
        this.biome = biome;
        this.ws = ws;
        text = biome.getName();
    }

    @Override public void onClick() {
        World.goToBiome(biome);
        ws.closeMap();
    }
}
