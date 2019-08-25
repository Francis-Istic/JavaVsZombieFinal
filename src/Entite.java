import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe abstraite représentant une entité
 * @author franc
 */

public abstract class Entite {
	

   /**
    * Position (coordonnées) de l'entité
    */
	protected Position position;
	
	/**
	 * Booléen qui indique si l'entité est morte ou non
	 */
	protected boolean mort;
	
	/**
	 * Nombre de HP de l'entité
	 */
	protected int HP;
	
	public Entite(double x, double y) {
		position = new Position(x, y);
		this.mort=false;
	}
	
	public double getX() {
		return position.getX();
	}
	
	public double getY() {
		return this.position.getY();
	}
	
	
	public void setPosition(Position p){
		this.position = p;
	}
	
	// met a jour l'entite : dÃ©placement, effectuer une action
	public abstract void step();
	
	// dessine l'entite, aux bonnes coordonnees
	public abstract void dessine();
	

}
