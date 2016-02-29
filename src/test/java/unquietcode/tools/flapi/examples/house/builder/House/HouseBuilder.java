
package unquietcode.tools.flapi.examples.house.builder.House;

import unquietcode.tools.flapi.examples.house.AffordableHouse;
import unquietcode.tools.flapi.examples.house.ExpensiveHouse;
import unquietcode.tools.flapi.examples.house.Wall;
import unquietcode.tools.flapi.runtime.ChainInfo;
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
 * Generated on February 28, 2016 16:29:18 PST using version 0.0-DEVELOPMENT
 */
@Generated(value = "unquietcode.tools.flapi", date = "2016-02-28T16:29:18-08:00", comments = "generated using Flapi, the fluent API generator for Java, version 0.0-DEVELOPMENT")
public interface HouseBuilder<_ReturnType> {
    @MethodInfo(type = TransitionType.Terminal, chainInfo = {
        @ChainInfo(type = unquietcode.tools.flapi.examples.house.builder.Wall.WallBuilder.Head.class, position = 0)
    })
    unquietcode.tools.flapi.examples.house.builder.Wall.WallBuilder.Head<Wall> addWall();

    @MethodInfo(type = TransitionType.Terminal)
    AffordableHouse constructAffordableHouse();

    @MethodInfo(type = TransitionType.Terminal)
    ExpensiveHouse constructExpensiveHouse();


    /**
     * Marker interface denoting the main entry point for this block.
     */
    public interface Head<_ReturnType>
        extends HouseBuilder<_ReturnType>
    {

    }


    /**
     * Marker interface denoting the main entry point for this descriptor.
     */
    public interface Start
        extends HouseBuilder.Head<Void>
    {

    }
}
