package unquietcode.tools.flapi;

import unquietcode.tools.flapi.builder.Descriptor.DescriptorBuilder;
import unquietcode.tools.flapi.builder.Descriptor.DescriptorGenerator;
import unquietcode.tools.flapi.helpers.DescriptorHelperImpl;
import unquietcode.tools.flapi.runtime.ExecutionListener;

import javax.lang.model.SourceVersion;
import java.util.regex.Pattern;

/**
 * @author Ben Fagin
 * @version 05-11-2012
 *
 * From here you can reach the world.
 */
public class Flapi {
	private static SourceVersion JDKVersion = SourceVersion.RELEASE_6;
	private static boolean outputRuntime = false;


	/**
	 * Shortcut to build a new descriptor.
	 */
	public static DescriptorBuilder.$<Void> builder(ExecutionListener...listeners) {
		return DescriptorGenerator.create(new DescriptorHelperImpl(), listeners);
	}

	/**
	 * Set the JDK version that Flapi should use when generating source files.
	 * Certain features are only available in newer versions of the runtime.
	 *
	 * @param version the JDK version to use when generating sources
	 */
	public static void setJDKVersion(SourceVersion version) {
		if (version != null && version.ordinal() >= SourceVersion.RELEASE_5.ordinal()) {
			JDKVersion = version;
		} else {
			throw new DescriptorBuilderException("Only JDK versions [5,7] are supported.");
		}
	}

	/**
	 * @return the JDK version currently being used when generating source files.
	 */
	public static SourceVersion getJDKVersion() {
		return JDKVersion;
	}

	public static boolean shouldOutputRuntime() {
		return outputRuntime;
	}

	public static void shouldOutputRuntime(boolean outputRuntime) {
		Flapi.outputRuntime = outputRuntime;
	}

	/*package*/ static boolean not(boolean condition) {
		return !condition;
	}

	// a regex library for java built with Flapi?
	// docs and everything
	// .anyCharacter()
	// .anyWhiteSpace()

	private static final Pattern PATTERN = Pattern.compile("(\\{(\\s*)})*");

//	/*package*/ static String format(String pattern, Object...data) {
//		Matcher matcher = PATTERN.matcher(pattern);
//		StringBuffer sb = new StringBuffer();
//		int cur = 0;
//
//		while (matcher.find()) {
//			if (cur >= data.length) {
//				throw new RuntimeException("not enough data");
//			}
//
//			matcher.appendReplacement(sb, data[cur++].toString());
//		}
//
//		matcher.appendTail(sb);
//		return sb.toString();
//	}
}
