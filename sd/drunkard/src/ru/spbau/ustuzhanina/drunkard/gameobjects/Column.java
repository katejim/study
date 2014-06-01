package ru.spbau.ustuzhanina.drunkard.gameobjects;

import ru.spbau.ustuzhanina.drunkard.Constant;
import ru.spbau.ustuzhanina.drunkard.gamezone.Field;

/**
 * Created by KateKate on 02.03.14.
 */
public class Column extends GameObjects implements IStaticObj {
    @Override
    public void registerStatObj(Field field) {
        super.setPosition(Constant.InitialPos.COLUMN_POSITION.pos);
        field.setObject(this.getPosition(), this);
    }

    @Override
    public String symbol() {
        return "C";
    }
}
