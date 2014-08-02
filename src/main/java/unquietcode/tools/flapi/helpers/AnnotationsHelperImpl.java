package unquietcode.tools.flapi.helpers;

import unquietcode.tools.flapi.ClassReference;
import unquietcode.tools.flapi.DescriptorBuilderException;
import unquietcode.tools.flapi.builder.Annotation.AnnotationHelper;
import unquietcode.tools.flapi.outline.MethodOutline;

import java.lang.annotation.Annotation;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkState;


public class AnnotationsHelperImpl implements AnnotationHelper {
    private final MethodOutline method;
    private final Object annotation;
	private final String FQCN;
	private final Map<String, Object> parameters = new LinkedHashMap<String, Object>();

	public AnnotationsHelperImpl(MethodOutline method, Class<? extends Annotation> annotation) {
	    this(method, annotation, annotation.getName());
    }

	public AnnotationsHelperImpl(MethodOutline method, ClassReference annotation) {
		this(method, annotation, annotation.getFQCN());
	}

	private AnnotationsHelperImpl(MethodOutline method, Object annotation, String FQCN) {
		this.method = method;
		this.annotation = annotation;
		this.FQCN = FQCN;
	}

	@Override
	public void withParameter(String name, Class value) {
		checkAndAdd(name, value);
	}

	@Override
	public void withParameter(String name, ClassReference value) {
		checkAndAdd(name, value);
	}

	@Override
	public void withParameter(String name, Enum value) {
		checkAndAdd(name, value);
	}

	@Override
	public void withParameter(String name, String value) {
		checkAndAdd(name, value);
	}

	@Override
	public void withParameter(String name, boolean value) {
		checkAndAdd(name, value);
	}

	@Override
	public void withParameter(String name, byte value) {
		checkAndAdd(name, value);
	}

	@Override
	public void withParameter(String name, double value) {
		checkAndAdd(name, value);
	}

	@Override
	public void withParameter(String name, float value) {
		checkAndAdd(name, value);
	}

	@Override
	public void withParameter(String name, int value) {
		checkAndAdd(name, value);
	}

	@Override
	public void withParameter(String name, long value) {
		checkAndAdd(name, value);
	}

	@Override
	public void withParameter(String name, short value) {
		checkAndAdd(name, value);
	}

	@Override
	public void withParameter(String name, Class[] values) {
		checkAndAdd(name, values);
	}

	@Override
	public void withParameter(String name, ClassReference[] values) {
		checkAndAdd(name, values);
	}

	@Override
	public void withParameter(String name, Enum[] values) {
		checkAndAdd(name, values);
	}

	@Override
	public void withParameter(String name, String[] values) {
		checkAndAdd(name, values);
	}

	@Override
	public void withParameter(String name, boolean[] values) {
		checkAndAdd(name, values);
	}

	@Override
	public void withParameter(String name, byte[] values) {
		checkAndAdd(name, values);
	}

	@Override
	public void withParameter(String name, double[] values) {
		checkAndAdd(name, values);
	}

	@Override
	public void withParameter(String name, float[] values) {
		checkAndAdd(name, values);
	}

	@Override
	public void withParameter(String name, int[] values) {
		checkAndAdd(name, values);
	}

	@Override
	public void withParameter(String name, long[] values) {
		checkAndAdd(name, values);
	}

	@Override
	public void withParameter(String name, short[] values) {
		checkAndAdd(name, values);
	}

	@Override
	public void finish() {
		if (FQCN.equals(Deprecated.class.getName())) {
			checkState(parameters.isEmpty(), "the @Deprecated annotation does not have parameters");
			method.setDeprecated(null);
		} else {
			method.addAnnotation(annotation, parameters);
		}
	}

	private void checkAndAdd(String name, Object value) {
		if (name == null || name.trim().isEmpty()) {
			throw new DescriptorBuilderException("a valid parameter name is required");
		}

		if (value == null) {
			throw new DescriptorBuilderException("parameter values cannot be null");
		}

		if (parameters.containsKey(name)) {
			throw new DescriptorBuilderException("duplicate annotation parameter name found: " + name);
		}

		parameters.put(name, value);
	}
}