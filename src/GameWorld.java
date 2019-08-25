import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GameWorld {

	
	/**
	 * Liste d'entités présente dans notre jeu
	 */
	protected static List<Entite> entites;
	protected static List<Entite> projectile;
	
	/**
	 * Booléen qui indique si la partie est gagné
	 */
	private static boolean gameWon;
	
	/**
	 * Booléan qui indique si la partie est perdue
	 */
	private static boolean gameLost;
	
	
	/**
	 * Le timer d'apparition du soleil
	 */
	private static Timer ts;
	
	/**
	 * Le timer d'apparition des zombie
	 */
	private static Timer tz;
	
	/**
	 * Le timer de début d'apparition des zombie (ne pas confondre avec le timer précedent)
	 */
	private static Timer td;
	
	/**
	 * Le timer représentant le delai pour poser un tournesol
	 */
	private static Timer tour;
	
	/**
	 * Le timer représentant le delai pour poser une noix
	 */
	private static Timer noix;
	
	/**
	 * Le timer représentant le delai pour poser un lance-pois
	 */
	private static Timer pois;
	
	/**
	 * Grille de jeu
	 */
	public static Grille g1;

	// constructeur, il faut initialiser notre monde virtuel
	public GameWorld() {

		//On crée nos collections
		projectile = new LinkedList<Entite>();
		entites = new LinkedList<Entite>();
		g1 = new Grille();
		g1.init();
		
		
		//On donne les valeurs à nos attributs
		gameWon=false;
		gameLost=false;
		ts = new Timer(6500);
		tz = new Timer(5000);
		td = new Timer(20000);
		tour = new Timer(5000);
		noix = new Timer(20000);
		pois = new Timer(5000);
	}

	/**
	 * Gestion des interactions clavier avec l'utilisateur
	 * 
	 * @param key
	 *            Touche pressee par l'utilisateur
	 */
	public void processUserInput(char key) {
		switch (key) {
		case 't':
			double a = getClickX();
			double b = getClickY();
			if(Soleil.cmpt>=50 && tour.hasFinished() && DansGrille(a,b)) {
				Position pos1 = g1.getCoord(a, b);
				a = pos1.getX();
				b = pos1.getY();
				Entite c = g1.recupCase(a, b).e;
				if(c==null && NoZombie(a,b)) {
					entites.add(new Tournesol(a,b));
					Soleil.cmpt=Soleil.cmpt-50;
					tour.restart();
					g1.recupCase(a, b).e = new Tournesol(a,b);
				}
				else {
					System.out.println("Une entitee existe deja");
				}
			}
			else {
				System.out.println("Not enough Money");
			}
			break;
		case 'p':
			double e = getClickX();
			double f = getClickY();
			if(Soleil.cmpt>=100 && pois.hasFinished() && DansGrille(e,f)) {
				Position pos2 = g1.getCoord(e,f);
				e = pos2.getX();
				f = pos2.getY();
				Entite a2= g1.recupCase(e,f).e;
				if(a2==null && NoZombie(e,f)) {
					entites.add(new Pois(e,f));
					Soleil.cmpt=Soleil.cmpt-100;
					pois.restart();
					g1.recupCase(e, f).e = new Pois(e,f);
				} 
				else {
					System.out.println("Une entitee existe deja");
				}
			} else {
				System.out.println("Not Enough Money");
			}
			break;
		case 'n':
			double g = getClickX();
			double h = getClickY();
			if(Soleil.cmpt>=50 && noix.hasFinished() && DansGrille(g,h)) {
				Position pos3 = g1.getCoord(g, h);
				g = pos3.getX();
				h = pos3.getY();
				Entite a3 = g1.recupCase(g, h).e;
				if(a3==null && NoZombie(g,h)) {
					entites.add(new Noix(g,h));
					Soleil.cmpt=Soleil.cmpt-50;
					noix.restart();
					g1.recupCase(g, h).e = new Noix(g,h);
				}
				else {
					System.out.println("Une entitee existe deja");
				}
			} else {
				System.out.println("Not enough Money");
			}
			break;
		default:
			System.out.println("Touche non prise en charge");
			break;
		}
	}
	
	/**
	 * Gestion des interactions souris avec l'utilisateur (la souris a Ã©tÃ© cliquÃ©e)
	 * 
	 * @param x position en x de la souris au moment du clic
	 * @param y position en y de la souris au moment du clic
	 */
	public static void processMouseClick(double x, double y) {
		//System.out.println("La souris a ete cliquee en : "+x+" - "+y);
	}
	
	/**
	 * Fonction qui récupère une coordonnée en X
	 * @return double, qui correspond à la position de la souris en X
	 */
	public static double getClickX(){
		processMouseClick(StdDraw.mouseX(),StdDraw.mouseY());
		return StdDraw.mouseX();
	}
	
	/**
	 * Fonction qui récupère une coordonnée en Y
	 * @return double, qui correspond à la position de la souris en Y
	 */
	public static double getClickY(){
		processMouseClick(StdDraw.mouseX(),StdDraw.mouseY());
		return StdDraw.mouseY();
	}
	
	// on fait bouger/agir toutes les entites
	public void step() {
		List<Entite> entitesBis = new LinkedList<Entite>(entites);
		List<Entite> projectileBis = new LinkedList<Entite>(projectile);
		for (Entite entite : entitesBis) {
			entite.step();
			if(entite.mort == true) {
				entites.remove(entite);
			}
		}
		
		for(Entite projectiles : projectileBis){
			projectiles.step();
			if(projectiles.mort==true){
				projectile.remove(projectiles);
			}
		}
	}

	// dessine les entites du jeu
	public void dessine() {
		StdDraw.picture(0.512, 0.512, "jardin.PNG", 0.95, 1.0);
		String a = Integer.toString(Soleil.cmpt);
		StdDraw.text(0.014, 0.971,a);
		
		//affiche les entites
		for (Entite entite : entites)
			entite.dessine();
		for (Entite proj : projectile)
			proj.dessine();
	}

	public static boolean gameWon() {
		return gameWon;
	}
	
	/**
	 * Fonction qui dit si la partie est perdue ou pas
	 * @return boolean, qui représente la défaite de la partie
	 */
	public static boolean gameLost(){
		for (Entite entite : entites) {
			if(entite instanceof Zombie) {
				if(entite.position.getX()<=0.05){
					gameLost=true;
					break;
				}
				else {
					gameLost=false;
				}
			}
		}
		return gameLost;
		
	}
	
	/**
	 * Fonction qui vérifie si le timer d'apparition du Soleil est finie
	 * Et qui en fait apparaitre un aléatoirement dans une des case de la grille
	 */
	public void refreshSoleil() {
		if(ts.hasFinished()) {
			int k =  (int) (Math.random() * (8 - 0) + 0);
			int j =  (int) (Math.random() * (4 - 0) + 0);
			double x = g1.grid[k][j].X;
			double y = g1.grid[k][j].Y;
			entites.add(new Soleil(x,y));
			ts.restart();
		}
	}
	
	/**
	 * Fonction qui vérifie si le timer de première apparition est finie ainsi que le timer régulier
	 * Si oui, il fait apparaitre un zombie a droite de la grille sur une ligne aléatoire.
	 */
	public void refreshZombie() {
		int s = (int) (Math.random() * (10-0) + 0);
		if(td.hasFinished()) {
			if(tz.hasFinished()) {
				int i = (int) (Math.random() * (4-0) + 0);
				double x = g1.grid[8][i].X+0.06;
				double y = g1.grid[8][i].Y;
				if(s>=9) {
					entites.add(new SuperZombie(x,y));
				}
				else {
					entites.add(new ZombieBase(x,y));
				}
				tz.restart();
			}	
		}
	}
	/**
	 * Fonction qui vérifie qu'il n'y a pas de zombie sur la case
	 * @param x, double représentant des coordonnées en X
	 * @param y, double représentant des coordonnées en Y
	 * @return boolean, qui dit si oui ou non, un zombie sur la grille est au même endroit que les coordonnées mis en param
	 */
	public boolean NoZombie(double x, double y ) {
		boolean zombie = true;
		for(Entite e : entites)
			if(e instanceof Zombie) {
					if(((x-0.05275<=e.getX() && x+0.05275>=e.getX()) && (y-0.0995<=e.getY() && y+0.0995>=e.getY()))) {
							zombie = false;
					}
				}
		return zombie;
	}
	
	/**
	 * Fonction qui vérifie si nous cliquons bien dans la grille
	 * @param x, double représentant des coordonnées en X
	 * @param y, double représentant des coordonnées en Y
	 * @return boolean, qui dit si oui ou non, nous avons clique dans la grille
	 */
	public boolean DansGrille(double x, double y) {
		boolean dans = false;
		for(int i=0;i<9;i++) {
			for(int j =0;j<4;j++) {
				if((g1.grid[i][j].X-0.05275<=x && g1.grid[i][j].X+0.05275>=x)&&(g1.grid[i][j].Y-0.0995<=y 
						&& g1.grid[i][j].Y+0.0995>=y)) {
					dans=true;
				}
			}
		}
		return dans;
	}
	
	
}
