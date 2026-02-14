public class Stance {

    //Weapons have stances, stances have attacks.

    private final Attack[] attacks;
    private int parryBonus;

    public Stance(Attack[] attacks, int parryBonus) {
        this.attacks = attacks;
        this.parryBonus = parryBonus;
    }

    public Attack[] getAttacks() {
        return attacks;
    }

    public int getParryBonus() {
        return parryBonus;
    }

}
