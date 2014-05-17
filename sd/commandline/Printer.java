import parser.ExpVisitor;
import parser.expressions.*;

import java.util.ArrayList;
import java.util.List;


public class Printer implements ExpVisitor {
    public static class ElementDescription {
        public enum Type {
            NUMBER, VARIABLE;
        }

        public int length;
        public int start;
        public Type type;

        private ElementDescription(int start, int length, Type type) {
            this.start = start;
            this.length = length;
            this.type = type;
        }
    }

    public List<ElementDescription> varAndNumElements = new ArrayList<ElementDescription>();

    public String result = "";

    public void visit(Num exp) {
        result += Double.toString(exp.number.doubleValue());
        varAndNumElements.add(new ElementDescription(exp.start, exp.length, ElementDescription.Type.NUMBER));
    }

    public void visit(Variable exp) {
        result += exp.name;
        varAndNumElements.add(new ElementDescription(exp.start, exp.length, ElementDescription.Type.VARIABLE));
    }

    public void visit(Div exp) {
        Div div = exp;
        div.left.accept(this);
        result += " / ";
        div.right.accept(this);
    }

    public void visit(Mul exp) {
        Mul mul = exp;
        mul.left.accept(this);
        result += " * ";
        mul.right.accept(this);
    }

    public void visit(Sum exp) {
        Sum sum = exp;
        result += "(";
        sum.left.accept(this);
        result += " + ";
        sum.right.accept(this);
        result += ")";

    }

    @Override
    public void visit(Sub exp) {
        Sub sub = exp;
        result += "(";
        sub.left.accept(this);
        result += " - ";
        sub.right.accept(this);
        result += ")";
    }

    public void visit(Assignment exp) {
        result += exp.name;
        varAndNumElements.add(new ElementDescription(exp.start, exp.length, ElementDescription.Type.VARIABLE));
        result += " = ";
        exp.value.accept(this);
    }
}

