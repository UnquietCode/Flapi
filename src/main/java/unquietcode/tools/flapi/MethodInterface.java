package unquietcode.tools.flapi;

/**
* @author Benjamin Fagin
* @version 02-16-2012
*/ // method
public interface MethodInterface<_ReturnType> {
	BlockInterface<_ReturnType> once();
	BlockInterface<_ReturnType> any();
	BlockInterface<_ReturnType> exactly(int num);

	Method_atLeast<_ReturnType> atMost(int num);
	Method_atMost<_ReturnType> atLeast(int num);

	public interface Method_atMost<_ReturnType> extends BlockInterface<_ReturnType> {
		BlockInterface<_ReturnType> atMost(int num);
	}

	public interface Method_atLeast<_ReturnType> extends BlockInterface<_ReturnType> {
		BlockInterface<_ReturnType> atLeast(int num);
	}
}
