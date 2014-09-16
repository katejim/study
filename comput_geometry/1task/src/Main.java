import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by kate on 16.09.14.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int polyVertexCount = Integer.parseInt(scanner.nextLine());
        Polygon polygon = new Polygon();
        for (int i = 0; i < polyVertexCount; i++) {
            polygon.addPointToPolygon(getPoint(scanner.nextLine()));
        }

        int checkVertexCount = Integer.parseInt(scanner.nextLine());
        Algo algo = new Algo(polygon);
        ArrayList<String> answers = new ArrayList<String>();
        for (int i = 0; i < checkVertexCount; i++) {
            answers.add(algo.pointInPolygon(getPoint(scanner.nextLine())));
        }
        for (int i = 0; i < checkVertexCount; i++){
            System.out.println(answers.get(i));
        }
    }

    public static Point getPoint(String str) {
        String[] value = str.replaceAll("[,()]", "").split(" ");
        return new Point(Integer.parseInt(value[0]), Integer.parseInt(value[1]));
    }
}
