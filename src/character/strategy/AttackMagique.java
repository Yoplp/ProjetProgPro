package character.strategy;

import character.Character;
import character.Monster;
import character.Type;

public class AttackMagique implements AttackStrategy {
    @Override
    public void attack(Character attacker, Character target) {
        System.out.println(attacker.getName() + " attaque " + target.getName());
        if (target.getType() == Type.MAGIC) {
            System.out.println(target.getName() + " est faible aux attaques magiques !");
            target.takeDamage(attacker.getAttack() * 2);
        } else {
            target.takeDamage(attacker.getAttack());
        }
    }
}

