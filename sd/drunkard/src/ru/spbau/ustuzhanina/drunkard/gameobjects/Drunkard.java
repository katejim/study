package ru.spbau.ustuzhanina.drunkard.gameobjects;

import ru.spbau.ustuzhanina.drunkard.Constant;
import ru.spbau.ustuzhanina.drunkard.gamezone.Coordinates;
import ru.spbau.ustuzhanina.drunkard.gamezone.Field;

import java.util.List;

/**
 * Created by KateKate on 01.03.14.
 */
public class Drunkard extends GameObjects implements IActiveObj {
    private boolean withBottle;
    private Field field;
    private DrunkardState state = DrunkardState.READY_DRUNKARD;

    public Drunkard(Field field) {
        setPosition(Constant.InitialPos.DRUNCARD_INITIAL_POSITION.pos);
        this.field = field;
        withBottle = true;
    }


    @Override
    public Boolean makeTurn() {
        if (state != DrunkardState.READY_DRUNKARD) {
            return false;
        }
        Coordinates newPosition = generateNewPositioin();
        if (field.isCeilAvailable(newPosition)) {
            field.delObject(getPosition());
            handleDrunkardBottle();
            field.setObject(newPosition, this);
            setPosition(newPosition);
        } else {
            if (!field.isWallBorder(newPosition)) {
                if (isBottleHere(newPosition)) {
                    state = DrunkardState.LAY_DRUNKARD;
                } else if (isColumnHere(newPosition)) {
                    state = DrunkardState.SLEEP_DRUNKARD;
                } else if (isSleepDrunkurdHere(newPosition)) {
                    state = DrunkardState.SLEEP_DRUNKARD;
                }
            }
        }
        return true;
    }

    @Override
    public String symbol() {
        switch (state) {
            case READY_DRUNKARD:
                return "D";
            case SLEEP_DRUNKARD:
                return "Z";
            case LAY_DRUNKARD:
                return "&";
        }
        return null;
    }

    private void handleDrunkardBottle() {
        if (isWithBottle()) {
            int randLeaveBottle = (int) (Math.random() * 300);
            if (randLeaveBottle <= 10) {
                setWithBottle(false);
                field.setObject(getPosition(), new Bottle(getPosition()));
            }
        }
    }

    private Coordinates generateNewPositioin() {
        List<Coordinates> nearCeils = field.getNearCeil(getPosition());
        int direction = (int) (Math.random() * 120);
        int koeff = nearCeils.size() == 4 ? 30 : 20;

        if (direction < koeff) {
            return nearCeils.get(0);
        } else if (direction < 2 * koeff) {
            return nearCeils.get(1);
        } else if (direction < 3 * koeff) {
            return nearCeils.get(2);
        } else if (direction < 4 * koeff) {
            return nearCeils.get(3);
        }
        if (nearCeils.size() == 6) {
            if (direction < 5 * koeff) {
                return nearCeils.get(4);
            } else {
                return nearCeils.get(5);
            }
        }
        return null;
    }

    public Boolean getState(){
        if (state == DrunkardState.LAY_DRUNKARD || state == DrunkardState.SLEEP_DRUNKARD){
            return true;
        }
        return false;
    }

    private boolean isBottleHere(Coordinates position) {
        return (field.getCeil(position).getGameObjects() instanceof Bottle);
    }

    private boolean isColumnHere(Coordinates position) {
        return (field.getCeil(position).getGameObjects() instanceof Column);
    }

    private boolean isSleepDrunkurdHere(Coordinates position) {
        return ((field.getCeil(position).getGameObjects() instanceof Drunkard)
                && (state == DrunkardState.SLEEP_DRUNKARD));
    }

    public boolean isWithBottle() {
        return withBottle;
    }

    public void setWithBottle(boolean withBottle) {
        this.withBottle = withBottle;
    }

    public enum DrunkardState {
        SLEEP_DRUNKARD, READY_DRUNKARD, LAY_DRUNKARD
    }
}
