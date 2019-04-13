import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SimpleCalc {
    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char MULTIPLICATION = '*';
    private static final char DIVISION = '/';
    private static final char EQUALLY = '=';

    public static void main(String[] args) {
        List<JButton> buttonList;

        JFrame window = new CalcGUI();
        window.setVisible(true);
        window.pack();

        buttonList = CalcGUI.getButtonList();
        buttonList.addAll(CalcGUI.getFunctionButtonList2());

        addJButtonListener(buttonList);
    }

    private static void addJButtonListener(List<JButton> list) {

        ActionListener actionListener = new NumberListener();
        for (JButton button : list) {
            button.addActionListener(actionListener);
        }
    }

    //Класс слушатель
    static class NumberListener implements ActionListener {
        private String buttonCommand;
        private String temp;


        //Действия пр нажатии кнопок
        @Override
        public void actionPerformed(ActionEvent e) throws NumberFormatException {
            buttonCommand = e.getActionCommand();
            if (buttonCommand != ".") {
                if (temp != null) {
                    temp = temp + buttonCommand;
                    CalcGUI.textField.setText(temp);
                    switch (buttonCommand.charAt(0)) {
                        case EQUALLY:
                            CalcGUI.textField.setText(stringCalc(CalcGUI.textField.getText()));
                            System.out.println("SimpleCalc.stringCalc вызов");
                    }
                } else temp = buttonCommand;
            }

        }


    }


    // Калькуляция строки
    private static String stringCalc(String textField) {
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
