import java.util.ArrayList;
import java.util.Arrays;

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
            - ArrayList of all possible skill proficiencies X
            - ArrayList of skill proficiencies they have    X
            - ArrayList of all possible features
            - ArrayList of features they have
            - Weapons
            - Armor/Resistances
            - Speed
            - ArrayList of current conditions
        - Constructors
            - Automatic Constructor                         X
            - Full constructor                              X
        - Methods
            - Getters                                       X
            - Take Damage                                   X
            - Heal/Give THP                                 X
            - Print Status
            - Run Turn
                - Start Turn
                - Run Turn
                - End Turn
            - Defending Parry
            - Attacking Parry
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
    private int level;

    private boolean isWounded = false;
    private boolean isMortal = false;
    private boolean isDying = false;
    private boolean isDead = false;

    private int str;
    private int dex;
    private int con;
    private int wit;
    private int will;
    private int know;

    private boolean hasWillSaves;
    private boolean hasFortSaves;
    private boolean hasReflexSaves;

    private int universalRes;
    private int physicalRes;
    private int elementalRes;
    private int corrosiveRes;
    private int arcaneRes;

    public static final Skill[] allSkills = {
            new Skill("Athletics", Skill.str),
            new Skill("Exertion", Skill.str),
            new Skill("Agility", Skill.dex),
            new Skill("Lockpicking", Skill.dex),
            new Skill("Slight of Hand", Skill.dex),
            new Skill("Stealth", Skill.dex),
            new Skill("Arcana", Skill.know),
            new Skill("Biology", Skill.know),
            new Skill("Medicine", Skill.know),
            new Skill("Recall", Skill.know),
            new Skill("Deception", Skill.wit),
            new Skill("Foraging", Skill.wit),
            new Skill("Insight", Skill.wit),
            new Skill("Perception", Skill.wit),
            new Skill("Persuasion", Skill.wit),
            new Skill("Inspiration", Skill.will),
            new Skill("Intimidation", Skill.will),
            new Skill("Magic", Skill.will)
    };

    ArrayList<Skill> skillProfs = new ArrayList<Skill>();


    //Automated Constructor. Fill in abilities, saves, skill proficiencies, health mod, and level, get out all the stuff
    public Creature(int level, int healthMod, int str, int dex, int con, int know, int wit, int will,
                    boolean hasReflexSaves, boolean hasFortSaves, boolean hasWillSaves, Skill[] skillProfs) {

        this.level = level;
        this.healthMod = healthMod;
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.know = know;
        this.wit = wit;
        this.will = will;
        this.hasReflexSaves = hasReflexSaves;
        this.hasFortSaves = hasFortSaves;
        this.hasWillSaves = hasWillSaves;
        this.skillProfs = new ArrayList<Skill>(Arrays.asList(skillProfs));

        if (level >= 11) {
            proficiency = 3;
        } else if (level >= 6) {
            proficiency = 2;
        } else {
            proficiency = 1;
        }

        map = 7;
        ap = map;

        mep = level + (2 * con) + (2 * will);
        ep = mep;

        mhp = 4 + (level * healthMod) + (con * proficiency * 2);
        hp = mhp;
        thp = 0;
    }

    //fully customizable constructor. no formulas here baybee
    public Creature(int level, int healthMod, int str, int dex, int con, int know, int wit, int will,
                    boolean hasReflexSaves, boolean hasFortSaves, boolean hasWillSaves, int proficiency,
                    int map, int mep, int mhp, int universalRes, int physicalRes, int elementalRes, int corrosiveRes,
                    int arcaneRes, Skill[] skillProfs) {

        this.level = level;
        this.healthMod = healthMod;
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.know = know;
        this.wit = wit;
        this.will = will;
        this.hasReflexSaves = hasReflexSaves;
        this.hasFortSaves = hasFortSaves;
        this.hasWillSaves = hasWillSaves;
        this.proficiency = proficiency;
        this.skillProfs = new ArrayList<Skill>(Arrays.asList(skillProfs));

        this.map = map;
        ap = map;
        this.mep = mep;
        ep = mep;
        this.mhp = mhp;
        hp = mhp;
        thp = 0;

        this.universalRes = universalRes;
        this.physicalRes = physicalRes;
        this.elementalRes = elementalRes;
        this.corrosiveRes = corrosiveRes;
        this.arcaneRes = arcaneRes;
    }

    //handles all effects that happen at the start of your turn.
    public void startTurn() {

    }

    //runs a loop of the turn
    public void runTurn() {

        /*
        functionality limited until other methods can be made
         */

    }

    //handles all effects that happen at the end of your turn.
    public void endTurn() {

    }


    //lowers the creatures health by a given amount. Will update isWounded/Mortal/Dying.
    //takes an integer amount of damage and an integer representing damage type. (see Main.(dmg type))
    public void damage(int amount, int type, boolean isUnblockable) {

        int resisted = 0;
        if (!isUnblockable) {
            if (type == Main.physical) {
                resisted = physicalRes;
            } else if (type == Main.elemental) {
                resisted = elementalRes;
            } else if (type == Main.corrosive) {
                resisted = corrosiveRes;
            } else if (type == Main.arcane) {
                resisted = arcaneRes;
            }
        }

        int damage = Math.max(0, (amount - resisted));

        if (thp >= damage) {
            thp -= damage;
            damage = 0;
        } else {
            damage -= thp;
            thp = 0;
        }

        hp -= damage;

        if (hp <= mhp/2) {
            isWounded = true;
        }
        if (hp <= mhp/4) {
            isMortal = true;
        }
        if (hp <= 0) {
            isDying = true;
        }
    }

    //increases hp, updates isWounded/Mortal/Dying
    public void heal(int amount) {
        hp += amount;
        if (hp > mhp) {
            hp = mhp;
        }

        if (hp >= mhp/2) {
            isWounded = false;
        }
        if (hp >= mhp/4) {
            isMortal = false;
        }
        if (hp >= 0) {
            isDying = false;
        }
    }

    //increases temp hp (thp)
    public void giveThp(int amount) {
        thp += amount;
    }


    //getter methods (booooring!)

}
