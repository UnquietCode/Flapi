/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi;

/**
 * @author Ben Fagin
 * @version 2014-07-22
 */
public class ClassReference {
	private final String fqcn;

	public ClassReference(String fqcn) {
		if (fqcn == null || fqcn.trim().isEmpty()) {
			throw new IllegalArgumentException("a fully qualified class name is required");
		}
		this.fqcn = fqcn;
	}

	public String getFQCN() {
		return fqcn;
	}
}