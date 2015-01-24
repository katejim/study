import javafx.util.Pair;

import java.util.*;

/**
 * Created by kate on 06.01.15.
 */
class ClientData {
    private static final Integer MALE = 1;
    private static final Integer FEMALE = 0;
    private ArrayList<Person> params;
    private static int nameCounter = 0;

    public ClientData() {
        params = new ArrayList<Person>();
        generateData();
    }


    public ArrayList<Person> getParams() {
        return params;
    }

    private void generateData() {
        int femalecount = 0;
        Random random = new Random();
        int paramSize = randBetween(8, 20);
        for (int i = 0; i < paramSize; i++) {
            Integer month = randBetween(0, 11);
            boolean male = random.nextBoolean();
            int name = nameCounter;
            params.add(new Person(month, male ? MALE : FEMALE, name));
            if (!male){
                femalecount++;
            }
            nameCounter++;
        }
        if (femalecount == 0){
            params.get(paramSize-1).setMale(FEMALE);
        }
        if (femalecount == paramSize){
            params.get(paramSize-1).setMale(MALE);
        }
    }

    private static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    class Person {
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

        public void setMale(int male) {
            this.male = male;
        }
    }
}