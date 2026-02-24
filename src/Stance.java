public class Stance {

    //Weapons have stances, stances have attacks.

    private final Attack[] attacks;
    private final int parryBonus;
    private final int hands;
    private final String name;

    public Stance(Attack[] attacks, int parryBonus, int hands, String name) {
        this.attacks = attacks;
        this.parryBonus = parryBonus;
        this.hands = hands;
        this.name = name;
    }

    public String toString() {
        String output = name + " ";
        for (int i = 0; i < attacks.length; i++) {
            output += ("Attack " + (i + 1) + ": " + attacks[i]);
        }
        return output;
    }

    public String getName() {
        return name;
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
