import java.awt.Graphics;
import java.awt.Color;

public class Pion extends Piece {
    public Pion(int x, int y, boolean estBlanc) {
        super(x, y, estBlanc);
    }

    @Override
    public boolean mouvementValide(int newX, int newY, Plateau plateau) {
        int direction = estBlanc ? -1 : 1;
        if ((newX == x + direction) && (Math.abs(newY - y) == 1)) {
            x = newX;
            y = newY;
            return true;
        }
        if ((newX == x + 2 * direction) && (Math.abs(newY - y) == 2)) {
            int milieuX = x + direction;
            int milieuY = (y + newY) / 2;
            Case caseMilieu = plateau.getCase(milieuX, milieuY);
            if (caseMilieu.getPiece() != null && caseMilieu.getPiece().estBlanc() != estBlanc) {
                Piece pieceCapturee = caseMilieu.getPiece();
                caseMilieu.setPiece(null);
                x = newX;
                y = newY;
                plateau.capturerPiece(pieceCapturee);
                return true;
            }
        }
        return false;
    }

    @Override
    public void dessiner(Graphics g, int width, int height) {
        g.setColor(estBlanc ? Color.WHITE : Color.BLACK);
        g.fillOval(10, 10, width - 20, height - 20);
        g.setColor(Color.BLACK);
        g.drawOval(10, 10, width - 20, height - 20);
    }
}