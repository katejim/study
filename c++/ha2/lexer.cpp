#include <iostream>

#include "lexer.h"
Lexer::Lexer(string const &inFile) :  curTokenOut(0)
{
    ifstream f(inFile.c_str());
    if(!f)
        std::cout<<"no such file or wrong path"<<std::endl;
    else
        divideToTokens(f);
}

const Lexer::mapType Lexer::ident =
{
    { "def", Token :: Def },
    {"return", Token::Return},
    {"end", Token::End},
    {"print", Token::Print},
    {"read", Token::Read},
    {"while", Token::While},
    {"if", Token::If}
};

void Lexer::getIdent(string::const_iterator &it, string::const_iterator lineEnd , int lineNumber)
{
    Token::Type type;
    string IdentStr;

    while (it != lineEnd  &&  (isalpha(*it) || isdigit(*it) || *it == '_'))
        IdentStr += *(it++);

    if (ident.find(IdentStr) !=ident.end())
        type = ident.find(IdentStr) -> second;
    else
        type = Token::Identifier;

    allTokens.push_back(Token(lineNumber, type, IdentStr));
}
void Lexer::getNumber(string::const_iterator &it, string::const_iterator lineEnd , int lineNumber)
{
    string NumStr;
    while (it != lineEnd && isdigit(*it))
        NumStr += *(it++);

    allTokens.push_back(Token(lineNumber, Token::Number, NumStr));
}

void Lexer::getOperation(string::const_iterator &it, int lineNumber)
{
    string value;
    Token::Type type;
    switch (*it)
    {
    case '(':
        type = Token::Openbraket;
        value = '(';
        break;
    case ')':
        type = Token::Closebraket;
        value = ')';
        break;
    case ':':
        type = Token::Colon;
        value = ':';
        break;
    case '+':
        type = Token::Plus;
        value = '+';
        break;
    case '-':
        type = Token::Minus;
        value = '-';
        break;
    case '*':
        type = Token::Mult;
        value = '*';
        break;
    case '/':
        type = Token::Div;
        value = '/';
        break;
    case '=':
        value = *it;
        if (*(it + 1) == '=')
        {
            value += '=';
            type =  Token::Comparision;
            ++it;
        }
        else
            type =  Token::Equal;
        break;
    case '!':
        value = *it;
        if (*(it + 1) == '=')
        {
            value += '=';
            type =  Token::Comparision;
            ++it;
        }
        break;
    case '<':
    case '>':
        value = *it;
        type =  Token::Comparision;
        if (*(it + 1) == '=')
        {
            value += '=';
            ++it;
        }
        break;
    }
    ++it;
    allTokens.push_back(Token(lineNumber, type, value));
}
void Lexer::divideToTokens(ifstream &f)
{
    int lineNumber = 1;
    for (string line; getline(f, line); ++lineNumber)
    {
        string::const_iterator it = line.begin();
        string::const_iterator endLine = line.end();
        while ( it != line.end())
        {
            if(isspace(*it))
            {
                ++it;
                continue;
            }
            else if(*it == '#')
                break;

            else  if (isalpha(*it))
                getIdent(it, endLine, lineNumber);

            else if (isdigit(*it))
                getNumber(it, endLine, lineNumber );

            else
                getOperation(it,  lineNumber );

        }
        allTokens.push_back(Token(lineNumber, Token::Cr, "\n"));
    }
    allTokens.push_back(Token(lineNumber, Token::Eof, "\n"));
}

Token Lexer::getCurToken(size_t offset)
{
    if (curTokenOut + offset != (allTokens.size() - 1))
        return allTokens[curTokenOut + offset];
    else
        return allTokens[allTokens.size() - 1];
}

Token Lexer::nextToken()
{
    Token myTok = getCurToken();
    ++curTokenOut;
    return myTok;
}


