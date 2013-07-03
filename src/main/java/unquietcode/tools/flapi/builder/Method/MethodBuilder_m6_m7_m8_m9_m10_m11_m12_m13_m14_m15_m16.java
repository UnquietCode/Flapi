
package unquietcode.tools.flapi.builder.Method;

import unquietcode.tools.flapi.builder.BlockChain.BlockChainBuilder_m6_m19_m20_m21;
import unquietcode.tools.flapi.runtime.MethodInfo;
import unquietcode.tools.flapi.runtime.TransitionType;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on July 02, 2013 0:08:51 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 02, 2013 0:08:51 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16 <_ReturnType> {
    /**
     * Add a BlockChain, which is a block which must be passed through
     * before the current method returns.
     */
    @MethodInfo(type = TransitionType.Lateral, next = MethodBuilder_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16 .class, chain = {
        BlockChainBuilder_m6_m19_m20_m21 .class
    })
    BlockChainBuilder_m6_m19_m20_m21 <MethodBuilder_m7_m8_m9_m10_m11_m12_m13_m14_m15_m16 <_ReturnType>> addBlockChain();

    /**
     * expose the method only after the specified group is finished
     */
    @MethodInfo(type = TransitionType.Lateral, chain = {

    })
    MethodBuilder_m6_m8_m9_m10_m11_m12_m13_m14_m15_m16 <_ReturnType> after(int group);

    /**
     * expect the method [0, inf) times
     */
    @MethodInfo(type = TransitionType.Ascending, chain = {

    })
    _ReturnType any();

    /**
     * expect the method [X, inf) times
     */
    @MethodInfo(type = TransitionType.Ascending, chain = {

    })
    _ReturnType atLeast(int num);

    /**
     * expect the method [0, X] times
     */
    @MethodInfo(type = TransitionType.Ascending, chain = {

    })
    _ReturnType atMost(int num);

    /**
     * expect the method [0, X] times, and assign a group number
     */
    @MethodInfo(type = TransitionType.Ascending, chain = {

    })
    _ReturnType atMost(int num, int group);

    /**
     * expect the method [atLeast, atMost] times
     */
    @MethodInfo(type = TransitionType.Ascending, chain = {

    })
    _ReturnType between(int atLeast, int atMost);

    /**
     * expect the method [X, X] times
     */
    @MethodInfo(type = TransitionType.Ascending, chain = {

    })
    _ReturnType exactly(int num);

    /**
     * mark the method as terminal, exiting the block when called
     */
    @MethodInfo(type = TransitionType.Ascending, chain = {

    })
    _ReturnType last();

    /**
     * mark the method as terminal, returning an object of the given type when called
     */
    @MethodInfo(type = TransitionType.Ascending, chain = {

    })
    _ReturnType last(Class returnType);

    /**
     * Marks this method with a Deprecated annotation.
     * Also adds a note to the Javadocs.
     */
    @MethodInfo(type = TransitionType.Lateral, chain = {

    })
    MethodBuilder_m6_m7_m8_m9_m10_m11_m12_m13_m14_m15 <_ReturnType> markAsDeprecated(String reason);
}
