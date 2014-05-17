package parser.expressions;


import parser.ExpVisitor;
import evaluator.ExpressionVisitor;

/**
 * Created by KateKate on 12.05.14.
 */
public class Variable implements Exp {
    public final String name;
    public final int start;
    public final int length;

    public Variable(String name, int start, int length) {
        this.name = name;
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
