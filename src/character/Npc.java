package character;

import item.Inventory;

public class Npc extends Character {
    private String message;
    private Inventory inventory;

    public Npc(String name, int attack, int health, String message) {
        super(name, attack, health);
        this.message = message;
    }
}