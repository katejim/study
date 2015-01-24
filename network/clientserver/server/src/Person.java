/**
 * Created by kate on 07.01.15.
 */
public class Person {
    public static final Integer MALE = 1;
    public static final Integer FEMALE = 0;

    private int month;
    private int male;
    private int name;

    Person(int month, int male, int name) {
        this.month = month;
        this.male = male;
        this.name = name;
    }

    public int getMonth() {
        return month;
    }

    public int getMale() {
        return male;
    }

    public int getName() {
        return name;
    }
}
