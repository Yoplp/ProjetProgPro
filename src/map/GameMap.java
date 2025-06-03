package map;

import character.Monster;
import character.Npc;
import character.Type;
import item.Inventory;
import item.ItemFactory;

import java.util.*;

public class GameMap {
    private Map<String, Room> rooms;
    private Room startRoom;
    private Room bossRoom;

    public GameMap() {
        rooms = new HashMap<>();
        initMap();
    }

    private void initMap() {
        Room start = new Room("Start");

        Room boss = new Room("Boss");
        boss.addCharacter(new Monster("Boss final", 3, 20, Type.NORMAL));

        Room merchant = new Room("Marchand");
        Npc marchand = new Npc("Marchand", 9, 10, "Tu veux acheter quelque chose ?");
        Inventory invMarchand = new Inventory();
        invMarchand.addItem(ItemFactory.createItem("épée"));
        invMarchand.addItem(ItemFactory.createItem("baguette magique"));
        marchand.setInventory(invMarchand);
        merchant.addCharacter(marchand);

        Room bar = new Room("Bar");
        Npc vieilHomme = new Npc("Vieil homme", 9, 10, "Tu veux ce vieux breuvage ?");
        Inventory invVieilHomme = new Inventory();
        invVieilHomme.addItem(ItemFactory.createItem("potion"));
        vieilHomme.setInventory(invVieilHomme);
        bar.addCharacter(vieilHomme);

        Room goblin = new Room("Goblin");
        goblin.addCharacter(new Monster("Goblin", 3, 15, Type.PHYSICAL));

        Room ghost = new Room("Fantômes");
        ghost.addCharacter(new Monster("Fantômes", 7, 5, Type.MAGIC));

        Room pc = new Room("PC Bang");
        Npc prince = new Npc("Prince Nigérien", 9, 10, "Donne-moi 5 pièces et je te rends riche !");
        Inventory invPrince = new Inventory();
        invPrince.addItem(ItemFactory.createItem("clé"));
        prince.setInventory(invPrince);
        pc.addCharacter(prince);

        Room door = new Room("Porte");

        Room hallway1 = new Room("Couloir 1");
        Room hallway2 = new Room("Couloir 2");
        Room hallway3 = new Room("Couloir 3");
        Room hallway4 = new Room("Couloir 4");
        Room hallway5 = new Room("Couloir 5");
        Room hallway6 = new Room("Couloir 6");
        
        connectRooms(door, Direction.NORTH, boss);
        connectRooms(start, Direction.NORTH, hallway2);
        connectRooms(start, Direction.SOUTH, hallway1);
        connectRooms(bar, Direction.WEST, hallway2);
        connectRooms(merchant, Direction.EAST, hallway1);
        connectRooms(hallway2, Direction.NORTH, hallway3);
        connectRooms(hallway3, Direction.WEST, hallway4);
        connectRooms(hallway4, Direction.WEST, hallway5);
        connectRooms(hallway4, Direction.NORTH, door);
        connectRooms(door, Direction.NORTH, boss);
        connectRooms(hallway5, Direction.WEST, hallway6);
        connectRooms(hallway5, Direction.SOUTH, ghost);
        connectRooms(hallway6, Direction.WEST, goblin);
        connectRooms(hallway6, Direction.NORTH, pc);

        rooms.put(bar.getName(), bar);
        rooms.put(start.getName(), start);
        rooms.put(merchant.getName(), merchant);
        rooms.put(door.getName(), door);
        rooms.put(boss.getName(), boss);
        rooms.put(ghost.getName(), ghost);
        rooms.put(pc.getName(), pc);
        rooms.put(goblin.getName(), goblin);
        rooms.put(hallway1.getName(), hallway1);
        rooms.put(hallway2.getName(), hallway2);
        rooms.put(hallway3.getName(), hallway3);
        rooms.put(hallway4.getName(), hallway4);
        rooms.put(hallway5.getName(), hallway5);
        rooms.put(hallway6.getName(), hallway6);

        this.bossRoom = boss;
        this.startRoom = start;
    }
    
    private void connectRooms(Room room1, Direction dirFrom1To2, Room room2) {
        room1.addExit(dirFrom1To2, room2);
        room2.addExit(dirFrom1To2.getOpposite(), room1);
    }

    public Room getStartRoom() {
        return startRoom;
    }

    public Room getBossRoom() {
        return bossRoom;
    }

    public Room getRoom(String name) {
        return rooms.get(name.toLowerCase());
    }
}

