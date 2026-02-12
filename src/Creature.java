public class Creature {

    /* Creature class needs:
        - Instance Variables
            - Name                                          X
            - Health Points (max, current, temp)            X
            - Energy Points (max, current)                  X
            - Action Points (max, current)                  X
            - Proficiency Bonus                             X
            - Health Modifier                               X
            - Str Dex Con Know Wit Will                     X
            - Level                                         X
            - ArrayList of all possible skill proficiencies
            - ArrayList of skill proficiencies they have
            - ArrayList of all possible features
            - ArrayList of features they have
        - Methods
            - Take Damage
            - Run Turn
                - Start Turn
                - Run Turn
                - End Turn
            - Parry
            - Roll Initiative
            - Roll Saving Throws
            - End Combat
            - Rest
            - Check for feature
            - Check for skill proficiency
     */

    private String name;
    private int mhp;
    private int hp;
    private int thp;
    private int mep;
    private int ep;
    private int map;
    private int ap;
    private int healthMod;
    private int proficiency;
    private boolean isAlive;
    private int level;

    private int str;
    private int dex;
    private int con;
    private int wit;
    private int will;
    private int know;

    private boolean hasWillSaves;
    private boolean hasFortSaves;
    private boolean hasReflexSaves;

    private static Skill[] allSkills;

}
