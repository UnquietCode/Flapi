/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi;

public class DescriptorBuilderException extends RuntimeException {

	public DescriptorBuilderException(String message) {
		super(message);
	}

	public DescriptorBuilderException(Throwable cause) {
		super(cause);
	}

	public DescriptorBuilderException(String message, Throwable cause) {
		super(message, cause);
	}
}