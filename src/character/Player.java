package character;

import character.strategy.AttackMagique;
import character.strategy.AttackPhysique;
import character.strategy.AttackStrategy;
import item.Inventory;
import map.Direction;
import map.Room;

public class Player extends Character {
    public static final int PLAYER_MAX_HEALTH = 10;
    private Room currentRoom;
    private Inventory inventory;

    public Player(String name, int attack, Room currentRoom) {
        super(name, attack, PLAYER_MAX_HEALTH, Type.NORMAL);
        this.currentRoom = currentRoom;
        this.inventory = new Inventory();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean move(Direction direction) {
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom != null) {
            currentRoom = nextRoom;
            System.out.println("Vous vous déplacez vers " + direction);
            return true;
        }
        System.out.println("Impossible de se déplacer vers " + direction);
        return false;
    }

    @Override
    public void performAttack(Character target) {
        if (!selectAttackStrategy()) {
            System.out.println("Vous n'avez pas d'arme pour attaquer !");
            return;
        }
        super.performAttack(target);
    }

    private boolean selectAttackStrategy() {
        if (inventory.getItem("Baguette magique") != null) {
            setAttackStrategy(new AttackMagique());
            return true;
        } else if (inventory.getItem("Épée") != null) {
            setAttackStrategy(new AttackPhysique());
            return true;
        }
        return false;
    }
}
