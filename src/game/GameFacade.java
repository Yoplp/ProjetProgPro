package game;

import java.util.Scanner;

import map.Room;
import map.Direction;
import character.*;
import character.Character;
import character.state.AggressiveState;
import character.strategy.AttackMagique;
import character.strategy.AttackPhysique;
import item.Inventory;
import item.Item;
import item.ItemType;

public class GameFacade {
    private GameController controller;
    private VictoryManager victoryManager;
    private Scanner scanner;

    public GameFacade() {
        scanner = new Scanner(System.in);
        victoryManager = new VictoryManager();
    }

    public void run() {
        controller = GameController.getInstance();
        GameInitializer.initializeGame(controller);

        System.out.println("Bienvenue dans ce jeu textuel !");

        while (controller.isRunning()) {
            Room currentRoom = controller.getPlayer().getCurrentRoom();
            System.out.println("Vous êtes dans la salle : " + currentRoom.getName());

            if (currentRoom.getName().equalsIgnoreCase("Porte")) {
                if (controller.getPlayer().getInventory().getItem("Clé") == null) {
                    System.out.println("La porte est verrouillée. Vous avez besoin d'une clé pour entrer.\n");
                    return;
                } else {
                    System.out.println("Vous utilisez la clé pour ouvrir la porte.\n");
                }
            }

            for (Character character : currentRoom.getCharacters()) {
                if (character instanceof Monster) {
                    handleMonster((Monster) character);
                    if (!controller.isRunning()) return;
                } else if (character instanceof Npc) {
                    handleNpc((Npc) character);
                    if (!controller.isRunning()) return;
                }
            }

            victoryManager.checkGameState(controller);
            if (controller.isRunning()) {
                handleMovement(currentRoom);
            }
        }
    }

    private void handleMonster(Monster monster) {
        System.out.println("Un monstre apparaît : " + monster.getName());
        boolean interacting = true;
        while (interacting) {
            System.out.println("1. Attaquer / 2. Utiliser objet / 3. Fuir");
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    combat(monster);
                    break;
                case "2":
                    useItem();
                    break;
                case "3":
                    interacting = false;
                    break;
                default:
                    System.out.println("Commande invalide.");
            }
        }
    }

    private void handleNpc(Npc npc) {
        System.out.println("Un personnage est là : " + npc.getName());
        boolean interacting = true;
        while (interacting) {
            System.out.println("1. Interagir / 2. Attaquer / 3. Utiliser objet / 4. Sortir");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                	npc.getState().interact(npc, controller.getPlayer(), scanner);
                    break;
                case "2":
                	combat(npc);
                    npc.setState(new AggressiveState());
                    interacting = false;
                    break;
                case "3":
                    useItem();
                    break;
                case "4":
                    interacting = false;
                    break;
                default:
                    System.out.println("Commande invalide.");
            }
        }
    }

    private void handleMovement(Room currentRoom) {
        System.out.println("Où voulez-vous aller ?");
        currentRoom.showExits();

        String input = scanner.nextLine().toUpperCase().trim();
        try {
            if (!controller.getPlayer().moveTo(Direction.valueOf(input))) {
                System.out.println("Impossible d'aller dans cette direction.\n");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Direction invalide.\n");
        }
    }

    private void useItem() {
        Inventory inv = controller.getPlayer().getInventory();
        inv.showInventory();

        if (!inv.getItems().isEmpty()) {
            System.out.println("Quel objet voulez-vous utiliser ?");
            String input = scanner.nextLine().trim();
            Item item = inv.getItem(input);
            if (item != null) {
                item.use(controller.getPlayer());
                if (item.getType() == ItemType.CONSUMABLE) {
                    inv.removeItem(item);
                }
            } else {
                System.out.println("Objet introuvable dans l'inventaire.\n");
            }
        }
    }

    private void combat(Character target) {
        Player player = controller.getPlayer();
        boolean inCombat = true;

        while (inCombat) {
            System.out.println("Choisissez un type d'attaque : (1. physique / 2. magique)");
            String input = scanner.nextLine().trim();
            switch (input) {
                case "1" -> {
                    if (player.getInventory().getItem("Épée") != null) {
                        player.setAttackStrategy(new AttackPhysique());
                    } else {
                        System.out.println("Vous n'avez pas d'épée !");
                    }
                }
                case "2" -> {
                    if (player.getInventory().getItem("Baguette magique") != null) {
                        player.setAttackStrategy(new AttackMagique());
                    } else {
                        System.out.println("Vous n'avez pas de baguette magique !");
                    }
                }
                default -> {
                    System.out.println("Type d'attaque inconnu.");
                    continue;
                }
            }

            player.performAttack(target);

            if (target.isDead()) {
                System.out.println(target.getName() + " est vaincu !");
                player.getCurrentRoom().removeCharacter(target);
                player.healToFull();
                System.out.println("Vous êtes soigné, votre vie est restaurée !");
                break;
            }

            System.out.println(target.getName() + " riposte !");
            player.takeDamage(target.getAttack());
            System.out.println("Vous avez maintenant " + player.getHealth() + " PV.\n");

            if (target.isDead() || controller.getPlayer().isDead()) {
                inCombat = false;
            }
        }
    }
}
