/* TODO:
    - Create Enemy class
    - Create Condition class
    - Implement Conditions into Creature class
    - Create feature class
    - Create leveling system
    - Clean up formatting on "help" action in player.chooseWeapon and player.chooseStance
    - Clean up formatting on Weapon.toString() (and also on Stance.toString())
    - Add weapon and attack properties
 */

public class Main {

    public static void main(String[] args) {

        Player me = new Player();

        for (int i = 1; i < 16; i++) {
            me.levelUp();
        }

    }

}