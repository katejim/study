package parser;

import parser.expressions.*;

/**
 * Created by KateKate on 12.05.14.
 */
public interface ExpVisitor {
    void visit(Num num);
    void visit(Sum sum);
    void visit(Sub sub);
    void visit(Mul mul);
    void visit(Div div);
    void visit(Variable variable);
    void visit(Assignment assignment);
}