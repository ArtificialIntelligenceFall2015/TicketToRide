/**
 * World.java
 * This class represent as an world map, which contain a list of paths
 */
package TicketToRide.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import TicketToRide.Control.PathHandler;

/**
 * @author Jun He
 *
 */
public class World {
	public static List<Path> map;
	public static List<City> cities;

	/**
	 * This field use for get path by using cities string 
	 * Ex. City1 c1, City2 c2, List Paths p will represent as: 
	 * key: c1c2, value: p 
	 * because of some of two cities has more than two Path it needs a list of path
	 **/
	public static HashMap<String, List<Path>> citiesPath;
	
	public static HashMap<String, City> stringToCities;
	
	static {
		cities=ParseCSVData.parseCities();
		stringToCities=new HashMap<String, City>();
		
		for(City city: cities){
			stringToCities.put(city.getCityName(), city);
		}
		
		map = ParseCSVData.parseRoutes();
		citiesPath=new HashMap<String, List<Path>>();
		
		for(Path path: map){
			PathHandler.addCitysPath(path.getCity1(),path.getCity2(),path);
		}
	}
}
