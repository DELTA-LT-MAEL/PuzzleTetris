package model;

import java.util.ArrayList;

public class PlateauPuzzle {

    private ArrayList<PiecesPuzzle> pieces;
    private int largeur;
    private int longueur;
    private String[][] tableau;


    	/**
	 * Méthode constructeur de la class PlateauPuzzle
	 * 
	 * @param largeur  la largeur du tableau
	 * @param longueur  la longueur du tableau
	 * @param pieces la liste des pieces du tableau
	 * @return un tableau avec des pieces
	 */
    public PlateauPuzzle(int largeur, int longueur, ArrayList<PiecesPuzzle> pieces) {
        this.longueur = longueur;
        this.largeur = largeur;
        this.pieces = pieces;
        this.tableau = new String[longueur][largeur];
    }

    	/**
	 * Méthode permettant d'ajouter un pixel de couleur  dans le plateau
	 * 
     * @param coul la couleur du pixel
	 * @param i la valeur de la position i du pixel
     * @param j la valeur de la position j du pixel
	 * @ensure met la valeur de la couleur à la position (i,j)
	 */
    public void setCouleurTableau(String coul, int i, int j) {
        this.tableau[i][j] = coul;
    }

    	/**
	 * Méthode permettant de retouner la largeur du tableau
	 * 
	 * @return la largeur du tableau
	 */
    public int getLargeur() {
        return this.largeur;
    }

    public String getCouleur(int x, int y ){
        return this.tableau[x][y];
    }
    public void setTableau(String[][] tab){
        this.tableau = tab;
    }
    public void setPieces(ArrayList<PiecesPuzzle> pieces){
        this.pieces = pieces;
    }


	/**
	 * Méthode permettant de retouner la longueur du tableau
     *
	 * @return la longueur du tableau
	 */
    public int getLongueur() {
        return this.longueur;
    }

    	/**
	 * Méthode permettant de retouner la liste des pieces du tableau
     *
	 * @return la liste des pieces du tableau du tableau
	 */
    public ArrayList<PiecesPuzzle> getPieces() {
        return this.pieces;
    }
    

    /**
	 * Méthode permettant de retouner le tableau
     *
	 * @return le tableau
	 */
    public String[][] getTableau() {
        return this.tableau;
    }
    public String[][] getTableauView() {
        for(int i = 0; i < this.longueur; i++){
            for(int j = 0; j < this.largeur; j++){
                if(this.tableau[i][j] != null){
                    for(int k = 0; k < this.pieces.size(); k++){
                        if(this.tableau[i][j].equals(this.pieces.get(k).getCouleur())){
                            this.tableau[i][j] = pieces.get(k).getId();
                        }
                    }
                }
            }
        }
        return this.tableau;
    }

    /**
	 * Méthode permettant de supprimer une piece dans la liste de pieces
	 * 
	 * @param piece la pièce à supprimer
	 * @ensure la pieces n'est plus dans la liste de piece du tableau
	 */
    public void supPiece(PiecesPuzzle piece){
        this.pieces.remove(piece);
    }

    /**
	 * Méthode permettant d'ajouter une piece
	 * 
	 * @param piece la pièce à ajouter
     * @param tab le tableau dans lequel la piece va être ajouter
     * @return si la piece a été ajouter
	 */

     public Boolean ajouterPiece(PiecesPuzzle piece, String[][] tab) {
        if (collision(piece, tab) == false) {
            int[] pos = piece.getPos();
            Boolean[][] forme = piece.getForme();
            int lon = piece.getLongueur();
            int lar = piece.getLargeur();
            for (int i = 0; i < lon; i++) {
                for (int j = 0; j < lar; j++) {
                    if (forme[i][j] == true) {
                        setCouleurTableau(piece.getCouleur(), pos[0] + i, pos[1] + j);
                    }
                }
            }
            this.pieces.add(piece);
            System.out.println("piece ajouté");
            return true;
        }
        System.out.println("collision");
        return false;
    }
    
   /**
	 * Méthode permettant de vérifier les collision d'une piece dans un tableau
	 * 
	 * @param piece la pièce à ajouter
     * @param tab le tableau dans lequel on regarde les collisions
	 * @return si il y a une collision ou non de la piece dans le tableau
	 */
    public boolean collision(PiecesPuzzle piece, String[][] tab) {
        int[] pos = piece.getPos();
        Boolean[][] forme = piece.getForme();

        if (pos[0]+piece.getLongueur() > this.longueur || pos[0]<0 ) {
            return true;
        }
        if (pos[1]+piece.getLargeur() > this.largeur || pos[1]<0 ) {
            return true;
        }
        for (int i = 0; i < piece.getLongueur(); i++) {
            for (int j = 0; j < piece.getLargeur(); j++) {
                if (forme[i][j] == true && tab[pos[0] + i][pos[1] + j] != null) {
                    return true;
                }
            }
        }
        return false;
    }

       /**
	 * Méthode permettant de déplacer une piece dans un tableau avec une rotation
	 * 
	 * @param piece la pièce à déplacer
     * @param pos la position où la pièce va êtrte déplacer
     * @param rota le nombre de rotation horaire que va faire la pièce
	 * @return si la piece a pu être déplacer sans collision
	 */

    public Boolean deplacer(PiecesPuzzle piece, int[] pos, int rota) {
        int[] posBas = {0,0};
        posBas[0] = piece.getPos()[0];
        posBas[1] = piece.getPos()[1];

        /* Supprime la piece du tableau */
        supPiece(piece);
        for (int i = 0; i < piece.getLongueur(); i++) {
            for (int j = 0; j < piece.getLargeur(); j ++) {
                if(piece.getForme()[i][j] == true){
                    setCouleurTableau(null, (piece.getPos()[0]+i), (piece.getPos()[1] + j));
                }
            }
        }
        
        /*
         * Essaie de placer la piece à la position et rotation donnée
         * dans le tableau 
         */
        
        if(rota<0){
            for (int i = rota; i< 0; i++) {
                piece.rotationAH();
            }
        }
        else{
            for (int i = 0; i < rota; i++) {
                piece.rotationH();
        }
        
        }
        piece.setPos(pos);

        /* Si cela échoue remet la piece à sa place d'origine */
        if (collision(piece, this.tableau) == true) {
            for (int i = rota; i < 4; i++) {
                piece.rotationH();
            }
            piece.setPos(posBas);
            System.out.println("Re-pose en : ()" + piece.getPos()[0] +"," +piece.getPos()[1]+")" );
            ajouterPiece(piece, this.tableau);
            System.out.println("Impossible de déplacer la piece "+ piece.getId());
            return false;
        }

        /*
         * Si cela reussi alors ajouter la pièce
         */
        System.out.println("piece déplacer");
        ajouterPiece(piece, this.tableau);
        return true;
    }

    /**
	 * Méthode permettant de retouner l'aire des pieces dans le tableau
     *
	 * @return l'aire des pieces dans le tableau
	 */
    public int aire() {
        int aire = 0;

        for (int i = 0; i < this.pieces.size(); i++) {
            for (int j = 0; j < this.pieces.get(i).getLongueur(); j++) {
                for (int k = 0; k < this.pieces.get(i).getLargeur(); k++) {
                    if (this.pieces.get(i).getForme()[j][k] == true) {
                        aire += 1;
                    }
                }
            }
        }
        return aire;
    }

        /**
	 * Méthode permettant de retouner le périmetre des pieces dans le tableau
     *
	 * @return le périmetre des pieces dans le tableau
	 */

    public int perimetre() {
        int perimetre = 0;
        for (int i = 0; i < this.longueur; i++) {
            for (int j = 0; j < this.largeur; j++) {
                if (this.tableau[i][j] != null) {

                    if (i > 0) {
                        if (tableau[i - 1][j] == null) {
                            perimetre += 1;
                        }
                    }
                    if (i < this.longueur - 1) {
                        if (tableau[i + 1][j] == null) {
                            perimetre += 1;
                        }
                    }
                    if (j > 0) {
                        if (tableau[i][j - 1] == null) {
                            perimetre += 1;
                        }
                    }
                    if (j < this.largeur - 1) {
                        if (tableau[i][j + 1] == null) {
                            perimetre += 1;
                        }
                    }

                }
            }
        }
        return perimetre;
    }





}
