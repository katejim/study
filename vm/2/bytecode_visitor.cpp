#include "bytecode_visitor.h"
int16_t ByteCodeVisitor::funcIdx = 0;

int16_t ByteCodeVisitor::currentContext = 0;
pair<int16_t, int16_t> ByteCodeVisitor::getVarIdx(Context * context, string varName) const {
    while (context->variableMap.find(varName) ==  context->variableMap.end())
        context = context->parent;

    return make_pair(context->idx, context->variableMap[varName].first);
}

int ByteCodeVisitor::getFuncIdx(Context * context, string varName) const {

    if (context == NULL){
        cout << "WRONG SCOPE IDX Function" << endl;
        return 0;
    }
    FunctionMap currentMap = context->functionMap;
    return currentMap[varName];
}


VarType ByteCodeVisitor::getTypeToBinOperation(VarType left, VarType right){
    if ((left==VT_INVALID) || (right==VT_INVALID)){
        return VT_INVALID;
    } else if(left == right){
        return left;
    } else if (left != right){
        return VT_DOUBLE;
    }
    return VT_INVALID;
}


void ByteCodeVisitor::visitBinaryOpNode(BinaryOpNode * node)
{
    VarType resParamLeft, resParamRight;
    node->left()->visit(this);
    resParamLeft = resultType;
    node->right()->visit(this);
    resParamRight = resultType;
    resultType = getTypeToBinOperation(resParamLeft, resParamRight);

    //convert to common cast
    if (resParamRight != resultType)
        typeConverter(resultType, resParamRight);
    if (resParamLeft != resultType){
        byteCode()->addInsn(BC_SWAP);
        typeConverter(resultType, resParamLeft);
        byteCode()->addInsn(BC_SWAP);
    }

    switch (node->kind()) {
    case tADD: case tSUB: case tMUL: case tDIV: case tMOD:
    case tAAND: case tAOR: case tAXOR: case tRANGE:
        arithOperation(node->kind(), resultType);
        break;
    case tAND: case tOR:
        logicalOperations(node->kind(), resultType);
        break;
    case tGT: case tGE: case tLT: case tLE: case tEQ: case tNEQ:
        comparateOperation(node->kind(), resultType);
        break;
    default:
        break;
    }
}

void ByteCodeVisitor::visitUnaryOpNode(UnaryOpNode * node)
{
    node->operand()->visit(this);
    switch (node->kind()) {
    case tSUB: case tNOT:
        unaryOperations(resultType, node->kind());
        break;
    default:
        break;
    }

}

void ByteCodeVisitor::visitDoubleLiteralNode(DoubleLiteralNode * node)
{
    resultType = VT_DOUBLE;
    pushDoubleOnStack(node->literal());
}

void ByteCodeVisitor::visitIntLiteralNode(IntLiteralNode * node)
{
    resultType = VT_INT;
    pushIntOnStack(node->literal());
}

void ByteCodeVisitor::visitStringLiteralNode(StringLiteralNode * node)
{
    resultType = VT_STRING;

}

void ByteCodeVisitor::visitLoadNode(LoadNode * node)
{
    resultType = node->var()->type();
    loadValueToStack(resultType, getVarIdx(current, node->var()->name()));
}

void ByteCodeVisitor::visitStoreNode(StoreNode * node)
{
    resultType = node->var()->type();
    VarType needType = resultType;
    node->value()->visit(this);
    VarType currentType = resultType;
    storeValueFromStack(node->op(), needType, currentType, getVarIdx(current, node->var()->name()));
}


void ByteCodeVisitor::visitPrintNode(PrintNode * node)
{
    for(int i = 0; i != node->operands(); ++i)
    {
        node ->operandAt(i)->visit(this);
        printValueFromStack(resultType);
    }
}

// so if we haven't variable or functions in scope - we will put
// empty map
void ByteCodeVisitor::visitBlockNode(BlockNode * node)
{
    for (uint32_t i = 0; i < node->nodes(); i++)
    {
        if (node->nodeAt(i)->isCallNode())
        {

            CallNode * cn = (CallNode*)node->nodeAt(i);
            node->nodeAt(i)->visit(this);
            int16_t idx = getFuncIdx(current, cn->name());
            if (nativeFunctions.find(idx) != nativeFunctions.end())
                call(idx, true);
            else
                call(idx, false);
        }
        else
            node->nodeAt(i)->visit(this);
    }
}




//native
void ByteCodeVisitor::visitFunctionNode(FunctionNode * node)
{
    bool native = false;
    if (node->body()->nodeAt(0)->isNativeCallNode()){
        native = true;
        int16_t idx = getFuncIdx(current, node->name());
        nativeFunctions.insert(make_pair(idx, native));
    }

    //function in which we are to write bytecode
    currenFunction = (BytecodeFunction*) code->functionById(getFuncIdx(current, node->name()));

    Context * newContext = new Context(currentContext++, current);
    newContext->name = node->name();
    current = newContext;


    VariableMap varMap;
    //add signature
    int16_t varIndx = 0;

    //first variables is signature vars
    if (node->signature().size() > 1){
        int size = node->signature().size();
        for (int i = size - 1; i != 0; --i){
            SignatureElement elem = node->signature().at(i);
            if (!native){
                storeValueFromStack(tASSIGN, elem.first, elem.first, make_pair(current->idx, varIndx));
                Variable variable = make_pair(varIndx++, elem.first);
                varMap.insert(make_pair(elem.second, variable));
            }
        }
    }

    if (!native){
        Scope::VarIterator it(node->body()->scope());
        while(it.hasNext())
        {
            AstVar * var = it.next();
            Variable variable = make_pair(varIndx++, var->type());
            varMap.insert(make_pair(var->name(), variable));
        }

        allVariables.push_back(varMap);
        newContext->variableMap = varMap;

        Scope::FunctionIterator itf(node->body()->scope());
        FunctionMap funcMap;

        while(itf.hasNext())
        {
            AstFunction * fn = itf.next();
            BytecodeFunction *bcFn = new BytecodeFunction(fn);
            code->addFunction(bcFn);
            funcMap.insert(make_pair(fn->name(), funcIdx++));
        }
        allFunctions.push_back(funcMap);
        newContext->functionMap = funcMap;

        Scope::FunctionIterator itff(node->body()->scope());
        while(itff.hasNext()){
            AstFunction * fn = itff.next();
            fn->node()->visit(this);
        }
        node->body()->visit(this);
    }

    current = current->parent;
    currenFunction = (BytecodeFunction*) code->functionById(getFuncIdx(current->parent, current->name));
    resultType = node->returnType();
}

void ByteCodeVisitor::visitCallNode(CallNode * node)
{
    int16_t idx  = getFuncIdx(current, node->name());
    BytecodeFunction *bcF = (BytecodeFunction*) code->functionById(idx);
    for(uint32_t i = 0; i < node->parametersNumber(); i++)
    {
        node->parameterAt(i)->visit(this);
        VarType curType = bcF->signature().at(i + 1).first;
        if (resultType != curType){
            typeConverter(resultType, curType);
        }
    }
}

void ByteCodeVisitor::visitWhileNode(WhileNode * node)
{
    Label inM(byteCode()), endM(byteCode());
    byteCode()->bind(inM);

    node ->whileExpr()->visit(this);
    byteCode()->addInsn(zeroMap[VT_INT]);
    byteCode()->addBranch(BC_IFICMPE, endM);

    node->loopBlock()->visit(this);

    byteCode()->addBranch(BC_JA, inM);
    byteCode()->bind(endM);
}

//incremented var save on VARO
//initially  = range_low
//range_high on VAR1
//suppose that variables don't clear after pushing on stack
void ByteCodeVisitor::visitForNode(ForNode * node)
{
    Label inM(byteCode()), endM(byteCode());

    node->inExpr()->visit(this);

    byteCode()->bind(inM);

    byteCode()->addInsn(BC_LOADIVAR1);
    byteCode()->addInsn(BC_LOADIVAR0);

    byteCode()->addBranch(BC_IFICMPG, endM);

    node->body()->visit(this);

    //increment var
    byteCode()->addInsn(BC_LOADDVAR0);
    byteCode()->addInsn(unitMap[VT_INT]);
    byteCode()->addInsn(BC_IADD);
    byteCode()->addInsn(BC_STOREIVAR0);

    byteCode()->addBranch(BC_JA, inM);
    byteCode()->bind(endM);
}

//TODO new scope - maybe add to block node
void ByteCodeVisitor::visitIfNode(IfNode * node)
{
    Label elseM(byteCode()), endM(byteCode());

    //return 0 1 or -1
    node->ifExpr()->visit(this);

    byteCode()->addInsn(zeroMap[VT_INT]);

    //suppose that BC_IFICMPE pop values from stack
    byteCode()->addBranch(BC_IFICMPE, elseM);

    node->thenBlock()->visit(this);

    if (node->elseBlock() != NULL)
        byteCode()->addBranch(BC_JA, endM);

    byteCode()->bind(elseM);
    if(node->elseBlock() != NULL)
    {
        node->elseBlock()->visit(this);
    }
    byteCode()->bind(endM);
}



void ByteCodeVisitor::visitReturnNode(ReturnNode * node)
{
    if (node->returnExpr() != NULL)
    {
        node->returnExpr()->visit(this);
    }
    byteCode()->addInsn(BC_RETURN);
}



void ByteCodeVisitor::visitTop()
{
    BytecodeFunction *bcFn = new BytecodeFunction(top);
    code->addFunction(bcFn);
    VariableMap map;
    allVariables.push_back(map);
    FunctionMap fm;
    fm.insert(make_pair(top->node()->name(), funcIdx++));
    allFunctions.push_back(fm);
    Context * topContext = new Context(currentContext++, map, fm, NULL);
    topContext->name = top->node()->name();
    current = topContext;
    top->node()->visit(this);
    byteCode()->addInsn(BC_STOP);
}


void ByteCodeVisitor::typeConverter(VarType typeOut, VarType curType){
    if (typeOut != curType){
        if ((typeOut == VT_DOUBLE) && (curType == VT_INT)){
            byteCode()->addInsn(BC_I2D);
        } else if ((typeOut == VT_INT) && (curType == VT_DOUBLE)){
            byteCode()->addInsn(BC_D2I);
        } else if ((typeOut == VT_INT) && (curType == VT_STRING)){
            byteCode()->addInsn(BC_S2I);
        } else if ((typeOut == VT_DOUBLE) && (curType == VT_STRING)){
            byteCode()->addInsn(BC_S2I);
            byteCode()->addInsn(BC_I2D);
        }
    }
}
void ByteCodeVisitor::pushDoubleOnStack(double value){
    byteCode()->addInsn(BC_DLOAD);
    byteCode()->addDouble(value);
}

void ByteCodeVisitor::pushIntOnStack(int value){
    byteCode()->addInsn(BC_ILOAD);
    byteCode()->addInt64(value);
}


//TODO handle VT_INVALID
void ByteCodeVisitor::storeValueFromStack(TokenKind kind, VarType typeOut, VarType curType, pair<int16_t, int16_t> var){
    typeConverter(typeOut, curType);
    if (kind == tINCRSET){
        loadValueToStack(typeOut, var);
        arithOperation(tADD, typeOut);
    } else if (kind == tDECRSET){
        loadValueToStack(typeOut, var);
        byteCode()->addInsn(subMap[typeOut]);
    }

    byteCode()->addInsn(storeMap[typeOut]);
    byteCode()->addInt16(var.first);
    byteCode()->addInt16(var.second);
}

void ByteCodeVisitor::loadValueToStack(VarType typeOut, pair<int16_t, int16_t> var){
    byteCode()->addInsn(loadMap[typeOut]);
    byteCode()->addInt16(var.first);
    byteCode()->addInt16(var.second);
}

void ByteCodeVisitor::printValueFromStack(VarType typeOut){
    byteCode()->addInsn(printMap[typeOut]);
}


//addNatioveCall
void ByteCodeVisitor::call(int16_t idx, bool native){
    if (native)
        byteCode()->addInsn(BC_CALLNATIVE);
    else
        byteCode()->addInsn(BC_CALL);
    byteCode()->addInt16(idx);
}
//TODO VT_INVALID MOD NOT INT
void ByteCodeVisitor::arithOperation(TokenKind kind, VarType resultType){
    switch (kind) {
    case tADD:
        byteCode()->addInsn(sumMap[resultType]);
        break;
    case tSUB:
        byteCode()->addInsn(BC_SWAP);
        byteCode()->addInsn(subMap[resultType]);
        break;
    case tMUL:
        byteCode()->addInsn(multMap[resultType]);
        break;
    case tDIV:
        byteCode()->addInsn(BC_SWAP);
        byteCode()->addInsn(divMap[resultType]);
        break;
    case tMOD:
        if (resultType != VT_INT)
            cout << "WRONG TYPE";
        byteCode()->addInsn(BC_SWAP);
        byteCode()->addInsn(BC_IMOD);
        break;
    case tAAND:
        if (resultType != VT_INT)
            cout << "WRONG TYPE";
        byteCode()->addInsn(BC_IAAND);
        break;
    case tAOR:
        if (resultType != VT_INT)
            cout << "WRONG TYPE";
        byteCode()->addInsn(BC_IAOR);
        break;
    case tAXOR:
        if (resultType != VT_INT)
            cout << "WRONG TYPE";
        byteCode()->addInsn(BC_IAXOR);
        break;
    case tRANGE:
        if (resultType != VT_INT)
            cout << "WRONG TYPE";
        byteCode()->addInsn(BC_STOREIVAR1);
        byteCode()->addInsn(BC_STOREIVAR0);
    default:
        break;
    }
}

void ByteCodeVisitor::logicalOperations(TokenKind kind, VarType resType){
    byteCode()->addInsn(zeroMap[resType]);
    byteCode()->addInsn(compareMap[resType]);
    byteCode()->addInsn(BC_SWAP);
    byteCode()->addInsn(zeroMap[resType]);
    byteCode()->addInsn(compareMap[resType]);
    switch (kind) {
    case tAND:
        byteCode()->addInsn(BC_IAAND);
        break;
    case tOR:
        byteCode()->addInsn(BC_IAOR);
        break;
    default:
        break;
    }
}

//expected that cmp return
// -1 a < b
// 0  a == b
// 1  a > b
void ByteCodeVisitor::comparateOperation(TokenKind kind, VarType resType){
    byteCode()->addInsn(BC_SWAP);
    byteCode()->addInsn(compareMap[resType]);
    switch (kind) {
    case tEQ:
        unaryOperations(resType, tNOT);
        break;
    case tNEQ:
        break;
    case tGT:
        byteCode()->addInsn(unitMap[resType]);
        byteCode()->addInsn(subMap[resType]);
        unaryOperations(resType, tNOT);
        break;
    case tLE:
        byteCode()->addInsn(unitMap[resType]);
        byteCode()->addInsn(subMap[resType]);
        break;
    case tGE:
        byteCode()->addInsn(unitMap[resType]);
        byteCode()->addInsn(sumMap[resType]);
    case tLT:
        byteCode()->addInsn(unitMap[resType]);
        byteCode()->addInsn(sumMap[resType]);
        unaryOperations(resType, tNOT);
        break;
    default:
        break;
    }
}
void ByteCodeVisitor::unaryOperations(VarType resultType, TokenKind kind){
    switch (kind) {
    case tSUB:
        byteCode()->addInsn(zeroMap[resultType]);
        byteCode()->addInsn(subMap[resultType]);
        break;
        //0 -> 1
        // !0 -> 0
    case tNOT:
        byteCode()->addInsn(negateMap[resultType]);
    default:
        break;
    }
}

void ByteCodeVisitor::initMaps(){
    loadMap[VT_INT] = BC_LOADCTXIVAR;
    loadMap[VT_DOUBLE] = BC_LOADCTXDVAR;
    loadMap[VT_STRING] = BC_LOADCTXSVAR;

    storeMap[VT_INT] = BC_STORECTXIVAR;
    storeMap[VT_DOUBLE] = BC_STORECTXDVAR;
    storeMap[VT_STRING] = BC_STORECTXSVAR;

    printMap[VT_INT] = BC_IPRINT;
    printMap[VT_DOUBLE] = BC_DPRINT;
    printMap[VT_STRING] = BC_SPRINT;

    sumMap[VT_INT] = BC_IADD;
    sumMap[VT_DOUBLE] = BC_DADD;

    subMap[VT_INT] = BC_ISUB;
    subMap[VT_DOUBLE] = BC_DSUB;

    multMap[VT_INT] = BC_IMUL;
    multMap[VT_DOUBLE] = BC_DMUL;

    zeroMap[VT_INT] = BC_ILOAD0;
    zeroMap[VT_DOUBLE] = BC_DLOAD0;
    zeroMap[VT_STRING] = BC_SLOAD0;

    unitMap[VT_INT] = BC_ILOAD1;
    unitMap[VT_DOUBLE] = BC_DLOAD1;


    uunitMap[VT_INT] = BC_ILOADM1;
    uunitMap[VT_DOUBLE] = BC_DLOADM1;

    negateMap[VT_INT] = BC_INEG;
    negateMap[VT_DOUBLE] = BC_DNEG;

    compareMap[VT_INT] = BC_ICMP;
    compareMap[VT_DOUBLE] = BC_DCMP;
}
