package character.state;

import character.Npc;
import character.Player;
import item.Item;
import java.util.Scanner;

public class PassiveState implements NpcState {
    @Override
    public void interact(Npc npc, Player player, Scanner scanner) {
        System.out.println(npc.getName() + " dit : " + npc.getMessage());

        if (npc.getName().equalsIgnoreCase("Prince Nigérien")) {
            Scanner ecoute = new Scanner(System.in); 
            System.out.println("Le prince vous propose un marché : vous donnez accès à vos comptes contre une clé. Acceptez-vous ? (oui/non)");
            String reponse = ecoute.nextLine().trim().toLowerCase();

            if (reponse.equals("oui")) {
                if (player.getGold() >= 5) {
                    player.setGold(0);
                    Item cle = npc.getInventory().getItem("Clé");
                    if (cle != null) {
                        player.getInventory().addItem(cle);
                        System.out.println("Le prince vous vole tout votre or et vous donne une vielle clé rouillé !");
                    } else {
                        System.out.println("Le prince voulait vous arnaquer mais il n’a rien à donner !");
                    }
                } else {
                    System.out.println("Le prince dit : Tu es trop pauvre pour moi !");
                }
            } else {
                System.out.println("Le prince dit : Tant pis pour toi !");
            }
            return;
        }


        npc.sellItemsTo(player, scanner);
    }
}
