package ru.spbau.ustuzhanina.drunkard.gameobjects;

import ru.spbau.ustuzhanina.drunkard.Constant;
import ru.spbau.ustuzhanina.drunkard.Game;
import ru.spbau.ustuzhanina.drunkard.gamezone.Field;

/**
 * Created by KateKate on 03.05.14.
 */
public class GlassJarHouse extends GameObjects implements IActiveObj {
    private Game game;
    private Beggar beggar;
    private Field field;
    private static int timer;

    public GlassJarHouse(Game game) {
        this.game = game;
        this.field = game.field;
        beggar = new Beggar(field);
    }

    @Override
    public Boolean makeTurn() {
        if ((beggar.getWithBottle() == true) && (beggar.getPosition().equals(Constant.InitialPos.BEGGAR_INITIAL_POSITION.pos))) {
            beggar.setState(Beggar.BeggarState.DRUNKING_BEGGAR);
            game.field.delObject(Constant.InitialPos.BEGGAR_INITIAL_POSITION.pos);
            game.addNoActiveObj(beggar);
            beggar.setWithBottle(false);
            return true;
        }
        if ((beggar.getState() == Beggar.BeggarState.DRUNKING_BEGGAR) && (timer < 30)) {
            timer++;
            return true;
        }
        if ((beggar.getState() == Beggar.BeggarState.DRUNKING_BEGGAR) && (timer == 30)) {
            beggar.setState(Beggar.BeggarState.READY_BEGGAR);
            timer = 0;
        }
        if ((beggar.getBottlePosition() != null) && (beggar.getState() == Beggar.BeggarState.READY_BEGGAR)) {
            game.addActiveObj(beggar);
            beggar.setState(Beggar.BeggarState.WALKING_BEGGAR);
            field.setObject(Constant.InitialPos.BEGGAR_INITIAL_POSITION.pos, beggar);
        }
        return true;
    }
}
