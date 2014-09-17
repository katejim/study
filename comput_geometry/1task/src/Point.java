/**
 * Created by kate on 16.09.14.
 */
public class Point {
    private final long x;
    private final long y;

    public Point(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }


    public Point sub(Point fst, Point snd) {
        return new Point(snd.x - fst.x, snd.y - fst.y);
    }

    public boolean equal(Point fst, Point snd) {
        return ((fst.x == snd.x) && (fst.y == snd.y));
    }

    private double length() {
        return Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0));
    }

    public enum ORIENTATION {LEFT, RIGHT, BEYOND, BEHIND, BETWEEN, ORIGIN, DESTINATION}


    public ORIENTATION classifyOrientation(Edge edge) {
        Point begin = edge.getBegin();
        Point end = edge.getEnd();

        Point current = this;

        Point a = sub(begin, end);
        Point b = sub(begin, current);

        long sa = a.x * b.y - b.x * a.y;
        if (sa > 0) {
            return ORIENTATION.LEFT;
        }
        if (sa < 0) {
            return ORIENTATION.RIGHT;
        }
        if ((a.x * b.x < 0) || (a.y * b.y < 0)) {
            return ORIENTATION.BEHIND;
        }
        if (a.length() < b.length()) {
            return ORIENTATION.BEYOND;
        }
        if (equal(begin, current)) {
            return ORIENTATION.ORIGIN;
        }
        if (equal(end, current)) {
            return ORIENTATION.DESTINATION;
        }
        return ORIENTATION.BETWEEN;
    }

    public void show() {
        System.out.print(x + " " + y);
    }
}
