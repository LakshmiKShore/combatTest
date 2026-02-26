import java.sql.Array;
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
            - Weapons                                       X
            - Resistances                                   X
            - Speed (CUT SYSTEM, MAYBE ADD LATER?)          X
            - ArrayList of current conditions
            - ArrayList of actions that can be taken        X
        - Constructors
            - Automatic Constructor                         X
            - Full constructor                              X
        - Methods
            - Getters                                       X
            - Take Damage                                   X
            - Heal/Give THP                                 X
            - Print Status
            - Run Turn                                      X
                - Start Turn                                X
                - Run Turn                                  X
                - End Turn                                  X
            - Defending Parry                               X
            - Attacking Parry                               X
            - Roll Initiative                               X
            - Roll Saving Throws                            X
            - End Combat
            - Rest
            - Check for feature
            - Check for skill proficiency                   X
            - Level Up (maybe put in Player subclass)
     */

    protected String name;
    protected int maxHp;
    protected int hp;
    protected int tempHp;
    protected int maxEp;
    protected int ep;
    protected int maxAp;
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

    protected int attacksMade;
    protected int maxHands;
    protected int freeHands;

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
    ArrayList<Skill> skillProfs = new ArrayList<>();

    protected ArrayList<Weapon> currentWeapons;

    protected Action parry = new Action("Parry", "Attempt to block an incoming attack.", 1, 0);
    protected Action changeStance = new Action("Change Stance", "Switch how you wield one of your weapons, changing which attacks you have access to.", 1, 0);

    protected Action[] baseActions = new Action[] {
            changeStance
    };


    //Automated Constructor. Fill in abilities, saves, skill proficiencies, health mod, and level, get out all the stuff
    public Creature(String name, int level, int healthMod, int str, int dex, int con, int know, int wit, int will,
                    boolean hasReflexSaves, boolean hasFortSaves, boolean hasWillSaves, Skill[] skillProfs, Weapon[] currentWeapons) {

        this.name = name;
        this.level = level;
        this.healthMod = healthMod;
        abilities[Creature.str] = str;
        abilities[Creature.dex] = dex;
        abilities[Creature.con] = con;
        abilities[Creature.know] = know;
        abilities[Creature.wit] = wit;
        abilities[Creature.will] = will;
        savingThrows[reflexSave] = hasReflexSaves;
        savingThrows[fortitudeSave] = hasFortSaves;
        savingThrows[willSave] = hasWillSaves;
        this.skillProfs = new ArrayList<>(Arrays.asList(skillProfs));
        this.currentWeapons = new ArrayList<>(Arrays.asList(currentWeapons));

        if (level >= 11) {
            proficiency = 3;
        } else if (level >= 6) {
            proficiency = 2;
        } else {
            proficiency = 1;
        }

        maxAp = 7;
        ap = maxAp;

        maxEp = level + (2 * con) + (2 * will);
        ep = maxEp;

        maxHp = 4 + (level * healthMod) + (con * proficiency * 2);
        hp = maxHp;
        tempHp = 0;

        maxHands = 2;
        freeHands = 2;
        attacksMade = 0;
    }

    //fully customizable constructor. no formulas here baybee
    public Creature(String name, int level, int healthMod, int str, int dex, int con, int know, int wit, int will,
                    boolean hasReflexSaves, boolean hasFortSaves, boolean hasWillSaves, int proficiency,
                    int maxAp, int maxEp, int maxHp, int universalRes, int physicalRes, int elementalRes, int corrosiveRes,
                    int arcaneRes, int maxHands, Skill[] skillProfs, Weapon[] currentWeapons) {

        this.name = name;
        this.level = level;
        this.healthMod = healthMod;
        abilities[Creature.str] = str;
        abilities[Creature.dex] = dex;
        abilities[Creature.con] = con;
        abilities[Creature.know] = know;
        abilities[Creature.wit] = wit;
        abilities[Creature.will] = will;
        savingThrows[reflexSave] = hasReflexSaves;
        savingThrows[fortitudeSave] = hasFortSaves;
        savingThrows[willSave] = hasWillSaves;
        this.proficiency = proficiency;
        this.skillProfs = new ArrayList<Skill>(Arrays.asList(skillProfs));
        this.currentWeapons = new ArrayList<>(Arrays.asList(currentWeapons));

        this.maxAp = maxAp;
        ap = maxAp;
        this.maxEp = maxEp;
        ep = maxEp;
        this.maxHp = maxHp;
        hp = maxHp;
        tempHp = 0;

        this.universalRes = universalRes;
        this.physicalRes = physicalRes;
        this.elementalRes = elementalRes;
        this.corrosiveRes = corrosiveRes;
        this.arcaneRes = arcaneRes;

        this.maxHands = maxHands;
        freeHands = maxHands;
        attacksMade = 0;
    }

    //this constructor solves an error for some reason idk
    public Creature() {
    }


    //TURN RELATED METHODS

    //Runs the 3 main turn-related methods in order.
    public void runTurn(Creature[] allies, Creature[] enemies) {
        if (isDead) {
            return;
        }

        startTurn(allies, enemies);
        mainTurn(allies, enemies);
        endTurn(allies, enemies);
    }

    //handles all effects that happen at the start of your turn.
    public void startTurn(Creature[] allies, Creature[] enemies) {
        /*
        WILL ADD FUNCTIONALITY ONCE FEATURES ARE ADDED.
        SOME THINGS DO HAPPEN HERE I PROMISE.
        JUST NONE YET.
         */

    }

    //runs a loop of the turn
    public void mainTurn(Creature[] allies, Creature[] enemies) {

        boolean quitTurn = false;

        //Turn Loop
        while (ap > 0 && !quitTurn) {

            boolean didSomething = turnBehavior(allies, enemies);

            quitTurn = !didSomething;

        }



    }

    //handles all effects that happen at the end of your turn.
    public void endTurn(Creature[] allies, Creature[] enemies) {

        if (isDying) {
            System.out.println(name + " is bleeding out.");
            damageUnresistable(1, Main.physical);
        }

        if (!isDying) {
            ap = maxAp;
        }

        for (Weapon weapon : currentWeapons) {
            weapon.resetAttacksMade();
        }

        attacksMade = 0;

    }

    //Decides what to do on the turn. Returns true if something was done this loop, false if not (Doing nothing ends the turn)
    //MUST BE OVERRIDDEN IN CHILD CLASSES!
    //A Creature will always attempt to use their first attack in their first weapon on the first enemy. If they can't, they end turn.

    public boolean turnBehavior(Creature[] allies, Creature[] enemies) {
        boolean didSomething = false;

        Attack firstAttack = currentWeapons.get(0).getCurrentStance().getAttacks()[0];

        if (canUseAttack(firstAttack)) {
            attack(firstAttack, weaponToUse(firstAttack), enemies[0]);
            didSomething = true;
        }

        return didSomething;
    }



    //ACTION TAKING RELATED METHODS (besides parry action)

    //runs an attack. Here is where action points are spent.
    public void attack(Attack attack, Weapon weapon, Creature target) {

        int discount = getNextAttackDiscount();
        int damageBonus = getNextAttackModifier();

        //if you cannot make that attack
        if (!weapon.canAttack(attack, ap, discount)) {
            System.out.println("Cannot use.");
            return;
        }

        System.out.println(name + " attacked " + target.getName() + " with their " + weapon.getName() + "'s " + attack.getName() + "." );

        weapon.attack(attack, target, this, damageBonus);
        ap -= (attack.getCost() - discount);
        attacksMade++;

    }

    //Checks if the player can use a given attack from any of their weapons.
    public boolean canUseAttack(Attack attack) {

        for (Weapon weapon : currentWeapons) {
            if (!weapon.hasAttack(attack)) {
                continue;
            }
            if (weapon.canAttack(attack, ap, getNextAttackDiscount())) {
                return true;
            }
        }

        return false;
    }

    //returns the first Weapon in currentWeapons that can use a given attack.
    //canUseAttack must return TRUE before you can use this method, or it could return an error
    public Weapon weaponToUse(Attack attack){
        for (Weapon weapon : currentWeapons) {
            if (!weapon.hasAttack(attack)) {
                continue;
            }
            if (weapon.canAttack(attack, ap, getNextAttackDiscount())) {
                return weapon;
            }
        }

        System.out.println("Error: No Valid Weapon");
        return null;

    }

    //Calculates the discount for the player's next attack.
    public int getNextAttackDiscount() {
        return 0;
    }

    //Calculates the modifier for the player's next attack.
    public int getNextAttackModifier() {
        int modifier = 0;
        modifier += proficiency;

        return modifier;
    }



    //PARRY RELATED METHODS

    public int attackingParry(int advantages, int disadvantages) {
        return rollD20(proficiency, advantages, disadvantages);
    }

    public int defendingParry(int advantages, int disadvantages) {
        return rollD20(proficiency + maxParryBonus(), advantages, disadvantages);
    }

    //Checks if the player can parry, then asks if they want to.
    //Here is where action points are spent on parrying.
    public boolean parryPrompt(int diceType, int diceNumber) {

        int discount = 0;

        int parryCost = parry.getCost() - discount;
        boolean willParry = parryBehavior(diceType, diceNumber, parryCost);

        if (parry.canUse(ap, discount) && willParry) {
            ap -= parryCost;
            return true;
        }
        return false;

    }

    //Asks if the Creature wants to parry. MUST BE OVERRIDDEN in child classes to add functionality.
    public boolean parryBehavior(int diceType, int diceNumber, int parryCost) {
        return true;
    }

    //finds the maximum parry bonus for all weapons in currentWeapons
    public int maxParryBonus() {
        int max = currentWeapons.get(0).getCurrentStance().getParryBonus();

        for (Weapon x : currentWeapons) {
            if (max < x.getCurrentStance().getParryBonus()) {
                max = x.getCurrentStance().getParryBonus();
            }
        }

        return max;
    }


    //finds all sources of advantage for an attacking parry.
    public int attackingParryAdvantages() {
        return 0;
    }

    //finds all sources of advantage for a defending parry.
    public int defendingParryAdvantages() {
        return 0;
    }

    //finds all sources of disadvantage for an attacking parry.
    public int attackingParryDisadvantages() {
        return 0;
    }

    //finds all sources of disadvantage for a defending parry.
    public int defendingParryDisadvantages() {
        return 0;
    }



    //OTHER COMBAT METHODS

    //returns true if they succeeded the save, false if they failed.
    public boolean succeededSavingThrow(int dc, int type, int advantages, int disadvantages) {
        int modifier = 0;
        if (type == Creature.reflexSave) {
            modifier += abilities[dex];
        }
        if (type == Creature.fortitudeSave) {
            modifier += abilities[con];
        }
        if (type == Creature.willSave) {
            modifier += abilities[will];
        }

        if (savingThrows[type]) {
            modifier += proficiency;
        }

        int roll = rollD20(modifier, advantages, disadvantages);
        return (roll >= dc);
    }

    //rolls initiative. Returns a double (a roll plus an offset from 0 to 0.999) to break ties.
    public double rollInitiative() {

        int roll = rollD20(abilities[dex], 0, 0);
        double offset = Math.random();
        return roll + offset;

    }

    //returns the total number of free hands the player has, when ignoring the given weapon.
    public int newlyAvailableHands(Weapon weapon) {
        int handsInUse = handsInUse();
        handsInUse -= weapon.getCurrentHandsUsed();
        return maxHands - handsInUse;
    }

    //updates the number of free hands the player has.
    public void updateFreeHands() {
        freeHands = maxHands - handsInUse();
    }

    //returns the total number of hands being currently used.
    public int handsInUse() {
        int sum = 0;
        for (Weapon weapon : currentWeapons) {
            sum += weapon.getCurrentHandsUsed();
        }
        return sum;
    }

    //switches a weapon's stance.
    public void switchStances(Weapon weapon, Stance newStance) {
        if (!canSwitchStances(weapon, newStance)) {
            System.out.println("Cannot Switch to this Stance.");
            return;
        }
        weapon.setCurrentStance(newStance);
        ap -= changeStance.getCost();
    }

    //returns TRUE if the player has enough AP and hands to switch to the given stance.
    public boolean canSwitchStances(Weapon weapon, Stance newStance) {

        if (hasWeapon(weapon)) {
            return false;
        }
        if (!weapon.hasStance(newStance)) {
            return false;
        }
        return newStance.getHands() > newlyAvailableHands(weapon);
    }

    //returns TRUE if the player has the given weapon, false otherwise.
    public boolean hasWeapon(Weapon weapon) {

        for (Weapon weapon1 : currentWeapons) {
            if (weapon.equals(weapon1)) {
                return true;
            }
        }
        return false;
    }


    //Formats and prints out the result of usableActions.
    public void printUsableActions() {

        for (Action action : getUsableActions()) {
            System.out.println(action);
        }

    }

    //Returns an Action[] array of all possible actions that the Creature can currently take.
    public Action[] getUsableActions() {

        ArrayList<Action> output = new ArrayList<>();

        for (Action action : baseActions) {
            if (action.canUse(ap, 0)) {
                output.add(action);
            }
        }
        for (Attack attack : getCurrentAttacks()) {
            if (attack.canUse(ap, getNextAttackDiscount())) {
                output.add(attack);
            }
        }

        return output.toArray(new Action[0]);

    }

    //Returns an Attack[] array of the attacks currently available though currentWeapons
    public Attack[] getCurrentAttacks() {

        ArrayList<Attack> output = new ArrayList<>();

        for (Weapon weapon : currentWeapons) {
            output.addAll(Arrays.asList(weapon.getCurrentAttacks()));
        }

        return output.toArray(new Attack[0]);

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
        resisted += universalRes;

        int damage = Math.max(0, (amount - resisted));

        if (tempHp >= damage) {
            tempHp -= damage;
            damage = 0;
        } else {
            damage -= tempHp;
            tempHp = 0;
        }

        hp -= damage;
        System.out.println(name + " took " + damage + " " + Main.damageTypes[type] + " damage.");

        updateWoundedStatus();
    }

    //a version of the damage method that doesn't factor in resistances.
    public void damageUnresistable(int amount, int type) {
        if (tempHp >= amount) {
            tempHp -= amount;
            amount = 0;
        } else {
            amount -= tempHp;
            tempHp = 0;
        }

        hp -= amount;
        System.out.println(name + " took " + amount + " unresistable " + Main.damageTypes[type] + " damage.");

        updateWoundedStatus();
    }

    //increases hp, updates isWounded/Mortal/Dying
    //has no effect on a dead creature.
    public void heal(int amount) {
        if (isDead) {
            return;
        }

        hp += amount;
        if (hp > maxHp) {
            hp = maxHp;
        }

        updateWoundedStatus();
    }

    //increases temp hp (thp)
    public void giveThp(int amount) {
        tempHp += amount;
    }

    //updates isWounded/Mortal/Dying/Dead
    public void updateWoundedStatus() {
        isWounded = (hp <= maxHp / 2);

        isMortal = (hp <= maxHp /4);

        if (hp <= 0) {
            if (!isDying) {
                System.out.println(name + " is dying.");
            }
            isDying = true;
        } else {
            if (isDying) {
                System.out.println(name + " was stabilized.");
            }
        }

        if (hp <= (-1 * (maxHp / 2))) {
            if (!isDead) {
                System.out.println(name + " died.");
            }
            isDead = true;
        }
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


    //adds a weapon to currentWeapons
    public void equipWeapon(Weapon weapon) {
        currentWeapons.add(weapon);
    }

    //removes a weapon from currentWeapons. Returns a boolean: true if successfully removed, false if failed to find.
    public boolean unequipWeapon(Weapon weapon) {

        int index = indexOfWeapon(weapon);
        if (index == -1) {
            return false;
        }

        currentWeapons.remove(index);
        return true;

    }



    //UTILITY METHODS

    public int rollD20(int modifier, int advantages, int disadvantages) {
        int maxRoll = -99;
        int minRoll = 99;
        int numRolls = 1 + Math.abs(advantages - disadvantages);

        for (int i = 0; i < numRolls; i++) {
            int roll = (int) (Math.random() * 20) + 1;
            if (roll > maxRoll) {
                maxRoll = roll;
            }
            if (roll < minRoll) {
                minRoll = roll;
            }
        }

        if (advantages > disadvantages) {
            return maxRoll + modifier;
        } else {
            return minRoll + modifier;
        }
    }

    //finds the first index of a given weapon in currentWeapons. Returns -1 if none found.
    public int indexOfWeapon(Weapon weapon) {

        for (int i = 0; i < currentWeapons.size(); i++) {
            if (weapon.equals(currentWeapons.get(i))) {
                return i;
            }
        }

        return -1;

    }

    //returns a string detailing HP values
    public String hpReport() {
        String output = name + " has " + hp + "/" + maxHp + " hit points";

        if (tempHp > 0) {
            output += " and " + tempHp + " temporary hit points. ";
        } else {
            output += ". ";
        }

        output += name + " is ";

        if (isDead) {
            output += "dead.";
        } else if (isDying) {
            output += "dying.";
        } else if (isMortal) {
            output += "mortally wounded.";
        } else if (isWounded) {
            output += "wounded.";
        } else {
            output += "healthy.";
        }
        return output;
    }

    //prints out the result of the hpReport method.
    public void printHpReport() {
        System.out.println(hpReport());
    }

    //returns a string detailing action point and energy point values.
    public String apReport() {
        return (name + " has " + ap + "/" + maxAp + " action points and " + ep + "/" + maxEp + " energy points.");
    }

    //prints apReport
    public void printApReport() {
        System.out.println(apReport());
    }

    //returns a string detailing weapons wielded.
    public String weaponReport() {
        String output = "";

        for (Weapon weapon : currentWeapons) {
            output += weapon;
            output += "\n";
        }

        return output;
    }

    public void printWeaponReport() {
        System.out.println(weaponReport());
    }

    public String toString() {
        StringBuilder output = new StringBuilder();

        output.append(name).append(". \n");
        output.append(hpReport()).append("\n");
        output.append(apReport()).append("\n");
        output.append(weaponReport());

        return output.toString();
    }

    //checks if a Skill in Skill[] has the name
    public boolean checkForSkill(Skill[] array, String name) {
        for (Skill skill : array) {
            if (skill.getName().toLowerCase().equals(name)) {
                return true;
            }
        }

        return false;
    }

    //returns the Skill in Skill[] with the same name
    public Skill getSkillWithName(Skill[] array, String name) {
        for (Skill skill : array) {
            if (skill.getName().toLowerCase().equals(name)) {
                return skill;
            }
        }

        return null;
    }


    //getter methods (booooring!)

    public String getName() {
        return name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp;
    }

    public int getTempHp() {
        return tempHp;
    }

    public int getMaxEp() {
        return maxEp;
    }

    public int getEp() {
        return ep;
    }

    public int getMaxAp() {
        return maxAp;
    }

    public int getAp() {
        return ap;
    }

    public int getHealthMod() {
        return healthMod;
    }

    public int getProficiency() {
        return proficiency;
    }

    public int getLevel() {
        return level;
    }

    public boolean isWounded() {
        return isWounded;
    }

    public boolean isMortal() {
        return isMortal;
    }

    public boolean isDying() {
        return isDying;
    }

    public boolean isDead() {
        return isDead;
    }

    public int[] getAbilities() {
        return abilities;
    }

    public boolean[] getSavingThrows() {
        return savingThrows;
    }

    public int getUniversalRes() {
        return universalRes;
    }

    public int getPhysicalRes() {
        return physicalRes;
    }

    public int getElementalRes() {
        return elementalRes;
    }

    public int getCorrosiveRes() {
        return corrosiveRes;
    }

    public int getArcaneRes() {
        return arcaneRes;
    }

    public ArrayList<Skill> getSkillProfs() {
        return skillProfs;
    }

    public ArrayList<Weapon> getCurrentWeapons() {
        return currentWeapons;
    }

    public Action[] getBaseActions() {
        return baseActions;
    }

}
