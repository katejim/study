#ifndef FIELD_H
#define FIELD_H

#include <iostream>
#include <vector>

using std::vector;
using std::cout;
using std::endl;

struct Members {
    enum Types {none = 0, player = 1, enemy = 2};
};

class Field {
    typedef vector<Members::Types> row;
    typedef vector<row> field;

    static const int out_of_field = -1;
    field game_field;

public:
    static const int height = 6;
    static const int width = 7;

    Field() : game_field(height, row(width, Members::none)) {}

    bool check_on_board(int row, int column) ;
    bool check_four_seq(int row, int column , int d_row, int d_column, int player);
    bool check_win_state(int row, int column) ;

    int first_free_in_column(int column);

    void set_object(int row, Members::Types person);
    void del_object(int row, int column);

    void print_field() ;
};

#endif // GAME_H

