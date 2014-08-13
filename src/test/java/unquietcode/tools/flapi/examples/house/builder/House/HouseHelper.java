
package unquietcode.tools.flapi.examples.house.builder.House;

import unquietcode.tools.flapi.examples.house.AffordableHouse;
import unquietcode.tools.flapi.examples.house.ExpensiveHouse;
import unquietcode.tools.flapi.examples.house.Wall;
import unquietcode.tools.flapi.examples.house.builder.Wall.WallHelper;

import javax.annotation.Generated;
import java.util.concurrent.atomic.AtomicReference;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on August 13, 2014 12:17:17 PDT using version 0.0-DEVELOPMENT
 */
@Generated(value = "unquietcode.tools.flapi", date = "2014-08-13T12:17:17-07:00", comments = "generated using Flapi, the fluent API generator for Java")
public interface HouseHelper {
    Wall addWall(AtomicReference<WallHelper> _helper1);

    AffordableHouse constructAffordableHouse();

    ExpensiveHouse constructExpensiveHouse();
}
