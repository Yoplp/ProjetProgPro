package game;

import character.Player;
import map.GameMap;
import observer.ConsoleObserver;

public class GameInitializer {
    public static void initializeGame(GameController controller) {
        GameMap map = new GameMap();
        Player player = new Player("Héro", 10, map.getStartRoom());
        player.addObserver(new ConsoleObserver());
        controller.setMap(map);
        controller.setPlayer(player);
        controller.start();
    }
}
