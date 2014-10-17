#ifndef AST_HPP
#define AST_HPP

#include <vector>
#include <memory>
#include "visitor.h"


using std::shared_ptr;
using std::vector;
using std::string;

class ExprAST;

typedef shared_ptr<ExprAST> ExprSharedPtr;
typedef vector<ExprSharedPtr> ExprSharedPtrs;

class FuncDefAST;
typedef shared_ptr<FuncDefAST> FuncSharedPtr;


class ExprAST
{
    size_t curLine;
public:
    ExprAST(size_t lineNumber) : curLine(lineNumber) {}

    size_t getLine() const
    {
        return curLine;
    }

    virtual ~ExprAST()  {}
    virtual int accept(Visitor &) = 0;
};


class NumAST : public ExprAST
{
    int myValue;
public:
    NumAST(int value, size_t line) : ExprAST(line), myValue(value)  {}

    int getVal() const
    {
        return myValue;
    }

    int accept(Visitor &v)
    {
        return v.visit(*this);
    }
};

class InputAST: public ExprAST
{
    string myVar;
public:
    InputAST(string const &variable, size_t line) : ExprAST(line), myVar(variable) {}

    string const &getVar() const
    {
        return myVar;
    }

    int accept(Visitor &v)
    {
        return v.visit(*this);
    }
};

class OutputAST : public ExprAST
{
    ExprSharedPtr myExpr;
public:
    OutputAST(ExprSharedPtr expression, size_t line) : ExprAST(line), myExpr(expression) {}

    ExprSharedPtr getExpr() const
    {
        return myExpr;
    }

    int accept(Visitor &v)
    {
        return v.visit(*this);
    }

};

class ReturnAST : public ExprAST
{
    ExprSharedPtr myExpr;
public:
    ReturnAST(ExprSharedPtr expression, size_t line) : ExprAST(line), myExpr(expression) {}

    ExprSharedPtr getExpr() const
    {
        return myExpr;
    }

    int accept(Visitor &v)
    {
        return v.visit(*this);
    }
};


class CondAST : public ExprAST
{
    string myOp;
    ExprSharedPtr leftPart;
    ExprSharedPtr rightPart;
public:
    CondAST(string const &operation,ExprSharedPtr left,ExprSharedPtr right, size_t lineNumber)
        : ExprAST(lineNumber), myOp(operation), leftPart(left), rightPart(right) {}

    string const &getOp() const
    {
        return myOp;
    }

    ExprSharedPtr getLeftP() const
    {
        return leftPart;
    }

    ExprSharedPtr getRightP() const
    {
        return rightPart;
    }

    int accept(Visitor &v)
    {
        return v.visit(*this);
    }
};

class VarAST : public ExprAST
{
    string myVar;
public:
    VarAST(string const &variable, size_t line) : ExprAST(line), myVar(variable) {}

    string const &getName() const
    {
        return myVar;
    }

    int accept(Visitor &v)
    {
        return v.visit(*this);
    }
};

class VarDefAST : public ExprAST
{
    string myName;
    ExprSharedPtr myExpr;
public:
    VarDefAST(string const &name, ExprSharedPtr const &expression, size_t line)
        : ExprAST(line), myName(name), myExpr(expression)  {}

    string const &getName() const
    {
        return myName;
    }

    ExprSharedPtr getExpr() const
    {
        return myExpr;
    }

    int accept(Visitor &v)
    {
        return v.visit(*this);
    }

};

class FuncPrAST : public ExprAST
{
    string myName;
    ExprSharedPtrs myParams;
public:
    FuncPrAST(string const &name, ExprSharedPtrs const &params, size_t line)
        : ExprAST(line), myName(name), myParams(params) {}

    string const &getName() const
    {
        return myName;
    }

    ExprSharedPtrs const &getParams() const
    {
        return myParams;
    }

    int accept(Visitor &v)
    {
        return v.visit(*this);
    }
};

class ExprASTs : public ExprAST
{
    ExprSharedPtrs myBody;
public:
    ExprASTs(ExprSharedPtrs const &inBody, size_t line) : ExprAST(line), myBody(inBody) {}

    ExprSharedPtrs const &body() const
    {
        return myBody;
    }

};


class IfAST : public ExprASTs
{
    ExprSharedPtr myCond;
public:
    IfAST(ExprSharedPtr const &condition, ExprSharedPtrs const &inBody, size_t line)
        : ExprASTs(inBody, line), myCond(condition) {}


    ExprSharedPtr getCond() const
    {
        return myCond;
    }
    int accept(Visitor &v)
    {
        return v.visit(*this);
    }
};

class WhileAST : public ExprASTs
{
    ExprSharedPtr myCond;
public:
    WhileAST(ExprSharedPtr const &condition, ExprSharedPtrs const &inBody, size_t line)
        : ExprASTs(inBody, line), myCond(condition) {}

    ExprSharedPtr getCond() const
    {
        return myCond;
    }

    int accept(Visitor &v)
    {
        return v.visit(*this);
    }

};

class FuncDefAST : public ExprASTs
{
    string myName;
    vector<string> myParams;
public:
    FuncDefAST(string const &name,  vector<string> params, ExprSharedPtrs const &inBody, size_t line)
        : ExprASTs(inBody, line), myName(name), myParams(params) {}

    string const &getName() const
    {
        return myName;
    }
    vector<string> const &getParams() const
    {
        return myParams;
    }
    int accept(Visitor &v)
    {
        return v.visit(*this);
    }
};

#endif
