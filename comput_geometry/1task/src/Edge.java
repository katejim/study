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

//    int edgeType (Point &a, Edge &e)
//    {
//        Point v = e.org;
//        Point w = e.dest;
//        switch (a.classify(e)) {
//            case LEFT:
//                return ((v.y<a.y)&&(a.y<=w.y)) ? CROSSING : INESSENTIAL;
//            case RIGHT:
//                return ((w.y<a.y)&&(a.y<=v.y)) ? CROSSING : INESSENTIAL;
//            case BETWEEN:
//            case ORIGIN:
//            case DESTINATION:
//                return TOUCHING;
//            default:
//                return INESSENTIAL;
//        }
//    }
}
