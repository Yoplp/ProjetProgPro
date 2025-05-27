package game;

import java.util.ArrayList;
import java.util.Scanner;

import character.strategy.AttackStrategy;
import character.strategy.AttackMagique;
import character.strategy.AttackPhysique;
import map.Room;
import map.Direction;
import character.*;
import character.Character;
import item.Item;

public class Game {
    private GameState gameState;
    private Scanner scanner;

    public Game() {
        this.gameState = GameState.getInstance();
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
                            attaquer(monstre);
                            break;
                        case "utiliser objet":
                            //iventaire
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

            // Gestion des objets
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
                            //action du npc
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

    private void proposerSorties(Scanner scanner, Room salle, GameState etat) {
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
    
    public void attaquer(Monster cible) {
        AttackStrategy strategie;

        if (gameState.getPlayer().getInventory().getItem("Baguette magique") != null) {
            strategie = new AttackMagique();
        } else if (gameState.getPlayer().getInventory().getItem("Épée") != null) {
            strategie = new AttackPhysique();
        } else {
            System.out.println("Vous n'avez pas d'arme pour attaquer !");
            return;
        }

        strategie.attack(cible);

        if (cible.getHealth() <= 0) {
            System.out.println(cible.getName() + " est vaincu !");
            Room salleActuelle = gameState.getPlayer().getCurrentRoom();
            salleActuelle.removeCharacter(cible);
        }
    }

}
