package unquietcode.tools.flapi.helpers;

import unquietcode.tools.flapi.ClassReference;
import unquietcode.tools.flapi.DescriptorBuilderException;
import unquietcode.tools.flapi.builder.Annotation.AnnotationHelper;
import unquietcode.tools.flapi.outline.MethodOutline;

import static com.google.common.base.Preconditions.checkArgument;


public class AnnotationsHelperImpl implements AnnotationHelper {
    private final MethodOutline method;
    private final Object annotation;

    public AnnotationsHelperImpl(MethodOutline method, Class annotation) {
	    this(method, (Object) annotation);
        checkArgument(annotation.isAnnotation(), "only annotation classes are allowed");
    }

	public AnnotationsHelperImpl(MethodOutline method, ClassReference annotation) {
		this(method, (Object) annotation);
	}

	private AnnotationsHelperImpl(MethodOutline method, Object annotation) {
		this.method = method;
		this.annotation = annotation;
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
    public void withParameter(String name, ClassReference[] values) {
        checkAndAdd(name, values);
    }

    @Override
	public void finish() {
		// nothing
	}

	private void checkAndAdd(String name, Object value) {
		if (name == null || name.trim().isEmpty()) {
			throw new DescriptorBuilderException("a valid parameter name is required");
		}

		if (value == null) {
			throw new DescriptorBuilderException("parameter values cannot be null");
		}

		method.addAnnotationParam(annotation, name, value);
	}
}