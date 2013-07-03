
package unquietcode.tools.flapi.builder.Descriptor;

import unquietcode.tools.flapi.builder.Descriptor.DescriptorBuilder.$;
import unquietcode.tools.flapi.runtime.BlockInvocationHandler;
import unquietcode.tools.flapi.runtime.ExecutionListener;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 *
 * Visit https://github.com/UnquietCode/Flapi for more information.
 *
 *
 * Generated on July 02, 2013 19:55:15 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 02, 2013 19:55:15 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public class DescriptorGenerator {
	public static $<Void> create(DescriptorHelper helper, ExecutionListener... listeners) {
		if (helper == null) {
			throw new IllegalArgumentException("Helper cannot be null.");
		}

		BlockInvocationHandler handler = new BlockInvocationHandler(helper, null);
		handler.addListeners(listeners);
		return handler._proxy($.class);
	}
}