public class Adventure {
    /*
    Adventure class holds a bunch of public variables, like weapons, enemies and that stuff.
    It also handles the adventure as a whole, ie choosing combats to run, tracking the floor, giving loot.
     */


    // WEAPONS

    public static final Weapon longsword = new Weapon(
            new Stance[] {
                    new Stance(
                            new Attack[] {new Attack("Slash", 3, 8, 1, Attack.physical)}, 2, 2, "Standard Grip"
                    ),
                    new Stance(
                            new Attack[] {new Attack("Thrust", 3, 6, 1, Attack.physical), new Attack("Cut", 2, 4, 1, Attack.physical)}, 3, 2, "Half Sword"
                    ),
                    new Stance(
                            new Attack[] {new Attack("Slam", 3, 12, 1, Attack.physical)}, 0, 2, "Mordhau"
                    )
            },
            "Longsword"
    );

    public static final Weapon warhammer = new Weapon(
            new Stance(
                    new Attack[] {new Attack("Bash", 3, 8, 1, Attack.physical), new Attack("Beak Strike", 2, 4, 1, Attack.physical)}, 1, 1, "Standard Grip"
            ),
            "Warhammer"
    );

    public static final Weapon heavyAxe = new Weapon(
            new Stance[] {
                    new Stance(
                            new Attack[] {new Attack("Hack", 4, 4, 2, Attack.physical), new Attack("Slash", 3, 6, 1, Attack.physical)}, 0, 1, "One Handed"
                    ),
                    new Stance(
                            new Attack[] {new Attack("Hack", 4, 6, 2, Attack.physical), new Attack("Slash", 3, 8, 1, Attack.physical)}, 1, 2, "Two Handed"
                    )
            },
            "Heavy Axe"
    );


    public static final Weapon[] basicWeapons = {longsword, warhammer, heavyAxe};

}
