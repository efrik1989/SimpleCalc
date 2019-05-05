package simplecalc;

import java.util.ArrayList;
import java.util.List;

class PrepareForCalc extends Check {

    private String expression;

    private int priority;


    private List<Character> orders = new ArrayList<>();
    private List<Integer> numbers = new ArrayList<>();

    PrepareForCalc(String expression) {

        this.expression = expression;
        runToPrepare(expression);


    }


    private void runToPrepare(String expression) {
        if (expression != null) {
            checkForBrackets(expression);
            prepareForOrders(expression);
            prepareNumbers(expression);
        }

    }


    //Добавление очередности операций
    private void prepareForOrders(String expression) {
        priority = getCountBrackets();
        char symbol;
        int count = 0;
        while (priority >= 0) {
            for (int i = 0; i < expression.length(); i++) {
                symbol = expression.charAt(i);
                count = countController(symbol, count);
                if (priority == count) {
                    if (symbol != '=') {
                        checkForMultiplicationAndDivision(symbol, (ArrayList<Character>) orders);
                        System.out.println("PrepareForCalc.prepareForOrders вызвал = " );
                    }
                }
            }

            for (int i = 0; i < expression.length(); i++) {
                symbol = expression.charAt(i);
                count = countController(symbol, count);
                if (priority == count) {
                    if (symbol != '=') {
                        checkForAdditionAndSubtraction(symbol, (ArrayList<Character>) orders);
                        System.out.println("PrepareForCalc.prepareForOrders вызвал = " );
                    }
                }
            }

            priority--;
        }
    }

    // Составление списка операндов по очередности участия в операциях
    private void prepareNumbers(String expression) {
        char symbol;
        int count = 0;
        priority = getCountBrackets();
        StringBuilder tempString;
        String textTemp = expression.replaceAll("\\s+", "");
        while (priority >= 0) {
            tempString = new StringBuilder();
            for (int i = 0; i < textTemp.length(); i++) {
                symbol = expression.charAt(i);
                count = countController(symbol, count);
                if (priority == count) {
                    if (symbol != '=') {
                        tempString.append(symbol);
                        System.out.println("PrepareForCalc.StringBuilder.append() вызвал = " + tempString);
                    }
                }

            }
            priority--;
            addIntoList(tempString);

        }

        //Ниже код для просмотра того, что внутри списка
        for (Integer numInList : numbers ) {
            System.out.println("NumInList " + numInList);
        }
        for (Character ordersInList : orders) {
            System.out.println("OrdersInList " + ordersInList);
        }

    }
    //Конвертация в String и Добавление чисел в список
    private void addIntoList(StringBuilder tempData){
        String s = tempData.toString().replaceAll("[()]", "");
        String num[] = s.split("[/*+-]");
        for (String str : num) {
            System.out.println("CalcGUI.doActionInDocument() called prepareNumbers() = " + str);
            numbers.add(Integer.parseInt(str));
        }
    }
    // Изменяет значение count при попадании на скобки в цикле
    private int countController(char symbol, int count) {
        switch (symbol) {
            case OPENING_BRACKETS:
                count++;
                break;
            case CLOSING_BRACKETS:
                count--;
                break;
        }
        return count;
    }

    List<Character> getOrders() {
        return orders;
    }

    List<Integer> getNumbers() {
        return numbers;
    }
}
