
package unquietcode.tools.flapi.examples.house.builder.House;

import unquietcode.tools.flapi.examples.house.AffordableHouse;
import unquietcode.tools.flapi.examples.house.ExpensiveHouse;
import unquietcode.tools.flapi.examples.house.Wall;
import unquietcode.tools.flapi.examples.house.builder.House.HouseBuilder.$;
import unquietcode.tools.flapi.examples.house.builder.Wall.ImplWallBuilder_setColor_setWidth;
import unquietcode.tools.flapi.examples.house.builder.Wall.WallBuilder_setColor_setWidth;
import unquietcode.tools.flapi.examples.house.builder.Wall.WallHelper;
import unquietcode.tools.flapi.support.BuilderImplementation;
import unquietcode.tools.flapi.support.ObjectWrapper;

import javax.annotation.Generated;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on April 19, 2013 18:33:15 PDT using version 0.4
 */
@Generated(value = "unquietcode.tools.flapi", date = "April 19, 2013 18:33:15 PDT", comments = "generated using Flapi, the fluent API generator for Java")
public class ImplHouseBuilder
    implements $<Object> , HouseBuilder<Object> , BuilderImplementation
{
    private final HouseHelper _helper;
    private final Object _returnValue;

    public ImplHouseBuilder(HouseHelper helper, Object returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    public BuilderImplementation _getParent() {
        if (_returnValue instanceof BuilderImplementation) {
            return ((BuilderImplementation) _returnValue);
        } else {
            return null;
        }
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    public void _checkInvocations() {
        // nothing
    }

    public WallBuilder_setColor_setWidth addWall() {
        BuilderImplementation cur = this;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        ObjectWrapper<WallHelper> helper1 = new ObjectWrapper<WallHelper>();
        Wall intermediateResult = _helper.addWall(helper1);
        ImplWallBuilder_setColor_setWidth step1 = new ImplWallBuilder_setColor_setWidth(helper1 .get(), intermediateResult);
         
        return step1;
    }

    public AffordableHouse constructAffordableHouse() {
        BuilderImplementation cur = this;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        AffordableHouse intermediateResult = _helper.constructAffordableHouse();
         
        return intermediateResult;
    }

    public ExpensiveHouse constructExpensiveHouse() {
        BuilderImplementation cur = this;
        while (cur!= null) {
            cur._checkInvocations();
            cur = cur._getParent();
        }
         
        ExpensiveHouse intermediateResult = _helper.constructExpensiveHouse();
         
        return intermediateResult;
    }
}
