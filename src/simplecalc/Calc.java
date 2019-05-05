package simplecalc;

import java.util.List;

public class Calc {
    static final char ADDITION = '+';
    static final char SUBTRACTION = '-';
    static final char MULTIPLICATION = '*';
    static final char DIVISION = '/';

    public String stringCalc(String textField) {
       PrepareForCalc prepareForCalc = new PrepareForCalc(textField);
        List<Integer> numbers = prepareForCalc.getNumbers();
        List<Character> orders = prepareForCalc.getOrders();

        int num = 0;

        for (int i = 0; i < orders.size(); i++) {
            if (i == 0) {
                num = numbers.get(i);
            }
            if (i + 1 < numbers.size()) {
                switch (orders.get(i)) {
                    case DIVISION:
                        num = num / numbers.get(i + 1);
                        break;
                    case MULTIPLICATION:
                        num = num * numbers.get(i + 1);
                        break;
                    case SUBTRACTION:
                        num = num - numbers.get(i + 1);
                        break;
                    case ADDITION:
                        num = num + numbers.get(i + 1);
                        break;
                }
            }

        }

        System.out.print("= " + num);

        return num + "";
    }
}
