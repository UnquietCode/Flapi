/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.helpers;

import unquietcode.tools.flapi.builder.Documentation.DocumentationHelper;
import unquietcode.tools.flapi.outline.MethodOutline;

/**
 * @author Ben Fagin
 * @version 01-27-2013
 */
public class DocumentationHelperImpl implements DocumentationHelper {
	private final MethodOutline method;
	private final StringBuilder buffer = new StringBuilder();


	public DocumentationHelperImpl(MethodOutline method) {
		this.method = method;
	}

	@Override
	public void addContent(String content) {
		if (content == null) {
			throw new IllegalArgumentException("Content cannot be null!");
		}

		buffer.append(content);
	}

	@Override
	public void finish() {
		method.setDocumentation(buffer.toString());
	}
}
