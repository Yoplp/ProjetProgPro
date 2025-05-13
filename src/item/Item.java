package item;

public abstract class Item {
    private String name;
    private TypeItem type;

    public enum TypeItem {
        CONSUMABLE,
        EQUIPMENT
    }

    public Item(String name, TypeItem type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public TypeItem getType() {
        return type;
    }

    public abstract void utiliser();
}
