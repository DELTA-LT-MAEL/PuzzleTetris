package view;

import javax.swing.*;

import model.PlateauPuzzle;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameGUI extends JFrame {
    // private int n;
    // private int k;
    // private JPanel buttonPanel;
    private Grille grille;
    private JPanel menu;
    private boolean fin;
    private double score;
    private JLabel labelTitre;
    private JLabel labelCoup;
    private JPanel menuBouton;

    private int fontSize = 25;
    private Font font = new Font("", Font.ITALIC, fontSize);

    public GameGUI(PlateauPuzzle plateau) {
        this.fin = false;
        this.score = 0;
        this.setTitle("Jeu de puzzle");
        this.setLayout(new BorderLayout());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        grille = new Grille(plateau);
        System.out.println(grille.getet());

        menu = new JPanel(new GridLayout(3, 1));
        menuBouton = new JPanel(new GridLayout(2, 1));

        this.labelTitre = new JLabel("Score : " + this.score);
        this.labelTitre.setFont(font);

        this.labelCoup = new JLabel("bonjour");

        // changement de la police d'écriture
        Font font = new Font("Arial", Font.PLAIN, 22);
        labelCoup.setFont(font);

        this.labelCoup.setFont(font);
        this.labelTitre.setFont(font);

        menu.add(this.labelTitre);
        menu.add(this.labelCoup);
        menu.add(menuBouton);
        createButtons();
        menu.setBackground(new Color(130, 165, 207));
        this.add(grille, BorderLayout.WEST);
        this.add(menu, BorderLayout.EAST);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                newtaille();
            }
        });

        addWindowStateListener(e -> {
            newtaille();
        });

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            /* On veut demander la confirmation afin de pouvoir quitter le jeu ou pas */
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(GameGUI.this, "Souhaitez-vous quitter notre jeu ?",
                        "Quitter ?", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                newtaille();
            }
        });

    }

    public Grille getGrille() {
        return grille;
    }

    private void createButtons() {
        JButton button = new JButton("Quitter le jeu ");
        JButton button2 = new JButton("Valider");

        button.setPreferredSize(new Dimension(250, 50));
        button2.setPreferredSize(new Dimension(250, 50));
        // largeur inutile car elle se fait par rapport à la largeur du menu donc ne pas
        // la changer

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(GameGUI.this, "Souhaitez-vous quitter notre jeu ?",
                        "Quitter ?", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            dispose();
                        }
                    });
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("valider");
                setFin();
            }

        });

        menuBouton.add(button2);
        menuBouton.add(button);

    }

    public boolean getFin() {
        return this.fin;
    }

    public void setFin() {
        this.fin = true;
        System.out.println("Apres le clic " + this.fin);
    }

    public void setScore(double score) {
        this.labelTitre.setText("Score : " + score);
        ;
    }

    public void setCoup(int coup) {
        this.labelCoup.setText("Nombre de coups restants: " + coup);
    }

    private void newtaille() {
        int largeurFenetre = getWidth();

        int largeurPanel1 = (3 * largeurFenetre) / 4;
        int largeurPanel2 = largeurFenetre - largeurPanel1;

        grille.setPreferredSize(new Dimension(largeurPanel1, getHeight()));
        menu.setPreferredSize(new Dimension(largeurPanel2, getHeight()));

        revalidate();
        repaint();
    }
}