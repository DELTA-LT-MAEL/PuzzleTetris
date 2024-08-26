package model;


public class PiecesPuzzle {
	private int largeur;
	private int longueur;
	private Boolean[][] forme;
	private String couleur;
	private int[] pos;
	private String id;

	/**
	 * Méthode constructeur de la class PiecesPuzzle
	 * 
	 * @param pos     la position initiale de la piece
	 * @param forme   la matrice dans laquelle sont contenus des booleeans afin de
	 *                donner la forme que la pièce doit prendre
	 * @param couleur qui permettra de donner la couleur de la pièce
	 * @return une pièce de puzzle
	 */
	public PiecesPuzzle(int[] pos, Boolean[][] forme,String id, String couleur) {
		this.pos = pos;
		this.forme = forme;
		this.largeur = this.forme[0].length;
		this.longueur = this.forme.length;
		this.couleur = couleur;
		this.id = id;
	}

	/**
	 * Méthode permettant la rotation d'une PiecesPuzzle
	 * 
	 * @return une pièce de puzzle tournée de 90° dans le sens horaire
	 */
	public void rotationH() {
		Boolean[][] tab = new Boolean[this.largeur][this.longueur];
		for (int i = 0; i < this.longueur; i++) {
			for (int j = 0; j < this.largeur; j++) {
				tab[j][this.longueur - 1 - i] = this.forme[i][j];
			}
		}
		this.forme = tab;
		int temp = this.largeur;
		this.largeur = this.longueur;
		this.longueur = temp;
	}

	/**
	 * Méthode permettant la rotation d'une PiecesPuzzle
	 * 
	 * @return une pièce de puzzle tournée de 90° dans le sens anti-horaire
	 */

	public void rotationAH() {
		Boolean[][] tab = new Boolean[this.largeur][this.longueur];
		for (int i = 0; i < this.longueur; i++) {
			for (int j = 0; j < this.largeur; j++) {
				tab[this.largeur - 1 - j][i] = this.forme[i][j];
			}
		}
		this.forme = tab;
		int temp = this.largeur;
		this.largeur = this.longueur;
		this.longueur = temp;
	}

	/**
	 * Méthode permettant de paramétrer la longueur de la piece
	 * 
	 * @param n la nouvelle valeur de la longueur de la pièce
	 * @ensure le changement de la valeur de longueur
	 */

	public void setlongueur(int n) {
		this.longueur = n;
	}

	/**
	 * Méthode permettant de paramétrer la largeur de la piece
	 * 
	 * @param n la nouvelle valeur de la largeur de la pièce
	 * @ensure le changement de la valeur de largeur
	 */
	public void setlargeur(int n) {
		this.largeur = n;
	}

	/**
	 * Méthode permettant de paramétrer la position de la piece
	 * 
	 * @param pos la nouvelle valeur de la position de la pièce
	 * @ensure le changement de la valeur de la position de la pieces
	 */
	public void setPos( int[] pos) {
		this.pos = pos;
	}

	

	/**
	 * Méthode permettant de retouner la longueur de la piece
	 * 
	 * @return la longueur de la piece
	 */

	public int getLongueur() {
		return this.longueur;
	}
	
	public String getId() {
		return this.id;
	}

	/**
	 * Méthode permettant de retouner la largeur de la piece
	 * 
	 * @return la largeur de la piece
	 */
	public int getLargeur() {
		return this.largeur;
	}

	/**
	 * Méthode permettant de retouner la grille représentant la forme de la piece
	 * 
	 * @return la grille de la forme de la piece
	 */
	public Boolean[][] getForme() {
		return this.forme;
	}

	/**
	 * Méthode permettant de retouner la position de la piece
	 * 
	 * @return la position de la forme de la piece
	 */
	public int[] getPos() {
		return this.pos;
	}

	/**
	 * Méthode permettant de retouner la couleur de la piece
	 * 
	 * @return la couleur de la forme de la piece
	 */
	public String getCouleur() {
		return this.couleur;
	}

	/**
	 * Méthode permettant de print la matrice de la piece
	 * 
	 * @return la position de la matrice de la piece
	 */
	@Override
	public String toString() {
		String piece = "";
		String ligne = "";
		for (int i = 0; i < this.longueur; i++) {
			for (int j = 0; j < this.largeur; j++) {
				if (this.forme[i][j] == true) {
					ligne = ligne + "X";
				} else {
					ligne = ligne + "O";
				}
			}
			piece = piece + "\n" + ligne;
			ligne = "";
		}

		return piece;
	}

}