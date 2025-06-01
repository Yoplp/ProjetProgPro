package character;

import character.strategy.AttackStrategy;

public class Character {
    protected String name;
    protected int attack;
    protected int health;
    protected AttackStrategy attackStrategy;
    protected Type type;

    public Character(String name, int attack, int health, Type type) {
        this.name = name;
        this.attack = attack;
        this.health = health;
        this.type = type;
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

    public Type getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setAttackStrategy(AttackStrategy strategy) {
        this.attackStrategy = strategy;
    }

    public boolean isDead() {
        return health <= 0;
    }
    
    public void heal(int amount) {
        this.health += amount; 
    }

    public void performAttack(Character target) {
        if (attackStrategy != null) {
            attackStrategy.attack(this, target);
        }
    }
    
    public void takeDamage(int damage) {
        this.health -= damage;
        System.out.println(name + " a subi " + damage + " dégâts. PV restants : " + health);
        if (this.isDead()) {
            this.health = 0;
            System.out.println(name + " est vaincu !");
        }
    }
}
