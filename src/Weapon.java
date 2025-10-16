public class Weapon {

    String weaponName;
    int parryModifier;
    int attacksTotal;
    String attackOneName;
    int attackOneDice;
    int attackOneDamage;
    int attackOneAP;
    String attackTwoName;
    int attackTwoDice;
    int attackTwoDamage;
    int attackTwoAP;
    String attackThreeName;
    int attackThreeDice;
    int attackThreeDamage;
    int attackThreeAP;
    String[] weaponAttacks = new String[2];
    int[] weaponValues = new int[7];
    boolean isQuick;

    public Weapon(int weaponID) {
        setWeapon(weaponID);
    }

    public void setWeapon(int weaponID) {
        if (weaponID == 0) {
            weaponName = "Shortsword";
            parryModifier = 2;
            attacksTotal = 2;
            attackOneName = "slash";
            attackTwoName = "quick cut";
            attackOneDice = 1;
            attackOneDamage = 6;
            attackOneAP = 3;
            attackTwoDice = 1;
            attackTwoDamage = 4;
            attackTwoAP = 2;
            isQuick = false;
        }
        if (weaponID == 1) {
            weaponName = "Mace";
            weaponAttacks[0] = "slam";
            weaponAttacks[1] = "heavy slam";
            weaponValues[0] = 0; //CHANGE TO NEW VARS
            weaponValues[1] = 1;
            weaponValues[2] = 6;
            weaponValues[3] = 3;
            weaponValues[4] = 2;
            weaponValues[5] = 6;
            weaponValues[6] = 4;
            isQuick = false;
        }
        if (weaponID == 2) {
            weaponName = "Spear";
            weaponAttacks[0] = "stab";
            weaponAttacks[1] = "none";
            weaponValues[0] = 0; //CHANGE TO NEW VARS
            weaponValues[1] = 1;
            weaponValues[2] = 10;
            weaponValues[3] = 3;
            weaponValues[4] = 0;
            weaponValues[5] = 0;
            weaponValues[6] = 0;
            isQuick = false;
        }

        if (weaponID == 3) {
            weaponName = "Knife";
            weaponAttacks[0] = "nick";
        }
    }

    public String getAttackOneName() {
        return weaponAttacks[0]; //CHANGE TO NEW VARS
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
