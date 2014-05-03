package ru.spbau.ustuzhanina.drunkard.gameobjects;

import ru.spbau.ustuzhanina.drunkard.Constant;

/**
 * Created by KateKate on 02.03.14.
 */
public class EmptyObject extends GameObjects {
    public EmptyObject() {
        super.setSymbolToPrint(Constant.Symbols.FREE_CEIL_SYMBOL.symbol);
        super.setState(Constant.CeilState.FREE_CEIL);
        super.setCeilObject(Constant.Actors.NO);
    }
}
