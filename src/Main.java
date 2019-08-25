public class Main {

	public static void main(String[] args) {
		
		GameWorld world = new GameWorld();
		
		// reglage de la taille de la fenetre de jeu, en pixels (nb: un écran fullHD = 1980x1050 pixels)
		StdDraw.setCanvasSize(1900, 1000);
		
		/**
		 * Boucle d'acceuil du jeu permettant de cliquer sur Jouer
		 * Pour Pouvoir lancer une partie
		 */
		Timer test = new Timer(10000);
		boolean accueil = false;
		boolean start = false;
		while(!accueil){
			StdDraw.picture(0.512, 0.512, "accueil.png", 1.0 , 1.0);
			if (StdDraw.isMousePressed()) {
				if(GameWorld.getClickX()>0.297368421 && GameWorld.getClickX()<0.43263157
						&& GameWorld.getClickY()<0.46599999999 && GameWorld.getClickY()>0.403){
					accueil = true; 
					start = true;
				}
			}
			
			
			
		}
		// permet le double buffering, pour permettre l'animation
		StdDraw.enableDoubleBuffering();
		
		
		// la boucle principale de notre jeu
		while (!GameWorld.gameLost() && !GameWorld.gameWon() && start) {
			// Efface la fenetre graphique
			StdDraw.clear();
			
			
			// Capture et traite les eventuelles interactions clavier du joueur
			if (StdDraw.hasNextKeyTyped()) {
				char key = StdDraw.nextKeyTyped();
				world.processUserInput(key);
			}
			
			if (StdDraw.isMousePressed()) {
				world.processMouseClick(StdDraw.mouseX(), StdDraw.mouseY());
			}
			
			
			world.step();
			world.dessine();
			
			
			
			world.refreshSoleil();
			world.refreshZombie();
			
			
			// Montre la fenetre graphique mise a jour et attends 20 millisecondes
			StdDraw.show();
			StdDraw.pause(20);
		}
		
		
		if (GameWorld.gameWon()) System.out.println("Game won !");
		
		if (GameWorld.gameLost()) {
			StdDraw.clear();
			StdDraw.picture(0.5, 0.5, "loose.png", 1, 1);
			System.out.println("Game lost...");
			StdDraw.show();
		}

	}

}
