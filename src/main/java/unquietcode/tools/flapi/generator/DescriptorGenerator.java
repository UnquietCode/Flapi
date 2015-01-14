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

package unquietcode.tools.flapi.generator;

import com.sun.codemodel.JCodeModel;
import unquietcode.tools.flapi.DescriptorPostValidator;
import unquietcode.tools.flapi.DescriptorPreValidator;
import unquietcode.tools.flapi.graph.GraphBuilder;
import unquietcode.tools.flapi.graph.components.StateClass;
import unquietcode.tools.flapi.graph.processors.GraphProcessor;
import unquietcode.tools.flapi.outline.DescriptorOutline;

/**
 * @author Ben Fagin
 * @version 03-07-2012
 *
 * Turns an outline into a code model. The general flow is:
 *      outline → validate → build graph → transform → validate → generate
 */
public class DescriptorGenerator extends AbstractGenerator {
	private DescriptorOutline outline;

	public DescriptorGenerator(DescriptorOutline outline) {
		super(new GeneratorContext(outline.getPackageName()));
		ctx.condenseNames(outline.shouldEnableCondensedNames());
		ctx.disableTimestamps(outline.shouldDisableTimestamps());

		this.outline = outline;
	}
	
	public JCodeModel generate() {
		// pre-graph validation
		DescriptorPreValidator preValidator = new DescriptorPreValidator(outline);
		preValidator.validate();

		// convert to graph
		GraphBuilder converter = new GraphBuilder();
		StateClass graph = converter.buildGraph(outline);

		// post-graph validation
		DescriptorPostValidator postValidator = new DescriptorPostValidator(graph);
		postValidator.validate();

		// create the generator
		GeneratorGenerator generatorGen = new GeneratorGenerator(ctx);
		generatorGen.generate(graph, outline.getGenerator());

		// and the rest
		GraphProcessor processor = new GraphProcessor(ctx);
		processor.generate(graph);

		return ctx.model;
	}
}