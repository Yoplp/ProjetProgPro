package game;

import character.Monster;
import character.Player;
import map.GameMap;
import map.Room;

public class VictoryManager {

    public boolean checkGameState(GameController controller) {
        Player player = controller.getPlayer();
        GameMap map = controller.getMap();

        if (player.isDead()) {
            System.out.println("Game Over, vous êtes mort.");
            controller.stop();
            return false;
        }

        if (map.getBossRoom() != null) {
            if (map.getBossRoom().getCharacters().isEmpty()) {
                System.out.println("Victoire ! Vous avez vaincu le boss final.");
                controller.stop();
                return false;
            }
        }
        return true;
    }
}