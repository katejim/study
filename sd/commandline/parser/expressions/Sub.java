package parser.expressions;

import parser.ExpVisitor;
import evaluator.ExpressionVisitor;

/**
 * Created by KateKate on 13.05.14.
 */
public class Sub extends BiExp {
    public Sub(Exp left, Exp right) {
        super(left, right);
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
