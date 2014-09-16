import java.util.ArrayList;

/**
 * Created by kate on 16.09.14.
 */
public class Polygon {
    private ArrayList<Point> polygon;
    private static int counter = 0;

    public Polygon() {
        polygon = new ArrayList<Point>();
    }

    public Polygon(ArrayList<Point> polygon) {
        this.polygon = polygon;
    }

    public ArrayList<Point> getPolygon() {
        return polygon;
    }

    public void addPointToPolygon(Point point) {
        polygon.add(point);
    }

    public int size() {
        return polygon.size();
    }

    public Edge getEdge() {
        Edge rez = new Edge(polygon.get(counter % polygon.size()), polygon.get((counter + 1) % polygon.size()));
        counter += 1;
        return rez;
    }
}
