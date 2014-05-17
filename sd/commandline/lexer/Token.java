package lexer;

/**
 * Created by KateKate on 13.05.14.
 */
public class Token {
    public enum Type {
        VARIABLE, NUMBER,
        SUM, DIV, MUL, SUB,
        OPEN_BRACKET, CLOSE_BRACKET,
        EQ,
        WRONG,
        EOF;
    }

    public Token(Type tokenType, String value, int begin) {
        this.tokenType = tokenType;
        this.value = value;
        this.begin = begin;
        this.length = value.length();
    }

    public Type tokenType;
    public String value;
    public int begin;
    public int length;
}
