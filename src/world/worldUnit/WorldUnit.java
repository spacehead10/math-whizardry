package world.worldUnit;

import core.Values;
import entities.Entity;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

public abstract class WorldUnit implements Values {
    protected Entity entity;
    protected float x, y;
    protected boolean facingLeft;
    protected double animationTimer;
    protected int animationTick;

    public WorldUnit(Entity entity) {
        this.entity = entity;

        facingLeft = (Math.random() < 0.5);
        animationTimer = 0;
        animationTick = 0;
    }

    public void draw(Graphics g) {
        animationTimer += ANIMATION_TIMER_RATE;
        if (animationTimer >= 6) {
            animationTimer = 0;
        }
        animationTick = (int) animationTimer;

        if (facingLeft) {
            SpriteSheet sheet = entity.getSheetRight();
            if (sheet != null) {
                Image image = sheet.getSprite(animationTick, 0);
                if (image != null) {
                    image = image.getScaledCopy(WORLD_UNIT_SCALE);
                    image.draw(x - image.getWidth() / 2, y - image.getHeight());
                }
            }
        }
        else {
            SpriteSheet sheet = entity.getSheetLeft();
            if (sheet != null) {
                Image image = sheet.getSprite(animationTick, 0);
                if (image != null) {
                    image = image.getScaledCopy(WORLD_UNIT_SCALE);
                    image.draw(x - image.getWidth() / 2, y - image.getHeight());
                }
            }
        }
    }

    public void setLocation(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Entity getEntity() {
        return entity;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        if (entity.getSheetLeft() != null) {
            return entity.getSheetLeft().getSprite(0, 0).getWidth() / 3;
        }
        else {
            return 0;
        }
    }

    public float getHeight() {
        if (entity.getSheetLeft() != null) {
            return entity.getSheetLeft().getSprite(0, 0).getHeight() / 3;
        }
        else {
            return 0;
        }
    }
}
