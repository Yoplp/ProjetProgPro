package game;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import map.Room;
import map.Direction;
import character.*;
import character.Character;
import item.Inventory;
import item.Item;

public class Game {
    private GameState gameState;
    private Scanner scanner;
    private Player player;

    public Game() {
        this.gameState = GameState.getInstance();
        this.scanner = new Scanner(System.in);
    }

    public static List<Character> creerPersonnages() {
        List<Character> personnages = new ArrayList<>();

        personnages.add(new Monster("Golem", 3, 15));
        personnages.add(new Monster("Fantôme", 7, 5));
        personnages.add(new Monster("Boss final", 3, 20));

        personnages.add(new Npc("Marchand", "Tu veux acheter quelque chose ?"));
        personnages.add(new Npc("Vieil homme", "Il y a un secret derrière la porte..."));

        return personnages;
    }

    public void demarrer() {
        System.out.println("Bienvenue dans le jeu d'aventure !");

        boolean enCours = true;
        while (enCours) {
            Room salleActuelle = player.getCurrentRoom();
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
                            //attaquer
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
                            Inventory.addItem(item);
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

        System.out.println("C'est Gagné, C'est Gagné, Ouaiiiiis !!!!!!");
    }

    private void proposerSorties(Scanner scanner, Room salle, GameState etat) {
        System.out.println("Sorties disponibles :");
        for (Direction dir : Direction.values()) {
            Room sortie = salle.getExit(dir);
            if (sortie != null) {
                System.out.println("- " + dir.name().toUpperCase());
            }
        }
        System.out.print("Choisissez une direction : ");
        String direction = scanner.nextLine().toUpperCase().trim();
        try {
            Direction dirChoisie = Direction.valueOf(direction);
            Room prochaineSalle = salle.getExit(dirChoisie);
            if (prochaineSalle != null) {
                player.setCurrentRoom(prochaineSalle);
            } else {
                System.out.println("Pas de sortie dans cette direction.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Direction invalide.");
        }
    }
}
