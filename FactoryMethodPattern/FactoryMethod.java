import java.util.Random;

// Creator Classes ================================================================================

// This class presents questions for a math quiz
abstract class MathQuiz {

    // By using the createQuestion factory method, this method is decoupled from the actual question
    // class it is displaying. It will display any object which extends the ArithmeticQuestion class.
    // Subclasses can easily vary the questions displayed in the quiz by implementing a factory method
    // which instantiates the required question objects.
    public final void  displayQuestions(int numOfQuestions) {
        for(int i = 0; i < numOfQuestions; i++) {
            ArithmeticQuestion q = createQuestion();
            displayQuestion(q);
        }
    }

    private void displayQuestion(ArithmeticQuestion q) {
        int firstOperand = q.getFirstOperand();
        int secondOperand = q.getSecondOperand();
        String operator = q.getOperator();

        System.out.format("  %4d%n", firstOperand);
        System.out.format("%s %4d%n", operator, secondOperand);
        int lineLength = 6;
        for(int i = 0; i < lineLength; i++) {
            System.out.print("-");
        }

        System.out.println();
        System.out.println();
    }

    // This is the factory method which subclasses must implement with their own question implemnetation
    public abstract ArithmeticQuestion createQuestion();
}


// Implements a factory method which only produces addition questions
class AdditionQuiz extends MathQuiz {
    public ArithmeticQuestion createQuestion() {
        return new AdditionQuestion();
    }
}


// Implements a factory method which only produces subtraction questions
class SubtractionQuiz extends MathQuiz {
    public ArithmeticQuestion createQuestion() {
        return new SubtractionQuesiton();
    }
}

// Implements a factory method which can produce both addition and subtraction questions
class AdditionAndSubtractionQuiz extends MathQuiz {
    public ArithmeticQuestion createQuestion() {
        Random random = new Random();
        if (random.nextInt(2) == 0) {
            return new AdditionQuestion();
        } else {
            return new SubtractionQuesiton();
        }
    }
}


// Product classes ================================================================================

// Objects created by the factory method should extend this class
abstract class ArithmeticQuestion {
    int operandOne;
    int operandTwo;
    String operator;

    public int getFirstOperand() {
        return operandOne;
    }

    public int getSecondOperand() {
        return operandTwo;
    }

    public String getOperator() {
        return operator;
    }
}

// An addition question with operands up to 4 digits long
class AdditionQuestion extends ArithmeticQuestion {
    public AdditionQuestion() {
        Random random = new Random();
        super.operandOne = random.nextInt(9998) + 1;
        super.operandTwo = random.nextInt(9998) + 1;
        super.operator = "+";
    }
}

// A subtraction question with operands up to 4 digits long
// The first operand will always be greater than the second
class SubtractionQuesiton extends ArithmeticQuestion {
    public SubtractionQuesiton() {
        Random random = new Random();
        super.operandOne = random.nextInt(9998) + 1;
        super.operandTwo = random.nextInt(super.operandOne) + 1;
        super.operator = "-";
    }
}


class FactoryPattern {
    public static void main(String[] args) {
        MathQuiz additionQuiz = new AdditionQuiz();
        MathQuiz subtractionQuiz = new SubtractionQuiz();
        MathQuiz combinedQuiz = new AdditionAndSubtractionQuiz();

        System.out.println("Addition Quiz =========================");
        additionQuiz.displayQuestions(5);
        System.out.println("Subtraction Quiz ======================");
        subtractionQuiz.displayQuestions(5);
        System.out.println("Combined Quiz =========================");
        combinedQuiz.displayQuestions(5);
    }

    /* Sample execution:

    $ java FactoryPattern
    Addition Quiz =========================
      4480
    + 7639
    ------

       695
    + 1778
    ------

      8463
    + 3275
    ------

      7004
    + 5695
    ------

      8069
    + 9862
    ------

    Subtraction Quiz ======================
      4302
    - 2494
    ------

      2343
    - 2133
    ------

      2053
    - 1113
    ------

      5422
    -  438
    ------

      9055
    - 4056
    ------

    Combined Quiz =========================
      8156
    - 2503
    ------

      8673
    - 4760
    ------

      5593
    -  259
    ------

      1724
    + 1635
    ------

      2965
    +  868
    ------
    */
}
