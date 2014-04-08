#ifndef GAME_H
#define GAME_H

#include <vector>
#include <cstdlib>
#include <climits>
#include <algorithm>
#include <iostream>
#include <ctime>

#include "field.h"


using std::vector;
using std::cout;
using std::random_shuffle;
using std::cin;
using std::string;
using std::time;
using std::srand;

class Game {
    static const int max_depth = 5;
    Field cur_field;

    int count_win;
    int player_win;

public:
    void main_loop();
    int negamax(int cur_depth, int max_depth);
    int ai();
};



#endif // GAME_H
