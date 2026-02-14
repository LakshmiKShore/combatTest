public class Skill {

    static final int str = 0;
    static final int dex = 1;
    static final int con = 2;
    static final int know = 3;
    static final int wit = 4;
    static final int will = 5;

    static final String[] abilities = {"Strength", "Dexterity", "Constitution", "Knowledge", "Wit", "Willpower"};

    String name;
    int ability;


    public Skill(String name, int baseAbility){
        this.name = name;
        this.ability = baseAbility;
    }

    //toString method, prints name and base ability
    public String toString() {
        return name + " (" + abilities[ability] + ")";
    }

    //getters
    public String getName() {
        return name;
    }

    public int getAbility() {
        return ability;
    }

}
