package button;

import combat.math.AnswerChoice;
import combat.math.MathQuestion;
import core.Media;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class AnswerChoiceButton extends Button {
    private AnswerChoice answerChoice;
    private MathQuestion question;

    public AnswerChoiceButton(AnswerChoice answerChoice, MathQuestion question) {
        super(answerChoice.getX(), answerChoice.getY(), ANSWER_CHOICE_WIDTH, Media.defaultFontMedium.getHeight());
        this.answerChoice = answerChoice;
        this.question = question;
    }

    @Override public void render(Graphics g, GameContainer gc) {
        if (body != null) {
            body.draw(x, y, w, h);
        }
        if (answerChoice.getText() != null) {
            g.setColor(Color.white);
            answerChoice.getText().draw(x + 5, y, g);
        }
        if (mouseOver(gc)) {
            g.setColor(new Color(0, 0, 0, 63));
            g.fillRect(x, y, w, h);
        }
        if (question.getSelectedAnswer() == answerChoice) {
            g.setColor(new Color(255, 255, 255, 64));
            g.fillRect(x, y, w, h);
        }
    }

    @Override public void onClick() {
        question.selectAnswer(answerChoice);
    }
}
