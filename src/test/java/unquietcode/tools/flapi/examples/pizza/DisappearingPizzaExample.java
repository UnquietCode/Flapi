package unquietcode.tools.flapi.examples.pizza;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.DescriptorHelperImpl;
import unquietcode.tools.flapi.builder.DescriptorGenerator;
import unquietcode.tools.flapi.examples.pizza.builder.PizzaGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ben Fagin
 * @version 04-27-2012
 *
 * Descriptor example which shows the use of restricted methods.
 */
public class DisappearingPizzaExample {

	@Rule
	public TemporaryFolder temp = new TemporaryFolder();

	@Test
	public void descriptorGenerator() {
		main(new String[]{temp.getRoot().getAbsolutePath()});
	}

	public static void main(String[] args) {
		Descriptor builder =
			DescriptorGenerator.create(new DescriptorHelperImpl())
				.setPackage("unquietcode.tools.flapi.examples.pizza.builder")
				.setStartingMethodName("makePizza")
				.setDescriptorName("Pizza")
				.setReturnType(Pizza.class)

				.addMethod("addSauce(unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.SauceType sauceType)").atMost(1)
				.addMethod("addCheese()").atMost(1)
				.addMethod("addTopping(unquietcode.tools.flapi.examples.pizza.DisappearingPizzaExample.Topping topping)").atMost(3)
				.addMethod("bake()").last()
			.build()
		;

		builder.writeToFolder(args[0]);
	}

	@Test
	public void usage() {
		Pizza myDeliciousPizza = PizzaGenerator.makePizza(new PizzaHelperImpl())
			.addSauce(SauceType.Marinara)
			.addTopping(Topping.Garlic)
			.addTopping(Topping.Green_Peppers)
			.addTopping(Topping.Red_Onions)
			.addCheese()
		.bake();

		System.out.println("Worth the wait!\n");
		System.out.println(myDeliciousPizza);
	}


	public enum SauceType {
		Tomato, Marinara, Alfredo, Mushroom, Pesto
	}

	public enum Topping {
		Extra_Cheese, Garlic, Red_Onions, White_Onions, Bacon, Mushrooms, Olives, Green_Peppers
	}

	public static class Pizza {
		List<Topping> toppings = new ArrayList<Topping>();
		SauceType sauce;
		boolean hasCheese = false;

		public @Override String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("A delicious pizza with ").append(sauce).append(" sauce,");

			if (hasCheese) {
				sb.append(" cheese,");
			}

			if (toppings.isEmpty()) {
				sb.append(" and no toppings.");
			} else {
				sb.append(" and the following toppings:\n");
				for (Topping topping : toppings) {
					sb.append("\t").append(topping).append("\n");
				}
			}

			return sb.toString();
		}
	}
}
