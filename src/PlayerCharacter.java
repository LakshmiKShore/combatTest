import java.util.Scanner;

public class PlayerCharacter {

    String name;
    int maxHealth;
    int health;
    int healthMod;
    int maxActionPoints;
    int actionPoints;
    int proficiency;
    int maxEnergyPoints;
    int energyPoints;
    int level;
    boolean isAlive;

    int str;
    int dex;
    int con;
    int wit;
    int will;
    int know;

    int mainAttacks = 0;
    int offAttacks = 0;

    Weapon mainWeapon = new Weapon(0);
    Weapon offWeapon = new Weapon(0);

    public PlayerCharacter(String tempName, int tempHealth, int tempProficiency) {
        name = tempName;
        health = tempHealth;
        proficiency = tempProficiency;
        maxActionPoints = 7;
        isAlive = true;
    }

    public PlayerCharacter() {
        System.out.println("Null PlayerCharacter Created");
        name = "Null";
        health = 1;
        proficiency = 1;
        maxActionPoints = 7;
        isAlive = true;
    }

    public PlayerCharacter(String tempName, int tempStr, int tempDex, int tempCon, int tempWit, int tempWill, int tempKnow) {
        name = tempName;
        str = tempStr;
        dex = tempDex;
        con = tempCon;
        wit = tempWit;
        will = tempWill;
        know = tempKnow;
        level = 1;
        healthMod = 2;
        proficiency = 1;
        isAlive = true;
        maxActionPoints = 7;
        maxEnergyPoints = level + (con * 2) + (will * 2);
        maxHealth = 4 + (level * healthMod) + (con * proficiency * 2);
        energyPoints = maxEnergyPoints;
        health = maxHealth;

        Scanner creationScanner = new Scanner(System.in);
        creationScanner.useDelimiter("\n");

        System.out.println("What weapon should " + name + " use in their main hand?");
        System.out.println("Shortsword, Mace, Spear, Knife, Poleaxe, Shield");
        String cased = creationScanner.next();
        String input = cased.toLowerCase();

        if (input.equals("shortsword"))
            mainWeapon.setWeapon(1);
        if (input.equals("mace"))
            mainWeapon.setWeapon(2);
        if (input.equals("spear"))
            mainWeapon.setWeapon(3);
        if (input.equals("knife"))
            mainWeapon.setWeapon(4);
        if (input.equals("poleaxe"))
            mainWeapon.setWeapon(5);
        if (input.equals("shield"))
            mainWeapon.setWeapon(6);

        System.out.println(name + " chose the: " + mainWeapon.getWeaponName());

        if (mainWeapon.getMinHands() < 2) {
            System.out.println("What weapon should " + name + " use in their off hand?");
            System.out.println("Shortsword, Mace, Knife, Shield");
            cased = creationScanner.next();
            input = cased.toLowerCase();

            if (input.equals("shortsword"))
                offWeapon.setWeapon(1);
            if (input.equals("mace"))
                offWeapon.setWeapon(2);
            if (input.equals("knife"))
                offWeapon.setWeapon(4);
            if (input.equals("shield"))
                offWeapon.setWeapon(6);

            System.out.println(name + " chose the: " + offWeapon.getWeaponName());
        }
    }

    //Rolls initiative. Rolls a d20 and adds dex. A random number between 0 and 0.9999 is then added to avoid ties.
    //Cast to an int in order to remove the tiebreaker.
    public double initiativeRoll() {
        double result = ((int) (Math.random()*20 + 1) + dex) + Math.random();
        System.out.println(name + " rolled " + ((int) result) + " on initiative.");
        return result;
    }

    //ran once when combat starts. Triggers various features that trigger on combat start. Prompts player to switch
    //their stances.
    public void startCombat() {

    }

    public void runTurn() {
        Scanner playerScanner = new Scanner(System.in);
        playerScanner.useDelimiter("\n");

        //resets attacks made this turn
        mainAttacks = 0;
        offAttacks = 0;

        System.out.println();

        while (actionPoints > 0) { //runs until you die, have no action points, or manually end turn
            if (!isAlive)
                break;

            //returns the actions that the player can take
            String actions = getActions();

            //get player input (stored in String action)
            System.out.println("It is " + name + "'s turn. They have " + actionPoints +
                    " action points remaining. What will they do? \n"
                    + actions);
            String cased = playerScanner.next();
            String action = cased.toLowerCase();

            //mainhand attacks
            if (action.equals(mainWeapon.getAttackOneName()) && mainAttacks < mainWeapon.getAttacksPerTurn()
                    && !mainWeapon.equals(offWeapon)) {   //Runs attack if: you type the name of the attack,
                //you haven't made too many attacks this turn, and you have enough action points
                attack(mainWeapon, Main.enemy, 1, mainAttacks);
                mainAttacks++;
            } else if (action.equals(mainWeapon.getAttackTwoName()) && mainAttacks < mainWeapon.getAttacksPerTurn()
                    && !mainWeapon.equals(offWeapon)) {
                attack(mainWeapon, Main.enemy, 2, mainAttacks);
                mainAttacks++;
            } else if (action.equals(mainWeapon.getAttackThreeName()) && mainAttacks < mainWeapon.getAttacksPerTurn()
                    && !mainWeapon.equals(offWeapon)) {
                attack(mainWeapon, Main.enemy, 3, mainAttacks);
                mainAttacks++;
            } else

                //offhand attacks
                if (action.equals(offWeapon.getAttackOneName())) {
                    attack(offWeapon, Main.enemy, 1, mainAttacks);
                    offAttacks++;
                } else if (action.equals(offWeapon.getAttackTwoName())) {
                    attack(offWeapon, Main.enemy, 2, mainAttacks);
                    offAttacks++;
                } else if (action.equals(offWeapon.getAttackThreeName())) {
                    attack(offWeapon, Main.enemy, 3, mainAttacks);
                    offAttacks++;
                } else

                    //changing weapon stances
                    if (action.equals("stance")) {
                        //checks if the player has an offhand weapon
                        if (offWeapon.getID() != 0 && (offWeapon.getTotalStances() > 1)) {
                            System.out.println("Which weapon's stance should " + name + " switch? (main, off)");
                            cased = playerScanner.next(); //gets input
                            action = cased.toLowerCase();

                            if (action.equals("main") && (mainWeapon.getTotalStances() > 1)) { //switching mainhand weapon if it has more than one stance
                                System.out.println("Which stance should " + name + " switch? " + mainWeapon.getStanceNames(true)); //gets a list of the stances you can switch to
                                cased = playerScanner.next(); //gets input
                                action = cased.toLowerCase();
                                if (action.equals(mainWeapon.getStanceOneName())) { //setting the stance
                                    mainWeapon.setStance(0);
                                } else if (action.equals(mainWeapon.getStanceTwoName())) {
                                    mainWeapon.setStance(1);
                                } else if (action.equals(mainWeapon.getStanceThreeName())) {
                                    mainWeapon.setStance(2);
                                } else {
                                    System.out.println("Cannot switch to that stance.");
                                }
                            } else if (action.equals("off")) { //switching offhand weapon if it has more than one stance
                                System.out.println("Which stance should " + " switch? " + offWeapon.getStanceNames(true)); //gets a list of the stances you can switch to
                                cased = playerScanner.next(); //gets input
                                action = cased.toLowerCase();
                                if (action.equals(offWeapon.getStanceOneName())) { //setting the stance
                                    offWeapon.setStance(0);
                                } else if (action.equals(offWeapon.getStanceTwoName())) {
                                    offWeapon.setStance(1);
                                } else if (action.equals(offWeapon.getStanceThreeName())) {
                                    offWeapon.setStance(2);
                                } else {
                                    System.out.println("Cannot switch to that stance.");
                                }
                            } else {
                                System.out.println("Invalid Weapon."); //error handling
                            }
                        } else if ((mainWeapon.getTotalStances() > 1)) { //switching mainhand weapon if it has more than one stance
                            System.out.println("Which stance should " + name + " switch to? " + mainWeapon.getStanceNames(true)); //gets a list of the stances you can switch to
                            cased = playerScanner.next(); //gets input
                            action = cased.toLowerCase();
                            if (action.equals(mainWeapon.getStanceOneName())) { //setting the stance
                                mainWeapon.setStance(0);
                            } else if (action.equals(mainWeapon.getStanceTwoName())) {
                                mainWeapon.setStance(1);
                            } else if (action.equals(mainWeapon.getStanceThreeName())) {
                                mainWeapon.setStance(2);
                            } else {
                                System.out.println("Cannot switch to that stance.");
                            }
                        } else { //error handling
                            System.out.println("No weapons can switch stances.");
                        }
                    } else

                        //lists current status
                        if (action.equals("status")) {
                            status();
                        } else

                            //end turn script
                            if (action.equals("end turn") || action.equals("quit")) {
                                break;
                            } else {
                                System.out.println("Invalid Command.");
                            }
        }
        endTurn();
    }

        //returns the actions that the player can take
    private String getActions() {
        String actions = mainWeapon.getAttackOneName();
        if (mainWeapon.getAttacksTotal() > 1)
            actions += (", " + mainWeapon.getAttackTwoName());
        if (mainWeapon.getAttacksTotal() > 2)
            actions += (", " + mainWeapon.getAttackThreeName());

        if (!offWeapon.getWeaponName().equals("Unarmed")) {
            actions += (", " + offWeapon.getAttackOneName());
            if (offWeapon.getAttacksTotal() > 1)
                actions += (", " + offWeapon.getAttackTwoName());
            if (offWeapon.getAttacksTotal() > 2)
                actions += (", " + offWeapon.getAttackThreeName());
        }

        actions += ", stance, status, end turn.";
        return actions;
    }

    public void endTurn() {
        actionPoints = maxActionPoints;
    }

    //runs an attack. @params object of class Weapon, object of class Entity
    //int attack number (from 1 to 3), int attacks made (passed from mainAttacks or offAttacks)
    public void attack(Weapon weapon, Entity target, int attackNumb, int attacksMade) {
        if (attacksMade < weapon.getAttacksPerTurn()) {
            if (attackNumb == 1) {
                if (actionPoints >= weapon.getAttackOneAP()) {
                    actionPoints -= weapon.getAttackOneAP();
                    System.out.println(name + " attacked " + target.getName() + " with their " + weapon.getWeaponName() + "'s " + weapon.getAttackOneName() + ".");
                    attackingParry(weapon.getAttackOneDice(), weapon.getAttackOneDamage(), target);
                } else {
                    System.out.println("Not enough Action Points.");
                }
            } else if (attackNumb == 2) {
                if (actionPoints >= weapon.getAttackTwoAP()) {
                    actionPoints -= weapon.getAttackTwoAP();
                    System.out.println(name + " attacked " + target.getName() + " with their " + weapon.getWeaponName() + "'s " + weapon.getAttackTwoName() + ".");
                    attackingParry(weapon.getAttackTwoDice(), weapon.getAttackTwoDamage(), target);
                } else {
                    System.out.println("Not enough Action Points.");
                }
            } else if (attackNumb == 3) {
                if (actionPoints >= weapon.getAttackThreeAP()) {
                    actionPoints -= weapon.getAttackThreeAP();
                    System.out.println(name + " attacked " + target.getName() + " with their " + weapon.getWeaponName() + "'s " + weapon.getAttackThreeName() + ".");
                    attackingParry(weapon.getAttackThreeDice(), weapon.getAttackThreeDamage(), target);
                } else {
                    System.out.println("Not enough Action Points.");
                }
            } else {
                System.out.println("Error: PlayerCharacter.attack received an invalid attack number.");
            }
        } else {
            System.out.println(name + " has made too many attacks with this weapon this turn.");
        }
    }


    public int parry(int diceNumber, int diceType, int damageBonus) {
        Scanner parryScanner = new Scanner(System.in);
        parryScanner.useDelimiter("\n");

        int parryModifier = Math.max(mainWeapon.getParryModifier(), offWeapon.getParryModifier()) + proficiency;

        if (actionPoints >= 1) {
            System.out.println(name + " is being attacked for " + diceNumber + "d" + diceType + " + " + damageBonus +
                    " Damage. " + name + " has " + actionPoints + " action points remaining. " + "Should " + name + " parry?");
            String action = "";

            while (true) {
                String cased = parryScanner.next();
                action = cased.toLowerCase();
                if (action.equals("yes") || action.equals("parry") || action.equals("y")) {
                    actionPoints -= 1;
                    int parryRoll = (int) (Math.random() * 20) + 1;
                    System.out.println(name + " got a " + parryRoll + " + " + parryModifier + ".");
                    return parryRoll + parryModifier;
                } else if (action.equals("no") || action.equals("n")) {
                    return -20;
                } else if (action.equals("status")) {
                    status();
                }
            }
        } else {
            return -20;
        }
    }


    public void attackingParry(int diceNumber, int diceType, Entity target) {
        int atkParryRoll = attackRoll();
        int defParryRoll = target.parry(diceType, diceNumber, proficiency);
        if (defParryRoll != -20) {
            System.out.println(name + " got a " + atkParryRoll + ".");
        }
        if (atkParryRoll > defParryRoll) {
            int damage = damageRoll(diceType, diceNumber);
            target.reduceHealth(damage);
        } else {
            System.out.println(target.getName() + " parried " + name + "'s attack.");
        }
    }

    public int attackRoll() {
        return 1 + proficiency + (int) (Math.random() * 20) + 1;
    }

    public int damageRoll(int diceType, int diceNumber) {
        int result = 0;
        for (int i = 0; i < diceNumber; i++) {
            result += (int) (Math.random() * diceType) + 1;
        }
        result += proficiency;
        return result;
    }

    public void increaseLevel() {
        level++;
        maxEnergyPoints = level + (con * 2) + (will * 2);
        maxHealth = 4 + (level / 2) + (healthMod * proficiency * 2) + con;
        energyPoints = maxEnergyPoints;
        health = maxHealth;
        System.out.println(name + " is now level " + level);
    }

    public void reduceHealth(int damage) {
        health -= damage;
        if (health <= 0) {
            isAlive = false;
            System.out.println(name + " took " + damage + " damage and died.");
        } else {
            System.out.println(name + " took " + damage + " damage and has " + health + " health remaining.");
        }
    }

    public void status() {
        System.out.println(name + " has " + health + "/" + maxHealth + " health remaining.");
        System.out.println(name + " is wielding a " + mainWeapon.getWeaponName() + " in " + mainWeapon.getStanceName() + " and has made " + mainAttacks + "/" + mainWeapon.getAttacksPerTurn() + " attacks with it this turn.");
        if (offWeapon.weaponID != 0) {
            System.out.println(name + " is wielding a " + offWeapon.getWeaponName() + " in " + offWeapon.getStanceName() + " and has made " + offAttacks + "/" + mainWeapon.getAttacksPerTurn() + " attacks with it this turn.");
        }
        System.out.println(name + "'s defending parry modifier is +" + (Math.max(mainWeapon.getParryModifier(), offWeapon.getParryModifier()) + proficiency));
    }

    public void killEntity() {
        health = 0;
        isAlive = false;
    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getHealthMod() {
        return healthMod;
    }

    public int getProficiency() {
        return proficiency;
    }

    public int getMaxEnergyPoints() {
        return maxEnergyPoints;
    }

    public int getEnergyPoints() {
        return energyPoints;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    public int getLevel() {
        return level;
    }

    public int getStr() {
        return str;
    }

    public int getDex() {
        return dex;
    }

    public int getCon() {
        return con;
    }

    public int getWit() {
        return wit;
    }

    public int getWill() {
        return will;
    }

    public int getKnow() {
        return know;
    }

}
