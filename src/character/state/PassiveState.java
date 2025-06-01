package character.state;

import character.Npc;
import character.Player;
import item.Item;

public class PassiveState implements NpcState {
    @Override
    public void interact(Npc npc, Player player) {
        System.out.println(npc.getName() + " dit : " + npc.getMessage());

        if (npc.getName().equalsIgnoreCase("Prince Nigérien")) {
            int totalGold = player.getGold();
            if (totalGold >= 5) {
                player.setGold(0);
                Item cle = npc.getInventory().getItem("Clé dorée");
                if (cle != null) {
                    player.getInventory().addItem(cle);
                    System.out.println("Le prince vous vole tout votre or et vous donne une clé dorée.");
                } else {
                    System.out.println("Le prince voulait vous arnaquer, mais il n'a rien à donner !");
                }
            } else {
                System.out.println("Le prince dit : Tu es trop pauvre pour moi !");
            }
            return;
        }

        npc.sellItemsTo(player);
    }
}
