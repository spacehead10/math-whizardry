package combat.attack;

import combat.Elements;
import combat.Team;
import core.Values;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import static core.Main.getScreenHeight;
import static core.Main.getScreenWidth;

public abstract class Attack implements Values {
    protected Elements element;
    protected double damage;
    protected String name;
    protected boolean isAreaAttack;
    protected SpriteSheet sheetLeft, sheetRight;

    public void use(Team t, boolean missed) {
        t.takeDamage(this, isAreaAttack, missed);
    }

    public void render(Graphics g, boolean fromLeft, int tick, int moveTimer) {
        float x;
        SpriteSheet sheet;
        if (fromLeft) {
            x = getScreenWidth() * 0.9f;
            sheet = sheetRight;
        }
        else {
            x = getScreenWidth() * 0.1f;
            sheet = sheetLeft;
        }

        if (sheet != null) {
            Image image = sheet.getSprite(tick, 0);
            if (image != null) {
                image.draw(x - image.getWidth() / 2, getScreenHeight() * 0.45f - image.getHeight() / 2);
            }
        }
    }

    public final void movingRender(Graphics g, boolean fromLeft, int tick, int moveTimer) {
        float x;
        SpriteSheet sheet;
        if (fromLeft) {
            x = (getScreenWidth() * 0.15f + 284/2) + (moveTimer * PROJECTILE_SPEED);
            sheet = sheetRight;
        }
        else {
            x = (getScreenWidth() * 0.85f - 284/2) - (moveTimer * PROJECTILE_SPEED);
            sheet = sheetLeft;
        }

        if (sheet != null) {
            Image image = sheet.getSprite(tick, 0);
            if (image != null) {
                image.draw(x - image.getWidth() / 2, getScreenHeight() * 0.45f - image.getHeight() / 2);
            }
        }
    }

    public Elements getElement() {
        return element;
    }

    public String getName() {
        return name;
    }

    public String getNameOfElement() {
        return switch (element) {
            case LIGHT -> "Light";
            case FIRE -> "Fire";
            case WATER -> "Water";
            case STORM -> "Storm";
            case ICE -> "Ice";
            case EARTH -> "Earth";
        };
    }

    public double getDamage() {
        return damage;
    }

    public int getSheetSize() {
        if (sheetLeft != null) {
            return sheetLeft.getHorizontalCount();
        }
        else if (sheetRight != null) {
            return sheetRight.getHorizontalCount();
        }
        else {
            return 0;
        }
    }
}
