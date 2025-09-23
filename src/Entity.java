import java.util.Scanner;

public class Entity {

    private String name;
    private int health;
    private int maxActionPoints;
    private int actionPoints;
    private int armorClass;
    private int proficiency;
    private boolean isAlive;

    public Entity(String tempName, int tempHealth, int tempArmorClass, int tempProficiency) {
        name = tempName;
        health = tempHealth;
        armorClass = tempArmorClass;
        proficiency = tempProficiency;
        maxActionPoints = 7;
        isAlive = true;
    }

    public Entity() {
        System.out.println("Null PlayerCharacter Created");
        name = "Null";
        health = 1;
        armorClass = 10;
        proficiency = 1;
        maxActionPoints = 7;
        isAlive = true;
    }

    public void runTurn() {
        if (!isAlive) {
            return;
        }
        actionPoints = maxActionPoints;
        while (actionPoints > 0) {
            if (actionPoints >= 3) {
                actionPoints -= 3;
                attack();
            } else {
                break;
            }
        }
    }

    public void attack() {
        if (attackRoll() >= Main.player.getArmorClass()) {
            Main.player.reduceHealth(damageRoll(6, 1));
            System.out.println(Main.player.getName() + " has " + Main.player.getHeath() +
                    " health remaining.");
        } else {
            System.out.println(getName() + " missed.");
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
        health = 0;
        isAlive = false;
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

    //skks