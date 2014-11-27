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
        case BC_ILOAD0: case BC_ILOAD1: case BC_ILOADM1:
        case BC_DLOAD0: case BC_DLOAD1: case BC_DLOADM1:
        case BC_SLOAD0:
            createDefaultVar(instruction);
            break;
        case BC_DADD: case BC_DSUB: case BC_DMUL: case BC_DDIV:
            mathOperation<double>(instruction);
            break;
        case BC_IADD: case BC_ISUB: case BC_IMUL: case BC_IDIV: case BC_IMOD:
            mathOperation<int>(instruction);
            break;
        case BC_SWAP:
            swapValues();
            break;
        case BC_ICMP:
            compareVar<int>(instruction);
            break;
        case BC_DCMP:
            compareVar<int>(instruction);
        case BC_JA:
            position = byteCode()->getInt64(position - 2);
            break;
        default:
            break;
        }
    }while (instruction != BC_STOP);
}

void InterpretCode::loadVar(Instruction instruction){
    int16_t idxContext = byteCode()->getInt16(position-4);
    int16_t idxVar = byteCode()->getInt16(position-2);
    Var var = getVariable(getContext(idxContext), idxVar);
    variables.push(var);
}

void InterpretCode::saveVar(Instruction instruction){
    int16_t idxContext = byteCode()->getInt16(position-4);
    int16_t idxVar = byteCode()->getInt16(position-2);
    Context * context = createContext(idxContext);
    Context::VariableMap mMap = context->variableMap;

    Var var = variables.top();
    variables.pop();

    Context::Variable mVar = std::make_pair(idxVar, var);

    mMap.insert(std::make_pair(Utils::convertToString(idxVar), mVar));

    context->variableMap = mMap;
}

void InterpretCode::handleTopValue(Instruction instruction){
    Var doubleV(VT_DOUBLE, "double");
    Var intV(VT_INT, "int");

    Var var = variables.top();
    variables.pop();
    switch (instruction) {
    case BC_I2D:
        doubleV.setDoubleValue((double)getValue<double>(var));
        variables.push(doubleV);
        break;
    case BC_D2I:
        intV.setIntValue((int)getValue<int>(var));
        variables.push(intV);
        break;
    case BC_DNEG:
        if (doubleV.getDoubleValue() == 0.0)
            doubleV.setDoubleValue(0.0);
        else
            doubleV.setDoubleValue(1.0);
        variables.push(doubleV);
        break;
    case BC_INEG:
        if (intV.getIntValue() == 0)
            intV.setIntValue(0);
        else
            intV.setIntValue(1);
        variables.push(intV);
        break;
    default:
        break;
    }
}
void InterpretCode::swapValues(){
    if (variables.size() < 2)
        assert(variables.size() > 2);
    Var var1 = variables.top();
    variables.pop();
    Var var2 = variables.top();
    variables.pop();
    variables.push(var1);
    variables.push(var2);
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
        cout <<var.getIntValue();
        break;
    case VT_DOUBLE:
        cout << var.getDoubleValue();
        break;
    case VT_STRING:
        cout << var.getStringValue();
        break;
    default:
        break;
    }
}

void InterpretCode::handleAdditionVars(Instruction instruction){
    switch (instruction) {
    case BC_STOREIVAR0:
        var0 = variables.top();
        variables.pop();
        break;
    case BC_STOREIVAR1:
        var1 = variables.top();
        variables.pop();
        break;
    case BC_LOADIVAR0:
        variables.push(var0);
        break;
    case BC_LOADDVAR1:
        variables.push(var1);
        break;
    default:
        break;
    }
}

