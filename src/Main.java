import java.util.Scanner;

public class Main {
    static Scanner myScanner = new Scanner(System.in);
    static int toHit;
    static int damage;
    static Entity enemy = new Entity(
            "Bog Jones", 14, 1);
    static PlayerCharacter player = new PlayerCharacter("Lakshmi",3,0,2,0,1,-1);

    public static void main(String[] args) {
        initiativeRunner();
    }

    public static void initiativeRunner() {

        String firstInit = "player";
        player.increaseLevel();
        player.increaseLevel();
        player.increaseLevel();

        while (player.getIsAlive() && enemy.getIsAlive()) {
            enemy.runTurn();
            player.runTurn();
        }

    }
}