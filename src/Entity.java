public class Entity {

    private String name;
    private int maxHeath;
    private int health;
    private int healthMod;
    private int maxActionPoints;
    private int actionPoints;
    private int proficiency;
    private int maxEnergyPoints;
    private int energyPoints;
    private boolean isAlive;

    private int str;
    private int dex;
    private int con;
    private int wit;
    private int will;
    private int know;

    public Entity(String tempName, int tempHealth, int tempProficiency) {
        name = tempName;
        health = tempHealth;
        proficiency = tempProficiency;
        maxActionPoints = 7;
        isAlive = true;
    }

    public Entity() {
        System.out.println("Null PlayerCharacter Created");
        name = "Null";
        health = 1;
        proficiency = 1;
        maxActionPoints = 7;
        isAlive = true;
    }

    public void runTurn() {
        if (!isAlive) {
            return;
        }
        while (actionPoints > 0) {
            PlayerCharacter target = new PlayerCharacter();
            if (Main.p1.getIsAlive() && Main.p2.getIsAlive()) { //selecting target
                if (Math.random() > 0.5) {
                    target = Main.p1;
                } else {
                    target = Main.p2;
                }
            } else if (Main.p2.getIsAlive()) { //if a player is dead, target other player
                target = Main.p1;
            } else {
                target = Main.p2;
            }

            if (actionPoints == 4) { //making attacks
                actionPoints -= 2;
                attack(1,4,target);
            } else if (actionPoints >= 3) {
                actionPoints -= 3;
                attack(1,6,target);
            } else if (actionPoints >= 2) {
                actionPoints -= 2;
                attack(1,4,target);
            } else {
                break;
            }
        }
        endTurn();
    }

    public void endTurn() {
        actionPoints = maxActionPoints;
    }

    public void attack(int diceNumber, int diceType, PlayerCharacter target) {
        int atkParryRoll = attackRoll();
        int defParryRoll = target.defendingParry(diceNumber, diceType, proficiency);
        if (defParryRoll != -20) {
            System.out.println(name + " got a " + atkParryRoll + ".");
        }
        if (atkParryRoll > defParryRoll) {
            int damage = damageRoll(diceType, diceNumber);
            target.reduceHealth(damage);
        } else {
            System.out.println(name + " missed.");
        }
    }

    public int parry(int diceType, int diceNumber, int damageBonus) {
        double averageDamage = (diceType/2.0 + 0.5) * diceNumber + damageBonus;
        if (actionPoints >= 1 && (averageDamage + 2) >= health/2.0 )   {
            System.out.println(name + " attempted to parry.");
            actionPoints -= 1;
            int modifier = proficiency + 2; /*weapon parry modifier is set to 1 until a weapon system is made */
            int parryRoll = (int) (Math.random() * 20) + 1;
            System.out.println(name + " got a " + parryRoll + " + " + modifier + ".");
            return parryRoll + modifier;
        } else {
            System.out.println(name + " did not parry.");
            return -20;
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
            System.out.println(name + " took " + damage + " damage and died.");
        } else {
            System.out.println(name + " took " + damage + " damage and has " + health + " health remaining.");
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
        return maxHeath;
    }

    public int getHeath() {
        return health;
    }

    public int getHealthMod() {
        return healthMod;
    }

    public int getMaxEnergyPoints() {
        return maxEnergyPoints;
    }

    public int getEnergyPoints() {
        return energyPoints;
    }

    public int getProficiency() {
        return proficiency;
    }

    public boolean getIsAlive() {
        return isAlive;
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