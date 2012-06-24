
package unquietcode.tools.flapi.examples.house.builder;

import javax.annotation.Generated;
import unquietcode.tools.flapi.examples.house.AffordableHouse;
import unquietcode.tools.flapi.examples.house.ExpensiveHouse;
import unquietcode.tools.flapi.examples.house.Wall;
import unquietcode.tools.flapi.support.v0_2.BuilderImplementation;
import unquietcode.tools.flapi.support.v0_2.ObjectWrapper;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on June 24, 2012 16:46:25 CDT using version 0.2
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "June 24, 2012 16:46:25 CDT", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplHouseBuilder
    implements HouseBuilder, BuilderImplementation
{

    private final HouseHelper _helper;
    private final BuilderImplementation _returnValue;

    ImplHouseBuilder(HouseHelper helper, BuilderImplementation returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    public BuilderImplementation _getParent() {
        return _returnValue;
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    public void _checkInvocations() {
        // nothing
    }

    public WallBuilder_setColor_setWidth addWall() {
        ObjectWrapper<WallHelper> helper1 = new ObjectWrapper<WallHelper>();
        BuilderImplementation cur = _returnValue;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        Wall intermediateResult = _helper.addWall(helper1);
         
        ImplWallBuilder_setColor_setWidth step1 = new ImplWallBuilder_setColor_setWidth(helper1 .get(), intermediateResult);
        return step1;
    }

    public AffordableHouse constructAffordableHouse() {
        BuilderImplementation cur = _returnValue;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        AffordableHouse intermediateResult = _helper.constructAffordableHouse();
         
        return intermediateResult;
    }

    public ExpensiveHouse constructExpensiveHouse() {
        BuilderImplementation cur = _returnValue;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        ExpensiveHouse intermediateResult = _helper.constructExpensiveHouse();
         
        return intermediateResult;
    }

}
