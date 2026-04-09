/* TODO:
    - Implement Conditions into Creature class

    - Figure out a way for creatures to be immediately removed from the combat upon death.
    - Create Enemy class
    - Create feature class
    - Create leveling system
    - Clean up formatting on "help" action in player.chooseWeapon and player.chooseStance
    - Clean up formatting on Weapon.toString() (and also on Stance.toString())
    - Add weapon and attack properties
 */

public class Main {

    public static void main(String[] args) {

        Creature daChamp = new Creature(Adventure.tempSkeletonChamp);
        Creature daGuy = new Creature(Adventure.tempSkeleton);

        Condition newCondition = new Condition(Condition.bleeding, 1, Condition.targetStartTurn, daChamp, daGuy);

        Combat combat = new Combat(new Creature[] {daChamp, new Creature(Adventure.tempSkeletonChamp)}, new Creature[] {daGuy, new Creature(Adventure.tempSkeleton), new Creature(Adventure.tempSkeleton)});


        combat.runCombat();

    }

}