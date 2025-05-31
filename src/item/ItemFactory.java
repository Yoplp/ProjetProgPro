package item;

public class ItemFactory {
    public static Item createItem(String name) {
        return switch (name.toLowerCase()) {
            case "potion" -> new Item("Potion", ItemType.CONSUMABLE);
            case "clé" -> new Item("Clé", ItemType.CONSUMABLE);
            case "épée" -> new Item("Épée", ItemType.EQUIPMENT);
            case "baguette magique" -> new Item("Baguette magique", ItemType.EQUIPMENT);
            default -> throw new IllegalArgumentException("Item inconnu: " + name);
        };
    }
}
