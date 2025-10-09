public class Weapon {
    static final int parryModifier = 0;
    static final int attackOneDice = 1;
    static final int attackOneDamage = 2;
    static final int attackOneAP = 3;
    static final int attackTwoDice = 4;
    static final int attackTwoDamage = 5;
    static final int attackTwoAP = 6; //final ints to reduce confusion, use with weaponValues

    String weaponName;
    String[] weaponAttacks = new String[2];
    int[] weaponValues = new int[5];

    public Weapon(int weaponID) {
        if (weaponID == 0) {
            weaponName = "Shortsword";
            String[] weaponAttacks = {"Slash", "Quick Cut"};
            int[] weaponValues = {2, 1, 4, 3, 1, 6, 2};
            System.out.println(getAttackOneName());
            System.out.println(getAttackOneDamage());
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

    public int getAttackOneAP() {
        return weaponValues[3];
    }

    public int getAttackTwoDice() {
        return weaponValues[4];
    }

    public int getAttackTwoDamage() {
        return weaponValues[5];
    }

    public int getAttackTwoAP() {
        return weaponValues[6];
    }

}
