/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.tools.flapi.*;
import unquietcode.tools.flapi.MethodParser.JavaType;
import unquietcode.tools.flapi.graph.BlockMethodTracker;
import unquietcode.tools.flapi.graph.components.StateClass;
import unquietcode.tools.flapi.graph.components.Transition;
import unquietcode.tools.flapi.runtime.TransitionType;

import javax.annotation.Generated;
import javax.lang.model.SourceVersion;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ben Fagin
 * @version 03-07-2012
 */
public class GeneratorContext {
	private static final SimpleDateFormat humanReadableDateFormat = new SimpleDateFormat("MMMM dd, yyyy H:mm:ss z");

	private static final SimpleDateFormat iso8601DateFormat; static {
		boolean legacyVersion =
			SourceVersion.latestSupported() == SourceVersion.RELEASE_5
		 || SourceVersion.latestSupported() == SourceVersion.RELEASE_6
		;

		iso8601DateFormat = legacyVersion
						  ? new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
						  : new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
		;
	}

	public final JCodeModel model = new JCodeModel();
	public final BlockMethodTracker helperMethods = new BlockMethodTracker();
	private final JPackage thePackage;
	private final Map<String, JDefinedClass> interfaces = new HashMap<String, JDefinedClass>();
	private final Map<String, JDefinedClass> classes = new HashMap<String, JDefinedClass>();
	private boolean condenseNames = false;

	public GeneratorContext(String rootPackage) {
		if (rootPackage != null && !rootPackage.trim().equals("")) {
			thePackage = model._package(checkPackageName(rootPackage));
		} else {
			thePackage = model.rootPackage();
		}
	}

	private String checkPackageName(String name) {
		name = name.trim();

		if (!SourceVersion.isName(name)) {
			throw new DescriptorBuilderException("Package name '"+name+"' is not allowed.");
		}

		return name;
	}

	public void condenseNames(boolean value) {
		condenseNames = value;
	}

	public Pair<JDefinedClass, Boolean> getOrCreateInterface(String subPackage, String name) {
		JDefinedClass _interface = interfaces.get(name);
		final boolean created = _interface == null;

		if (_interface == null) {
			JPackage _package = subPackage != null && !subPackage.isEmpty()
							  ? thePackage.subPackage(subPackage)
							  : thePackage;
			try {
				_interface = _package._interface(name);
			} catch (JClassAlreadyExistsException ex) {
				throw new DescriptorBuilderException(ex);
			}

			interfaces.put(name, _interface);
			addGeneratedHeader(_interface);
		}

		return new Pair<JDefinedClass, Boolean>(_interface, created);
	}

	public Pair<JDefinedClass, Boolean> getOrCreateClass(String subPackage, String name) {
		JDefinedClass _class = classes.get(name);
		final boolean created = _class == null;

		if (_class == null) {
			JPackage _package = subPackage != null && !subPackage.isEmpty()
						  	  ? thePackage.subPackage(subPackage)
							  : thePackage;
			try {
				_class = _package._class(JMod.PUBLIC, name);
				classes.put(name, _class);
				addGeneratedHeader(_class);
			} catch (JClassAlreadyExistsException ex) {
				throw new DescriptorBuilderException(ex);
			}
		}

		return new Pair<JDefinedClass, Boolean>(_class, created);
	}

	public boolean doesClassExist(String name) {
		return classes.containsKey(name);
	}

	public boolean doesInterfaceExist(String name) {
		return interfaces.containsKey(name);
	}

	//---o---o---o---o---o---o---o--- Name Creation --o---o---o---o---o---o---o---o---o---o---//

	Map<String, String> nameMap = new HashMap<String, String>();
	int nameIdCounter = 1;
	private final Map<String, String> hashToSuffix = new HashMap<String, String>();
	private final Map<String, CharacterGenerator> nameToGenerator = new HashMap<String, CharacterGenerator>();

	/**
	 * Generates a name based on the following rules:
	 *  once => prefix + state name + suffix
	 *  1st method => name$number
	 *  2nd..Nth method => name$letter$number
	 *
	 *  Where 'letter' is for instance id and number is maxOccurs.
	 *  The letter was added to address FLAPI-105, which identified
	 *  a lack of support for two methods with the same name but
	 *  different parameters.
	 *
	 * @param prefix prefix for the name (eg 'Impl')
	 * @param suffix suffix for the name (eg 'Builder')
	 * @param state state to generate a name for
	 * @return the generated name, which should be unique across the graph
	 */
	public String getGeneratedName(String prefix, String suffix, StateClass state) {
		StringBuilder name = new StringBuilder();
		name.append(prefix).append(state.getName()).append(suffix);

		for (Transition transition : state.getTransitions()) {

			// reduce noise by not utilizing all of the available names
			if (transition.getType() == TransitionType.Terminal) {
				continue;
			}

			// also skip Ascending when it's leading nowhere
			if (transition.getType() == TransitionType.Ascending && transition.getOwner().isTopLevel()) {
				continue;
			}

			MethodParser parsed = new MethodParser(transition.getMethodSignature());
			String methodName = parsed.methodName;

			// create the special method+parameter 'hash' key
			String krazyKey = methodName;
			for (Pair<JavaType, String> param : parsed.params) {
				krazyKey += "|"+param.first.typeName;
			}

			// if this specific combo has been seen before, use the existing char
			if (hashToSuffix.containsKey(krazyKey)) {
				methodName += hashToSuffix.get(krazyKey);

			// else create a new suffix
			} else {
				CharacterGenerator characterGen;
				String methodSuffix;

				if (nameToGenerator.containsKey(methodName)) {
					characterGen = nameToGenerator.get(methodName);
					methodSuffix = characterGen.getAndIncrement();
				} else {
					characterGen = new CharacterGenerator("_1");
					nameToGenerator.put(methodName, characterGen);
					methodSuffix = "";  // don't label the first one because generally it's the only one
				}

				methodName += methodSuffix;
				hashToSuffix.put(krazyKey, methodSuffix);
			}

			name.append("_2");

			if (condenseNames) {
				if (nameMap.containsKey(methodName)) {
					name.append(nameMap.get(methodName));
				} else {
					String gen = "m"+(nameIdCounter++);
					name.append(gen);
					nameMap.put(methodName, gen);
				}
			} else {
				name.append(methodName);
			}

			if (transition.info().getMaxOccurrences() > 1) {
				name.append("_3").append(transition.info().getMaxOccurrences());
			}

			name.append("_4").append(transition.info().didTrigger() ? "t" : "f");
		}

		return name.toString();
	}

	//---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---//

	private String header = null;
	private Date generationDate = null;

	private void addGeneratedHeader(JDefinedClass clazz) {
		if (header == null) {
			generationDate = new Date();

			header = new StringBuilder()
				.append("This class was generated using Flapi, the fluent API generator for Java.\n")
				.append("Modifications to this file will be lost upon regeneration.\n")
				.append("You have been warned!\n")
				.append("\n")
				.append("Visit ").append(Constants.PROJECT_URL).append(" for more information.\n")
				.append("\n\n")
				.append("Generated on ").append(humanReadableDateFormat.format(generationDate))
				.append(" using version ").append(Constants.PROJECT_VERSION)
			.toString();
		}

		// javadoc header
		clazz.javadoc().append(header);

		// @Generated, when JDK version is >= 6
		if (Flapi.getJDKVersion().ordinal() >= SourceVersion.RELEASE_6.ordinal()) {
			clazz.annotate(Generated.class)
				.param("value", "unquietcode.tools.flapi")
				.param("date", iso8601DateFormat.format(generationDate))
				.param("comments", "generated using Flapi, the fluent API generator for Java")
			;
		}
	}
}

// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: //
// :::::::::::::::::::::::::: P07470 :::::::::::::::::::::::::::::::: //
// :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: //