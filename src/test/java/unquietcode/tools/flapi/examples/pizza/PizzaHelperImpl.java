package unquietcode.tools.flapi.examples.pizza;


import unquietcode.tools.flapi.examples.pizza.builder.Pizza.PizzaHelper;

/**
 * @author Ben Fagin
 * @version 04-28-2012
 */
public class PizzaHelperImpl implements PizzaHelper {
	private final DisappearingPizzaExample.Pizza pizza = new DisappearingPizzaExample.Pizza();

	@Override
	public void addSauce(DisappearingPizzaExample.SauceType sauceType) {
		pizza.sauce = sauceType;
	}

	@Override
	public void addTopping(DisappearingPizzaExample.Topping topping) {
		pizza.toppings.add(topping);
	}

	@Override
	public void addCheese() {
		pizza.hasCheese = true;
	}

	@Override
	public DisappearingPizzaExample.Pizza bake() {
		new Thread() {
			int time = 0;

			public @Override void run() {
				while (true) {
					if (time++ == 3) {
						System.out.println("done\n");
						break;
					}

					System.out.println("baking...");
					try {
						sleep(1000);
					} catch (InterruptedException ex) {
						throw new RuntimeException(ex);
					}
				}
			}
		}.run();

		return pizza;
	}
}
