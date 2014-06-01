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
    private Field field;
    private BeggarState state = BeggarState.READY_BEGGAR;

    public Beggar(Field field) {
        this.field = field;
        withBottle = false;
        setPosition(Constant.InitialPos.BEGGAR_INITIAL_POSITION.pos);
        find = false;
    }

    public Coordinates getBottlePosition() {
        for (int i = 0; i != Constant.FIELD_HEIGHT; i++) {
            for (int j = 0; j != Constant.FIELD_WIDTH; j++) {
                if (field.getCeil(new Coordinates(j, i)).getGameObjects() instanceof Bottle) {
                    return new Coordinates(j, i);
                }
            }
        }
        return null;
    }

    @Override
    public String symbol() {
        return "z";
    }

    public Boolean getWithBottle() {
        return withBottle;
    }

    public void setWithBottle(Boolean withBottle) {
        this.withBottle = withBottle;
    }

    @Override
    public Boolean makeTurn() {
        PathFind pfa = new PathFind(field);
        if (!find) {
            Coordinates wishPosition = getBottlePosition();
            if ((wishPosition != null) && (getPosition() != null)) {
                Coordinates newPosition = pfa.getNextCoordinates(getPosition(), wishPosition);
                if (newPosition == null)
                    return true;
                if (!newPosition.equals(wishPosition)) {
                    if (field.isCeilAvailable(newPosition)) {
                        field.moveObject(getPosition(), newPosition, this);
                    }
                    return true;
                } else {
                    field.moveObject(getPosition(), newPosition, this);
                    find = true;
                    setWithBottle(true);
                    return true;
                }
            }
        }

        if (find) {
            if (getPosition().equals(Constant.InitialPos.BEGGAR_INITIAL_POSITION.pos)) {
                find = false;
                return true;
            }
            Coordinates newPosition = pfa.getNextCoordinates(getPosition(), Constant.InitialPos.BEGGAR_INITIAL_POSITION.pos);
            if (newPosition != null) {
                if (field.isCeilAvailable(newPosition)) {
                    field.moveObject(getPosition(), newPosition, this);
                }
            }
        }
        return true;
    }

    public BeggarState getState() {
        return state;
    }

    public void setState(BeggarState state) {
        this.state = state;
    }

    enum BeggarState{
        READY_BEGGAR , WALKING_BEGGAR ,  DRUNKING_BEGGAR
    }
}
