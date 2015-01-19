/*********************************************************************
 Copyright 2015 the Flapi authors

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ********************************************************************/

package unquietcode.tools.flapi.examples.house;

import java.util.List;

/**
 * @author Ben Fagin
 * @version 05-28-2012
 */
public abstract class House {
	private List<Wall> walls;


	public List<Wall> getWalls() {
		return walls;
	}

	public void setWalls(List<Wall> walls) {
		this.walls = walls;
	}

	public String getShape() {
		switch (walls.size()) {
			case 0:
			case 1:
			case 2: return "Impossible! How is your house standing??";
			case 3: return "triangular";
			case 4: return "rectangular";
			case 5: return "pentagonal";
			case 6: return "hexagonal";
			case 7: return "heptagonal";
			case 8: return "octagonal";
			case 9: return "nonagonal";
			default: return "really big";
		}
	}

	abstract String getCost();
}
