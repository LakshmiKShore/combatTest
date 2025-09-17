public class Entity {

    private String name;
    private int health;
    private int armorClass;
    private int proficiency;
    private boolean isAlive;

    public Entity(String tempName, int tempHealth, int tempArmorClass, int tempProficiency) {
        name = tempName;
        health = tempHealth;
        armorClass = tempArmorClass;
        proficiency = tempProficiency;
        isAlive = true;
    }

    public Entity() {
        System.out.println("Null Entity Created");
        name = "Null";
        health = 1;
        armorClass = 10;
        proficiency = 1;
        isAlive = true;
    }

    public int attackRoll() {
        int result = 1 + proficiency + Main.rand.nextInt(19);
        return result;
    }

    public int damageRoll(int diceType, int diceNumber) {

    }

    public void reduceHealth(int damage) {
        health -= damage;
    }

    public void killEntity() {

    }

    public void getName() {
        System.out.println("My name is " + name );
    }

    public void getHeath() {
        System.out.println("My health is " + health);
    }

    public void getArmorClass() {
        System.out.println("My armor class is " + armorClass);
    }

    public void getProficiency() {
        System.out.println("My proficiency is " + proficiency);
    }

    public void getIsAlive() {
        if (isAlive == true) {
            System.out.println("I am alive");
        } else {
            System.out.println("I am not alive");
        }
    }

}
