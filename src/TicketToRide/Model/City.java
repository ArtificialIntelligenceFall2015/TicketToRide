/**
 * City.java
 */
package TicketToRide.Model;

import TicketToRide.Model.Constants.city;

/**
 * @author Sean Fast
 *
 */
public class City {
	private city cityName;
	private int x_val = 0;
	private int y_val = 0;

	public City(city cityName, int x_val, int y_val) {
		this.cityName = cityName;
		this.x_val = x_val;
		this.y_val = y_val;
	}

	/*
	 * Function: Path(String[]) Inputs: String[] pathArray array of strings that
	 * holds all the values of the current Path object being read in from the
	 * CSV file Description: Constructor for the Path class that creates a Path
	 * object based on the values stored in the pathArray array.
	 */
	public City(String[] cityArray) {
		int i = 0;

		this.cityName = city.valueOf(cityArray[i++]);
		this.x_val = Integer.parseInt(cityArray[i++]);
		this.y_val = Integer.parseInt(cityArray[i++]);

	}

	/**
	 * @return the cityName
	 */
	public city getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(city cityName) {
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

}
