/* TODO:
    - FINISH COMBAT.APPENDNUMBERSTONAMES
    - Create Enemy class
    - Implement Conditions into Creature class
    - Create feature class
    - Create leveling system
    - Clean up formatting on "help" action in player.chooseWeapon and player.chooseStance
    - Clean up formatting on Weapon.toString() (and also on Stance.toString())
    - Add weapon and attack properties
    - Remake initiative system to use a non-enhanced for loop, and start removing creatures as soon as they die.
    - Finish combat's automatic numbering of monsters
    - Rewrite the Condition class
 */

public class Main {

    public static void main(String[] args) {

        Combat combat = new Combat(new Creature[] {new Creature(Adventure.tempSkeleton), new Creature(Adventure.tempSkeleton)}, new Creature[] {new Creature(Adventure.tempSkeleton), new Creature(Adventure.tempSkeletonChamp), new Creature(Adventure.tempSkeletonChamp)});
        combat.runCombat();

    }

}