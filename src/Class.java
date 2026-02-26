public class Class {

    /*
    TODO: When Feature class is added, Class class will need some more stuff:
        - Array of all possible Aux features that can be taken
        - Array of all possible Class features that can be taken (sorted in order into separate arrays)
     */

    String name;
    int healthMod;
    int savingThrow;
    Skill[] skills;
    int numOfSkills;

    public Class(String name, int healthMod, int savingThrow, Skill[] skills, int numOfSkills) {
        this.name = name;
        this.healthMod = healthMod;
        this.savingThrow = savingThrow;
        this.skills = skills;
        this.numOfSkills = numOfSkills;
    }


    public String getName() {
        return name;
    }

    public int getHealthMod() {
        return healthMod;
    }

    public int getSavingThrow() {
        return savingThrow;
    }

    public Skill[] getSkills() {
        return skills;
    }

    public int getNumOfSkills() {
        return numOfSkills;
    }

}
