import java.awt.Graphics;
import java.awt.Color;

public class SuperDame extends Piece {

    public SuperDame(int x, int y, boolean estBlanc) {
        super(x, y, estBlanc);
    }

    @Override
    public boolean mouvementValide(int newX, int newY, Plateau plateau) {
        int dx = newX - x;
        int dy = newY - y;
        if (Math.abs(dx) == Math.abs(dy) && Math.abs(dx) > 0) {
            int stepX = dx / Math.abs(dx);
            int stepY = dy / Math.abs(dy);
            int currentX = x + stepX;
            int currentY = y + stepY;
            boolean capture = false;
            Piece pieceCapturee = null;

            while (currentX != newX && currentY != newY) {
                Case currentCase = plateau.getCase(currentX, currentY);
                if (currentCase.getPiece() != null) {
                    if (capture || currentCase.getPiece().estBlanc() == estBlanc) {
                        return false;
                    }
                    capture = true;
                    pieceCapturee = currentCase.getPiece();
                }
                currentX += stepX;
                currentY += stepY;
            }

            if (capture && pieceCapturee != null) {
                int captureX = x + stepX;
                int captureY = y + stepY;
                Case captureCase = plateau.getCase(captureX, captureY);
                captureCase.setPiece(null);
                plateau.capturerPiece(pieceCapturee);
            }

            x = newX;
            y = newY;
            return true;
        }
        return false;
    }

    @Override
    public void dessiner(Graphics g, int width, int height) {
        Color couleurSuperDame = estBlanc ? Color.CYAN : Color.MAGENTA;
        g.setColor(couleurSuperDame);
        g.fillOval(10, 10, width - 20, height - 20);
        g.setColor(Color.YELLOW);
        g.drawString("♕", width / 2 - 10, height / 2 + 10);
    }
}