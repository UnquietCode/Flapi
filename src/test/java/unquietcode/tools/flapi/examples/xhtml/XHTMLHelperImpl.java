package unquietcode.tools.flapi.examples.xhtml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import unquietcode.tools.flapi.ObjectWrapper;
import unquietcode.tools.flapi.examples.xhtml.builder.ElementHelper;
import unquietcode.tools.flapi.examples.xhtml.builder.XHTMLHelper;

import javax.xml.parsers.DocumentBuilderFactory;

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
	public Document _getReturnValue() {
		return document;
	}

	@Override
	public void done() {
		// nothing
	}

	@Override
	public void startElement(String tagName, ObjectWrapper<ElementHelper> _helper1) {
		Element element = document.createElement(tagName);
		document.appendChild(element);
		_helper1.set(new ElementHelperImpl(element));
	}


	@Override
	public void addComment(String comment) {
		document.appendChild(document.createComment(comment));
	}
}
