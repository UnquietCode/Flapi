package unquietcode.tools.flapi;



import java.lang.String; /**
 * @author Benjamin Fagin
 * @version 12-30-2011
 */
public interface DescriptorInterfaces {
	// builder is separate

	public interface DescriptorBuilder_showLog_anotherOption_setPackage extends DescriptorBuilder<DescriptorBuilder_showLog_anotherOption_setPackage> {
		DescriptorBuilder_setPackage_anotherOption showLog(boolean neal);
		DescriptorBuilder_showLog_anotherOption setPackage(String packageName);
		DescriptorBuilder_showLog_setPackage anotherOption(String anotherOption);
	}

	public interface DescriptorBuilder_showLog_setPackage extends DescriptorBuilder<DescriptorBuilder_showLog_setPackage> {
		DescriptorBuilder_setPackage showLog(boolean neal);
		DescriptorBuilder_showLog setPackage(String packageName);
	}

	public interface DescriptorBuilder_showLog_anotherOption extends DescriptorBuilder<DescriptorBuilder_showLog_anotherOption> {
		DescriptorBuilder_anotherOption showLog(boolean neal);
		DescriptorBuilder_showLog anotherOption(String packageName);
	}

	public interface DescriptorBuilder_setPackage_anotherOption extends DescriptorBuilder<DescriptorBuilder_setPackage_anotherOption> {
		DescriptorBuilder_anotherOption setPackage(String packageName);
		DescriptorBuilder_setPackage anotherOption(String anotherOption);
	}

	public interface DescriptorBuilder_setPackage extends DescriptorBuilder<DescriptorBuilder_setPackage> {
		DescriptorBuilder setPackage(String packageName);
	}

	public interface DescriptorBuilder_anotherOption extends DescriptorBuilder<DescriptorBuilder_anotherOption> {
		DescriptorBuilder anotherOption(String packageName);
	}

	public interface DescriptorBuilder_showLog extends DescriptorBuilder<DescriptorBuilder_showLog> {
		DescriptorBuilder showLog(boolean neal);
	}

	// method
	public interface Method<_ReturnType> {
		BlockInterfaces<_ReturnType> once();
		BlockInterfaces<_ReturnType> any();
		BlockInterfaces<_ReturnType> exactly(int num);

		Method_atMost<_ReturnType> atMost(int num);
		Method_atLeast<_ReturnType> atLeast(int num);

		public interface Method_atMost<_ReturnType> extends BlockInterfaces<_ReturnType> {
			BlockInterfaces<_ReturnType> atMost(int num);
		}

		public interface Method_atLeast<_ReturnType> extends BlockInterfaces<_ReturnType> {
			BlockInterfaces<_ReturnType> atLeast(int num);
		}
	}

	// block
	public interface BlockInterfaces<_ReturnType> {
		Method<BlockInterfaces<_ReturnType>> startBlock(String blockName, String methodSignature);
		Method<_ReturnType> addBlockReference(String blockName, String methodSignature);
		Method<_ReturnType> addMethod(String methodSignature);
		_ReturnType endBlock();
		_ReturnType endBlock(String methodSignature);
	}
}


/*

         The recursion you see is caused by the knowledge from Method that it must have a return type supporting
         block methods, and the block knowing that the method must know this.
         
 */

