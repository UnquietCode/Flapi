
package unquietcode.tools.flapi.examples.xhtml.builder.Element;

import unquietcode.tools.flapi.runtime.MethodInfo;
import unquietcode.tools.flapi.runtime.TransitionType;

import javax.annotation.Generated;


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
public interface ElementBuilder {
    public interface Start<_ReturnType>
        extends ElementBuilder_2addAttribute_4f_2addComment_4f_2setValue_4f_2startElement_4f<_ReturnType>
    {
        @MethodInfo(type = TransitionType.Recursive)
        ElementBuilder.Start<_ReturnType> addAttribute(String key, String value);

        @MethodInfo(type = TransitionType.Recursive)
        ElementBuilder.Start<_ReturnType> addComment(String comment);

        @MethodInfo(type = TransitionType.Ascending)
        _ReturnType endElement();

        @MethodInfo(type = TransitionType.Lateral)
        ElementBuilder_2addAttribute_4f_2addComment_4f_2endElement_4f_2startElement_4f<_ReturnType> setValue(String value);

        @MethodInfo(type = TransitionType.Recursive, chain = {
            ElementBuilder.Start.class
        })
        ElementBuilder.Start<ElementBuilder.Start<_ReturnType>> startElement(String tagName);
    }
}
