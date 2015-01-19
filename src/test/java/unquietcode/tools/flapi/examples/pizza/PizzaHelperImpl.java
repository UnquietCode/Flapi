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
