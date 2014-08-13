/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi;

import com.sun.codemodel.JCodeModel;
import unquietcode.tools.flapi.generator.DescriptorGenerator;
import unquietcode.tools.flapi.outline.DescriptorOutline;

import java.io.File;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public class Descriptor {
	private final JCodeModel model;


	public Descriptor(DescriptorOutline outline) {
		DescriptorGenerator generator = new DescriptorGenerator(outline);
		model = generator.generate();
	}

	/**
	 * Write the generated classes to a stream. The order is generally alphabetic
	 * but is not guaranteed. Several lines of separator are written out between
	 * each file.
	 *
	 * @see com.sun.codemodel.writer.SingleStreamCodeWriter
	 * @param stream to write generated files
	 */
	public Descriptor writeToStream(OutputStream stream) {
		CodeWriter.writeToStream(model, stream);
		return this;
	}

	/**
	 * Write the generated classes out to the specified folder.
	 * Classes in other packages will be written to folders relative
	 * to this one.
	 *
	 * Existing files will be overwritten!
	 *
	 * @see com.sun.codemodel.writer.FileCodeWriter
	 * @param folder where files are to be written
	 */
	public Descriptor writeToFolder(String folder) {
		File f = new File(folder);
		if (!f.exists()) {
			throw new DescriptorBuilderException("Folder '"+folder+"' does not exist.");
		}

		if (!f.isDirectory()) {
			throw new DescriptorBuilderException("Folder '"+folder+"' is not a folder!");
		}

		if (!f.canWrite()) {
			throw new DescriptorBuilderException("Cannot write to folder '"+folder+"'.");
		}

		// write out the support classes, if instructed
		if (Flapi.shouldOutputRuntime()) {
			try {
				ExtractRuntime.main(new String[]{folder});
			} catch (Exception ex) {
				throw new DescriptorBuilderException(ex);
			}
		}

		// always do this last so we don't unnecessarily write files
		CodeWriter.writeToDirectory(model, f);

		return this;
	}

	/**
	 * Writes individual files to individual streams.
	 * @param streams stream iterator, should always return another!
	 */
	public Map<String, OutputStream> writeToStreams(Iterator<OutputStream> streams) {
		if (streams == null) {
			throw new IllegalArgumentException("streams should not be null");
		}
		return CodeWriter.writeToStreams(model, streams);
	}
}
