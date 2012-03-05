package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-04-2012
 */
public interface MethodBuilder<_ReturnType> {
	_ReturnType once();
	_ReturnType any();
	_ReturnType only();
	_ReturnType atLeast(int num);
	_ReturnType atMost(int num);
	_ReturnType between(int atLeast, int atMost);
}
