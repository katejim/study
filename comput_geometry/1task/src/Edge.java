/**
 * Created by kate on 16.09.14.
 */
public class Edge {
    private final Point begin;
    private final Point end;

    public Edge(Point begin, Point end) {
        this.begin = begin;
        this.end = end;
    }

    public Point getBegin() {
        return begin;
    }

    public Point getEnd() {
        return end;
    }

    public void show(){
        begin.show();
        System.out.print("   ");
        end.show();
        System.out.println();
    }
}