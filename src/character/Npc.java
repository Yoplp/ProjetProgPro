package character;

import java.util.Scanner;

import character.state.NpcState;
import item.Inventory;
import item.Item;

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
    
    public void sellItemsTo(Player player) {
    	
        if (inventory == null || inventory.getItems().isEmpty()) {
            System.out.println("Ce PNJ n'a rien à vendre.");
            return;
        }

        System.out.println("Voici les objets en vente :");
        for (Item item : inventory.getItems()) {
            System.out.println("- " + item.getName() + " (5 pièces)");
        }

        System.out.println("Quel objet voulez-vous acheter ?");
        Scanner scanner = new Scanner(System.in);
        String choix = scanner.nextLine().trim();

        Item item = inventory.getItem(choix);
        if (item != null) {
            if (player.getGold() >= 5) {
                player.setGold(player.getGold() - 5);
                player.getInventory().addItem(item);
                System.out.println("Vous avez acheté : " + item.getName());
            } else {
                System.out.println("Vous n'avez pas assez de pièces.");
            }
        } else {
            System.out.println("Objet non trouvé.");
        }
    }
}