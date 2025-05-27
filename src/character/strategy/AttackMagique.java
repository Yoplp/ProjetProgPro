package character.strategy;

import character.Monster;

public class AttackMagique implements AttackStrategy {
    @Override
    public void attack(Monster cible) {
        System.out.println("Vous lancez un sort magique !");
        cible.takeDamage(5);
        System.out.println("Le "+ cible.getName()+"a pris 5 dégats");
    }
}

