package ru.spbau.ustuzhanina.drunkard.gameobjects;

import ru.spbau.ustuzhanina.drunkard.Constant;
import ru.spbau.ustuzhanina.drunkard.gamezone.Coordinates;
import ru.spbau.ustuzhanina.drunkard.gamezone.Field;
import ru.spbau.ustuzhanina.drunkard.PathFind;

/**
 * Created by KateKate on 03.05.14.
 */
public class Beggar extends GameObjects implements IActiveObj {
    private Boolean withBottle;
    private Boolean find;
    Coordinates curPosition;
    private Field field;

    public Beggar(Field field) {
        this.field = field;
        withBottle = false;
        setState(Constant.CeilState.BUSY_CEIL);
        setCeilObject(Constant.Actors.BEGGAR);
        setSymbolToPrint(Constant.Symbols.BEGGAR_CEIL_SYMBOL.symbol);
        setPosition(Constant.InitialPos.BEGGAR_INITIAL_POSITION.pos);
        setActiveState(Constant.ActiveState.READY_BEGGAR);
        curPosition = Constant.InitialPos.BEGGAR_INITIAL_POSITION.pos;
        find = false;
    }

    public Coordinates getBottlePosition() {
        for (int i = 0; i != Constant.FIELD_HEIGHT; i++) {
            for (int j = 0; j != Constant.FIELD_WIDTH; j++) {
                if (field.getCeil(new Coordinates(j, i)).getGameObjects().getCeilObject() == Constant.Actors.BOTTLE) {
                    return new Coordinates(j, i);
                }
            }
        }
        return null;
    }

    public Boolean getWithBottle() {
        return withBottle;
    }

    public void setWithBottle(Boolean withBottle) {
        this.withBottle = withBottle;
    }

    @Override
    public Boolean doSomething() {
        PathFind pfa = new PathFind(field);
        if (!find) {
            Coordinates wishPosition = getBottlePosition();
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
                    setWithBottle(true);
                    return true;
                }
            }
        }

        if (find) {
            if (curPosition.equals(Constant.InitialPos.BEGGAR_INITIAL_POSITION.pos)) {
                find = false;
                return true;
            }
            Coordinates newPosition = pfa.getNextCoordinates(curPosition, Constant.InitialPos.BEGGAR_INITIAL_POSITION.pos);
            if (newPosition != null) {
                if (field.isCeilAvailable(newPosition)) {
                    field.delObject(curPosition);
                    field.setObject(newPosition, this);
                    curPosition = newPosition;
                }
            }
        }
        return true;
    }
}
