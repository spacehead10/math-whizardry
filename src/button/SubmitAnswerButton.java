package button;

import combat.math.MathQuestion;

public class SubmitAnswerButton extends Button {
    private MathQuestion question;

    public SubmitAnswerButton(int x, int y, MathQuestion question) {
        super(x, y, MEGA_SQUARE_BUTTON_SIZE, MEGA_SQUARE_BUTTON_SIZE);
        this.question = question;
        text = "Submit";
    }

    @Override public void onClick() {
        question.submitAnswer();
    }
}
