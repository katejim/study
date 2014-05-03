package ru.spbau.ustuzhanina.drunkard;

import ru.spbau.ustuzhanina.drunkard.gamezone.Coordinates;

/**
 * Created by KateKate on 02.03.14.
 */
public class Constant {
    public final static int FIELD_WIDTH = 15;
    public final static int FIELD_HEIGHT = 15;
    public final static int NEW_DRUNKARD_FREQUENCY = 20;

    public enum Actors {
        NO, DRUNKARD, BOTTLE,
        COLUMN, LANTERN, POLICE,
        BEGGAR;
    }

    public enum Symbols {
        FREE_CEIL_SYMBOL("."), COLUMN_CEIL_SYMBOL("C"), DRUNKARD_CEIL_SYMBOL("D"),
        DRUNKARD_SLEEP_CEIL_SYMBOL("Z"), DRUNKARD_LAY_CEIL_SYMBOL("&"),
        PUB_CEIL_SYMBOL("T"), BOTTLE_CEIL_SYMBOL("B"), LANTERN_CEIL_SYMBOL("L"),
        POLICE_CEIL_SYMBOL("P"), BEGGAR_CEIL_SYMBOL("z");

        public final String symbol;

        Symbols(String symbol) {
            this.symbol = symbol;
        }
    }

    public enum CeilState{
        FREE_CEIL, BUSY_CEIL;
    }

    public enum InitialPos{
        COLUMN_POSITION(new Coordinates(7, 7)), LANTERN_POSITION(new Coordinates(10, 3)),
        DRUNCARD_INITIAL_POSITION(new Coordinates(9, 0)), BEGGAR_INITIAL_POSITION(new Coordinates(0, 4)),
        POLICEMEN_INITIAL_POSITION(new Coordinates(14, 3)),
        PUB(new Coordinates(9, -1)),
        POLICESTATION(new Coordinates(15,3)),
        GLASSJARHOUSE(new Coordinates(-1,4));



        public Coordinates pos;
        InitialPos(Coordinates pos) {
            this.pos = pos;
        }
    }

    public enum ActiveState{
        SLEEP_DRUNKARD , READY_DRUNKARD ,   LAY_DRUNKARD,
        READY_POLICEMEN ,WALKING_POLICMEN,  READY_BEGGAR ,
        WALKING_BEGGAR ,  DRUNKING_BEGGAR ;
    }
}
