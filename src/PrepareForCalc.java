import java.util.ArrayList;
import java.util.List;

class PrepareForCalc {
    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private static final char OPENING_BRACKETS = '(';
    private static final char CLOSING_BRACKETS = ')';

    private String expression;
    private int countBrackets = 0;
    private int priority;

    private List<Integer> indexOpeningBrackets = new ArrayList<>();
    private List<Integer> indexClosingBrackets = new ArrayList<>();
    private List<Character> orders = new ArrayList<>();
    private List<Integer> numbers = new ArrayList<>();

    PrepareForCalc(String expression) {

        this.expression = expression;
        runToPrepare(expression);

    }


    private void runToPrepare(String expression) {
        if (expression != null) {
            checkForBrackets(expression);
            checkForOrders(expression);
            prepareNumbers(expression);
        }

    }

    // Проверка на скобки
    private void checkForBrackets(String expression) {
        char symbol;

        for (int i = 0; i < expression.length(); i++) {
            symbol = expression.charAt(i);

            switch (symbol) {
                case OPENING_BRACKETS:
                    indexOpeningBrackets.add(i);
                    countBrackets++;
                    break;

                case CLOSING_BRACKETS:
                    indexClosingBrackets.add(i);
                    break;
            }

        }

    }

    // Проверка на арифметические операторы и добавление очередности операций
    private void checkForOrders(String expression) {
        priority = countBrackets + 1;
        char symbol;
        int countClosingBrackets = 0;
        while (priority > 0) {
            for (int i = 0; i < expression.length(); i++) {
                symbol = expression.charAt(i);
                if (countBrackets != 0) {
                    if (i >= indexOpeningBrackets.get(priority - 1) && i <= indexClosingBrackets.get(countClosingBrackets)) {

                        switch (symbol) {

                            case MULTIPLICATION:
                                orders.add(MULTIPLICATION);
                                break;
                            case DIVISION:
                                orders.add(DIVISION);
                                break;
                        }
                    }
                } else switch (symbol) {

                    case MULTIPLICATION:
                        orders.add(MULTIPLICATION);
                        break;
                    case DIVISION:
                        orders.add(DIVISION);
                        break;
                }

            }
            for (int i = 0; i < expression.length(); i++) {
                symbol = expression.charAt(i);
                if (countBrackets != 0) {
                    if (i >= indexOpeningBrackets.get(priority - 1) && i <= indexClosingBrackets.get(countClosingBrackets)) {
                        switch (symbol) {
                            case ADDITION:
                                orders.add(ADDITION);
                                break;

                            case SUBTRACTION:
                                orders.add(SUBTRACTION);
                                break;

                        }
                    }
                } else switch (symbol) {
                    case ADDITION:
                        orders.add(ADDITION);
                        break;

                    case SUBTRACTION:
                        orders.add(SUBTRACTION);
                        break;

                }

            }
            countClosingBrackets++;
            priority--;
        }
    }

    // Составление списка операндов по очередности участия в операциях
    private void prepareNumbers(String expression) {
        char symbol;
        int count = 1;
        priority = countBrackets + 1;
        StringBuilder tempString = new StringBuilder();
        String textTemp = expression.replaceAll("\\s+", "");
        while (priority > 0) {
            for (int i = 0; i < textTemp.length(); i++) {
                symbol = this.expression.charAt(i);
                switch (symbol) {
                    case OPENING_BRACKETS:
                        count++;
                    case CLOSING_BRACKETS:
                        count--;
                }
                if (priority == count) {
                    tempString.append(symbol);
                    System.out.println("PrepareForCalc.StringBuilder.append() вызвал" + tempString);
                }
                priority--;
            }
            String num[] = tempString.toString().replaceAll("\\p{P}", "").split("\\D");
            for (String str : num) {
                numbers.add(Integer.parseInt(str));
            }
        }

    }

    public List<Character> getOrders() {
        return orders;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
