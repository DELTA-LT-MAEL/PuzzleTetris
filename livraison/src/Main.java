import java.util.Scanner;

import model.JeuAssemblage;

public class Main {
    /**
     * Cette fonction permet de lancer le jeu
     * 
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Comment vous appelez vous?:");
        String nom = scanner.nextLine();
        System.out.println("Menu:");
        System.out.println("1. Lancement de l'interface du jeu");
        System.out.println("2. Lancement du jeu sur le terminal");
        System.out.println("3. Lancement du Bot sur le terminal ");
        System.out.println("4. Quitter");
        int choix = scanner.nextInt();

        switch (choix) {
            case 4:
                System.out.println("Au revoir !");
                break;
            case 3:
                System.out.println("Lancement du jeu...");
                JeuAssemblage jeuBot = new JeuAssemblage(20, 20, 10);
                jeuBot.jeuBot(nom);
                System.out.println("Résulat : " + (100 - jeuBot.resultat()));
                break;

            case 2:
                System.out.println("Combien de pièces voulez vous?");
                int nbrPieceTer = scanner.nextInt();
                System.out.println("Lancement du jeu...");
                JeuAssemblage jeuTerminal = new JeuAssemblage(20, 20, nbrPieceTer);
                jeuTerminal.jeuHumainConsole(nom);
                System.out.println("Résulat : " + (100 - jeuTerminal.resultat()));
                break;

            case 1:
                System.out.println("Combien de pièces voulez vous?");
                int nbrPieceInter = scanner.nextInt();
                System.out.println("Lancement du jeu...");
                long startTime = System.currentTimeMillis();
                JeuAssemblage jeuInterface = new JeuAssemblage(20, 20, nbrPieceInter);
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                System.out.println("Temps d'execution de l'interface: " + totalTime + " ms");
                jeuInterface.jeuHumainInterface(nom);
                System.out.println("Résulat : " + (100 - jeuInterface.resultat()));
                break;

        }

        scanner.close();

    }
}
