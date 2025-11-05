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

            System.out.println("What weapon would you like to use in your main hand?");
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

            System.out.println("You chose the: " + mainWeapon.getWeaponName());

            if (mainWeapon.getMinHands() < 2) {
                System.out.println("What weapon would you like to use in your off hand?");
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

                System.out.println("You chose the: " + offWeapon.getWeaponName());
            }

        }

        public void runTurn() {
            Scanner playerScanner = new Scanner(System.in);
            playerScanner.useDelimiter("\n");

                //resets attacks made this turn
            int mainAttacks = 0;
            int offAttacks = 0;

            while (actionPoints > 0) { //runs until you die, have no action points, or manually end turn
                if (!isAlive)
                    break;

                    //returns the actions that the player can take
                String actions = getActions();

                    //get player input (stored in String action)
                System.out.println("It is your turn. You have " + actionPoints +
                        " action points remaining. What would you like to do? \n"
                        + actions);
                String cased = playerScanner.next();
                String action = cased.toLowerCase();

                //mainhand attacks
                if (action.equals(mainWeapon.getAttackOneName()) && mainAttacks < mainWeapon.getAttacksPerTurn()) {   //Attack One
                    if (actionPoints >= mainWeapon.getAttackOneAP()) {  //If you have enough AP and aren't breaking the 2 attacks/weapon rule

                            //Subtract AP, call the attack function, increase attacks made this turn
                        actionPoints -= mainWeapon.getAttackOneAP();
                        System.out.println("You attacked " + Main.enemy.getName() + " with your " + mainWeapon.getWeaponName()
                                            + "'s " + mainWeapon.getAttackOneName() + ".");
                        attack(mainWeapon.getAttackOneDice(), mainWeapon.getAttackOneDamage(), Main.enemy);
                        mainAttacks++;

                            //print error messages
                    } else if (actionPoints < mainWeapon.getAttackOneAP()) {
                        System.out.println("Not enough Action Points.");
                    }
                } else if (action.equals(mainWeapon.getAttackOneName()) && mainAttacks >= mainWeapon.getAttacksPerTurn() && !(mainWeapon.getID() == offWeapon.getID())) {
                    System.out.println("You have made too many attacks with this weapon.");
                } else

                if (action.equals(mainWeapon.getAttackTwoName()) && mainAttacks < mainWeapon.getAttacksPerTurn()) { //Attack Two
                    if (actionPoints >= mainWeapon.getAttackTwoAP()) {  //If you have enough AP and aren't breaking the 2 attacks/weapon rule

                            //Subtract AP, call the attack function, increase attacks made this turn
                        actionPoints -= mainWeapon.getAttackTwoAP();
                        System.out.println("You attacked " + Main.enemy.getName() + " with your " + mainWeapon.getWeaponName()
                                + "'s " + mainWeapon.getAttackTwoName() + ".");
                        attack(mainWeapon.getAttackTwoDice(), mainWeapon.getAttackTwoDamage(), Main.enemy);
                        mainAttacks++;

                            //print error messages
                    } else if (actionPoints < mainWeapon.getAttackTwoAP()) {
                        System.out.println("Not enough Action Points.");
                    }
                } else if (action.equals(mainWeapon.getAttackTwoName()) && mainAttacks >= mainWeapon.getAttacksPerTurn() && !(mainWeapon.getID() == offWeapon.getID())) {
                    System.out.println("You have made too many attacks with this weapon.");
                } else

                if (action.equals(mainWeapon.getAttackThreeName()) && mainAttacks < mainWeapon.getAttacksPerTurn()) { //Attack Two
                    if (actionPoints >= mainWeapon.getAttackThreeAP()) {  //If you have enough AP and aren't breaking the 2 attacks/weapon rule

                        //Subtract AP, call the attack function, increase attacks made this turn
                        actionPoints -= mainWeapon.getAttackThreeAP();
                        System.out.println("You attacked " + Main.enemy.getName() + " with your " + mainWeapon.getWeaponName()
                                + "'s " + mainWeapon.getAttackThreeName() + ".");
                        attack(mainWeapon.getAttackThreeDice(), mainWeapon.getAttackThreeDamage(), Main.enemy);
                        mainAttacks++;

                        //print error messages
                    } else if (actionPoints < mainWeapon.getAttackThreeAP()) {
                        System.out.println("Not enough Action Points.");
                    }
                } else if (action.equals(mainWeapon.getAttackThreeName()) && mainAttacks >= mainWeapon.getAttacksPerTurn() && !(mainWeapon.getID() == offWeapon.getID())) {
                    System.out.println("You have made too many attacks with this weapon.");
                } else

                //offhand attacks
                if (action.equals(offWeapon.getAttackOneName())) {   //Attack One
                    if (actionPoints >= offWeapon.getAttackOneAP() && offAttacks < offWeapon.getAttacksPerTurn()) {  //If you have enough AP and aren't breaking the 2 attacks/weapon rule

                        //Subtract AP, call the attack function, increase attacks made this turn
                        actionPoints -= offWeapon.getAttackOneAP();
                        System.out.println("You attacked " + Main.enemy.getName() + " with your " + offWeapon.getWeaponName()
                                + "'s " + offWeapon.getAttackOneName() + ".");
                        attack(offWeapon.getAttackOneDice(), offWeapon.getAttackOneDamage(), Main.enemy);
                        offAttacks++;

                        //print error messages
                    } else if (actionPoints < offWeapon.getAttackOneAP()) {
                        System.out.println("Not enough Action Points.");
                    } else if (offAttacks >= offWeapon.getAttacksPerTurn()) {
                        System.out.println("You have made too many attacks with this weapon.");
                        System.out.println(offAttacks);
                    }
                } else

                if (action.equals(offWeapon.getAttackTwoName())) { //Attack Two
                    if (actionPoints >= offWeapon.getAttackTwoAP() && offAttacks < offWeapon.getAttacksPerTurn()) {  //If you have enough AP and aren't breaking the 2 attacks/weapon rule

                        //Subtract AP, call the attack function, increase attacks made this turn
                        actionPoints -= offWeapon.getAttackTwoAP();
                        System.out.println("You attacked " + Main.enemy.getName() + " with your " + offWeapon.getWeaponName()
                                + "'s " + offWeapon.getAttackTwoName() + ".");
                        attack(offWeapon.getAttackTwoDice(), offWeapon.getAttackTwoDamage(), Main.enemy);
                        offAttacks++;

                        //print error messages
                    } else if (actionPoints < offWeapon.getAttackTwoAP()) {
                        System.out.println("Not enough Action Points.");
                    } else if (offAttacks >= offWeapon.getAttacksPerTurn()) {
                        System.out.println("You have made too many attacks with this weapon.");
                    }
                } else

                if (action.equals(offWeapon.getAttackThreeName())) { //Attack Two
                    if (actionPoints >= offWeapon.getAttackThreeAP() && offAttacks < offWeapon.getAttacksPerTurn()) {  //If you have enough AP and aren't breaking the 2 attacks/weapon rule

                        //Subtract AP, call the attack function, increase attacks made this turn
                        actionPoints -= offWeapon.getAttackThreeAP();
                        System.out.println("You attacked " + Main.enemy.getName() + " with your " + offWeapon.getWeaponName()
                                + "'s " + offWeapon.getAttackThreeName() + ".");
                        attack(offWeapon.getAttackThreeDice(), offWeapon.getAttackThreeDamage(), Main.enemy);
                        offAttacks++;

                        //print error messages
                    } else if (actionPoints < offWeapon.getAttackTwoAP()) {
                        System.out.println("Not enough Action Points.");
                    } else if (offAttacks >= offWeapon.getAttacksPerTurn()) {
                        System.out.println("You have made too many attacks with this weapon.");
                    }
                } //no else, end of attacks

                    //changing weapon stances
                if (action.equals("stance")) {
                        //checks if the player has an offhand weapon
                    if (offWeapon.getID() != 0 ){
                        System.out.println("Which weapon's stance would you like to switch? (main, off)");
                        cased = playerScanner.next(); //gets input
                        action = cased.toLowerCase();

                        if (action.equals("main")) { //switching mainhand weapon
                            System.out.println("Which stance would you like to switch to? " + mainWeapon.getStanceNames(true)); //gets a list of the stances you can switch to
                            cased = playerScanner.next(); //gets input
                            action = cased.toLowerCase();
                            if (action.equals(mainWeapon.getStanceOneName())) { //setting the stance
                                mainWeapon.setStance(0);
                            }
                            else if (action.equals(mainWeapon.getStanceTwoName())) {
                                mainWeapon.setStance(1);
                            }
                            else if (action.equals(mainWeapon.getStanceThreeName())) {
                                mainWeapon.setStance(2);
                            } else {
                                System.out.println("Cannot switch to that stance.");
                            }
                        } else if (action.equals("off")) { //switching offhand weapon
                            System.out.println("Which stance would you like to switch to? " + offWeapon.getStanceNames(true)); //gets a list of the stances you can switch to
                            cased = playerScanner.next(); //gets input
                            action = cased.toLowerCase();
                            if (action.equals(offWeapon.getStanceOneName())) { //setting the stance
                                offWeapon.setStance(0);
                            }
                            else if (action.equals(offWeapon.getStanceTwoName())) {
                                offWeapon.setStance(1);
                            }
                            else if (action.equals(offWeapon.getStanceThreeName())) {
                                offWeapon.setStance(2);
                            } else {
                                System.out.println("Cannot switch to that stance.");
                            }
                        } else {
                            System.out.println("Invalid Weapon."); //error handling
                        }
                    } else { //switching mainhand weapon
                            System.out.println("Which stance would you like to switch to? " + mainWeapon.getStanceNames(true)); //gets a list of the stances you can switch to
                            cased = playerScanner.next(); //gets input
                            action = cased.toLowerCase();
                            if (action.equals(mainWeapon.getStanceOneName())) { //setting the stance
                                mainWeapon.setStance(0);
                            }
                            else if (action.equals(mainWeapon.getStanceTwoName())) {
                                mainWeapon.setStance(1);
                            }
                            else if (action.equals(mainWeapon.getStanceThreeName())) {
                                mainWeapon.setStance(2);
                            } else {
                                System.out.println("Cannot switch to that stance.");
                            }
                    }
                }

                    //lists current status
                if (action.equals("status")) {
                    System.out.println(name + " has " + health + "/" + maxHealth + " health remaining.");
                    System.out.println(name + " is wielding a " + mainWeapon.getWeaponName() + " in " + mainWeapon.getStanceName() + " and has made " + mainAttacks + "/" + mainWeapon.getAttacksPerTurn() + " attacks with it this turn.");
                    if (offWeapon.weaponID != 0) {
                        System.out.println(name + " is wielding a " + offWeapon.getWeaponName() + " in " + offWeapon.getStanceName() + " and has made " + offAttacks + "/" + mainWeapon.getAttacksPerTurn() + " attacks with it this turn.");
                    }
                    System.out.println(name + "'s defending parry modifier is +" + (Math.max(mainWeapon.getParryModifier(), offWeapon.getParryModifier()) + proficiency));
                }

                    //end turn script
                if (action.equals("end turn") || action.equals("quit")) {
                    break;
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

        actions += ", stance, status.";
        return actions;
    }

    public void endTurn() {
            actionPoints = maxActionPoints;
        }

        public int parry(int diceNumber, int diceType, int damageBonus) {
            Scanner parryScanner = new Scanner(System.in);
            parryScanner.useDelimiter("\n");

            int parryModifier = Math.max(mainWeapon.getParryModifier(), offWeapon.getParryModifier()) + proficiency;

            if (actionPoints >= 1) {
                System.out.println(name + " is being attacked for " + diceNumber + "d" + diceType + " + " + damageBonus +
                        " Damage. You have " + actionPoints + " action points remaining. " + "Would you like to parry?");
                String cased = parryScanner.next();
                String action = cased.toLowerCase();

                if (action.equals("yes") || action.equals("parry") || action.equals("y")) {
                    actionPoints -= 1;
                    int parryRoll = (int) (Math.random() * 20) + 1;
                    System.out.println(name + " got a " + parryRoll + " + " + parryModifier + ".");
                    return parryRoll + parryModifier;
                } else {
                    return -20;
                }
            } else {
                return -20;
            }
        }


        public void attack(int diceNumber, int diceType, Entity target) {
            int atkParryRoll = attackRoll();
            int defParryRoll = target.parry(diceType, diceNumber, proficiency);
            if (defParryRoll != -20) {
                System.out.println(name + " got a " + atkParryRoll + ".");
            }
            if (atkParryRoll > defParryRoll) {
                int damage = damageRoll(diceType, diceNumber);
                target.reduceHealth(damage);
                System.out.println(name + " dealt " + damage + " damage.");
                System.out.println(target.getName() + " has " + target.getHeath() +
                    " health remaining.");
            } else {
                System.out.println(target.getName() + " parried your attack.");
            }
        }

        public int attackRoll() {
            return 1 + proficiency + (int) (Math.random()*20) + 1;
        }

        public int damageRoll(int diceType, int diceNumber) {
            int result = 0;
            for (int i = 0; i < diceNumber; i++) {
                result += (int) (Math.random()*diceType) + 1;
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
            }
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
