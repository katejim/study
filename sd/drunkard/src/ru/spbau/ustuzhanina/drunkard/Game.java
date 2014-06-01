package ru.spbau.ustuzhanina.drunkard;

import ru.spbau.ustuzhanina.drunkard.gameobjects.Column;
import ru.spbau.ustuzhanina.drunkard.gameobjects.IActiveObj;
import ru.spbau.ustuzhanina.drunkard.gameobjects.IStaticObj;
import ru.spbau.ustuzhanina.drunkard.gameobjects.Lantern;
import ru.spbau.ustuzhanina.drunkard.gamezone.Field;
import ru.spbau.ustuzhanina.drunkard.gamezone.HexagonalField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KateKate on 02.03.14.
 */
public class Game {
    public Field field;

    public List<IActiveObj> activeObj;
    public List<IActiveObj> newObj;
    public List<IActiveObj> nonActiveObj;


    Game(boolean mode) {
        if (mode) {
            field = new HexagonalField();
        } else {
            field = new Field();
        }
        activeObj = new ArrayList<IActiveObj>();
        newObj = new ArrayList<IActiveObj>();
        nonActiveObj = new ArrayList<IActiveObj>();
    }

    public void oneGameStep() {
        activeObj.addAll(newObj);
        newObj.clear();
        nonActiveObj.clear();

        for (IActiveObj obj : activeObj) {
            if (!obj.makeTurn()) {
                nonActiveObj.add(obj);
            }
            field.printFieldState();
        }

        for (IActiveObj obj : nonActiveObj) {
            activeObj.remove(obj);
        }
        System.out.println();
    }

    public void registerAllStaticObj() {
        Column column = new Column();
        Lantern lantern = new Lantern();
        List<IStaticObj> staticObjList = new ArrayList<IStaticObj>();
        staticObjList.add(column);
        staticObjList.add(lantern);

        for (IStaticObj statObj : staticObjList) {
            statObj.registerStatObj(field);
        }
    }

    public void addActiveObj(IActiveObj activeObj1) {
        newObj.add(activeObj1);
    }

    public void addNoActiveObj(IActiveObj activeObj1) {
        nonActiveObj.add(activeObj1);
    }
}
