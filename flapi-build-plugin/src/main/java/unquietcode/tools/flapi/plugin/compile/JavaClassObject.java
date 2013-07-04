package unquietcode.tools.flapi.plugin.compile;

import javax.tools.SimpleJavaFileObject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class JavaClassObject extends SimpleJavaFileObject {

	private final File file;


	/**
	 * Registers the compiled class object under URI
	 * containing the class full name
	 *
	 * @param name
	 *            Full name of the compiled class
	 * @param kind
	 *            Kind of the data. It will be CLASS in our case
	 */
	public JavaClassObject(String name, Kind kind, File output) {
		super(URI.create("string:///" + name.replace('.', '/')
				+ kind.extension), kind);

		this.file = output;
	}

	/**
	 * Will provide the compiler with an output stream that leads
	 * to our byte array. This way the compiler will write everything
	 * into the byte array that we will instantiate later
	 */
	@Override
	public OutputStream openOutputStream() throws IOException {
		return new FileOutputStream(file);
	}
}