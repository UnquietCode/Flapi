package unquietcode.tools.flapi;

/**
 * @author Ben Fagin
 * @version 04-24-2012
 *
 * It is what it says it is.
 */
public class ObjectWrapper<_Type> {
	private _Type value;


	public ObjectWrapper() { }

	public ObjectWrapper(_Type value) {
		this.value = value;
	}

	public void set(_Type value) {
		this.value = value;
	}

	public _Type get() {
		return value;
	}

	public @Override String toString() {
		if (value == null) { return null; }
		else { return value.toString(); }
	}
}
