package simplecalc;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public class SimpleCalc {

    //добавление слушателя на кнопки
    private static void addJButtonListener(List<JButton> list) {

        ActionListener actionListener = new NumberListener();
        for (JButton button : list) {
            button.addActionListener(actionListener);
        }
    }

    //Запуск программы
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


}
