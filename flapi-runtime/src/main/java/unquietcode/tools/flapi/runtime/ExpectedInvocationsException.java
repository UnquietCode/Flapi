package unquietcode.tools.flapi.runtime;

/**
 * @author Ben Fagin
 * @version 05-31-2012
 *
 * Thrown when the number of invocations of a particular method
 * is greater or less than the expected number, as determined
 * by the individual descriptor.
 */
public class ExpectedInvocationsException extends RuntimeException {
	public ExpectedInvocationsException(String message) {
		super(message);
	}

	public ExpectedInvocationsException(Throwable cause) {
		super(cause);
	}

	public ExpectedInvocationsException(String message, Throwable cause) {
		super(message, cause);
	}
}
