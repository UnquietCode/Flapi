package unquietcode.tools.flapi;

import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import javax.tools.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ben Fagin
 * @version 09-01-2012
 *
 * Test harness which will dynamically compile the resulting code model and ensure that
 * no errors occur. Then actions can be performed on the resulting loaded classes.
 */
public abstract class AbstractCompiledTest {

	@Rule
	public TemporaryFolder temp = new TemporaryFolder();

	@After
	public void cleanup() {
		File[] files = temp.getRoot().listFiles();
		if (files == null) { return; }

		for (File file : files) {
			recursiveDelete(file);
		}
	}

	protected String loadResource(String fileName) {
		InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);

		if (is == null) {
			throw new RuntimeException("Could not locate resource '"+fileName+"'");
		}

		return new Scanner(is).useDelimiter("\\A").next();
	}

	protected String addTestClassMethod(String className, String body) {
		String code = "public class "+className+" {\n"+
			"public void test() {\n" +
				body +
			"\n}}"
		;

		return addSourceFile(className, code);
	}

	protected String addSourceFile(String className, String code) {
		FileOutputStream os = null;

		try {
			File f = new File(temp.getRoot(), className+".java");
			os = new FileOutputStream(f);
			os.write(code.getBytes("UTF-8"));

			return f.getAbsolutePath();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			if (os != null) try { os.close(); } catch (Exception ex) { }
		}
	}

	protected void testCompile() {
		List<File> sourceFiles = new ArrayList<File>();
		getFiles(temp.getRoot(), sourceFiles);
		compile(sourceFiles);
	}

	protected void testCompile(String testFile) {
		// read the test data
		String test = loadResource(testFile);

		// find every << ... >> pair
		String regex = "<<(.*)>>";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(test);

		// compile the plain test first
		{
			String testClass = addTestClassMethod("Test", test.replaceAll(regex, ""));
			testCompile();
			new File(testClass).delete();
		}

		// for each pair, compile without the comments
		// and expect a compile-time error
		while (matcher.find()) {
			String currentTest = new StringBuilder()
				.append(test.substring(0, matcher.start()))
				.append(matcher.group(1))
				.append(test.substring(matcher.end() + 1))
			.toString().replaceAll(regex, "");

			String testClass = addTestClassMethod("PartialTest", currentTest);

			try {
				testCompile();
				System.out.println("failed test: \n\n" + currentTest);
				System.out.flush();
				Assert.fail("expected a compilation error");
			} catch (CompilationException ex) {
				if (!ex.getMessage().contains("cannot find symbol")) {
					throw new RuntimeException("unexpected compilation error", ex);
				}
			}

			// remove this particular test
			new File(testClass).delete();
		}
	}

	protected String getTemporaryFolder() {
		return temp.getRoot().getAbsolutePath();
	}

	// - - --  ---------  - - --   -------- - -   - ----- -- ----------  -- ---

	private void recursiveDelete(File directory) {
		File[] files = directory.listFiles();
		if (files == null) { return; }

		for (File file : files) {
			if (file.isDirectory()) {
				recursiveDelete(file);
			} else {
				file.delete();
			}
		}
	}

	private void getFiles(File file, List<File> files) {
		if (file.isFile() && file.getName().endsWith(".java")) {
			files.add(file);
		} else {
			File[] dir = file.listFiles();

			if (dir != null) {
				for (File next : dir) {
					getFiles(next, files);
				}
			}
		}
	}

	private void compile(List<File> sourceFiles) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		StandardJavaFileManager fileManager
			= compiler.getStandardFileManager(diagnostics, Locale.getDefault(), Charset.defaultCharset());

		List<String> options = new ArrayList<String>();
		options.add("-classpath");
		options.add(System.getProperty("java.class.path"));

		Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(sourceFiles);
		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, options, null, compilationUnits);
		task.call();

		try {
			fileManager.close();
		} catch (IOException e) {
			// nothing
		}

		StringBuilder errors = new StringBuilder();
		for (Diagnostic<? extends JavaFileObject> error : diagnostics.getDiagnostics()) {
			if (error.getKind() != Diagnostic.Kind.NOTE) {
				errors.append("\n\n").append(error.getSource().getName())
					  .append(" (").append(error.getLineNumber()).append(",").append(error.getColumnNumber()).append(")\n")
					  .append(error.getMessage(Locale.getDefault()))
				;
			}
		}

		if (errors.length() != 0) {
			throw new CompilationException("The compilation was completed with errors."+errors.toString()+"\n");
		}
	}

	protected static class CompilationException extends RuntimeException {
		public CompilationException(String message) {
			super(message);
		}
	}
}