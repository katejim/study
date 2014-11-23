#include "mathvm.h"

#include <stdio.h>
#include <fcntl.h>
#include <sys/stat.h>

#include <iostream>
#include <map>
#include <cstdint>


using namespace mathvm;
using namespace std;

int main(int argc, char** argv) {
    string impl = "intepreter";
    const char* script = NULL;
    for (int32_t i = 1; i < argc; i++) {
        if (string(argv[i]) == "-j") {
            impl = "jit";
        }  if (string(argv[i]) == "-p") {
            impl = "intepreter";
        } else {
            script = argv[i];
        }
    }
    Translator* translator = Translator::create(impl);

    if (translator == 0) {
        cout << "TODO: Implement translator factory in translator.cpp!!!!" << endl;
        return 1;
    }

//        const char* expr ="int a; a = 8; double b; b = 8.0; print(5 > 3);"
//                          "function void add(double x, int t) {"
//                          "print(x);"
//                          "function void addw3(int y)"
//                          "{"
//                          "print(y);"
//                          "};"
//                          "} "
//                          "function void NEW(int y)"
//                          "{"
//                          "print(y);"
//                          "}; add(b, a); b = 7.3;" ;

    //    const char* expr = "int a; a = 6; int b; b = 3;"
    //                       "function void mprint(int t) { print(b);     function void add(int t) { print (b);} }"
    //                       "mprint(b);";

    //    const char* expr = "int t; t = 4;"
    //                       "function int strlen(int t) native 'strlen'; function int strlen2(int t) native 'strlen2'; strlen2(t); strlen(t);";

    // const char* expr = "int a; a = 8; while (5 > 7) { int a; a = 7; print (a); }";

    //    const char* expr = "int i; i = 0; for (i in -3..4)  {"
    //                       "print(i);"
    //                       "}";

    //    const char* expr = "int a; a = 31; if (a > 0)"
    //                            "{ int a; a = 3; print(a);} "
    //                            "else {int a; a = 4; print(a);}";


    //    const char* expr =
    //                        "print(12345678, '\n');"
    //                        "print(-1234567890123456, '\n');"

    //                        "print(1.0, '\n');"
    //                        "print(-2.0, '\n');"
    //                        "print(1e8, '\n');"
    //            "print(1e-8, '');";

    const char * expr = "print('asd' + 'asdsa');";
    cout << "expr = " << expr <<endl;

    bool isDefaultExpr = true;

    if (script != NULL) {
        expr = loadFile(script);
        if (expr == 0) {
            printf("Cannot read file: %s\n", script);
            return 1;
        }
        isDefaultExpr = false;
    }

    Code* code = 0;

    Status* translateStatus = translator->translate(expr, &code);
    if (translateStatus->isError()) {
        uint32_t position = translateStatus->getPosition();
        uint32_t line = 0, offset = 0;
        positionToLineOffset(expr, position, line, offset);
        printf("Cannot translate expression: expression at %d,%d; "
               "error '%s'\n",
               line, offset,
               translateStatus->getErrorCstr());
    } else {
        if (impl != "intepreter") {
            assert(code != 0);
            vector<Var*> vars;

            if (isDefaultExpr) {
                Var* xVar = new Var(VT_DOUBLE, "x");
                Var* yVar = new Var(VT_DOUBLE, "y");
                vars.push_back(xVar);
                vars.push_back(yVar);
                xVar->setDoubleValue(42.0);
            }
            Status* execStatus = code->execute(vars);
            if (execStatus->isError()) {
                printf("Cannot execute expression: error: %s\n",
                       execStatus->getErrorCstr());
            } else {
                if (isDefaultExpr) {
                    printf("x evaluated to %f\n", vars[0]->getDoubleValue());
                    for (uint32_t i = 0; i < vars.size(); i++) {
                        delete vars[i];
                    }
                }
            }
            delete code;
            delete execStatus;
        }
    }
    delete translateStatus;
    delete translator;

    if (!isDefaultExpr) {
        delete [] expr;
    }

    return 0;
}
