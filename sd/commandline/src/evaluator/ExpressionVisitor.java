package evaluator;

import parser.expressions.*;

/**
 * Created by KateKate on 13.05.14.
 */
public interface ExpressionVisitor {
    Exp visit(Num num) throws Error;
    Exp visit(Sum sum) throws Error;
    Exp visit(Sub sub) throws Error;
    Exp visit(Mul mul) throws Error;
    Exp visit(Div div) throws Error;
    Exp visit(Variable variable) throws Error;
    Exp visit(Assignment assignment) throws Error;

    public static class Error extends Exception {
        public Error(String msg) {
            super(msg);
        }
    }
}
