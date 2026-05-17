package combat.math;

import core.Media;
import core.Values;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class AnswerChoice implements Values {
    private MathString answerText;
    private boolean isCorrect;
    private boolean isSelected;
    private float x, y;

    public AnswerChoice(String answerText, boolean isCorrect, float x, float y) {
        this.answerText = new MathString(answerText);
        this.isCorrect = isCorrect;
        this.x = x;
        this.y = y;
        deselect();
    }

    public void select() {
        isSelected = true;
    }

    public void deselect() {
        isSelected = false;
    }

    public MathString getText() {
        return answerText;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }
}
