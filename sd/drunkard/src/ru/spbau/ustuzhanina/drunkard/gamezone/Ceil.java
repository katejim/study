package ru.spbau.ustuzhanina.drunkard.gamezone;

import ru.spbau.ustuzhanina.drunkard.Constant;
import ru.spbau.ustuzhanina.drunkard.gameobjects.EmptyObject;
import ru.spbau.ustuzhanina.drunkard.gameobjects.GameObjects;

/**
 * Created by KateKate on 01.03.14.
 */

public class Ceil {
    private GameObjects gameObjects;

    public Ceil() {
        this.gameObjects = new EmptyObject();
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public Boolean isEmpty(){
        return this.getGameObjects().getCeilObject() == Constant.Actors.NO;
    }

    public void setGameObjects(GameObjects gameObjects) {
        this.gameObjects = gameObjects;
    }

    public void delObject() {
        this.gameObjects = new EmptyObject();
    }

}
