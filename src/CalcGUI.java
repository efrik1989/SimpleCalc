import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CalcGUI extends JFrame {
    static JTextField textField;
    private Container container;
    private static List<JButton> list;
    private static List<JButton> list2;


    //Конструктор

    public CalcGUI() {
        super("Калькулятор");
        createGUI();
    }

    //Создание компонентов окна

    private void createGUI() {
        container = this.getContentPane();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        list = new ArrayList<>();
        list2 = new ArrayList<>();

        JPanel panelOnTop = new JPanel();
        panelOnTop.setLayout(new GridLayout(1, 2, 5, 10));
        textField = new JTextField("0");
        textField.setPreferredSize(new Dimension(400, 50));
        textField.setMinimumSize(textField.getPreferredSize());

        panelOnTop.add(textField);

        JPanel card1 = new JPanel();
        card1.setLayout(new GridLayout(4, 4, 5, 5));
        card1.setPreferredSize(new Dimension(300, 400));
        card1.setMaximumSize(getPreferredSize());

        for (int i = 1; i < 10; i++) {
            list.add(new JButton("" + i));
        }
        list.add(new JButton("."));
        list.add(new JButton("0"));
        list.add(new JButton("="));

        for (Component component : list) {
            card1.add(component);
        }


        JPanel card2 = new JPanel();
        card2.setLayout(new GridLayout(4, 4, 5, 5));
        card2.setPreferredSize(new Dimension(100, 400));
        card2.setMinimumSize(getPreferredSize());
        list2.add(new JButton("/"));
        list2.add(new JButton("*"));
        list2.add(new JButton("-"));
        list2.add(new JButton("+"));

        for (Component component : list2) {
            card2.add(component);
        }

        container.add(panelOnTop, new BorderLayout().NORTH);
        container.add(card1, new BorderLayout().CENTER);
        container.add(card2, new BorderLayout().EAST);

        setContentPane(container);
        setSize(400, 500);
    }

    public static List<JButton> getButtonList() {
        return list;
    }

    public static List<JButton> getFunctionButtonList2() {
        return list2;
    }
}
