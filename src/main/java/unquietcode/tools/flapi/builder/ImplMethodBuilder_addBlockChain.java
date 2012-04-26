package unquietcode.tools.flapi.builder;

/**
 * @author Ben Fagin
 * @version 04-23-2012
 */
public class ImplMethodBuilder_addBlockChain<_ReturnType> extends ImplMethodBuilder<_ReturnType> implements MethodBuilder_addBlockChain<_ReturnType> {

	ImplMethodBuilder_addBlockChain(MethodHelper helper, _ReturnType returnValue) {
		super(helper, returnValue);
	}

	@Override
	public BlockChainBuilder_addBlockChain<MethodBuilder<_ReturnType>> addBlockChain() {
		BlockChainHelper helper = (BlockChainHelper) _helper.addBlockChain().get(0);
		return new ImplBlockChainBuilder_addBlockChain(helper, this);
	}
}
