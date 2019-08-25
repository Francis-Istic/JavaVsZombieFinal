
public class SuperZombie extends Zombie {

	/**
	 * Vitesse de déplacement du SuperZombie
	 */
	protected double TRUC_MOVE_X = -0.002;
	
	/**
	 * Timer d'attaque du SuperZombie
	 */
	protected Timer attaque;
	
	public SuperZombie(double x, double y) {
		super(x,y);
		this.attaque= new Timer(1000);
		this.HP=560;
	}
	
	/**
	 * Meme fonction que ZOombieBase
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
								TRUC_MOVE_X=-0.002;
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
	 * Meme methode que ZombieBase
	 */
	@Override
	public void dessine() {
		if(!mort) {
			StdDraw.picture(this.position.getX(), this.position.getY(), "superz.gif", 0.08, 0.14);	
		}
	}

}
