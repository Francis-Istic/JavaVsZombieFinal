import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe représentant le Tire-Pois, classe fils de plante
 * @author franc
 */
public class Pois extends Plante {
	
	/**
	 * Timer représentant la cadence de tir du Tire-Pois
	 */
	protected Timer shoot;
	 
	//Constructeur qui initialise attributs
	public Pois(double x,double y){
		super(x,y);
		this.HP=300;
		this.shoot = new Timer(2000);
	}

	/**
	 * La plante meurt si ces HP sont à 0
	 * De plus, une entité projectile est crée si le timer de cadence de tir est finis
	 */
	@Override
	public void step() {
		if(HP<=0) {
			mort = true;
		}
		else {
			if(shoot.hasFinished()) {
				GameWorld.projectile.add(new Projectile(this.position.getX(), this.position.getY()));
				shoot.restart();
			}
			mort = false;
		}	
	}
	
	/**
	 * Dessine la plante si celle ci n'est pas morte
	 */
	@Override
	public void dessine() {
		if(!mort) {
			StdDraw.picture(this.position.getX(), this.position.getY(), "lancepois.gif", 0.07, 0.1);
		}
	}
	
}
