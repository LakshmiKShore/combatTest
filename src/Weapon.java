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
    int[] weaponValues = new int[7];

    public Weapon(int weaponID) {
        setWeapon(weaponID);
    }

    public void setWeapon(int weaponID) {
        if (weaponID == 0) {
            weaponName = "Shortsword";
            weaponAttacks[0] = "slash";
            weaponAttacks[1] = "quick cut";
            weaponValues[0] = 2;
            weaponValues[1] = 1;
            weaponValues[2] = 6;
            weaponValues[3] = 3;
            weaponValues[4] = 1;
            weaponValues[5] = 4;
            weaponValues[6] = 2;
        }
        if (weaponID == 1) {
            weaponName = "Mace";
            weaponAttacks[0] = "slam";
            weaponAttacks[1] = "heavy slam";
            weaponValues[0] = 0;
            weaponValues[1] = 1;
            weaponValues[2] = 6;
            weaponValues[3] = 3;
            weaponValues[4] = 2;
            weaponValues[5] = 6;
            weaponValues[6] = 4;
        }
        if (weaponID == 2) {
            weaponName = "Spear";
            weaponAttacks[0] = "stab";
            weaponAttacks[1] = "none";
            weaponValues[0] = 0;
            weaponValues[1] = 1;
            weaponValues[2] = 10;
            weaponValues[3] = 3;
            weaponValues[4] = 0;
            weaponValues[5] = 0;
            weaponValues[6] = 0;
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

    public String getWeaponName() {
        return weaponName;
    }

}
