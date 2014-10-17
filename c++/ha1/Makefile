default:  clean arj
main.o: main.cpp 
	g++ -c -Wall -Wextra -std=c++03 -pedantic main.cpp 
huffman.o: huffman.cpp huffman.h	
	g++ -c -Wall -Wextra -std=c++03 -pedantic huffman.cpp 
arj: main.o huffman.o 
	g++ main.o huffman.o -o arj
clean:
	rm -rf *.o arj

