/* TODO:
    - Rewrite the Condition class
    - Add calls to Condition before and after each creature's turns.
    - Implement Conditions into Creature class
    - Implement Creature.inflictCondition() into both creature and condition classes

    - Figure out a way for creatures to be immediately removed from the combat upon death.
    - Create Enemy class
    - Create feature class
    - Create leveling system
    - Clean up formatting on "help" action in player.chooseWeapon and player.chooseStance
    - Clean up formatting on Weapon.toString() (and also on Stance.toString())
    - Add weapon and attack properties
 */

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Combat combat = new Combat(new Creature[] {new Creature(Adventure.tempSkeletonChamp), new Creature(Adventure.tempSkeletonChamp)}, new Creature[] {new Creature(Adventure.tempSkeleton), new Creature(Adventure.tempSkeleton), new Creature(Adventure.tempSkeleton)});
        combat.runCombat();

    }

}