package simplecalc;

import java.util.ArrayList;
import java.util.List;

public class Check implements CheckForPoint, ChekForBrackets {
    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    static final char OPENING_BRACKETS = '(';
    static final char CLOSING_BRACKETS = ')';

    private List<Integer> indexOpeningBrackets = new ArrayList<>();
    private List<Integer> indexClosingBrackets = new ArrayList<>();

    private int priority;

    private int countBrackets = 0;

    private static boolean isDouble;

    public static boolean getIsDouble() {
        return isDouble;
    }

    // Проверка на скобки
    @Override
    public void checkForBrackets(String expression) {
        // Проверка на скобки
            char symbol;

            for (int i = 0; i < expression.length(); i++) {
                symbol = expression.charAt(i);

                switch (symbol) {
                    case OPENING_BRACKETS:
                        setIndexOpeningBrackets(i);
                        countBrackets++;
                        break;

                    case CLOSING_BRACKETS:
                        setIndexClosingBrackets(i);
                        break;
                }

            }

    }
    //Проверка на сложение и вычитание
    public void checkForAdditionAndSubtraction(char symbol, ArrayList<Character> orders){
        switch (symbol) {
            case ADDITION:
                orders.add(ADDITION);
                break;

            case SUBTRACTION:
                orders.add(SUBTRACTION);
                break;
        }
    }

    //Проверка на сложение и вычитание
    public void checkForMultiplicationAndDivision(char symbol, ArrayList<Character> orders) {
        switch (symbol) {

            case MULTIPLICATION:
                orders.add(MULTIPLICATION);
                break;
            case DIVISION:
                orders.add(DIVISION);
                break;
        }
    }


    public int getPriority() {
        return priority;
    }

    //Добавление индекса открытых скобок
    @Override
    public void setIndexOpeningBrackets(int index) {
        indexOpeningBrackets.add(index);
    }
    //Добавление индекса закрытых скобок
    @Override
    public void setIndexClosingBrackets(int index) {
        indexClosingBrackets.add(index);
    }

    public List<Integer> getIndexOpeningBrackets() {
        return indexOpeningBrackets;
    }

    public List<Integer> getIndexClosingBrackets() {
        return indexClosingBrackets;
    }

    public int getCountBrackets() {
        return countBrackets;
    }
}
