package controller.player;

import java.util.*;
import model.*;

public class Human implements Player {
    private String name;
    private Scanner scanner;

    public Human(String name) {
        this.name = name;
        this.scanner = new Scanner(System.in);
    }

    public String getScannerNext() {
        String commande = scanner.next();
        return commande;
    }

    public String getName() {
        return this.name;
    }

    public Scanner getScanner() {
        return this.scanner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Permet au joueur humain de sélectionner une pièce, de la faire pivoter et de la placer sur le plateau de jeu.
     * Demande au joueur de saisir des informations via la console.
     * Si la pièce sélectionnée entre en collision avec une autre pièce ou dépasse les limites, le joueur est invité à réessayer.
     */
    @Override
    public void placePiece(PlateauPuzzle plateau) {
        ArrayList<PiecesPuzzle> tab_colection_piece = plateau.getPieces();
        System.out.println("Veuillez choisir votre pièce (la lettre de la pièce)");
        String couleur = getScannerNext();
        for (int i = 0; i < tab_colection_piece.size(); i++) {
            if (tab_colection_piece.get(i).getId().equals(couleur)) {
                PiecesPuzzle piece = tab_colection_piece.get(i);
                int rota = 0;
                System.out.println("Voulez-vous faire une rotation sur votre pièce (o/oui/n/non)");
                String rotON = getScannerNext();

                while (rotON.equals("oui") || rotON.equals("o")) {
                    System.out.println("De quelle sens vous voulez faire la rotation de votre pièce (d/droite/g/gauche/quit)");
                    String rotation = getScannerNext();
                    if (rotation.equals("quit")) {
                        break;
                    }
                    
                    

                    System.out.println("Combien de rotation voulez vous faire?");
                    String nbrRota = getScannerNext();
                    if (rotation.equals("d") || rotation.equals("droite")) {
                        rota = Integer.valueOf(nbrRota)%4;
                    }
                    else if (rotation.equals("g") || rotation.equals("gauche")) {
                        rota = -Integer.valueOf(nbrRota)%4;
                    } 
                    else {
                        System.out.println("Erreur lors de la sélection de la commande.\nVeuillez choisir le sens de rotation ou quitter avec les commandes suivantes (d/droite/g/gauche/quit)");
                    }
                    break;
                }

                while (true) {
                    System.out.println("Veuillez placer votre pièce (colonnes puis lignes)");
                    String coord = getScannerNext();
                    String columnStr = coord.substring(0, 1);
                    String rowStr = coord.substring(1);
                    int column = columnStr.charAt(0) - 'A';
                    int row = Integer.valueOf(rowStr);
                    int[] newpos = {row, column};
                    if (plateau.deplacer(piece, newpos, rota) == false) {
                        System.out.println("Problème votre pièce rentre en contact avec une autre pièce ou elle sort du plateau de jeu");
                    } 
                    else{
                        break;
                    }
                }
                break;
            }
            
        }
    }
}