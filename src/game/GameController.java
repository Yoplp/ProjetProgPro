package game;

import map.GameMap;
import character.Player;

public class GameController {
    private static GameController instance;
    private GameMap map;
    private Player player;
    private boolean isRunning;

    private GameController() {

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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setMap(GameMap map) {
        this.map = map;
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
