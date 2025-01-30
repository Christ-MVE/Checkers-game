import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {
    private JTextField joueur1Nom;
    private JTextField joueur2Nom;
    private JComboBox<String> joueur1Couleur;
    private JComboBox<String> joueur2Couleur;
    private JComboBox<String> tempsParTour;

    public MenuPrincipal() {
        setTitle("Menu Principal - Jeu de Dames");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setResizable(false); // Empêcher le redimensionnement
        setLayout(new GridLayout(7, 2));

        add(new JLabel("Nom du Joueur 1:"));
        joueur1Nom = new JTextField();
        add(joueur1Nom);

        add(new JLabel("Couleur du Joueur 1:"));
        joueur1Couleur = new JComboBox<>(new String[] { "Blanc", "Noir" });
        add(joueur1Couleur);

        add(new JLabel("Nom du Joueur 2:"));
        joueur2Nom = new JTextField();
        add(joueur2Nom);

        add(new JLabel("Couleur du Joueur 2:"));
        joueur2Couleur = new JComboBox<>(new String[] { "Blanc", "Noir" });
        add(joueur2Couleur);

        add(new JLabel("Temps par tour:"));
        tempsParTour = new JComboBox<>(new String[] { "Sans temps", "30s", "60s", "90s", "120s" });
        add(tempsParTour);

        JButton commencer = new JButton("Commencer");
        commencer.setBackground(Color.GREEN);
        commencer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nom1 = joueur1Nom.getText();
                String nom2 = joueur2Nom.getText();
                String couleur1 = (String) joueur1Couleur.getSelectedItem();
                String couleur2 = (String) joueur2Couleur.getSelectedItem();
                String tempsSelectionne = (String) tempsParTour.getSelectedItem();

                if (nom1.isEmpty() || nom2.isEmpty() || couleur1.equals(couleur2)) {
                    JOptionPane.showMessageDialog(null,
                            "Veuillez entrer des noms valides et choisir des couleurs différentes pour chaque joueur.");
                } else {
                    boolean temps = !tempsSelectionne.equals("Sans temps");
                    int tempsParTour = temps ? Integer.parseInt(tempsSelectionne.replace("s", "")) : 0;
                    new InterfaceGraphique(nom1, couleur1, nom2, couleur2, tempsParTour);
                    dispose();
                }
            }
        });
        add(commencer);

        JButton regles = new JButton("R\u00E8gles");
        regles.setBackground(Color.YELLOW);
        regles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Regle();
            }
        });
        add(regles);

        JButton aide = new JButton("Aide");
        aide.setBackground(Color.CYAN);
        aide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Aide pour le jeu de dames:\n\n- Cliquez sur une case pour sélectionner un pion.\n- Cliquez sur une case vide pour déplacer le pion sélectionné.\n- Les captures se font en sautant par-dessus un pion adverse.");
            }
        });
        add(aide);

        setVisible(true);
    }
}