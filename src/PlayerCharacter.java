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

        Weapon playerWeapon = new Weapon(0);

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

            System.out.println("What weapon would you like to use?");
            System.out.println("Shortsword, Mace, Spear, Knife");
            String cased = creationScanner.next();
            String input = cased.toLowerCase();

            if (input.equals("shortsword"))
                playerWeapon.setWeapon(0);
            if (input.equals("mace"))
                playerWeapon.setWeapon(1);
            if (input.equals("spear"))
                playerWeapon.setWeapon(2);
            if (input.equals("knife"))
                playerWeapon.setWeapon(3);

            System.out.println("You chose the: " + playerWeapon.getWeaponName());
        }

        public void runTurn() {
            Scanner playerScanner = new Scanner(System.in);
            playerScanner.useDelimiter("\n");
            int mainAttacks = 0;
            String attacks = playerWeapon.getAttackOneName() + " ";
            if (playerWeapon.getAttacksTotal() > 1)
                attacks += (playerWeapon.getAttackTwoName() + " ");
            if (playerWeapon.getAttacksTotal() > 2)
                attacks += (playerWeapon.getAttackThreeName() + " ");

            while (actionPoints > 0) {
                if (!isAlive)
                    break;
                System.out.println("It is your turn. You have " + actionPoints +
                        " action points remaining. What would you like to do? \n"
                        + attacks);
                String cased = playerScanner.next();
                String action = cased.toLowerCase();

                if (action.equals(playerWeapon.getAttackOneName())) {
                    if (actionPoints >= playerWeapon.getAttackOneAP() && mainAttacks < playerWeapon.getAttacksTotal()) {
                        actionPoints -= playerWeapon.getAttackOneAP();
                        System.out.println("You attacked " + Main.enemy.getName() + " with your " + playerWeapon.getWeaponName()
                                            + "'s " + playerWeapon.getAttackOneName() + ".");
                        attack(playerWeapon.getAttackOneDice(), playerWeapon.getAttackOneDamage(), Main.enemy);
                        mainAttacks++;
                    } else if (actionPoints < playerWeapon.getAttackOneAP()) {
                        System.out.println("Not enough Action Points.");
                    } else if (mainAttacks >= playerWeapon.getAttacksTotal()) {
                        System.out.println("You have made too many attacks with this weapon.");
                    }
                }

                if (action.equals(playerWeapon.getAttackTwoName())) {
                    if (actionPoints >= playerWeapon.getAttackTwoAP() && mainAttacks < playerWeapon.getAttacksTotal()) {
                        actionPoints -= playerWeapon.getAttackTwoAP();
                        System.out.println("You attacked " + Main.enemy.getName() + " with your " + playerWeapon.getWeaponName()
                                + "'s " + playerWeapon.getAttackTwoName() + ".");
                        attack(playerWeapon.getAttackTwoDice(), playerWeapon.getAttackTwoDamage(), Main.enemy);
                        mainAttacks++;
                    } else if (actionPoints < playerWeapon.getAttackTwoAP()) {
                        System.out.println("Not enough Action Points.");
                    } else if (mainAttacks >= playerWeapon.getAttacksTotal()) {
                        System.out.println("You have made too many attacks with this weapon.");
                    }
                }

                if (action.equals("end turn")) {
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
            if (actionPoints >= 1) {
                System.out.println(name + " is being attacked for " + diceNumber + "d" + diceType + " + " + damageBonus +
                        " Damage. You have " + actionPoints + " action points remaining. " + "Would you like to parry?");
                String cased = parryScanner.next();
                String action = cased.toLowerCase();

                if (action.equals("yes") || action.equals("parry") || action.equals("y")) {
                    actionPoints -= 1;
                    int parryRoll = (int) (Math.random() * 20) + 1 + proficiency + playerWeapon.getParryModifier();
                    System.out.println(name + " got a " + parryRoll + ".");
                    return parryRoll;
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
