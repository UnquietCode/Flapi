/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
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