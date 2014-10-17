#ifndef ERRORS_HPP
#define ERRORS_HPP

#include <stdexcept>
#include <string>

using std::string;

class Exception : public std::exception
{
    string msg;
    size_t erLine;
public:
    Exception(size_t inLine, string const &message) : msg(message), erLine(inLine) {}

    const char* what() const throw()
    {
        return msg.c_str();
    }
    size_t getLine() const
    {
        return erLine;
    }
    ~Exception() throw () {}
};


class ParserException : public Exception
{
    size_t erLine;
public:
    explicit ParserException(size_t inLine, string const &message) : Exception(inLine, "you have synatax error " + message) {}
};

class EvaluatorException : public Exception
{
    size_t erLine;
public:
    explicit EvaluatorException(size_t inLine, string const &message) : Exception(inLine, message)  {}
};

class DivByZero : public EvaluatorException
{
public:
    explicit DivByZero(size_t inLine) : EvaluatorException(inLine, "you have divizion by zero") {}
};

class UndefVar : public EvaluatorException
{
public:
    explicit UndefVar(size_t inLine, string const &inName) : EvaluatorException(inLine, "you have undefined variable " + inName) {}
};

class UndefFunc : public EvaluatorException
{
public:
    explicit UndefFunc(size_t inLine, string const &inName) : EvaluatorException(inLine, "you have undefined function " + inName) {}
};

class ArgNumbMismatch : public EvaluatorException
{
public:
    explicit ArgNumbMismatch(size_t inLine, string const &inName) : EvaluatorException(inLine, "arguments number mismatch for " + inName) {}
};


#endif
