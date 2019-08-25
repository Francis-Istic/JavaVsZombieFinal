/**
 * Classe représentant un Zombie de Base
 * @author franc
 *
 */
public class ZombieBase extends Zombie{
	
	/**
	 * Double représentant le déplacement du zombie sur l'axe X
	 */
	protected double TRUC_MOVE_X = -0.0010275;
	
	/**
	 * Timer représentant le délai d'attaque du zombie;
	 */
	protected Timer attaque;
	
	//Constructeur initialise attributs
	public ZombieBase(double x, double y) {
		super(x, y);
		this.HP=200;
		this.attaque = new Timer(1000);
	}
	

	/**
	 * Fonction représente les actions du zombie et la collision
	 * Si ses HP tombent à 0, alors il meurt
	 * Sinon il regarde toutes les plantes, et si une d'entre elles se situent à coté alors il se stop et commence à taper
	 * La plante ciblé, on utilise le timer pour taper seulement toutes les secondes et pas avant
	 */
	@Override
	public void step() {
		if(HP<=0) {
			mort=true;
		} 
		else {
			for(Entite entite : GameWorld.entites)
				if(entite instanceof Tournesol || entite instanceof Pois || entite instanceof Noix) {
					if(Double.compare(GameWorld.g1.getCoord(this.position.getX(),this.position.getY()).getX(), 
							GameWorld.g1.getCoord(entite.getX(), entite.getY()).getX())==0
							&&Double.compare(GameWorld.g1.getCoord(this.position.getX(),this.position.getY()).getY()
									,GameWorld.g1.getCoord(entite.getX(), entite.getY()).getY())==0) {
						TRUC_MOVE_X=0.0;
						if(attaque.hasFinished()){
							entite.HP=entite.HP-30;
							attaque.restart();
							if(entite.HP<=0) {
								TRUC_MOVE_X=-0.0010275;
							}
						}
					}
				}
			}
			this.position.setX(this.position.getX() + TRUC_MOVE_X);
		if(this.position.getX()<=0.035){
			mort = true;
		}
		}
		

	/**
	 * Dessine le zombie si il n'est pas mort
	 */
	@Override
	public void dessine() {
		if(!mort) {
			StdDraw.picture(this.position.getX(), this.position.getY(), "zombie1.gif", 0.08, 0.14);	
		}
	}

}
