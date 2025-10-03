import java.util.Scanner;

public class PlayerCharacter {

        private String name;
        private int maxHealth;
        private int health;
        private int healthMod;
        private int maxActionPoints;
        private int actionPoints;
        private int proficiency;
        private int maxEnergyPoints;
        private int energyPoints;
        private int level;
        private boolean isAlive;

        private int str;
        private int dex;
        private int con;
        private int wit;
        private int will;
        private int know;

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
        }

        public void runTurn() {
            if (!isAlive) {
                return;
            }
            Scanner playerScanner = new Scanner(System.in);
            playerScanner.useDelimiter("\n");

            while (actionPoints > 0) {
                System.out.println("It is your turn. You have " + actionPoints +
                        " action points remaining. What would you like to do? \n"
                        + playerWeapon.getAttackOneName() + ", " + playerWeapon.getAttackTwoName());
                String action = playerScanner.next();

                if ((action.equals(playerWeapon.getAttackOneName())) && actionPoints >= 3) {
                    actionPoints -= 3;
                    attack(playerWeapon.getAttackOneDice(),playerWeapon.getAttackOneDamage());
                }

                if ((action.equals("fast attack") || action.equals("quick attack")) && actionPoints >= 2) {
                    actionPoints -= 2;
                    attack(1,4);
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

        public int parry() {
            Scanner parryScanner = new Scanner(System.in);
            parryScanner.useDelimiter("\n");
            if (actionPoints >= 1) {
                System.out.println("You are being attacked. You have " + actionPoints + " action points remaining. " +
                        "Would you like to parry?");
                String action = parryScanner.next();

                if (action.equals("yes") || action.equals("parry")) {
                    actionPoints -= 1;
                    return (int) (Math.random() * 20) + 1 + proficiency + playerWeapon.getParryModifier();
                } else {
                    return -20;
                }
            } else {
                return -20;
            }
        }


        public void attack(int diceType, int diceNumber) {
            if (attackRoll() >= Main.enemy.parry()) {
                Main.enemy.reduceHealth(damageRoll(diceType, diceNumber));
                System.out.println(Main.enemy.getName() + " has " + Main.enemy.getHeath() +
                    " health remaining.");
            } else {
                System.out.println("You missed.");
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

        public int getHeath() {
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
