package simplecalc;


import java.util.ArrayList;
import java.util.List;

//Класс пересчета строки на токены
class TokensCreator {
    private String expression;
    private StringBuilder temp = new StringBuilder();
    private List<String> tokenList = new ArrayList<>();

    //Конструктор
    public TokensCreator(String stringExpression) {
         this.expression = stringExpression;
         makeTokens(expression);
    }

    //Переведение строки в список токенов
    private void makeTokens(String expression) {
        char symbol;
        for (int i = 0; i < expression.length(); i++) {
            symbol = expression.charAt(i);
            getTokens(symbol, i);
        }

    }

    //Получение токена
    private void getTokens(char symbol, int index) {
        if (symbol != '/' && symbol != '*' && symbol != '+' && symbol != '-' && symbol != '(' && symbol != ')') {
            temp.append(symbol);
            if (index == expression.length() - 1) {
                tokenList.add(temp.toString());
                temp.setLength(0);
            }
        } else {
            if (temp.length() != 0) {
                tokenList.add(temp.toString());
                temp.setLength(0);
            }
            String s = "" + symbol;
            tokenList.add(s);

        }

    }
    //Получение спсика токенов
    public List<String> getTokenList() {
        return tokenList;
    }

    // Проверка работы
    public static void main(String[] args) {
        TokensCreator tokens = new TokensCreator("123+15-12*2");

        for (String list : tokens.getTokenList()) {
            System.out.println(list);
        }
    }

}
