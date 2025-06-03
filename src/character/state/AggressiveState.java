package character.state;

import character.Npc;
import character.Player;
import java.util.Scanner;

public class AggressiveState implements NpcState {
    @Override
    public void interact(Npc npc, Player player, Scanner scanner) {
        System.out.println(npc.getName() + " est enragé et vous attaque !");
        npc.performAttack(player);
    }
}
