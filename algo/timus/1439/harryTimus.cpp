#include <iostream>
#include <algorithm>

using std::cin;
using std::cout;
using std::endl;
using std::max;

class BattleWithYouKnowWho
{

  public:
    BattleWithYouKnowWho():
        tree(0)
    {
    }

    int get_real_room(int in_query) const
    {
        int left_count = 0;
        AVLTreeNode * p = tree;

        while (p != NULL)
        {
            int free_rooms = p->key - left_count - get_size(p->left);

            if( in_query < free_rooms)
                p = p->left;
            else
            {
                in_query -= free_rooms;
                left_count = p->key + 1;
                p = p->right;
            }
        }

        return in_query + left_count;
    }

    void delete_room(size_t room_number)
    {
        tree = insert(tree, get_real_room(room_number));
    }

    ~BattleWithYouKnowWho()
    {
        delete_node(tree);
    }


  private:
    struct AVLTreeNode
    {
        int key;
        AVLTreeNode * left;
        AVLTreeNode * right;

        int size;
        int height;

        explicit AVLTreeNode(int m_key) :
            key(m_key), left(0), right(0), size(1), height(1)
        {
        }
    };

    BattleWithYouKnowWho( BattleWithYouKnowWho const &);
    void operator = ( BattleWithYouKnowWho const &);

    AVLTreeNode * tree;

    static int get_height(AVLTreeNode const * p)
    {
        return p ? p->height : 0;
    }

    static int get_size(AVLTreeNode const * p)
    {
        return p ? p->size : 0;
    }

    static int diff(AVLTreeNode const * p)
    {
        return get_height(p->right) - get_height(p->left);
    }

    static void fix_height(AVLTreeNode * p)
    {
        int height_left_subtree = get_height(p->left);
        int height_right_subtree = get_height(p->right);
        p->height = max(height_left_subtree, height_right_subtree);
        ++p->height;
    }

    static AVLTreeNode * rotate_right(AVLTreeNode * p)
    {
        AVLTreeNode * q = p->left;
        p->left = q->right;
        q->right = p;
        fix_height(p);
        fix_height(q);
        q->size = p->size;
        p->size = get_size(p->left) + get_size(p->right) + 1;
        return q;
    }

    static AVLTreeNode * rotate_left(AVLTreeNode * q)
    {
        AVLTreeNode * p = q->right;
        q->right = p->left;
        p->left = q;
        fix_height(q);
        fix_height(p);
        p->size = q->size;
        q->size = get_size(q->left) + get_size(q->right) + 1;
        return p;
    }

    static AVLTreeNode * balance(AVLTreeNode * p)
    {
        fix_height(p);

        if( diff(p) == 2 )
        {
            if( diff(p->right) < 0 )
                p->right = rotate_right(p->right);

            return rotate_left(p);
        }

        if( diff(p) == -2 )
        {
            if( diff( p->left) > 0 )
                p->left = rotate_left(p->left);

            return rotate_right(p);
        }

        return p;
    }


    static AVLTreeNode * insert(AVLTreeNode * p, int key)
    {
        if( !p )
            return new AVLTreeNode(key);

        if( key < p->key )
            p->left = insert(p->left, key);
        else
            p->right = insert(p->right, key);

        ++p->size;
        return balance(p);
    }

    static void delete_node(AVLTreeNode * p)
    {
        if (p != NULL)
        {
            delete_node(p->left);
            delete_node(p->right);
            delete p;
        }
    }
};

int main()
{
    int all_numbers, all_lines;
    cin >> all_numbers >> all_lines;
    BattleWithYouKnowWho battle;

    for(int i = 0; i != all_lines; ++i)
    {
        char action_letter;
        int room_number;
        cin >> action_letter >> room_number;

        if (action_letter == 'D')
            battle.delete_room(room_number);
        else if (action_letter == 'L')
            cout << battle.get_real_room(room_number) << endl;
    }

    return 0;
}
