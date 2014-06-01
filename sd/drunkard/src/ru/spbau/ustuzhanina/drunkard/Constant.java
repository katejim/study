package ru.spbau.ustuzhanina.drunkard;

import ru.spbau.ustuzhanina.drunkard.gamezone.Coordinates;

/**
 * Created by KateKate on 02.03.14.
 */
public class Constant {
    public final static int FIELD_WIDTH = 15;
    public final static int FIELD_HEIGHT = 15;
    public final static int NEW_DRUNKARD_FREQUENCY = 20;

    public enum InitialPos{
        COLUMN_POSITION(new Coordinates(7, 7)), LANTERN_POSITION(new Coordinates(10, 3)),
        DRUNCARD_INITIAL_POSITION(new Coordinates(9, 0)), BEGGAR_INITIAL_POSITION(new Coordinates(0, 4)),
        POLICEMEN_INITIAL_POSITION(new Coordinates(14, 3));

        public Coordinates pos;
        InitialPos(Coordinates pos) {
            this.pos = pos;
        }
    }
}
