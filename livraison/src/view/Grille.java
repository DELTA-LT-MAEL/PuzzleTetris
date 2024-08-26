package view;

import javax.swing.*;



import java.awt.*;

import model.PlateauPuzzle;


public class Grille extends JPanel {
    private Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = (int) size.getWidth() / 2;

    private static final int lignes = 20;
    private static final int col = 20;
    private int taille; 
    private String[][] etatCases;
    private PlateauPuzzle jeu;

    /**
     * Méthode constructeur de la class Grille
     * 
     */
    public Grille(PlateauPuzzle jeu) {
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension((lignes + 1) * taille + 2, (col + 1) * taille + 2));
        this.jeu = jeu;
        this.taille= width / 20;
        etatCases = this.jeu.getTableau();// [lignes + 1][col + 1]

        


    }

    /**
     * Renvoie la taille de la Grille.
     *
     * @return La taille de la Grille.
     */
    public int GetTaille() {
        return this.taille;
    }
    public PlateauPuzzle getPlateau(){
        return this.jeu;
    }

    public String[][] getet() {
        return this.etatCases;
    }

    /**
     * Méthode permettant l'affichage de la grille
     * 
     * @param g
     * 
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < jeu.getLongueur(); i++) {
            for (int j = 0; j < jeu.getLargeur(); j++) {

                int x = j * taille + 100;
                int y = i * taille + 10;
                if (i >= 0 && j >= 0) {
                    if (etatCases[i][j] != null) {
                        for (int k = 0; k < jeu.getPieces().size(); k++) {
                            if (etatCases[i][j].equals(jeu.getPieces().get(k).getCouleur())) {
                                Color color = Color.decode(jeu.getPieces().get(k).getCouleur());
                                g.setColor(color);
                            }
                        }
                        g.fillRect(x, y, taille, taille);
                    } else {
                        g.setColor(Color.WHITE);
                        g.fillRect(x, y, taille, taille);
                    }
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, taille, taille);
                }

                /*
                 * if (i == 0 && j > 0) {
                 * g2d.drawString(String.valueOf((char) (j + 64)), taille * j + taille / 3,
                 * taille / 2 + 6);
                 * }
                 * if (j == 0 && i > 0) {
                 * g2d.drawString("" + i, x + taille / 3, y + taille / 2 + 6);
                 * }
                 */

            }

        }
    }
}