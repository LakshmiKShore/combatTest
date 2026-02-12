import java.util.ArrayList;

/* TODO:
    - Write creature class that Enemy and Player can extend from
    - Rewrite weapon class to use arrays
    - Create initiative system
    - Create feature class
    - Create character creation system
    - Create leveling system
 */

public class Main {

    static final int physical = 0;
    static final int elemental = 1;
    static final int corrosive = 2;
    static final int arcane = 3;

    static Entity enemy = new Entity(
            "Bog Jones", 26, 2);
    static PlayerCharacter p1 = new PlayerCharacter("Lakshmi",3,0,2,0,1,-1);
    static PlayerCharacter p2 = new PlayerCharacter("Fen", 1, 2, 3, -1, 0, 0);

    public static void main(String[] args) {
        initiativeRunner();
    }

    public static void initiativeRunner() {

        //Initiative is an array that keeps all creatures engaged in combat in a list. The list is ordered by initiative order.
        ArrayList<PlayerCharacter> initiative = new ArrayList<>();

        initiative.add(p1);
        initiative.add(p2);

        int prevInit = 0;

        ArrayList<Double> initRolls = new ArrayList<>();
        for (PlayerCharacter c : initiative) {
            initRolls.add(c.initiativeRoll());
        }



        while ((p1.getIsAlive() || p2.getIsAlive()) && enemy.getIsAlive()) {
            enemy.runTurn();
            System.out.println();
            p1.runTurn();
            System.out.println();
            p2.runTurn();
            System.out.println();
        }

    }

    public static int arrayListMax(ArrayList<Integer> arl) {
        int max = arl.get(0);
        for (int x : arl) {
            if (x > max) {
                max = x;
            }
        }
        return max;
    }

    public static int arrayListMax(ArrayList<Integer> arl, double below) {
        int max = arl.get(0);
        for (int x : arl) {
            if (x > max && x < below) {
                max = x;
            }
        }
        return max;
    }

}