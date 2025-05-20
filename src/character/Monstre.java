package character;

public class Monstre extends Character {

    public Monstre(String name,  int attack, int health) {
        super(name, health, attack);
    }

    public void attaquer() {
        System.out.println(name + " vous attaque !");
    }
}
