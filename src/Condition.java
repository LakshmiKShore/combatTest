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

    Creature inflictor;
    Creature target;

    int durationDecreaseTiming;
    public static final int inflictorStartTurn = 0;
    public static final int inflictorEndTurn = 1;
    public static final int targetStartTurn = 2;
    public static final int targetEndTurn = 3;

    int duration;
    int stacks;

    puArrayList<Condition>

}
