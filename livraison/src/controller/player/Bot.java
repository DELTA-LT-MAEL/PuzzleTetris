package controller.player;

import model.*;

import java.util.ArrayList;
import java.util.List;



public class Bot {
    private String nom;
    public PlateauPuzzle plateau;

    /**
     * Constructeur de la classe Bot.
     * 
     * @param nom     Le nom du bot.
     * @param plateau Le plateau de jeu.
     */
    public Bot(String nom, PlateauPuzzle plateau) {
        this.nom = nom;
        this.plateau = plateau;
    }


    public String getNom() {
        return this.nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Essaye de faire en sorte que le placement des pièces donnent le périmètre le plus bas.
     * 
     * @return le perimètre le plus bas .
     */
    public List<PiecesPuzzle> tryToGetLowestPerimeter() {
        List<PiecesPuzzle> pieces = new ArrayList<>();
        int perimetreLePlusBas = Integer.MAX_VALUE;
    
        List<PiecesPuzzle> piecesDisponibles = new ArrayList<>(this.plateau.getPieces());
        String[][] copieTableau = copierTableau(this.plateau.getTableau());
    
        for (PiecesPuzzle piece : piecesDisponibles) {
            for (int i = 0; i < this.plateau.getLongueur(); i++) {
                for (int j = 0; j < this.plateau.getLargeur(); j++) {
                    for (int rota = 0; rota < 4; rota++) {
                        int[] pos = {i, j};
                        if (this.plateau.deplacer(piece, pos, rota)) {
                            int perimetreActuel = this.plateau.perimetre();
    
                            if (perimetreActuel < perimetreLePlusBas) {
                                perimetreLePlusBas = perimetreActuel;
                                pieces.clear();
                                pieces.add(piece);
                            } else if (perimetreActuel == perimetreLePlusBas) {
                                pieces.add(piece);
                            }
    
                            
                            this.plateau.setTableau(copieTableau);
                        }
                    }
                }
            }
        }
    
        return pieces;
    }
    
    
    private String[][] copierTableau(String[][] original) {
        String[][] copie = new String[original.length][];
        for (int i = 0; i < original.length; i++) {
            copie[i] = original[i].clone();
        }
        return copie;
    }


}






