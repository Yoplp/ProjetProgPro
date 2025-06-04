package item;

import character.Player;

public class Item {
    public static final int POTION_HEAL = 5;
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
    
    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
    
    public void use(Player player) {
        if (this.name.equalsIgnoreCase("Potion") && this.type == ItemType.CONSUMABLE) {
            System.out.println("Vous utilisez une potion et récupérez "+ POTION_HEAL +" PV !");
            player.heal(POTION_HEAL);
        } else {
            System.out.println("Cet objet ne peut pas être utilisé maintenant.");
        }
    }
}
