package game;

import character.Monster;
import character.Player;
import map.GameMap;
import map.Room;

public class VictoryManager {

    public void checkGameState(GameController controller) {
        Player player = controller.getPlayer();
        GameMap map = controller.getMap();

        if (player.isDead()) {
            System.out.println("Game Over, vous êtes mort.");
            controller.stop();
            return;
        }

        Room bossRoom = map.getRoom("Boss");
        if (bossRoom != null) {
            for (character.Character c : bossRoom.getCharacters()) {
                if (c instanceof Monster boss) {
                    if (boss.getName().equalsIgnoreCase("Boss final") && boss.isDead()) {
                        System.out.println("Victoire ! Vous avez vaincu le boss final.");
                        controller.stop();
                        return;
                    }
                }
            }
        }
    }
}