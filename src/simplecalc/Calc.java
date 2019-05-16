package simplecalc;

import java.util.HashMap;


public class Calc {

    private Stack numbersStack = new Stack();
    private  Stack operatorsStack = new Stack();

    private String[] operatorsArray = {"/", "*", "-", "+"};

    //Конструктор
    public Calc() {

    }

    private HashMap createPriorityMap(){
        HashMap<String, Integer> map = new HashMap<>();

        for (String operator : operatorsArray) {
            if (operator.equals("/") || operator.equals("*")) {
                map.put(operator, 2);
            }else if (operator.equals("-") || operator.equals("+")) {
                map.put(operator, 1);
            }
        }

        return map;
    }
    //Логика
     String calculate(String expression) {

        TokensCreator tokensCreator = new TokensCreator(expression);
        HashMap <String, Integer> priorityMap = createPriorityMap();

        //Перебор токенов
        for (String token : tokensCreator.getTokenList()) {
            if (token.matches("[/*+-]")) {

                binaryOperatorsConditions(token, priorityMap);
            }
            else if (token.matches("[()]")) {

                bracketsConditions(token);

                }
            else numbersStack.addToStack(token);
        }
        while (!operatorsStack.IsEmpty()) {

            numbersStack.addToStack(doAction());
        }

        return (String) numbersStack.getTokenFromStack();

    }
    //Основная калькуляция
    private String doAction() {
        int temp = 0;
        String tempOperator = (String) operatorsStack.getTokenFromStack();
        int num1, num2;
        num2 = Integer.parseInt((String) numbersStack.getTokenFromStack());
        numbersStack.remove();
        num1 = Integer.parseInt((String) numbersStack.getTokenFromStack());
        numbersStack.remove();

        switch (tempOperator) {
            case "/" :
                temp = num1 / num2;
                break;
            case "*" :
                temp = num1 * num2;
                break;
            case "-" :
                temp = num1 - num2;
                break;
            case "+" :
                temp = num1 + num2;
                break;
        }

        operatorsStack.remove();

        return temp + "";
    }
    // Условия совершения бинарных операций
    private void binaryOperatorsConditions(String token, HashMap<String, Integer> priorityMap) {
        if (operatorsStack.IsEmpty()) {
            operatorsStack.addToStack(token);

        } else if (operatorsStack.getTokenFromStack().equals("(") || priorityMap.get(token) > priorityMap.get(operatorsStack.getTokenFromStack())) {
            operatorsStack.addToStack(token);

        }
        else if (priorityMap.get(token) <= priorityMap.get(operatorsStack.getTokenFromStack())) {

            while (!operatorsStack.IsEmpty() && !operatorsStack.getTokenFromStack().equals("(") && priorityMap.get(token) <= priorityMap.get(operatorsStack.getTokenFromStack())) {
                numbersStack.addToStack(doAction());
            }
            operatorsStack.addToStack(token);

        }
    }
    // Действия при скобка
    private void bracketsConditions(String token) {
        switch (token) {
            case "(" :
                operatorsStack.addToStack(token);
                break;
            case ")" :
                if (operatorsStack.getTokenFromStack().equals("(")) {
                    operatorsStack.remove();
                } else while (!operatorsStack.getTokenFromStack().equals("(")) {
                    numbersStack.addToStack(doAction());
                }
                operatorsStack.remove();
                break;
        }
    }
    // Метод для проверки
    public static void main(String[] args) {
        Calc calc = new Calc();
        System.out.println(calc.calculate("15+45-(2+5*6)-5"));
    }
}



