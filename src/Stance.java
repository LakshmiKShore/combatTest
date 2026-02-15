public class Stance {

    //Weapons have stances, stances have attacks.

    private final Attack[] attacks;
    private final int parryBonus;
    private final int hands;

    public Stance(Attack[] attacks, int parryBonus, int hands) {
        this.attacks = attacks;
        this.parryBonus = parryBonus;
        this.hands = hands;
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < attacks.length; i++) {
            output += ("Attack " + (i + 1) + ": " + attacks[i]);
        }
        return output;
    }


    public Attack[] getAttacks() {
        return attacks;
    }

    public int getParryBonus() {
        return parryBonus;
    }

    public int getHands() {
        return hands;
    }

}
