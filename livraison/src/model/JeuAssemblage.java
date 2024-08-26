package model;

import java.util.*;



import view.ViewTerminal;
import view.GameGUI;

import controller.player.Human;
import controller.player.HumanGUI;
import controller.player.Bot;
import java.io.OutputStream;
import java.io.PrintStream;

public class JeuAssemblage {

    public PlateauPuzzle plateau;
    public PlateauPuzzle plateauBase;
    public int vue;
    public int nbrPiece;
    public ArrayList<Boolean[][]>formes;
    private Scanner scanner;
    private ArrayList<String> listeID;
    /**
     * Constructeur de l'assemblage du jeu et dispose de manière aléatoire les pieces dans le tableau
     * 
     * @param larg La largeur du tableau
     * @param log La longueur du tableau
     * @param nbrPiece Le nombre de pièce dans le tableau
     * 
     * 
     */
    public JeuAssemblage(int larg, int log, int nbrPiece){
        Boolean[][][] listeFormes = {{{true,true,true},{true,false,true}}, {{true,true},{true,false}}, {{true,true,true}}, {{true,true,true},{true,false,false}}, {{true,true,true},{false,true,false},{false,true,false}}, {{true,false,false,true},{true,false,false,true},{true,false,false,true},{true,true,true,true}},{{true,true,true},{false,false,true}},{{true}} };
        listeID = new ArrayList<>();
        this.nbrPiece = nbrPiece;
        ArrayList<PiecesPuzzle> liste1 = new ArrayList<PiecesPuzzle>();
        ArrayList<PiecesPuzzle> liste2 = new ArrayList<PiecesPuzzle>();
        
        this.plateau = new PlateauPuzzle(larg, log, liste1 );
        this.plateauBase = new PlateauPuzzle(larg, log, liste2 );


        Random rand = new Random();

        for(int i =0 ; i < nbrPiece;i++){
            Boolean[][] forme = listeFormes[(int)Math.floor(Math.random() * (listeFormes.length ))];
            int[] pos = {rand.nextInt(larg),rand.nextInt(log)};
            PiecesPuzzle p = new PiecesPuzzle(pos, forme, this.randomId(), this.randomHexaGenerator());
            for(int j=0; j< rand.nextInt(4); j++){
                p.rotationH();
            }
            
            while(this.plateau.ajouterPiece(p, this.plateau.getTableau()) != true){
                pos[0] = rand.nextInt(larg) ;
                pos[1]= rand.nextInt(log);
                p.setPos(pos);
            }
            this.plateauBase.ajouterPiece(p, this.plateauBase.getTableau());
        }

        this.scanner = new Scanner(System.in);



    }
    /**Fonction retournant un string unique servant d'id aux pièces
    * @return String id
    */
    public String randomId() {
        String id = "";
        Random rand = new Random();
        boolean unique = false;
        while (!unique) {
            char letter = (char) (rand.nextInt(26) + 'A');
            while(letter == 'X'){
                letter = (char) (rand.nextInt(26) + 'A');
            }
            id = String.valueOf(letter);
            if (!isIdExists(id)) {
                unique = true;
            }
        }
        listeID.add(id);
        return id;
    }
    /**Fonction vérifiant si un String id donné existe deja ou non
    * @param id L'id à vérifier
    * @return boolean si il existe deja
    */
    private boolean isIdExists(String id) {

        if (listeID.contains(id)) {
             return true;
         }
         return false;
    }

    /**Fonction renvoyant un string qui sera une couleur aléatoire au format hexadecimal
    * @return string la couleur au format hexa
    */
    public String randomHexaGenerator(){
        String hexa = "#";
        Random rand = new Random();
        int i = 0;
        while(i < 6){
            int n = rand.nextInt(15);
            if(n < 10){
                hexa += n;
            }else{
                switch(n){
                    case 10:
                        hexa += "A";
                        break;
                    case 11:
                        hexa += "B";
                        break;
                    case 12:
                        hexa += "C";
                        break;
                    case 13:
                        hexa += "D";
                        break;
                    case 14:
                        hexa += "E";
                        break;
                }
            }
            i++;
        }
        return hexa;
    }
    /**Fonction retournant le resultat d'un scanner
    * @return commander un String
    */
    public String getScannerNext() {
        String commande = scanner.next();
        return commande;
    }

    /**Fonction retournant le plateau actuel du jeu
    * @return plateau le plateau du jeu
    */
    public PlateauPuzzle getPlateauPuzzle(){
        return this.plateau;
    }


    /**Fonction retournant le résultat du jeu en fonxtion du périmetre total des pièces
    * @return le résultat aroundis à 2 décimals
    */
    public double resultat() {
        double perimetreBase = this.plateauBase.perimetre();
        double perimetreActuel = this.plateau.perimetre();
        double resultat =((perimetreActuel/perimetreBase)*100);

        return Math.round(resultat*100)/100;
    }

    /**Fonction qui lance la boucle de jeu en console
    * @return le résultat aroundis à 2 décimals
    */
    public double jeuHumainConsole(String nom){
        
        ViewTerminal vue = new ViewTerminal(this.plateau);
        
        Human joueur = new Human(nom);
        vue.situationToStringPlateau();
        System.out.println("Voulez-vous déplacer une pièce ?(o/oui/n/non)");
        String jouer = getScannerNext();
        while(jouer.equals("o") !=true && jouer.equals("n")!=true && jouer.equals("oui") !=true && jouer.equals("non")!=true ){
            System.out.println("Veulliez rentrer une reponse valide(o/oui/n/non) : ");
            jouer = getScannerNext();
        }
        while(jouer.equals("o") || jouer.equals("oui")){
            joueur.placePiece(this.plateau);
            vue.situationToStringPlateau();
            System.out.println("Voulez-vous déplacer une pièce ?(o/oui/n/non)");
            jouer = getScannerNext();
            int j =0;
            while(j < this.nbrPiece || (jouer.equals("o") !=true && jouer.equals("n")!=true && jouer.equals("oui") !=true && jouer.equals("non")!=true) ){
                System.out.println("Veulliez rentrer une reponse valide(o/oui/n/non) : ");
                jouer = getScannerNext();
                j++;
            }
        }
        return this.resultat();
    }


    /**Fonction qui lance la boucle de jeu en interface
    * @return le résultat aroundis à 2 décimals
    */
    public double jeuHumainInterface(String nom){
        
        GameGUI vue = new GameGUI(this.plateau);
        HumanGUI joueur = new HumanGUI(nom, vue, this.plateau);
        int i =0;
        
        while(i < this.nbrPiece && (vue.getFin() == false)){
            vue.setCoup(this.nbrPiece-i);
            joueur.placePieceGUI(this.plateau);
            i++;
        }
        vue.setCoup(0);
        vue.setScore(100-this.resultat());
        return this.resultat();

    }
    /**Fonction qui lance la boucle de jeu pour le bot en console
    * @return le résultat aroundis à 2 décimals
    */
    public double jeuBot(String nom) {
        ViewTerminal vue = new ViewTerminal(this.plateau);
        Bot joueur = new Bot(nom, this.plateau);
        vue.situationToStringPlateau();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(new NullOutputStream()));
        joueur.tryToGetLowestPerimeter();
        System.setOut(originalOut);
        vue.situationToStringPlateau();
        return this.resultat();
    }
    private static class NullOutputStream extends OutputStream {
        @Override
        public void write(int b) {   
        }
    }
}