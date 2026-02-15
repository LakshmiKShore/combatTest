public class Action {
    /*
        TODO:
            - Create Child Classes for different types of actions
            - Create an implementation for the switch stances action
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