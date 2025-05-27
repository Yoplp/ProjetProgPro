package map;

import character.Monster;
import character.Npc;

import java.util.*;

public class GameMap {
    private Map<String, Room> rooms;
    private Room startRoom;

    public GameMap() {
        rooms = new HashMap<>();
        initMap();
    }

    private void initMap() {
        Room start = new Room("Start");

        Room merchant = new Room("Marchand");
        merchant.addCharacter(new Npc("Marchand", 10,10, "Tu veux acheter quelque chose ?"));

        Room bar = new Room("Bar");
        bar.addCharacter(new Npc("Vieil homme", 10,10, "Il y a un secret derrière la porte..."));

        Room goblin = new Room("Goblin");
        goblin.addCharacter(new Monster("Goblin", 3, 15));
        Room chestGoblin = new Room("Chambre du Goblin");

        Room ghost = new Room("Fantômes");
        ghost.addCharacter(new Monster("Fantômes", 7, 5));
        Room chestGhost = new Room("Tombe du Fantômes");

        Room pc = new Room("PC Bang");
        pc.addCharacter(new Npc("Prince Nigérien", 10, 10, "Salut à toi, jeune entrepreneur !"));

        Room door = new Room("Door");
        door.addCharacter(new Monster("Door", 0, 1000));

        Room boss = new Room("Boss");
        boss.addCharacter(new Monster("Boss final", 3, 20));

        Room hallway1 = new Room("Hallway 1");
        Room hallway2 = new Room("Hallway 2");
        Room hallway3 = new Room("Hallway 3");
        Room hallway4 = new Room("Hallway 4");
        Room hallway5 = new Room("Hallway 5");
        Room hallway6 = new Room("Hallway 6");

        connectRooms(start, Direction.NORTH, hallway2);
        connectRooms(start, Direction.SOUTH, hallway1);
        connectRooms(bar, Direction.WEST, hallway2);
        connectRooms(merchant, Direction.EAST, hallway1);
        connectRooms(hallway2, Direction.NORTH, hallway3);
        connectRooms(hallway3, Direction.WEST, hallway4);
        connectRooms(hallway4, Direction.WEST, hallway5);
        connectRooms(hallway4, Direction.NORTH, door);
        connectRooms(hallway5, Direction.WEST, hallway6);
        connectRooms(hallway5, Direction.SOUTH, ghost);
        connectRooms(ghost, Direction.SOUTH, chestGhost);
        connectRooms(hallway6, Direction.WEST, goblin);
        connectRooms(hallway6, Direction.NORTH, pc);
        connectRooms(goblin, Direction.WEST, chestGoblin);

        rooms.put(bar.getName(), bar);
        rooms.put(start.getName(), start);
        rooms.put(merchant.getName(), merchant);
        rooms.put(door.getName(), door);
        rooms.put(boss.getName(), boss);
        rooms.put(ghost.getName(), ghost);
        rooms.put(chestGhost.getName(), chestGhost);
        rooms.put(pc.getName(), pc);
        rooms.put(goblin.getName(), goblin);
        rooms.put(chestGoblin.getName(), chestGoblin);
        rooms.put(hallway1.getName(), hallway1);
        rooms.put(hallway2.getName(), hallway2);
        rooms.put(hallway3.getName(), hallway3);
        rooms.put(hallway4.getName(), hallway4);
        rooms.put(hallway5.getName(), hallway5);
        rooms.put(hallway6.getName(), hallway6);

        this.startRoom = start;
    }
    
    private void connectRooms(Room room1, Direction dirFrom1To2, Room room2) {
        room1.addExit(dirFrom1To2, room2);
        room2.addExit(dirFrom1To2.getOppositeDirection(), room1);
    }

    public Room getStartRoom() {
        return startRoom;
    }

    public Room getRoom(String name) {
        return rooms.get(name.toLowerCase());
    }
}

