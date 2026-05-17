package world.terrain;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import world.Cell;
import world.biome.Biome;

public abstract class Terrain {
    protected Cell cell;
    protected Color color;

    public Terrain() {
        color = Color.gray;
    }

    public void setCell(Cell c) {
        cell = c;
    }

    public void render(Graphics g, Biome biome) {
        int w = Cell.getWidth();
        int h = Cell.getHeight();
        g.setColor(color);
        g.fillRect(cell.getGridX() * w, cell.getGridY() * h, w, h);
    }
}
