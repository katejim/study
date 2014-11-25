#include "interpretator.h"
void InterpretCode::handleByteCode(){
    Instruction instruction;
    do {
        instruction = byteCode()->getInsn(position);
        position += getInstructionLength(instruction);
        switch (instruction) {
        case BC_ILOAD: case BC_DLOAD: case BC_SLOAD:
            createVar(instruction, "constant");
            break;
        case BC_LOADCTXDVAR: case BC_LOADCTXIVAR:
            loadVar(instruction);
            break;
        case BC_STORECTXDVAR: case BC_STORECTXIVAR:
            saveVar(instruction);
            break;
        case BC_IPRINT: case BC_DPRINT: case BC_SPRINT:
            printVariable(variables.top());
            variables.pop();
            break;
        default:
            break;
        }
    }while (instruction != BC_STOP);
}

void InterpretCode::loadVar(Instruction instruction){}
void InterpretCode::saveVar(Instruction instruction){
    Var var = createVar(instruction, Utils::convertToString(varNameIdx));
    //Variable mVar = std::make_pair(varNameIdx, var);
    //currentContext->variableMap.insert()
}

Var InterpretCode::createVar(Instruction instr, const string & name){
    Var var(Utils::getType(instr), name);
    switch (var.type()) {
    case VT_INT:
        var.setIntValue(byteCode()->getInt64(position - 8));
        break;
    case VT_DOUBLE:
        var.setDoubleValue(byteCode()->getDouble(position - 8));
        break;
    case VT_STRING:
        var.setStringValue(constantById(byteCode()->getInt16(position - 2)).c_str());
        break;
    default:
        break;
    }
    variables.push(var);
    return var;
}
void InterpretCode::printVariable(Var var){
    switch (var.type()) {
    case VT_INT:
        cout <<var.getIntValue() <<endl;
        break;
    case VT_DOUBLE:
        cout << var.getDoubleValue()<<endl;
        break;
    case VT_STRING:
        cout << var.getStringValue() <<endl;
        break;
    default:
        break;
    }
}


