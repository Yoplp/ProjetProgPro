package character.state;

import character.Npc;
import character.Player;

public interface NpcState {
    void interact(Npc npc, Player player);
}
