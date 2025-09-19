import java.util.Scanner;

public class Main {
    static Scanner myScanner = new Scanner(System.in);
    static int toHit;
    static int damage;
    static Entity enemy = new Entity(
            "Bog Jones", 4, 11, 1);
    static PlayerCharacter player = new PlayerCharacter(
            "Lakshmi",6,12,1);

    public static void main(String[] args) {
        initiativeRunner();
    }

    public static void initiativeRunner() {

        while (player.getIsAlive() && enemy.getIsAlive()) {
            player.runTurn();
            enemy.runTurn();
        }

    }
}