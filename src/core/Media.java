package core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import java.awt.Font;
import org.newdawn.slick.SlickException;

public class Media {
    public static final TrueTypeFont defaultFontTiny = new TrueTypeFont(new Font("Trebuchet MS", Font.PLAIN, 16), false);
    public static final TrueTypeFont defaultFontSmall = new TrueTypeFont(new Font("Trebuchet MS", Font.PLAIN, 20), false);
    public static final TrueTypeFont defaultFontMedium = new TrueTypeFont(new Font("Trebuchet MS", Font.PLAIN, 32), false);
    public static final TrueTypeFont defaultFontLarge = new TrueTypeFont(new Font("Trebuchet MS", Font.PLAIN, 48), false);
    public static final TrueTypeFont debugFont = new TrueTypeFont(new Font("Roboto Mono", Font.PLAIN, 16), false);

    public static Image imgButton;
    public static Image imgBattleBGFire;
    public static Image imgBattleBGIce;
    public static Image imgCalderaCastleGround;
    public static Image imgPermafrostGlaciersGround;
    public static Image imgCalderaCastleWallCornerBL;
    public static Image imgCalderaCastleWallBottomOverhang;
    public static Image imgCalderaCastleWallCornerBR;
    public static Image imgCalderaCastleWallBottomSide;
    public static Image imgCalderaCastleWallNarrowHorizontal;
    public static Image imgCalderaCastleWallLeftOverhang;
    public static Image imgCalderaCastleWallLeftSide;
    public static Image imgCalderaCastleWallRightOverhang;
    public static Image imgCalderaCastleWallRightSide;
    public static Image imgCalderaCastleWallCenter;
    public static Image imgCalderaCastleWallTopOverhang;
    public static Image imgCalderaCastleWallCornerTR;
    public static Image imgCalderaCastleWallTopSide;
    public static Image imgCalderaCastleWallCornerTL;
    public static Image imgCalderaCastleWallNarrowVertical;
    public static Image imgPermafrostGlaciersWallCornerBL;
    public static Image imgPermafrostGlaciersWallBottomOverhang;
    public static Image imgPermafrostGlaciersWallCornerBR;
    public static Image imgPermafrostGlaciersWallBottomSide;
    public static Image imgPermafrostGlaciersWallNarrowHorizontal;
    public static Image imgPermafrostGlaciersWallLeftOverhang;
    public static Image imgPermafrostGlaciersWallLeftSide;
    public static Image imgPermafrostGlaciersWallRightOverhang;
    public static Image imgPermafrostGlaciersWallRightSide;
    public static Image imgPermafrostGlaciersWallCenter;
    public static Image imgPermafrostGlaciersWallTopOverhang;
    public static Image imgPermafrostGlaciersWallCornerTR;
    public static Image imgPermafrostGlaciersWallTopSide;
    public static Image imgPermafrostGlaciersWallCornerTL;
    public static Image imgPermafrostGlaciersWallNarrowVertical;
    public static Image imgMysteriousFan;
    public static Image imgLimestoneWand;
    public static Image imgTrident;
    public static Image imgFireRelicOne;
    public static Image imgGold;
    public static SpriteSheet sheetCreatureFlameLutinLeft;
    public static SpriteSheet sheetCreatureFlameLutinRight;
    public static SpriteSheet sheetCreatureGardenLutinLeft;
    public static SpriteSheet sheetCreatureGardenLutinRight;
    public static SpriteSheet sheetCreatureFrostLutinLeft;
    public static SpriteSheet sheetCreatureFrostLutinRight;
    public static SpriteSheet sheetCreatureRiverLutinLeft;
    public static SpriteSheet sheetCreatureRiverLutinRight;
    public static SpriteSheet sheetCreatureCloudLutinLeft;
    public static SpriteSheet sheetCreatureCloudLutinRight;
    public static SpriteSheet sheetWizardLeft;
    public static SpriteSheet sheetWizardRight;
    public static SpriteSheet sheetWizardWalkingLeft;
    public static SpriteSheet sheetWizardWalkingRight;

    public static void loadImages() throws SlickException {
        imgButton = new Image("res/buttonGray.png");
        imgBattleBGFire = new Image("res/battleBGFire.png");
        imgBattleBGIce = new Image("res/battleBGIce.png");
        sheetCreatureFlameLutinLeft = new SpriteSheet(new Image("res/creatureFlameLutinLeft.png"), 284, 284);
        sheetCreatureFlameLutinRight = new SpriteSheet(new Image("res/creatureFlameLutinRight.png"), 284, 284);
        sheetCreatureGardenLutinLeft = new SpriteSheet(new Image("res/creatureGardenLutinLeft.png"), 284, 284);
        sheetCreatureGardenLutinRight = new SpriteSheet(new Image("res/creatureGardenLutinRight.png"), 284, 284);
        sheetCreatureFrostLutinLeft = new SpriteSheet(new Image("res/creatureFrostLutinLeft.png"), 284, 284);
        sheetCreatureFrostLutinRight = new SpriteSheet(new Image("res/creatureFrostLutinRight.png"), 284, 284);
        sheetCreatureRiverLutinLeft = new SpriteSheet(new Image("res/creatureRiverLutinLeft.png"), 284, 284);
        sheetCreatureRiverLutinRight = new SpriteSheet(new Image("res/creatureRiverLutinRight.png"), 284, 284);
        sheetCreatureCloudLutinLeft = new SpriteSheet(new Image("res/creatureCloudLutinLeft.png"), 284, 284);
        sheetCreatureCloudLutinRight = new SpriteSheet(new Image("res/creatureCloudLutinRight.png"), 284, 284);
        sheetWizardLeft = new SpriteSheet(new Image("res/wizardIdleLeft.png"), 284, 284);
        sheetWizardRight = new SpriteSheet(new Image("res/wizardIdleRight.png"), 284, 284);
        sheetWizardWalkingLeft = new SpriteSheet(new Image("res/wizardWalkingLeft.png"), 284, 284);
        sheetWizardWalkingRight = new SpriteSheet(new Image("res/wizardWalkingRight.png"), 284, 284);
        imgCalderaCastleGround = new Image("res/stoneTile.png");
        imgPermafrostGlaciersGround = new Image("res/iceTile.png");
        imgCalderaCastleWallCornerBL = new Image("res/wallBottomLeftCorner.png");
        imgCalderaCastleWallBottomOverhang = new Image("res/wallBottomOverhang.png");
        imgCalderaCastleWallCornerBR = new Image("res/wallBottomRightCorner.png");
        imgCalderaCastleWallBottomSide = new Image("res/wallBottomSide.png");
        imgCalderaCastleWallNarrowHorizontal = new Image("res/wallHorizontalNarrow.png");
        imgCalderaCastleWallLeftOverhang = new Image("res/wallLeftOverhang.png");
        imgCalderaCastleWallLeftSide = new Image("res/wallLeftSide.png");
        imgCalderaCastleWallRightOverhang = new Image("res/wallRightOverhang.png");
        imgCalderaCastleWallRightSide = new Image("res/wallRightSide.png");
        imgCalderaCastleWallCenter = new Image("res/wallTileCenter.png");
        imgCalderaCastleWallTopOverhang = new Image("res/wallTopOverhang.png");
        imgCalderaCastleWallCornerTR = new Image("res/wallTopRightCorner.png");
        imgCalderaCastleWallTopSide = new Image("res/wallTopSide.png");
        imgCalderaCastleWallCornerTL = new Image("res/wallTopLeftCorner.png");
        imgCalderaCastleWallNarrowVertical = new Image("res/wallVerticalNarrow.png");
        imgPermafrostGlaciersWallCornerBL = new Image("res/iceWallBottomLeftCorner.png");
        imgPermafrostGlaciersWallBottomOverhang = new Image("res/iceWallBottomOverhang.png");
        imgPermafrostGlaciersWallCornerBR = new Image("res/iceWallBottomRightCorner.png");
        imgPermafrostGlaciersWallBottomSide = new Image("res/iceWallBottomSide.png");
        imgPermafrostGlaciersWallNarrowHorizontal = new Image("res/iceWallHorizontalNarrow.png");
        imgPermafrostGlaciersWallLeftOverhang = new Image("res/iceWallLeftOverhang.png");
        imgPermafrostGlaciersWallLeftSide = new Image("res/iceWallLeftSide.png");
        imgPermafrostGlaciersWallRightOverhang = new Image("res/iceWallRightOverhang.png");
        imgPermafrostGlaciersWallRightSide = new Image("res/iceWallRightSide.png");
        imgPermafrostGlaciersWallCenter = new Image("res/iceWallCenter.png");
        imgPermafrostGlaciersWallTopOverhang = new Image("res/iceWallTopOverhang.png");
        imgPermafrostGlaciersWallCornerTR = new Image("res/iceWallTopRightCorner.png");
        imgPermafrostGlaciersWallTopSide = new Image("res/iceWallTopSide.png");
        imgPermafrostGlaciersWallCornerTL = new Image("res/iceWallTopLeftCorner.png");
        imgPermafrostGlaciersWallNarrowVertical = new Image("res/iceWallVerticalNarrow.png");
        imgMysteriousFan = new Image("res/itemMysteriousFanWand.png");
        imgLimestoneWand = new Image("res/itemLimestoneWand.png");
        imgTrident = new Image("res/itemTridentWand.png");
        imgFireRelicOne = new Image("res/itemFireRelicOne.png");
        imgGold = new Image("res/itemGold.png");
    }

    public static final int LEFT = 0;
    public static final int TOP = 0;
    public static final int CENTER = 1;
    public static final int RIGHT = 2;
    public static final int BOTTOM = 2;

    /**
     * Draws aligned text.
     * @param str String to draw
     * @param x Position based on your chosen alignment
     * @param y Position based on your chosen alignment
     * @param xAlign Media.LEFT, Media.CENTER, or Media.RIGHT
     * @param yAlign Media.TOP, Media.CENTER, or Media.BOTTOM
     * @param font Font to use
     * @param g Pass the graphics object your drawing method is using
     */
    public static void drawAlignedString(String str, float x, float y, int xAlign, int yAlign, TrueTypeFont font, Graphics g) {
        float adjustedX, adjustedY;
        switch (xAlign) {
            case LEFT:
                adjustedX = x;
                break;
            case CENTER:
                adjustedX = x - font.getWidth(str) / 2f;
                break;
            case RIGHT:
                adjustedX = x - font.getWidth(str);
                break;
            default:
                return;
        }
        switch (yAlign) {
            case TOP:
                adjustedY = y;
                break;
            case CENTER:
                adjustedY = y - font.getHeight() / 2f;
                break;
            case BOTTOM:
                adjustedY = y - font.getHeight();
                break;
            default:
                return;
        }
        g.setFont(font);
        g.drawString(str, adjustedX, adjustedY);
    }

    public static void drawShadowedString(String str, float x, float y, int xAlign, int yAlign, TrueTypeFont font, Graphics g) {
        g.setColor(Color.black);
        drawAlignedString(str, x - 2, y - 2, xAlign, yAlign, font, g);
        g.setColor(Color.white);
        drawAlignedString(str, x, y, xAlign, yAlign, font, g);
    }
}
