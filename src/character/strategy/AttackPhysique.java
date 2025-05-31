package character.strategy;


import character.Character;
import character.Type;

public class AttackPhysique implements AttackStrategy {
    @Override
    public void attack(Character attacker, Character target) {
        System.out.println(attacker.getName() + " attaque " + target.getName());
        if (target.getType() == Type.PHYSICAL) {
            System.out.println(target.getName() + " est faible aux attaques physiques !");
            target.takeDamage(attacker.getAttack() * 2);
        } else {
            target.takeDamage(attacker.getAttack());
        }
    }
}

