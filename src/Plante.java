/**
 * Classe abstraite représentant un type d'Entité : Plante
 * @author franc
 */
public abstract class Plante extends Entite {

	
	public int getHP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public Plante(double x, double y) {
		super(x, y);
	}
	/**
	 * Action de la plante
	 */
	public abstract void step(); 

	/**
	 * Dessine la plante
	 */
	public abstract void dessine();

}
