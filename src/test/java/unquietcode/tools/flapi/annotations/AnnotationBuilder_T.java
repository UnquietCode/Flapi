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

	@Test(expected=DescriptorBuilderException.class)
	public void testThatOnlyOneAnnotationIsPermittedPerMethod() {
		Flapi.create(MultiHelper.class).build();
	}

	public interface MultiHelper {
		@Last
		@AtMost(1)
		void done();
	}
}
