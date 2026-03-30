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
    //automatically appends numbers to creatures with the same name.
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
        appendNumbersToNames();

    }



    //TURN RUNNING METHODS

    //Loops through combatants, and runs each combatant's turn in order.
    //Passes two arrays representing the creatures in the combat to the creature running its turn
    //These arrays are sorted as allies (same team) and enemies (opposite team).
    //Returns -1 if the villains won and +1 if the heroes won.
    public int runCombat() {

        int round = 0;

        while (whoWon() == 0) {
            round++;
            System.out.println("Round " + round);
            System.out.println(this);

            for (int i = 0; i < combatants.size(); i++) {
                Creature creature = combatants.get(i);

                System.out.println(creature.getName() + "'s Turn.");
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

                markDeadCreatures(); //marks dead creatures by adding them to toBeRemoved
                for (Creature deadCreature : toBeRemoved) { //decrements i to account for the arraylist shrinking
                    if (combatants.indexOf(deadCreature) <= i) {
                        i--;
                    }
                }
                removeDeadCreatures(); //removes dead creatures

                System.out.println();

                if (whoWon() != 0) {
                    break;
                }

            }

        }

        if (whoWon() == -1) {
            System.out.println("Villains won.");
        } else {
            System.out.println("Heroes won.");
        }

        return whoWon();

    }

    //Adds dead creatures to an ArrayList that stores enemies that need to be removed from initiative
    public void markDeadCreatures() {

        for (Creature creature : combatants) {

            if (!creature.isDead()) { //skips if the creature isn't. dead.
                continue;
            }

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

    //Appends a number to the end of every creature with the same name as at least one other creature
    public void appendNumbersToNames() {

        Creature[][] duplicateNames = getCreaturesWithSameNames();

        for (Creature[] creaturesWithName : duplicateNames) {

            int i = 1;
            for (Creature creature : creaturesWithName) {
                creature.appendName(i);
                i++;
            }

        }

    }

    //Returns an array of arrays of creatures with the same names in combatants.
    public Creature[][] getCreaturesWithSameNames() {

        ArrayList<ArrayList<Creature>> sameNameBuckets = new ArrayList<ArrayList<Creature>>();

        for (Creature creature : combatants) {
            boolean foundBucket = false;

            for (ArrayList<Creature> bucket : sameNameBuckets) {

                //If the creature has the same name as the creatures in this bucket, add the creature to this bucket
                //and then move on to the next creature
                if (bucket.get(0).getName().equals(creature.getName())) {
                    bucket.add(creature);
                    foundBucket = true;
                    break;
                }
            }

            if (foundBucket) {
                continue;
            }

            //if the creature didn't get put into any buckets, add a new bucket with this creature
            sameNameBuckets.add(new ArrayList<Creature>(Arrays.asList(new Creature[]{creature})));

        }

        //format the sameNameBuckets as a 2d array and returns
        Creature[][] output = new Creature[sameNameBuckets.size()][];

        for (int i = 0; i < output.length; i++) {
            output[i] = sameNameBuckets.get(i).toArray(new Creature[0]);
        }

        return output;

    }

    //prints out combatants in order, with their initiative rolls.
    public String toString() {

        String output = "";

        for (Creature creature : combatants) {

            output += creature.getName();

            if (teamAffiliation.get(creature) == 1) {
                output += " (Hero): ";
            } else if (teamAffiliation.get(creature) == -1) {
                output += " (Villain): ";
            } else {
                output += ": ";
            }

            int roll = initiativeRolls.get(creature).intValue();
            output += roll;

            output += "\n";

        }

        return output;

    }


}
