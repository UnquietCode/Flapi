package unquietcode.tools.flapi.plugin.compile;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import java.io.File;
import java.io.IOException;

public class ClassFileManager extends ForwardingJavaFileManager {
	private final String basePath;

	/**
	 * Will initialize the manager with the specified
	 * standard java file manager
	 */
	public ClassFileManager(StandardJavaFileManager standardManager, String basepath) {
		super(standardManager);
		this.basePath = basepath;
	}

	/**
	 * Gives the compiler an instance of the JavaClassObject
	 * so that the compiler can write the byte code into it.
	 */
	@Override
	public JavaFileObject getJavaFileForOutput(
		Location location, String className, JavaFileObject.Kind kind, FileObject sibling
	) throws IOException {
		File file = new File(basePath+File.separator+className.replace('.', '/')+kind.extension);
		file.getParentFile().mkdirs();
		file.createNewFile();

		return new JavaClassObject(className, kind, file);
	}
}