public class Attack extends Action {

    /*
    Attack needs:
        - Instance Variables
            - Type of Dice
            - Number of Dice
            - Special Modifiers
            - Weapon carrier
     */

    private int diceType;
    private int diceNumber;
    private int modifier;
    private Weapon carrier;

    //constructor without modifier (defaults to zero)
    public Attack(String name, String description, int cost, int diceType, int diceNumber, Weapon carrier) {
        super(name, description, cost);
        this.diceType = diceType;
        this.diceNumber = diceNumber;
        this.carrier = carrier;
        modifier = 0;
    }

    //constructor with modifier
    public Attack(String name, String description, int cost, int diceType, int diceNumber, int modifier, Weapon carrier) {
        super(name, description, cost);
        this.diceType = diceType;
        this.diceNumber = diceNumber;
        this.carrier = carrier;
        this.modifier = modifier;
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


    //overrides canUse. Takes into account both AP cost and the number of attacks the carrier weapon has made this turn.
    public boolean canUse(int userAP) {
        return (userAP >= cost); //TODO: Add functionality once Weapon gets the attacks made variable
    }

}
