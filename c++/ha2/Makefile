default:  clean pp
lexer.o: lexer.cpp lexer.h	
	g++ -c -Wall -Wextra -pedantic -std=c++0x  lexer.cpp
parser.o: parser.cpp parser.h
	g++ -c -Wall -Wextra -pedantic -std=c++0x   parser.cpp 
evaluator.o: evaluator.cpp evaluator.h
	g++ -c -Wall -Wextra -pedantic -std=c++0x   evaluator.cpp 
main.o: main.cpp 
	g++ -c -Wall -Wextra -pedantic -std=c++0x   main.cpp  
pp: main.o parser.o lexer.o evaluator.o
	g++ main.o parser.o lexer.o evaluator.o -o pp
clean:
	rm -rf *.o pp

