import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		Partie partie = new Partie();
		
		while(!partie.estTerminee()) {
			partie.getJoueurUn().jouer(partie);
			partie.getJoueurDeux().jouer(partie);
			partie.afficherPoints();
		}
		partie.afficherGagnant();
		
	}
}
