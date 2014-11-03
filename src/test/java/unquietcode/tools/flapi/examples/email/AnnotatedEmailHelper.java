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

package unquietcode.tools.flapi.examples.email;

import unquietcode.tools.flapi.annotations.*;

import java.io.File;

/**
 * @author Ben Fagin
 * @version 2014-11-03
 */
@Block(name="Email")
public interface AnnotatedEmailHelper {

	@AtMost(1)
	void subject(String subject);

	@AtLeast(1)
	void addRecipient(String emailAddress);

	@Exactly(1)
	void sender(String emailAddress);

	@Any
	void addCC(String emailAddress);

	@Any
	void addBCC(String emailAddress);

	@AtMost(1)
	void body(String text);

	@Any
	void addAttachment(File file);

	@Last
	EmailMessage send();
}