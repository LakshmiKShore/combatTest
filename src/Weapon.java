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
    int minHands;
    int weaponID;

    public Weapon(int ID) {
        setWeapon(ID);
    }

    public void setWeapon(int ID) {
        weaponID = ID;
        if (weaponID == 0) {
            weaponName = "Unarmed";
            parryModifier = 0;
            attacksTotal = 1;
            attackOneName = "strike";
            attackOneDice = 1;
            attackOneDamage = 1;
            attackOneAP = 3;
            attacksPerTurn = 2;
            minHands = 1;
        }
        if (weaponID == 1) {
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
            minHands = 1;
        }
        if (weaponID == 2) {
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
            minHands = 1;
        }
        if (weaponID == 3) {
            weaponName = "Spear";
            attacksTotal = 1;
            attackOneName = "stab";
            parryModifier = 0;
            attackOneDice = 1;
            attackOneDamage = 10;
            attackOneAP = 3;
            attacksPerTurn = 2;
            minHands = 2;
        }

        if (weaponID == 4) {
            weaponName = "Knife";
            attacksTotal = 2;
            attackOneName = "nick";
            attackTwoName = "stab";
            parryModifier = 0;
            attackOneDice = 1;
            attackOneDamage = 1;
            attackOneAP = 1;
            attackTwoDice = 1;
            attackTwoDamage = 8;
            attackTwoAP = 3;
            attacksPerTurn = 3;
            minHands = 1;
        }

        if (weaponID == 5) {
            weaponName = "Poleaxe";
            attacksTotal = 3;
            attackOneName = "thrust";
            attackTwoName = "beak";
            attackThreeName = "slam";
            parryModifier = 1;
            attackOneDice = 1;
            attackOneDamage = 4;
            attackOneAP = 2;
            attackTwoDice = 1;
            attackTwoDamage = 6;
            attackTwoAP = 3;
            attackThreeDice = 2;
            attackThreeDamage = 6;
            attackThreeAP = 4;
            attacksPerTurn = 2;
            minHands = 2;
        }

        if (weaponID == 6) {
            weaponName = "Small Shield";
            attacksTotal = 1;
            attackOneName = "bash";
            parryModifier = 3;
            attackOneDice = 1;
            attackOneDamage = 1;
            attackOneAP = 2;
            attacksPerTurn = 2;
            minHands = 1;
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

    public int getMinHands() {
        return minHands;
    }

    public int getID() {
        return weaponID;
    }

}
