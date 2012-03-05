package unquietcode.tools.flapi;


/**
 * @author Benjamin Fagin
 * @version 12-30-2011
 */
public interface DescriptorBuilder<_ReturnValue> {
	void build();
	MethodInterface<_ReturnValue> startBlock(String blockName, String methodSignature);
	MethodInterface<_ReturnValue> startBlock(String blockName);
}

// TODO block signatures, how when where?