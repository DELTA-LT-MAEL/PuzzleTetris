package controller.player;

import model.*;
import java.util.ArrayList;

import controller.*;
import view.*;


public class HumanGUI extends Human implements ControlGUI {
    private GameControl controller;
    private PlateauPuzzle plateau;
    private GameGUI vue;

    


    public HumanGUI(String name, GameGUI vue, PlateauPuzzle plateau) {
        super(name);
        this.plateau = plateau;
        this.controller = new GameControl(vue.getGrille());
        this.vue = vue;
    }



    @Override
    public GameControl getController() {
        return this.controller;
    }

    /**
     * Renvoie la prochaine entrée du scanner pour obtenir les coordonnées du clic.
     * 
     * @return Les coordonnées du clic sous forme de chaîne de caractères
     */

    public int[] getScannerNextGui() {
        int ligneClic =-1;
        int colonneClic =-1;
        int[] pos = {0,0};
        while (ligneClic < 0 || ligneClic > 20 || colonneClic < 0 || colonneClic > 20 ) { 
            if(vue.getFin()){
                break;
            }
            ligneClic = this.controller.getX();
            colonneClic = this.controller.getY();
            pos[0] = ligneClic;
            pos[1] = colonneClic;

            try {
                Thread.sleep(10); // Suspendre le thread pendant 1 seconde
                } catch (InterruptedException e) {
                e.printStackTrace();
                }
        }
        controller.setNull(); 
        return (pos);
    }


        
    /**
     * Renvoie la pièce correspondant aux coordonnées spécifiées.
     * @param x La coordonnée x
     * @param y La coordonnée y
     * @return La pièce correspondant aux coordonnées spécifiées, ou null si aucune pièce n'est présente
     */
    public PiecesPuzzle getPieceCoord(int[] pos) {
        int x = pos[0];
        int y = pos[1];
        if (x >= 0 && x< 20 && y >= 0 && y < 20) {
            String coul = this.plateau.getCouleur(x,y);
            ArrayList<PiecesPuzzle> tab_colection_piece = plateau.getPieces();
            for (int i = 0; i < tab_colection_piece.size(); i++) {
                if (tab_colection_piece.get(i).getCouleur() == coul) {
                    PiecesPuzzle piece = tab_colection_piece.get(i);
                    if (piece != null) {
                        System.out.println("piece touvée");
                        return piece; 
                    }
                }
            }
        }
        return null;
    }
    /**
     * Effectue le déplacement du joueur en fonction des commandes reçues du contrôleur.
     * @param int x La coordonnée x qui correspond au clic
     * @param int y La coordonnée y qui correspond au clic
     * @return Une chaîne de caractères représentant la nouvelle position et la rotation de la pièce
     */
    public void deplacement(PiecesPuzzle piece, int[] newPos) {
        int[] pos = piece.getPos();
        int[] newP = {pos[0]+newPos[0]  , pos[1]+newPos[1] };
        
        if (newPos[0]!=0 || newPos[1]!= 0 || newPos[2] != 0) {
            plateau.deplacer(piece, newP, newPos[2]);
            this.vue.getGrille().repaint();
            controller.setNullPos();
        }
        
    }

    /**
     * Place la pièce sur le plateau en utilisant l'interface graphique.
     * 
     * @param plateau Le plateau de jeu
     * @return Une chaîne de caractères représentant la pièce, sa nouvelle position et sa rotation
     */
    public void placePieceGUI(PlateauPuzzle plateau) {

        controller.setPause(false);
        PiecesPuzzle piece =null;

        while (piece == null) {
            if(vue.getFin()){
                break;
            }
            int [] coord = getScannerNextGui();
            System.out.println(coord);
            piece = this.getPieceCoord(coord);
        }
        controller.setStartRecording(true);


        while(controller.getPause() == false){
            if(vue.getFin()){
                break;
            }
            this.deplacement(piece,controller.getPos() );
            try {
                Thread.sleep(100); // Suspendre le thread pendant 1 seconde
                } catch (InterruptedException e) {
                e.printStackTrace();
                }


        }
        controller.setStartRecording(false);
        
    }

}