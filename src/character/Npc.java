package character;

import character.state.NpcState;
import item.Inventory;

public class Npc extends Character {
    private String message;
    private Inventory inventory;
    private NpcState state;

    public Npc(String name, int attack, int health, String message) {
        super(name, attack, health, Type.NORMAL);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public NpcState getState() {
        return state;
    }

    public void setState(NpcState state) {
        this.state = state;
    }

    public void interact(Player player) {
        state.interact(this, player);
    }
}