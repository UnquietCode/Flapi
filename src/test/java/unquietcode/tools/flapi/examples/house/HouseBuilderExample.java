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

import org.junit.Test;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.Flapi;
import unquietcode.tools.flapi.examples.house.builder.HouseBuilder;
import unquietcode.tools.flapi.examples.house.builder.HouseGenerator;

import java.awt.*;

/**
 * @author Ben Fagin
 * @version 05-28-2012
 */
public class HouseBuilderExample {

	public static void main(String[] args) {
		Descriptor descriptor = Flapi.builder()
			.setDescriptorName("House")
			.setPackage("unquietcode.tools.flapi.examples.house.builder")

			.startBlock("Wall", "addWall()").last(Wall.class)
				.addMethod("setColor(java.awt.Color color)").atMost(1)
				.addMethod("setWidth(double inches)").atMost(1)
			.endBlock()

			.addMethod("constructExpensiveHouse()").last(ExpensiveHouse.class)
			.addMethod("constructAffordableHouse()").last(AffordableHouse.class)
		.build();

		descriptor.writeToFolder(args[0]);
	}

	@Test
	public void usage() {
		HouseBuilder<Void> houseBuilder = HouseGenerator.create(new HouseHelperImpl());

		// using the builder
		Wall wall1 = houseBuilder.addWall()
			.setWidth(120.35)
			.setColor(Color.RED)
		;

		Wall wall2 = houseBuilder.addWall()
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