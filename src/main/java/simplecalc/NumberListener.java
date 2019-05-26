package simplecalc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static simplecalc.CalcGUI.getTextField;

/**
 * Класс слушатель кнопок
 */
class NumberListener implements ActionListener {
    private static final char EQUALLY = '=';
    private static final char CANCEL = 'C';
    private static String temp;

    /**
     * Действия пр нажатии кнопок
     *
     * @throws NumberFormatException
     */
    @Override
    public void actionPerformed(ActionEvent e) throws NumberFormatException {
        String buttonCommand = e.getActionCommand();
        if (!buttonCommand.equals(".")) {
            if (temp != null) {
                if (!buttonCommand.equals(EQUALLY)) {
                    temp = temp + buttonCommand;
                }
                getTextField().setText(temp);
                switch (buttonCommand.charAt(0)) {
                    case EQUALLY:
                        Calc calc = new Calc();
                        getTextField().setText(calc.calculate(getTemp().
                                replaceAll("=", "").
                                replaceAll(" ", "")));
                        System.out.println("SimpleCalc.stringCalc вызов = " + getTextField().getText());
                        break;
                    case CANCEL:
                        try {
                            cancel();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;
                }
            } else temp = buttonCommand;
        }
    }

    /**
     * Проверка на корректность введенных значений
     */
    private static void checkCorrectSymbol(String expression) throws Exception {
        if (!(expression.length() == 0)) {
            char symbol = expression.charAt(expression.length() - 1);
            String s = String.valueOf(symbol);
            if (s.matches("[a-zA-ZА-Яа-я]")) {
                setTemp("Введите числовое выражение.");
            }
        }
    }

    /**
     * Получение значения переменной Temp
     */
    public static String getTemp() {
        return temp;
    }

    /**
     * Назначение переменной Temp
     */
    public static void setTemp(String temp) throws Exception {
        NumberListener.temp = temp;
        checkCorrectSymbol(temp);
    }

    /**
     * Метод очистки поля ввода и временных переменных
     * @throws Exception
     */
    public static void cancel() throws Exception {
        setTemp("");
        getTextField().setText("");
    }
}