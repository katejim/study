package parser.expressions;

import parser.ExpVisitor;
import evaluator.ExpressionVisitor;

/**
 * Created by KateKate on 12.05.14.
 */
public class Div extends BiExp {
    public Div(Exp left, Exp right) {
        super(left, right);
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