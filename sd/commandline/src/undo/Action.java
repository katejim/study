package undo;

import lexer.Token;
import parser.expressions.Exp;

import java.util.Map;

/**
 * Created by KateKate on 15.05.14.
 */
public class Action {
    public final Map<String, Exp> context;
    public Boolean simplify;

    public Action(Map<String, Exp> context, Boolean simplify) {
        this.context = context;
        this.simplify = simplify;
    }
}
