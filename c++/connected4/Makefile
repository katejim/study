default:  clean bot

field.o: field.cpp field.h	
	g++ -c -Wall -Wextra -pedantic   field.cpp
game.o: game.cpp game.h	
	g++ -c -Wall -Wextra -pedantic   game.cpp
main.o: main.cpp 
	g++ -c -Wall -Wextra -pedantic   main.cpp  
bot: main.o field.o game.o 
	g++ main.o field.o game.o -o bot
clean:
	rm -rf *.o bot




