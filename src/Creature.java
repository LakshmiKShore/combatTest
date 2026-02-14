import javax.management.BadAttributeValueExpException;
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
            - Array of all skills                           X
            - ArrayList of skill proficiencies they have    X
            - ArrayList of all possible features
            - ArrayList of features they have
            - Weapons
            - Armor/Resistances
            - Speed
            - ArrayList of current conditions
            - ArrayList of actions that can be taken
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
            - Defending Parry                               X
            - Attacking Parry                               X
            - Roll Initiative
            - Roll Saving Throws                            X
            - End Combat
            - Rest
            - Check for feature
            - Check for skill proficiency                   X
     */

    protected String name;
    protected int mhp;
    protected int hp;
    protected int thp;
    protected int mep;
    protected int ep;
    protected int map;
    protected int ap;
    protected int healthMod;
    protected int proficiency;
    protected int level;

    protected boolean isWounded = false;
    protected boolean isMortal = false;
    protected boolean isDying = false;
    protected boolean isDead = false;

    protected int[] abilities = new int[6];
    static final int str = 0;
    static final int dex = 1;
    static final int con = 2;
    static final int know = 3;
    static final int wit = 4;
    static final int will = 5;

    protected boolean[] savingThrows = new boolean[3];
    static final int fortitudeSave = 0;
    static final int reflexSave = 1;
    static final int willSave = 2;

    protected int universalRes;
    protected int physicalRes;
    protected int elementalRes;
    protected int corrosiveRes;
    protected int arcaneRes;

    public static final Skill athletics = new Skill("Athletics", str);
    public static final Skill exertion = new Skill("Exertion", str);
    public static final Skill agility = new Skill("Agility", dex);
    public static final Skill lockpicking = new Skill("Lockpicking", dex);
    public static final Skill slightOfHand = new Skill("Slight of Hand", dex);
    public static final Skill stealth = new Skill("Stealth", dex);
    public static final Skill arcana = new Skill("Arcana", know);
    public static final Skill biology = new Skill("Biology", know);
    public static final Skill medicine = new Skill("Medicine", know);
    public static final Skill recall = new Skill("Recall", know);
    public static final Skill deception = new Skill("Deception", wit);
    public static final Skill foraging = new Skill("Foraging", wit);
    public static final Skill insight = new Skill("Insight", wit);
    public static final Skill perception = new Skill("Perception", wit);
    public static final Skill persuasion = new Skill("Persuasion", wit);
    public static final Skill inspiration = new Skill("Inspiration", will);
    public static final Skill intimidation = new Skill("Intimidation", will);
    public static final Skill magic = new Skill("Magic", will);

    public static Skill[] allSkills = {
            athletics, exertion, agility, lockpicking, slightOfHand, stealth, arcana, biology, medicine,
            recall, deception, foraging, insight, perception, persuasion, inspiration, intimidation, magic
    };

    ArrayList<Skill> skillProfs = new ArrayList<Skill>();

    private Weapon[] currentWeapons;


    //Automated Constructor. Fill in abilities, saves, skill proficiencies, health mod, and level, get out all the stuff
    public Creature(String name, int level, int healthMod, int str, int dex, int con, int know, int wit, int will,
                    boolean hasReflexSaves, boolean hasFortSaves, boolean hasWillSaves, Skill[] skillProfs) {

        this.name = name;
        this.level = level;
        this.healthMod = healthMod;
        abilities[str] = str;
        abilities[dex] = dex;
        abilities[con] = con;
        abilities[know] = know;
        abilities[wit] = wit;
        abilities[will] = will;
        savingThrows[reflexSave] = hasReflexSaves;
        savingThrows[fortitudeSave] = hasFortSaves;
        savingThrows[willSave] = hasWillSaves;
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
    public Creature(String name, int level, int healthMod, int str, int dex, int con, int know, int wit, int will,
                    boolean hasReflexSaves, boolean hasFortSaves, boolean hasWillSaves, int proficiency,
                    int map, int mep, int mhp, int universalRes, int physicalRes, int elementalRes, int corrosiveRes,
                    int arcaneRes, Skill[] skillProfs) {

        this.name = name;
        this.level = level;
        this.healthMod = healthMod;
        abilities[str] = str;
        abilities[dex] = dex;
        abilities[con] = con;
        abilities[know] = know;
        abilities[wit] = wit;
        abilities[will] = will;
        savingThrows[reflexSave] = hasReflexSaves;
        savingThrows[fortitudeSave] = hasFortSaves;
        savingThrows[willSave] = hasWillSaves;
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


    //COMBAT ASSIST METHODS (attacks, parries, saves, etc)

    public int attackingParry(int advantages, int disadvantages) {
        return rollD20(proficiency, advantages, disadvantages);
    }

    public int defendingParry(int advantages, int disadvantages) {
        return rollD20(proficiency + maxParryBonus(), advantages, disadvantages);
    }

    public int maxParryBonus() {
        int max = currentWeapons[0].getCurrentStance().getParryBonus();

        for (Weapon x : currentWeapons) {
            if (max < x.getCurrentStance().getParryBonus()) {
                max = x.getCurrentStance().getParryBonus();
            }
        }

        return max;
    }

    //returns true if they succeeded the save, false if they failed.
    public boolean succeededSavingThrow(int dc, int type, int advantages, int disadvantages) {
        int modifier = 0;
        if (savingThrows[type]) {
            modifier += proficiency;
        }

        int roll = rollD20(modifier, advantages, disadvantages);
        return (roll >= dc);
    }



    //HEALTH RELATED METHODS (damage, heal)

    //lowers the creatures health by a given amount. Will update isWounded/Mortal/Dying.
    //takes an integer amount of damage and an integer representing damage type. (see Attack.(dmg type))
    public void damage(int amount, int type) {

        int resisted = 0;
        if (type == Main.physical) {
            resisted = physicalRes;
        } else if (type == Main.elemental) {
            resisted = elementalRes;
        } else if (type == Main.corrosive) {
            resisted = corrosiveRes;
        } else if (type == Main.arcane) {
            resisted = arcaneRes;
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
        if (hp <= (-1 * (mhp / 2))) {
            isDead = true;
        }
    }

    //a version of the damage method that doesn't factor in resistances.
    public void damageUnresistable(int amount, int type) {
        if (thp >= amount) {
            thp -= amount;
            amount = 0;
        } else {
            amount -= thp;
            thp = 0;
        }

        hp -= amount;

        if (hp <= mhp/2) {
            isWounded = true;
        }
        if (hp <= mhp/4) {
            isMortal = true;
        }
        if (hp <= 0) {
            isDying = true;
        }
        if (hp <= (-1 * (mhp / 2))) {
            isDead = true;
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




    //OUT OF COMBAT METHODS

    //makes an ability check
    //generates a random number 1-20, adds the relevant skill, then adds proficiency if you are proficient, returns the result
    public int skillCheck(Skill skill, int advantages, int disadvantages) {
        int modifier = abilities[skill.getAbility()];
        if (isProficient(skill)) {
            modifier += proficiency;
        }

        return rollD20(modifier, advantages, disadvantages);
    }

    //checks if the creature is proficient in a skill (checks if the skill is in skillProfs)
    public boolean isProficient(Skill skill) {
        for (Skill x : skillProfs) {
            if (x.equals(skill)) {
                return true;
            }
        }
        return false;
    }



    //UTILITY METHODS

    public int rollD20(int modifier, int advantages, int disadvantages) {
        int maxRoll = -99;
        int minRoll = 99;
        int numRolls = Math.abs(advantages - disadvantages);

        for (int i = 0; i < numRolls; i++) {
            int roll = (int) (Math.random() * 20) + 1 + modifier;
            if (roll > maxRoll) {
                maxRoll = roll;
            }
            if (roll < minRoll) {
                minRoll = roll;
            }
        }

        if (advantages > disadvantages) {
            return maxRoll;
        } else {
            return minRoll;
        }
    }


    //getter methods (booooring!)

}
