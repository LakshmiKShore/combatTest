public class Action {
    /*
    Action class needs:
        - Instance Variables
            - Name
            - Description
            - Action Point Cost
        - Child Classes
            - Attack
            - Elementalist Spell
            - Hemomancer Spell
        - Methods
            - Check if valid
            - Use
     */

    protected String name;
    protected String description;
    protected int cost;

    //custom constructor
    public Action(String name, String description, int cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }


    //checks if the user has enough AP to use this action. returns true if they have enough, false otherwise
    public boolean canUse(int userAP, int discount){
        return userAP >= (cost - discount);
    }


    //toString, prints name and description
    public String toString() {
        return (name + ". " + description);
    }

    //getter methods
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }
}