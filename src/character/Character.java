package character;

public class Character {
    protected String name;
    protected int attack;
    protected int health;

    public Character(String name, int attack, int health) {
        this.name = name;
        this.attack = attack;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
