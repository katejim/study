#include <iostream>
#include <vector>

using std::cin;
using std::cout;
using std::endl;
using std::vector;

struct node
{
    int key;
    node* left;
    node* right;

    int size;
    int height;

    node(int m_key) : key(m_key), left(0), right(0), size(1), height(1) {}

} *tree;

unsigned char get_height(node* p)
{
    return p ? p -> height : 0;
}
int get_size(node *p)
{
    return p ? p -> size : 0;
}

int diff(node* p)
{
    return get_height(p -> right) - get_height(p -> left);
}

void fix_height(node* p)
{
    int height_left_subtree = get_height(p -> left);
    int height_right_subtree = get_height(p -> right);
    if(height_left_subtree > height_right_subtree)
        p -> height = height_left_subtree;
    else
        p -> height = height_right_subtree;
    ++p -> height;
}

node* rotate_right(node* p)
{
    node* q = p -> left;
    p -> left = q -> right;
    q -> right = p;
    fix_height(p);
    fix_height(q);

    q -> size = p -> size;
    p -> size = get_size(p -> left) + get_size(p -> right) + 1;

    return q;
}

node* rotate_left(node* q)
{
    node* p = q -> right;

    q -> right = p -> left;
    p -> left = q;

    fix_height(q);
    fix_height(p);

    p -> size = q -> size;
    q -> size = get_size(q -> left) + get_size(q -> right) + 1;

    return p;
}

node* balance(node* p)
{
    fix_height(p);
    if( diff(p) == 2 )
    {
        if( diff(p -> right) < 0 )
            p -> right = rotate_right(p -> right);
        return rotate_left(p);
    }
    if( diff(p) == -2 )
    {
        if( diff( p -> left) > 0  )
            p -> left = rotate_left(p -> left);
        return rotate_right(p);
    }
    return p;
}


node* insert(node* p, int key)
{
    if( !p )
        return new node(key);
    if( key < p->key )
        p->left = insert(p -> left, key);
    else
        p->right = insert(p->right, key);
    ++p->size;

    return balance(p);
}

int get_real_position(int inQuery) {
    inQuery;
    int leftCount = 0;
    int rightCount;
    node *p = tree;
    int size_left_subtree;
    while (p != NULL) {
        size_left_subtree = p->key - leftCount - get_size(p->left);
        if( inQuery < size_left_subtree)
            p  = p->left;
        else {
            inQuery -= size_left_subtree;
            leftCount = p->key + 1;
            p = p->right;
        }
    }
    return inQuery + leftCount;
}

int rezult(char letter, int current_room_number) {
    int rez = -1;
    if (letter == 'D')
        tree = insert(tree, get_real_position(current_room_number));
    else if (letter == 'L')
        rez = get_real_position(current_room_number);
    return rez;
}

int main()
{
    int allNumbers, allLines;
    cin >> allNumbers >>allLines;

    tree = NULL;

    vector <int> rez;
    for(int i = 0; i != allLines; ++i) {
        char actionLetter;
        int roomNumber;
        cin >> actionLetter >> roomNumber;
        int temp = rezult(actionLetter, roomNumber);
        if (temp != -1)
            rez.push_back(temp);
    }

    int size = rez.size();
    for(int i = 0; i < size ; ++i)
        cout << rez[i] << endl;
    return 0;
}
