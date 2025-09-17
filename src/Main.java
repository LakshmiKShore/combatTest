import java.util.Scanner;

public class Main {
    static Scanner myScanner = new Scanner(System.in);
    static int toHit;
    static int damage;

    public static void main(String[] args) {
        myScanner.useDelimiter("\n");
        Entity player = new Entity("Lakshmi",6,12,1);
        Entity enemy = new Entity("Bog Jones", 4, 11, 1);

        System.out.println(enemy.getName() + " stands before you. What would you like to do?");
        String action = myScanner.next();
        if (action.equals("attack")) {
            toHit = player.attackRoll();
            damage = player.damageRoll(6, 1);
            if (toHit > enemy.getArmorClass()) {
                enemy.reduceHealth(damage);
                System.out.println(enemy.getName() + " has " + enemy.getHeath() + " health remaining.");
            }
        }


    }
}