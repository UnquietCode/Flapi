package unquietcode.tools.flapi.examples.xhtml;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.w3c.dom.Document;
import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.builder.DescriptorGenerator;
import unquietcode.tools.flapi.examples.xhtml.builder.ElementBuilder_setValue;
import unquietcode.tools.flapi.examples.xhtml.builder.XHTMLBuilder;
import unquietcode.tools.flapi.examples.xhtml.builder.XHTMLGenerator;
import unquietcode.tools.flapi.helpers.DescriptorHelperImpl;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Benjamin Fagin
 * @version 04-27-2012
 *
 * An example which builds an XHTML builder descriptor.
 */
public class XHTMLBuilderExample {

	@Rule
	public TemporaryFolder temp = new TemporaryFolder();

	@Test
	public void descriptorGenerator() {
		main(new String[]{temp.getRoot().getAbsolutePath()});
	}

	public static void main(String[] args) {
		Descriptor builder =
			DescriptorGenerator.create(new DescriptorHelperImpl())
				.setPackage("unquietcode.tools.flapi.examples.xhtml.builder")
				.setStartingMethodName("createDocument")
				.setDescriptorName("XHTML")

				.addMethod("addComment(String comment)").any()
				.addMethod("done()").last(Document.class)

				.startBlock("Element", "startElement(String tagName)").any()
					.addMethod("setValue(String value)").atMost(1)
					.addMethod("addAttribute(String key, String value)").any()
					.addMethod("addComment(String comment)").any()
					.addMethod("endElement()").last()
					.addBlockReference("Element", "startElement(String tagName)").any()
				.endBlock()
			.build()
		;

		builder.writeToFolder(args[0]);
	}

	@Test
	public void usage() {
		Document doc = XHTMLGenerator.createDocument(new XHTMLHelperImpl())
			.addComment("This is a list of books in my library.")
			.startElement("books")
				.startElement("book")
					.addAttribute("ISBN", "978-0375703768")
					.setValue("House of Leaves")
				.endElement()
				.startElement("book")
					.addAttribute("ISBN", "978-0399533457")
					.setValue("The Cloudspotter's Guide")
				.endElement()
			.endElement()
		.done();

		printDocument(doc);
	}

	@Test
	public void dividedUsage() {
		Document doc;
		ElementBuilder_setValue<XHTMLBuilder<Void>> movies
			= XHTMLGenerator.createDocument(new XHTMLHelperImpl())
			.addComment("This is a list of movies in my library.")
			.startElement("movies");

		for (Movie movie : getMovieList()) {
			movies.startElement(movie.mediaType)
			.setValue(movie.title)
			.endElement();
		}

		doc = movies.endElement().done();
		printDocument(doc);
	}

	private List<Movie> getMovieList() {
		List<Movie> movies = new ArrayList<Movie>();
		Movie movie;

		movie = new Movie();
		movie.mediaType = "DVD";
		movie.title = "Hedwig and the Angry Inch";
		movies.add(movie);

		movie = new Movie();
		movie.mediaType = "DVD";
		movie.title = "Best in Show";
		movies.add(movie);

		movie = new Movie();
		movie.mediaType = "VHS";
		movie.title = "Blow Dry";
		movies.add(movie);

		return movies;
	}

	static class Movie {
		String mediaType;
		String title;
	}

	private void printDocument(Document doc) {
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "4");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

			System.out.println();
			transformer.transform(new DOMSource(doc), new StreamResult(System.out));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
}