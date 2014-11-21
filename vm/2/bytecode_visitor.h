#ifndef BYTECODE_VISITOR_H
#define BYTECODE_VISITOR_H
#include "mathvm.h"
#include "parser.h"
#include "visitors.h"
#include "generatebytecode.h"
#include "context.h"
#include "errors.h"
#include <iostream>
#include <map>

using namespace mathvm;
using std::ostream;
using std::cout;
using std::map;
using std::make_pair;

class KatyaCode : public Code{
public:
    KatyaCode(){}
    virtual Status* execute(vector<Var*>& vars) {
        return Status::Ok();
    }
};

class ByteCodeVisitor: public AstVisitor
{
    typedef pair<int16_t, VarType> Variable;
    typedef map <string, Variable> VariableMap;

    //with unique id to all program
    typedef bool native;
    typedef map <string, int16_t> FunctionMap;
    typedef map <int16_t, native> Function;

public:
    ByteCodeVisitor(AstFunction * top_m):top(top_m),  level(-1), code(new KatyaCode)
    {
        initMaps();
        visitTop();
    }
    //TODO implements macros
    void visitBinaryOpNode(BinaryOpNode * node);
    void visitUnaryOpNode(UnaryOpNode * node);
    void visitDoubleLiteralNode(DoubleLiteralNode * node);
    void visitIntLiteralNode(IntLiteralNode * node);
    void visitStringLiteralNode(StringLiteralNode * node);
    void visitLoadNode(LoadNode * node);
    void visitStoreNode(StoreNode * node);
    void visitPrintNode(PrintNode * node);
    void visitBlockNode(BlockNode * node);
    void visitReturnNode(ReturnNode *node);

    //native
    void visitFunctionNode(FunctionNode * node);
    void visitTop();

    void visitCallNode(CallNode * node);

    void visitIfNode(IfNode * node);
    void visitWhileNode(WhileNode *node);
    void visitForNode(ForNode *node);

    Code * getCode(){
        return code;
    }

private:
    BytecodeFunction * currenFunction;
    vector <VariableMap> allVariables;
    vector <FunctionMap> allFunctions;
    static int16_t funcIdx;
    Function nativeFunctions;


    VarType resultType;

    pair<int16_t, int16_t> getVarIdx(Context * context, string varName) const;
    int getFuncIdx(Context * context, string varName) const;

    VarType getTypeToBinOperation(VarType left, VarType right);

    ostream & result = std::cout;
    AstFunction * top;
    size_t level;
    Code * code;
    Context *context = NULL, *current;
    static int16_t currentContext;

private:
    void typeConverter(VarType typeOut, VarType curType);
    void pushDoubleOnStack(double value);
    void pushIntOnStack(int value);
    //TODO handle VT_INVALID
    void storeValueFromStack(TokenKind kind, VarType typeOut, VarType curType, pair<int16_t, int16_t> var);
    void loadValueToStack(VarType typeOut, pair<int16_t, int16_t> var);
    void printValueFromStack(VarType typeOut);
    //TODO VT_INVALID MOD NOT INT
    void arithOperation(TokenKind kind, VarType resultType);
    void unaryOperations(VarType resultType, TokenKind kind);
    void logicalOperations(TokenKind kind, VarType resultType);
    void comparateOperation(TokenKind kind, VarType resultType);
    void call(int16_t idx, bool native);
    Bytecode *byteCode() {
        return currenFunction->bytecode();
    }

    map <VarType, Instruction> loadMap;
    map <VarType, Instruction> storeMap;
    map <VarType, Instruction> printMap;
    map <VarType, Instruction> sumMap;
    map <VarType, Instruction> subMap;
    map <VarType, Instruction> multMap;
    map <VarType, Instruction> divMap;
    map <VarType, Instruction> zeroMap;
    map <VarType, Instruction> negateMap;
    map <VarType, Instruction> unitMap;
    map <VarType, Instruction> uunitMap;
    map <VarType, Instruction> compareMap;

    void initMaps();
};

Status *BytecodeTranslatorImpl::translate(const string & program,
                                          Code **code){
    Parser parser;
    Status * status = parser.parseProgram(program);
    if (status && status->isError()) return status;

    ByteCodeVisitor result(parser.top());
    *code = result.getCode();

    Code::FunctionIterator it(*code);
    while(it.hasNext()){
        BytecodeFunction *bcF = (BytecodeFunction*)it.next();
        cout << endl<<"nameF = " <<bcF->name() << endl;
        bcF->bytecode()->dump(cout);
        cout << endl;
    }
    return Status::Ok();
}

Translator * Translator::create(const string & impl)
{
    if (impl == "intepreter")
    {
        return new BytecodeTranslatorImpl();
    }
    else
    {
        return NULL;
    }
}


#endif // BYTECODE_VISITOR_H
