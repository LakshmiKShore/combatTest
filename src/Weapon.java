public class Weapon {

    /*
    Updates to be made to Weapon:
        - Switch to using the new Attack class instead of hardcoding attacks
        - start storing attacks used in Weapon instead of PlayerCharacter
        - add a method to increment attacks used, and one to reset it
        - add a method checking attacks used against max attacks/turn
     */

    private int maxAttacks;
    private int attacksMade;
    private Stance[] stances;
    private Stance currentStance;

    public Weapon(int maxAttacks, Stance[] stances) {

    }


    public int getMaxAttacks() {
        return maxAttacks;
    }

    public int getAttacksMade() {
        return attacksMade;
    }

    public Stance[] getStances() {
        return stances;
    }

    public Stance getCurrentStance() {
        return currentStance;
    }

}
