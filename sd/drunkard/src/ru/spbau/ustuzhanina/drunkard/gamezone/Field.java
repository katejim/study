package ru.spbau.ustuzhanina.drunkard.gamezone;

import ru.spbau.ustuzhanina.drunkard.Constant;
import ru.spbau.ustuzhanina.drunkard.gameobjects.GameObjects;

/**
 * Created by KateKate on 01.03.14.
 */
public class Field {
    private Ceil[][] allCeils;

    public Field() {
        allCeils = new Ceil[Constant.FIELD_WIDTH][Constant.FIELD_HEIGHT];
        for (int i = 0; i < Constant.FIELD_WIDTH; i++) {
            for (int j = 0; j < Constant.FIELD_HEIGHT; j++) {
                allCeils[i][j] = new Ceil();
            }
        }
    }

    public void printFieldState() {
        System.out.println("                  " + Constant.Symbols.PUB_CEIL_SYMBOL.symbol);
        for (int i = 0; i < Constant.FIELD_WIDTH ; i++) {
            for (int j = 0; j < Constant.FIELD_HEIGHT ; j++) {
                System.out.print(allCeils[j][i].getGameObjects().getSymbolToPrint() + " ");
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

    public boolean isCeilAvailable(Coordinates position) {
        if (!isWallBorder(position)) {
            if (!allCeils[position.getX()][position.getY()].getGameObjects().isEmpty()) {
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
        } else {
            return false;
        }
    }
}
