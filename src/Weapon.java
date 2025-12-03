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
    int currentHands;
    int weaponID;
    int totalStances;
    int stanceID;
    int stanceOneCost;
    int stanceTwoCost;
    int stanceThreeCost;
    String stanceOneName;
    String stanceTwoName;
    String stanceThreeName;

    public Weapon(int ID) {
        setWeapon(ID);
    }

    public void setWeapon(int ID) {
        weaponID = ID;
        setStance(0);
    }

    public void setStance(int ID) {
        stanceID = ID;
        if (weaponID == 0) {
            weaponName = "Unarmed";
            minHands = 1;
            stanceOneCost = 0;
            stanceOneName = "standard grip";
            totalStances = 1;
            if (stanceID == 0) {
                parryModifier = 0;
                attacksTotal = 1;
                attackOneName = "strike";
                attackOneDice = 1;
                attackOneDamage = 1;
                attackOneAP = 3;
                attacksPerTurn = 2;
                currentHands = 1;
            } else {
                System.out.println("Error: Unknown Stance");
            }
        }

        if (weaponID == 1) {
            weaponName = "Shortsword";
            minHands = 1;
            stanceOneCost = 0;
            stanceOneName = "standard grip";
            totalStances = 1;
            if (stanceID == 0) {
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
                currentHands = 1;
            } else {
                System.out.println("Error: Unknown Stance");
            }
        }

        if (weaponID == 2) {
            weaponName = "Mace";
            totalStances = 1;
            stanceOneCost = 0;
            stanceOneName = "standard grip";
            minHands = 1;
            if (stanceID == 0) {
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
                currentHands = 1;
            } else {
                System.out.println("Error: Unknown Stance");
            }
        }

        if (weaponID == 3) {
            weaponName = "Spear";
            totalStances = 3;
            minHands = 1;
            stanceOneCost = 1;
            stanceTwoCost = 1;
            stanceThreeCost = 1;
            stanceOneName = "standard grip";
            stanceTwoName = "wide grip";
            stanceThreeName = "one handed";
            if (stanceID == 0) {
                attacksTotal = 1;
                attackOneName = "stab";
                parryModifier = 0;
                attackOneDice = 1;
                attackOneDamage = 10;
                attackOneAP = 3;
                attacksPerTurn = 2;
                currentHands = 2;
            } else if (stanceID == 1) {
                attacksTotal = 2;
                attackOneName = "stab";
                attackTwoName = "bash";
                parryModifier = 1;
                attackOneDice = 1;
                attackOneDamage = 6;
                attackOneAP = 3;
                attackTwoDice = 1;
                attackTwoDamage = 4;
                attackTwoAP = 2;
                attacksPerTurn = 2;
                currentHands = 2;
            } else if (stanceID == 2) {
                attacksTotal = 1;
                attackOneName = "stab";
                parryModifier = 0;
                attackOneDice = 1;
                attackOneDamage = 8;
                attackOneAP = 3;
                attacksPerTurn = 2;
                currentHands = 1;
            } else {
                System.out.println("Error: Unknown Stance");
            }
        }

        if (weaponID == 4) {
            weaponName = "Knife";
            attackOneName = "nick";
            attackTwoName = "stab";
            parryModifier = 0;
            minHands = 1;
            totalStances = 2;
            stanceOneCost = 1;
            stanceOneName = "standard grip";
            stanceTwoCost = 1;
            stanceTwoName = "reverse grip";
            if (stanceID == 0) {
                attacksTotal = 2;
                attackOneName = "nick";
                attackTwoName = "stab";
                parryModifier = 1;
                attackOneDice = 1;
                attackOneDamage = 1;
                attackOneAP = 1;
                attackTwoDice = 1;
                attackTwoDamage = 4;
                attackTwoAP = 3;
                attacksPerTurn = 3;
                currentHands = 1;
            } else if (stanceID == 1) {
                attacksTotal = 2;
                attackOneName = "nick";
                attackTwoName = "stab";
                parryModifier = 1;
                attackOneDice = 1;
                attackOneDamage = 1;
                attackOneAP = 1;
                attackTwoDice = 1;
                attackTwoDamage = 8;
                attackTwoAP = 3;
                attacksPerTurn = 3;
                currentHands = 1;
            }
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
            totalStances = 1;
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
            totalStances = 1;
        }
    }

    //update to only allow you to switch to a stance you have the hands for
    public String getStanceNames(int freeHands, boolean removeCurrent) {
        String stanceNames = "";
        String toReplace = "";
        stanceNames += stanceOneName;
        if (totalStances >= 2)
            stanceNames += ", " + stanceTwoName;
        if (totalStances >= 3)
            stanceNames += ", " + stanceThreeName;
        if (removeCurrent) {
            if (stanceID == 0) {
                toReplace = stanceOneName;
            }
            if (stanceID == 1) {
                toReplace = stanceTwoName;
            }
            if (stanceID == 2) {
                toReplace = stanceThreeName;
            }
            stanceNames = stanceNames.replace(toReplace + ", ","");
        }
        return stanceNames;
    }
    
    public String getStanceName() {
        String currentStance = "";
        if (stanceID == 0) {
            currentStance = stanceOneName;
        } else if (stanceID == 1) {
            currentStance = stanceTwoName;
        } else if (stanceID == 2) {
            currentStance = stanceThreeName;
        }
        return currentStance;
    }

    public String getStanceOneName() {
        return stanceOneName;
    }

    public String getStanceTwoName() {
        return stanceTwoName;
    }

    public String getStanceThreeName() {
        return stanceThreeName;
    }

    public int getAttacksTotal() {
        return attacksTotal;
    }

    public String getAttackOneName() {
        return attackOneName;
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

    public int getCurrentHands() {
        return currentHands;
    }

    public int getID() {
        return weaponID;
    }

    public int getTotalStances() {
        return totalStances;
    }

    public int getStanceID() {
        return stanceID;
    }

}
