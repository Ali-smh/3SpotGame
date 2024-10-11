import java.util.ArrayList;
import java.util.Iterator;

public class Plateau {

	private int largeur, hauteur;
	private ArrayList<Piece> pieces;
	
	
	/**
     * Constructeur de la classe Plateau.
     * @param l la largeur du plateau.
     * @param h la hauteur du plateau.
     */
	public Plateau(int l, int h) {
		assert(l > 0 && h > 0);
		largeur = l; hauteur = h;
		pieces = new ArrayList<Piece>();
	}
	
	/**
     * Ajoute une pièce au plateau.
     * @param p la pièce à ajouter.
     */
	public void ajouter(Piece p) {pieces.add(p);}
	
	
	/**
     * Retourne la pièce occupant une position spécifique sur le plateau.
     * @param x la coordonnée x de la position.
     * @param y la coordonnée y de la position.
     * @return la pièce occupant la position spécifiée, ou null si la position est vide.
     */
	public Piece occupant(int x, int y) {
		for(Piece p : pieces) {
			if(p.occupe(x, y)) return p;
		}
		return null;
	}
	
	
	public int getLargeur() { return largeur;}
	
	public int getHauteur() { return hauteur;}
	
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("* * * * * * * * * * * * *\n");
		for(int x = 0; x < largeur; ++x) {
			res.append("*	*	*	*\n");
			for(int y = 0; y < hauteur; ++y) {
				Piece occupant = occupant(x, y);
				if(occupant == null && y == 2) res.append("*   O   ");
				else if(occupant == null) res.append("*       ");
				else res.append("*   "+occupant.getCouleur()+"   ");
			}
			res.append("*");
			res.append("\n");
			res.append("*	*	*	*\n");
			res.append("* * * * * * * * * * * * *\n");
		}
		return res.toString();
	}
}
