/* TODO:
    - Create Enemy class
    - Create feature class
    - Create leveling system
    - Clean up formatting on "help" action in player.chooseWeapon and player.chooseStance
    - Add weapon and attack properties
 */

public class Main {

    public static void main(String[] args) {

        Player me = new Player();
        Player you = new Player();

        Combat combat = new Combat(new Creature[] {me}, new Creature[] {you});
        combat.runCombat();

    }

}