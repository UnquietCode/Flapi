/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.examples.xhtml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import unquietcode.tools.flapi.examples.xhtml.builder.Element.ElementHelper;
import unquietcode.tools.flapi.examples.xhtml.builder.XHTML.XHTMLHelper;

import javax.xml.parsers.DocumentBuilderFactory;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author Ben Fagin
 * @version 04-27-2012
 */
public class XHTMLHelperImpl implements XHTMLHelper {
	final Document document;

	public XHTMLHelperImpl() {
		try {
			document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public Document done() {
		return document;
	}

	@Override
	public void startElement(String tagName, AtomicReference<ElementHelper> _helper1) {
		Element element = document.createElement(tagName);
		document.appendChild(element);
		_helper1.set(new ElementHelperImpl(element));
	}


	@Override
	public void addComment(String comment) {
		document.appendChild(document.createComment(comment));
	}
}
