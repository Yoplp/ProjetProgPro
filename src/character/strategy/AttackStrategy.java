package character.strategy;


import character.Character;

public interface AttackStrategy {
    void attack(Character attacker, Character target);
}

