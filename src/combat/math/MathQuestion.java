package combat.math;

import button.AnswerChoiceButton;
import button.SubmitAnswerButton;
import core.Media;
import core.Values;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.text.DecimalFormat;
import java.util.List;
import java.util.ArrayList;

import static core.Utils.randomIntInRange;
import static core.Main.getScreenWidth;
import static core.Main.getScreenHeight;

public class MathQuestion implements Values {
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

    private static boolean easyMode;

    public MathQuestion() {
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

        if (easyMode) {
            switch (randomIntInRange(0, 2)) {
                case 0 -> generateLinearSolutionQuestion();
                case 1 -> generateLinearSlopeQuestion();
                default -> generateLinearFunctionValueQuestion();
            }
        }
        else {
            switch (randomIntInRange(0, 5)) {
                case 0 -> generateDerivPowerQuestion();
                case 1 -> generateDerivTrigQuestion();
                case 2 -> generateDerivExponentialQuestion();
                case 3 -> generateIntegralPowerQuestion();
                case 4 -> generateIntegralTrigQuestion();
                default -> generateIntegralExponentialQuestion();
            }
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
            while (!correctText.isEmpty() && correctText.charAt(correctText.length() - 1) == '+') {
                correctText = correctText.substring(0, correctText.length() - 1);
            }
            if (correctText.isEmpty()) {
                correctText = "0";
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

    public void generateDerivLnQuestion() {
        answers[correctAnswerIndex] = new AnswerChoice("Placeholder correct answer", true, answerX, answerY + correctAnswerIndex * (Media.defaultFontMedium.getHeight() + 10));
        for (int i = 0; i < answers.length; i++) {
            if (i != correctAnswerIndex) {
                answers[i] = new AnswerChoice("Placeholder incorrect answer", false, answerX, answerY + i * (Media.defaultFontMedium.getHeight() + 10));
            }
        }
    }

    public void generateIntegralPowerQuestion() {
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
            while (!noCText.isEmpty() && noCText.charAt(noCText.length() - 1) == '+') {
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
            while (!differentiatedText.isEmpty() && differentiatedText.charAt(differentiatedText.length() - 1) == '+') {
                differentiatedText = differentiatedText.substring(0, differentiatedText.length() - 1);
            }
            if (differentiatedText.isEmpty()) {
                differentiatedText = "0";
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
        //Question
        int innerCoeff = randomIntInRange(2, 9);
        int outerCoeff = randomIntInRange(1, 5) * innerCoeff;
        int functionType = randomIntInRange(0, 5);

        String baseText = "" + outerCoeff;
        baseText = baseText + switch (functionType) {
            case 0 -> "cos(";
            case 1 -> "sin(";
            case 2 -> "sec^{2}(";
            case 3 -> "csc(";
            case 4 -> "sec(";
            default -> "csc^{2}(";
        };
        baseText = baseText + innerCoeff + "x)";
        if (functionType == 3) {
            baseText = baseText + "cot(" + innerCoeff + "x)";
        }
        if (functionType == 4) {
            baseText = baseText + "tan(" + innerCoeff + "x)";
        }

        questionText = new MathString("What is the indefinite integral of " + baseText + "?");

        //Correct answer
        {
            int correctInner = innerCoeff;
            int correctOuter = outerCoeff / correctInner;

            String correctText = "";
            if (functionType == 1 || functionType == 3 || functionType == 5) {
                correctText = correctText + "-";
            }
            if (correctOuter > 1) {
                correctText = correctText + correctOuter;
            }
            correctText = correctText + switch (functionType) {
                case 0 -> "sin(";
                case 1 -> "cos(";
                case 2 -> "tan(";
                case 3 -> "csc(";
                case 4 -> "sec(";
                default -> "cot(";
            };
            correctText = correctText + correctInner + "x)+C";

            answers[correctAnswerIndex] = new AnswerChoice(correctText, true, answerX, answerY + correctAnswerIndex * (Media.defaultFontMedium.getHeight() + 10));
        }

        //Distractor answers
        {
            int negativeInner = innerCoeff;
            int negativeOuter = outerCoeff / negativeInner;

            String negativeText = "";
            if (functionType == 0 || functionType == 2 || functionType == 4) {
                negativeText = negativeText + "-";
            }
            if (negativeOuter > 1) {
                negativeText = negativeText + negativeOuter;
            }
            negativeText = negativeText + switch (functionType) {
                case 0 -> "sin(";
                case 1 -> "cos(";
                case 2 -> "tan(";
                case 3 -> "csc(";
                case 4 -> "sec(";
                default -> "cot(";
            };
            negativeText = negativeText + negativeInner + "x)+C";

            int j = randomIntInRange(0, 2);
            answers[incorrectAnswerIndexes.get(j)] = new AnswerChoice(negativeText, false, answerX, answerY + incorrectAnswerIndexes.get(j) * (Media.defaultFontMedium.getHeight() + 10));
            incorrectAnswerIndexes.remove(j);
        }

        {
            int differentiatedOuter = outerCoeff * innerCoeff;
            int differentiatedInner = innerCoeff;

            String differentiatedWithCText = "";
            if (functionType == 0 || functionType == 3 || functionType == 5) {
                differentiatedWithCText = differentiatedWithCText + "-";
            }
            if (functionType == 2 || functionType == 5) {
                differentiatedOuter *= 2;
            }
            differentiatedWithCText = differentiatedWithCText + differentiatedOuter;
            differentiatedWithCText = differentiatedWithCText + switch (functionType) {
                case 0 -> "sin(";
                case 1 -> "cos(";
                case 2 -> "sec^{2}("; //sec^{2}(x) -> 2sec(x)sec(x)tan(x)
                case 3 -> "[csc^{3}("; //csc(x)cot(x) -> -csc(x)csc^{2}(x)-cot(x)csc(x)cot(x)
                case 4 -> "[sec^{3}("; //sec(x)tan(x) -> sec(x)sec^{2}(x)+tan(x)sec(x)tan(x)
                default -> "csc^{2}("; //csc^{2}(x) -> -2csc(x)csc(x)cot(x)
            };
            differentiatedWithCText = differentiatedWithCText + differentiatedInner + "x)";
            if (functionType == 2) {
                differentiatedWithCText = differentiatedWithCText + "tan(" + differentiatedInner + "x)";
            }
            if (functionType == 5) {
                differentiatedWithCText = differentiatedWithCText + "cot(" + differentiatedInner + "x)";
            }
            if (functionType == 3) {
                differentiatedWithCText = differentiatedWithCText + "+csc(" + differentiatedInner + "x)cot^{2}(" + differentiatedInner + "x)]";
            }
            if (functionType == 4) {
                differentiatedWithCText = differentiatedWithCText + "+sec(" + differentiatedInner + "x)tan^{2}(" + differentiatedInner + "x)]";
            }
            differentiatedWithCText = differentiatedWithCText + "+C";

            int j = randomIntInRange(0, 1);
            answers[incorrectAnswerIndexes.get(j)] = new AnswerChoice(differentiatedWithCText, false, answerX, answerY + incorrectAnswerIndexes.get(j) * (Media.defaultFontMedium.getHeight() + 10));
            incorrectAnswerIndexes.remove(j);
        }

        {
            int negativeDifferentiatedOuter = outerCoeff * innerCoeff;
            int negativeDifferentiatedInner = innerCoeff;

            String negativeDifferentiatedWithCText = "";
            if (functionType == 1 || functionType == 2 || functionType == 4) {
                negativeDifferentiatedWithCText = negativeDifferentiatedWithCText + "-";
            }
            if (functionType == 2 || functionType == 5) {
                negativeDifferentiatedOuter *= 2;
            }
            negativeDifferentiatedWithCText = negativeDifferentiatedWithCText + negativeDifferentiatedOuter;
            negativeDifferentiatedWithCText = negativeDifferentiatedWithCText + switch (functionType) {
                case 0 -> "sin(";
                case 1 -> "cos(";
                case 2 -> "sec^{2}("; //sec^{2}(x) -> 2sec(x)sec(x)tan(x)
                case 3 -> "[csc^{3}("; //csc(x)cot(x) -> -csc(x)csc^{2}(x)-cot(x)csc(x)cot(x)
                case 4 -> "[sec^{3}("; //sec(x)tan(x) -> sec(x)sec^{2}(x)+tan(x)sec(x)tan(x)
                default -> "csc^{2}("; //csc^{2}(x) -> -2csc(x)csc(x)cot(x)
            };
            negativeDifferentiatedWithCText = negativeDifferentiatedWithCText + negativeDifferentiatedInner + "x)";
            if (functionType == 2) {
                negativeDifferentiatedWithCText = negativeDifferentiatedWithCText + "tan(" + negativeDifferentiatedInner + "x)";
            }
            if (functionType == 5) {
                negativeDifferentiatedWithCText = negativeDifferentiatedWithCText + "cot(" + negativeDifferentiatedInner + "x)";
            }
            if (functionType == 3) {
                negativeDifferentiatedWithCText = negativeDifferentiatedWithCText + "+csc(" + negativeDifferentiatedInner + "x)cot^{2}(" + negativeDifferentiatedInner + "x)]";
            }
            if (functionType == 4) {
                negativeDifferentiatedWithCText = negativeDifferentiatedWithCText + "+sec(" + negativeDifferentiatedInner + "x)tan^{2}(" + negativeDifferentiatedInner + "x)]";
            }
            negativeDifferentiatedWithCText = negativeDifferentiatedWithCText + "+C";

            answers[incorrectAnswerIndexes.getFirst()] = new AnswerChoice(negativeDifferentiatedWithCText, false, answerX, answerY + incorrectAnswerIndexes.getFirst() * (Media.defaultFontMedium.getHeight() + 10));
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

    public void generateIntegralLnQuestion() {
        answers[correctAnswerIndex] = new AnswerChoice("Placeholder correct answer", true, answerX, answerY + correctAnswerIndex * (Media.defaultFontMedium.getHeight() + 10));
        for (int i = 0; i < answers.length; i++) {
            if (i != correctAnswerIndex) {
                answers[i] = new AnswerChoice("Placeholder incorrect answer", false, answerX, answerY + i * (Media.defaultFontMedium.getHeight() + 10));
            }
        }
    }

    public void generateLinearSolutionQuestion() {
        //Question
        int slope = randomIntInRange(2, 9);
        if (Math.random() < 0.5) {
            slope *= -1;
        }
        int intercept = randomIntInRange(1, 9) * slope;
        if (Math.random() < 0.5) {
            intercept *= -1;
        }

        String baseText = "y=";
        if (slope < 0) {
            baseText = baseText + "-";
        }
        baseText = baseText + Math.abs(slope) + "x";
        if (intercept < 0) {
            baseText = baseText + "-";
        }
        else {
            baseText = baseText + "+";
        }
        baseText = baseText + Math.abs(intercept);

        questionText = new MathString("Solve for the x-intercept of " + baseText + ".");

        //Correct answer
        {
            int correctSolution = -intercept / slope;

            String correctText = "" + correctSolution;

            answers[correctAnswerIndex] = new AnswerChoice(correctText, true, answerX, answerY + correctAnswerIndex * (Media.defaultFontMedium.getHeight() + 10));
        }

        //Distractor answers
        {
            int negativeSolution = intercept / slope;

            String negativeText = "" + negativeSolution;

            int j = randomIntInRange(0, 2);
            answers[incorrectAnswerIndexes.get(j)] = new AnswerChoice(negativeText, false, answerX, answerY + incorrectAnswerIndexes.get(j) * (Media.defaultFontMedium.getHeight() + 10));
            incorrectAnswerIndexes.remove(j);
        }

        {
            int yIntSolution = intercept;

            String yIntText = "" + yIntSolution;

            int j = randomIntInRange(0, 1);
            answers[incorrectAnswerIndexes.get(j)] = new AnswerChoice(yIntText, false, answerX, answerY + incorrectAnswerIndexes.get(j) * (Media.defaultFontMedium.getHeight() + 10));
            incorrectAnswerIndexes.remove(j);
        }

        {
            int negativeYIntSolution = -intercept;

            String negativeYIntText = "" + negativeYIntSolution;

            answers[incorrectAnswerIndexes.getFirst()] = new AnswerChoice(negativeYIntText, false, answerX, answerY + incorrectAnswerIndexes.getFirst() * (Media.defaultFontMedium.getHeight() + 10));
        }
    }

    public void generateLinearSlopeQuestion() {
        //Question
        int x2 = randomIntInRange(-9, 9);
        int x1 = randomIntInRange(-9, 9);
        while (x1 == x2) {
            x1 = randomIntInRange(-9, 9);
        }
        int dx = x2 - x1;

        int dy = dx * randomIntInRange(2, 9); // |slope| = randomIntInRange(2, 9)
        if (Math.random() < 0.5) { //slope = (+/-) randomIntInRange(2, 9)
            dy *= -1;
        }
        int y2 = randomIntInRange(-9, 9);
        int y1 = y2 - dy; //dy = y2 - y1

        String point1 = "(" + x1 + ", " + y1 + ")";
        String point2 = "(" + x2 + ", " + y2 + ")";

        questionText = new MathString("What is the slope of the line through " + point1 + " and " + point2 + "?");

        //Correct answer
        {
            int correctSlope = dy / dx;

            String correctText = "" + correctSlope;

            answers[correctAnswerIndex] = new AnswerChoice(correctText, true, answerX, answerY + correctAnswerIndex * (Media.defaultFontMedium.getHeight() + 10));
        }

        //Distractor answers
        {
            int negativeSlope = - dy / dx;

            String negativeText = "" + negativeSlope;

            int j = randomIntInRange(0, 2);
            answers[incorrectAnswerIndexes.get(j)] = new AnswerChoice(negativeText, false, answerX, answerY + incorrectAnswerIndexes.get(j) * (Media.defaultFontMedium.getHeight() + 10));
            incorrectAnswerIndexes.remove(j);
        }

        {
            DecimalFormat df = new DecimalFormat("0.###");

            double reciprocalSlope = (double) dx / dy;

            String reciprocalText = df.format(reciprocalSlope);

            int j = randomIntInRange(0, 1);
            answers[incorrectAnswerIndexes.get(j)] = new AnswerChoice(reciprocalText, false, answerX, answerY + incorrectAnswerIndexes.get(j) * (Media.defaultFontMedium.getHeight() + 10));
            incorrectAnswerIndexes.remove(j);
        }

        {
            DecimalFormat df = new DecimalFormat("0.###");

            double negativeReciprocalSlope = (double) (-dx) / dy;

            String negativeReciprocalText = df.format(negativeReciprocalSlope);

            answers[incorrectAnswerIndexes.getFirst()] = new AnswerChoice(negativeReciprocalText, false, answerX, answerY + incorrectAnswerIndexes.getFirst() * (Media.defaultFontMedium.getHeight() + 10));
        }
    }

    public void generateLinearFunctionValueQuestion() {
        answers[correctAnswerIndex] = new AnswerChoice("Placeholder correct answer", true, answerX, answerY + correctAnswerIndex * (Media.defaultFontMedium.getHeight() + 10));
        for (int i = 0; i < answers.length; i++) {
            if (i != correctAnswerIndex) {
                answers[i] = new AnswerChoice("Placeholder incorrect answer", false, answerX, answerY + i * (Media.defaultFontMedium.getHeight() + 10));
            }
        }

        //Question
        int slope = randomIntInRange(1, 9); //slope != 0 to avoid mx+b = -mx+b
        if (Math.random() < 0.5) {
            slope *= -1;
        }
        int intercept = randomIntInRange(1, 9); //intercept != 0 to avoid mx+b = mx-b
        if (Math.random() < 0.5) {
            intercept *= -1;
        }

        String baseText = "f(x)=";
        if (slope < 0) {
            baseText = baseText + "-";
        }
        if (Math.abs(slope) != 1) {
            baseText = baseText + Math.abs(slope);
        }
        baseText = baseText + "x";
        if (intercept != 0) {
            if (intercept < 0) {
                baseText = baseText + "-";
            }
            else {
                baseText = baseText + "+";
            }
            baseText = baseText + Math.abs(intercept);
        }

        int evaluationPoint = randomIntInRange(-9, 9);
        while (evaluationPoint == 0 || slope * evaluationPoint + intercept == 0) { //evaluationPoint != 0 to avoid mx+b = -mx+b; mx+b != 0 to avoid mx+b = -mx-b
            evaluationPoint = randomIntInRange(-9, 9);
        }
        String evaluationText = "f(" + evaluationPoint + ")";

        questionText = new MathString("If " + baseText + ", what is " + evaluationText + "?");

        //Correct answer
        int correctFunctionValue; //this question type seems a bit sketchy in terms of potential repeat answers, so all answers are declared outside of braces to be used in repeat checks for the other answers
        {
            correctFunctionValue = slope * evaluationPoint + intercept;

            String correctText = "" + correctFunctionValue;

            answers[correctAnswerIndex] = new AnswerChoice(correctText, true, answerX, answerY + correctAnswerIndex * (Media.defaultFontMedium.getHeight() + 10));
        }

        //Distractor answers
        int negativeSlopeFunctionValue;
        {
            negativeSlopeFunctionValue = -slope * evaluationPoint + intercept;
            while (negativeSlopeFunctionValue == correctFunctionValue) { //repeat answer check
                negativeSlopeFunctionValue = randomIntInRange(-90, 90);
            }

            String negativeSlopeText = "" + negativeSlopeFunctionValue;

            int j = randomIntInRange(0, 2);
            answers[incorrectAnswerIndexes.get(j)] = new AnswerChoice(negativeSlopeText, false, answerX, answerY + incorrectAnswerIndexes.get(j) * (Media.defaultFontMedium.getHeight() + 10));
            incorrectAnswerIndexes.remove(j);
        }

        int negativeInterceptFunctionValue;
        {
            negativeInterceptFunctionValue = slope * evaluationPoint - intercept;
            while (negativeInterceptFunctionValue == correctFunctionValue || negativeInterceptFunctionValue == negativeSlopeFunctionValue) {
                negativeInterceptFunctionValue = randomIntInRange(-90, 90);
            }

            String negativeInterceptText = "" + negativeInterceptFunctionValue;

            int j = randomIntInRange(0, 1);
            answers[incorrectAnswerIndexes.get(j)] = new AnswerChoice(negativeInterceptText, false, answerX, answerY + incorrectAnswerIndexes.get(j) * (Media.defaultFontMedium.getHeight() + 10));
            incorrectAnswerIndexes.remove(j);
        }

        int negativeFunctionValue;
        {
            negativeFunctionValue = -slope * evaluationPoint - intercept;
            while (negativeFunctionValue == correctFunctionValue || negativeFunctionValue == negativeSlopeFunctionValue || negativeFunctionValue == negativeInterceptFunctionValue) {
                negativeFunctionValue = randomIntInRange(-90, 90);
            }

            String negativeText = "" + negativeFunctionValue;

            answers[incorrectAnswerIndexes.getFirst()] = new AnswerChoice(negativeText, false, answerX, answerY + incorrectAnswerIndexes.getFirst() * (Media.defaultFontMedium.getHeight() + 10));
        }
    }

    public static void setEasyMode(boolean easyMode) {
        MathQuestion.easyMode = easyMode;
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

    public static boolean easyMode() {
        return easyMode;
    }
}
