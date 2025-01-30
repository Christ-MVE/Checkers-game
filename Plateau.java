import java.awt.*;
import javax.swing.*;

public class Plateau extends JPanel {
    private final int TAILLE = 8;
    private Case[][] cases = new Case[TAILLE][TAILLE];
    private Case caseSelectionnee;
    private InterfaceGraphique interfaceGraphique;
    private int capturesBlanc = 0;
    private int capturesNoir = 0;

    public Plateau(String couleur1, String couleur2, InterfaceGraphique interfaceGraphique) {
        this.interfaceGraphique = interfaceGraphique;
        setLayout(new GridLayout(TAILLE, TAILLE));
        initialiserPlateau(couleur1, couleur2);
    }

    private void initialiserPlateau(String couleur1, String couleur2) {
        Color couleurBlanc = couleur1.equals("Blanc") ? new Color(220, 220, 220) : new Color(139, 69, 19);
        Color couleurNoir = couleur2.equals("Blanc") ? new Color(220, 220, 220) : new Color(139, 69, 19);

        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                cases[i][j] = new Case((i + j) % 2 == 0 ? couleurBlanc : couleurNoir, i, j);
                add(cases[i][j]);
            }
        }
        for (int i = 0; i < TAILLE; i++) {
            if (i % 2 == 0) {
                cases[1][i].setPiece(new Pion(1, i, false));
                cases[5][i].setPiece(new Pion(5, i, true));
                cases[7][i].setPiece(new Pion(7, i, true));
            } else {
                cases[0][i].setPiece(new Pion(0, i, false));
                cases[2][i].setPiece(new Pion(2, i, false));
                cases[6][i].setPiece(new Pion(6, i, true));
            }
        }
    }

    public Case getCase(int ligne, int colonne) {
        return cases[ligne][colonne];
    }

    public void caseCliquee(Case caseCliquee) {
        if (caseSelectionnee == null) {
            if (caseCliquee.getPiece() != null
                    && caseCliquee.getPiece().estBlanc() == interfaceGraphique.estTourBlanc()) {
                caseSelectionnee = caseCliquee;
            }
        } else {
            Piece piece = caseSelectionnee.getPiece();
            if (piece.mouvementValide(caseCliquee.getLigne(), caseCliquee.getColonne(), this)) {
                caseCliquee.setPiece(piece);
                caseSelectionnee.setPiece(null);
                caseSelectionnee = null;
                if ((piece.estBlanc() && caseCliquee.getLigne() == 0)
                        || (!piece.estBlanc() && caseCliquee.getLigne() == 7)) {
                    caseCliquee.setPiece(
                            new SuperDame(caseCliquee.getLigne(), caseCliquee.getColonne(), piece.estBlanc()));
                }
                interfaceGraphique.changerTour();
                interfaceGraphique.verifierVainqueur();
            }
        }
        repaint();
    }

    public void capturerPiece(Piece piece) {
        if (piece != null) {
            if (piece.estBlanc()) {
                capturesNoir++;
            } else {
                capturesBlanc++;
            }
            interfaceGraphique.mettreAJourPionsCaptures(capturesBlanc, capturesNoir);
        }
    }

    public int getTaille() {
        return TAILLE;
    }
}