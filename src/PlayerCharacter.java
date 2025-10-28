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

        Weapon playerMainWeapon = new Weapon(0);
        Weapon playerOffWeapon = new Weapon(0);

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
            healthMod = 1;
            proficiency = 1;
            isAlive = true;
            maxActionPoints = 7;
            maxEnergyPoints = level + (con * 2) + (will * 2);
            maxHealth = 4 + (level / 2) + (healthMod * proficiency * 2) + con;
            energyPoints = maxEnergyPoints;
            health = maxHealth;

            Scanner creationScanner = new Scanner(System.in);
            creationScanner.useDelimiter("\n");

            System.out.println("What weapon would you like to use in your main hand?");
            System.out.println("Shortsword, Mace, Spear, Knife, Poleaxe");
            String cased = creationScanner.next();
            String input = cased.toLowerCase();

            if (input.equals("shortsword"))
                playerMainWeapon.setWeapon(1);
            if (input.equals("mace"))
                playerMainWeapon.setWeapon(2);
            if (input.equals("spear"))
                playerMainWeapon.setWeapon(3);
            if (input.equals("knife"))
                playerMainWeapon.setWeapon(4);
            if (input.equals("poleaxe"))
                playerMainWeapon.setWeapon(5);

            System.out.println("You chose the: " + playerMainWeapon.getWeaponName());

            if (playerMainWeapon.getMinHands() < 2) {
                System.out.println("What weapon would you like to use in your off hand?");
                System.out.println("Shortsword, Mace, Knife");
                cased = creationScanner.next();
                input = cased.toLowerCase();

                if (input.equals("shortsword"))
                    playerOffWeapon.setWeapon(1);
                if (input.equals("mace"))
                    playerOffWeapon.setWeapon(2);
                if (input.equals("knife"))
                    playerOffWeapon.setWeapon(4);

                System.out.println("You chose the: " + playerOffWeapon.getWeaponName());
            }

        }

        public void runTurn() {
            Scanner playerScanner = new Scanner(System.in);
            playerScanner.useDelimiter("\n");

                //resets attacks made this turn
            int mainAttacks = 0;
            int offAttacks = 0;

                //String attacks stores the attacks your weapon can make
            String attacks = playerMainWeapon.getAttackOneName();
            if (playerMainWeapon.getAttacksTotal() > 1)
                attacks += (", " + playerMainWeapon.getAttackTwoName());
            if (playerMainWeapon.getAttacksTotal() > 2)
                attacks += (", " + playerMainWeapon.getAttackThreeName());

            if (!playerOffWeapon.getWeaponName().equals("Unarmed")) {
                attacks += (", " + playerOffWeapon.getAttackOneName());
                if (playerOffWeapon.getAttacksTotal() > 1)
                    attacks += (", " + playerOffWeapon.getAttackTwoName());
                if (playerOffWeapon.getAttacksTotal() > 2)
                    attacks += (", " + playerOffWeapon.getAttackThreeName());
            }

            attacks += ".";

            while (actionPoints > 0) { //runs until you die, have no action points, or manually end turn
                if (!isAlive)
                    break;

                    //get player input (stored in String action)
                System.out.println("It is your turn. You have " + actionPoints +
                        " action points remaining. What would you like to do? \n"
                        + attacks);
                String cased = playerScanner.next();
                String action = cased.toLowerCase();

                //mainhand attacks
                if (action.equals(playerMainWeapon.getAttackOneName()) && mainAttacks < playerMainWeapon.getAttacksPerTurn()) {   //Attack One
                    if (actionPoints >= playerMainWeapon.getAttackOneAP()) {  //If you have enough AP and aren't breaking the 2 attacks/weapon rule

                            //Subtract AP, call the attack function, increase attacks made this turn
                        actionPoints -= playerMainWeapon.getAttackOneAP();
                        System.out.println("You attacked " + Main.enemy.getName() + " with your " + playerMainWeapon.getWeaponName()
                                            + "'s " + playerMainWeapon.getAttackOneName() + ".");
                        attack(playerMainWeapon.getAttackOneDice(), playerMainWeapon.getAttackOneDamage(), Main.enemy);
                        mainAttacks++;

                            //print error messages
                    } else if (actionPoints < playerMainWeapon.getAttackOneAP()) {
                        System.out.println("Not enough Action Points.");
                    }
                } else if (mainAttacks >= playerMainWeapon.getAttacksPerTurn() && !(playerMainWeapon.getID() == playerOffWeapon.getID())) {
                    System.out.println("You have made too many attacks with this weapon.");
                } else

                if (action.equals(playerMainWeapon.getAttackTwoName()) && mainAttacks < playerMainWeapon.getAttacksPerTurn()) { //Attack Two
                    if (actionPoints >= playerMainWeapon.getAttackTwoAP()) {  //If you have enough AP and aren't breaking the 2 attacks/weapon rule

                            //Subtract AP, call the attack function, increase attacks made this turn
                        actionPoints -= playerMainWeapon.getAttackTwoAP();
                        System.out.println("You attacked " + Main.enemy.getName() + " with your " + playerMainWeapon.getWeaponName()
                                + "'s " + playerMainWeapon.getAttackTwoName() + ".");
                        attack(playerMainWeapon.getAttackTwoDice(), playerMainWeapon.getAttackTwoDamage(), Main.enemy);
                        mainAttacks++;

                            //print error messages
                    } else if (actionPoints < playerMainWeapon.getAttackTwoAP()) {
                        System.out.println("Not enough Action Points.");
                    }
                } else if (mainAttacks >= playerMainWeapon.getAttacksPerTurn() && !(playerMainWeapon.getID() == playerOffWeapon.getID())) {
                    System.out.println("You have made too many attacks with this weapon.");
                } else

                if (action.equals(playerMainWeapon.getAttackThreeName()) && mainAttacks < playerMainWeapon.getAttacksPerTurn()) { //Attack Two
                    if (actionPoints >= playerMainWeapon.getAttackThreeAP()) {  //If you have enough AP and aren't breaking the 2 attacks/weapon rule

                        //Subtract AP, call the attack function, increase attacks made this turn
                        actionPoints -= playerMainWeapon.getAttackThreeAP();
                        System.out.println("You attacked " + Main.enemy.getName() + " with your " + playerMainWeapon.getWeaponName()
                                + "'s " + playerMainWeapon.getAttackThreeName() + ".");
                        attack(playerMainWeapon.getAttackThreeDice(), playerMainWeapon.getAttackThreeDamage(), Main.enemy);
                        mainAttacks++;

                        //print error messages
                    } else if (actionPoints < playerMainWeapon.getAttackThreeAP()) {
                        System.out.println("Not enough Action Points.");
                    }
                } else if (mainAttacks >= playerMainWeapon.getAttacksPerTurn() && !(playerMainWeapon.getID() == playerOffWeapon.getID())) {
                    System.out.println("You have made too many attacks with this weapon.");
                } else

                //offhand attacks
                if (action.equals(playerOffWeapon.getAttackOneName())) {   //Attack One
                    if (actionPoints >= playerOffWeapon.getAttackOneAP() && offAttacks < playerOffWeapon.getAttacksPerTurn()) {  //If you have enough AP and aren't breaking the 2 attacks/weapon rule

                        //Subtract AP, call the attack function, increase attacks made this turn
                        actionPoints -= playerOffWeapon.getAttackOneAP();
                        System.out.println("You attacked " + Main.enemy.getName() + " with your " + playerOffWeapon.getWeaponName()
                                + "'s " + playerOffWeapon.getAttackOneName() + ".");
                        attack(playerOffWeapon.getAttackOneDice(), playerOffWeapon.getAttackOneDamage(), Main.enemy);
                        offAttacks++;

                        //print error messages
                    } else if (actionPoints < playerOffWeapon.getAttackOneAP()) {
                        System.out.println("Not enough Action Points.");
                    } else if (offAttacks >= playerOffWeapon.getAttacksPerTurn()) {
                        System.out.println("You have made too many attacks with this weapon.");
                    }
                } else

                if (action.equals(playerOffWeapon.getAttackTwoName())) { //Attack Two
                    if (actionPoints >= playerOffWeapon.getAttackTwoAP() && offAttacks < playerOffWeapon.getAttacksPerTurn()) {  //If you have enough AP and aren't breaking the 2 attacks/weapon rule

                        //Subtract AP, call the attack function, increase attacks made this turn
                        actionPoints -= playerOffWeapon.getAttackTwoAP();
                        System.out.println("You attacked " + Main.enemy.getName() + " with your " + playerOffWeapon.getWeaponName()
                                + "'s " + playerOffWeapon.getAttackTwoName() + ".");
                        attack(playerOffWeapon.getAttackTwoDice(), playerOffWeapon.getAttackTwoDamage(), Main.enemy);
                        offAttacks++;

                        //print error messages
                    } else if (actionPoints < playerOffWeapon.getAttackTwoAP()) {
                        System.out.println("Not enough Action Points.");
                    } else if (offAttacks >= playerOffWeapon.getAttacksPerTurn()) {
                        System.out.println("You have made too many attacks with this weapon.");
                    }
                } else

                if (action.equals(playerOffWeapon.getAttackThreeName())) { //Attack Two
                    if (actionPoints >= playerOffWeapon.getAttackThreeAP() && offAttacks < playerOffWeapon.getAttacksPerTurn()) {  //If you have enough AP and aren't breaking the 2 attacks/weapon rule

                        //Subtract AP, call the attack function, increase attacks made this turn
                        actionPoints -= playerOffWeapon.getAttackThreeAP();
                        System.out.println("You attacked " + Main.enemy.getName() + " with your " + playerOffWeapon.getWeaponName()
                                + "'s " + playerOffWeapon.getAttackThreeName() + ".");
                        attack(playerOffWeapon.getAttackThreeDice(), playerOffWeapon.getAttackThreeDamage(), Main.enemy);
                        offAttacks++;

                        //print error messages
                    } else if (actionPoints < playerOffWeapon.getAttackTwoAP()) {
                        System.out.println("Not enough Action Points.");
                    } else if (offAttacks >= playerOffWeapon.getAttacksPerTurn()) {
                        System.out.println("You have made too many attacks with this weapon.");
                    }
                } //no else, end of attacks

                    //end turn script
                if (action.equals("end turn") || action.equals("quit")) {
                    break;
                }
            }
            endTurn();

        }

        public void endTurn() {
            actionPoints = maxActionPoints;
        }

        public int parry(int diceNumber, int diceType, int damageBonus) {
            Scanner parryScanner = new Scanner(System.in);
            parryScanner.useDelimiter("\n");

            int parryModifier = Math.max(playerMainWeapon.getParryModifier(), playerOffWeapon.getParryModifier()) + proficiency;

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
