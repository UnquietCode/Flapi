package unquietcode.tools.flapi;



import java.lang.String; /**
 * @author Benjamin Fagin
 * @version 12-30-2011
 */
public interface DescriptorInterfaces {
	// builder is separate
	
	public interface DescriptorBuilder<_ReturnValue> {
		void build();
		MethodInterface<_ReturnValue> startBlock(String blockName, String methodSignature);
	}

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

}


/*

         The recursion you see is caused by the knowledge from Method that it must have a return type supporting
         block methods, and the block knowing that the method must know this.
         
 */

