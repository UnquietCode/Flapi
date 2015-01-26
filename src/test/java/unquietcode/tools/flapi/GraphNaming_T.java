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

package unquietcode.tools.flapi;

import org.junit.Test;
import unquietcode.tools.flapi.generator.GeneratorContext;
import unquietcode.tools.flapi.generator.naming.DefaultNameGenerator;
import unquietcode.tools.flapi.graph.components.LateralTransition;
import unquietcode.tools.flapi.graph.components.StateClass;
import unquietcode.tools.flapi.graph.components.Transition;
import unquietcode.tools.flapi.java.MethodSignature;
import unquietcode.tools.flapi.java.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class GraphNaming_T {


	@Test
	public void twoMethodsInDifferentStatesYieldsDifferentStateNames() throws ParseException {
		Transition t1 = new LateralTransition();
		t1.info().setMethodSignature(new MethodSignature("method(int a, int b)"));
		t1.info().setMaxOccurrences(1);

		Transition t2 = new LateralTransition();
		t2.info().setMethodSignature(new MethodSignature("method(int a)"));
		t2.info().setMaxOccurrences(1);

		GeneratorContext ctx = new GeneratorContext("");
		ctx.setNameGenerator(new DefaultNameGenerator() {
			public @Override String builderName(String stateName) {
				return stateName;
			}
		});

		StateClass sc1 = new StateClass();
		sc1.setName("State");
		sc1.addTransitions(t1);
		String s1 = ctx.getGeneratedName(sc1);

		StateClass sc2 = new StateClass();
		sc2.setName("State");
		sc2.addTransitions(t2);
		String s2 = ctx.getGeneratedName(sc2);

		assertFalse(s1.equals(s2));
	}

	/**
	 * We expect one to be lettered and one not to be.
	 */
	@Test
	public void twoMethodsInTheSameClass() throws ParseException {
		Transition t1 = new LateralTransition();
		t1.info().setMethodSignature(new MethodSignature("method(int a, int b)"));
		t1.info().setMaxOccurrences(1);

		Transition t2 = new LateralTransition();
		t2.info().setMethodSignature(new MethodSignature("method(int a)"));
		t2.info().setMaxOccurrences(1);

		StateClass sc1 = new StateClass();
		sc1.setName("State");
		sc1.addTransitions(t1, t2);

		GeneratorContext ctx = new GeneratorContext("");
		ctx.setNameGenerator(new DefaultNameGenerator() {
			public @Override String builderName(String stateName) {
				return stateName;
			}
		});

		String name = ctx.getGeneratedName(sc1);
		assertEquals("expected the same name", "State_2method_2method_1A", name);
	}
}

