public class Action {
    /*
        TODO:
            - Create Child Classes for different types of actions
     */

    protected String name;
    protected String description;
    protected int cost;
    protected int type;

    public static final int base = 0;
    public static final int reaction = 1;
    public static final int attack = 2;

    //custom constructor
    public Action(String name, String description, int cost, int type) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.type = type;
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
    public int getType() {
        return type;
    }

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