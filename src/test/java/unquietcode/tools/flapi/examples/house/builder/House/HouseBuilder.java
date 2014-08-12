
package unquietcode.tools.flapi.examples.house.builder.House;

import unquietcode.tools.flapi.examples.house.AffordableHouse;
import unquietcode.tools.flapi.examples.house.ExpensiveHouse;
import unquietcode.tools.flapi.examples.house.Wall;
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
 * Generated on August 12, 2014 13:17:30 PDT using version 0.0-DEVELOPMENT
 */
@Generated(value = "unquietcode.tools.flapi", date = "2014-08-12T13:17:30-07:00", comments = "generated using Flapi, the fluent API generator for Java")
public interface HouseBuilder<_ReturnType> {
    public interface Start<_ReturnType>
        extends HouseBuilder<_ReturnType>
    {
        @MethodInfo(type = TransitionType.Terminal, chain = {
            unquietcode.tools.flapi.examples.house.builder.Wall.WallBuilder.Start.class
        })
        unquietcode.tools.flapi.examples.house.builder.Wall.WallBuilder.Start<Wall> addWall();

        @MethodInfo(type = TransitionType.Terminal)
        AffordableHouse constructAffordableHouse();

        @MethodInfo(type = TransitionType.Terminal)
        ExpensiveHouse constructExpensiveHouse();
    }
}
