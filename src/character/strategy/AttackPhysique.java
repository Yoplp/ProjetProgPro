package character.strategy;


import character.Monster;

public class AttackPhysique implements AttackStrategy {
    @Override
    public void attack( Monster monster) {
        if (monster.isWeakToPhysical()) {
            int damage = 5;
            System.out.println(monster.getName() + " est faible aux attaques physiques !");
            monster.takeDamage(damage);
        } else {
            System.out.println("L'attaque physique n'affecte pas " + monster.getName() + " !");
        }
    }
}

