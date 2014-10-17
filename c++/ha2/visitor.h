#ifndef VISITOR_HPP
#define VISITOR_HPP

class Visitor
{
public:
    virtual int visit(class NumAST const &) = 0;
    virtual int visit(class VarAST const &) = 0;
    virtual int visit(class VarDefAST const &) = 0;

    virtual int visit(class InputAST const &) = 0;
    virtual int visit(class OutputAST const &) = 0;

    virtual int visit(class CondAST const &) = 0;
    virtual int visit(class IfAST const &) = 0;
    virtual int visit(class WhileAST const &) = 0;

    virtual int visit(class ReturnAST const &) = 0;
    virtual int visit(class FuncPrAST const &) = 0;
    virtual int visit(class FuncDefAST const &) = 0;
};

#endif
