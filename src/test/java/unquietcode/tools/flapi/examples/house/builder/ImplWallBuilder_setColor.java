
package unquietcode.tools.flapi.examples.house.builder;

import java.awt.Color;
import javax.annotation.Generated;
import unquietcode.tools.flapi.examples.house.Wall;
import unquietcode.tools.flapi.support.v0_2.BuilderImplementation;


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
public class ImplWallBuilder_setColor
    implements WallBuilder_setColor, BuilderImplementation
{

    private final WallHelper _helper;
    private final Wall _returnValue;

    ImplWallBuilder_setColor(WallHelper helper, Wall returnValue) {
        _helper = helper;
        _returnValue = returnValue;
    }

    public BuilderImplementation _getParent() {
        return null;
    }

    private void _transferInvocations(Object next) {
        // nothing
    }

    public void _checkInvocations() {
        // nothing
    }

    public Wall setColor(Color color) {
        _helper.setColor(color);
         
        return _returnValue;
    }

}
