/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/
package unquietcode.tools.flapi.helpers;

import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.configurator.DescriptorConfigurator.DescriptorConfiguratorHelper;
import unquietcode.tools.flapi.outline.DescriptorOutline;

import java.util.Objects;

/**
 * @author Ben Fagin
 * @version 08-03-2014
 */
public class DescriptorConfiguratorHelperImpl implements DescriptorConfiguratorHelper {
	final DescriptorOutline outline;

	public DescriptorConfiguratorHelperImpl(DescriptorOutline outline) {
		this.outline = Objects.requireNonNull(outline);
	}

	@Override
	public Descriptor build() {
		outline.prepare();
		return new Descriptor(outline);
	}

	@Override
	public void setPackage(String packageName) {
		outline.setPackageName(packageName);
	}

	@Override
	public void setDescriptorName(String descriptorName) {
		if (descriptorName == null || descriptorName.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty.");
		}
		outline.setDescriptorName(descriptorName);
	}

	@Override
	public void setReturnType(Class returnType) {
		if (returnType == null) {
			throw new IllegalArgumentException("Return type cannot be null.");
		}
		setReturnType(returnType.getName());
	}

	@Override
	public void setReturnType(String returnType) {
		if (returnType == null) {
			throw new IllegalArgumentException("Return type cannot be null.");
		}
		outline.selfBlock.setReturnType(returnType);
	}

	@Override
	public void setStartingMethodName(String methodName) {
		if (methodName == null || methodName.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty.");
		}
		outline.setCreateMethod(methodName);
	}

	@Override
	public void enableCondensedClassNames() {
		outline.enableCondensedNames(true);
	}
}