package ru.spbau.ustuzhanina.drunkard.gameobjects;

import ru.spbau.ustuzhanina.drunkard.Constant;
import ru.spbau.ustuzhanina.drunkard.Game;
import ru.spbau.ustuzhanina.drunkard.gamezone.Field;

/**
 * Created by KateKate on 02.05.14.
 */
public class Bar extends GameObjects implements IActiveObj {
    private Field field;
    private Game game;
    private static int steps;

    public Bar(Game game) {
        this.field = game.field;
        this.game = game;
        steps = 19;
    }

    @Override
    public Boolean makeTurn() {
        steps++;
        if (steps == Constant.NEW_DRUNKARD_FREQUENCY) {
            steps = 0;
            if (field.isCeilAvailable(Constant.InitialPos.DRUNCARD_INITIAL_POSITION.pos)) {
                Drunkard drunkard = new Drunkard(field);
                game.addActiveObj(drunkard);
                field.setObject(Constant.InitialPos.DRUNCARD_INITIAL_POSITION.pos, drunkard);
            }
        }
        return true;
    }
}
