import utils.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

/**
 * Created by kate on 16.09.14.
 */
public class Polygon {
    private ArrayList<Pair<Point, Integer>> polygon;
    private static int counter = 0;

    public Polygon() {
        polygon = new ArrayList<Pair<Point, Integer>>();
    }

    public Polygon(ArrayList<Pair<Point, Integer>> polygon) {
        this.polygon = polygon;
    }

    public ArrayList<Pair<Point, Integer>> getPolygon() {
        return polygon;
    }

    public void addPointToPolygon(Pair<Point, Integer> point) {
        polygon.add(point);
    }

    public int size() {
        return polygon.size();
    }

    public Edge getEdge() {
        Edge rez = new Edge(polygon.get(counter % polygon.size()).first, polygon.get((counter + 1) % polygon.size()).first);
        counter += 1;
        return rez;
    }
    public void sortVertexByXValue(){
        Collections.sort(polygon, new Comparator<Pair<Point, Integer>>() {
            @Override
            public int compare(Pair<Point, Integer> o1, Pair<Point, Integer> o2) {
                return o1.first.compareTo(o2.first);
            }
        });
    }

    public void show(){
        for(Pair<Point, Integer> pair:polygon){
            pair.first.show();
            System.out.println(pair.second);
        }
    }
}
