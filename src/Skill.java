public class Skill {

    final int str = 0;
    final int dex = 1;
    final int con = 2;
    final int know = 3;
    final int wit = 4;
    final int will = 5;

    String name;
    int baseAbility;

    public Skill(String name, int baseAbility){
        this.name = name;
        this.baseAbility = baseAbility;
    }

}
