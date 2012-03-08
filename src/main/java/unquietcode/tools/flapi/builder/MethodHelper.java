package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
public interface MethodHelper {
	void once();
	void any();
	void first();
	void last();
	void atLeast(int num);
	void atMost(int num);
	void between(int atLeast, int atMost);
}
