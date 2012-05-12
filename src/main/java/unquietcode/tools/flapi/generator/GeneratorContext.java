package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.tools.flapi.Constants;
import unquietcode.tools.flapi.DescriptorBuilderException;
import unquietcode.tools.flapi.MethodParser;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Ben Fagin
 * @version 03-07-2012
 */
public class GeneratorContext {
	public final JCodeModel model = new JCodeModel();
	final JPackage thePackage;
	final Map<String, JDefinedClass> interfaces = new HashMap<String, JDefinedClass>();
	final Map<String, JDefinedClass> classes = new HashMap<String, JDefinedClass>();
	private boolean condenseNames = false;

	public GeneratorContext(String rootPackage) {
		if (rootPackage != null) {
			thePackage = model._package(rootPackage.trim());
		} else {
			thePackage = model.rootPackage();
		}
	}


	public void condenseNames(boolean value) {
		condenseNames = value;
	}

	public JDefinedClass getOrCreateInterface(String name) {
		JDefinedClass _interface = interfaces.get(name);
		if (_interface == null) {
			try {
				_interface = thePackage._interface(name);
				interfaces.put(name, _interface);
				addGeneratedHeader(_interface);
			} catch (JClassAlreadyExistsException ex) {
				throw new DescriptorBuilderException(ex);
			}
		}

		return _interface;
	}

	public JDefinedClass getOrCreateClass(String name) {
		JDefinedClass _class = classes.get(name);
		if (_class == null) {
			try {
				_class = thePackage._class(JMod.PUBLIC, name);
				classes.put(name, _class);
				addGeneratedHeader(_class);
			} catch (JClassAlreadyExistsException ex) {
				throw new DescriptorBuilderException(ex);
			}
		}

		return _class;
	}

	//---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---//

	Map<String, String> nameMap = new HashMap<String, String>();
	int nameIdCounter = 1;

	public String getGeneratedName(String suffix, Set<MethodOutline> methods) {
		StringBuilder name = new StringBuilder();
		name.append(name).append(suffix);

		for (MethodOutline method : new TreeSet<MethodOutline>(methods)) {
			MethodParser parsed = new MethodParser(method.getMethodSignature());
			name.append("_");

			if (condenseNames) {
				if (nameMap.containsKey(parsed.methodName)) {
					name.append(nameMap.get(parsed.methodName));
				} else {
					String gen = "m"+(nameIdCounter++);
					name.append(gen);
					nameMap.put(parsed.methodName, gen);
				}
			} else {
				name.append(parsed.methodName);
			}

			if (method.maxOccurrences > 1) {
				name.append("i").append(method.maxOccurrences);
			}
		}

		return name.toString();
	}

	//---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---//

	private String header = null;

	private void addGeneratedHeader(JDefinedClass clazz) {
		if (header == null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy k:mm:ss z");

			header = new StringBuilder()
				.append("This class was generated using Flapi, the fluent API generator for Java.\n")
				.append("Modifications to this file will be lost upon regeneration.\n")
				.append("You have been warned!\n")
				.append("\n")
				.append("Visit ").append(Constants.PROJECT_URL).append(" for more information.\n")
				.append("\n\n")
				.append("Generated on ").append(dateFormat.format(new Date()))
				.append(" using version ").append(Constants.PROJECT_VERSION).append("")
			.toString();
		}

		clazz.javadoc().append(header);
	}
}
