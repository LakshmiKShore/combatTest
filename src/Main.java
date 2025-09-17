import java.util.Scanner;
import java.util.Random;

public class Main {
    static Scanner myScanner = new Scanner(System.in);
    static Random rand = new Random();
    static int toHit;
    static int damage;

    public static void main(String[] args) {
        myScanner.useDelimiter("\n");
        Entity player = new Entity("Lakshmi",6,12,1);
        player.getName();
        player.getHeath();
        player.getArmorClass();
        player.getProficiency();
        player.getIsAlive();

        Entity enemy = new Entity("Bog Jones", 4, 11, 1);

        toHit = player.attackRoll();
        damage = player.damageRoll(1,1);
        if toHit >= enemy.getArmorClass() {

        }

        String action = myScanner.next();


    }
}