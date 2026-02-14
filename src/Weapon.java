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

    //constructor for a simple weapon with only one stance and no special properties
    public Weapon(Stance standardGrip) {
        maxAttacks = 2;
        attacksMade = 0;
        stances = new Stance[] {standardGrip};
        currentStance = standardGrip;
    }

    //constructor with customization for maxAttacks, but only one stance.
    public Weapon(int maxAttacks, Stance standardGrip) {
        this.maxAttacks = maxAttacks;
        attacksMade = 0;
        stances = new Stance[] {standardGrip};
        currentStance = standardGrip;
    }

    //constructor with multiple stances but no special features
    //constructor with full customization and multiple stances
    public Weapon(Stance[] stances) {
        maxAttacks = 2;
        attacksMade = 0;
        this.stances = stances;
        currentStance = stances[0];
    }

    //constructor with full customization and multiple stances
    public Weapon(int maxAttacks, Stance[] stances) {
        this.maxAttacks = maxAttacks;
        attacksMade = 0;
        this.stances = stances;
        currentStance = stances[0];
    }


    //COMBAT METHODS

    //makes an attack. Returns true if the attack hits, returns false if it misses.
    public boolean attack(Attack attack, Creature target, Creature attacker) {
        incrementAttacksMade();
        return attack.use(target, attacker);
    }

    //checks if the player has hit their attacks per turn limit.
    public boolean canAttack() {
        return (attacksMade < maxAttacks);
    }

    //checks if the player has hit their attacks/turn limit AND has enough AP for the given attack.
    public boolean canAttack(Attack attack, int ap, int discount) {
        return (canAttack() && attack.canUse(ap, discount));
    }

    //increments attacksMade. kinda obvious ngl lowkey embarrassing that you need this comment here
    public void incrementAttacksMade() {
        attacksMade++;
    }

    //used by a Creature at end of turn, resets attacksMade.
    public void resetAttacksMade() {
        attacksMade = 0;
    }


    //switches to a certain stance in Stance[] stances
    public void setCurrentStance(int newStance) {
        if (newStance >= stances.length) {
            System.out.println("Invalid Stance.");
            return;
        }
        currentStance = stances[newStance];
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
