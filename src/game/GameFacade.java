package game;

import java.util.ArrayList;
import java.util.Scanner;

import character.strategy.AttackStrategy;
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
    private final Scanner scanner;

    public GameFacade() {
        this.scanner = new Scanner(System.in);
        this.victoryManager = new VictoryManager();
    }

    public void run() {
        controller = GameController.getInstance();
        GameInitializer.initializeGame(controller);

        System.out.println("Bienvenue dans ce jeu textuel !");

        while (controller.isRunning()) {
            Room currentRoom = controller.getPlayer().getCurrentRoom();
            System.out.println("Vous êtes dans la salle : " + currentRoom.getName());

            for (Character character : new ArrayList<>(currentRoom.getCharacters())) {
                if (character instanceof Monster monster) {
                    handleMonster(monster);
                } else if (character instanceof Npc npc) {
                    handleNpc(npc);
                }
                if (!controller.isRunning()) return;
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
            interacting = switch (input) {
                case "1" -> {
                    combat(monster);
                    yield false;
                }
                case "2" -> {
                    useItem();
                    yield true;
                }
                case "3" -> false;
                default -> {
                    System.out.println("Commande invalide.");
                    yield true;
                }
            };
        }
    }

    private void handleNpc(Npc npc) {
        System.out.println("Un personnage est là : " + npc.getName());
        boolean interacting = true;
        while (interacting) {
            System.out.println("1. Interagir / 2. Attaquer / 3. Utiliser objet / 4. Sortir");
            String input = scanner.nextLine().trim();
            interacting = switch (input) {
                case "1" -> {
                    npc.getState().interact(npc, controller.getPlayer(), scanner);
                    yield victoryManager.checkGameState(controller);
                }
                case "2" -> {
                    combat(npc);
                    if (!npc.isDead()) {
                        System.out.println("Vous avez pris la fuite !");
                        System.out.println(npc.getName() + " est maintenant agressif !");
                        npc.setState(new AggressiveState());
                    }
                    yield false;
                }
                case "3" -> {
                    useItem();
                    yield true;
                }
                case "4" -> false;
                default -> {
                    System.out.println("Commande invalide.");
                    yield true;
                }
            };
        }
    }

    private void handleMovement(Room currentRoom) {
        System.out.println("Où voulez-vous aller ?");
        currentRoom.showExits();

        String input = scanner.nextLine().toUpperCase().trim();
        controller.getPlayer().moveTo(input);
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

        if (!hasAnyWeapon(player)) {
            System.out.println("Vous n'avez pas d'armes pour attaquer !");
            player.performAttack(target);
            enemyCounterAttack(player, target);
            return;
        }

        while (!target.isDead() && !player.isDead()) {
            AttackStrategy strategy = selectAttackStrategy(player);
            if (strategy == null) continue;

            player.setAttackStrategy(strategy);
            player.performAttack(target);

            if (target.isDead()) {
                player.getCurrentRoom().removeCharacter(target);
                victoryManager.checkGameState(controller);
                return;
            }

            enemyCounterAttack(player, target);
        }
    }

    private boolean hasAnyWeapon(Player player) {
        return player.getInventory().getItem("Épée") != null ||
                player.getInventory().getItem("Baguette magique") != null;
    }

    private AttackStrategy selectAttackStrategy(Player player) {
        boolean hasSword = player.getInventory().getItem("Épée") != null;
        boolean hasWand = player.getInventory().getItem("Baguette magique") != null;

        System.out.println("Choisissez un type d'attaque :");
        if (hasSword) System.out.println("1. Physique");
        if (hasWand) System.out.println("2. Magique");

        String input = scanner.nextLine().trim();

        return switch (input) {
            case "1" -> {
                if (!hasSword) {
                    System.out.println("Vous n'avez pas d'épée !");
                    yield null;
                }
                yield new AttackPhysique();
            }
            case "2" -> {
                if (!hasWand) {
                    System.out.println("Vous n'avez pas de baguette magique !");
                    yield null;
                }
                yield new AttackMagique();
            }
            default -> {
                System.out.println("Type d'attaque inconnu.");
                yield null;
            }
        };
    }

    private void enemyCounterAttack(Player player, Character target) {
        System.out.println(target.getName() + " riposte !");
        player.takeDamage(target.getAttack());
        System.out.println("Vous avez maintenant " + player.getHealth() + " PV.\n");
    }
}
