package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin (Nokia)
 * @version 03-07-2012
 */
public interface BlockChainBuilder<_SelfType, _ReturnType> {
	_ReturnType addBlockReference(String blockName);
	BlockBuilder<_ReturnType> startBlock(String blockName);



	/*

		when last, returns _ReturnType
		when it has a chain, include that chain

		the 'self' is really a known interface plus return type. that's the full type
		


		Could maybe structure the call chain? This would make rendering a snap, much easier than
		this ridiculousness.

		So, build a tree structure of all return types and all that.

		Then render it
	 */
}
