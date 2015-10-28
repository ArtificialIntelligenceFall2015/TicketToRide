/**
 * World.java
 * This class represent as an world map, which contain a list of paths
 */
package TicketToRide.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jun He
 *
 */
public class World {
	static {
		map = new ArrayList<Path>();
		city = new ArrayList<String>();
	}

	public static List<Path> map;
	public static List<String> city;
}
