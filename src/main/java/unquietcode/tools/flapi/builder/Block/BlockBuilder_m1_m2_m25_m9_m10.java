
/*******************************************************************************
 Copyright 2013 Benjamin Fagin

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

package unquietcode.tools.flapi.builder.Block;

import javax.annotation.Generated;
import unquietcode.tools.flapi.builder.Method.MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22;


/**
 * This class was generated using Flapi, the fluent API generator for Java.
 * Modifications to this file will be lost upon regeneration.
 * You have been warned!
 * 
 * Visit https://github.com/UnquietCode/Flapi for more information.
 * 
 * 
 * Generated on February 04, 2013 10:10:17 CST using version 0.3
 * 
 */
@Generated(value = "unquietcode.tools.flapi", date = "February 04, 2013 10:10:17 CST", comments = "generated using Flapi, the fluent API generator for Java")
public interface BlockBuilder_m1_m2_m25_m9_m10 <_ReturnType >{


    /**
     * add a new method which proceeds to an existing block
     * 
     */
    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22 <BlockBuilder_m1_m2_m25_m9_m10 <_ReturnType>> addBlockReference(String blockName, String methodSignature);

    /**
     * add a new method to the block
     * 
     */
    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22 <BlockBuilder_m1_m2_m25_m9_m10 <_ReturnType>> addMethod(String methodSignature);

    /**
     * finish editing of the current block
     * 
     */
    _ReturnType endBlock();

    /**
     * Start a new block, nested inside the current one.
     * The block can be referenced from outside by using the designated name.
     * 
     */
    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22 <BlockBuilder_m1_m2_m25_m9_m10 <BlockBuilder_m1_m2_m25_m9_m10 <_ReturnType>>> startBlock(String blockName, String methodSignature);

    /**
     * Start a new anonymous block, nested inside the current one.
     * The block cannot be referenced from outside, as it has no name.
     * 
     */
    MethodBuilder_m11_m12_m13_m14_m15_m16_m17_m18_m19_m20_m21_m22 <BlockBuilder_m1_m2_m25_m9_m10 <BlockBuilder_m1_m2_m25_m9_m10 <_ReturnType>>> startBlock(String methodSignature);

}
