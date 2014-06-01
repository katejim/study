package ru.spbau.ustuzhanina.drunkard.gameobjects;

import ru.spbau.ustuzhanina.drunkard.Constant;
import ru.spbau.ustuzhanina.drunkard.gamezone.Coordinates;
import ru.spbau.ustuzhanina.drunkard.gamezone.Field;
import ru.spbau.ustuzhanina.drunkard.PathFind;

/**
 * Created by KateKate on 02.05.14.
 */
public class Policman extends GameObjects implements IActiveObj {
    private Field field;
    private Boolean find;
    private Boolean withDrunkard;
    private PolicmanState state = PolicmanState.READY_POLICEMEN;

    public Policman(Field field) {
        withDrunkard = false;
        setPosition(Constant.InitialPos.POLICEMEN_INITIAL_POSITION.pos);
        this.field = field;
        find = false;
    }

    @Override
    public Boolean makeTurn() {
        PathFind pfa = new PathFind(field);
        if (state == PolicmanState.WALKING_POLICMEN) {
            if (!find) {
                Coordinates wishPosition = getSleepLayPosition();
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
                        setWithDrunkard(true);
                        return true;
                    }
                }
            }

            if (find) {
                if (getPosition().equals(Constant.InitialPos.POLICEMEN_INITIAL_POSITION.pos)) {
                    find = false;
                    return true;
                }
                Coordinates newPosition = pfa.getNextCoordinates(getPosition(), Constant.InitialPos.POLICEMEN_INITIAL_POSITION.pos);
                if (newPosition != null) {
                    if (field.isCeilAvailable(newPosition)) {
                        field.moveObject(getPosition(), newPosition, this);
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
        if (field.getCeil(position).getGameObjects()  instanceof Drunkard ){
            Drunkard drunkard = (Drunkard) field.getCeil(position).getGameObjects();
            return  drunkard.getState();
        }
        return false;
    }

    public Boolean getWithDrunkard() {
        return withDrunkard;
    }
    public void setWithDrunkard(Boolean withDrunkard) {
        this.withDrunkard = withDrunkard;
    }

    @Override
    public String symbol() {
        return "P";
    }

    enum PolicmanState{
        READY_POLICEMEN ,WALKING_POLICMEN;
    }

    public PolicmanState getState() {
        return state;
    }

    public void setState(PolicmanState state) {
        this.state = state;
    }
}
