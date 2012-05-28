/*******************************************************************************
 Copyright 2012 Benjamin Fagin

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.


     Read the included LICENSE.TXT for more information.
 ******************************************************************************/

package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.tools.flapi.Constants;
import unquietcode.tools.flapi.DescriptorBuilderException;
import unquietcode.tools.flapi.Flapi;
import unquietcode.tools.flapi.MethodParser;
import unquietcode.tools.flapi.outline.MethodOutline;

import javax.annotation.Generated;
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
	private final Map<String, JDefinedClass> interfaces = new HashMap<String, JDefinedClass>();
	private final Map<String, JDefinedClass> classes = new HashMap<String, JDefinedClass>();
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

	public boolean doesClassExist(String name) {
		return classes.containsKey(name);
	}

	public boolean doesInterfaceExist(String name) {
		return interfaces.containsKey(name);
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
	private Date generationDate = null;

	private void addGeneratedHeader(JDefinedClass clazz) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy k:mm:ss z");

		if (header == null) {
			generationDate = new Date();

			header = new StringBuilder()
				.append("This class was generated using Flapi, the fluent API generator for Java.\n")
				.append("Modifications to this file will be lost upon regeneration.\n")
				.append("You have been warned!\n")
				.append("\n")
				.append("Visit ").append(Constants.PROJECT_URL).append(" for more information.\n")
				.append("\n\n")
				.append("Generated on ").append(dateFormat.format(generationDate))
				.append(" using version ").append(Constants.PROJECT_VERSION).append("")
			.toString();
		}

		// javadoc header
		clazz.javadoc().append(header);

		// @Generated, when JDK version is >= 6
		if (Flapi.getJDKVersion() >= 6) {
			clazz.annotate(Generated.class)
				.param("value", "unquietcode.tools.flapi")
				.param("date", dateFormat.format(generationDate))
				.param("comments", "generated using Flapi, the fluent API generator for Java")
			;
		}
	}
}
