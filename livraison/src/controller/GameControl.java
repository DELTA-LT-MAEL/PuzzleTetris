package controller;

import java.awt.event.*;

import view.*;

public class GameControl implements MouseListener, KeyListener {
   private int x;
   private int y;
   private int[] pos; 
   private int taille;
   private Grille grille;
   private Boolean pause;
   private boolean startRecording = false; // Flag to indicate whether recording should start
   
   public GameControl(Grille grille){
      this.taille = grille.GetTaille();
      int[] pos =  {0,0,0};
      this.x = -1;
      this.y = -1;
      this.pos = pos;
      this.grille = grille;
      this.pause = false;
      this.grille.addMouseListener(this);

      this.grille.addKeyListener(this);
      this.grille.setFocusable(true);
      this.grille.requestFocus();
   }
   /**
    * Méthode qui capte le clique de la souris et enregistre la case sur laquelle le clique a été effectué
    */
   public void mouseClicked(MouseEvent e) {
      int x = (e.getX() - 100) / taille;
      int y = (e.getY() - 10) / taille ;
      this.setValues(y, x);
      System.out.println("Case cliqué: ("+y+","+x+")" );
   }

   public void mousePressed(MouseEvent e) {}
   
   public void mouseReleased(MouseEvent e) {}

   public void mouseEntered(MouseEvent e) {}
     
   public void mouseExited(MouseEvent e) {}

   public void keyTyped(KeyEvent e) {}

   public void keyReleased(KeyEvent e) {}

   public void keyPressed(KeyEvent e) {
      
      if (startRecording==true){
         int keyCode = e.getKeyCode();
         switch( keyCode ) {
            
            case KeyEvent.VK_A:
               this.pos[2] = -1;
               System.out.println("A key pressed");
               break;
            
            case KeyEvent.VK_E:
               this.pos[2] = 1;
               System.out.println("E key pressed");
               break;

            case KeyEvent.VK_UP:
              this.pos[0] = -1;
              System.out.println("Up key pressed");
               break;

            case KeyEvent.VK_DOWN:
               this.pos[0] = 1;
               System.out.println("Down key pressed");
               break;

            case KeyEvent.VK_LEFT:
              this.pos[1] = -1;
              System.out.println("Left key pressed");
              break;
          
            case KeyEvent.VK_RIGHT :
               this.pos[1] = 1;
               System.out.println("Right key pressed");
               break;
            
            case KeyEvent.VK_ENTER :
               this.startRecording = false;
               this.pause = true;
               System.out.println("Enter key pressed");
               break;
         }
      }
   }
   
   /**
    * Set the flag to start or stop recording key presses.
    * @param startRecording true to start recording, false to stop recording
    */
   public void setStartRecording(boolean startRecording) {
      this.startRecording = startRecording;
   }
   
  /**
    * Méthode qui renvoie le triplet de modification de la piece
    * @return int[] pos
    * @post result == this.pos
    */
   public int getX(){
      return this.x;
   }

     /**
    * Méthode qui renvoie le triplet de modification de la piece
    * @return int[] pos
    * @post result == this.pos
    */
   public int getY(){
      return this.y;
   }


   /**
    * Méthode qui renvoie le triplet de modification de la piece
    * @return int[] pos
    * @post result == this.pos
    */
   public int[] getPos(){
      return this.pos;
   }

/**
    * Méthode qui renvoie le boolean de pause
    * @return boolean pause
   * @post result == this.pause
    */
   public boolean getPause(){
      return this.pause;
   }

/**
    * Méthode qui renvoie le boolean de pause
    * @return boolean pause
   * @post result == this.pause
    */
   public void setPause(boolean bool){
      this.pause = bool;
   }




   /**
    * Méthode qui change la valeur de this.x et this.y par x et y
    * @param x
    * @param y
    * @ensures this.x == x
    * @ensures this.y == y
    * @throws AssertionError si this.x != x ou this.y != y
    */
   public void setValues(int x, int y){
      this.x = x;
      this.y = y;
      assert this.x != x: "Erreur : mauvaise valeur de x à la fin de la méthode";
      assert this.y != y: "Erreur : mauvaise valeur de y à la fin de la méthode";
   }

   /**
    * Méthode qui remet les valeurs de x et y à -1 pour qu'elles ne soient plus valides
    * @ensures this.x == -1
    * @ensures this.y == -1
    */
   public void setNull(){
        this.x = -1;
        this.y = -1;
   }
      /**
    * Méthode qui remet les valeurs de x et y à -1 pour qu'elles ne soient plus valides
    * @ensures this.x == -1
    * @ensures this.y == -1
    */
   public void setNullPos(){
      this.pos[0] = 0;
      this.pos[1] = 0;
      this.pos[2] = 0;

   }

}
