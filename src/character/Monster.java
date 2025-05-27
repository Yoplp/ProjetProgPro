package character;

public class Monster extends Character {

    public Monster(String name,  int attack, int health) {
        super(name, health, attack);
    }

    public void attaquer() {
        System.out.println(name + " vous attaque !");
    }
}
