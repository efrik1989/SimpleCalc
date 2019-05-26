package simplecalc;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Основной клас, запускающий программу
 */
public class SimpleCalc {

    /**
     * Добавление слушателя на кнопки
     */
    private static void addJButtonListener(List<JButton> buttonList) {

        ActionListener actionListener = new NumberListener();
        for (JButton button : buttonList) {
            button.addActionListener(actionListener);
        }
    }

    /**
     * Запуск программы, взаимодействи с кнопками и GUI
     */
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
