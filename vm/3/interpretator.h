#ifndef INTERPRETATOR_H
#define INTERPRETATOR_H
#include "mathvm.h"
#include "context.h"
#include "utils.h"
#include <stack>
using std::stack;
using namespace mathvm;
class InterpretCode : public Code{
public:
    InterpretCode():position(0), contextIdx(0), varNameIdx(0)
    {}
    virtual Status* execute(vector<Var*>& vars) {
        cout << "here" <<endl;
        Code::FunctionIterator it(this);
        while(it.hasNext()){
            currenFunction = (BytecodeFunction*)it.next();
            cout << currenFunction->name() << endl;
            Context::VariableMap vars;
            Context::FunctionMapM funcs;
            currentContext = new Context(contextIdx++, vars, funcs, NULL);

            byteCode()->dump(cout);
            handleByteCode();
            int64_t value =  byteCode()->getInt64(1);
        }
        return Status::Ok();
    }

    size_t getInstructionLength(Instruction instruction) {
        struct {
            Instruction insn;
            size_t length;
        } instructions[] = {
#define ENUM_ELEM_LEN(b, d, l) {BC_##b, l},
            FOR_BYTECODES(ENUM_ELEM_LEN)
        };
        return instructions[instruction].length;
    }
private:
    void handleByteCode();
    BytecodeFunction * currenFunction;
    Bytecode *byteCode() {
        return currenFunction->bytecode();
    }
    uint8_t position;
    stack <Var> variables;
    Context * currentContext;
    int16_t contextIdx;
    int16_t varNameIdx;
private:
    VarType getType(Instruction instruction) const;
    Var createVar(Instruction type, const string & name);
    void loadVar(Instruction instruction);
    void saveVar(Instruction instruction);
    void printVariable(Var var);
};

#endif // INTERPRETATOR_H
