#include "huffman.h"
Huffman::Huffman( string const &inFile,  string const &outFile):
	inFileName(inFile),
	outFileName(outFile){}

void Huffman::countFrequences(){
	fill(weight, weight + 0x100, 0);
	countSymbols = 0;			
	ifstream f(inFileName.c_str());
	if(!f) cout<<"noo such file or wrong path";
	else{
		while (!f.eof()){
			unsigned char ch = f.get();							
			++weight[ch];
			++countSymbols;
		}
	}	
}
void Huffman::makehuffmanTree(){
	int newNode = 5;
	pairsSize = 0;
	multimap <int ,int> sortedWeight;		
	for(int i = 0; i < 0x100; ++i)
		if(weight[i] > 0){							
			Node myNode = { (unsigned char)i, -1, -1};				
			huffmanTree.push_back(myNode);				
			sortedWeight.insert(pair<int,int>(weight[i], huffmanTree.size() - 1));
			++pairsSize;
		}

	if((sortedWeight.size() == 0) || (sortedWeight.size() == 1)){
		Node myNode1 = { (unsigned char)newNode, -1, -1};
		huffmanTree.push_back(myNode1);
		sortedWeight.insert(pair<int,int>(1, huffmanTree.size() - 1));			
		huffmanTree.push_back(myNode1);
		sortedWeight.insert(pair<int,int>(4, huffmanTree.size() - 1));
	}


	while(sortedWeight.size() > 1) {				
		int w0 = sortedWeight.begin()->first;
		int n0 = sortedWeight.begin()->second;
		sortedWeight.erase(sortedWeight.begin());		
		int w1 = sortedWeight.begin()->first;
		int n1 = sortedWeight.begin()->second;
		sortedWeight.erase(sortedWeight.begin());
		Node myNode = { (unsigned char)newNode, n0, n1};			
		huffmanTree.push_back(myNode);
		sortedWeight.insert(pair<int,int>(w0 + w1, huffmanTree.size() - 1));
	}
}

void Huffman::buildCodes(Node const & node, vector<bool> & code) {
	if (node.left == -1 && node.right == -1){
		codes[node.ch] = code;
		return;
	}

	code.push_back(false);
	buildCodes(huffmanTree[node.left], code);
	code.back() = true;
	buildCodes(huffmanTree[node.right], code);
	code.pop_back();
}

void writeNumber(std::ostream &out, unsigned long number){
	for (; number >= 0x80; number /= 0x80)
		out.put((number % 0x80) | 0x80);
	out.put(number);
}

void Huffman::writeFrequences(ostream &out){
	for(int i = 0; i < 0x100; ++i)
		if(weight[i] > 0){
			unsigned char chOut = i;
			out.put(chOut);
			writeNumber(out, weight[i]);
		}
}

void Huffman::compressFile(){
	countFrequences();
	makehuffmanTree();

	vector<bool> code;
	ofstream fOut(outFileName.c_str());	

	writeNumber(fOut, countSymbols);
	writeNumber(fOut, pairsSize);
	writeFrequences(fOut);
	codes.assign(256, vector <bool> (1,false));

	buildCodes(huffmanTree.back(), code);
	deque <unsigned long> myDeq;	
	ifstream fIn(inFileName.c_str());
	while (!fIn.eof()){			
		unsigned char ch = fIn.get();			
		for(size_t i = 0; i < codes[ch].size(); ++i)			
			myDeq.push_back(codes[ch][i]);

		while (myDeq.size() >= 8)
			fOut.put(bufWrite(myDeq));
	}

	if (myDeq.size() != 0)	
		fOut.put(bufWrite(myDeq));
}


void readNumber(std::istream &in, unsigned long &number){
	number = 0;
	unsigned char ch = 0x80;
	for (size_t width = 0; !in.eof() && ch >= 0x80; ++width){
		ch = in.get();
		number |= ((ch & 0x7F) << (width * 7));
	}
}

void Huffman::readFrequences(istream &fIn){
	fill(weight, weight + 0x100, 0);
	for(size_t i = 0; i < pairsSize; ++i){			
		unsigned char ch = fIn.get();
		unsigned long inWeight;
		readNumber(fIn, inWeight);
		weight[ch] = inWeight;						
	}
}

void Huffman::decompressFile(){		
	ifstream fIn(inFileName.c_str());		
	if(!fIn) cout<<"noo such file or wrong path";
	else {
		readNumber(fIn, countSymbols);
		countSymbols--;	
		readNumber(fIn, pairsSize);
		readFrequences(fIn);

		huffmanTree.clear();
		makehuffmanTree();

		size_t counter = 0;					
		int k = huffmanTree.size() - 1;
		Node n = huffmanTree[k];
		ofstream fOut(outFileName.c_str());

		while(!fIn.eof()){
			unsigned char ch = fIn.get();
			vector <bool> date;
			for(int i = 0;i < 8; ++i)
				date.push_back((ch & (1<<i)) != 0);

			for(size_t i = 0; i < 8; ++i){
				if(counter == (countSymbols)) break;
				else{
					if(date[i] == 1)
						n = huffmanTree[n.right];				
					else 
						n = huffmanTree[n.left];
					if(n.right == -1){				
						fOut.put(n.ch);
						n = huffmanTree[k];
						++counter;
					}
				}
			}			
		}		
	}
}


unsigned char Huffman::bufWrite(deque <unsigned long> &inDeq){
	unsigned char chOut = 0;
	int count = 0;
	while (count < 8){		
		if(inDeq[0])
			chOut |= (1<<count);
		++count;
		inDeq.pop_front();
	}
	return chOut;
}
		

