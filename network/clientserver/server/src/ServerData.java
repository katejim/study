import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created by kate on 07.01.15.
 */
public class ServerData {
    private int[][] compatibility;

    public ServerData() {
        compatibility = new int[12][12];
        initCompability();
    }

    public int[][] getCompatibility() {
        return compatibility;
    }

    //    assume that format of table
    // Xi_j - значение совместимости знаков знаков зодиака (http://www.chtojebudet.ru/sovmestimost-znakov-zodiaka-v-lubvi.html)
//      male\female     овен телец близнец рак лев дева весы скорпион стрелец козерог водолей рыбы
//    овен               X0_0   X0_1      ..  .. .  ..
//    телец              X1_0   X1_1       .. .. ..
//    близнец            ..
//    рак                ..
//    лев
//    дева
//    весы
//    скорпион
//    стрелец
//    козерог
//    водолей            ..
//    рыбы               X11_0 ..
    private void initCompability() {
        //овен male
        compatibility[0][0] = 45;
        compatibility[0][1] = 73;
        compatibility[0][2] = 46;
        compatibility[0][3] = 47;
        compatibility[0][4] = 59;
        compatibility[0][5] = 48;
        compatibility[0][6] = 66;
        compatibility[0][7] = 59;
        compatibility[0][8] = 67;
        compatibility[0][9] = 43;
        compatibility[0][10] = 89;
        compatibility[0][11] = 43;

        //телец male
        compatibility[1][0] = 85;
        compatibility[1][1] = 89;
        compatibility[1][2] = 72;
        compatibility[1][3] = 79;
        compatibility[1][4] = 54;
        compatibility[1][5] = 76;
        compatibility[1][6] = 67;
        compatibility[1][7] = 89;
        compatibility[1][8] = 79;
        compatibility[1][9] = 79;
        compatibility[1][10] = 63;
        compatibility[1][11] = 91;

        //близнец male
        compatibility[2][0] = 51;
        compatibility[2][1] = 63;
        compatibility[2][2] = 75;
        compatibility[2][3] = 57;
        compatibility[2][4] = 48;
        compatibility[2][5] = 56;
        compatibility[2][6] = 73;
        compatibility[2][7] = 60;
        compatibility[2][8] = 66;
        compatibility[2][9] = 86;
        compatibility[2][10] = 89;
        compatibility[2][11] = 38;

        //рак male
        compatibility[3][0] = 48;
        compatibility[3][1] = 92;
        compatibility[3][2] = 67;
        compatibility[3][3] = 51;
        compatibility[3][4] = 95;
        compatibility[3][5] = 87;
        compatibility[3][6] = 74;
        compatibility[3][7] = 79;
        compatibility[3][8] = 55;
        compatibility[3][9] = 56;
        compatibility[3][10] = 71;
        compatibility[3][11] = 73;

        //лев male
        compatibility[4][0] = 49;
        compatibility[4][1] = 53;
        compatibility[4][2] = 43;
        compatibility[4][3] = 94;
        compatibility[4][4] = 45;
        compatibility[4][5] = 68;
        compatibility[4][6] = 69;
        compatibility[4][7] = 76;
        compatibility[4][8] = 88;
        compatibility[4][9] = 79;
        compatibility[4][10] = 68;
        compatibility[4][11] = 43;

        //дева male
        compatibility[5][0] = 39;
        compatibility[5][1] = 55;
        compatibility[5][2] = 54;
        compatibility[5][3] = 90;
        compatibility[5][4] = 76;
        compatibility[5][5] = 62;
        compatibility[5][6] = 62;
        compatibility[5][7] = 78;
        compatibility[5][8] = 78;
        compatibility[5][9] = 58;
        compatibility[5][10] = 38;
        compatibility[5][11] = 53;

        //весы male
        compatibility[6][0] = 58;
        compatibility[6][1] = 56;
        compatibility[6][2] = 66;
        compatibility[6][3] = 74;
        compatibility[6][4] = 89;
        compatibility[6][5] = 62;
        compatibility[6][6] = 69;
        compatibility[6][7] = 64;
        compatibility[6][8] = 87;
        compatibility[6][9] = 49;
        compatibility[6][10] = 90;
        compatibility[6][11] = 55;

        //скорпион male
        compatibility[7][0] = 53;
        compatibility[7][1] = 84;
        compatibility[7][2] = 58;
        compatibility[7][3] = 68;
        compatibility[7][4] = 92;
        compatibility[7][5] = 72;
        compatibility[7][6] = 54;
        compatibility[7][7] = 38;
        compatibility[7][8] = 96;
        compatibility[7][9] = 54;
        compatibility[7][10] = 52;
        compatibility[7][11] = 87;

        //стрелец male
        compatibility[8][0] = 61;
        compatibility[8][1] = 49;
        compatibility[8][2] = 71;
        compatibility[8][3] = 61;
        compatibility[8][4] = 93;
        compatibility[8][5] = 53;
        compatibility[8][6] = 85;
        compatibility[8][7] = 95;
        compatibility[8][8] = 91;
        compatibility[8][9] = 66;
        compatibility[8][10] = 89;
        compatibility[8][11] = 88;

        //козирог male
        compatibility[9][0] = 58;
        compatibility[9][1] = 95;
        compatibility[9][2] = 72;
        compatibility[9][3] = 63;
        compatibility[9][4] = 88;
        compatibility[9][5] = 49;
        compatibility[9][6] = 45;
        compatibility[9][7] = 64;
        compatibility[9][8] = 40;
        compatibility[9][9] = 84;
        compatibility[9][10] = 78;
        compatibility[9][11] = 91;

        //водолей male
        compatibility[10][0] = 72;
        compatibility[10][1] = 56;
        compatibility[10][2] = 78;
        compatibility[10][3] = 61;
        compatibility[10][4] = 78;
        compatibility[10][5] = 38;
        compatibility[10][6] = 89;
        compatibility[10][7] = 50;
        compatibility[10][8] = 75;
        compatibility[10][9] = 67;
        compatibility[10][10] = 76;
        compatibility[10][11] = 71;

        //рыбы male
        compatibility[11][0] = 45;
        compatibility[11][1] = 92;
        compatibility[11][2] = 39;
        compatibility[11][3] = 72;
        compatibility[11][4] = 52;
        compatibility[11][5] = 63;
        compatibility[11][6] = 68;
        compatibility[11][7] = 65;
        compatibility[11][8] = 82;
        compatibility[11][9] = 69;
        compatibility[11][10] = 46;
        compatibility[11][11] = 76;
    }
}
