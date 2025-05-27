package character.state;

import character.Npc;
import character.Player;

public class PassiveState implements NpcState {
    @Override
    public void interact(Npc npc, Player player) {
        System.out.println(npc.getName() + " says: " + npc.getMessage());
        npc.sellItemsTo(player);
    }
}
