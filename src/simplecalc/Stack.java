package simplecalc;

import java.util.ArrayList;
import java.util.List;

//Класс Реализации Stack

public class Stack {
    List<Object> list;


    public Stack(){
        list = new ArrayList<>();

    }
    //Добавить в стэк
    public void addToStack(Object object) {
        list.add(object);
    }

    //Удалить из стэка
    public void remove() {
        list.remove(list.size() - 1);

    }
    //Получение последнего добавленного элемента в стэк
    public Object getTokenFromStack(){
        if (list.size() != 0) {
            return list.get(list.size() - 1);
        }
        return null;
    }

    //Проверка на пустоту стэка
    public boolean IsEmpty() {
        if (list.size() == 0) {
         return true;
        }
        else return false;
    }

    // Проверка работаспособности стэка
    public static void main(String[] args) {
       Stack stack = new Stack();

       stack.addToStack(";jgf");
       stack.addToStack("жопа");
       stack.addToStack("opa-popa");

       while (!stack.IsEmpty()) {
           System.out.println(stack.getTokenFromStack());
           stack.remove();
       }

    }
}

