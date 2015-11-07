/**
 * Constants.java
 * This class contains the global constants for the game
 */
package TicketToRide.Model;

import java.awt.Color;

/**
 * @author Jun He
 * @author Sean Fast
 *
 */
public class Constants {
	public static enum playerColor {
		BLUE, RED, GREEN, YELLOW, BLACK
	};

	public static enum trainCard {
		PINK, WHITE, BLUE, YELLOW, ORANGE, BLACK, RED, GREEN, RAINBOW
	};

	public static enum pathColor {
		PINK(Color.PINK), WHITE(Color.WHITE), BLUE(Color.BLUE), YELLOW(Color.YELLOW), ORANGE(Color.ORANGE), BLACK(Color.BLACK), RED(Color.RED), GREEN(Color.GREEN), GRAY(Color.GRAY);
		
		private Color c;
		
		pathColor(Color c) {
			this.c = c;
		}
		
		public Color getRealColor() {
			return this.c;
		}
		
	};

	public static enum decision {
		CLAIM_A_ROUTE, DRAW_TRAIN_CARDS, DRAW_DES_TICKETS
	};

	public static final int TAKENCOST = 10;// TODO need to reset
}
