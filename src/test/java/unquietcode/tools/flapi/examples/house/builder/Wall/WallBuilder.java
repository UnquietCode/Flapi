
package unquietcode.tools.flapi.examples.house.builder.Wall;

import unquietcode.tools.flapi.examples.house.Wall;
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
 * Generated on August 12, 2014 13:17:30 PDT using version 0.0-DEVELOPMENT
 */
@Generated(value = "unquietcode.tools.flapi", date = "2014-08-12T13:17:30-07:00", comments = "generated using Flapi, the fluent API generator for Java")
public interface WallBuilder<_ReturnType> {
    @MethodInfo(type = TransitionType.Terminal)
    Wall setWidth(double inches);

    @MethodInfo(type = TransitionType.Terminal)
    Wall setColor(Color color);

    public interface Start<_ReturnType>
        extends WallBuilder_2setColor_4f_2setWidth_4f<_ReturnType>
    {
        @MethodInfo(type = TransitionType.Lateral)
        WallBuilder<_ReturnType> setColor(Color color);

        @MethodInfo(type = TransitionType.Lateral)
        WallBuilder<_ReturnType> setWidth(double inches);
    }
}