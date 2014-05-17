package parser.expressions; /**
 * Created by KateKate on 12.05.14.
 */
import parser.ExpVisitor;
import evaluator.ExpressionVisitor;

public interface Exp {
    void accept(ExpVisitor visitor);
    Exp evaluate(ExpressionVisitor visitor) throws ExpressionVisitor.Error;
}










