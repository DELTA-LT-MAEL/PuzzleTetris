package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Option extends JFrame {
    private String selectedTaille;
    private String selectedDifficulte;

    public Option() {
        this.setTitle("Options");
        this.setSize(400, 300);
        this.setLayout(new GridLayout(4, 2));

        JLabel taille = new JLabel("Taille de l'écran:");
        JComboBox<String> choixtaille = new JComboBox<>(new String[] { "Petit", "Moyen", "Grand" });

        JLabel dif = new JLabel("Difficulté:");
        JComboBox<String> choixdif = new JComboBox<>(new String[] { "Facile", "Moyen", "Difficile" });

        this.add(taille);
        this.add(choixtaille);
        this.add(dif);
        this.add(choixdif);

        JButton appliquerButton = new JButton("Appliquer");
        appliquerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choix = JOptionPane.showConfirmDialog(
                        Option.this,
                        "Voulez-vous vraiment appliquer les options suivantes?",
                        "Modification",
                        JOptionPane.YES_NO_OPTION);
                if (choix == JOptionPane.YES_OPTION) {

                    selectedTaille = (String) choixtaille.getSelectedItem();
                    selectedDifficulte = (String) choixdif.getSelectedItem();
                    System.out.print(selectedTaille + " et " + selectedDifficulte);
                    // game.newoption(selectedTaille, selectedDifficulte);
                    dispose();
                }
            }
        });

        JButton annulerButton = new JButton("Annuler");
        annulerButton.setPreferredSize(new Dimension(200, 50));
        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choix = JOptionPane.showConfirmDialog(
                        Option.this,
                        "Voulez-vous vraiment quitter les options sans appliquer de modifications?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (choix == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });
        JButton recommencer = new JButton("Recommencer");
        recommencer.setPreferredSize(new Dimension(200, 50));
        recommencer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choix = JOptionPane.showConfirmDialog(
                        Option.this,
                        "Souhaitez-vous recommencer votre partie?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (choix == JOptionPane.YES_OPTION) {
                    dispose();
                    // ajouter un appel à la classe jeuAssemblage qui réinitialise la partie
                }
            }
        });

        this.add(appliquerButton);
        this.add(annulerButton);
        this.add(recommencer);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
