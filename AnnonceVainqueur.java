import javax.swing.*;
import java.awt.Font;

public class AnnonceVainqueur extends JFrame {
    public AnnonceVainqueur(String nomVainqueur) {
        setTitle("Résultat du Jeu");
        setSize(300, 200);
        setResizable(false); // Empêcher le redimensionnement
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel message = new JLabel("Le vainqueur est " + nomVainqueur + " !", JLabel.CENTER);
        message.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(message);

        setVisible(true);
    }
}