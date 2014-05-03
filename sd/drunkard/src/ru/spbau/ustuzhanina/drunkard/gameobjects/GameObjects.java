package ru.spbau.ustuzhanina.drunkard.gameobjects;

import ru.spbau.ustuzhanina.drunkard.Constant;
import ru.spbau.ustuzhanina.drunkard.gamezone.Coordinates;

/**
 * Created by KateKate on 02.03.14.
 */
public class GameObjects implements IGameObject{
    private Constant.CeilState state;
    private String symbolToPrint;
    private Constant.Actors actor;
    private Coordinates position;
    private Constant.ActiveState activeState;

    @Override
    public boolean isEmpty() {
        return state != Constant.CeilState.FREE_CEIL;
    }
    @Override
    public void setState(Constant.CeilState state) {
        this.state = state;
    }
    @Override
    public String getSymbolToPrint() {
        return symbolToPrint;
    }
    @Override
    public void setSymbolToPrint(String symbolToPrint) {
        this.symbolToPrint = symbolToPrint;
    }
    @Override
    public Constant.Actors getCeilObject() {
        return actor;
    }
    @Override
    public void setCeilObject(Constant.Actors actor) {
        this.actor = actor;
    }
    @Override
    public Coordinates getPosition() {
        return position;
    }
    @Override
    public void setPosition(Coordinates position) {
        this.position = position;
    }

    @Override
    public Constant.ActiveState getActiveState() {
        return activeState;
    }

    @Override
    public void setActiveState(Constant.ActiveState activeState) {
        this.activeState = activeState;
    }
}
