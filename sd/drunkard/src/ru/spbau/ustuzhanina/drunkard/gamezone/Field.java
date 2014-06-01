package ru.spbau.ustuzhanina.drunkard.gamezone;

import ru.spbau.ustuzhanina.drunkard.Constant;
import ru.spbau.ustuzhanina.drunkard.gameobjects.GameObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KateKate on 01.03.14.
 */
public class Field {
    protected Ceil[][] allCeils;

    public Field() {
        allCeils = new Ceil[Constant.FIELD_WIDTH][Constant.FIELD_HEIGHT];
        for (int i = 0; i < Constant.FIELD_WIDTH; i++) {
            for (int j = 0; j < Constant.FIELD_HEIGHT; j++) {
                allCeils[i][j] = new Ceil();
            }
        }
    }

    public void printFieldState() {
        System.out.println();
        for (int i = 0; i < Constant.FIELD_WIDTH; i++) {
            for (int j = 0; j < Constant.FIELD_HEIGHT; j++) {
                System.out.print(allCeils[j][i].getGameObjects().symbol() + " ");
            }
            System.out.println();
        }
    }

    public void setObject(Coordinates position, GameObjects gameObjects) {
        allCeils[position.getX()][position.getY()].setGameObjects(gameObjects);
    }

    public void delObject(Coordinates position) {
        allCeils[position.getX()][position.getY()].delObject();
    }


    public Ceil getCeil(Coordinates position) {
        return allCeils[position.getX()][position.getY()];
    }


    public List<Coordinates> getNearCeil(Coordinates position){
        List <Coordinates> result = new ArrayList<Coordinates>();
        result.add(new Coordinates(position.getX() +1, position.getY()));
        result.add(new Coordinates(position.getX() -1, position.getY()));
        result.add(new Coordinates(position.getX(),position.getY() + 1));
        result.add(new Coordinates(position.getX(),position.getY() - 1));
        return  result;
    }
    public boolean isCeilAvailable(Coordinates position) {
        if (!isWallBorder(position)) {
            if (!allCeils[position.getX()][position.getY()].isEmpty()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean isWallBorder(Coordinates position) {
        if ((position.getX() < 0) || (position.getX() >= Constant.FIELD_WIDTH)
                || (position.getY() < 0) || (position.getY() >= Constant.FIELD_HEIGHT)) {
            return true;
        }
        return false;
    }

    public void moveObject(Coordinates oldPosition, Coordinates newPosition, GameObjects obj){
        delObject(oldPosition);
        setObject(newPosition, obj);
        obj.setPosition(newPosition);
    }
}
