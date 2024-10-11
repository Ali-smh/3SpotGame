import java.util.ArrayList;


/**
 * La classe Piece modélise une pièce sur un plateau de jeu.
 */
public class Piece {
	private char couleur;
	private int x, y; // Position de la pièce sur le plateau
	private Orientation orientation;
	
	
	/**
     * Constructeur de la classe Piece.
     * @param c la couleur de la pièce.
     * @param x la coordonnée x de la position de la pièce sur le plateau.
     * @param y la coordonnée y de la position de la pièce sur le plateau.
     * @param o l'orientation de la pièce.
     */
	public Piece(char c, int x, int y, Orientation o) {
		couleur = c; orientation = o;
		this.x = x; this.y = y;
	}
	
	public char getCouleur() {return couleur;}
	
	
	/**
     * Vérifie si la pièce occupe une position spécifique sur le plateau.
     * @param x la coordonnée x de la position à vérifier.
     * @param y la coordonnée y de la position à vérifier.
     * @return vrai si la pièce occupe la position spécifiée, sinon faux.
     */
	public boolean occupe(int x, int y) {
		boolean res = false;
		
		if(this.x == x && this.y == y) res = true;
		else if(orientation == Orientation.HORIZONTAL && this.x == x && this.y == y-1) res = true;
		else if(orientation == Orientation.VERTICAL && this.x-1 == x && this.y == y) res = true;
		else res = false;
		
		return res;
	}
	
	
	/**
     * Déplace la pièce vers une destination spécifiée sur le plateau.
     * @param numDest le numéro de la destination.
     * @param p le plateau sur lequel se déplace la pièce.
     */
	public void bouger(int numDest, Plateau p) {
		ArrayList<int[]> destinations = getDestinations(p);
	    int nx = destinations.get(numDest-1)[0];
	    int ny = destinations.get(numDest-1)[1];
	    
	    if(aDeuxOrientations(destinations.get(numDest-1), p) && numDest < destinations.size()) {
	    	if(destinations.get(numDest-1)[0] == destinations.get(numDest)[0] && destinations.get(numDest-1)[1] == destinations.get(numDest)[1]) this.orientation = Orientation.VERTICAL;
	    	else this.orientation = Orientation.HORIZONTAL;
	    }else {
	    	 if(p.occupant(x, y) == null || (p.occupant(x, y) == this && (this.x != nx || this.y != ny))) {
	 	    	if(ny+1 < p.getLargeur() && (p.occupant(nx, ny+1) == null) || p.occupant(nx, ny+1) == this) this.orientation = Orientation.HORIZONTAL;
	 			else if(nx-1 >= 0 && (p.occupant(nx-1, ny) == null || p.occupant(nx-1, ny) == this)) this.orientation = Orientation.VERTICAL;
	 	    }
	    }
	    
	    this.x = nx; this.y = ny;
	}
	
	
	/**
     * Vérifie si une destination a deux orientations possibles.
     * @param destination les coordonnées de la destination à vérifier.
     * @param p le plateau sur lequel se déplace la pièce.
     * @return vrai si la destination a deux orientations possibles, sinon faux.
     */
	private boolean aDeuxOrientations(int[] destination, Plateau p) {
		int nbOrientations = 0;
		ArrayList<int[]> destinations = getDestinations(p);
		for(int[] dest : destinations) {
			if(dest[0] == destination[0] && dest[1] == destination[1]) nbOrientations++;
		}
		return nbOrientations == 2;
	}
	
	
	/**
     * Obtient les destinations possibles pour la pièce sur le plateau.
     * @param p le plateau sur lequel se déplace la pièce.
     * @return une liste de tableau d'entiers représentant les coordonnées des destinations possibles.
     */
	private ArrayList<int[]> getDestinations(Plateau p){
		ArrayList<int[]> destinations = new ArrayList<>();
		
		for(int x = 0; x < p.getHauteur(); ++x) {
			for(int y = 0; y < p.getLargeur(); ++y) {
				if(p.occupant(x, y) == null || (p.occupant(x, y) == this && (this.x != x || this.y != y))) {
					int[] position = {x, y};
					if(x-1 >= 0) {
						if((p.occupant(x-1, y) == null) || (p.occupant(x-1, y) == this)) destinations.add(position);
					}
					if(y+1 < p.getLargeur()) {
						if((p.occupant(x, y+1) == null)||(p.occupant(x, y+1) == this)) destinations.add(position);
					}
				}
			}
		}
		
		return destinations;
	}
	
	/**
     * Vérifie si une destination est valide.
     * @param d le numéro de la destination à vérifier.
     * @param p le plateau sur lequel se déplace la pièce.
     * @return vrai si la destination est valide, sinon faux.
     */
	public boolean estDestinationValide(int d, Plateau p) {
		ArrayList<int[]> destinations = getDestinations(p);
		if (d > destinations.size()) return false;
		return true;
	}
	
	 /**
     * Obtient le numéro de destination pour une position donnée.
     * @param d les coordonnées de la position à vérifier.
     * @param p le plateau sur lequel se déplace la pièce.
     * @return le numéro de destination, ou -1 si la position n'est pas une destination valide.
     */
	private int numDestination(int[] d, Plateau p) {
		ArrayList<int[]> destinations = getDestinations(p);
		for(int[] destination : destinations) {
			if(destination[0] == d[0] && destination[1] == d[1]) {
				return destinations.indexOf(destination)+1;
			}
		}
		return -1;
	}
	
	
	/**
     * Affiche les destinations possibles pour la pièce sur le plateau.
     * @param p le plateau sur lequel se déplace la pièce.
     */
	public void afficherDestinations(Plateau p) {
		StringBuilder res = new StringBuilder();
		res.append("* * * * * * * * * * * * *\n");
		for (int x = 0; x < p.getLargeur(); ++x) {
			res.append("*	*	*	*\n");
			for(int y = 0; y < p.getHauteur(); ++y) {
				int[] position = {x, y};
				int numDestination = numDestination(position, p);
				Piece occupant = p.occupant(x, y);
				
				if(numDestination != -1) {
					if(aDeuxOrientations(position, p)) res.append("*  "+numDestination+"-"+(numDestination+1)+"  ");
					else res.append("*   "+numDestination+"   ");
				}
				else if(this.x == x && this.y == y) res.append("*       ");
				else if((occupant == this || occupant == null) && y == 2) res.append("*   O   ");
				else if(occupant == this) res.append("*       ");
				else if(occupant != null) res.append("*   "+occupant.getCouleur()+"   ");
				else res.append("*       ");
			}
			res.append("*");
			res.append("\n");
			res.append("*	*	*	*\n");
			res.append("* * * * * * * * * * * * *\n");
		}
		System.out.println(res);
		
	}
	
	
	public String toString() {return ""+couleur;}
}
