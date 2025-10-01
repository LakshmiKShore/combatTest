public class Weapons {
    static final int parryModifier = 0;
    static final int attackOneDice = 1;
    static final int attackOneDamage = 2;
    static final int attackTwoDice = 3;
    static final int attackTwoDamage = 4; //final ints to reduce confusion, use with (weapon)Values

    static String[] shortswordAttacks = {"Slash", "Quick Cut"};
    static int[] shortswordValues = {2, 1, 4, 1, 6}; //Parry mod, attack one xdx, attack two xdx, etc
}
