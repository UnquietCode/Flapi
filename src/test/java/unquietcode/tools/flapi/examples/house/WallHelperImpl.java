/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.examples.house;


import unquietcode.tools.flapi.examples.house.builder.Wall.WallHelper;

import java.awt.*;

/**
 * @author Ben Fagin
 * @version 05-28-2012
 */
public class WallHelperImpl implements WallHelper {
	final Wall wall = new Wall();

	@Override
	public void setColor(Color color) {
		wall.setColor(color);
	}

	@Override
	public void setWidth(double inches) {
		wall.setWidth(inches);
	}
}
