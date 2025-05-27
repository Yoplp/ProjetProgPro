package character.strategy;

import character.Monster;

public class AttackMagique implements AttackStrategy {
    @Override
    public void attack( Monster monster) {
        if (monster.isWeakToMagic()) {
            int damage = 5;
            System.out.println(monster.getName() + " est faible à la magie !");
            monster.takeDamage(damage);
        } else {
            System.out.println("La magie n'affecte pas " + monster.getName() + " !");
        }
    }
}

