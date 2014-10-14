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


    public Point sub(Point fst, Point snd) {
        return new Point(snd.x - fst.x, snd.y - fst.y);
    }

    public boolean equal(Point fst, Point snd) {
        return ((fst.x == snd.x) && (fst.y == snd.y));
    }

    private double length() {
        return Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0));
    }

    public void show() {
        System.out.print(x + " " + y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        if (y != point.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public int compareTo(Point another){
        Point point  = (Point)another;
        if (this.x < another.getX()){
            return -1;
        } else if(this.x > another.getX()){
            return 1;
        } else {
            return 0;
        }
    }

}
