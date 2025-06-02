package game;

import character.Player;
import map.GameMap;

public class GameInitializer {
    public static void initializeGame(GameController controller) {
        GameMap map = new GameMap();
        Player player = new Player("Salvateur Stellaire", 10, map.getStartRoom());
        controller.setMap(map);
        controller.setPlayer(player);
        controller.start();
    }
}
