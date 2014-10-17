#include <iostream>
#include <string>

#include "parser.h"
#include "evaluator.h"

using std::cerr;
using std::endl;

int main(int argc, const char *argv[])
{
    if(argc < 2)
    {
        cout << "you should add prog name";
        return 1;
    }
    else
        try
        {
            Parser parser(argv[1]);
            Program program = parser.parse();
            Evaluator evaluator(program);
            evaluator.start();
        }
        catch (Exception const &er)
        {
            cerr << "at line №: " <<  er.getLine() <<"  "<< er.what() <<  endl;
            return 1;
        }
    return 0;
}
