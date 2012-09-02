package unquietcode.tools.flapi;

import org.junit.Test;

import java.io.File;
import java.util.Scanner;

/**
 * @author Benjamin Fagin
 * @version 04-25-2012
 *
 * Test which should produce the actual descriptor used by the tool.
 * Very exciting!
 */
public class ActualDescriptorTest extends AbstractCompiledTest {

	@Test
	public void test() {
		MainDescriptor.main(new String[]{getTemporaryFolder()});
		testCompile();
	}


	/*
		Useful for printing out individual files from the generation for inspection,
		for diagnostic purposes.
	 */
	//@Test
	public void generateAndRetrieve() throws Exception {
		MainDescriptor.main(new String[]{getTemporaryFolder()});
		String print[] = {"DescriptorGenerator"};

		for (String name : print) {
			String path = getTemporaryFolder();
			path += "/unquietcode/tools/flapi/builder/" +name+".java";
			File file = new File(path);

			Scanner scanner = new Scanner(file);
			String text = scanner.useDelimiter("\\A").next();

			System.out.println(name+":");
			System.out.println("---------------------------------");
			System.out.println(text);
			System.out.println("\n\n");
		}
	}
}