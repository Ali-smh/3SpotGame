
/**
 * La classe Partie représente une partie de jeu avec deux joueurs et un plateau de jeu.
 */
public class Partie {
	private Joueur joueurUn, joueurDeux;
	private Piece pieceRouge, pieceNeutre, pieceBleue;
	private Plateau plateau;
	
	public Partie() {
		pieceRouge = new Piece('R', 0, 1, Orientation.HORIZONTAL);
		pieceNeutre = new Piece('W', 1, 1, Orientation.HORIZONTAL);
		pieceBleue = new Piece('B', 2, 1, Orientation.HORIZONTAL);
		joueurUn = new Joueur(pieceRouge);
		joueurDeux = new Joueur(pieceBleue);
		plateau = new Plateau(3, 3);
		plateau.ajouter(pieceRouge);
		plateau.ajouter(pieceNeutre);
		plateau.ajouter(pieceBleue);
	}
	
	/**
     * Vérifie si la partie est terminée en fonction des points des joueurs.
     * @return vrai si la partie est terminée, sinon faux.
     */
	public boolean estTerminee() {
		boolean ok = false;
		if(joueurUn.points == Joueur.MAX_POINTS && joueurDeux.points < Joueur.MAX_POINTS/2 || joueurDeux.points == Joueur.MAX_POINTS && joueurUn.points < Joueur.MAX_POINTS/2) {
			ok = true;
		}else if(joueurUn.points == Joueur.MAX_POINTS && joueurDeux.points >= Joueur.MAX_POINTS/2 || joueurDeux.points == Joueur.MAX_POINTS && joueurUn.points >= Joueur.MAX_POINTS/2) {
			ok = true;
		}
		return ok;
	}
	
	/**
     * Affiche le gagnant de la partie.
     */
	public void afficherGagnant() {
		char gagnant = 'N';
		
		if(joueurUn.points == Joueur.MAX_POINTS && joueurDeux.points < Joueur.MAX_POINTS/2) gagnant = joueurDeux.piece.getCouleur();
		else gagnant = joueurUn.piece.getCouleur();

		System.out.println("Le gagnant est joueur "+gagnant);
	}
	
	/**
     * Affiche les points des deux joueurs.
     */
	public void afficherPoints() {
		System.out.println("Joueur R: "+joueurUn.points+" points | Joueur B: "+joueurDeux.points+" points");
	}
	
	public Plateau getPlateau() {return plateau;}
	
	public Piece getPieceNeutre() {return pieceNeutre;}
	
	public Joueur getJoueurUn() {return joueurUn;}
	
	public Joueur getJoueurDeux() {return joueurDeux;}
}
