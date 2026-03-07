public class Condition {

    /*
    Condition class only has Name, Type, Severity, and Description.
    Functionality of Conditions are mostly in the Creature class.
    For example, the bleeding condition deals 1d4 additional damage whenever you take non-bleed damage.
    This condition is implemented by checking if a Creature has the bleeding condition when they take damage.
    If they do, then we call Creature.bleedDamage()
     */

    String name;
    String description;
    int type;
    int severity;
    boolean stacking;
    int stacks = 0;

    public static final int internal = 0;
    public static final int external = 1;
    public static final int minor = 0;
    public static final int major = 1;
    public static final int unremovable = 2;

    public Condition(String name, String description, int type, int severity, boolean stacking) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.severity = severity;
        this.stacking = stacking;
    }

    public Condition(Condition toCopy) {
        name = toCopy.getName();
        description = toCopy.getDescription();
        type = toCopy.getType();
        severity = toCopy.getSeverity();
        stacking = toCopy.getStacking();
        stacks = toCopy.getStacks();
    }


    public void increaseStacks(int stacks) {
        this.stacks += stacks;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getType() {
        return type;
    }

    public int getSeverity() {
        return severity;
    }

    public boolean getStacking() {
        return stacking;
    }

    public int getStacks() {
        return stacks;
    }

}
