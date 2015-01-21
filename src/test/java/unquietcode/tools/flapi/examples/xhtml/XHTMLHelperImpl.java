/*********************************************************************
 Copyright 2015 the Flapi authors

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
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
