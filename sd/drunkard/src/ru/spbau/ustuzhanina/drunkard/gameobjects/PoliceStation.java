package ru.spbau.ustuzhanina.drunkard.gameobjects;

import ru.spbau.ustuzhanina.drunkard.Constant;
import ru.spbau.ustuzhanina.drunkard.Game;
import ru.spbau.ustuzhanina.drunkard.gamezone.Field;

/**
 * Created by KateKate on 02.05.14.
 */
public class PoliceStation extends GameObjects implements IActiveObj {
    Game game;
    Field field;
    Policman policman;

    public PoliceStation(Game game) {
        this.game = game;
        this.field = game.field;
        policman = new Policman(field);
    }

    @Override
    public Boolean doSomething() {
        if (policman.curPosition.equals(Constant.InitialPos.POLICEMEN_INITIAL_POSITION.pos) && (policman.getWithDrunkard() == true)) {
            policman.setActiveState(Constant.ActiveState.READY_POLICEMEN);
            game.field.delObject(Constant.InitialPos.POLICEMEN_INITIAL_POSITION.pos);
            game.addNoActiveObj(policman);
            policman.setWithDrunkard(false);
            return true;
        }
        if ((policman.getSleepLayPosition() != null) && (policman.getActiveState() == Constant.ActiveState.READY_POLICEMEN && field.isCeilAvailable(Constant.InitialPos.POLICEMEN_INITIAL_POSITION.pos))) {
            if (field.getCeil(Constant.InitialPos.POLICEMEN_INITIAL_POSITION.pos).isEmpty()) {
                game.addActiveObj(policman);
                policman.setActiveState(Constant.ActiveState.WALKING_POLICMEN);
                field.setObject(Constant.InitialPos.POLICEMEN_INITIAL_POSITION.pos, policman);
            }
        }
        return true;
    }
}
