public class Class {

    /*
    TODO: When Feature class is added, Class class will need some more stuff:
        - Array of all possible Aux features that can be taken
        - Array of all possible Class features that can be taken (sorted in order into separate arrays)
     */

    String name;
    int healthMod;

    public Class(String name, int healthMod) {
        this.name = name;
        this.healthMod = healthMod;
    }


    public String getName() {
        return name;
    }

    public int getHealthMod() {
        return healthMod;
    }

}
