import java.util.*;

public class Combat {
    /*
    Combat Class needs:
        - An ArrayList that contains all creatures that are alive and actively participating in the combat
            - This ArrayList must be sorted in initiative order
        - Two ArrayLists that sort the above arrayList into two different sides
        - A method that lets a creature pick a target from the enemies' ArrayList
        - A loop that runs combat until one side is all dead
     */

    ArrayList<Creature> heroes = new ArrayList<>();
    ArrayList<Creature> villains = new ArrayList<>();
    ArrayList<Creature> combatants = new ArrayList<>();
    Map<Creature, Integer> teamAffiliation = new HashMap<>();
    Map<Creature, Double> initiativeRolls;

    int rounds = 0;


    //the only constructor.
    //asks every creature in the combat to roll initiative and then sorts combatants into initiative order.
    public Combat(Creature[] heroes, Creature[] villains) {

        this.heroes = new ArrayList<>(Arrays.asList(heroes));
        this.villains = new ArrayList<>(Arrays.asList(villains));

        combatants = this.heroes;
        combatants.addAll(this.villains);

        for(Creature creature : heroes) {
            teamAffiliation.put(creature, 1);
        }
        for(Creature creature : villains) {
            teamAffiliation.put(creature, -1);
        }

        initiativeRolls = promptInitiativeRolls();
        System.out.println(this);
        sortByInitiative();
    }



    //TURN RUNNING METHODS

    //Loops through combatants, and runs each combatant's turn in order.
    public void runCombat() {

        while (whoWon() == 0) {
            for (Creature creature : combatants) {
                creature.printHpReport();

                if (creature.isDead()) {
                    int team = teamAffiliation.get(creature);

                    if (team == -1) {
                        villains.remove(creature);
                    }
                    if (team == 1) {
                        heroes.remove(creature);
                    }
                    continue;
                }

                creature.runTurn();

            }
        }

    }

    //Checks if either side has won. Returns -1 if villains have won, 1 if heroes have won, and 0 if combat is ongoing
    //One side wins when all heroes are dead, or when all villains are dead.
    //When a creature dies, they are removed from the arraylists, so this checks if either arraylist is empty.
    public int whoWon() {

        if (villains.isEmpty()) {
            return 1;
        }
        if (heroes.isEmpty()) {
            return -1;
        }
        return 0;

    }


    //INITIATIVE ROLLING/SORTING METHODS


    //Prompts each Creature in Combatants to roll initiative, then creates a dictionary.
    //Dictionary takes Creatures as keys, and returns their initiative rolls.
    //This dictionary is then returned.
    public Map<Creature, Double> promptInitiativeRolls() {
        Map<Creature, Double> output = new HashMap<>();

        for (Creature creature : combatants) {
            double roll = creature.rollInitiative();
            output.put(creature, roll);
        }

        return output;
    }

    //Sorts combatants by their initiative rolls (descending).
    public void sortByInitiative() {

        //this uses bubble sort :P
        while (!combatantsIsSorted()) {
            for (int i = 0; i < combatants.size() - 1; i++) {
                Double rollOne = initiativeRolls.get(combatants.get(i));
                Double rollTwo = initiativeRolls.get(combatants.get(i + 1));

                if (Double.compare(rollTwo, rollOne) == 1) {
                    Collections.swap(combatants, i, i + 1);
                }
            }
        }

    }

    //Checks if combatants is sorted by initiative rolls (descending). Returns true if it is, false if it's not.
    public boolean combatantsIsSorted() {

        double lowestRoll = initiativeRolls.get(combatants.get(0));

        for (Creature creature : combatants) {
            double currentRoll = initiativeRolls.get(creature);

            if (Double.compare(currentRoll, lowestRoll) == 1) {
                return false;
            }

            lowestRoll = currentRoll;

        }

        return true;
    }

    //prints out combatants in order, with their initiative rolls.
    public String toString() {

        String output = "";

        for (Creature creature : combatants) {

            output += creature.getName() + ": ";

            int roll = initiativeRolls.get(creature).intValue();
            output += roll;

            output += "\n";

        }

        return output;

    }


}
