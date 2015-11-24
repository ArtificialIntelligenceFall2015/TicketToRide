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
		BLUE(Color.BLUE), RED(Color.RED), GREEN(Color.GREEN), YELLOW(Color.YELLOW), BLACK(Color.BLACK);
		private Color c;

		playerColor(Color c) {
			this.c = c;
		}

		public Color getRealColor() {
			return this.c;
		}

	};

	public static enum trainCard {
		PINK(Color.PINK), WHITE(Color.WHITE), BLUE(Color.BLUE), YELLOW(Color.YELLOW), ORANGE(Color.ORANGE), BLACK(
				Color.BLACK), RED(Color.RED), GREEN(Color.GREEN), RAINBOW(Color.GRAY);

		private Color c;

		trainCard(Color c) {
			this.c = c;
		}

		public Color getRealColor() {
			return this.c;
		}

	};

	public static enum pathColor {
		PINK(Color.PINK), WHITE(Color.WHITE), BLUE(Color.BLUE), YELLOW(Color.YELLOW), ORANGE(Color.ORANGE), BLACK(
				Color.BLACK), RED(Color.RED), GREEN(Color.GREEN), GRAY(Color.GRAY);

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

	public static enum strategies {
		CR, RC, PP, RR

	};

	public static final int[] routeScore = { 0, 1, 2, 4, 7, 10, 15 };

	public static final int TAKENCOST = 10;// TODO need to reset
}
