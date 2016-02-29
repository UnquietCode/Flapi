
package unquietcode.tools.flapi.examples.house.builder.Wall;

import unquietcode.tools.flapi.runtime.MethodInfo;
import unquietcode.tools.flapi.runtime.TransitionType;

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
 * Generated on February 28, 2016 16:29:18 PST using version 0.0-DEVELOPMENT
 * @see unquietcode.tools.flapi.examples.house.builder.Wall.WallHelper
 */
@Generated(value = "unquietcode.tools.flapi", date = "2016-02-28T16:29:18-08:00", comments = "generated using Flapi, the fluent API generator for Java, version 0.0-DEVELOPMENT")
public interface WallBuilder_2setColor_2setWidth<_ReturnType> {
    @MethodInfo(type = TransitionType.Lateral)
    WallBuilder_2setWidth_5t<_ReturnType> setColor(Color color);

    @MethodInfo(type = TransitionType.Lateral)
    WallBuilder_2setColor_5t<_ReturnType> setWidth(double inches);
}
