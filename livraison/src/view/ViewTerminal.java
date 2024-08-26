package view;


import model.*;

public class ViewTerminal implements GameView {
    private PlateauPuzzle plat;

    public ViewTerminal(PlateauPuzzle plateau) {
        this.plat = plateau;
    }

    /*
     * Cette méthode permet d'afficher la liste des pièces pouvant être déplacées
     */
    public void situationToString(/* jeu assemblage j */) {
        situationToStringPlateau();
        String res = "";
        res += "Veuillez choisir une piece parmis les suivantes:";// faire une liste des pièces du jeu

        System.out.println(res);

    }

    /**
     * Cette méthode permet d'afficher le plateau du
     * 
     * @ensure l'affichage sur le terminal d'une grille de longueur et de largeur
     *         définies par la classe PlateauPuzzle
     */
    public void situationToStringPlateau() {

        System.out.print("     ");
        for (char c = 'A'; c <= 'A' + plat.getLargeur() - 1; c++) {
            System.out.print(c + "  ");
        }
        System.out.println();
        System.out.print("    ");
        for (int i = 0; i <= plat.getLargeur() - 1; i++) {
            System.out.print(" - ");
        }

        System.out.println();

        for (int i = 0; i <= this.plat.getLongueur() - 1; i++) {
            if (i < 10)
                System.out.print(" " + i + " |");

            else if (i < 100)
                System.out.print(i + " |");
            else
                System.out.print(i + "|");

            for (int j = 0; j <= this.plat.getLargeur() - 1; j++) {
                if (this.plat.getTableau()[i][j] == null)
                    System.out.print(" X ");
                else {
                    System.out.print(" " + this.plat.getTableauView()[i][j] + " "); // Ligne verticale de la grille
                    // Vous pouvez ajouter ici le contenu de chaque cellule de la grille si
                    // nécessaire
                }

            }
            System.out.println("|");

        }
        System.out.print("    ");
        for (int i = 0; i <= this.plat.getLargeur() - 1; i++) {
            System.out.print(" - ");
        }
        System.out.println();

    }

    /**
     * Cette méthode permet d'afficher la piece
     * 
     * @ensure l'affichage sur le terminal de la matrice de la pièce
     */
    public void situationToStringPiece(/* Piece Puzzle p */) {
        String res = "";
        res += "La piece sélectionnée est :"/* affihcer la piece */ + "elle est possitionné à ";
        /*
         * position de la pièce
         */
        System.err.println(res);
    }

}