package ru.spbau.ustuzhanina.drunkard.gameobjects;

import ru.spbau.ustuzhanina.drunkard.Constant;
import ru.spbau.ustuzhanina.drunkard.gamezone.Coordinates;
import ru.spbau.ustuzhanina.drunkard.gamezone.Field;

/**
 * Created by KateKate on 01.03.14.
 */
public class Drunkard extends GameObjects implements IActiveObj {
    private boolean withBottle;
    private  Field field;

    public Drunkard(Field field) {
        setState(Constant.CeilState.BUSY_CEIL);
        setCeilObject(Constant.Actors.DRUNKARD);
        setSymbolToPrint(Constant.Symbols.DRUNKARD_CEIL_SYMBOL.symbol);
        setPosition(Constant.InitialPos.DRUNCARD_INITIAL_POSITION.pos);
        this.field = field;
        setActiveState(Constant.ActiveState.READY_DRUNKARD);
        withBottle = true;
    }


    @Override
    public Boolean doSomething() {
        if (getActiveState() != Constant.ActiveState.READY_DRUNKARD){
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
                    setActiveState(Constant.ActiveState.LAY_DRUNKARD);
                    setSymbolToPrint(Constant.Symbols.DRUNKARD_LAY_CEIL_SYMBOL.symbol);
                } else if (isColumnHere(newPosition)) {
                    setActiveState(Constant.ActiveState.SLEEP_DRUNKARD);
                    setSymbolToPrint(Constant.Symbols.DRUNKARD_SLEEP_CEIL_SYMBOL.symbol);
                } else if (isSleepDrunkurdHere(newPosition)) {
                    setActiveState(Constant.ActiveState.SLEEP_DRUNKARD);
                    setSymbolToPrint(Constant.Symbols.DRUNKARD_SLEEP_CEIL_SYMBOL.symbol);
                }
            }
        }
        return true;
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
        int direction = (int) (Math.random() * 100);
        Coordinates newPosition;
        //down
        if (direction < 25) {
            newPosition = new Coordinates(getPosition().getX() - 1, getPosition().getY());
            //up
        } else if (direction < 50) {
            newPosition = new Coordinates(getPosition().getX(), getPosition().getY() - 1);
            //down
        } else if (direction < 75) {
            newPosition = new Coordinates(getPosition().getX(), getPosition().getY() + 1);
            //right
        } else {
            newPosition = new Coordinates(getPosition().getX() + 1, getPosition().getY());
        }
        return newPosition;
    }

    private boolean isBottleHere(Coordinates position) {
        return (field.getCeil(position).getGameObjects().getCeilObject() == Constant.Actors.BOTTLE);
    }

    private boolean isColumnHere(Coordinates position) {
        return  (field.getCeil(position).getGameObjects().getCeilObject() == Constant.Actors.COLUMN);
    }

    private boolean isSleepDrunkurdHere(Coordinates position) {
        return ((field.getCeil(position).getGameObjects().getCeilObject() == Constant.Actors.DRUNKARD)
                && (this.getActiveState() == Constant.ActiveState.SLEEP_DRUNKARD));
    }

    public boolean isWithBottle() {
        return withBottle;
    }
    public void setWithBottle(boolean withBottle) {
        this.withBottle = withBottle;
    }
}
