package item;

import character.Player;

public class Item {
    private String name;
    private ItemType type;

    public Item(String name, ItemType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }
    
    public void use(Player player) {
        if (this.name.equalsIgnoreCase("Potion") && this.type == ItemType.CONSUMABLE) {
            player.heal(5); 
            System.out.println("Vous utilisez une potion et récupérez 20 PV !");
        } else {
            System.out.println("Cet objet ne peut pas être utilisé maintenant.");
        }
    }
}
