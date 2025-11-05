import java.util.Scanner;

public class Main {
    static Scanner myScanner = new Scanner(System.in);
    static int toHit;
    static int damage;
    static Entity enemy = new Entity(
            "Bog Jones", 14, 1);
    static PlayerCharacter p1 = new PlayerCharacter("Lakshmi",3,0,2,0,1,-1);
    static PlayerCharacter p2 = new PlayerCharacter("Fen", 1, 2, 3, -1, 0, 0);

    public static void main(String[] args) {
        initiativeRunner();
    }

    public static void initiativeRunner() {

        String firstInit = "player";
        p1.increaseLevel();
        p1.increaseLevel();
        p1.increaseLevel();
        p2.increaseLevel();
        p2.increaseLevel();
        p2.increaseLevel();

        while ((p1.getIsAlive() && p2.getIsAlive()) && enemy.getIsAlive()) {
            p1.runTurn();
            p2.runTurn();
            enemy.runTurn();
        }

    }
}