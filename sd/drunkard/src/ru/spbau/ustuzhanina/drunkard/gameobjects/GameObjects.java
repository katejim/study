package ru.spbau.ustuzhanina.drunkard.gameobjects;

import ru.spbau.ustuzhanina.drunkard.Constant;
import ru.spbau.ustuzhanina.drunkard.gamezone.Coordinates;

/**
 * Created by KateKate on 02.03.14.
 */
public class GameObjects implements IGameObject{
    private Coordinates position;

    @Override
    public String symbol(){
        return ".";
    }

    @Override
    public Coordinates getPosition() {
        return position;
    }

    @Override
    public void setPosition(Coordinates position) {
        this.position = position;
    }
}
