#ifndef LEXER_HPP
#define LEXER_HPP

#include <string>
#include <vector>
#include <fstream>
#include <map>

using std::string;
using std::vector;
using std::ifstream;
using std::map;



struct Token
{
    enum Type
    {
        Eof,	Cr,
        Def,
        Plus,  Minus, Mult, Div,  Equal,
        Return,  End,  While, If, Comparision,
        Comma, Colon, Openbraket,	 Closebraket,
        Print, Read,
        Identifier,	 Number
    };
    Token(int inLine, Token::Type inType, string inValue) :   tokenLine(inLine),
        tokenType(inType),  tokenValue(inValue) {}
    int  tokenLine;
    Type  tokenType;
    string  tokenValue;
};

class Lexer
{
    int curTokenOut;
    vector <Token> allTokens;
    void divideToTokens(ifstream &);
    Lexer &operator=(Lexer const &);
    Lexer(Lexer const &other);
    void getIdent(string::const_iterator &, string::const_iterator , int);
    void getNumber(string::const_iterator &, string::const_iterator , int);
    void getOperation(string::const_iterator &, int);
    typedef map<string, Token::Type> mapType;



public:
    Lexer(string const &sourceFileName);
    Token getCurToken(size_t offset = 0);
    Token nextToken();
    static const mapType ident;



};


#endif
