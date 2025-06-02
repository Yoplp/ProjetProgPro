package item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }
    
    public List<Item> getItems() {
        return items;
    }

    public void showInventory() {
        if (items.isEmpty()) {
            System.out.println("Inventaire vide\n");
        } else {
            System.out.println("Inventaire:");
            for (Item item : items) {
                System.out.println(item);
            }
        }
    }

    public Item getItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }
}
