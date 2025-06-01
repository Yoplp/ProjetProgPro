package game;

import java.util.ArrayList;
import java.util.Scanner;

import map.Room;
import map.Direction;
import character.*;
import character.Character;
import character.strategy.AttackMagique;
import character.strategy.AttackPhysique;
import item.Inventory;
import item.Item;
import item.ItemType;

public class Game {
    private GameController gameState;
    private Scanner scanner;

    public Game() {
        this.gameState = GameController.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Bienvenue dans ce jeu textuel !");

        while (gameState.isRunning()) {
            Room salleActuelle = gameState.getPlayer().getCurrentRoom();
            System.out.println("\nVous êtes dans : " + salleActuelle.getName());

            boolean actionFaite = false;

            for (Character c : salleActuelle.getCharacter()) {
                if (c instanceof Monster) {
                    Monster monstre = (Monster) c;
                    System.out.println("Un monstre est ici : " + monstre.getName());
                    System.out.println("Que voulez-vous faire ? (attaquer / utiliser objet / sortir)");
                    String input = scanner.nextLine().toLowerCase().trim();

                    switch (input) {
                        case "attaquer":
                        	attack(monstre);
                            break;
                        case "utiliser objet":
                            Inventory inv = gameState.getPlayer().getInventory();
                            inv.showInventory();
                            System.out.println("Quel objet voulez-vous utiliser ?");
                            String nomObjet = scanner.nextLine().trim();
                            Item item = inv.getItem(nomObjet);
                            if (item != null) {
                                item.use(gameState.getPlayer());
                                if (item.getType() == ItemType.CONSUMABLE) {
                                    inv.removeItem(item);
                                }
                            } else {
                                System.out.println("Objet introuvable dans l'inventaire.");
                            }
                            break;
                        case "sortir":
                            proposerSorties(scanner, salleActuelle, gameState);
                            break;
                        default:
                            System.out.println("Commande inconnue.");
                    }

                    actionFaite = true;
                    break;
                }
            }

            if (actionFaite) continue;

            if (!salleActuelle.getItem().isEmpty()) {
                for (Item item : salleActuelle.getItem()) {
                    System.out.println("Un objet est ici : " + item.getName());
                }

                System.out.println("Que voulez-vous faire ? (prendre / sortir)");
                String input = scanner.nextLine().toLowerCase().trim();

                switch (input) {
                    case "prendre":
                        for (Item item : new ArrayList<>(salleActuelle.getItem())) {
                            gameState.getPlayer().getInventory().addItem(item);
                            salleActuelle.removeItem(item);
                            System.out.println("Objet ajouté à l'inventaire : " + item.getName());
                        }
                        break;
                    case "sortir":
                        proposerSorties(scanner, salleActuelle, gameState);
                        break;
                    default:
                        System.out.println("Commande inconnue.");
                }

                continue;
            }

            for (Character c : salleActuelle.getCharacter()) {
                if (c instanceof Npc) {
                    Npc pnj = (Npc) c;
                    System.out.println("Vous semblez voir une personne, Oh c'est le " + pnj.getName());
                    System.out.println("Que voulez-vous faire ? (action / sortir)");
                    String input = scanner.nextLine().toLowerCase().trim();

                    switch (input) {
                        case "action":
                        	pnj.interact(gameState.getPlayer());
                            break;
                        case "sortir":
                            proposerSorties(scanner, salleActuelle, gameState);
                            break;
                        default:
                            System.out.println("Commande inconnue.");
                    }

                    actionFaite = true;
                    break;
                }
            }

            if (actionFaite) continue;

            System.out.println("La salle est vide.");
            proposerSorties(scanner, salleActuelle, gameState);
        }
    }
    
    private void proposerSorties(Scanner scanner, Room salle, GameController etat) {
        System.out.println("Sorties disponibles :");
        for (Direction dir : Direction.values()) {
            if (salle.getExit(dir) != null) {
                System.out.println("- " + dir.name().toUpperCase());
            }
        }

        System.out.print("Choisissez une direction : ");
        String direction = scanner.nextLine().toUpperCase().trim();

        try {
            Direction dirChoisie = Direction.valueOf(direction);
            Room prochaineSalle = salle.getExit(dirChoisie);
            if (prochaineSalle != null) {
            	if (prochaineSalle.getName().equalsIgnoreCase("Door")) {
                    if (gameState.getPlayer().getInventory().getItem("Clé") == null) {
                        System.out.println("La porte est verrouillée. Vous avez besoin d'une clé pour entrer.");
                        return;
                    } else {
                        System.out.println("Vous utilisez la clé pour ouvrir la porte.");
                    }
                }
                gameState.getPlayer().setCurrentRoom(prochaineSalle);
            } else {
                System.out.println("Pas de sortie dans cette direction.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Direction invalide.");
        }
    }
    
    
    private void attack(Monster monstre) {
        Player player = gameState.getPlayer();

        while (monstre.getHealth() > 0 && player.getHealth() > 0) {
            System.out.println("\nChoisissez un type d'attaque : (physique / magique)");
            String choix = scanner.nextLine().toLowerCase().trim();

            switch (choix) {
                case "physique":
                    if (player.getInventory().getItem("Épée") != null) {
                        player.setAttackStrategy(new AttackPhysique());
                    } else {
                        System.out.println("Vous n'avez pas d'épée !");
                        continue;
                    }
                    break;

                case "magique":
                    if (player.getInventory().getItem("Baguette magique") != null) {
                        player.setAttackStrategy(new AttackMagique());
                    } else {
                        System.out.println("Vous n'avez pas de baguette magique !");
                        continue;
                    }
                    break;

                default:
                    System.out.println("Type d'attaque inconnu.");
                    continue;
            }

            player.attack(monstre);

            if (monstre.getHealth() <= 0) {
                System.out.println(monstre.getName() + " est vaincu !");
                player.getCurrentRoom().removeCharacter(monstre);
                player.healToFull();
                System.out.println("Vous êtes soigné, votre vie est restaurée !");
                break;
            }

            System.out.println(monstre.getName() + " riposte !");
            player.takeDamage(monstre.getAttack());
            System.out.println("Vous avez maintenant " + player.getHealth() + " PV.");

        }
    }


}
