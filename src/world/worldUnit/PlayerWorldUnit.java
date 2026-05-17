package world.worldUnit;

import core.Media;
import entities.player.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import popup.message.DebugMessage;
import world.Cell;
import world.World;
import world.terrain.Wall;

import java.util.List;
import java.util.ArrayList;

import static popup.PopupManager.addPopup;

public class PlayerWorldUnit extends WorldUnit {
    private double animationTimer;
    private int animationTick;
    private SpriteSheet walkingLeft, walkingRight;
    private boolean walking;

    public PlayerWorldUnit(Player player) {
        super(player);
        animationTimer = 0;
        animationTick = 0;
        walkingLeft = Media.sheetWizardWalkingLeft;
        walkingRight = Media.sheetWizardWalkingRight;
        walking = false;
    }

    public void update(GameContainer gc) {
        Input input = gc.getInput();

        List<Cell> wallCells = new ArrayList<>();
        for (int i = 0; i < ROOM_GRID_WIDTH; i++) {
            for (int j = 0; j < ROOM_GRID_HEIGHT; j++) {
                Cell c = World.getCurrentBiome().getCurrentRoom().getCells()[i][j];
                if (c.getTerrain() instanceof Wall) {
                    wallCells.add(c);
                }
            }
        }

        if (input.isKeyDown(Input.KEY_W)) {
            boolean collided = false;
            for (Cell w : wallCells) {
                if (w.collisionBottom(this, PLAYER_SPEED)) {
                    y = w.getY() + w.getWidth() + getHeight() + 1;
                    collided = true;
                }
            }
            if (!collided) {
                y -= PLAYER_SPEED;
            }
        }
        if (input.isKeyDown(Input.KEY_A)) {
            facingLeft = true;
            boolean collided = false;
            for (Cell w : wallCells) {
                if (w.collisionRight(this, PLAYER_SPEED)) {
                    x = w.getX() + w.getWidth() + getWidth() / 2 + 1;
                    collided = true;
                }
            }
            if (!collided) {
                x -= PLAYER_SPEED;
            }
        }
        if (input.isKeyDown(Input.KEY_S)) {
            boolean collided = false;
            for (Cell w : wallCells) {
                if (w.collisionTop(this, PLAYER_SPEED)) {
                    y = w.getY() - 1;
                    collided = true;
                }
            }
            if (!collided) {
                y += PLAYER_SPEED;
            }
        }
        if (input.isKeyDown(Input.KEY_D)) {
            facingLeft = false;
            boolean collided = false;
            for (Cell w : wallCells) {
                if (w.collisionLeft(this, PLAYER_SPEED)) {
                    x = w.getX() - getWidth() / 2 - 1;
                    collided = true;
                }
            }
            if (!collided) {
                x += PLAYER_SPEED;
            }
        }

        if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_D)) {
            walking = true;
        }
        else {
            walking = false;
        }
    }

    @Override public void draw(Graphics g) {
        if (walking) {
            animationTimer += ANIMATION_TIMER_RATE;
            if (animationTimer >= 6) {
                animationTimer = 0;
            }
            animationTick = (int) animationTimer;

            if (facingLeft) {
                SpriteSheet sheet = walkingLeft;
                if (sheet != null) {
                    Image image = sheet.getSprite(animationTick, 0);
                    if (image != null) {
                        image = image.getScaledCopy(WORLD_UNIT_SCALE);
                        image.draw(x - image.getWidth() / 2, y - image.getHeight());
                    }
                }
            }
            else {
                SpriteSheet sheet = walkingRight;
                if (sheet != null) {
                    Image image = sheet.getSprite(animationTick, 0);
                    if (image != null) {
                        image = image.getScaledCopy(WORLD_UNIT_SCALE);
                        image.draw(x - image.getWidth() / 2, y - image.getHeight());
                    }
                }
            }
        }
        else {
            super.draw(g);
        }
    }

//    @Override public void draw(Graphics g) {
//        super.draw(g);
//        g.setColor(Color.green);
//        g.fill(new Circle(x, y, 10));
//        g.setColor(Color.blue);
//        g.drawRect(x - getWidth() / 2, y - getHeight(), getWidth(), getHeight());
//        g.setColor(Color.red);
//        g.draw(new Circle(x, y, AGGRO_RADIUS));
//    }
}
