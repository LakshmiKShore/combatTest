public class Skill {

    static final int str = 0;
    static final int dex = 1;
    static final int con = 2;
    static final int know = 3;
    static final int wit = 4;
    static final int will = 5;

    String name;
    int baseAbility;

    public Skill(String name, int baseAbility){
        this.name = name;
        this.baseAbility = baseAbility;
    }

}
