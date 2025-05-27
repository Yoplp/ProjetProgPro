package game;

import map.GameMap;
import character.Player;

public class GameState {
    private static GameState instance;
    private GameMap map;
    private Player joueur;
    private boolean isRunning;

    private GameState() { 
        this.map = new GameMap();
        this.joueur = new Player("Salvateur Stellaire dis SS", 10, map.getStartRoom());
        this.isRunning = true;
    }

    public static GameState getInstance() {
            if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }

    public Player getJoueur() {
        return joueur;
    }

    public GameMap getMap() {
        return map;
    }

    public void start() {
        isRunning = true;
    }

    public void stop() {
        isRunning = false;
    }
}
