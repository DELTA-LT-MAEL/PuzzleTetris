package tests;

import java.awt.event.KeyEvent;
//import java.awt.event.MouseEvent;
import controller.GameControl;
import view.Grille;
import java.util.ArrayList;
import model.*;

public class TestController implements TestMVC {

    public static void launchTest() {

        try {

            int largeur = 5;
            int longueur = 5;
            ArrayList<PiecesPuzzle> pieces = new ArrayList<>();

            PlateauPuzzle mockPlateau = new PlateauPuzzle(largeur, longueur, pieces);
            Grille mockGrille = new Grille(mockPlateau);
            GameControl gameControl = new GameControl(mockGrille);

            // MouseEvent mockMouseEvent = new MouseEvent(mockGrille,
            // MouseEvent.MOUSE_CLICKED, 0, 0, 50, 50, 1, false);
            // gameControl.cliqueSouris(mockMouseEvent);

            assert gameControl.getX() == 2 : "Error in testCliqueSouris: getX()";
            assert gameControl.getY() == 4 : "Error in testCliqueSouris: getY()";

            KeyEvent mockKeyEventA = new KeyEvent(mockGrille, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_A, 'A');
            gameControl.keyPressed(mockKeyEventA);

            assert gameControl.getPos()[2] == -1 : "Error in testKeyPressed: getPos()[2]";

            KeyEvent mockKeyEventRight = new KeyEvent(mockGrille, KeyEvent.KEY_PRESSED, 0, 0, KeyEvent.VK_RIGHT,
                    KeyEvent.CHAR_UNDEFINED);
            gameControl.keyPressed(mockKeyEventRight);

            assert gameControl.getPos()[1] == 1 : "Error in testKeyPressed: getPos()[1]";
            System.out.println("GameControl: All tests passed.");

            System.out.println("Controller: All tests passed.");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
