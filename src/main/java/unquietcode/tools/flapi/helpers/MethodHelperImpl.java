/*********************************************************************
 Flapi, the fluent API builder for Java.
 Visit the project page at https://github.com/UnquietCode/Flapi

 Flapi is free and open software provided without a license.
 ********************************************************************/

package unquietcode.tools.flapi.helpers;

import unquietcode.tools.flapi.ClassReference;
import unquietcode.tools.flapi.DescriptorBuilderException;
import unquietcode.tools.flapi.builder.Annotation.AnnotationHelper;
import unquietcode.tools.flapi.builder.BlockChain.BlockChainHelper;
import unquietcode.tools.flapi.builder.Documentation.DocumentationHelper;
import unquietcode.tools.flapi.builder.Method.MethodHelper;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.util.concurrent.atomic.AtomicReference;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Ben Fagin
 * @version 03-04-2012
 */
public class MethodHelperImpl implements MethodHelper {
	final MethodOutline method;

	MethodHelperImpl(MethodOutline method) {
		this.method = method;
	}

	@Override
	public void exactly(int num) {
		between(num, num);
	}

	@Override
	public void any() {
		method.setMinOccurrences(0);
		method.setMaxOccurrences(-1);
	}

	@Override
	public void any(int group) {
		any();
		method.setGroup(group);
	}

	@Override
	public void last() {
		method.setMinOccurrences(0);
		method.setMaxOccurrences(1);
		method.isTerminal(true);
	}

	@Override
	public void last(Class returnType) {
		if (returnType == null) {
			throw new DescriptorBuilderException("Intermediate return type cannot be null.");
		}

		last(returnType.getName());
	}

	@Override
	public void last(String returnType) {
		if (returnType == null) {
			throw new DescriptorBuilderException("Intermediate return type cannot be null.");
		}

		last();
		method.setReturnType(returnType);
	}

	@Override
	public void atLeast(int num) {
		if (num < 0) {
			throw new DescriptorBuilderException("must have at least >= 0");
		}

		method.setMaxOccurrences(-1);
		method.setMinOccurrences(num);
	}

	@Override
	public void atMost(int num, int group) {
		atMost(num);
		method.setGroup(group);
	}

	@Override
	public void atMost(int num) {
		if (num <= 0) {
			throw new DescriptorBuilderException("must have at least > 0");
		}

		method.setMinOccurrences(0);
		method.setMaxOccurrences(num);
	}

	@Override
	public void between(int atLeast, int atMost) {
		if (atLeast < 0) {
			throw new DescriptorBuilderException("must have at least >= 0");
		}

		if (atMost <= 0) {
			throw new DescriptorBuilderException("must have at most > 0");
		}

		if (atMost < atLeast) {
			throw new DescriptorBuilderException("must have atLeast <= then atMost");
		}

		method.setMaxOccurrences(atLeast);
		method.setMinOccurrences(atMost);
	}

	@Override
	public void addBlockChain(AtomicReference<BlockChainHelper> _helper1) {
		_helper1.set(new BlockChainHelperImpl(method));
	}

	@Override
	public void withDocumentation(AtomicReference<DocumentationHelper> _helper1) {
		DocumentationHelper helper = new DocumentationHelperImpl(method);
		_helper1.set(helper);
	}

	@Override
	public void withDocumentation(String documentation) {
		method.setDocumentation(documentation);
	}

	@Override
	public void markAsDeprecated(String reason) {
		method.setDeprecated(reason);
	}

	@Override
	public void addAnnotation(Class annotation, AtomicReference<AnnotationHelper> _helper1) {
		checkNotNull(annotation);
		AnnotationHelper helper = new AnnotationsHelperImpl(method, annotation);
		_helper1.set(helper);
	}

	@Override
	public void addAnnotation(String annotation, AtomicReference<AnnotationHelper> _helper1) {
		checkNotNull(annotation);
		AnnotationHelper helper = new AnnotationsHelperImpl(method, new ClassReference(annotation));
		_helper1.set(helper);
	}

    @Override
	public void after(int group) {
		method.setTrigger(group);
	}
}