import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Case extends JPanel {
    private Piece piece;
    private int ligne, colonne;

    public Case(Color couleur, int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
        setBackground(couleur);
        setPreferredSize(new Dimension(80, 80));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Plateau plateau = (Plateau) getParent();
                plateau.caseCliquee(Case.this);
            }
        });
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        repaint();
    }

    public Piece getPiece() {
        return piece;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (piece != null) {
            piece.dessiner(g, getWidth(), getHeight());
        }
    }
}