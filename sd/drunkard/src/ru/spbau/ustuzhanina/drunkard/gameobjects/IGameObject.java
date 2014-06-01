package ru.spbau.ustuzhanina.drunkard.gameobjects;

import ru.spbau.ustuzhanina.drunkard.Constant;
import ru.spbau.ustuzhanina.drunkard.gamezone.Coordinates;

/**
 * Created by KateKate on 01.05.14.
 */
public interface IGameObject {
    public String symbol();
    public Coordinates getPosition();
    public void setPosition(Coordinates position);
}
