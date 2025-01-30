import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class InterfaceGraphique extends JFrame {
    private JLabel tourLabel;
    private JLabel pionsCapturesLabel;
    private JLabel timerLabel;
    private Plateau plateau;
    private String joueur1Nom;
    private String joueur2Nom;
    private boolean tourBlanc;
    private int tempsParTour;
    private Timer timer;
    private int tempsRestant;

    public InterfaceGraphique(String nom1, String couleur1, String nom2, String couleur2, int tempsParTour) {
        this.joueur1Nom = nom1;
        this.joueur2Nom = nom2;
        this.tourBlanc = true;
        this.tempsParTour = tempsParTour;

        setTitle("Jeu de Dames");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setResizable(false); // Empêcher le redimensionnement
        setLayout(new BorderLayout());

        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new BorderLayout());
        welcomePanel.setBackground(new Color(100, 150, 255));
        JLabel welcomeLabel = new JLabel("Bienvenue sur notre jeu Checkers game", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(1, 4, 20, 10));
        infoPanel.setBackground(new Color(245, 245, 245));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        tourLabel = new JLabel("Tour: " + joueur1Nom);
        pionsCapturesLabel = new JLabel("Pions captur\u00E9s: 0 - 0");
        timerLabel = new JLabel("Temps restant: " + (tempsParTour > 0 ? tempsParTour + "s" : "Illimit\u00E9"));

        Font labelFont = new Font("Segoe UI", Font.BOLD, 18);
        tourLabel.setFont(labelFont);
        pionsCapturesLabel.setFont(labelFont);
        timerLabel.setFont(labelFont);

        tourLabel.setForeground(new Color(50, 50, 50));
        pionsCapturesLabel.setForeground(new Color(50, 50, 50));
        timerLabel.setForeground(new Color(50, 50, 50));

        infoPanel.add(tourLabel);
        infoPanel.add(pionsCapturesLabel);
        infoPanel.add(timerLabel);

        JButton menuButton = new JButton("Menu");
        menuButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        menuButton.setBackground(new Color(100, 150, 255));
        menuButton.setForeground(Color.WHITE);
        menuButton.setFocusPainted(false);
        menuButton.setBorder(BorderFactory.createLineBorder(new Color(80, 130, 255), 2));
        menuButton.setPreferredSize(new Dimension(150, 40));

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem recommencerItem = new JMenuItem("Recommencer");
        JMenuItem quitterItem = new JMenuItem("Quitter");
        JMenuItem continuerItem = new JMenuItem("Continuer");
        JMenuItem pauseItem = new JMenuItem("Pause");
        JMenuItem historiqueItem = new JMenuItem("Historique");

        recommencerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuPrincipal();
            }
        });

        quitterItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        continuerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour continuer le jeu
            }
        });

        pauseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour mettre le jeu en pause
            }
        });

        historiqueItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action pour afficher l'historique des joueurs
            }
        });

        popupMenu.add(recommencerItem);
        popupMenu.add(quitterItem);
        popupMenu.add(continuerItem);
        popupMenu.add(pauseItem);
        popupMenu.add(historiqueItem);

        menuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                popupMenu.show(menuButton, e.getX(), e.getY());
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.add(menuButton);

        add(welcomePanel, BorderLayout.NORTH);
        add(infoPanel, BorderLayout.SOUTH);
        add(buttonPanel, BorderLayout.EAST);

        plateau = new Plateau(couleur1, couleur2, this);
        add(plateau, BorderLayout.CENTER);

        if (tempsParTour > 0) {
            demarrerMinuterie();
        }

        setVisible(true);
    }

    private void demarrerMinuterie() {
        tempsRestant = tempsParTour;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    tempsRestant--;
                    timerLabel.setText("Temps restant: " + tempsRestant + "s");
                    if (tempsRestant <= 0) {
                        changerTour();
                    }
                });
            }
        }, 1000, 1000);
    }

    public void changerTour() {
        if (timer != null) {
            timer.cancel();
        }
        tourBlanc = !tourBlanc;
        tourLabel.setText("Tour: " + (tourBlanc ? joueur1Nom : joueur2Nom));
        if (tempsParTour > 0) {
            demarrerMinuterie();
        }
    }

    public void mettreAJourPionsCaptures(int captures1, int captures2) {
        pionsCapturesLabel.setText("Pions captur\u00E9s: " + captures1 + " - " + captures2);
    }

    public boolean estTourBlanc() {
        return tourBlanc;
    }

    public void verifierVainqueur() {
        int pionsBlancs = 0;
        int pionsNoirs = 0;

        for (int i = 0; i < plateau.getTaille(); i++) {
            for (int j = 0; j < plateau.getTaille(); j++) {
                Piece piece = plateau.getCase(i, j).getPiece();
                if (piece != null) {
                    if (piece.estBlanc()) {
                        pionsBlancs++;
                    } else {
                        pionsNoirs++;
                    }
                }
            }
        }

        if (pionsBlancs == 0) {
            afficherVainqueur(joueur2Nom);
        } else if (pionsNoirs == 0) {
            afficherVainqueur(joueur1Nom);
        }
    }

    public void afficherVainqueur(String nomVainqueur) {
        new AnnonceVainqueur(nomVainqueur);
        dispose();
    }
}