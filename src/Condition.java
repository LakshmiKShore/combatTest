public class Condition {

    /*
    Condition class only has Name, Type, Severity, and Description.
    Functionality of Conditions are mostly in the Creature class.
    For example, the bleeding condition deals 1d4 additional damage whenever you take non-bleed damage.
    This condition is implemented by checking if a Creature has the bleeding condition when they take damage.
    If they do, then we call Creature.bleedDamage()
     */

    /*
        TODO: Implement the following
            - Exhausted* (needs removal conditions)
            - Poisoned
            - Distracted
            - Taunted
            - Prone
            - Incapacitated
     */

    String name;
    String description;
    int type;
    int severity;
    boolean isStacking;
    int duration = 999;
    int stacks = 0;

    public static final int internal = 0;
    public static final int external = 1;
    public static final int minor = 0;
    public static final int major = 1;
    public static final int unremovable = 2;

    public Condition(String name, String description, int type, int severity, boolean isStacking) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.severity = severity;
        this.isStacking = isStacking;
    }

    public Condition(Condition toCopy) {
        name = toCopy.getName();
        description = toCopy.getDescription();
        type = toCopy.getType();
        severity = toCopy.getSeverity();
        isStacking = toCopy.getIsStacking();
        stacks = toCopy.getStacks();
        duration = toCopy.getDuration();
    }

    public Condition(Condition toCopy, int duration) {
        name = toCopy.getName();
        description = toCopy.getDescription();
        type = toCopy.getType();
        severity = toCopy.getSeverity();
        isStacking = toCopy.getIsStacking();
        stacks = toCopy.getStacks();
        this.duration = duration;
    }


    public void increaseStacks(int stacks) {
        this.stacks += stacks;
    }

    public void reduceDuration() {
        duration--;
    }


    public boolean nameEquals(Condition toCompare) {
        return (this.name.equals(toCompare.getName()));
    }

    public String toString() {
        String output = name;

        if (isStacking) {
            output += " (" + stacks + ")";
        }
        if (duration < 25) {
            output += ", ends in " + duration + " rounds.";
        }
        return output;
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

    public boolean getIsStacking() {
        return isStacking;
    }

    public int getStacks() {
        return stacks;
    }

    public int getDuration() {
        return duration;
    }

}
