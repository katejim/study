#include "parser.h"
#include <iostream>

using namespace std;
Program Parser::parse(){

	ExprSharedPtrs content;
	Funcs funcsMap;	
	nextLine();
	while (myLexer.getCurToken().tokenType != Token::Eof) {	
		ExprSharedPtr instrunct = instruction();		
		if (instrunct) {			
			content.push_back(instrunct);
			continue;
		}

		FuncSharedPtr funct = function();						
		if (funct) {
			funcsMap[funct->getName()] = funct;
			continue;
		}		
	}
	return Program(ExprSharedPtr (new FuncDefAST("",vector<string>(), content, myLexer.getCurToken().tokenLine)), funcsMap);
}
void Parser::nextLine(){
	while (myLexer.getCurToken().tokenType == Token::Cr)
			myLexer.nextToken();	
}

ExprSharedPtr Parser::instruction() {		
	ExprSharedPtr result = input();
	if (!result)
		result = output();

	if (!result)
		result = varDef();

	if (!result)
		result = expression();

	if (!result)
		result = ifP();

	if (!result)
		result = whileP();

	if (!result)
		result = returnP();

	if (result) 
		nextLine();
	return result;
}
ExprSharedPtr Parser::number(){
	if (myLexer.getCurToken().tokenType != Token::Number)
		return ExprSharedPtr();
	Token const &t = myLexer.nextToken();

	return ExprSharedPtr(new NumAST(atoi(t.tokenValue.c_str()), t.tokenLine));
}

ExprSharedPtr Parser::input(){
	if (myLexer.getCurToken().tokenType != Token::Read)
		return ExprSharedPtr();
	myLexer.nextToken();
	Token const &t = myLexer.nextToken();
	if (t.tokenType != Token::Identifier)
		throw ParserException(t.tokenLine, " wrong type of input value + " + t.tokenValue);
	return ExprSharedPtr(new InputAST(t.tokenValue, t.tokenLine));
}

ExprSharedPtr Parser::output(){
	if (myLexer.getCurToken().tokenType != Token::Print)
		return ExprSharedPtr();

	Token const &t = myLexer.getCurToken();
	myLexer.nextToken();

	
	ExprSharedPtr expr = expression();
	if (!expr)
		throw ParserException(t.tokenLine, " because mismatch output expression");
	return ExprSharedPtr(new OutputAST(expr, t.tokenLine));
}
ExprSharedPtr Parser::primary() {
	ExprSharedPtr  leftPart = value();
	if (!leftPart)
		return ExprSharedPtr();

	if (myLexer.getCurToken().tokenType != Token::Div && myLexer.getCurToken().tokenType != Token::Mult)
		return  leftPart;
	Token const &t = myLexer.getCurToken();
	Token::Type operation = myLexer.nextToken().tokenType;
	ExprSharedPtr rightPart = primary();
	if (!rightPart)
		throw ParserException(t.tokenLine, " because mismatch right part after " );

	string op;
	switch (operation) {
		case Token::Div:
			op = '/';
			break;
		case Token::Mult:
			op = '*';
			break;
		default:
			throw ParserException(myLexer.getCurToken().tokenLine, " because wrong opertaion ");
	}
	return ExprSharedPtr(new CondAST(op, leftPart, rightPart, t.tokenLine));
}

ExprSharedPtr Parser::expression(){
	ExprSharedPtr  leftPart = primary();
	if (! leftPart)
		return ExprSharedPtr();

	if (myLexer.getCurToken().tokenType != Token::Plus && myLexer.getCurToken().tokenType != Token::Minus)
		return  leftPart;
	Token const &t = myLexer.getCurToken();

	Token::Type operation = myLexer.nextToken().tokenType;
	ExprSharedPtr rightPart = expression();
	if (!rightPart)
		throw ParserException(t.tokenLine, " because you haven't got right part of expression");

	string op;
	switch (operation) {
		case Token::Plus:
			op = '+';
			break;
		case Token::Minus:
			op = '-';
			break;
		default:
			throw ParserException(myLexer.getCurToken().tokenLine, " because wrong operation " + operation);
	}
	return ExprSharedPtr(new CondAST(op,  leftPart, rightPart, t.tokenLine));
}


ExprSharedPtr Parser::value(){	
	if (myLexer.getCurToken().tokenType == Token::Minus) {
		myLexer.nextToken(); 
		ExprSharedPtr val = value();
		if (!val)
			throw ParserException(myLexer.getCurToken().tokenLine, " because value after operaton - mismatch");
		ExprSharedPtr zero(new NumAST(0, 0));
		string op ;
		op = '-';
		return ExprSharedPtr(new CondAST(op, zero, val, myLexer.getCurToken().tokenLine));
	}

	
	if (myLexer.getCurToken().tokenType == Token::Openbraket) {
		myLexer.nextToken();
		ExprSharedPtr expr = expression();
		if (!expr || myLexer.nextToken().tokenType != Token::Closebraket)
			throw ParserException(myLexer.getCurToken().tokenLine, " because expression or close braket missmatch");
		return expr;
	}

	ExprSharedPtr val = number();
	if (!val)
		val = identifier();
	return val;
}

ExprSharedPtr Parser::identifier(){	
	if (myLexer.getCurToken().tokenType != Token::Identifier)
		return ExprSharedPtr();
	Token const &t = myLexer.getCurToken();
	string name = myLexer.nextToken().tokenValue;
	
	
	if (myLexer.getCurToken().tokenType != Token::Openbraket)
		return ExprSharedPtr(new VarAST(name, t.tokenLine));

	
	myLexer.nextToken();
	ExprSharedPtrs params;

	if (myLexer.getCurToken().tokenType != Token::Closebraket) {	
		while (true) {
			ExprSharedPtr param = expression();
			if (!param)
				throw ParserException(t.tokenLine," because params are mismatch ");
			params.push_back(param);
			
			if (myLexer.getCurToken().tokenType == Token::Comma)
				myLexer.nextToken();
			else
				break;
		}
		if (myLexer.getCurToken().tokenType != Token::Closebraket)
			throw ParserException(t.tokenLine," because close braket missmatch");		
	}
	myLexer.nextToken();
	return ExprSharedPtr(new FuncPrAST(name, params, t.tokenLine));
}

ExprSharedPtr Parser::varDef(){    
	if (myLexer.getCurToken().tokenType != Token::Identifier || myLexer.getCurToken(1).tokenType != Token::Equal)
		return ExprSharedPtr();
	
	string name = myLexer.nextToken().tokenValue;
	
	if (myLexer.nextToken().tokenType != Token::Equal)
		throw ParserException(myLexer.getCurToken().tokenLine, " because  = missmatch");

	ExprSharedPtr expr = expression();
	if (!expr)
		throw ParserException(myLexer.getCurToken().tokenLine, " because  missmatch definition to variable after = ");

	return ExprSharedPtr(new VarDefAST(name, expr, myLexer.getCurToken().tokenLine));
}

ExprSharedPtr Parser::cond(){
	ExprSharedPtr left = expression();
	if (!left)
		return ExprSharedPtr();

	Token const &compToken = myLexer.nextToken(); 
	if (compToken.tokenType != Token::Comparision)
		throw ParserException(compToken.tokenLine, " because incorrect inequality " + compToken.tokenValue);

	ExprSharedPtr right = expression();
	if (!right)
		throw ParserException(compToken.tokenLine, " because missmatch right part of condition");

	return ExprSharedPtr(new CondAST(compToken.tokenValue, left, right, compToken.tokenLine));
}

ExprSharedPtr Parser::ifP(){	
	if (myLexer.getCurToken().tokenType != Token::If)
		return ExprSharedPtr();
	myLexer.nextToken(); 

	ExprSharedPtr condition = cond();

	if (!condition)
		throw ParserException(myLexer.getCurToken().tokenLine, " because condition mismatch");
	else if (myLexer.nextToken().tokenType != Token::Colon)
		throw ParserException(myLexer.getCurToken().tokenLine, " because mismatch : ");
	nextLine();

	ExprSharedPtrs content;
	while (myLexer.getCurToken().tokenType != Token::End) {
		ExprSharedPtr instruct = instruction();
		if (!instruct)
			throw ParserException(myLexer.getCurToken().tokenLine, " mismatch instruction after if condition");
		content.push_back(instruct);
	}
	myLexer.nextToken(); 

	return ExprSharedPtr(new IfAST(condition, content, myLexer.getCurToken().tokenLine));
}

ExprSharedPtr Parser::whileP() {
	if (myLexer.getCurToken().tokenType != Token::While)
		return ExprSharedPtr();
	myLexer.nextToken();

	ExprSharedPtr condition = cond();
	if (!condition)
		throw ParserException(myLexer.getCurToken().tokenLine, " because condition mismatch");
	else if (myLexer.nextToken().tokenType != Token::Colon)
		throw ParserException(myLexer.getCurToken().tokenLine, " because mismatch : ");

	nextLine();
	ExprSharedPtrs content;
	while (myLexer.getCurToken().tokenType != Token::End) {
		ExprSharedPtr instruct = instruction();
		if (!instruct)
			throw ParserException(myLexer.getCurToken().tokenLine, " because mismatch instruction after while");
		content.push_back(instruct);
	}
	myLexer.nextToken(); 
	return ExprSharedPtr(new WhileAST(condition, content, myLexer.getCurToken().tokenLine));
}

ExprSharedPtr Parser::returnP(){
	if (myLexer.getCurToken().tokenType != Token::Return)
		return ExprSharedPtr();
	myLexer.nextToken(); 

	ExprSharedPtr expr = expression();
	if (!expr)
		throw ParserException(myLexer.getCurToken().tokenLine, " because nothing to return");
	return ExprSharedPtr(new ReturnAST(expr, myLexer.getCurToken().tokenLine));
}

FuncSharedPtr Parser::function(){
	if (myLexer.getCurToken().tokenType != Token::Def)
		return FuncSharedPtr();

	myLexer.nextToken();
	Token const &t = myLexer.nextToken();
	if (t.tokenType != Token::Identifier)
		throw ParserException(myLexer.getCurToken().tokenLine, " because wrong type of function name " + t.tokenValue);
	else if (myLexer.nextToken().tokenType != Token::Openbraket){std::cout<<myLexer.getCurToken().tokenValue;
		throw ParserException(myLexer.getCurToken().tokenLine, " because mismatch ( after function's name");}

	vector<string> params;
	if (myLexer.getCurToken().tokenType != Token::Closebraket) {		
		while (true) {
			Token const &param = myLexer.nextToken();
			if (param.tokenType != Token::Identifier)
				throw ParserException(myLexer.getCurToken().tokenLine, " because wrong type of function parametr " + param.tokenValue);
			params.push_back(param.tokenValue);
			
			if (myLexer.getCurToken().tokenType == Token::Comma)
				myLexer.nextToken(); 
			else
				break;
		}
		if (myLexer.getCurToken().tokenType != Token::Closebraket)
			throw ParserException(myLexer.getCurToken().tokenLine, " because mismatch ) after function's params");
	}
	myLexer.nextToken(); 

	if (myLexer.nextToken().tokenType != Token::Colon)
		throw ParserException(myLexer.getCurToken().tokenLine, " because : mismatch");
	

	nextLine();

	ExprSharedPtrs content;
	while (myLexer.getCurToken().tokenType != Token::End) {
		ExprSharedPtr instruct = instruction();
		if (!instruct)
			throw ParserException(myLexer.getCurToken().tokenLine, " missmatch instruction in function body" + myLexer.getCurToken().tokenValue);
		content.push_back(instruct);
	}
	myLexer.nextToken(); 

	nextLine();

	return FuncSharedPtr(new FuncDefAST(t.tokenValue, params, content, t.tokenLine));
}
