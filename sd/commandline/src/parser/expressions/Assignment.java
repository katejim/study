package parser.expressions;

import parser.ExpVisitor;
import evaluator.ExpressionVisitor;

/**
 * Created by KateKate on 13.05.14.
 */
public class Assignment implements Exp {
    public final String name;
    public final Exp value;
    public final int start;
    public final int length;

    public Assignment(String name, Exp value,int start, int length) {
        this.name = name;
        this.value = value;
        this.length = length;
        this.start = start;
    }

    @Override
    public void accept(ExpVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Exp evaluate(ExpressionVisitor visitor) throws ExpressionVisitor.Error {
        return visitor.visit(this);
    }
}
