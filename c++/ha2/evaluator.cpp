#include "evaluator.h"

class Return
{
    int value;
public:
    explicit Return(int inValue) : value(inValue) {}

    long getValue() const
    {
        return value;
    }
};

int Evaluator::visit(NumAST const &in)
{
    return in.getVal();
}

int Evaluator::visit(VarAST const &in)
{
    int value = 0;
    if (!getVar(in.getName(), value))
    {
        throw UndefVar(in.getLine(), in.getName());
    }
    return value;
}

int Evaluator::visit(InputAST const &in)
{
    int n;
    cin >> n;
    setVar(in.getVar(), n);
    return n;
}


int Evaluator::visit(OutputAST const &in)
{
    int n = in.getExpr()->accept(*this);
    cout << n << endl;
    return n;
}

int Evaluator::visit(VarDefAST const &in)
{
    int n = in.getExpr()->accept(*this);
    setVar(in.getName(), n);
    return n;
}

int Evaluator::visit(ReturnAST const &in)
{
    int n = in.getExpr()->accept(*this);
    throw Return(n);
}


int Evaluator::visit(CondAST const &in)
{
    int leftPart = in.getLeftP() -> accept(*this);
    int rightPart = in.getRightP() -> accept(*this);

    if (in.getOp() == "==")
        return leftPart == rightPart;
    if (in.getOp() == "!=")
        return leftPart != rightPart;
    if (in.getOp() == "<")
        return leftPart < rightPart;
    if (in.getOp() == "<=")
        return leftPart <= rightPart;
    if (in.getOp() == ">")
        return leftPart > rightPart;
    if (in.getOp() == ">=")
        return leftPart >= rightPart;
    if (in.getOp() == "+")
        return leftPart + rightPart;
    if (in.getOp() == "-")
        return leftPart - rightPart;
    if (in.getOp() == "*")
        return leftPart * rightPart;
    if (in.getOp() == "/")
    {
        if (rightPart == 0)
        {
            throw DivByZero(in.getLine());
        }
        return leftPart / rightPart;
    }

    return leftPart / rightPart;
    return 0;
}
void Evaluator::valueAcceptFunc(ExprSharedPtrs::const_iterator begin, ExprSharedPtrs::const_iterator end)
{
    for (ExprSharedPtrs::const_iterator it = begin; it != end;    ++it)
        (*it)->accept(*this);
}
int Evaluator::visit(IfAST const &in)
{
    int value = in.getCond()->accept(*this);
    if (value)
        valueAcceptFunc(in.body().begin(),in.body().end());
    return value;
}

int Evaluator::visit(WhileAST const &in)
{
    int value = 0;
    while (in.getCond()->accept(*this))
    {
        valueAcceptFunc(in.body().begin(),in.body().end());
    }
    return value;
}
int Evaluator::visit(FuncDefAST const &in)
{

    try
    {
        valueAcceptFunc(in.body().begin(),in.body().end());
    }
    catch (Return const &r)
    {
        return r.getValue();
    }

    return 0;
}
int Evaluator::visit(FuncPrAST const &in)
{
    Funcs::const_iterator it =  myFuncs.find(in.getName());
    if (it ==  myFuncs.end())
        throw UndefFunc(in.getLine(), in.getName());

    FuncSharedPtr func = it->second;
    ExprSharedPtrs args = in.getParams();
    vector<string> params = func->getParams();

    if (params.size() != args.size())
        throw ArgNumbMismatch(in.getLine(), in.getName());

    map <string, int> funcLoc;
    for (size_t i = 0; i < args.size(); ++i)
    {
        int value = (args[i])->accept(*this);
        funcLoc[params[i]] = value;
    }

    varsVocal.push(funcLoc);

    int res = func->accept(*this);
    varsVocal.pop();
    return res;
}



bool Evaluator::getVar(string const &name, int &value)
{
    if(!varsVocal.empty())
    {
        map<string, int> const & locals = varsVocal.top();
        map<string, int>::const_iterator var = locals.find(name);
        if (var != locals.end())
        {
            value = var->second;
            return true;
        }
    }

    map<string, int>::const_iterator var = varsGlob.find(name);
    if (var != varsGlob.end())
    {
        value = var->second;
        return true;
    }
    return false;

}
void Evaluator::setVar(string const &name, int value)
{
    if (!varsVocal.empty())
        varsVocal.top()[name] = value;
    else
        varsGlob[name] = value;
}


