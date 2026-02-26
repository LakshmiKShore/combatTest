import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Player extends Creature {

    /*
        player class needs:
            - Character Creation constructor
            - Override for turnBehavior to get player input
            - Override for parryBehavior to get player input
     */

    Scanner scanner;

    Class champion = new Class("Champion", 3);
    Class warrior = new Class("Warrior", 3);
    Class skirmisher = new Class("Skirmisher", 2);
    Class duelist = new Class("Duelist", 2);
    Class elementalist = new Class("Elementalist", 1);
    Class hemomancer = new Class("Hemomancer", 1);

    Class[] classes = {champion, warrior, skirmisher, duelist, elementalist, hemomancer};
    Class playerClass;

    int abilityPoints = 4;


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
            String inputClass = scanner.next().toLowerCase();
            boolean done = false;

            for (Class c : classes) {
                if (inputClass.equals(c.getName().toLowerCase())) {
                    playerClass = c;
                    healthMod = c.getHealthMod();
                    done = true;
                }
            }

            if (done) {
                break;
            }
        }




    }

    //runs the user through the process of choosing statistics.
    public void buyStatistics() {

        while (getSumOfAbilityScores() != abilityPoints) {

            for (int i = 0; i < abilities.length; i++) {
                System.out.println("What do you want your " + Skill.abilities[i] + " score to be?");

                int input = scanner.nextInt();
                abilities[i] = input;

            }

            printAbilityScores();
            int abilityPointDifference =

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
            System.out.println(Skill.abilities[i] + ": " + abilities[i]);
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
