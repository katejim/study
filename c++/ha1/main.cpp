#include <iostream>
#include <cstring>
#include "huffman.h"
using namespace std;

int main(int argc, char *argv[]){
	bool compress = false;
	bool decompress = false;
	string fileInName, fileOutName;
	
	for(int i = 0; i < argc; ++i){
		if ( strcmp(argv[i],"-c") == 0 )
			compress = true;
		if ( strcmp(argv[i],"-d") == 0 )
			decompress = true;
		if ( strcmp(argv[i],"-i") == 0 )
			fileInName = argv[i + 1];
		if ( strcmp(argv[i],"-o") == 0 )
			fileOutName = argv[i + 1];
	}
	Huffman compressFile(fileInName, fileOutName);		
	if (compress)
		compressFile.compressFile();
		
	if (decompress)
		compressFile.decompressFile();
	
		
	
	return 0;
}

