import java.util.ArrayList;

/* TODO:
    - Write creature class that Enemy and Player can extend from
    - Rewrite weapon class to use arrays
    - Create initiative system
    - Create feature class
    - Create character creation system
    - Create leveling system
 */

public class Main {

    static final int physical = 0;
    static final int elemental = 1;
    static final int corrosive = 2;
    static final int arcane = 3;

    public static void main(String[] args) {

        Creature bogJ = new Creature("Bog Jones",1, 2, 0, 1, 2, 0, 1, 0, false, true, false, new Skill[]{});

        System.out.println(Creature.foraging);
        System.out.println(Creature.stealth);
        System.out.println(Creature.athletics);

    }

}