package game;

import java.util.List;
import java.util.Scanner;
import map.Room;
import character.*;
import character.Character;

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

        personnages.add(new Monstre("Gobelin", 30));
        personnages.add(new Monstre("Fantôme", 20));
        personnages.add(new Monstre("Boss final", 100));

        personnages.add(new PNJ("Marchand", "Tu veux acheter quelque chose ?"));
        personnages.add(new PNJ("Vieil homme", "Il y a un secret derrière la porte..."));
    
    public void demarrer() {
        System.out.println("Bienvenue dans le jeu d'aventure !");
        
        boolean enCours = true;
        while (enCours) {
            Room salleActuelle = player.getCurrentRoom();
            System.out.println("\nVous êtes dans : " + salleActuelle.getName());

            if (salleActuelle.getCharacter() == "mo") {
                System.out.println("⚔️ Un monstre est ici : " + salleActuelle.getMonstre().getNom());
                System.out.println("Que voulez-vous faire ? (attaquer / utiliser objet / sortir)");
                String input = scanner.nextLine().toLowerCase().trim();

                switch (input) {
                    case "attaquer":
                        player.attaquer(salleActuelle.getMonstre());
                        break;
                    case "utiliser objet":
                        player.utiliserObjet();
                        break;
                    case "sortir":
                        proposerSorties(scanner, salleActuelle, gameState);
                        break;
                    default:
                        System.out.println("Commande inconnue.");
                }

            } else if (salleActuelle.getObjet() != null) {
                System.out.println("📦 Un objet est ici : " + salleActuelle.getObjet().getNom());
                System.out.println("Que voulez-vous faire ? (prendre / sortir)");
                String input = scanner.nextLine().toLowerCase().trim();

                switch (input) {
                    case "prendre":
                        player.ajouterObjetInventaire(salleActuelle.getObjet());
                        salleActuelle.setObjet(null);
                        System.out.println("Objet ajouté à l'inventaire.");
                        break;
                    case "sortir":
                        proposerSorties(scanner, salleActuelle, gameState);
                        break;
                    default:
                        System.out.println("Commande inconnue.");
                }

            } else if (salleActuelle.getPNJ() != null) {
                System.out.println("🧑 Un personnage est ici : " + salleActuelle.getPNJ().getNom());
                System.out.println("Que voulez-vous faire ? (parler / sortir)");
                String input = scanner.nextLine().toLowerCase().trim();

                switch (input) {
                    case "parler":
                        salleActuelle.getPNJ().parler();
                        break;
                    case "sortir":
                        proposerSorties(scanner, salleActuelle, gameState);
                        break;
                    default:
                        System.out.println("Commande inconnue.");
                }

            } else {
                System.out.println("La salle est vide.");
                proposerSorties(scanner, salleActuelle, gameState);
            }
        }

        System.out.println("C'est Gagné, C'est Gagné, Ouaiiiiis !!!!!!");
    }
}
