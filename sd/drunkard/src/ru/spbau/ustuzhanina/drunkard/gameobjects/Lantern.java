package ru.spbau.ustuzhanina.drunkard.gameobjects;

import ru.spbau.ustuzhanina.drunkard.Constant;
import ru.spbau.ustuzhanina.drunkard.gamezone.Field;

/**
 * Created by KateKate on 02.05.14.
 */
public class Lantern extends GameObjects implements IStaticObj {
    @Override
    public void registerStatObj(Field field) {
        super.setPosition(Constant.InitialPos.LANTERN_POSITION.pos);
        field.setObject(this.getPosition(), this);
    }

    @Override
    public String symbol() {
        return "L";
    }
}
