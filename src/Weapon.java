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
    int attacksPerTurn;

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
            attacksPerTurn = 2;
        }
        if (weaponID == 1) {
            weaponName = "Mace";
            attacksTotal = 2;
            attackOneName = "slam";
            attackTwoName = "heavy slam";
            parryModifier = 0;
            attackOneDice = 1;
            attackOneDamage = 6;
            attackOneAP = 3;
            attackTwoDice = 2;
            attackTwoDamage = 6;
            attackTwoAP = 4;
            attacksPerTurn = 2;
        }
        if (weaponID == 2) {
            weaponName = "Spear";
            attacksTotal = 1;
            attackOneName = "stab";
            parryModifier = 0;
            attackOneDice = 1;
            attackOneDamage = 10;
            attackOneAP = 3;
            attacksPerTurn = 2;
        }

        if (weaponID == 3) {
            weaponName = "Knife";
            attacksTotal = 3;
            attackOneName = "nick";
            attackTwoName = "stab";
            parryModifier = 0;
            attackOneDice = 1;
            attackOneDamage = 1;
            attackOneAP = 1;
            attackTwoDice = 1;
            attackTwoDamage = 8;
            attackTwoAP = 3;
        }
    }

    public int getAttacksTotal() {
        return attacksTotal;
    }

    public String getAttackOneName() {
        return attackOneName; //CHANGE TO NEW VARS
    }

    public String getAttackTwoName() {
        return attackTwoName;
    }

    public String getAttackThreeName() {
        return attackThreeName;
    }

    public int getParryModifier() {
        return parryModifier;
    }

    public int getAttackOneDice() {
        return attackOneDice;
    }

    public int getAttackOneDamage() {
        return attackOneDamage;
    }

    public int getAttackOneAP() {
        return attackOneAP;
    }

    public int getAttackTwoDice() {
        return attackTwoDice;
    }

    public int getAttackTwoDamage() {
        return attackTwoDamage;
    }

    public int getAttackTwoAP() {
        return attackTwoAP;
    }

    public int getAttackThreeDice() {
        return attackThreeDice;
    }

    public int getAttackThreeDamage() {
        return attackThreeDamage;
    }

    public int getAttackThreeAP() {
        return attackThreeAP;
    }

    public int getAttacksPerTurn() {
        return attacksPerTurn;
    }

    public String getWeaponName() {
        return weaponName;
    }

}
