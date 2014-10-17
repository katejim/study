#ifndef PARSER_HPP
#define PARSER_HPP

#include <string>
#include <map>

#include "errors.h"
#include "lexer.h"
#include "ast.h"

using std::string;
using std::map;
using std::vector;

typedef map <string, FuncSharedPtr> Funcs;

struct Program
{
    Program(ExprSharedPtr const &ep, Funcs const &funcs) : entry(ep), Functions(funcs) {}
    ExprSharedPtr entry;
    Funcs Functions;
};

class Parser
{
public:
    Parser( string const &inFile) : myLexer(inFile)   { }
    Program parse();
private:
    Lexer myLexer;
    void nextLine();
    Parser(Parser const &);
    Parser &operator=(Parser const &);

    ExprSharedPtr instruction();
    ExprSharedPtr primary();
    ExprSharedPtr identifier();
    ExprSharedPtr number();
    ExprSharedPtr value();
    ExprSharedPtr input();
    ExprSharedPtr output();
    ExprSharedPtr varDef();
    ExprSharedPtr expression();
    ExprSharedPtr cond();
    ExprSharedPtr ifP();
    ExprSharedPtr whileP();
    ExprSharedPtr returnP();
    FuncSharedPtr function();
};


#endif
