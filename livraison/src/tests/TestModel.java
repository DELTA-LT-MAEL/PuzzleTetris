package tests;

import model.*;
import java.util.ArrayList;

public class TestModel implements TestMVC {

    public static void launchTest() {
        try {
            // PiecesPuzzle
            int[] pos1 = {0, 0};
            Boolean[][] forme1 = {
                    {true, true},
                    {true, true}
            };
            String id1 = "1";
            String couleur1 = "Red";

            PiecesPuzzle puzzlePiece1 = new PiecesPuzzle(pos1, forme1, id1, couleur1);

            assert puzzlePiece1.getId().equals(id1) : "Error in getId()";
            assert puzzlePiece1.getCouleur().equals(couleur1) : "Error in getCouleur()";
            assert puzzlePiece1.getLongueur() == 2 : "Error in getLongueur()";
            assert puzzlePiece1.getLargeur() == 2 : "Error in getLargeur()";
            assert puzzlePiece1.getForme() == forme1 : "Error in getForme()";
            assert puzzlePiece1.getPos() == pos1 : "Error in getPos()";

            int[] pos2 = {0, 0};
            Boolean[][] forme2 = {
                    {true, true},
                    {true, false}
            };
            String id2 = "2";
            String couleur2 = "Blue";

            PiecesPuzzle puzzlePiece2 = new PiecesPuzzle(pos2, forme2, id2, couleur2);

            puzzlePiece2.rotationH();
            Boolean[][] expectedRotatedFormeH = {
                    {true, true},
                    {false, true}
            };
            assert areMatricesEqual(puzzlePiece2.getForme(), expectedRotatedFormeH) : "Error in rotationH()";

            puzzlePiece2.rotationAH();
            Boolean[][] expectedRotatedFormeAH = {
                    {true, false},
                    {true, true}
            };
            assert areMatricesEqual(puzzlePiece2.getForme(), expectedRotatedFormeAH) : "Error in rotationAH()";
            System.out.println("PiecesPuzzle: All tests passed.");

            // PlateauPuzzle
            int largeur = 5;
            int longueur = 5;
            ArrayList<PiecesPuzzle> pieces = new ArrayList<>();

            PlateauPuzzle plateau = new PlateauPuzzle(largeur, longueur, pieces);

            assert plateau.getLargeur() == largeur : "Error in getLargeur()";
            assert plateau.getLongueur() == longueur : "Error in getLongueur()";
            assert plateau.getPieces().isEmpty() : "Error in getPieces()";
            assert plateau.getTableau().length == longueur : "Error in getTableau()";
            assert plateau.getTableau()[0].length == largeur : "Error in getTableau()";

            assert plateau.ajouterPiece(puzzlePiece1, plateau.getTableau()) : "Error in ajouterPiece()";

            plateau.supPiece(puzzlePiece1);
            assert plateau.getPieces().isEmpty() : "Error in supPiece()";

            int[] newPos = {2, 2};
            assert plateau.deplacer(puzzlePiece1, newPos, 1) : "Error in deplacer()";

            System.out.println("PlateauPuzzle: All tests passed.");

            System.out.println("Model: All tests passed.");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static boolean areMatricesEqual(Boolean[][] matrix1, Boolean[][] matrix2) {
        if (matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length) {
            return false;
        }

        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[0].length; j++) {
                if (!matrix1[i][j].equals(matrix2[i][j])) {
                    return false;
                }
            }
        }

        return true;
    }
}
