import utils.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by kate on 16.09.14.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int polyVertexCount = Integer.parseInt(scanner.nextLine());
        Polygon polygon = new Polygon();
        for (int i = 0; i < polyVertexCount; i++) {
            Pair<Point, Integer> pair = new Pair<Point, Integer>(getPoint(scanner.nextLine()), i);
            polygon.addPointToPolygon(pair);
        }

        polygon.show();
        System.out.println("SORTED");
        polygon.sortVertexByXValue();
        polygon.show();
    }

    public static Point getPoint(String str) {
        String[] value = str.replaceAll("[,()]", "").split(" ");
        return new Point(Integer.parseInt(value[0]), Integer.parseInt(value[1]));
    }
}
