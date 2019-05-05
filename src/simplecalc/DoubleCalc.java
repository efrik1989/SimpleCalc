package simplecalc;

import java.util.List;

public class DoubleCalc extends Calc{

    DoubleCalc(){}

    @Override
    public String stringCalc(String textField) {
        PrepareForCalc prepareForCalc = new PrepareForCalc(textField);
        List<Integer> numbers = prepareForCalc.getNumbers();
        List<Character> orders = prepareForCalc.getOrders();

        double num = 0.0;

        for (int i = 0; i < orders.size(); i++) {
            if (i == 0) {
                num = numbers.get(i);
            }
            if (i + 1 < numbers.size()) {
                switch (orders.get(i)) {
                    case DIVISION:
                        num = num / numbers.get(i + 1);
                    case MULTIPLICATION:
                        num = num * numbers.get(i + 1);
                    case SUBTRACTION:
                        num = num - numbers.get(i + 1);
                    case ADDITION:
                        num = num + numbers.get(i + 1);
                }
            }

        }

        System.out.print("= " + num);

        return num + "";
    }
}

