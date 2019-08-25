/**
 * Classe de l'entité Projectile qui est crée dans la classe Pois
 * @author franc
 */
public class Projectile extends Entite {

	/**
	 * Vitesse de Déplacement du projectile
	 */
	private double TRUC_MOVE_X = 0.005275;
	
	/**
	 * Booleen qui indique si le booleen a touche sa cible
	 */
	private boolean touche;

	//Constructeur qui initialise attributs
	public Projectile(double x, double y) {
		super(x,y);
		this.touche = false;
		this.mort = false;
	}
	
	
	/**
	 * Si les HP du projectile sont à 0, le projectile meurt
	 * Sinon on observe toutes les coordonnées des zombies présents et si celle-ci sont les mêmes que celle du zombie
	 * Alors le zombie prend des dégats et le projectile meurt (car il touche la cible)
	 */
	@Override
	public void step() {
		if(touche){
			mort = true;
		}
		if(!touche) {
			for(Entite entite : GameWorld.entites)
				if(entite instanceof Zombie) {
					if((Double.compare(GameWorld.g1.getCoord(this.position.getX(),this.position.getY()).getX(), 
						GameWorld.g1.getCoord(entite.getX(), entite.getY()).getX())==0) &&
						(Double.compare(GameWorld.g1.getCoord(this.position.getX(),this.position.getY()).getY()
						,GameWorld.g1.getCoord(entite.getX(), entite.getY()).getY())==0)) {
							entite.HP=entite.HP-20; //fait perdre 20hp à l'entité touchée. 
							mort = true;//efface le projectile
							touche = true;
					}
				}
			this.position.setX(this.position.getX() + TRUC_MOVE_X);
		}
	}

	/**
	 * Dessine le projectile
	 */
	@Override
	public void dessine() {
		if(!mort) {
			StdDraw.setPenColor(StdDraw.GREEN);
			StdDraw.filledCircle(this.position.getX(), this.position.getY(), 0.009);
		}
	}

}
