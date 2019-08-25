import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe abstraite repr�sentant une entit�
 * @author franc
 */

public abstract class Entite {
	

   /**
    * Position (coordonn�es) de l'entit�
    */
	protected Position position;
	
	/**
	 * Bool�en qui indique si l'entit� est morte ou non
	 */
	protected boolean mort;
	
	/**
	 * Nombre de HP de l'entit�
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
	
	// met a jour l'entite : déplacement, effectuer une action
	public abstract void step();
	
	// dessine l'entite, aux bonnes coordonnees
	public abstract void dessine();
	

}
