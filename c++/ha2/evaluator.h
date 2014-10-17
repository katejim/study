#ifndef EVALUATOR_HPP
#define EVALUATOR_HPP

#include <iostream>
#include <string>
#include <stack>

#include "ast.h"
#include "visitor.h"
#include "parser.h"
#include "errors.h"


using std::string;
using std::vector;
using std::map;
using std::stack;
using std::cout;
using std::cin;
using std::endl;

class Evaluator : public Visitor
{
    ExprSharedPtr entry;
    map<string, FuncSharedPtr> myFuncs;
    map<string, int> varsGlob;
    stack<map<std::string, int> > varsVocal;

    bool getVar(string const &, int &);
    void setVar(string const &, int );

    void valueAcceptFunc(ExprSharedPtrs::const_iterator , ExprSharedPtrs::const_iterator);
    Evaluator(Evaluator const &);
    Evaluator &operator=(Evaluator const &);
public:
    explicit Evaluator(Program const &program) : entry(program.entry), myFuncs(program.Functions)  {}

    int visit(class NumAST const &);
    int visit(class VarAST const &);
    int visit(class VarDefAST const &);

    int visit(class InputAST const &);
    int visit(class OutputAST const &);

    int visit(class CondAST const &);
    int visit(class IfAST const &);
    int visit(class WhileAST const &);

    int visit(class ReturnAST const &);
    int visit(class FuncPrAST const &);
    int visit(class FuncDefAST const &);

    void start()
    {
        entry->accept(*this);
    }
};



#endif
