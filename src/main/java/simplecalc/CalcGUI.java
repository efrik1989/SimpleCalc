package simplecalc;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс отвечающий за создание GUI приложениия
 */
public class CalcGUI extends JFrame {
    private static JFormattedTextField textField;
    private Container container;
    private static List<JButton> list;
    private static List<JButton> list2;
    private static List<JButton> listOnTop;
    private Font myFont = new Font("MyFont", Font.BOLD, 20);

    public CalcGUI() {
        super("Калькулятор");
        createGUI();
    }

    /**
     * Создание компонентов окна
     */
    private void createGUI() {
        //Назначение слушателя на текстовое поле
        DocumentListener textFieldListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent event) {
                doActionInDocument(event);
            }

            @Override
            public void removeUpdate(DocumentEvent event) {
                doActionInDocument(event);
            }

            @Override
            public void changedUpdate(DocumentEvent event) {

            }
        };
        container = this.getContentPane();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        list = new ArrayList<>();
        list2 = new ArrayList<>();
        listOnTop = new ArrayList<>();

        JPanel panelOnTop = createPanelOnTop(400, 50, textFieldListener);

        JPanel centerPanel = createCenterPanel(300, 400);

        JPanel rightPanel = createRightPanel(100, 400);

        container.add(panelOnTop, new BorderLayout().NORTH);
        container.add(centerPanel, new BorderLayout().CENTER);
        container.add(rightPanel, new BorderLayout().EAST);

        setContentPane(container);
        setSize(400, 500);
    }

    /**
     * Подхватывание текста JTextField и добавление в переменную
     *
     * @param event изменение значения документа
     */
    private void doActionInDocument(DocumentEvent event) {
        Document doc = event.getDocument();
        try {
            NumberListener.setTemp(doc.getText(0, doc.getLength()));
            System.out.println("CalcGUI.doActionInDocument() called SimpleCalc.NumberListener.getTemp() = " + NumberListener.getTemp());
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public static List<JButton> getButtonList() {
        return list;
    }

    public static List<JButton> getFunctionButtonList2() {
        return list2;
    }

    public static List<JButton> getListOnTop() {
        return listOnTop;
    }

    /**
     * Создание кнопок операций
     */
    private void createOrdersButton() {
        String[] ordersArray = {"/", "*", "-", "+"};
        for (int i = 0; i < ordersArray.length; i++) {
            JButton button = new JButton(ordersArray[i]);
            button.setFont(myFont);
            list2.add(button);
        }
    }

    /**
     * Создание числовых кнопок и специальных кнопок
     */
    private void createSimpleAndSpecialButtons() {
        String[] specialsButtons = {".", "0", "="};
        for (int i = 1; i < 10; i++) {
            JButton button = new JButton("" + i);
            button.setFont(myFont);
            list.add(button);
            System.out.println("Создание кнопки " + i);
        }
        for (int i = 0; i < specialsButtons.length; i++) {
            JButton button = new JButton(specialsButtons[i]);
            button.setFont(myFont);
            list.add(button);
            System.out.println("Создание кнопки " + specialsButtons[i]);
        }
    }

    /**
     * Создание панели в центральной части
     *
     * @return объект панель с компонентами на нем
     */
    private JPanel createCenterPanel(int width, int height) {
        JPanel card = new JPanel();
        card.setLayout(new GridLayout(4, 4, 5, 5));
        card.setPreferredSize(new Dimension(width, height));
        card.setMaximumSize(getPreferredSize());
        createSimpleAndSpecialButtons();

        for (Component component : list) {
            card.add(component);
        }

        return card;
    }

    /**
     * Создание панели в правой части
     *
     * @return объект панель с компонентами на нем
     */
    private JPanel createRightPanel(int width, int height) {
        JPanel card = new JPanel();
        card.setLayout(new GridLayout(4, 4, 5, 5));
        card.setPreferredSize(new Dimension(width, height));
        card.setMinimumSize(getPreferredSize());

        createOrdersButton();

        for (Component component : list2) {
            card.add(component);
        }

        return card;
    }

    /**
     * Создание верхней панели
     *
     * @return объект панель с компонентами на нем
     */
    private JPanel createPanelOnTop(int width, int height, DocumentListener listener) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 5, 5));
        textField = new JFormattedTextField("0");
        textField.setFont(myFont);
        textField.setPreferredSize(new Dimension(width, height));
        textField.setMinimumSize(textField.getPreferredSize());
        textField.getDocument().addDocumentListener(listener);
        textField.setEditable(true);

        JButton ceButton = new JButton("C");
        ceButton.setFont(myFont);
        ceButton.setPreferredSize(new Dimension(400, 50));
        ceButton.setMaximumSize(ceButton.getPreferredSize());

        listOnTop.add(ceButton);

        panel.add(textField);
        panel.add(listOnTop.get(0));

        return panel;
    }

    public static JTextField getTextField() {
        return textField;
    }
}
