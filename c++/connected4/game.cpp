#include "game.h"

void Game :: main_loop() {
    srand ( unsigned (time(0)));
    bool fl = true;
    string turn;
    cin >> turn;
    int step = 0;
    if (turn == "Go") {
        while (true) {
            int column;
            if (fl) {
                column = 3;
                fl = false;
            }
            else {
                column = ai();
            }
            cur_field.set_object(column, Members::player);

            cout << column << endl;
            cin >> step;
            cur_field.set_object(step, Members::enemy);

        }
    } else {
        while (true) {
            cur_field.set_object(atoi(turn.c_str()), Members::enemy);
            int column = ai();
            cout << column <<endl;
            cur_field.set_object(column, Members::player);

            cin >> turn;
        }
    }
}
int Game :: negamax(int depth, int max_depth) {
    Members::Types temp_player;

    if (depth % 2 == 0)
        temp_player = Members::player;
    else
        temp_player = Members::enemy;

    vector<int> free_row(cur_field.width,0);
    for(int i = 0; i != cur_field.width; ++i) {
        free_row[i] = cur_field.first_free_in_column(i);
    }

    int chance  = 0;
    if(depth <= max_depth)
    {
        for(int i = 0 ; i != cur_field.width ; ++i)
        {
            int temp = 0;
            if(cur_field.check_on_board(free_row[i],i))
            {
                cur_field.set_object(i, temp_player);
                if( cur_field.check_win_state(free_row[i], i))
                {
                    ++count_win;
                    if(temp_player == Members::player)
                        ++player_win;
                    else
                        --player_win;

                    cur_field.del_object(free_row[i],i);
                    return -1;
                }
                temp = negamax(depth+1, max_depth);
                if(i == 0)
                    chance = temp;
                if(chance < temp)
                    chance = temp;
                cur_field.del_object(free_row[i],i);
            }
        }
    }
    return -chance;
}

int Game :: ai()
{
    Members::Types temp_player  = Members::player;
    float temp_value = INT_MAX;
    int column = 0;

    vector <int> unordered_steps;
    for (int i = 0; i !=cur_field.width ; ++i)
        unordered_steps.push_back(i);

    random_shuffle ( unordered_steps.begin(), unordered_steps.end() );
    for(int i = 0 ; i != cur_field.width ; ++i)
    {
        count_win = 0;
        player_win = 0;
        int i_value = unordered_steps[i];
        int check_position = cur_field.first_free_in_column(i_value);

        if(cur_field.check_on_board(check_position, i_value))
        {
            cur_field.set_object(i_value, temp_player);

            if(cur_field.check_win_state(check_position, i_value) == temp_player)
            {
                cur_field.del_object(check_position, i_value);
                column = i_value;
                return column;
            }

            float temp = -(100*negamax(1, max_depth));

            if(count_win != 0)
                temp -= ((100*player_win)/count_win);

            if( temp_value > temp  )
            {
                temp_value = temp;
                column = i_value;
            }
            cur_field.del_object(check_position, i_value);
        }
    }
    return column;
}




