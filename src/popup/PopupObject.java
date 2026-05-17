package popup;

import org.newdawn.slick.Graphics;

public abstract class PopupObject {
    protected float x, y;
    protected int duration, timer;
    protected boolean fading;

    public PopupObject(float x, float y, int duration, boolean fading) {
        this.x = x;
        this.y = y;
        this.duration = duration;
        timer = duration;
        this.fading = fading;
    }

    public void update() {
        if (timer > 0) {
            timer--;
        }
    }

    public abstract void render(Graphics g);

    public int getDuration() {
        return duration;
    }

    public int getTimeLeft() {
        return timer;
    }

    public double getPercentTimeLeft() {
        return (double) timer / duration;
    }
}
