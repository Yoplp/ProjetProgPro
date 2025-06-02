package character.strategy;

import character.Character;
import character.Monster;
import character.Type;

public class AttackMagique implements AttackStrategy {
	@Override
	public void attack(Character attacker, Character target) {
	    System.out.println(attacker.getName() + " attaque " + target.getName() + " avec une attaque MAGIQUE");
	    if (target.getType() != Type.PHYSICAL) {
	        System.out.println("C'est super efficace !");
	        target.takeDamage(attacker.getAttack());
	    } else {
	        System.out.println("L'attaque n'a aucun effet...");
	    }
	}
}

