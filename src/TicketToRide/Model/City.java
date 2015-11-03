/**
 * City.java
 * This class defines the City object, used to represent the nodes of the graph
 * and populated from a csv file
 */
package TicketToRide.Model;

/**
 * @author Sean Fast
 *
 */
public class City {
	private String cityName;
	private int x_val = 0;
	private int y_val = 0;

	/**
	 * Basic Constructor
	 * @param cityName
	 * @param x_val
	 * @param y_val
	 */
	public City(String cityName, int x_val, int y_val) {
		this.cityName = cityName;
		this.x_val = x_val;
		this.y_val = y_val;
	}

	/**
	 * String arg contructor: creates City object based on 
	 * values stored in cityArray read in from csv file
	 * @param cityArray city data from csv file
	 */
	public City(String[] cityArray) {
		int i = 0;

		this.cityName = cityArray[i++];
		this.x_val = Integer.parseInt(cityArray[i++]);
		this.y_val = Integer.parseInt(cityArray[i++]);

	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the x_val
	 */
	public int getX_val() {
		return x_val;
	}

	/**
	 * @param x_val
	 *            the x_val to set
	 */
	public void setX_val(int x_val) {
		this.x_val = x_val;
	}

	/**
	 * @return the y_val
	 */
	public int getY_val() {
		return y_val;
	}

	/**
	 * @param y_val
	 *            the y_val to set
	 */
	public void setY_val(int y_val) {
		this.y_val = y_val;
	}
	
	public boolean equals(City c){
		return cityName.equals(c.getCityName());
	}
}
