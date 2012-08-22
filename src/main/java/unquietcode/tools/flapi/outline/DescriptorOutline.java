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

package unquietcode.tools.flapi.outline;


/**
 * @author Ben Fagin
 * @version 03-07-2012
 */
public class DescriptorOutline implements Outline {
	private String packageName;
	private boolean enableCondensedNames = false;
	public final BlockOutline selfBlock = new BlockOutline(null);
	private final GeneratorOutline generator = new GeneratorOutline(selfBlock);

	public DescriptorOutline() {
		selfBlock.setIsTopLevel();
	}

	public GeneratorOutline getGenerator() {
		return generator;
	}
	
	public String getPackageName() {
		return packageName;
	}

	public void enableCondensedNames(boolean value) {
		enableCondensedNames = value;
	}

	public boolean shouldEnableCondensedNames() {
		return enableCondensedNames;
	}

	public void setDescriptorName(String name) {
		selfBlock.setName(name);
	}

	public void setCreateMethod(String methodName) {
		generator.methodName = methodName;
	}

	public String getCreateMethod() {
		return generator.methodName;
	}
	
	public MethodOutline addMethod(String methodSignature) {
		return selfBlock.addMethod(methodSignature);
	}

	public BlockOutline addBlock(String blockName) {
		return selfBlock.addBlock(blockName);
	}
	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
}
