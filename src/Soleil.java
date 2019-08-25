/**
 * Classe représentant l'entité Soleil
 * @author franc
 */
public class Soleil extends Entite {
	
	/**
	 * Compteur de Soleil (Static car même valeur pour tout les soleil)
	 */
	public static int cmpt=50;
	
	/**
	 * Timer représentant le délai que l'on a pour cliquer sur le soleil
	 */
	protected Timer timer;
	
	//Constructeurs initialise les attributs
	public Soleil(double x, double y) {
		super(x,y);
		this.timer = new Timer(20000);
	}
	
	/**
	 * Fonction annexe qui renvoie vrai ou faux, si on clique au même endroit que les coordonnées mis en parametre
	 * @param x, double représentant des coordonnées en X
	 * @param y, double représentant des coordonnées en Y
	 * @return boolean, vrai, si on clique sur les coord (x,y)
	 */
	public boolean isMousePressedInRange(double x, double y){
		if(GameWorld.getClickX()<(0.02 + x)&& GameWorld.getClickX()>(-0.02 +x)
				&&GameWorld.getClickY()<(0.05+y)&&GameWorld.getClickY()>(-0.05 + y)&&StdDraw.isMousePressed()){
			return true;
		}
		else return false;
	}
	
	/**
	 * Fonction qui rajoute 25 au compteur si on clique sur le centre du Soleil
	 * Sinon on regarde le timer de disparition du soleil
	 * Dans ces 2 cas, le soleil meurt à la fin
	 */
	@Override
	public void step() {
		if(isMousePressedInRange(this.position.getX(),this.position.getY())) {
			mort = true;
			Soleil.cmpt+=25;
		}
		else if(timer.hasFinished()){
			mort = true;
			Soleil.cmpt+=25;
		}
	}

	/**
	 * Dessine le soleil si il n'est pas mort
	 */
	@Override
	public void dessine() {
		if(!mort) {
			StdDraw.picture(this.position.getX(), this.position.getY(), "soleil.png",0.1,0.1);
		}
	}

}
