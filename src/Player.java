import java.util.*;

public class Player extends Creature {

    /*
        player class needs:
            - Character Creation constructor
            - Override for turnBehavior to get player input
            - Override for parryBehavior to get player input
     */

    Scanner scanner;

    Class champion = new Class("Champion", 3, Player.fortitudeSave,
            new Skill[] {athletics, exertion, agility, medicine, persuasion, intimidation, inspiration}, 3
    );
    Class warrior = new Class("Warrior", 3, Player.fortitudeSave,
            new Skill[] {athletics, exertion, agility, recall, biology, persuasion, foraging, intimidation, inspiration}, 3
    );
    Class skirmisher = new Class("Skirmisher", 2, Player.reflexSave,
            new Skill[] {slightOfHand, lockpicking, stealth, agility, biology, medicine, deception, persuasion, perception, foraging}, 4
    );
    Class duelist = new Class("Duelist", 2, Player.reflexSave,
            new Skill[] {exertion, slightOfHand, agility, medicine, deception, persuasion, perception, insight, magic}, 3
    );
    Class elementalist = new Class("Elementalist", 1, Player.willSave,
            new Skill[] {stealth, agility, recall, biology, arcana, medicine, insight, magic}, 3
    );
    Class hemomancer = new Class("Hemomancer", 1, Player.willSave,
            new Skill[] {stealth, agility, recall, biology, arcana, medicine, insight, magic}, 3
    );

    Class[] classes = {champion, warrior, skirmisher, duelist, elementalist, hemomancer};
    Class playerClass;

    int abilityPoints = 4;
    int maxAbilityScoreValue = 4;


    //super constructors
    public Player(String name, int level, int healthMod, int str, int dex, int con, int know, int wit, int will, boolean hasReflexSaves, boolean hasFortSaves, boolean hasWillSaves, Skill[] skillProfs, Weapon[] currentWeapons) {
        super(name, level, healthMod, str, dex, con, know, wit, will, hasReflexSaves, hasFortSaves, hasWillSaves, skillProfs, currentWeapons);
        scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
    }

    public Player(String name, int level, int healthMod, int str, int dex, int con, int know, int wit, int will, boolean hasReflexSaves, boolean hasFortSaves, boolean hasWillSaves, int proficiency, int maxAp, int maxEp, int maxHp, int universalRes, int physicalRes, int elementalRes, int corrosiveRes, int arcaneRes, int maxHands, Skill[] skillProfs, Weapon[] currentWeapons) {
        super(name, level, healthMod, str, dex, con, know, wit, will, hasReflexSaves, hasFortSaves, hasWillSaves, proficiency, maxAp, maxEp, maxHp, universalRes, physicalRes, elementalRes, corrosiveRes, arcaneRes, maxHands, skillProfs, currentWeapons);
        scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
    }


    //Character Creation constructor. Allows the user to customize their character.
    public Player() {
        scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        level = 1;
        savingThrows[reflexSave] = false;
        savingThrows[fortitudeSave] = false;
        savingThrows[willSave] = false;
        proficiency = 1;
        maxAp = 7;
        maxHands = 2;

        System.out.println("What... is your name?");
        name = scanner.next();

        while (true) {
            System.out.println("What... is your class?");
            String inputClass = scanner.next().toLowerCase();
            boolean done = false;

            for (Class c : classes) {
                if (inputClass.equals(c.getName().toLowerCase())) {
                    playerClass = c;
                    healthMod = c.getHealthMod();
                    savingThrows[c.getSavingThrow()] = true;
                    done = true;
                }
            }

            if (done) {
                break;
            } else {
                System.out.println("Invalid Class.");
            }
        }

        buyStatistics(2);

        chooseSkills(playerClass.getSkills(), playerClass.getNumOfSkills());
        chooseSkills(allSkills, abilities[know]);
        System.out.println(skillProfs);

        chooseWeapons();

        maxEp = level + (2 * Math.max(con, 0)) + (2 * Math.max(will, 0));
        ep = maxEp;

        maxHp = 4 + (level * healthMod) + (Math.max(con, 0) * proficiency * 2);
        hp = maxHp;
        tempHp = 0;

    }

    //runs the user through the process of choosing statistics.
    public void buyStatistics(int maxValue) {
        boolean doneScoring = false;

        while (!doneScoring) {
            resetAbilityScores();
            System.out.println("You have " + abilityPoints + " ability points.");

            for (int i = 0; i < abilities.length; i++) {
                System.out.println("What do you want your " + Skill.abilityNames[i] + " score to be?");

                int input = scanner.nextInt();

                if (-1 <= input && input <= maxValue) {
                    abilities[i] = input;
                    System.out.println(Skill.abilityNames[i] + ": " + input);
                } else {
                    System.out.println("Must be between -1 and " + maxValue + ".");
                    i--;
                }

                System.out.println("You have " + (abilityPoints - getSumOfAbilityScores()) + " ability points remaining.");
            }

            System.out.println();
            printAbilityScores();
            int abilityPointDifference = abilityPoints - getSumOfAbilityScores();

            if (abilityPointDifference == 0) {
                System.out.println("Are you happy with these scores?");
                while(true) {
                    String input = scanner.next().toLowerCase();
                    if (input.equals("yes") || input.equals("y")) {
                        doneScoring = true;
                        break;
                    } else
                    if (input.equals("no") || input.equals("n")) {
                        break;
                    }
                }
            } else if (abilityPointDifference > 0) {
                System.out.println("You have " + abilityPointDifference + " points remaining.");
            } else {
                System.out.println("You spent " + (-1 * abilityPointDifference) + " too many points.");
            }
        }

    }

    //resets the Player's ability scores to zero.
    public void resetAbilityScores() {
        abilities = new int[abilities.length];
    }

    //runs the user through the process of choosing which skills to have proficiency in.
    public void chooseSkills(Skill[] skillArray, int numOfSkills) {

        ArrayList<Skill> potentialSkills = new ArrayList<>(Arrays.asList(skillArray));
        potentialSkills.removeAll(skillProfs);

        for (int i = 0; i < numOfSkills; i++) {

            System.out.println("Choose " + (numOfSkills - i) + " more skills.");
            System.out.println(potentialSkills);
            String input = scanner.next().toLowerCase();

            if (!checkForSkill(potentialSkills.toArray(new Skill[0]), input)) {
                System.out.println("chekc your spelling");
                i--;
                continue;
            }
            if (checkForSkill(skillProfs.toArray(new Skill[0]), input)) {
                System.out.println("You already have that skill.");
                i--;
                continue;
            }

            Skill chosenSkill = getSkillWithName(potentialSkills.toArray(skillArray), input);

            System.out.println(chosenSkill);
            skillProfs.add(chosenSkill);
            potentialSkills.remove(chosenSkill);

        }

    }

    //prompts the player to choose their starting weapons
    public void chooseWeapons() {
        System.out.println("Choose your weapons.");
        System.out.println(Arrays.toString(Adventure.basicWeapons));

        while (handsInUse() < maxHands) {
            System.out.println("You have " + (maxHands - handsInUse()) + " free hand(s) remaining. What weapon would you like to use? (enter \"none\" for none.)");
            String input = scanner.next().toLowerCase();
            Weapon toAdd = null;

            if (input.equals("none")) {
                break;
            }

            for (Weapon weapon : Adventure.basicWeapons) {
                if (weapon.getName().toLowerCase().equals(input) && weapon.getMinHandsStance().getHands() <= (maxHands - handsInUse()) ) {
                    System.out.println(weapon);
                    toAdd = weapon;
                }
            }

            if (toAdd == null) {
                continue;
            }

            toAdd.setCurrentStance(toAdd.getMinHandsStance());
            currentWeapons.add(toAdd);

            System.out.println(currentWeapons);
        }

    }


    //LEVELING METHODS

    //initiates the leveling process.
    //For now, since features have not been implemented, levels where you would gain a new feature are commented out.
    @SuppressWarnings("DuplicateBranchesInSwitch")
    public void levelUp() {

        level++;
        System.out.println("New Level: " + level);

        switch (level) {
            case 1:
                //gainClassFeature(1);
                System.out.println("gain class feature");
                break;
            case 2:
                //gainAuxFeature();
                System.out.println("gain aux feature");
                break;
            case 3:
                abilityScoreIncrease();
                break;
            case 4:
                //gainClassFeature(2);
                System.out.println("gain class feature");
                break;
            case 5:
                //gainAuxFeature();
                System.out.println("gain aux feature");
                break;
            case 6:
                proficiency++;
                System.out.println("proficiency bonus increase");
                break;
            case 7:
                //gainClassFeature(3);
                System.out.println("gain class feature");
                break;
            case 8:
                abilityScoreIncrease();
                break;
            case 9:
                //gainAuxFeature();
                System.out.println("gain aux feature");
                break;
            case 10:
                //gainClassFeature(4);
                System.out.println("gain class feature");
                break;
            case 11:
                proficiency++;
                System.out.println("proficiency bonus increase");
            case 12:
                //gainClassFeature(5);
                System.out.println("gain class feature");
                break;
            case 13:
                //gainAuxFeature();
                System.out.println("gain aux feature");
                break;
            case 14:
                abilityScoreIncrease();
                break;
            case 15:
                //gainClassFeature(6);
                System.out.println("gain class feature");
                break;
            case 16:
                //gainLegendaryFeature();
                System.out.println("gain legendary feature");
                break;
        }

    }

    //allows the user to add one point to an ability score of their choice.
    public void abilityScoreIncrease() {
        while (true) {
            printAbilityScores();
            System.out.println("Which ability score would you like to increase?");

            String input = scanner.next().toLowerCase();

            for (int i = 0; i < Skill.abilityNames.length; i++) {
                String abilityName = Skill.abilityNames[i].toLowerCase();

                if (!input.equals(abilityName)) {
                    continue;
                }
                if (abilities[i] >= maxAbilityScoreValue) {
                    System.out.println("Cannot increase. Maximum value is " + maxAbilityScoreValue + ".");
                    continue;
                }

                abilities[i]++;

                if (i == Creature.know) {
                    chooseSkills(Creature.allSkills, 1);
                }

                return;
            }
        }
    }





    //Prompts the player to choose what to do on their turn.
    public boolean turnBehavior(Creature[] allies, Creature[] enemies) {

        Action[] actions = getUsableActions();
        printApReport();
        System.out.println("What should " + name + " do?");

        while (true) {
            String input = scanner.next().toLowerCase();

            //special input cases.

            //"help" prints out possible actions.
            if (input.equals("help")) {
                System.out.println("Help. Prints all usable actions.");
                System.out.println("Quit. Ends your turn.");
                System.out.println("Look. Shows the status of an ally or enemy.");
                printUsableActions();
                continue;
            }

            //"end turn" and "quit" return FALSE, ending the turn loop.
            if (input.equals("quit") || input.equals("end turn")) {
                return false;
            }

            //"look" prompts a target out of all combatants, then prints that target.
            if (input.equals("look")) {

                ArrayList<Creature> combatants = new ArrayList<>();

                Collections.addAll(combatants, allies);
                Collections.addAll(combatants, enemies);

                System.out.println(getTarget(combatants.toArray(new Creature[0])));

                continue;
            }


            //Actions.

            //checks the input against the names of all possible actions.
            boolean validAction = false;
            Action chosenAction = null;
            for (Action action : actions) {

                if (input.equals(action.getName().toLowerCase())) {
                    chosenAction = action;
                    validAction = true;
                }

            }
            if (!validAction) {
                System.out.println("Invalid Action.");
                continue;
            }


            //special action cases.

            //change stance: call the chooseWeapon method and the chooseStanceChange method
            if (chosenAction.equals(changeStance)) {
                Weapon chosenWeapon = chooseWeapon();
                Stance chosenStance = chooseStance(chosenWeapon);

                chosenWeapon.setCurrentStance(chosenStance);

                ap -= changeStance.getCost();

                return true;
            }

            //Attacks: call chooseTarget then attack that target
            if (chosenAction.getType() == Action.attack) {

                Creature target = getTarget(enemies);
                attack((Attack) chosenAction, weaponToUse((Attack) chosenAction), target);
                return true;

            }



        }

    }

    //prompts user to choose a weapon for something
    public Weapon chooseWeapon() { //TODO: this doesn't work for dual wielding weapons with the same name.
                                   //might be able to get away with it if i just don't have any one handed weapons with stances?
        System.out.println("Which weapon?");

        while (true) {
            String input = scanner.next().toLowerCase();

            for (Weapon weapon : currentWeapons) {
                if (input.equals(weapon.getName().toLowerCase())) {
                    return weapon;
                }

                if (input.equals("help")) {
                    System.out.println(currentWeapons);
                }
            }
        }
    }

    //Prompts user which stance they would like to switch to
    public Stance chooseStance(Weapon weapon) {
        System.out.println("To what stance?");

        while (true) {
            String input = scanner.next().toLowerCase();

            for (Stance stance : weapon.getStances()) {
                if (input.equals(stance.getName().toLowerCase())) {
                    return stance;
                }

                if (input.equals("help")) {
                    System.out.println(Arrays.toString(weapon.getStances()));
                }
            }
        }

    }


    //prompts user if they want to parry an incoming attack.
    public boolean parryBehavior(int diceType, int diceNumber, int parryCost) {

        if (!parry.canUse(ap, 0)) {
            return false;
        }

        System.out.println(name + " is being attacked for " + diceNumber + "d" + diceType + " damage. It will cost " + parryCost + " AP to parry. Should " + name + " parry?");
        while (true) {
            String input = scanner.next().toLowerCase();
            if (input.equals("yes") || input.equals("y")) {
                return true;
            }
            if (input.equals("no") || input.equals("n")) {
                return false;
            }
        }

    }


    //prompts user to choose a target from an array
    public Creature getTarget(Creature[] possibleTargets) {
        System.out.println("Targeting?");
        Creature target = null;

        while (true) {

            String input = scanner.next().toLowerCase();

            for (Creature creature : possibleTargets) {
                if (input.equals(creature.getName().toLowerCase())) {
                    target = creature;

                    return target;
                }

                if (input.equals("help")) {
                    System.out.println(Arrays.toString(possibleTargets));
                }
            }

            System.out.println("Invalid Target.");

        }

    }


    public void printAbilityScores() {
        for (int i = 0; i < abilities.length; i++) {
            System.out.println(Skill.abilityNames[i] + ": " + abilities[i]);
        }
    }

    public int getSumOfAbilityScores() {
        int sum = 0;

        for (int x : abilities) {
            sum += x;
        }
        return sum;
    }

}
