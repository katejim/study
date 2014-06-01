package ru.spbau.ustuzhanina.drunkard.gamezone;

import ru.spbau.ustuzhanina.drunkard.Constant;

import java.util.List;

/**
 * Created by KateKate on 25.05.14.
 */
public class HexagonalField extends Field {
    @Override
    public void printFieldState() {
        System.out.println();
        for (int j = 0; j < Constant.FIELD_HEIGHT; j++) {
            if (j % 2 == 0) {
                System.out.print(" ");
                for (int i = 0; i < Constant.FIELD_WIDTH - 1; i += 1) {
                    System.out.print(allCeils[i][j].getGameObjects().symbol() + " ");
                }
            } else {
                for (int i = 0; i < Constant.FIELD_WIDTH; i += 1) {
                    System.out.print(allCeils[i][j].getGameObjects().symbol() + " ");
                }
            }
            System.out.println();
        }
    }

    @Override
    public boolean isWallBorder(Coordinates position) {
        int curWidth = Constant.FIELD_WIDTH * 2 - 1;
        if ((position.getY() % 2) == 0) {
            if ((position.getX() < 0) ||
                    (position.getX() >= Constant.FIELD_WIDTH - 1)) {
                return true;
            }
        } else {
            if ((position.getX() < 0) || (position.getX() >= Constant.FIELD_WIDTH)) {
                return true;
            }
        }
        if ((position.getY() < 0) || (position.getY() >= Constant.FIELD_HEIGHT)) {
            return true;
        }
        return false;
    }

    @Override
    public List<Coordinates> getNearCeil(Coordinates position) {
        List<Coordinates> result = super.getNearCeil(position);
        if (position.getY() % 2 == 0) {
            result.add(new Coordinates(position.getX() + 1, position.getY() - 1));
            result.add(new Coordinates(position.getX() + 1, position.getY() + 1));
        } else {
            result.add(new Coordinates(position.getX() - 1, position.getY() - 1));
            result.add(new Coordinates(position.getX() - 1, position.getY() + 1));
        }
        return result;
    }
}
