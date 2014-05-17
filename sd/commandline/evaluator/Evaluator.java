package evaluator;

import parser.expressions.*;

import java.util.Map;

/**
 * Created by KateKate on 13.05.14.
 */
public class Evaluator implements ExpressionVisitor {
    public final Map<String, Exp> context;
    public Boolean simplify = true;

    public Evaluator(Map<String, Exp> context, Boolean simplify) {
        this.context = context;
        this.simplify = simplify;
    }

    @Override
    public Exp visit(Num num) {
        return num;
    }

    @Override
    public Exp visit(Sum exp) throws Error {
        Exp left = exp.left.evaluate(this);
        Exp right = exp.right.evaluate(this);
        if (left instanceof Num && right instanceof Num) {
            return new Num(((Num) left).number.doubleValue() + ((Num) right).number.doubleValue(), 0, 0);
        }
        return new Sum(left, right);
    }

    @Override
    public Exp visit(Sub exp) throws Error {
        Exp left = exp.left.evaluate(this);
        Exp right = exp.right.evaluate(this);
        if (left instanceof Num && right instanceof Num) {
            return new Num(((Num) left).number.doubleValue() - ((Num) right).number.doubleValue(), 0, 0);
        }
        return new Sub(left, right);
    }

    @Override
    public Exp visit(Mul exp) throws Error {
        Exp left = exp.left.evaluate(this);
        Exp right = exp.right.evaluate(this);
        if (left instanceof Num && right instanceof Num) {
            return new Num(((Num) left).number.doubleValue() * ((Num) right).number.doubleValue(), 0, 0);
        }
        return new Mul(left, right);
    }

    @Override
    public Exp visit(Div exp) throws Error {
        Exp left = exp.left.evaluate(this);
        Exp right = exp.right.evaluate(this);
        if (left instanceof Num && right instanceof Num) {
            if (((Num) right).number.doubleValue() == 0 ){
                throw new ExpressionVisitor.Error ("division by zero");
            }
            return new Num(((Num) left).number.doubleValue() / ((Num) right).number.doubleValue(), 0, 0);
        }
        return new Div(left, right);
    }

    @Override
    public Exp visit(Variable variable) throws Error {
        if (context.containsKey(variable.name)) {
            return context.get(variable.name).evaluate(this);
        }
        if (simplify) {
            return variable;
        }
        throw new ExpressionVisitor.Error ("variable " + variable.name + " not found ");
    }

    @Override
    public Exp visit(Assignment assignment) throws Error {
        Exp result = assignment.value.evaluate(this);
        context.put(assignment.name, result);
        return result;
    }

}
