
package unquietcode.tools.flapi.builder;

import unquietcode.tools.flapi.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 09, 2012 23:25:34 CDT using version 0.1
 * 
 */
public interface BlockChainHelper {


    void addBlockReference(String blockName);

    void addBlockChain(ObjectWrapper<BlockChainHelper> _helper1);

    void startBlock(String blockName, String methodSignature, ObjectWrapper<BlockHelper> _helper1);

}
