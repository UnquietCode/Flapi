/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.examples.house;

import java.awt.*;

/**
 * @author Ben Fagin
 * @version 05-28-2012
 */
public class Wall {
	private Color color;
	private double width;
	private boolean isWeightBearing;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void isWeightBearing(boolean value) {
		isWeightBearing = value;
	}

	public boolean isWeightBearing() {
		return isWeightBearing;
	}
}
