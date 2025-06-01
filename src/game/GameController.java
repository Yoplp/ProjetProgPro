package game;

import map.GameMap;
import map.Room;
import character.Monster;
import character.Player;

public class GameController {
    private static GameController instance;
    private GameMap map;
    private Player player;
    private boolean isRunning;
    
    private GameController() {
        this.map = new GameMap();
        this.player = new Player("Salvateur Stellaire dis SS", 10, map.getStartRoom());
        this.isRunning = true;
        if (player.isDead()) {
        	System.out.println("Game Over");
        	this.isRunning=false;
        }
        Room bossRoom = map.getRoom("Boss");
        if (bossRoom != null) {
            for (character.Character c : bossRoom.getCharacter()) {
                if (c instanceof Monster) {
                    Monster boss = (Monster) c;
                    if (boss.getName().equalsIgnoreCase("Boss final") && boss.isDead()) {
                        System.out.println("C'est Gagné");
                        this.isRunning = false;
                    }
                }
            }
        }
    }

    public static GameController getInstance() {
        if (instance == null) {

            instance = new GameController();
        }
        return instance;
    }

    public Player getPlayer() {
        return player;
    }

    public GameMap getMap() {
        return map;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void start() {
        isRunning = true;
    }

    public void stop() {
        isRunning = false;
    }
}
