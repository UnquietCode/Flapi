/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.examples.house;


import unquietcode.tools.flapi.examples.house.builder.House.HouseHelper;
import unquietcode.tools.flapi.examples.house.builder.Wall.WallHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Ben Fagin
 * @version 05-28-2012
 */
public class HouseHelperImpl implements HouseHelper {
	private List<Wall> walls = new ArrayList<Wall>();

	@Override
	public ExpensiveHouse constructExpensiveHouse() {
		ExpensiveHouse house = new ExpensiveHouse();
		house.setWalls(walls);
		return house;
	}

	@Override
	public AffordableHouse constructAffordableHouse() {
		AffordableHouse house = new AffordableHouse();
		house.setWalls(walls);
		return house;
	}

	@Override
	public Wall addWall(AtomicReference<WallHelper> _helper1) {
		WallHelperImpl wallHelper = new WallHelperImpl();
		_helper1.set(wallHelper);
		walls.add(wallHelper.wall);

		return wallHelper.wall;
	}
}
