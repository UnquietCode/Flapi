
package unquietcode.tools.flapi.examples.email.builder;

import java.io.File;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit http://www.unquietcode.com/flapi for more information.
 * 
 * 
 * Generated on May 09, 2012 23:29:25 CDT using version 0.1
 * 
 */
public interface EmailBuilder<_ReturnType >{


    EmailBuilder<_ReturnType> addAttachment(File file);

    EmailBuilder<_ReturnType> addBCC(String emailAddress);

    EmailBuilder<_ReturnType> addCC(String emailAddress);

    EmailBuilder<_ReturnType> addRecipient(String emailAddress);

    _ReturnType send();

    EmailBuilder<_ReturnType> setSender(String emailAddress);

}
