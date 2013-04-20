/*******************************************************************************
 Copyright 2012 Benjamin Fagin

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.


     Read the included LICENSE.TXT for more information.
 ******************************************************************************/

package unquietcode.tools.flapi.examples.house;


import unquietcode.tools.flapi.examples.house.builder.House.HouseHelper;
import unquietcode.tools.flapi.examples.house.builder.Wall.WallHelper;
import unquietcode.tools.flapi.support.ObjectWrapper;

import java.util.ArrayList;
import java.util.List;

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
	public Wall addWall(ObjectWrapper<WallHelper> _helper1) {
		WallHelperImpl wallHelper = new WallHelperImpl();
		_helper1.set(wallHelper);
		walls.add(wallHelper.wall);

		return wallHelper.wall;
	}
}
