package unquietcode.tools.flapi.graph.processors;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JType;
import unquietcode.tools.flapi.generator.AbstractGenerator;
import unquietcode.tools.flapi.generator.GeneratorContext;
import unquietcode.tools.flapi.graph.TransitionVisitor;
import unquietcode.tools.flapi.graph.components.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;


public class ReturnTypeProcessor extends AbstractGenerator {

	public ReturnTypeProcessor(GeneratorContext context) {
		super(context);
	}


	public JType computeReturnType(Transition transition) {
		List<StateClass> sequentialStates = new ArrayList<StateClass>(transition.getStateChain());

		// setup initial return value
		final AtomicReference<JType> initialType = new AtomicReference<JType>();

		transition.accept(new TransitionVisitor.$() {
			@Override
			public void visit(AscendingTransition transition) {
				JClass sc = BUILDER_NARROWED_INTERFACE_STRATEGY.createType(ctx, transition.getOwner());
				initialType.set(sc.getTypeParameters().get(0));
			}

			@Override
			public void visit(LateralTransition transition) {
				initialType.set(BUILDER_NARROWED_INTERFACE_STRATEGY.createType(ctx, transition.getSibling()));
			}

			@Override
			public void visit(RecursiveTransition transition) {
				initialType.set(BUILDER_NARROWED_INTERFACE_STRATEGY.createType(ctx, transition.getSelf()));
			}

			@Override
			public void visit(TerminalTransition transition) {
				Class clazz = transition.getReturnType() == null ? Void.class : transition.getReturnType();

				if (!clazz.equals(Void.class) || !transition.getStateChain().isEmpty()) {
					initialType.set(ctx.model.ref(clazz));
				} else {
					initialType.set(ctx.model.VOID);
				}
			}
		});

		// add the intermediate states
		JType returnType = initialType.get();
		for (int i = sequentialStates.size()-1; i >= 0; --i) {
			StateClass sequentialState = sequentialStates.get(i);
			JClass targetBuilder = BUILDER_INTERFACE_STRATEGY.createType(ctx, sequentialState);
			returnType = targetBuilder.narrow(returnType);
		}

		return returnType;
	}
}
