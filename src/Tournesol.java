/**
 * Classe représentant la plante Tournesol
 * @author franc
 *
 */
public class Tournesol extends Plante{
	
	/**
	 * Timer représentant le délai du pouvoir du Tournesol
	 */
	private Timer power;
	
	//Constructeur initialise attributs
	public Tournesol(double x,double y){
		super(x,y);
		this.HP=300;
		this.power = new Timer(24000);
	}

	/**
	 * Si la plante meurt, elle disparais
	 * Sinon on regarde le timer Powern et si celui-ci est finis alors on rajoute un Soleil à la liste d'entité
	 * Sur les coordonnées du Soleil
	 */
	@Override
	public void step() {
		if(HP<=0) {
			mort=true;
		}
		else {
			mort=false;
			if(power.hasFinished()) {
				GameWorld.entites.add(new Soleil(this.position.getX(),this.position.getY()));
				power.restart();
			}
		}
	}
	
	/**
	 * Le tournesol est dessiné si il n'est pas mort
	 */
	@Override
	public void dessine() {
		if(!mort) {
			StdDraw.picture(this.position.getX(), this.position.getY(), "tournesol.png", 0.06, 0.1);
		}
	}

}
