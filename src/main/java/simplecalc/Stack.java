package simplecalc;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Реализации Stack
 */
public class Stack {
    List<Object> list;

    Stack() {
        list = new ArrayList<>();
    }

    /**
     * Добавить в стэк
     */
    void addToStack(Object object) {
        list.add(object);
    }

    /**
     * Удалить из стэка
     */
    void remove() {
        list.remove(list.size() - 1);

    }

    /**
     * Получение последнего добавленного элемента в стэк
     */
    Object getTokenFromStack() {
        if (list.size() != 0) {
            return list.get(list.size() - 1);
        }
        return null;
    }

    /**
     * Проверка на пустой стек
     */
    boolean IsEmpty() {
        return list.size() == 0;
    }
}

