package character.strategy;


import character.Monster;

public class AttaquePhysique implements AttackStrategy {
    @Override
    public void attaquer(Monster cible) {
        System.out.println("Vous attaquez physiquement !");
        cible.takeDamage(5);
        System.out.println("Le "+ cible.getName()+"a pris 5 dégats");
    }
}

