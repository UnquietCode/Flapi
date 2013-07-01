
package unquietcode.tools.flapi.examples.house.builder.Wall;

import unquietcode.tools.flapi.support.BuilderImplementation;

import javax.annotation.Generated;
import java.awt.*;


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
public class ImplWallBuilder_setColor_setWidth
    implements WallBuilder_setColor_setWidth<Object> , BuilderImplementation
{
    private final WallHelper _helper;
    private final Object _returnValue;

    public ImplWallBuilder_setColor_setWidth(WallHelper helper, Object returnValue) {
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

    public WallBuilder_setWidth setColor(Color color) {
        _helper.setColor(color);
        ImplWallBuilder_setWidth step1 = new ImplWallBuilder_setWidth(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }

    public WallBuilder_setColor setWidth(double inches) {
        _helper.setWidth(inches);
        ImplWallBuilder_setColor step1 = new ImplWallBuilder_setColor(_helper, _returnValue);
         
        _transferInvocations(step1);
        return step1;
    }
}