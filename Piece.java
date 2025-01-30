import java.awt.Graphics;

public abstract class Piece {
    protected int x, y;
    protected boolean estBlanc;

    public Piece(int x, int y, boolean estBlanc) {
        this.x = x;
        this.y = y;
        this.estBlanc = estBlanc;
    }

    public boolean estBlanc() {
        return estBlanc;
    }

    public abstract boolean mouvementValide(int newX, int newY, Plateau plateau);

    public abstract void dessiner(Graphics g, int width, int height);
}