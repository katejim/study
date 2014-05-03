package ru.spbau.ustuzhanina.drunkard.gameobjects;

import ru.spbau.ustuzhanina.drunkard.Constant;
import ru.spbau.ustuzhanina.drunkard.gamezone.Coordinates;

/**
 * Created by KateKate on 01.05.14.
 */
public interface IGameObject {
    public boolean isEmpty();

    public void setState(Constant.CeilState state);

    public String getSymbolToPrint();

    public void setSymbolToPrint(String symbolToPrint);

    public Constant.Actors getCeilObject();

    public void setCeilObject(Constant.Actors actor);

    public Coordinates getPosition();

    public void setPosition(Coordinates position);

    public Constant.ActiveState getActiveState();

    public void setActiveState(Constant.ActiveState state);
}
