
package unquietcode.tools.flapi.examples.house.builder;

import java.awt.Color;
import javax.annotation.Generated;


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
public class ImplWallBuilder_setColor
    implements WallBuilder_setColor
{

    private final WallHelper _helper;
    private final Object _returnValue;

    ImplWallBuilder_setColor(WallHelper helper, Object returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    private void _checkInvocations() {
        // nothing
    }

    public Object construct() {
        _checkInvocations();
        _helper.construct();
         
        Object retval = _returnValue;
        return retval;
    }

    public WallBuilder setColor(Color color) {
        _helper.setColor(color);
         
        WallBuilder retval = new ImplWallBuilder(_helper, _returnValue);
        _transferInvocations(retval);
        return retval;
    }

}
