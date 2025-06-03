package character.state;

import java.util.Scanner;

import character.Npc;
import character.Player;

public interface NpcState {
    void interact(Npc npc, Player player, Scanner scanner);
}
