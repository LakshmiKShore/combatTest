import java.util.ArrayList;

/* TODO:
    - Write creature class that Enemy and Player can extend from
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

        Attack poke = new Attack("Poke", "pokepokepokepoke", 3, 4, 1, Main.physical);
        Stance stance = new Stance(new Attack[] {poke}, 1, 1);
        Weapon weapon = new Weapon(stance);

        Creature bogJ = new Creature("Bog Jones",1, 3, 0, 1, 4, 0, 1, 0, false, true, false, new Skill[]{Creature.biology, Creature.perception}, new Weapon[] {weapon});
        Creature deltaJ = new Creature("Delta Jones",1, 3, 0, 1, 4, 0, 1, 0, false, true, false, new Skill[]{Creature.biology, Creature.perception}, new Weapon[] {weapon});

        System.out.println("Health: " + bogJ.getHp());
        System.out.println(weapon.canAttack(poke, 2, 1));
        System.out.println(weapon.attack(poke, bogJ, deltaJ));
        System.out.println("Health: " + bogJ.getHp());

    }

}