public class Weapon {
    static final int parryModifier = 0;
    static final int attackOneDice = 1;
    static final int attackOneDamage = 2;
    static final int attackTwoDice = 3;
    static final int attackTwoDamage = 4; //final ints to reduce confusion, use with weaponValues
    String weaponName;
    String[] weaponAttacks = new String[4];
    int[] weaponValues = new int[5];

    public Weapon(int weaponID) {
        if (weaponID == 0) {
            weaponName = "Shortsword";
            String[] weaponAttacks = {"Slash", "Quick Cut"};
            int[] weaponValues = {2, 1, 4, 1, 6};
        }
    }

    public String getAttackOneName() {
        return weaponAttacks[0];
    }

    public String getAttackTwoName() {
        return weaponAttacks[1];
    }

    public int getParryModifier() {
        return weaponValues[0];
    }

    public int getAttackOneDice() {
        return weaponValues[1];
    }

    public int getAttackOneDamage() {
        return weaponValues[2];
    }

    public int getAttackTwoDice() {
        return weaponValues[3];
    }

    public int getAttackTwoDamage() {
        return weaponValues[4];
    }

}
