import java.util.Scanner;

public class PlayerCharacter {

        private String name;
        private int health;
        private int maxActionPoints;
        private int actionPoints;
        private int armorClass;
        private int proficiency;
        private boolean isAlive;

        public PlayerCharacter(String tempName, int tempHealth, int tempArmorClass, int tempProficiency) {
            name = tempName;
            health = tempHealth;
            armorClass = tempArmorClass;
            proficiency = tempProficiency;
            maxActionPoints = 7;
            isAlive = true;
        }

        public PlayerCharacter() {
            System.out.println("Null PlayerCharacter Created");
            name = "Null";
            health = 1;
            armorClass = 10;
            proficiency = 1;
            maxActionPoints = 7;
            isAlive = true;
        }

        public void runTurn() {
            Scanner playerScanner = new Scanner(System.in);
            playerScanner.useDelimiter("\n");
            actionPoints = maxActionPoints;

            while (actionPoints > 0) {
                System.out.println("It is your turn. You have " + actionPoints +
                        " action points remaining. What would you like to do?");
                String action = playerScanner.next();

                if (action.equals("attack") && actionPoints >= 3) {
                    actionPoints -= 3;
                    int toHit = attackRoll();

                    if (toHit >= Main.enemy.getArmorClass()) {
                        Main.enemy.reduceHealth(damageRoll(6, 1));
                        System.out.println("Bog Jones has " + Main.enemy.getHeath() +
                                " health remaining.");
                    } else {
                        System.out.println("You missed.");
                    }
                }

                if (action.equals("end turn")) {
                    break;
                }

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
            return result;
        }

        public void reduceHealth(int damage) {
            health -= damage;
            if (health <= 0) {
                isAlive = false;
            }
        }

        public void killEntity() {

        }

        public String getName() {
            return name;
        }

        public int getHeath() {
            return health;
        }

        public int getArmorClass() {
            return armorClass;
        }

        public int getProficiency() {
            return proficiency;
        }

        public boolean getIsAlive() {
            return isAlive;
        }
    }
