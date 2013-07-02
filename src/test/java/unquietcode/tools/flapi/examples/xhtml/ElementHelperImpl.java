package unquietcode.tools.flapi.examples.xhtml;

import org.w3c.dom.Comment;
import org.w3c.dom.Element;
import unquietcode.tools.flapi.examples.xhtml.builder.Element.ElementHelper;

import java.util.concurrent.atomic.AtomicReference;

/*
 * @author Ben Fagin
 * @version 04-27-2012
 */
public class ElementHelperImpl implements ElementHelper {
	private final Element element;

	ElementHelperImpl(Element element) {
		this.element = element;
	}

	@Override
	public void endElement() {
		// nothing
	}

	@Override
	public void startElement(String tagName, AtomicReference<ElementHelper> _helper1) {
		Element newElement = element.getOwnerDocument().createElement(tagName);
		element.appendChild(newElement);
		_helper1.set(new ElementHelperImpl(newElement));
	}

	@Override
	public void addAttribute(String key, String value) {
		element.setAttribute(key, value);
	}

	@Override
	public void addComment(String comment) {
		Comment commentNode = element.getOwnerDocument().createComment(comment);
		element.appendChild(commentNode);
	}

	@Override
	public void setValue(String value) {
		element.setTextContent(value);
	}
}
