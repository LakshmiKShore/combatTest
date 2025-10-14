import java.util.Scanner;

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
            if (actionPoints >= 3) {
                actionPoints -= 3;
                attack(6,1,Main.player);
            } else if (actionPoints >= 2) {
                actionPoints -= 2;
                attack(4,1,Main.player);
            } else {
                break;
            }
        }
        endTurn();
    }

    public void endTurn() {
        actionPoints = maxActionPoints;
    }

    public void attack(int diceType, int diceNumber, PlayerCharacter target) {
        if (attackRoll() >= target.parry(diceType, diceNumber, proficiency)) {
            int damage = damageRoll(diceType, diceNumber);
            target.reduceHealth(damage);
            System.out.println(target.getName() + " took " + damage + " damage and has " + target.getHealth() +
                    " health remaining.");
        } else {
            System.out.println(name + " missed.");
        }
    }

    public int parry(int diceType, int diceNumber, int damageBonus) {
        double averageDamage = (diceType/2.0 + 0.5) * diceNumber + damageBonus;
        if (actionPoints >= 1 && (averageDamage + 2) >= health )   {
            System.out.println(name + " attempted to parry.");
            actionPoints -= 1;
            return (int) (Math.random() * 20) + 1 + proficiency + 1; /*weapon parry modifier is set to 1
                                                                     until a weapon system is made */
        } else {
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