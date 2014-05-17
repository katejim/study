package parser.expressions;

import parser.ExpVisitor;
import evaluator.ExpressionVisitor;

/**
 * Created by KateKate on 12.05.14.
 */
public class Num implements Exp {
    public final Number number;
    public final int start;
    public final int length;

    public Num(Number number, int start, int length) {
        this.number = number;
        this.length = length;
        this.start = start;
    }

    @Override
    public void accept(ExpVisitor prettyPrinter) {
        prettyPrinter.visit(this);
    }

    @Override
    public Exp evaluate(ExpressionVisitor visitor) throws ExpressionVisitor.Error {
        return visitor.visit(this);
    }
}