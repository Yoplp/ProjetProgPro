package map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import character.Character;
import item.Item;

public class Room {
    private String name;
    private Map<String, Room> exit;
    private List<Item> item;
    private List<Character> character;

    public Room(String name){
        this.name=name;
        this.exit = new HashMap<>();
        this.item = new ArrayList<>();
        this.character = new ArrayList<>();
    }
    public void addExit(String direction, Room NearRoom) {
        exit.put(direction.toLowerCase(), NearRoom);
    }

    public Room getSortie(String direction) {
        return exit.get(direction.toLowerCase());
    }

    public void addItem(Item item) {
        this.item.add(item);
    }

    public void addCharacter(Character character) {
    	this.character.add(character);
    }
    
	public String getName() {
		return name;}
	public void setName(String name) {
		this.name = name;}
	public Map<String, Room> getExit() {
		return exit;}
	public void setExit(Map<String, Room> exit) {
		this.exit = exit;}
	public List<Item> getItem() {
		return item;}
	public void setItem(List<Item> item) {
		this.item = item;}
	public List<Character> getCharacter() {
		return character;}
	public void setCharacter(List<Character> character) {
		this.character = character;}
}
