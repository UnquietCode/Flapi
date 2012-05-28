
package unquietcode.tools.flapi.examples.house.builder;

import javax.annotation.Generated;
import unquietcode.tools.flapi.examples.house.AffordableHouse;
import unquietcode.tools.flapi.examples.house.ExpensiveHouse;
import unquietcode.tools.flapi.examples.house.Wall;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 28, 2012 17:35:16 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 28, 2012 17:35:16 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public interface HouseBuilder<_ReturnType >{


    WallBuilder_setColor_setWidth<Wall> addWall();

    AffordableHouse constructAffordableHouse();

    ExpensiveHouse constructExpensiveHouse();

}
