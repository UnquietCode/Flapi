package unquietcode.tools.flapi.generator;

import com.sun.codemodel.*;
import unquietcode.tools.flapi.MethodParser;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-07-2012
 */
public class GeneratorContext {

	public final JCodeModel model = new JCodeModel();
	final JPackage thePackage;
	final Map<String, JDefinedClass> interfaces = new HashMap<String, JDefinedClass>();
	final Map<String, JDefinedClass> classes = new HashMap<String, JDefinedClass>();
	boolean condenseNames = false;


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
			} catch (JClassAlreadyExistsException ex) {
				throw new RuntimeException(ex);
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
			} catch (JClassAlreadyExistsException ex) {
				throw new RuntimeException(ex);
			}
		}

		return _class;
	}




	//---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---o---//

}
