/*******************************************************************************
 Copyright 2012 Benjamin Fagin

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.


     Read the included LICENSE.TXT for more information.
 ******************************************************************************/

package unquietcode.tools.flapi.support.v0_2;

/**
 * @author Ben Fagin
 * @version 05-31-2012
 *
 * Unifying interface for all builder implementations, providing several
 * guarantees which are used within the generated logic.
 */
public interface BuilderImplementation {
	BuilderImplementation _getParent();
	void _checkInvocations();
}
