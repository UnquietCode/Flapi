/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.annotations;

import org.junit.Test;
import unquietcode.tools.flapi.DescriptorBuilderException;
import unquietcode.tools.flapi.Flapi;

/**
 * @author Ben Fagin
 * @version 2014-08-03
 */
public class AnnotationBuilder_T {

	@Test(expected=DescriptorBuilderException.class)
	public void testThatOnlyLastMethodsCanReturnValues() {
		Flapi.create(MyHelper.class).build();
	}

	public interface MyHelper {
		@Any Integer one();
		@Last String two();
	}
}
