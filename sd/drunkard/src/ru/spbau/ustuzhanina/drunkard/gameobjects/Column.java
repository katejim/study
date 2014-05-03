package ru.spbau.ustuzhanina.drunkard.gameobjects;

import ru.spbau.ustuzhanina.drunkard.Constant;
import ru.spbau.ustuzhanina.drunkard.gamezone.Field;

/**
 * Created by KateKate on 02.03.14.
 */
public class Column extends GameObjects implements IStaticObj {
    @Override
    public void registerStatObj(Field field) {
        super.setState(Constant.CeilState.BUSY_CEIL);
        super.setCeilObject(Constant.Actors.COLUMN);
        super.setSymbolToPrint(Constant.Symbols.COLUMN_CEIL_SYMBOL.symbol);
        super.setPosition(Constant.InitialPos.COLUMN_POSITION.pos);
        field.setObject(this.getPosition(), this);
    }
}
