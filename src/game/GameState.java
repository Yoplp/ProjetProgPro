package game;

import map.GameMap;
import character.Player;

public class GameState {
    private static GameState instance;
    private GameMap map;
    private Player player;
    private boolean isRunning;

    private GameState() { 
        this.map = new GameMap();
        this.player = new Player("Salvateur Stellaire dis SS", 10, map.getStartRoom());
        this.isRunning = true;
    }

    public static GameState getInstance() {
        if (instance == null) {

            instance = new GameState();
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
