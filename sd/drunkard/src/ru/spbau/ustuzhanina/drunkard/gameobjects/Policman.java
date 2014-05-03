package ru.spbau.ustuzhanina.drunkard.gameobjects;

import ru.spbau.ustuzhanina.drunkard.Constant;
import ru.spbau.ustuzhanina.drunkard.gamezone.Coordinates;
import ru.spbau.ustuzhanina.drunkard.gamezone.Field;
import ru.spbau.ustuzhanina.drunkard.PathFind;

/**
 * Created by KateKate on 02.05.14.
 */
public class Policman extends GameObjects implements IActiveObj {
    Field field;
    Coordinates curPosition;
    Boolean find;
    Boolean withDrunkard;

    public Policman(Field field) {
        withDrunkard = false;
        setState(Constant.CeilState.BUSY_CEIL);
        setCeilObject(Constant.Actors.POLICE);
        setSymbolToPrint(Constant.Symbols.POLICE_CEIL_SYMBOL.symbol);
        setPosition(Constant.InitialPos.POLICEMEN_INITIAL_POSITION.pos);
        this.field = field;
        setActiveState(Constant.ActiveState.READY_POLICEMEN);
        curPosition = Constant.InitialPos.POLICEMEN_INITIAL_POSITION.pos;
        find = false;
    }


    @Override
    public Boolean doSomething() {
        PathFind pfa = new PathFind(field);
        if (getActiveState() == Constant.ActiveState.WALKING_POLICMEN) {
            if (!find) {

                Coordinates wishPosition = getSleepLayPosition();
                if ((wishPosition != null) && (curPosition != null)) {
                    Coordinates newPosition = pfa.getNextCoordinates(curPosition, wishPosition);
                    if (newPosition == null)
                        return true;
                    if (!newPosition.equals(wishPosition)) {
                        if (field.isCeilAvailable(newPosition)) {
                            field.delObject(curPosition);
                            field.setObject(newPosition, this);
                            curPosition = newPosition;
                        }
                        return true;
                    } else {
                        field.setObject(newPosition, this);
                        field.delObject(curPosition);
                        curPosition = newPosition;
                        find = true;
                        setWithDrunkard(true);
                        return true;
                    }
                }
            }

            if (find) {
                if (curPosition.equals(Constant.InitialPos.POLICEMEN_INITIAL_POSITION.pos)) {
                    find = false;
                    return true;
                }
                Coordinates newPosition = pfa.getNextCoordinates(curPosition, Constant.InitialPos.POLICEMEN_INITIAL_POSITION.pos);
                if (newPosition != null) {
                    if (field.isCeilAvailable(newPosition)) {
                        field.delObject(curPosition);
                        field.setObject(newPosition, this);
                        curPosition = newPosition;
                    }
                }
            }
        }
        return true;
    }

    public Coordinates getSleepLayPosition() {
        for (int i = Constant.InitialPos.LANTERN_POSITION.pos.getX() - 3; i < Constant.InitialPos.LANTERN_POSITION.pos.getX() + 3; i++) {
            for (int j = Constant.InitialPos.LANTERN_POSITION.pos.getY() - 3; j < Constant.InitialPos.LANTERN_POSITION.pos.getY() + 3; j++) {
                if (checkIfSleepLay(new Coordinates(i, j))) {
                    return new Coordinates(i, j);
                }
            }
        }
        return null;
    }

    public Boolean checkIfSleepLay(Coordinates position) {
        if ((field.getCeil(position).getGameObjects().getCeilObject() == Constant.Actors.DRUNKARD) &&
                ((field.getCeil(position).getGameObjects().getActiveState() == Constant.ActiveState.SLEEP_DRUNKARD) ||
                        (field.getCeil(position).getGameObjects().getActiveState() == Constant.ActiveState.LAY_DRUNKARD))) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean getWithDrunkard() {
        return withDrunkard;
    }
    public void setWithDrunkard(Boolean withDrunkard) {
        this.withDrunkard = withDrunkard;
    }
}
