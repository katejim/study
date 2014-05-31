#include <iostream>
#include <vector>
#include <string.h>
using namespace std;

vector<long long> p_pow, first_speach_hash, second_speach_hash;

string fill_string(string &, size_t);
void compute_pows(size_t);
vector<long long> compute_hashes(vector <long long>  &, string);
bool compare_strings(size_t, size_t, size_t);

int main()
{
    size_t speach_len  =0;
    cin>>speach_len;
    string first_speach, second_speach="";

    fill_string(first_speach, speach_len);
    fill_string(second_speach, speach_len);
    cout << first_speach << endl << second_speach <<endl;

    compute_pows(speach_len);

    first_speach_hash.assign(speach_len, 0);
    second_speach_hash.assign(speach_len, 0);

    compute_hashes(first_speach_hash, first_speach);
    compute_hashes(second_speach_hash, second_speach);

    cout << "here" <<endl;
    for (int i =0;i != first_speach_hash.size();i++){
        cout << first_speach_hash[i] <<endl;
    }
    size_t cur_len = speach_len, i = 0, j = 0;
    string cur_string = "";

    int left = 0, right = speach_len - 1; // текущий интервал значений

    while (left <= right)
    {
        bool fl = false;
        // находим middle - середину интерва
        int middle = (left + right) / 2;
        //cout << left <<right <<middle;
        for (int k = 0; k != speach_len; k += 1){
            cout << "left" <<left<< "k = " <<k <<"len = " << right-left+1;

            if(compare_strings(left, k , right-left +1 )){
                cout << "find"<<endl;
                cout<<left << right ;
                fl = true;
                break;
            }
        }
        if (fl){
            left = middle + 1;
        } else {
            right = middle - 1;
        }
    }

    return 0;
}

void compute_pows(size_t max_len){
    const int p = 31;
    p_pow.assign(max_len,0);

    p_pow[0] = 1;
    for (size_t i=1; i<max_len; ++i)
        p_pow[i] = p_pow[i-1] * p;
}

vector <long long> compute_hashes(vector <long long> & hash,  string s){

    size_t max_len = s.length();

    for (size_t i=0; i<max_len; ++i)
    {
        hash[i] = (s[i] - 'A' + 1) * p_pow[i];
        if (i)  hash[i] += hash[i-1];
    }
    return hash;
}

bool compare_strings(size_t i1, size_t i2, size_t len){
    long long h1 = first_speach_hash[i1+len-1];
    if (i1)  h1 -= first_speach_hash[i1-1];
    long long h2 = second_speach_hash[i2+len-1];
    if (i2)  h2 -= second_speach_hash[i2-1];
    // сравниваем их
    if (h1 * p_pow[i2-i1] == h2 ||
        h1 == h2 * p_pow[i1-i2]){
        cout << "equal";
        return true;
    }
    else{
        cout << "different";
        return false;
    }
}

string fill_string(string & str, size_t len){
    for (size_t i = 0; i!= len; ++i){
        char letter;
        cin >> letter;
        str += letter;
    }
    return str;
}
