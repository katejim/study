package parser;

import lexer.Lexer;
import lexer.Token;
import parser.expressions.*;

/**
 * Created by KateKate on 13.05.14.
 */
public class Parser {
    private final String string;
    private final Lexer lexer;

    public Parser(String string) {
        this.string = string;
        lexer = new Lexer(string);
        lexer.divideToLexem();
    }

    public Exp parse() {
        Exp exp = parseExpression();
        if (lexer.getCurrentToken().tokenType != Token.Type.EOF) {
            return null;
        }
        return exp;
    }

    private Num parseNum() {
        if (lexer.getCurrentToken().tokenType != Token.Type.NUMBER) {
            return null;
        }

        Number number = Double.parseDouble(lexer.getCurrentToken().value);
        int begin = lexer.getCurrentToken().begin;
        int length = lexer.getCurrentToken().length;
        lexer.nextToken();
        return new Num(number, begin, length);
    }

    private Exp parseVariable() {
        if (lexer.getCurrentToken().tokenType != Token.Type.VARIABLE) {
            return null;
        }
        String value = lexer.getCurrentToken().value;
        int begin = lexer.getCurrentToken().begin;
        int length = lexer.getCurrentToken().length;

        lexer.nextToken();
        if (lexer.getCurrentToken().tokenType == Token.Type.EQ) {
            lexer.nextToken();
            Exp expr = parseExpression();
            if (expr == null) {
                return null;
            }
            return new Assignment(value, expr, begin, length);
        }
        return new Variable(value, begin, length);
    }

    private Exp parseExpression() {
        Exp leftPart = primary();
        if (leftPart == null) {
            return null;
        }

        if (lexer.getCurrentToken().tokenType == Token.Type.WRONG) {
            return null;
        }

        if (lexer.getCurrentToken().tokenType != Token.Type.SUB && lexer.getCurrentToken().tokenType != Token.Type.SUM) {
            return leftPart;
        }

        Token.Type op = lexer.getCurrentToken().tokenType;
        lexer.nextToken();
        Exp rightPart = parseExpression();
        if (rightPart == null) {
            return null;
        }

        if (op == Token.Type.SUB) {
            return new Sub(leftPart, rightPart);
        }
        if (op == Token.Type.SUM) {
            return new Sum(leftPart, rightPart);
        }
        return null;
    }

    private Exp value() throws Error {
        if (lexer.getCurrentToken().tokenType == Token.Type.SUB) {
            lexer.nextToken();
            Exp expr = value();
            if (expr == null) {
                return null;
            }
            return new Sub(new Num(0, 0, 0), expr);
        }
        if (lexer.getCurrentToken().tokenType == Token.Type.OPEN_BRACKET) {
            lexer.nextToken();
            Exp expr = parseExpression();
            if (expr == null || lexer.getCurrentToken().tokenType != Token.Type.CLOSE_BRACKET) {
                return null;
            }
            lexer.nextToken();
            return expr;
        }

        Exp val = parseNum();
        if (val == null)
            val = parseVariable();
        return val;
    }

    private Exp primary() {
        Exp leftPart = value();
        if (leftPart == null) {
            return null;
        }

        if (lexer.getCurrentToken().tokenType != Token.Type.DIV && lexer.getCurrentToken().tokenType != Token.Type.MUL) {
            return leftPart;
        }

        Token.Type op = lexer.getCurrentToken().tokenType;
        lexer.nextToken();
        Exp rightPart = primary();
        if (rightPart == null) {
            return null;
        }

        if (op == Token.Type.DIV) {
            return new Div(leftPart, rightPart);
        }
        if (op == Token.Type.MUL) {
            return new Mul(leftPart, rightPart);
        }

        return null;
    }
}
