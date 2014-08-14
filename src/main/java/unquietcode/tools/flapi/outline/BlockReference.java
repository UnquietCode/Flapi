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

package unquietcode.tools.flapi.outline;


import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Ben Fagin
 * @version 03-10-2012
 *
 * A block which serves as a reference to an already created block.
 * Instances should therefore not be treated as 'definitions' of
 * blocks but rather containers of information related to an existing
 * block. Certain properties however, such as the {@link #getConstructor()},
 * are unique to each {@link BlockOutline}, including references.
 */
public class BlockReference extends BlockOutline {
	private final BlockOutline directReference;

	public BlockReference(BlockOutline directReference) {
		this.directReference = checkNotNull(directReference);
	}

	public BlockReference(String name) {
		directReference = null;
		setName(name);
	}

	public BlockOutline directReference() {
		return directReference;
	}
}