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
            - Stunned
            - Dazed
            - Exhausted* (needs removal conditions)
            - Burning
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
    boolean isDurationPaused;

    Creature inflicter;
    Creature target;
    Creature durationDecreasesOn;

    int durationDecreaseTiming;
    public static final int inflictorStartTurn = 2;
    public static final int inflictorEndTurn = 3;
    public static final int targetStartTurn = 0;
    public static final int targetEndTurn = 1;

    int duration;
    int stacks;

    public static ArrayList<Condition> allConditions = new ArrayList<>();
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
    public Condition(Condition base, Creature target, Creature inflicter) {
        name = base.getName();
        description = base.getDescription();
        type = base.getType();
        severity = base.getSeverity();
        isRemovable = base.isRemovable();
        isStacking = base.isStacking();
        isDuration = base.isDuration();
        isPersistent = base.isPersistent();

        this.inflicter = inflicter;
        this.target = target;
        duration = -1;
        stacks = -1;

        allConditions.add(this);
        target.addCondition(this);
    }

    //Creates a copy of a stacking, non-duration condition, and inflicts it upon a creature.
    public Condition(Condition base, int stacks, Creature target, Creature inflicter) {
        name = base.getName();
        description = base.getDescription();
        type = base.getType();
        severity = base.getSeverity();
        isRemovable = base.isRemovable();
        isStacking = base.isStacking();
        isDuration = base.isDuration();
        isPersistent = base.isPersistent();

        this.inflicter = inflicter;
        this.target = target;
        duration = -1;
        this.stacks = stacks;

        allConditions.add(this);
        stackingConditions.add(this);
        target.addCondition(this);
    }

    //Creates a copy of a non-stacking, duration condition, and inflicts it upon a creature.
    public Condition(Condition base, int duration, int durationDecreaseTiming, Creature target, Creature inflicter) {
        name = base.getName();
        description = base.getDescription();
        type = base.getType();
        severity = base.getSeverity();
        isRemovable = base.isRemovable();
        isStacking = base.isStacking();
        isDuration = base.isDuration();
        isPersistent = base.isPersistent();

        this.inflicter = inflicter;
        this.target = target;
        this.duration = duration;
        this.durationDecreaseTiming = durationDecreaseTiming;
        stacks = -1;

        if (durationDecreaseTiming == Condition.inflictorStartTurn || durationDecreaseTiming == Condition.inflictorEndTurn) {
            durationDecreasesOn = inflicter;
        } else {
            durationDecreasesOn = target;
        }

        allConditions.add(this);
        durationConditions.add(this);
        target.addCondition(this);
    }

    //Creates a copy of a stacking, duration condition, and inflicts it upon a creature.
    public Condition(Condition base, int stacks, int duration, int durationDecreaseTiming, Creature target, Creature inflicter) {
        name = base.getName();
        description = base.getDescription();
        type = base.getType();
        severity = base.getSeverity();
        isRemovable = base.isRemovable();
        isStacking = base.isStacking();
        isDuration = base.isDuration();
        isPersistent = base.isPersistent();

        this.inflicter = inflicter;
        this.target = target;
        this.duration = duration;
        this.durationDecreaseTiming = durationDecreaseTiming;
        this.stacks = stacks;

        if (durationDecreaseTiming == Condition.inflictorStartTurn || durationDecreaseTiming == Condition.inflictorEndTurn) {
            durationDecreasesOn = inflicter;
        } else {
            durationDecreasesOn = target;
        }

        allConditions.add(this);
        stackingConditions.add(this);
        durationConditions.add(this);
        target.addCondition(this);
    }


    //Checks all duration conditions to see if their durations should be decreased, then decreases them if they should be.
    public static void decreaseConditionsStartOfTurn(Creature creatureTakingTurn) {

        for (Condition condition : durationConditions) {

            if (condition.durationDecreasesOn != creatureTakingTurn) {
                continue;
            }
            if (condition.durationDecreaseTiming == Condition.inflictorEndTurn || condition.durationDecreaseTiming == Condition.targetEndTurn) {
                continue;
            }
            if (condition.isDurationPaused) {
                continue;
            }

            condition.duration--;
        }

        cleanUpAll();
    }


    //Checks all duration conditions to see if their durations should be decreased, then decreases them if they should be.
    public static void decreaseConditionsEndOfTurn(Creature creatureTakingTurn) {

        for (Condition condition : durationConditions) {

            if (condition.durationDecreasesOn != creatureTakingTurn) {
                continue;
            }
            if (condition.durationDecreaseTiming == Condition.inflictorStartTurn || condition.durationDecreaseTiming == Condition.targetStartTurn) {
                continue;
            }

            condition.duration--;
            condition.cleanUp();

        }

    }


    //reduces or increases a condition's stacks
    public void changeStacks(int amount) {
        if (!isStacking) {
            return;
        }

        stacks += amount;
    }



    //Checks all conditions to see if they should still be active, and if not, removes them from everywhere.
    public static void cleanUpAll() {
        for (int i = 0; i < allConditions.size(); i++) {
            boolean removed = allConditions.get(i).cleanUp();

            if (removed) {
                i--;
            }
        }
    }

    //Checks to see if the condition should still be active, then if not, removes it from everywhere.
    //Returns a boolean: TRUE if the condition was removed, FALSE if the condition was not removed.
    public boolean cleanUp() {
        if (isDuration && duration <= 0) {
            comprehensiveRemove();
            System.out.println(target.getName() + " is no longer " + this + ".");
            return true;
        }

        if (isStacking && stacks <= 0) {
            comprehensiveRemove();
            System.out.println(target.getName() + " is no longer " + this + ".");
            return true;
        }

        return false;
    }

    //removes the condition from all the places it needs to be removed from
    public void comprehensiveRemove() {

        allConditions.remove(this);
        durationConditions.remove(this);
        stackingConditions.remove(this);
        target.nonComprehensiveRemoveCondition(this);

    }




    public void setDuration(int value) {
        duration = value;
    }

    public void setDurationDecreaseTiming(int value) {
        durationDecreaseTiming = value;
    }

    public String toString() {
        return name;
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

    public Creature getInflicter() {
        return inflicter;
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

    public static ArrayList<Condition> getAllConditions() {
        return allConditions;
    }

    public static ArrayList<Condition> getStackingConditions() {
        return stackingConditions;
    }

    public static ArrayList<Condition> getDurationConditions() {
        return durationConditions;
    }
}
