
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
 * Generated on May 28, 2012 15:38:23 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "May 28, 2012 15:38:23 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplHouseBuilder
    implements HouseBuilder
{

    private final HouseHelper _helper;
    private final Object _returnValue;

    ImplHouseBuilder(HouseHelper helper, Object returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    private void _checkInvocations() {
        // nothing
    }

    public WallBuilder_setColor_setWidth addWall() {
        ObjectWrapper<WallHelper> helper1 = new ObjectWrapper<WallHelper>();
        _checkInvocations();
        Wall intermediateResult = _helper.addWall(helper1);
         
        WallBuilder_setColor_setWidth step1 = new ImplWallBuilder_setColor_setWidth(helper1 .get(), intermediateResult);
        WallBuilder_setColor_setWidth retval = step1;
        return retval;
    }

    public AffordableHouse constructAffordableHouse() {
        _checkInvocations();
        AffordableHouse intermediateResult = _helper.constructAffordableHouse();
         
        AffordableHouse retval = intermediateResult;
        return retval;
    }

    public ExpensiveHouse constructExpensiveHouse() {
        _checkInvocations();
        ExpensiveHouse intermediateResult = _helper.constructExpensiveHouse();
         
        ExpensiveHouse retval = intermediateResult;
        return retval;
    }

}
