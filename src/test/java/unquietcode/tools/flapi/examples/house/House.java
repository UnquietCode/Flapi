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
