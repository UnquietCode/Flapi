
/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.examples.house.builder.House;

import unquietcode.tools.flapi.examples.house.AffordableHouse;
import unquietcode.tools.flapi.examples.house.ExpensiveHouse;
import unquietcode.tools.flapi.examples.house.Wall;
import unquietcode.tools.flapi.examples.house.builder.Wall.WallBuilder_setColor_setWidth;
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
public interface HouseBuilder<_ReturnType> {
    @MethodInfo(type = TransitionType.Terminal, chain = {
        WallBuilder_setColor_setWidth.class
    })
    WallBuilder_setColor_setWidth<Wall> addWall();

    @MethodInfo(type = TransitionType.Terminal, chain = {

    })
    AffordableHouse constructAffordableHouse();

    @MethodInfo(type = TransitionType.Terminal, chain = {

    })
    ExpensiveHouse constructExpensiveHouse();

    public interface $<_ReturnType>
        extends HouseBuilder<_ReturnType>
    {

    }
}
