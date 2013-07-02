
package unquietcode.tools.flapi.examples.house.builder.House;

import unquietcode.tools.flapi.examples.house.AffordableHouse;
import unquietcode.tools.flapi.examples.house.ExpensiveHouse;
import unquietcode.tools.flapi.examples.house.Wall;
import unquietcode.tools.flapi.examples.house.builder.Wall.WallBuilder_setColor_setWidth;
import unquietcode.tools.flapi.support.MethodInfo;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on July 01, 2013 21:53:50 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "July 01, 2013 21:53:50 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface HouseBuilder<_ReturnType> {
    @MethodInfo(checkInvocations = true, checkParentInvocations = true, type = 2, chain = {
        WallBuilder_setColor_setWidth.class
    })
    WallBuilder_setColor_setWidth<Wall> addWall();

    @MethodInfo(checkInvocations = true, checkParentInvocations = true, type = 2, chain = {

    })
    AffordableHouse constructAffordableHouse();

    @MethodInfo(checkInvocations = true, checkParentInvocations = true, type = 2, chain = {

    })
    ExpensiveHouse constructExpensiveHouse();

    public interface $<_ReturnType>
        extends HouseBuilder<_ReturnType>
    {

    }
}
