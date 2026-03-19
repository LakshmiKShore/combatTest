import java.sql.Array;
import java.util.ArrayList;

public class Condition {

    /*
    Functionality of Conditions are mostly in the Creature class.
    For example, the bleeding condition deals 1d4 additional damage whenever you take non-bleed damage.
    This condition is implemented by checking if a Creature has the bleeding condition when they take damage.
    If they do, then we call Creature.bleedDamage()
     */

    /*
        Condition class needs:
            - Name (or way to identify)
            - Description (for being printed)
            - Type (internal or external)
            - Severity (minor or major)
            - isRemovable
            - isStacking
            - isDuration
            - Creature inflictor
            - Creature target
            - durationDecreaseTiming (end/beginning of target's/inflictor's turn)
            - duration
            - stacks
            - arrayList of all conditions

            - decreaseDuration(Creature creature, boolean isStartOfTurn)
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
    public static final int internal = 0;
    public static final int external = 1;

    int severity;
    public static final int minor = 0;
    public static final int major = 1;

    boolean isRemovable;
    boolean isStacking;
    boolean isDuration;
    boolean isPersistent;

    Creature inflictor;
    Creature target;

    int durationDecreaseTiming;
    public static final int inflictorStartTurn = 0;
    public static final int inflictorEndTurn = 1;
    public static final int targetStartTurn = 2;
    public static final int targetEndTurn = 3;

    int duration;
    int stacks;

    public static ArrayList<Condition> conditions = new ArrayList<>();
    public static ArrayList<Condition> stackingConditions = new ArrayList<>();
    public static ArrayList<Condition> durationConditions = new ArrayList<>();


    //Creates the base condition that other conditions copy from. DOES NOT GET ADDED to the arraylists!
    public Condition(String name, String description, int type, int severity, boolean isRemovable, boolean isStacking, boolean isDuration, boolean isPersistent) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.severity = severity;
        this.isRemovable = isRemovable;
        this.isStacking = isStacking;
        this.isDuration = isDuration;
        this.isPersistent = isPersistent;
    }

    //Creates a copy of a non-stacking, non-duration condition, and inflicts it upon a creature.
    public Condition(Condition base, Creature target, Creature inflictor) {
        name = base.getName();
        description = base.getDescription();
        type = base.getType();
        severity = base.getSeverity();
        isRemovable = base.isRemovable();
        isStacking = base.isStacking();
        isDuration = base.isDuration();
        isPersistent = base.isPersistent();

        this.inflictor = inflictor;
        this.target = target;
        duration = -1;
        stacks = -1;

        conditions.add(this);
        //target.inflictCondition(this);
    }

    //Creates a copy of a stacking, non-duration condition, and inflicts it upon a creature.
    //public Condition(Condition base, int stacks, Creature target, Creature inflictor);


    //TODO: Finish Condition constructors


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

    public boolean isRemovable() {
        return isRemovable;
    }

    public boolean isStacking() {
        return isStacking;
    }

    public boolean isDuration() {
        return isDuration;
    }

    public boolean isPersistent() {
        return isPersistent;
    }

    public Creature getInflictor() {
        return inflictor;
    }

    public Creature getTarget() {
        return target;
    }

    public int getDurationDecreaseTiming() {
        return durationDecreaseTiming;
    }

    public int getDuration() {
        return duration;
    }

    public int getStacks() {
        return stacks;
    }

    public static ArrayList<Condition> getConditions() {
        return conditions;
    }

    public static ArrayList<Condition> getStackingConditions() {
        return stackingConditions;
    }

    public static ArrayList<Condition> getDurationConditions() {
        return durationConditions;
    }
}
