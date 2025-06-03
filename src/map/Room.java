package map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import character.Character;
import item.Item;

public class Room {
    private String name;
    private Map<Direction, Room> exit;
    private List<Item> item;
    private List<Character> character;

    public Room(String name){
        this.name=name;
        this.exit = new HashMap<>();
        this.item = new ArrayList<>();
        this.character = new ArrayList<>();
    }

    public void addExit(Direction direction, Room NearRoom) {
        exit.put(direction, NearRoom);
    }

    public void showExits() {
        for (Direction direction : exit.keySet()) {
            System.out.println("- " + direction.getTrad());
        }
    }

    public Room getExit(Direction direction) {
        return exit.get(direction);
    }

    public void addCharacter(Character character) {
    	this.character.add(character);
    }

    public void removeCharacter(Character character) {
        this.character.remove(character);
    }

    public List<Character> getCharacters() {
        return character;
    }

    public void addItem(Item item) {
        this.item.add(item);
    }

    public void removeItem(Item item) {
        this.item.remove(item);
    }

    public List<Item> getItems() {
        return item;
    }

    public String getName() {
        return name;
    }
}
