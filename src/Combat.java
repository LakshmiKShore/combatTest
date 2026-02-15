import java.util.*;

public class Combat {

    ArrayList<Creature> heroes = new ArrayList<>();
    ArrayList<Creature> villains = new ArrayList<>();
    ArrayList<Creature> combatants = new ArrayList<>();
    ArrayList<Creature> toBeRemoved = new ArrayList<>();
    Map<Creature, Integer> teamAffiliation = new HashMap<>();
    Map<Creature, Double> initiativeRolls;

    int rounds = 0;


    //the only constructor.
    //asks every creature in the combat to roll initiative and then sorts combatants into initiative order.
    public Combat(Creature[] heroes, Creature[] villains) {

        this.heroes = new ArrayList<>(Arrays.asList(heroes));
        this.villains = new ArrayList<>(Arrays.asList(villains));

        Collections.addAll(combatants, heroes);
        Collections.addAll(combatants, villains);

        for(Creature creature : heroes) {
            teamAffiliation.put(creature, 1);
        }
        for(Creature creature : villains) {
            teamAffiliation.put(creature, -1);
        }

        initiativeRolls = promptInitiativeRolls();
        sortByInitiative();

    }



    //TURN RUNNING METHODS

    //Loops through combatants, and runs each combatant's turn in order.
    //Passes two arrays representing the creatures in the combat to the creature running its turn
    //These arrays are sorted as allies (same team) and enemies (opposite team).
    public void runCombat() {

        int round = 0;

        while (whoWon() == 0) {
            round++;
            System.out.println("Round " + round);
            System.out.println(this);

            for (Creature creature : combatants) {
                System.out.println(creature.getName() + "'s Turn.");
                creature.printHpReport();
                int team = teamAffiliation.get(creature);

                Creature[] allies = new Creature[0];
                Creature[] enemies = new Creature[0];

                if (team == -1) {
                    allies = villains.toArray(allies);
                    enemies = heroes.toArray(enemies);
                }
                if (team == 1) {
                    allies = heroes.toArray(allies);
                    enemies = villains.toArray(enemies);
                }

                creature.runTurn(allies, enemies);

                markDeadCreatures(); //marks dead creatures by adding them to toBeRemoved, also removes them as targets
                System.out.println();

                if (whoWon() != 0) {
                    break;
                }

            }

            removeDeadCreatures();

        }

    }

    //Adds dead creatures to an ArrayList that stores enemies that need to be removed from initiative
    //This also removes dead creatures from the villains and heroes list, which prevents them from being targeted
    public void markDeadCreatures() {

        for (Creature creature : combatants) {

            if (!creature.isDead()) { //skips if the creature isn't. dead.
                continue;
            }

            villains.remove(creature);
            heroes.remove(creature);
            toBeRemoved.add(creature);

        }

    }

    //Removes creatures marked by markDeadCreatures from initiative, then clears the list of marked creatures.
    public void removeDeadCreatures() {

        for (Creature creature : toBeRemoved) {
            villains.remove(creature);
            heroes.remove(creature);
            combatants.remove(creature);
        }

        toBeRemoved.clear();

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
