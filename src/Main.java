import java.util.Scanner;
import java.util.Random;

public class Main {
    static Scanner myScanner = new Scanner(System.in);
    static Random rand = new Random();
    public static void main(String[] args) {
        myScanner.useDelimiter("\n");
        int playerHealth = 10;
        int enemyHealth = 10;
        int enemyAC = 14;
        int playerAC = 14;
        int damage;
        int toHit;
        do {
            String action = myScanner.next();
            if (action.equals("attack")) {
                toHit = 1 + rand.nextInt(19);
                damage = 1 + rand.nextInt(8);
                System.out.println("You rolled a " + toHit + " to hit!");
                if (toHit > enemyAC) {
                    enemyHealth -= damage;
                    System.out.println("You hit! Dealt " + damage + " damage. Enemy has " + enemyHealth + " health remaining.");
                } else {
                    System.out.println("You missed.");
                }
                toHit = 1 + rand.nextInt(19);
                damage = 1 + rand.nextInt(8);
                if (toHit > playerAC) {
                    playerHealth -= damage;
                    System.out.println("The enemy attacked, dealing " + damage + " damage! You have " + playerHealth + " health remaining.");
                } else {
                    System.out.println("The enemy attacked and missed!");
                }
            }
        } while (enemyHealth > 0 && playerHealth > 0);
        if (enemyHealth < 0) {
            System.out.println("You killed the enemy!");
        }
        if (playerHealth < 0) {
            System.out.println("The enemy killed you!");
        }
    }
}