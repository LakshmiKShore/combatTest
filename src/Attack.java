public class Attack extends Action {

    private final int diceType;
    private final int diceNumber;
    private final int modifier;
    private final int damageType;

    //constructor without modifier (defaults to zero)
    public Attack(String name, String description, int cost, int diceType, int diceNumber, int damageType) {
        super(name, description, cost);
        this.diceType = diceType;
        this.diceNumber = diceNumber;
        this.damageType = damageType;
        modifier = 0;
    }

    //constructor with modifier
    public Attack(String name, String description, int cost, int diceType, int diceNumber, int modifier, int damageType) {
        super(name, description, cost);
        this.diceType = diceType;
        this.diceNumber = diceNumber;
        this.damageType = damageType;
        this.modifier = modifier;
    }


    //uses the attack. requires a target creature and an attacker creature
    //prints out if the attack hit or missed.
    //returns TRUE if the attack hits, FALSE if the attack misses.
    public boolean use(Creature target, Creature attacker, int damageBonus) {

        int damage = damage(damageBonus, false, false, false);

        if (!target.parryPrompt(diceType, diceNumber)) { //if the target DOESN'T parry
            System.out.print("hit, dealing ");
            target.damage(damage, damageType);
            return true;
        }

        //if the target DOES parry

        int defenderParry = target.defendingParry(target.defendingParryAdvantages(),target.defendingParryDisadvantages());
        int attackerParry = attacker.attackingParry(attacker.attackingParryAdvantages(), attacker.attackingParryDisadvantages());

        if (attackerParry > defenderParry) {
            System.out.print("hit, dealing ");
            target.damage(damage, damageType);
            return true;
        } else {
            System.out.println("missed.");
            return false;
        }

    }


    //Calculates damage for a hit.
    //DoubleModifiers doubles modifiers, DoubleDice doubles the number of dice rolled, isCritical takes the max of all dice
    public int damage(int damageBonus, boolean doubleModifiers, boolean doubleDice, boolean isCritical) {

        int damageFromDice = 0;
        int diceNumber = this.diceNumber;
        if (doubleDice) {
            diceNumber *= 2;
        }

        for (int i = 0; i < diceNumber; i++) {
            if (isCritical) {
                damageFromDice += diceType;
            } else {
                damageFromDice += (int) (Math.random() * diceType) + 1;
            }
        }

        if (doubleModifiers) {
            damageBonus *= 2;
        }

        return damageFromDice + damageBonus;
    }


    public String toString() {
        return (name + ": " + description + ". Deals " + diceNumber + "d" + diceType);
    }


    //Getters

    public int getDiceType() {
        return diceType;
    }

    public int getDiceNumber() {
        return diceNumber;
    }

    public int getModifier() {
        return modifier;
    }

}
