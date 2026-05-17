package world.terrain;

import core.Media;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import world.Cell;
import world.World;
import world.biome.Biome;
import world.worldUnit.PlayerWorldUnit;

public class Wall extends Terrain {
    private Image cornerBL, cornerBR, cornerTL, cornerTR;
    private Image bottomSide, topSide, leftSide, rightSide;
    private Image center;
    private Image bottomOverhang, topOverhang, leftOverhang, rightOverhang;
    private Image narrowHorizontal, narrowVertical;

    public Wall() {
        color = Color.blue;
    }

    @Override public void render(Graphics g, Biome biome) {
        float x = cell.getX();
        float y = cell.getY();
        int w = Cell.getWidth();
        int h = Cell.getHeight();
        if (biome == World.calderaCastle()) {
            cornerBL = Media.imgCalderaCastleWallCornerBL;
            cornerBR = Media.imgCalderaCastleWallCornerBR;
            cornerTL = Media.imgCalderaCastleWallCornerTL;
            cornerTR = Media.imgCalderaCastleWallCornerTR;
            bottomSide = Media.imgCalderaCastleWallBottomSide;
            topSide = Media.imgCalderaCastleWallTopSide;
            leftSide = Media.imgCalderaCastleWallLeftSide;
            rightSide = Media.imgCalderaCastleWallRightSide;
            bottomOverhang = Media.imgCalderaCastleWallBottomOverhang;
            topOverhang = Media.imgCalderaCastleWallTopOverhang;
            leftOverhang = Media.imgCalderaCastleWallLeftOverhang;
            rightOverhang = Media.imgCalderaCastleWallRightOverhang;
            center = Media.imgCalderaCastleWallCenter;
            narrowHorizontal = Media.imgCalderaCastleWallNarrowHorizontal;
            narrowVertical = Media.imgCalderaCastleWallNarrowVertical;
        }
        else if (biome == World.permafrostGlaciers()) {
            cornerBL = Media.imgPermafrostGlaciersWallCornerBL;
            cornerBR = Media.imgPermafrostGlaciersWallCornerBR;
            cornerTL = Media.imgPermafrostGlaciersWallCornerTL;
            cornerTR = Media.imgPermafrostGlaciersWallCornerTR;
            bottomSide = Media.imgPermafrostGlaciersWallBottomSide;
            topSide = Media.imgPermafrostGlaciersWallTopSide;
            leftSide = Media.imgPermafrostGlaciersWallLeftSide;
            rightSide = Media.imgPermafrostGlaciersWallRightSide;
            bottomOverhang = Media.imgPermafrostGlaciersWallBottomOverhang;
            topOverhang = Media.imgPermafrostGlaciersWallTopOverhang;
            leftOverhang = Media.imgPermafrostGlaciersWallLeftOverhang;
            rightOverhang = Media.imgPermafrostGlaciersWallRightOverhang;
            center = Media.imgPermafrostGlaciersWallCenter;
            narrowHorizontal = Media.imgPermafrostGlaciersWallNarrowHorizontal;
            narrowVertical = Media.imgPermafrostGlaciersWallNarrowVertical;
        }

        if (cell.topTouchingWall() && cell.bottomTouchingWall() && cell.leftTouchingWall() && cell.rightTouchingWall() && center != null) {
            center.draw(x, y, w, h);
        }
        else if (cell.topTouchingWall() && cell.bottomTouchingWall() && cell.leftTouchingWall() && rightSide != null) {
            rightSide.draw(x, y, w, h);
        }
        else if (cell.topTouchingWall() && cell.bottomTouchingWall() && cell.rightTouchingWall() && leftSide != null) {
            leftSide.draw(x, y, w, h);
        }
        else if (cell.topTouchingWall() && cell.leftTouchingWall() && cell.rightTouchingWall() && bottomSide != null) {
            bottomSide.draw(x, y, w, h);
        }
        else if (cell.bottomTouchingWall() && cell.leftTouchingWall() && cell.rightTouchingWall() && topSide != null) {
            topSide.draw(x, y, w, h);
        }
        else if (cell.topTouchingWall() && cell.leftTouchingWall() && cornerBR != null) {
            cornerBR.draw(x, y, w, h);
        }
        else if (cell.topTouchingWall() && cell.rightTouchingWall() && cornerBL != null) {
            cornerBL.draw(x, y, w, h);
        }
        else if (cell.bottomTouchingWall() && cell.leftTouchingWall() && cornerTR != null) {
            cornerTR.draw(x, y, w, h);
        }
        else if (cell.bottomTouchingWall() && cell.rightTouchingWall() && cornerTL != null) {
            cornerTL.draw(x, y, w, h);
        }
        else if (cell.topTouchingWall() && cell.bottomTouchingWall() && narrowVertical != null) {
            narrowVertical.draw(x, y, w, h);
        }
        else if (cell.leftTouchingWall() && cell.rightTouchingWall() && narrowHorizontal != null) {
            narrowHorizontal.draw(x, y, w, h);
        }
        else if (cell.topTouchingWall() && bottomOverhang != null) {
            bottomOverhang.draw(x, y, w, h);
        }
        else if (cell.bottomTouchingWall() && topOverhang != null) {
            topOverhang.draw(x, y, w, h);
        }
        else if (cell.leftTouchingWall() && rightOverhang != null) {
            rightOverhang.draw(x, y, w, h);
        }
        else if (cell.rightTouchingWall() && leftOverhang != null) {
            leftOverhang.draw(x, y, w, h);
        }
        else {
            super.render(g, biome);
        }
    }
}
