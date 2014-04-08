#include "field.h"


bool Field :: check_on_board(int x, int y)
{
    return (0<=x && x < height && 0<=y && y < width);
}

bool Field :: check_four_seq(int x, int y, int dx, int dy, int color)
{
    int length = 1;
    int i =  1;

    while(check_on_board(x + dx*i, y+dy*i))
    {
        if (game_field[x + dx*i][y+dy*i] == color)
        {
            ++length;
            ++i;
        }
        else break;

    }

    i = -1;
    while(check_on_board(x + dx*i, y+dy*i))
    {
        if (game_field[x + dx*i][y+dy*i] == color)
        {
            ++length;
            --i;
        }
        else break;

    }
    return (length >= 4);
}

bool Field :: check_win_state(int x, int y)
{
    bool rez = false;
    if (game_field[x][y] !=  Members::none)
    {
        int color = game_field[x][y];
        rez = rez ? rez : check_four_seq(x,y, 1,0,color);
        rez = rez ? rez : check_four_seq(x,y, 1,-1,color);
        rez = rez ? rez : check_four_seq(x,y, 0,1,color);
        rez = rez ? rez : check_four_seq(x,y, 1,1,color);
    }
    return rez;
}

int Field :: first_free_in_column(int column) {
    int rez_row = out_of_field;
    for (int i = 0; i != height; ++i)
    {
        if (game_field[i][column] == Members::none)
        {
            rez_row = i;
            break;
        }
    }
    return rez_row;
}

void Field :: print_field()
{
    for(int i = height - 1; i != -1; --i)
    {
        for (int j = 0; j != width; ++j)
            cout << game_field[i][j]<<" ";
        cout << endl;
    }
}

void Field::set_object(int column, Members::Types m_person) {
    for (int i = 0; i != height; ++i) {
        if (game_field[i][column] == Members::none) {
            game_field[i][column] = m_person;
            break;
        }
    }
}
void Field::del_object(int row, int column) {
    game_field[row][column] = Members::none;
}
