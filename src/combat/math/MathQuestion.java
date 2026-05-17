package combat.math;

import button.AnswerChoiceButton;
import button.SubmitAnswerButton;
import core.Media;
import core.Values;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import java.util.List;
import java.util.ArrayList;

import static core.Utils.randomIntInRange;
import static core.Main.getScreenWidth;
import static core.Main.getScreenHeight;

public class MathQuestion implements Values {
    private QuestionType type;
    private MathString questionText;

    private AnswerChoice[] answers;
    private AnswerChoice selectedAnswer;

    private boolean correctAnswerSubmitted;
    private boolean incorrectAnswerSubmitted;

    private AnswerChoiceButton[] answerChoiceButtons;
    private SubmitAnswerButton submitAnswerButton;

    private float answerX, answerY;
    private int correctAnswerIndex;
    private List<Integer> incorrectAnswerIndexes;

    public MathQuestion() {
        type = QuestionType.MULTIPLE_CHOICE;

        answerX = getScreenWidth() * 0.33f;
        answerY = getScreenHeight() * 0.5f;
        correctAnswerIndex = randomIntInRange(0, 3);
        incorrectAnswerIndexes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            if (i != correctAnswerIndex) {
                incorrectAnswerIndexes.add(i);
            }
        }

        answers = new AnswerChoice[4];

        switch (randomIntInRange(0, 4)) {
            case 0 -> generateDerivPowerQuestion();
            case 1 -> generateDerivTrigQuestion();
            case 2 -> generateDerivExponentialQuestion();
            case 3 -> generateIntegralPowerQuestion();
            default -> generateIntegralExponentialQuestion();
        }

        answerChoiceButtons = new AnswerChoiceButton[4];
        for (int i = 0; i < answerChoiceButtons.length; i++) {
            answerChoiceButtons[i] = new AnswerChoiceButton(answers[i], this);
        }

        submitAnswerButton = new SubmitAnswerButton(getScreenWidth() - 110 - MEGA_SQUARE_BUTTON_SIZE, getScreenHeight() - 110 - MEGA_SQUARE_BUTTON_SIZE, this);

        correctAnswerSubmitted = false;
        incorrectAnswerSubmitted = false;
    }

    public void render(Graphics g, GameContainer gc) {
        g.setColor(new Color(255, 255, 255, 127));

        int border = 100;
        g.fillRect(border, border, getScreenWidth() - 2 * border, getScreenHeight() - 2 * border);
        g.setColor(Color.black);
        questionText.draw(getScreenWidth() * 0.25f, getScreenHeight() * 0.33f, g);
        for (AnswerChoiceButton a : answerChoiceButtons) {
            a.render(g, gc);
        }
        submitAnswerButton.render(g, gc);
    }

    public void mousePressed(int x, int y) {
        for (AnswerChoiceButton a : answerChoiceButtons) {
            a.mousePressed(x, y);
        }
        submitAnswerButton.mousePressed(x, y);
    }

    public void selectAnswer(AnswerChoice selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
        for (AnswerChoice a : answers) {
            if (a == selectedAnswer) {
                a.select();
            }
            else {
                a.deselect();
            }
        }
    }

    public void submitAnswer() {
        if (selectedAnswer != null) {
            if (selectedAnswer.isCorrect()) {
                correctAnswerSubmitted = true;
            }
            else {
                incorrectAnswerSubmitted = true;
            }
        }
    }

    public void generateDerivPowerQuestion() {
        //Question
        int length = randomIntInRange(1, 3);

        int[] coeffs = new int[length];
        for (int i = 0; i < length; i++) {
            coeffs[i] = randomIntInRange(1, 9);
        }

        int[] powers = new int[length];
        for (int i = 0; i < length; i++) {
            powers[i] = randomIntInRange(0, 9);
        }

        //Bug testing code
//        length = 3;
//        coeffs = new int[length];
//        powers = new int[length];
//        coeffs[0] = randomIntInRange(1, 9);
//        coeffs[1] = randomIntInRange(1, 9);
//        coeffs[2] = randomIntInRange(1, 9);
//        powers[0] = 0;
//        powers[1] = 2;
//        powers[2] = 1;

//        length = 3;
//        coeffs = new int[length];
//        powers = new int[length];
//        coeffs[0] = randomIntInRange(1, 9);
//        coeffs[1] = randomIntInRange(1, 9);
//        coeffs[2] = randomIntInRange(1, 9);
//        powers[0] = 2;
//        powers[1] = 0;
//        powers[2] = 1;

//        length = 3;
//        coeffs = new int[length];
//        powers = new int[length];
//        coeffs[0] = randomIntInRange(1, 9);
//        coeffs[1] = randomIntInRange(1, 9);
//        coeffs[2] = randomIntInRange(1, 9);
//        powers[0] = 2;
//        powers[1] = 1;
//        powers[2] = 0;

//        length = 3;
//        coeffs = new int[length];
//        powers = new int[length];
//        coeffs[0] = randomIntInRange(1, 9);
//        coeffs[1] = randomIntInRange(1, 9);
//        coeffs[2] = randomIntInRange(1, 9);
//        powers[0] = 0;
//        powers[1] = 0;
//        powers[2] = 2;

//        length = 3;
//        coeffs = new int[length];
//        powers = new int[length];
//        coeffs[0] = randomIntInRange(1, 9);
//        coeffs[1] = randomIntInRange(1, 9);
//        coeffs[2] = randomIntInRange(1, 9);
//        powers[0] = 2;
//        powers[1] = 0;
//        powers[2] = 0;

//        length = 1;
//        coeffs = new int[length];
//        powers = new int[length];
//        coeffs[0] = randomIntInRange(1, 9);
//        powers[0] = 0;

        String baseText = "";
        for (int i = 0; i < length; i++) {
            if (coeffs[i] > 1 || powers[i] == 0) {
                baseText = baseText + coeffs[i];
            }
            if (powers[i] > 0) {
                baseText = baseText + "x";
                if (powers[i] > 1) {
                    baseText = baseText + "^{" + powers[i] + "}";
                }
            }
            if (i < length - 1) {
                baseText = baseText + "+";
            }
        }
        questionText = new MathString("What is the derivative of " + baseText + "?");

        //Correct answer
        {
            int[] correctCoeffs = new int[length];
            int[] correctPowers = new int[length];
            for (int i = 0; i < length; i++) {
                correctCoeffs[i] = coeffs[i] * powers[i];
                correctPowers[i] = powers[i] - 1;
            }

            String correctText = "";
            for (int i = 0; i < length; i++) {
                if (length == 1 && correctCoeffs[0] == 0) {
                    correctText = "0";
                    break;
                }
                if (correctCoeffs[i] > 0 || length == 1) {
                    if (correctCoeffs[i] > 1 || correctPowers[i] == 0) {
                        correctText = correctText + correctCoeffs[i];
                    }
                    if (correctPowers[i] > 0) {
                        correctText = correctText + "x";
                        if (correctPowers[i] > 1) {
                            correctText = correctText + "^{" + correctPowers[i] + "}";
                        }
                    }
                    correctText = correctText + "+";
                }
            }
            while (correctText.charAt(correctText.length() - 1) == '+') {
                correctText = correctText.substring(0, correctText.length() - 1);
            }

            answers[correctAnswerIndex] = new AnswerChoice(correctText, true, answerX, answerY + correctAnswerIndex * (Media.defaultFontMedium.getHeight() + 10));
        }

        //Distractor answers
        {
            int[] integratedCoeffs = new int[length];
            int[] integratedPowers = new int[length];
            for (int i = 0; i < length; i++) {
                integratedPowers[i] = powers[i] + 1;
                integratedCoeffs[i] = coeffs[i] / integratedPowers[i];
            }
            String integratedText = "";
            for (int i = 0; i < length; i++) {
                if (integratedCoeffs[i] > 1 || integratedPowers[i] == 0) {
                    integratedText = integratedText + integratedCoeffs[i];
                }
                if (integratedPowers[i] > 0) {
                    integratedText = integratedText + "x";
                    if (integratedPowers[i] > 1) {
                        integratedText = integratedText + "^{" + integratedPowers[i] + "}";
                    }
                }
                if (i < length - 1) {
                    integratedText = integratedText + "+";
                }
            }

            int j = randomIntInRange(0, 2);
            answers[incorrectAnswerIndexes.get(j)] = new AnswerChoice(integratedText, false, answerX, answerY + incorrectAnswerIndexes.get(j) * (Media.defaultFontMedium.getHeight() + 10));
            incorrectAnswerIndexes.remove(j);
        }

        {
            int j = randomIntInRange(0, 1);
            answers[incorrectAnswerIndexes.get(j)] = new AnswerChoice(baseText, false, answerX, answerY + incorrectAnswerIndexes.get(j) * (Media.defaultFontMedium.getHeight() + 10));
            incorrectAnswerIndexes.remove(j);
        }

        {
            answers[incorrectAnswerIndexes.getFirst()] = new AnswerChoice("Does not exist", false, answerX, answerY + incorrectAnswerIndexes.getFirst() * (Media.defaultFontMedium.getHeight() + 10));
        }
    }

    public void generateDerivTrigQuestion() {
        //Question
        int outerCoeff = randomIntInRange(1, 9);
        int innerCoeff = randomIntInRange(1, 9);
        int functionType = randomIntInRange(0, 5);

        String baseText = "";
        if (outerCoeff > 1) {
            baseText = baseText + outerCoeff;
        }
        baseText = baseText + switch (functionType) {
            case 0 -> "sin(";
            case 1 -> "cos(";
            case 2 -> "tan(";
            case 3 -> "csc(";
            case 4 -> "sec(";
            default -> "cot(";
        };
        if (innerCoeff > 1) {
            baseText = baseText + innerCoeff;
        }
        baseText = baseText + "x)";
        questionText = new MathString("What is the derivative of " + baseText + "?");

        //Correct answer
        {
            int correctOuter = outerCoeff * innerCoeff;
            int correctInner = innerCoeff;

            String correctText = "";
            if (functionType == 1 || functionType == 3 || functionType == 5) {
                correctText = correctText + "-";
            }
            if (correctOuter > 1) {
                correctText = correctText + correctOuter;
            }
            correctText = correctText + switch (functionType) {
                case 0 -> "cos(";
                case 1 -> "sin(";
                case 2 -> "sec^{2}(";
                case 3 -> "csc(";
                case 4 -> "sec(";
                default -> "csc^{2}(";
            };
            if (correctInner > 1) {
                correctText = correctText + correctInner;
            }
            correctText = correctText + "x)";
            if (functionType == 3) {
                correctText = correctText + "cot(";
                if (correctInner > 1) {
                    correctText = correctText + correctInner;
                }
                correctText = correctText + "x)";
            }
            if (functionType == 4) {
                correctText = correctText + "tan(";
                if (correctInner > 1) {
                    correctText = correctText + correctInner;
                }
                correctText = correctText + "x)";
            }

            answers[correctAnswerIndex] = new AnswerChoice(correctText, true, answerX, answerY + correctAnswerIndex * (Media.defaultFontMedium.getHeight() + 10));
        }

        //Distractor answers
        {
            int negativeOuter = outerCoeff * innerCoeff;
            int negativeInner = innerCoeff;

            String negativeText = "";
            if (functionType == 0 || functionType == 2 || functionType == 4) {
                negativeText = negativeText + "-";
            }
            if (negativeOuter > 1) {
                negativeText = negativeText + negativeOuter;
            }
            negativeText = negativeText + switch (functionType) {
                case 0 -> "cos(";
                case 1 -> "sin(";
                case 2 -> "sec^{2}(";
                case 3 -> "csc(";
                case 4 -> "sec(";
                default -> "csc^{2}(";
            };
            if (negativeInner > 1) {
                negativeText = negativeText + negativeInner;
            }
            negativeText = negativeText + "x)";
            if (functionType == 3) {
                negativeText = negativeText + "cot(";
                if (negativeInner > 1) {
                    negativeText = negativeText + negativeInner;
                }
                negativeText = negativeText + "x)";
            }
            if (functionType == 4) {
                negativeText = negativeText + "tan(";
                if (negativeInner > 1) {
                    negativeText = negativeText + negativeInner;
                }
                negativeText = negativeText + "x)";
            }

            int j = randomIntInRange(0, 2);
            answers[incorrectAnswerIndexes.get(j)] = new AnswerChoice(negativeText, false, answerX, answerY + incorrectAnswerIndexes.get(j) * (Media.defaultFontMedium.getHeight() + 10));
            incorrectAnswerIndexes.remove(j);
        }

        {
            int wrongFunctionOuter = outerCoeff * innerCoeff;
            int wrongFunctionInner = innerCoeff;

            String wrongFunctionText = "";
            if (functionType == 1 || functionType == 3 || functionType == 5) {
                wrongFunctionText = wrongFunctionText + "-";
            }
            if (wrongFunctionOuter > 1) {
                wrongFunctionText = wrongFunctionText + wrongFunctionOuter;
            }
            wrongFunctionText = wrongFunctionText + switch (functionType) {
                case 0 -> "sec^{2}(";
                case 1 -> "csc^{2}(";
                case 2 -> "sec(";
                case 3 -> "cos(";
                case 4 -> "sin(";
                default -> "csc(";
            };
            if (wrongFunctionInner > 1) {
                wrongFunctionText = wrongFunctionText + wrongFunctionInner;
            }
            wrongFunctionText = wrongFunctionText + "x)";
            if (functionType == 5) {
                wrongFunctionText = wrongFunctionText + "cot(";
                if (wrongFunctionInner > 1) {
                    wrongFunctionText = wrongFunctionText + wrongFunctionInner;
                }
                wrongFunctionText = wrongFunctionText + "x)";
            }
            if (functionType == 2) {
                wrongFunctionText = wrongFunctionText + "tan(";
                if (wrongFunctionInner > 1) {
                    wrongFunctionText = wrongFunctionText + wrongFunctionInner;
                }
                wrongFunctionText = wrongFunctionText + "x)";
            }

            int j = randomIntInRange(0, 1);
            answers[incorrectAnswerIndexes.get(j)] = new AnswerChoice(wrongFunctionText, false, answerX, answerY + incorrectAnswerIndexes.get(j) * (Media.defaultFontMedium.getHeight() + 10));
            incorrectAnswerIndexes.remove(j);
        }

        {
            int negativeWrongFunctionOuter = outerCoeff * innerCoeff;
            int negativeWrongFunctionInner = innerCoeff;

            String negativeWrongFunctionText = "";
            if (functionType == 0 || functionType == 2 || functionType == 4) {
                negativeWrongFunctionText = negativeWrongFunctionText + "-";
            }
            if (negativeWrongFunctionOuter > 1) {
                negativeWrongFunctionText = negativeWrongFunctionText + negativeWrongFunctionOuter;
            }
            negativeWrongFunctionText = negativeWrongFunctionText + switch (functionType) {
                case 0 -> "sec^{2}(";
                case 1 -> "csc^{2}(";
                case 2 -> "sec(";
                case 3 -> "cos(";
                case 4 -> "sin(";
                default -> "csc(";
            };
            if (negativeWrongFunctionInner > 1) {
                negativeWrongFunctionText = negativeWrongFunctionText + negativeWrongFunctionInner;
            }
            negativeWrongFunctionText = negativeWrongFunctionText + "x)";
            if (functionType == 5) {
                negativeWrongFunctionText = negativeWrongFunctionText + "cot(";
                if (negativeWrongFunctionInner > 1) {
                    negativeWrongFunctionText = negativeWrongFunctionText + negativeWrongFunctionInner;
                }
                negativeWrongFunctionText = negativeWrongFunctionText + "x)";
            }
            if (functionType == 2) {
                negativeWrongFunctionText = negativeWrongFunctionText + "tan(";
                if (negativeWrongFunctionInner > 1) {
                    negativeWrongFunctionText = negativeWrongFunctionText + negativeWrongFunctionInner;
                }
                negativeWrongFunctionText = negativeWrongFunctionText + "x)";
            }

            answers[incorrectAnswerIndexes.getFirst()] = new AnswerChoice(negativeWrongFunctionText, false, answerX, answerY + incorrectAnswerIndexes.getFirst() * (Media.defaultFontMedium.getHeight() + 10));
        }
    }

    public void generateDerivExponentialQuestion() {
        //Question
        int outerCoeff = randomIntInRange(1, 9);
        int innerCoeff = randomIntInRange(2, 9);

        String baseText = "";
        if (outerCoeff > 1) {
            baseText = baseText + outerCoeff;
        }
        baseText = baseText + "e^{" + innerCoeff + "x}";
        questionText = new MathString("What is the derivative of " + baseText + "?");

        //Correct answer
        {
            int correctOuter = outerCoeff * innerCoeff;
            int correctInner = innerCoeff;

            String correctText = correctOuter + "e^{" + correctInner + "x}";

            answers[correctAnswerIndex] = new AnswerChoice(correctText, true, answerX, answerY + correctAnswerIndex * (Media.defaultFontMedium.getHeight() + 10));
        }

        //Distractor answers
        {
            int integratedOuter = outerCoeff / innerCoeff;
            int integratedInner = innerCoeff;

            String integratedText = "";
            if (integratedOuter > 1) {
                integratedText = integratedText + integratedOuter;
            }
            integratedText = integratedText + "e^{" + integratedInner + "x}";

            int j = randomIntInRange(0, 2);
            answers[incorrectAnswerIndexes.get(j)] = new AnswerChoice(integratedText, false, answerX, answerY + incorrectAnswerIndexes.get(j) * (Media.defaultFontMedium.getHeight() + 10));
            incorrectAnswerIndexes.remove(j);
        }

        {
            int wrongCoeffsOuter = outerCoeff * innerCoeff;
            int wrongCoeffsInner = innerCoeff - 1;

            String wrongCoeffsText = wrongCoeffsOuter + "e^{";
            if (wrongCoeffsInner > 1) {
                wrongCoeffsText = wrongCoeffsText + wrongCoeffsInner;
            }
            wrongCoeffsText = wrongCoeffsText + "x}";

            int j = randomIntInRange(0, 1);
            answers[incorrectAnswerIndexes.get(j)] = new AnswerChoice(wrongCoeffsText, false, answerX, answerY + incorrectAnswerIndexes.get(j) * (Media.defaultFontMedium.getHeight() + 10));
            incorrectAnswerIndexes.remove(j);
        }

        {
            int wrongCoeffsIntegratedOuter = outerCoeff / (innerCoeff + 1);
            int wrongCoeffsIntegratedInner = innerCoeff + 1;

            String wrongCoeffsIntegratedText = "";
            if (wrongCoeffsIntegratedOuter > 1) {
                wrongCoeffsIntegratedText = wrongCoeffsIntegratedText + wrongCoeffsIntegratedOuter;
            }
            wrongCoeffsIntegratedText = wrongCoeffsIntegratedText + "e^{" + wrongCoeffsIntegratedInner + "x}";

            answers[incorrectAnswerIndexes.getFirst()] = new AnswerChoice(wrongCoeffsIntegratedText, false, answerX, answerY + incorrectAnswerIndexes.getFirst() * (Media.defaultFontMedium.getHeight() + 10));
        }
    }

    public void generateIntegralPowerQuestion() {
        answers[correctAnswerIndex] = new AnswerChoice("Placeholder correct answer", true, answerX, answerY + correctAnswerIndex * (Media.defaultFontMedium.getHeight() + 10));
        for (int i = 0; i < answers.length; i++) {
            if (i != correctAnswerIndex) {
                answers[i] = new AnswerChoice("Placeholder incorrect answer", false, answerX, answerY + i * (Media.defaultFontMedium.getHeight() + 10));
            }
        }

        //Question
        int length = randomIntInRange(1, 3);

        int[] powers = new int[length];
        for (int i = 0; i < length; i++) {
            powers[i] = randomIntInRange(0, 5);
        }

        int[] coeffs = new int[length];
        for (int i = 0; i < length; i++) {
            coeffs[i] = randomIntInRange(1, 5) * (powers[i] + 1);
        }

        String baseText = "";
        for (int i = 0; i < length; i++) {
            if (coeffs[i] > 1 || powers[i] == 0) {
                baseText = baseText + coeffs[i];
            }
            if (powers[i] > 0) {
                baseText = baseText + "x";
                if (powers[i] > 1) {
                    baseText = baseText + "^{" + powers[i] + "}";
                }
            }
            if (i < length - 1) {
                baseText = baseText + "+";
            }
        }
        questionText = new MathString("What is the indefinite integral of " + baseText + "?");

        //Correcct answer
        {
            int[] correctCoeffs = new int[length];
            int[] correctPowers = new int[length];
            for (int i = 0; i < length; i++) {
                correctPowers[i] = powers[i] + 1;
                correctCoeffs[i] = coeffs[i] / correctPowers[i];
            }

            String correctText = "";
            for (int i = 0; i < length; i++) {
                if (length == 1 && correctCoeffs[0] == 0) {
                    correctText = "0";
                    break;
                }
                if (correctCoeffs[i] > 0 || length == 1) {
                    if (correctCoeffs[i] > 1 || correctPowers[i] == 0) {
                        correctText = correctText + correctCoeffs[i];
                    }
                    if (correctPowers[i] > 0) {
                        correctText = correctText + "x";
                        if (correctPowers[i] > 1) {
                            correctText = correctText + "^{" + correctPowers[i] + "}";
                        }
                    }
                    correctText = correctText + "+";
                }
            }
            correctText = correctText + "C";

            answers[correctAnswerIndex] = new AnswerChoice(correctText, true, answerX, answerY + correctAnswerIndex * (Media.defaultFontMedium.getHeight() + 10));
        }

        //Distractor answers
        {
            int[] noCCoeffs = new int[length];
            int[] noCPowers = new int[length];
            for (int i = 0; i < length; i++) {
                noCPowers[i] = powers[i] + 1;
                noCCoeffs[i] = coeffs[i] / noCPowers[i];
            }

            String noCText = "";
            for (int i = 0; i < length; i++) {
                if (length == 1 && noCCoeffs[0] == 0) {
                    noCText = "0";
                    break;
                }
                if (noCCoeffs[i] > 0 || length == 1) {
                    if (noCCoeffs[i] > 1 || noCPowers[i] == 0) {
                        noCText = noCText + noCCoeffs[i];
                    }
                    if (noCPowers[i] > 0) {
                        noCText = noCText + "x";
                        if (noCPowers[i] > 1) {
                            noCText = noCText + "^{" + noCPowers[i] + "}";
                        }
                    }
                    noCText = noCText + "+";
                }
            }
            while (noCText.charAt(noCText.length() - 1) == '+') {
                noCText = noCText.substring(0, noCText.length() - 1);
            }

            int j = randomIntInRange(0, 2);
            answers[incorrectAnswerIndexes.get(j)] = new AnswerChoice(noCText, false, answerX, answerY + incorrectAnswerIndexes.get(j) * (Media.defaultFontMedium.getHeight() + 10));
            incorrectAnswerIndexes.remove(j);
        }

        {
            int[] differentiatedCoeffs = new int[length];
            int[] differentiatedPowers = new int[length];
            for (int i = 0; i < length; i++) {
                differentiatedCoeffs[i] = coeffs[i] * powers[i];
                differentiatedPowers[i] = powers[i] - 1;
            }

            String differentiatedText = "";
            for (int i = 0; i < length; i++) {
                if (length == 1 && differentiatedCoeffs[0] == 0) {
                    differentiatedText = "0";
                    break;
                }
                if (differentiatedCoeffs[i] > 0 || length == 1) {
                    if (differentiatedCoeffs[i] > 1 || differentiatedPowers[i] == 0) {
                        differentiatedText = differentiatedText + differentiatedCoeffs[i];
                    }
                    if (differentiatedPowers[i] > 0) {
                        differentiatedText = differentiatedText + "x";
                        if (differentiatedPowers[i] > 1) {
                            differentiatedText = differentiatedText + "^{" + differentiatedPowers[i] + "}";
                        }
                    }
                    differentiatedText = differentiatedText + "+";
                }
            }
            while (differentiatedText.charAt(differentiatedText.length() - 1) == '+') {
                differentiatedText = differentiatedText.substring(0, differentiatedText.length() - 1);
            }

            int j = randomIntInRange(0, 1);
            answers[incorrectAnswerIndexes.get(j)] = new AnswerChoice(differentiatedText, false, answerX, answerY + incorrectAnswerIndexes.get(j) * (Media.defaultFontMedium.getHeight() + 10));
            incorrectAnswerIndexes.remove(j);
        }

        {
            int[] differentiatedWithCCoeffs = new int[length];
            int[] differentiatedWithCPowers = new int[length];
            for (int i = 0; i < length; i++) {
                differentiatedWithCCoeffs[i] = coeffs[i] * powers[i];
                differentiatedWithCPowers[i] = powers[i] - 1;
            }

            String differentiatedWithCText = "";
            for (int i = 0; i < length; i++) {
                if (length == 1 && differentiatedWithCCoeffs[0] == 0) {
                    differentiatedWithCText = "0";
                    break;
                }
                if (differentiatedWithCCoeffs[i] > 0 || length == 1) {
                    if (differentiatedWithCCoeffs[i] > 1 || differentiatedWithCPowers[i] == 0) {
                        differentiatedWithCText = differentiatedWithCText + differentiatedWithCCoeffs[i];
                    }
                    if (differentiatedWithCPowers[i] > 0) {
                        differentiatedWithCText = differentiatedWithCText + "x";
                        if (differentiatedWithCPowers[i] > 1) {
                            differentiatedWithCText = differentiatedWithCText + "^{" + differentiatedWithCPowers[i] + "}";
                        }
                    }
                    differentiatedWithCText = differentiatedWithCText + "+";
                }
            }
            differentiatedWithCText = differentiatedWithCText + "C";

            answers[incorrectAnswerIndexes.getFirst()] = new AnswerChoice(differentiatedWithCText, false, answerX, answerY + incorrectAnswerIndexes.getFirst() * (Media.defaultFontMedium.getHeight() + 10));
        }
    }

    public void generateIntegralTrigQuestion() {
        answers[correctAnswerIndex] = new AnswerChoice("Placeholder correct answer", true, answerX, answerY + correctAnswerIndex * (Media.defaultFontMedium.getHeight() + 10));
        for (int i = 0; i < answers.length; i++) {
            if (i != correctAnswerIndex) {
                answers[i] = new AnswerChoice("Placeholder incorrect answer", false, answerX, answerY + i * (Media.defaultFontMedium.getHeight() + 10));
            }
        }
    }

    public void generateIntegralExponentialQuestion() {
        //Question
        int innerCoeff = randomIntInRange(2, 5);
        int outerCoeff = randomIntInRange(1, 5) * innerCoeff; //guarantees divisible

        String baseText = outerCoeff + "e^{" + innerCoeff + "x}";
        questionText = new MathString("What is the indefinite integral of " + baseText + "?");

        //Correct answer
        {
            int correctOuter = outerCoeff / innerCoeff;
            int correctInner = innerCoeff;

            String correctText = "";
            if (correctOuter > 1) {
                correctText = correctText + correctOuter;
            }
            correctText = correctText + "e^{" + correctInner + "x}+C";

            answers[correctAnswerIndex] = new AnswerChoice(correctText, true, answerX, answerY + correctAnswerIndex * (Media.defaultFontMedium.getHeight() + 10));
        }

        //Distractor answers
        {
            int differentiatedOuter = outerCoeff * innerCoeff;
            int differentiatedInner = innerCoeff;

            String differentiatedText = differentiatedOuter + "e^{" + differentiatedInner + "x}";

            int j = randomIntInRange(0, 2);
            answers[incorrectAnswerIndexes.get(j)] = new AnswerChoice(differentiatedText, false, answerX, answerY + incorrectAnswerIndexes.get(j) * (Media.defaultFontMedium.getHeight() + 10));
            incorrectAnswerIndexes.remove(j);
        }

        {
            int noCOuter = outerCoeff / innerCoeff;
            int noCInner = innerCoeff;

            String noCText = "";
            if (noCOuter > 1) {
                noCText = noCText + noCOuter;
            }
            noCText = noCText + "e^{" + noCInner + "x}";

            int j = randomIntInRange(0, 1);
            answers[incorrectAnswerIndexes.get(j)] = new AnswerChoice(noCText, false, answerX, answerY + incorrectAnswerIndexes.get(j) * (Media.defaultFontMedium.getHeight() + 10));
            incorrectAnswerIndexes.remove(j);
        }

        {
            int differentiatedWithCOuter = outerCoeff * innerCoeff;
            int differentiatedWithCInner = innerCoeff;

            String differentiatedWithCText = differentiatedWithCOuter + "e^{" + differentiatedWithCInner + "x}+C";

            answers[incorrectAnswerIndexes.getFirst()] = new AnswerChoice(differentiatedWithCText, false, answerX, answerY + incorrectAnswerIndexes.getFirst() * (Media.defaultFontMedium.getHeight() + 10));
        }
    }

    public AnswerChoice getSelectedAnswer() {
        return selectedAnswer;
    }

    public boolean correctAnswerSubmitted() {
        return correctAnswerSubmitted;
    }

    public boolean incorrectAnswerSubmitted() {
        return incorrectAnswerSubmitted;
    }
}
