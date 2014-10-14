#include "mathvm.h"
#include "visitors.h"
#include "parser.h"
#include <map>

namespace mathvm {
    Translator* Translator::create(const string& impl) {
        if (impl == "printer") {
            return AstPrinter();
        } else {
            return NULL;
        }
    }

    class AstPrinter : public Translator {
    public:
        virtual Status* translate(const string& program, Code* *code)  {
            Parser parser;
            Status* status = parser.parseProgram(program);
            if (status->isError()) return status;
            // Implement printer, using parser.top() as root of the AST
            return new Status("No executable code produced");
        }
    };
}
