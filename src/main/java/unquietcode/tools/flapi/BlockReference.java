package unquietcode.tools.flapi;

import unquietcode.tools.flapi.outline.BlockOutline;
import unquietcode.tools.flapi.outline.MethodOutline;

/**
 * @author Ben Fagin
 * @version 03-10-2012
 *
 * A block which serves as a reference to an already created block.
 * Instances should therefore not be treated as 'definitions' of
 * blocks but rather containers of information related to an existing
 * block. Certain properties however, such as the {@link #constructor},
 * are unique to each {@link BlockOutline}, including references.
 */
public class BlockReference extends BlockOutline {

}
