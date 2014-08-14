/*********************************************************************
 Copyright 2014 the Flapi authors

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ********************************************************************/

package unquietcode.tools.flapi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies a method parameter that is to be used for building
 * a block chain around the method. The parameter must be of
 * type {@link java.util.concurrent.atomic.AtomicReference},
 * and the provided {@link #value()} class must match the
 * generic type of the reference object. A mismatch will not
 * be discovered until runtime, at which point you will receive
 * a ClassCastException.
 *
 * @author Ben Fagin
 * @version 2014-08-03
 */
@FlapiAnnotation
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface BlockChain {
	Class<?> value();
}