package ru.spbau.ustuzhanina.drunkard.gameobjects;

import ru.spbau.ustuzhanina.drunkard.Constant;
import ru.spbau.ustuzhanina.drunkard.gamezone.Coordinates;

/**
 * Created by KateKate on 02.03.14.
 */
public class Bottle extends GameObjects {
    public Bottle(Coordinates position) {
        super.setPosition(position);
    }

    @Override
    public String symbol() {
        return "B";
    }
}
