/**
 * Classe repr�sentant la plante Tournesol
 * @author franc
 *
 */
public class Tournesol extends Plante{
	
	/**
	 * Timer repr�sentant le d�lai du pouvoir du Tournesol
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
	 * Sinon on regarde le timer Powern et si celui-ci est finis alors on rajoute un Soleil � la liste d'entit�
	 * Sur les coordonn�es du Soleil
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
	 * Le tournesol est dessin� si il n'est pas mort
	 */
	@Override
	public void dessine() {
		if(!mort) {
			StdDraw.picture(this.position.getX(), this.position.getY(), "tournesol.png", 0.06, 0.1);
		}
	}

}
