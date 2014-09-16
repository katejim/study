/**
 * Created by kate on 16.09.14.
 */
public class Algo {
    private final Polygon polygon;

    public Algo(Polygon polygon) {
        this.polygon = polygon;
    }

    enum POINTLOCALIZATION {yes, no}

    enum EDGELOCALIZATION {TOUCHING, CROSSING, INESSENTIAL}

    EDGELOCALIZATION edgeType(Point point, Edge edge) {
        Point v = edge.getBegin();
        Point w = edge.getEnd();

        switch (point.classifyOrientation(edge)) {
            case LEFT:
                return ((v.getY() < point.getY()) && (point.getY() <= w.getY())) ? EDGELOCALIZATION.CROSSING : EDGELOCALIZATION.INESSENTIAL;
            case RIGHT:
                return ((w.getY() < point.getY()) && (point.getY() <= v.getY())) ? EDGELOCALIZATION.CROSSING : EDGELOCALIZATION.INESSENTIAL;
            case BETWEEN:
            case ORIGIN:
            case DESTINATION:
                return EDGELOCALIZATION.TOUCHING;
            default:
                return EDGELOCALIZATION.INESSENTIAL;
        }
    }

    String pointInPolygon(Point point) {
        int parity = 0;
        for (int i = 0; i < polygon.size(); i++) {
            Edge e = polygon.getEdge();
            switch (edgeType(point, e)) {
                case TOUCHING:
                    return POINTLOCALIZATION.yes.toString();
                case CROSSING:
                    parity = 1 - parity;
            }
        }
        return (parity != 0 ? POINTLOCALIZATION.yes.toString() : POINTLOCALIZATION.no.toString());
    }
}
