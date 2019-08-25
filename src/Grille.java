import java.util.ArrayList;
import java.util.LinkedList;

public class Grille {

	/**
	 * Classe représentant une Case de la grille
	 * @author franc
	 */
	public class Case {
		/**
		 * Coordonée en X de la Case
		 */
		protected double X;
		
		/**
		 * Coordonnée en Y de la Case
		 */
		protected double Y;
		
		/**
		 * Entité présente dans la Case
		 */
		protected Entite e;
		
		public Case(double X, double Y){
			this.X=X;
			this.Y=Y;
			this.e=null;
		}
	}
	
	
	/**
	 * Tableau BiDimensionel, représentant la grille
	 * Avec 9 colonnes et 5 lignes
	 */
	protected Case [][] grid = new Case[9][5];
	
	/**
	 * Constrcuteur initialise la Grille, en mettant une Case dans le tableau
	 */
	public Grille() {
		for(int i=0;i<9;i++) {
			for(int j =0;j<4;j++) {
				this.grid[i][j]=new Case(0.0, 0.0);
			}
		}
	}
	
	/**
	 * Iniatialise le tableau en parant d'une position donnée
	 * Puis construit un milieu de case entre chaque hauteur et longueur donnée
	 */
	public void init() {
		double positionX = 0.089;
		double positionY = 0.31;
		double witdh = 0.1055;
		double heigth = 0.199;
		for(int i = 0; i<9; i++) { //i nb de colonne
			for(int j = 0; j<5;j++) { //j nbr de ligne
				grid[i][j]=new Case(positionX+ i*witdh , positionY + j*heigth);
			}
		}
	}
	
	
	/**
	 * Ancienne fonction qui permet de dessiner la grille
	 * Plus utilisé mais pratique au début du projet pour visualiser notre grille
	 */
	public void dessine() {
		double positionX = 0.089;
		double positionY = 0.212;
		double witdh = 0.1055;
		double heigth = 0.199;
		for(int i = 0; i<9; i++) { //i nb de colonne
			for(int j = 0; j<5;j++) { //j nbr de ligne
				StdDraw.rectangle(positionX+ i*witdh, positionY + j*heigth, witdh/2,heigth);
			}
		}
	}


	/**
	 * Fonction qui renvoie le centre de la Case selon les coordonnées mis en parametre
	 * @param x, double correspondant aux coordonnées en X
	 * @param y, double correspondant aux coordonnées en Y
	 * @return Position, représentant le milieu de la Case dans laquelle les coordonnées sont
	 */
	public Position getCoord(double x, double y) {
		for(int i=0;i<9;i++) {
			for(int j =0;j<5;j++) {
				if((grid[i][j].X-0.05275<=x && grid[i][j].X+0.05275>=x)&&(grid[i][j].Y-0.0995<=y 
						&& grid[i][j].Y+0.0995>=y)) {
					x=grid[i][j].X;
					y=grid[i][j].Y;
				}
			}
		}
		return new Position(x,y);
	}
	
	/**
	 * Fonction qui recupère une Case selon les coordonnées
	 * @param x, double correspondant aux coordonnées en X
	 * @param y, double correspondant aux coordonnées en Y
	 * @return Case
	 */
	public Case recupCase(double x, double y) {
		Case c = new Case(0.0,0.0);
		for(int i =0;i<9;i++) {
			for(int j=0;j<5;j++) {
				if(grid[i][j].X==x  && grid[i][j].Y==y) {
					c = grid[i][j];
				}
			}
		}
		return c;
	}
	
}
