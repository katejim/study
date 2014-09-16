/**
 * Created by kate on 16.09.14.
 */
public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point sum(Point fst, Point snd) {
        return new Point(fst.x + fst.x, snd.y + snd.y);
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

        double sa = a.x * b.y - b.x * a.y;
        if (sa > 0) {
            return ORIENTATION.LEFT;
        }
        if (sa < 0) {
            return ORIENTATION.RIGHT;
        }
        if ((a.x * b.x < 0.0) || (a.y * b.y < 0.0)) {
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
        System.out.println(x + " " + y);
    }
}
