import javax.swing.*;
import java.awt.Font;

public class Regle extends JFrame {
    public Regle() {
        setTitle("Règles du Jeu de Dames");
        setSize(500, 400);
        setResizable(false); // Empêcher le redimensionnement
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea reglesText = new JTextArea(
                "Règles du jeu de dames:\n\n" +
                        "1. Les pions se déplacent en diagonale.\n" +
                        "2. Les captures se font en sautant par-dessus un pion adverse.\n" +
                        "3. Lorsqu'un pion atteint le côté opposé du plateau,\n" +
                        "   il devient une super dame.\n" +
                        "4. Les super dames peuvent se déplacer en diagonale\n" +
                        "   sur plusieurs cases.\n" +
                        "5. Le joueur qui capture tous les pions adverses ou\n" +
                        "   bloque tous leurs mouvements gagne.");
        reglesText.setEditable(false);
        reglesText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        add(new JScrollPane(reglesText));
        setVisible(true);
    }
}