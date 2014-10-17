#include <iostream>
#include <fstream>
#include <map>
#include <vector>
#include <deque>
using namespace std;

struct Node{		
	unsigned char ch;		
	int left;
	int right;		
};

class Huffman{
	private:

		int weight[0x100];
		vector <Node> huffmanTree;
		unsigned long pairsSize;				
		string inFileName;
		string outFileName;		
		unsigned long  countSymbols;
		vector <vector <bool> > codes;

		void buildCodes(Node const & , vector<bool> & );
		void writeFrequences(ostream &out);
		void countFrequences();
		void makehuffmanTree();
		void readFrequences(istream &);
		unsigned char bufWrite(deque <unsigned long> &inDeq);
	public:
		Huffman(string const &, string const &);		
		void compressFile();
		void decompressFile();
};
