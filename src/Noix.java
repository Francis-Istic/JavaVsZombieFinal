/**
 * Classe représentant la Noix, Classe fille de Plante
 * @author franc
 */

public class Noix extends Plante {
	
	//Constructeur qui initialise attributs
	public Noix(double x, double y) {
		super(x, y);
		this.HP=1500;
	}
	
	/**
	 * La noix ne fait rien en terme d'action
	 * Mais si ses HP tombent à 0, alors elle meurt
	 */
	@Override
	public void step() {
		if(HP<=0) {
			mort=true;
		}
		else mort=false;
	}
	
	
	/**
	 * On desine le sprite de la noix, si celle-ci est vivante
	 */
	@Override
	public void dessine() {
		if(!mort) {
			StdDraw.picture(this.position.getX(),this.position.getY(),"noix.png",0.06,0.12);
		}

	}

}
