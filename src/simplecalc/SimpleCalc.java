package simplecalc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static simplecalc.CalcGUI.getTextField;

public class SimpleCalc {

    private static final char EQUALLY = '=';
    private static final char CANCEL = 'C';
    private static boolean isDouble;
    private static Calc calc;

    public static void main(String[] args) {
        List<JButton> buttonList;

        JFrame window = new CalcGUI();
        window.setVisible(true);
        window.pack();

        buttonList = CalcGUI.getButtonList();
        buttonList.addAll(CalcGUI.getFunctionButtonList2());
        buttonList.addAll(CalcGUI.getListOnTop());

        addJButtonListener(buttonList);
    }

    //В зависимости от результатов проверки на плавующую точку, создание экземпляра Calc
    public static void equallyAction() {
        isDouble = Check.getIsDouble();
        if(isDouble){
            calc = new DoubleCalc();
        } else calc = new IntCalc();
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
        private static String temp;


        //Действия пр нажатии кнопок
        @Override
        public void actionPerformed(ActionEvent e) throws NumberFormatException {
            buttonCommand = e.getActionCommand();
            if (!buttonCommand.equals(".")) {
                    if (temp != null) {
                        temp = temp + buttonCommand;
                        getTextField().setText(temp);
                        switch (buttonCommand.charAt(0)) {
                            case EQUALLY:
                                SimpleCalc.equallyAction();
                                getTextField().setText(calc.stringCalc(getTemp()));
                                System.out.println("SimpleCalc.stringCalc вызов = " + getTextField().getText());
                                break;
                            case CANCEL:
                                cancel();
                        }
                    } else temp = buttonCommand;

            }

        }

        public static String getTemp() {
            return temp;
        }

        public static void setTemp(String temp) {
            NumberListener.temp = temp;
        }

        private void cancel() {
            setTemp("");
            getTextField().setText("");
        }
    }


}
