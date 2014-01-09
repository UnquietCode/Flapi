/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.writer.FileCodeWriter;
import com.sun.codemodel.writer.SingleStreamCodeWriter;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Ben Fagin
 * @version 03-10-2012
 */
public class CodeWriter {

	public static void writeToStream(JCodeModel model, OutputStream stream) {
		SingleStreamCodeWriter writer = new SingleStreamCodeWriter(stream);

		try {
			model.build(writer);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public static void writeToDirectory(JCodeModel model, File directory) {
		try {
			FileCodeWriter fileWriter = new FileCodeWriter(directory, false);
			model.build(fileWriter);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static Map<String, OutputStream> writeToStreams(JCodeModel model, final Iterator<OutputStream> streams) {
		final Map<String,OutputStream> map = new HashMap<String, OutputStream>();

		try {
			model.build(new com.sun.codemodel.CodeWriter() {
				public @Override OutputStream openBinary(JPackage pkg, String fileName) throws IOException {
					String pkgName = pkg.name();

					if (pkgName.length() != 0) {
						pkgName += '.';
					}

					String fqcn = pkgName+fileName;
					OutputStream stream = streams.next();
					map.put(fqcn, stream);

					return stream;
				}

				@Override
				public void close() throws IOException {
					// nothing
				}
			});
		} catch (IOException ex) {
			throw new DescriptorBuilderException(ex);
		}

		return map;
	}
}
