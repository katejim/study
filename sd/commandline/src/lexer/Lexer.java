package lexer;

import evaluator.ExpressionVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KateKate on 13.05.14.
 */

public class Lexer {
    private List<Token> tokens = new ArrayList<Token>();
    private int tokenNum = 0;
    private String string;

    public Lexer(String string) {
        this.string = string;
    }


    public void divideToLexem() {
        int i = 0;
        while (i < string.length()) {
            if (Character.isWhitespace(string.charAt(i))) {
                i += 1;
                continue;
            }
            if (Character.isAlphabetic(string.charAt(i))) {
                i = getVariable(i);
                continue;
            }
            if (Character.isDigit(string.charAt(i))) {
                i = getNumber(i);
                continue;
            }
            i = getOperation(i);
        }
        tokens.add(new Token(Token.Type.EOF, "eof", -1));
    }

    public Token getCurrentToken() {
        return tokens.get(tokenNum);
    }

    public Token nextToken() {
        tokenNum += 1;
        if (tokenNum < tokens.size()) {
            getCurrentToken();
        }
        return null;
    }

    private int getVariable(int begin) {
        String value = "";
        int i = begin;
        while ((i < string.length()) && (Character.isAlphabetic(string.charAt(i)))) {
            value += string.charAt(i);
            i += 1;
        }
        tokens.add(new Token(Token.Type.VARIABLE, value, begin));
        return i;
    }

    private int getNumber(int begin) {
        String value = "";
        int i = begin;
        while ((i < string.length()) && (Character.isDigit(string.charAt(i)) || string.charAt(i) == '.')) {
            value += string.charAt(i);
            i += 1;
        }
        tokens.add(new Token(Token.Type.NUMBER, value,begin));
        return i;
    }

    private int getOperation(int begin) throws Error{
        String value = "";
        int i = begin;
        Token.Type type;
        switch (string.charAt(i)) {
            case '(':
                type = Token.Type.OPEN_BRACKET;
                value = "(";
                break;
            case ')':
                type = Token.Type.CLOSE_BRACKET;
                value = ")";
                break;
            case '+':
                type = Token.Type.SUM;
                value = "+";
                break;
            case '-':
                type = Token.Type.SUB;
                value = "-";
                break;
            case '*':
                type = Token.Type.MUL;
                value = "*";
                break;
            case '/':
                type = Token.Type.DIV;
                value = "/";
                break;
            case '=':
                type = Token.Type.EQ;
                value = "=";
                break;
            default:
                type = Token.Type.WRONG;
                value = "";
                value += string.charAt(i);
                break;
        }
        tokens.add(new Token(type, value,begin));
        return i + 1;
    }
}
