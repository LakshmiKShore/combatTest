public class Entity {

    private String name;
    private int health;
    private int armorClass;
    private int proficiency;

    public Entity(String tempName, int tempHealth, int tempArmorClass, int tempProficiency) {
        name = tempName;
        health = tempHealth;
        armorClass = tempArmorClass;
        proficiency = tempProficiency;
    }

    public int attackRoll() {
        int result = 1 + proficiency + Main.rand.nextInt(19);
        return result;
    }

    public void reduceHealth(int damage) {
        health -= damage;
    }

    public void statusRead() {
        System.out.println("My health is" + );
    }

}
