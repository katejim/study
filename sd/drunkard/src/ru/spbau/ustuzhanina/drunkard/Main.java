package ru.spbau.ustuzhanina.drunkard;

import ru.spbau.ustuzhanina.drunkard.gameobjects.Bar;
import ru.spbau.ustuzhanina.drunkard.gameobjects.GlassJarHouse;
import ru.spbau.ustuzhanina.drunkard.gameobjects.PoliceStation;
import ru.spbau.ustuzhanina.drunkard.gamezone.HexagonalField;

/**
 * Created by KateKate on 01.03.14.
 */
public class Main {
    static Game game;

    public static void main(String[] args) {
        boolean hexagonal = false;
        if (args.length > 0){
            hexagonal = true;
        }
        HexagonalField field = new HexagonalField();
        field.printFieldState();

        game = new Game(hexagonal);
        game.registerAllStaticObj();

        Bar bar = new Bar(game);
        PoliceStation policeStation = new PoliceStation(game);
        GlassJarHouse glassJarHouse = new GlassJarHouse(game);

        game.addActiveObj(bar);
        game.addActiveObj(policeStation);
        game.addActiveObj(glassJarHouse);

        for (int i = 0; i != 500; i++) {
            game.oneGameStep();
            windowDelay();
        }
    }

    private static void windowDelay() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        System.out.print('\r');
    }
}

