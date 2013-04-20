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
