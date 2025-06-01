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
    private AttackStrategy attackStrategy;
    private int gold;


    public Player(String name, int attack, Room currentRoom) {
        super(name, attack, PLAYER_MAX_HEALTH, Type.NORMAL);
        this.currentRoom = currentRoom;
        this.inventory = new Inventory();
        this.gold = 5;
    }
    
    public int getGold() {
        return gold;
    }
    @Override
    public void heal(int amount) {
        this.health = Math.min(this.health + amount, PLAYER_MAX_HEALTH);
    }
    
    public void healToFull() {
        this.health = PLAYER_MAX_HEALTH;
    }

    public void setGold(int gold) {
        this.gold = gold;
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

    public void setAttackStrategy(AttackStrategy strategy) {
        this.attackStrategy = strategy;
    }

    public void attack(Character target) {
        if (attackStrategy != null) {
            attackStrategy.attack(this, target);
        } else {
            System.out.println("Aucune stratégie d'attaque définie !");
        }
    }

}
