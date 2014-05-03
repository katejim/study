package ru.spbau.ustuzhanina.drunkard;

import ru.spbau.ustuzhanina.drunkard.gamezone.Coordinates;
import ru.spbau.ustuzhanina.drunkard.gamezone.Field;

import java.util.*;

/**
 * Created by KateKate on 02.05.14.
 */
public class PathFind {
    LinkedList<Coordinates> path;
    Map<Coordinates, Coordinates> parents;
    Field field;

    public PathFind(Field field) {
        this.field = field;
        path = new LinkedList<Coordinates>();
        parents = new HashMap<Coordinates, Coordinates>();
    }

    private Boolean findPath(Coordinates from, Coordinates to) {
        path.clear();

        Coordinates cur = from;
        Queue<Coordinates> queue = new LinkedList<Coordinates>();
        //(child, parent)
        parents = new HashMap<Coordinates, Coordinates>();

        queue.add(cur);
        parents.put(cur, null);

        while ((cur != null) && !(cur.equals(to) && (!queue.isEmpty()))) {
            cur = queue.poll();
            List<Coordinates> near = getNearCeil(cur, to);
            for (Coordinates ceil : near) {
                if (!parents.containsKey(ceil)) {
                    queue.add(ceil);
                    parents.put(ceil, cur);
                }
            }
        }

        if (!parents.containsKey(to)) {
            return false;
        }

        Coordinates parent = to;
        while (parent != null) {
            path.add(parent);
            parent = parents.get(parent);
        }

        path.removeLast();
        return true;
    }


    public Coordinates getNextCoordinates(Coordinates from, Coordinates to) {
        boolean fl = findPath(from, to);

        if ((path.size() != 0) && fl){
            return path.removeLast();
        } else return null;
    }


    private List<Coordinates> getNearCeil(Coordinates ceil, Coordinates to) {
        List<Coordinates> rez = new ArrayList<Coordinates>();
        if (ceil != null && to != null) {
            Coordinates newCoordinate = new Coordinates(ceil.getX() - 1, ceil.getY());
            if (!field.isWallBorder(newCoordinate)) {
                if (field.isCeilAvailable(new Coordinates(ceil.getX() - 1, ceil.getY())) || (new Coordinates(ceil.getX() - 1, ceil.getY()).equals(to))) {
                    rez.add(new Coordinates(ceil.getX() - 1, ceil.getY()));
                }
            }
            newCoordinate = new Coordinates(ceil.getX(), ceil.getY() - 1);
            if (!field.isWallBorder(newCoordinate)) {
                if (field.isCeilAvailable(new Coordinates(ceil.getX(), ceil.getY() - 1)) || (new Coordinates(ceil.getX(), ceil.getY() - 1).equals(to))) {
                    rez.add(new Coordinates(ceil.getX(), ceil.getY() - 1));
                }
            }
            newCoordinate = new Coordinates(ceil.getX() + 1, ceil.getY());
            if (!field.isWallBorder(newCoordinate)) {
                if (field.isCeilAvailable(new Coordinates(ceil.getX() + 1, ceil.getY())) || (new Coordinates(ceil.getX() + 1, ceil.getY()).equals(to))) {
                    rez.add(new Coordinates(ceil.getX() + 1, ceil.getY()));
                }
            }
            newCoordinate = new Coordinates(ceil.getX(), ceil.getY() + 1);
            if (!field.isWallBorder(newCoordinate)) {
                if (field.isCeilAvailable(new Coordinates(ceil.getX(), ceil.getY() + 1)) || (new Coordinates(ceil.getX(), ceil.getY() + 1).equals(to))) {
                    rez.add(new Coordinates(ceil.getX(), ceil.getY() + 1));
                }
            }
        }
        return rez;
    }
}

