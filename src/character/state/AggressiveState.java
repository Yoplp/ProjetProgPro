package character.state;

import character.Npc;
import character.Player;

public class AggressiveState implements NpcState {
    @Override
    public void interact(Npc npc, Player player) {
        System.out.println(npc.getName() + " becomes aggressive!");
        npc.performAttack(player);
    }
}