/* TODO:
    - Create Enemy class
    - Create Condition class
    - Implement Conditions into Creature class
    - Create feature class
    - Create leveling system
    - Clean up formatting on "help" action in player.chooseWeapon and player.chooseStance
    - Clean up formatting on Weapon.toString() (and also on Stance.toString())
    - Add weapon and attack properties
    - Remake initiative system to use a non-enhanced for loop, and start removing creatures as soon as they die.
 */

public class Main {

    public static void main(String[] args) {

        Player me = new Player();
        Player you = new Player();

        System.out.println(me);

        Combat combat = new Combat(new Creature[]{me}, new Creature[]{you});
        combat.runCombat();

    }

}