package character;

import character.strategy.AttackStrategy;
import item.Inventory;
import map.Direction;
import map.Room;

public class Player extends Character {
    public static final int PLAYER_MAX_HEALTH = 10;
    public static final int DROPPED_GOLD = 10;
    public static final int STARTING_GOLD = 5;
    private Room currentRoom;
    private Inventory inventory;
    private AttackStrategy attackStrategy;
    private int gold;


    public Player(String name, int attack, Room currentRoom) {
        super(name, attack, PLAYER_MAX_HEALTH, Type.NORMAL);
        this.currentRoom = currentRoom;
        this.inventory = new Inventory();
        this.gold = STARTING_GOLD;
    }
    
    public int getGold() {
        return gold;
    }

    public void addGold(int gold) {
        this.gold += gold;
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

    public boolean moveTo(String input) {
        Direction direction = Direction.parse(input);
        if (direction == null) {
            System.out.println("Direction invalide.\n");
            return false;
        }

        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("Impossible d'aller dans cette direction.\n");
            return false;
        }

        if (currentRoom.getName().equalsIgnoreCase("Porte") &&
                nextRoom.getName().equalsIgnoreCase("Boss")) {

            if (inventory.getItem("Clé") == null) {
                System.out.println("La porte est verrouillée, vous avez besoin d'une clé pour entrer.\n");
                return false;
            }
            System.out.println("Vous utilisez la clé pour ouvrir la porte.\n");
        }

        currentRoom = nextRoom;
        System.out.println("Vous vous déplacez vers le " + direction.getTrad() + "\n");
        return true;
    }

    public void setAttackStrategy(AttackStrategy strategy) {
        this.attackStrategy = strategy;
    }

    @Override
    public void performAttack(Character target) {
        if (attackStrategy != null) {
            attackStrategy.attack(this, target);
            if (target.isDead()) {
                healToFull();
                System.out.println("Vous êtes soigné, votre vie est restaurée !");
                addGold(DROPPED_GOLD);
                System.out.println("Vous êtes soigné, vous avez actuellement " + gold + " pièces d'or !");
            }
        } else {
            System.out.println("Aucune stratégie d'attaque définie !\n");
        }
    }
}
