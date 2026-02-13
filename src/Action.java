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

    private String name;
    private String description;
    private int cost;

    //custom constructor
    public Action(String name, String description, int cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }


    //checks if the user has enough AP to use this action. returns true if they have enough, false otherwise
    public boolean canUse(int userAP){
        return userAP >= cost;
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