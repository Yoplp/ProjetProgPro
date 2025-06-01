package character.strategy;


import character.Character;
import character.Type;

public class AttackPhysique implements AttackStrategy {
	@Override
	public void attack(Character attacker, Character target) {
	    System.out.println(attacker.getName() + " attaque " + target.getName() + " avec une attaque PHYSIQUE");
	    if (target.getType() == Type.PHYSICAL) {
	        System.out.println("C'est super efficace !");
	        target.takeDamage(attacker.getAttack());
	    } else {
	        System.out.println("L'attaque n'a aucun effet...");
	    }
	}
}

