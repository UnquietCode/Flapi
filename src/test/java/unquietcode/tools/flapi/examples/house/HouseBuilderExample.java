/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.examples.house;

import org.junit.Test;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.DescriptorMaker;
import unquietcode.tools.flapi.Flapi;
import unquietcode.tools.flapi.examples.house.builder.House.HouseBuilder;
import unquietcode.tools.flapi.examples.house.builder.House.HouseGenerator;

import java.awt.*;

/**
 * @author Ben Fagin
 * @version 05-28-2012
 */
public class HouseBuilderExample implements DescriptorMaker {

	@Override
	public Descriptor descriptor() {
		return Flapi.builder()
			.setDescriptorName("House")
			.setPackage("unquietcode.tools.flapi.examples.house.builder")

			.startBlock("Wall", "addWall()").last(Wall.class)
				.addMethod("setColor(java.awt.Color color)").atMost(1)
				.addMethod("setWidth(double inches)").atMost(1)
			.endBlock()

			.addMethod("constructExpensiveHouse()").last(ExpensiveHouse.class)
			.addMethod("constructAffordableHouse()").last(AffordableHouse.class)
		.build();
	}

	@Test
	public void usage() {
		HouseBuilder<Void> houseBuilder = HouseGenerator.create(new HouseHelperImpl());

		// using the builder
		houseBuilder.addWall()
			.setWidth(120.35)
			.setColor(Color.RED)
		;

		houseBuilder.addWall()
			.setColor(Color.BLACK)
			.setWidth(87.45)
		;

		// using both the builder and the object
		Wall wall3 = houseBuilder.addWall()
			.setColor(Color.BLACK)
			.setWidth(80.25)
		;
		wall3.isWeightBearing(true);    // this method isn't on the builder

		AffordableHouse option1 = houseBuilder.constructAffordableHouse();
		ExpensiveHouse option2 = houseBuilder.constructExpensiveHouse();

		System.out.println(getInfo(option1));
		System.out.println();
		System.out.println(getInfo(option2));
	}

	private String getInfo(House house) {
		return new StringBuilder()
			.append("Cost: ").append(house.getCost())
			.append("\nWalls: ").append(house.getWalls().size())
			.append("\nEnjoy your new ").append(house.getShape()).append(" house!")
		.toString();
	}
}