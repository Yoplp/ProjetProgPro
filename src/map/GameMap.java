package map;

import java.util.*;

public class GameMap {
    private Map<String, Room> rooms;
    private Room StartRoom;

    public GameMap() {
        rooms = new HashMap<>();
        initialiserCarte();
    }

    private void initialiserCarte() {
    	Room Bar = new Room("Bar");
    	Room Start = new Room("Début");
    	Room Marchand = new Room("Marchand");
    	Room couloir1 = new Room("Couloir 1");
    	Room couloir2 = new Room("Couloir 2");
    	Room couloir3 = new Room("Couloir 3");
    	Room couloir4 = new Room("Couloir 4");
    	Room couloir5 = new Room("Couloir 5");
    	Room couloir6 = new Room("Couloir 6");
    	Room Porte = new Room("Porte");
    	Room Boss = new Room("Boss");
    	Room ghost = new Room("ghost");
    	Room ChestGhost = new Room("chest ghost");
    	Room PC = new Room("PC");
    	Room gobelin = new Room("gobelin");
    	Room chestgobelin = new Room("chest gobelin");
        
        Start.addExit("nord", couloir2);
        Start.addExit("sud", couloir1);
        Bar.addExit("ouest", couloir2);
        Marchand.addExit("est", couloir1);
        couloir1.addExit("nord", Start);
        couloir1.addExit("ouest", Marchand);
        couloir2.addExit("nord", couloir3);
        couloir2.addExit("sud", Start);
        couloir2.addExit("est", Bar);
        couloir3.addExit("ouest", couloir4);
        couloir3.addExit("sud", couloir2);
        couloir4.addExit("ouest", couloir5);
        couloir4.addExit("est", couloir3);
        couloir4.addExit("nord", Porte);
        couloir5.addExit("ouest", couloir6);
        couloir5.addExit("est", couloir4);
        couloir5.addExit("sud", ghost);
        ghost.addExit("sud", ChestGhost);
        ghost.addExit("nord", couloir5);
        ChestGhost.addExit("nord", ghost);
        couloir6.addExit("ouest", gobelin);
        couloir6.addExit("est", couloir5);
        couloir6.addExit("nord", PC);
        gobelin.addExit("ouest", chestgobelin);
        gobelin.addExit("est", couloir6);
        chestgobelin.addExit("est", gobelin);
        PC.addExit("sud", couloir6);
        
        

        rooms.put("bar", Bar);
        rooms.put("debut", Start);
        rooms.put("marchand", Marchand);
        rooms.put("couloir1", couloir1);
        rooms.put("couloir2", couloir2);
        rooms.put("couloir3", couloir3);
        rooms.put("couloir4", couloir4);
        rooms.put("couloir5", couloir5);
        rooms.put("couloir6", couloir6);
        rooms.put("porte", Porte);
        rooms.put("boss", Boss);
        rooms.put("ghost", ghost);
        rooms.put("chestghost", ChestGhost);
        rooms.put("pc", PC);
        rooms.put("gobelin", gobelin);
        rooms.put("chestgobelin", chestgobelin);

        this.StartRoom = Start;
    }

    public Room getStart() {
        return StartRoom;
    }

    public Room getRoomByName(String nom) {
        return rooms.get(nom.toLowerCase());
    }
}

