/**
 * Constants.java
 * This class contains the global constants for the game
 */
package TicketToRide.Model;

/**
 * @author Jun He
 * @author Sean Fast
 *
 */
public class Constants {
	public static enum trainColor {
		BLUE, RED, GREEN, YELLOW, BLACK
	};

	public static enum trainCard {
		PINK, WHITE, BLUE, YELLOW, ORANGE, BLACK, RED, GREEN, RAINBOW
	};

	public static enum pathColor {
		PINK, WHITE, BLUE, YELLOW, ORANGE, BLACK, RED, GREEN, GRAY
	};
	
	public static enum decision{
		CLAIM_A_ROUTE, DRAW_TRAIN_CARDS, DRAW_DES_TICKETS
	};
	
	public static final int TAKENCOST=10;//TODO need to reset
}
