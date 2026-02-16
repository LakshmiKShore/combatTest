/* TODO:
    - Write creature class that Enemy and Player can extend from
        - Create Player class
        - Create Enemy class
    - Create feature class
    - Create character creation system
    - Create leveling system
 */

import java.util.Arrays;

public class Main {

    static final int physical = 0;
    static final int elemental = 1;
    static final int corrosive = 2;
    static final int arcane = 3;

    static final String[] damageTypes = new String[] {
            "Physical", "Elemental", "Corrosive", "Arcane"
    };

    public static void main(String[] args) {

        Attack poke = new Attack("Poke", "pokepokepokepoke", 3, 4, 1, Main.physical);
        Stance stance = new Stance(new Attack[] {poke}, 1, 1);
        Weapon weapon = new Weapon(stance, "Stick");

        Creature bogJ = new Creature("Bog Jones",1, 3, 0, 1, 4, 0, 1, 0, false, true, false, new Skill[]{Creature.biology, Creature.perception}, new Weapon[] {new Weapon(weapon), new Weapon(weapon)});
        Player fennick = new Player("Fennick",1, 3, 0, 1, 4, 0, 1, 0, false, true, false, new Skill[]{Creature.biology, Creature.perception}, new Weapon[] {new Weapon(weapon), new Weapon(weapon)});

        Combat combat = new Combat(new Creature[] {fennick}, new Creature[] {bogJ});
        combat.runCombat();

    }

}