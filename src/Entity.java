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

    public int attackRoll() {
        int result = 1 + proficiency + (int) (Math.random()*20) + 1;
        return result;
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

    //skks