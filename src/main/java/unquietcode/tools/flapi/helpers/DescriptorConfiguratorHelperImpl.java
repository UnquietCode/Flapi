/*********************************************************************
 Copyright 2014 the Flapi authors

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ********************************************************************/

package unquietcode.tools.flapi.helpers;

import unquietcode.tools.flapi.Descriptor;
import unquietcode.tools.flapi.configurator.DescriptorConfigurator.DescriptorConfiguratorHelper;
import unquietcode.tools.flapi.outline.DescriptorOutline;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Ben Fagin
 * @version 08-03-2014
 */
public class DescriptorConfiguratorHelperImpl implements DescriptorConfiguratorHelper {
	final DescriptorOutline outline;

	public DescriptorConfiguratorHelperImpl(DescriptorOutline outline) {
		this.outline = checkNotNull(outline);
	}

	@Override
	public Descriptor build() {
		outline.prepare();
		return new Descriptor(outline);
	}

	@Override
	public void setStartingMethodName(String methodName) {
		if (methodName == null || methodName.trim().isEmpty()) {
			throw new IllegalArgumentException("Name cannot be empty.");
		}
		outline.setCreateMethod(methodName);
	}

	@Override
	public void setPackage(String packageName) {
		outline.setPackageName(packageName);
	}

	@Override
	public void enableCondensedClassNames() {
		outline.enableCondensedNames(true);
	}

	@Override
	public void disableTimestamps() {
		outline.disableTimestamps(true);
	}
}