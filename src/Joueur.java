import java.util.Scanner;

public class Joueur {
	public final static int MAX_POINTS = 12;
	int points;
	Piece piece;
	
	/**
     * Constructeur de la classe Joueur.
     * @param piece la pièce contrôlée par le joueur.
     */
	public Joueur(Piece piece) {
		points = 0; this.piece = piece;
	}
	
	/**
     * Augmente les points du joueur en fonction de la position de sa pièce sur le plateau.
     * @param p la partie en cours.
     */
	public void augmenterPoints(Partie p) {
		for(int i = 0; i < p.getPlateau().getHauteur(); ++i) {
			if(p.getPlateau().occupant(i, 2) == piece) points++;
		}
	}
	
	/**
     * Permet au joueur de jouer son tour.
     * @param p la partie en cours.
     */
	public void jouer(Partie p){
		Scanner entree = new Scanner(System.in);
		
		System.out.println(p.getPlateau().toString());
		piece.afficherDestinations(p.getPlateau());
		
		System.out.println("Joueur "+piece.getCouleur()+" veuillez deplacer votre pièce");
		int destination = entree.nextInt();
		
		while(!piece.estDestinationValide(destination, p.getPlateau())) {
			System.out.println("DESTINATION INVALIDE ! Joueur "+piece.getCouleur()+" veuillez entrer une destination valide");
			destination = entree.nextInt();
		}
		piece.bouger(destination, p.getPlateau());
		
		System.out.println(p.getPlateau().toString());
		p.getPieceNeutre().afficherDestinations(p.getPlateau());
		
		System.out.println("Joueur "+piece.getCouleur()+" veuillez deplacer la pièce neutre");
		destination = entree.nextInt();
		
		while(!p.getPieceNeutre().estDestinationValide(destination, p.getPlateau())) {
			System.out.println("DESTINATION INVALIDE ! Joueur "+piece.getCouleur()+" veuillez entrer une destination valide pour la pièce neutre");
			destination = entree.nextInt();
		}
		p.getPieceNeutre().bouger(destination, p.getPlateau());
		augmenterPoints(p);
		System.out.println(p.getPlateau().toString());
	}
}
