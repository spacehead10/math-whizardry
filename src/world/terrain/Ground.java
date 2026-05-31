package world.terrain;

import core.Media;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import world.Cell;
import world.World;
import world.biome.Biome;

public class Ground extends Terrain {
    private boolean enemySpawn;
    private char tpZoneType;

    public Ground(boolean enemySpawn, char tpZoneType) {
        this.tpZoneType = tpZoneType;
        this.enemySpawn = enemySpawn;
        if (isTPStart()) {
            color = Color.green;
        }
        else if (enemySpawn) {
            color = Color.red;
        }
        else {
            color = Color.cyan;
        }
    }

    @Override public void render(Graphics g, Biome biome) {
        float x = cell.getX();
        float y = cell.getY();
        int w = Cell.getWidth();
        int h = Cell.getHeight();
        if (biome == World.calderaCastle()) {
            Media.imgCalderaCastleGround.draw(x, y, w, h);
        }
        else if (biome == World.permafrostGlaciers()) {
            Media.imgPermafrostGlaciersGround.draw(x, y, w, h);
        }
        else if (biome == World.cumulusAcropolis()) {
            Media.imgCumulusAcropolisGround.draw(x, y, w, h);
        }
        else if (biome == World.pearlescentReef()) {
            Media.imgPearlescentReefGround.draw(x, y, w, h);
        }
        else if (biome == World.overgrowthGardens()) {
            Media.imgOvergrowthGardensGround.draw(x, y, w, h);
        }
        else {
            super.render(g, biome);
        }
    }

    public boolean isEnemySpawn() {
        return enemySpawn;
    }

    public boolean isTPStart() {
        return tpZoneType == 'U' || tpZoneType == 'D' || tpZoneType == 'L' || tpZoneType == 'R';
    }

    public boolean isTPEnd() {
        return tpZoneType == 'u' || tpZoneType == 'd' || tpZoneType == 'l' || tpZoneType == 'r';
    }

    public char getTPZoneType() {
        return tpZoneType;
    }
}
