package unquietcode.tools.flapi;

import java.util.Map;

import static unquietcode.tools.flapi.DescriptorInterfaces.*;
import static unquietcode.tools.flapi.DescriptorHelper.*;
import static unquietcode.tools.flapi.DescriptorInterfaces.Method;

/**
 * @author Benjamin Fagin
 * @version 01-08-2012
 */
public final class DescriptorClasses {

	public static class ImplDescriptorBuilder<_ReturnValue> implements DescriptorBuilder<_ReturnValue> {
		protected _ReturnValue retval;
		protected DescriptorHelper descriptorHelper;

		protected void setup(DescriptorHelper descriptorHelper, _ReturnValue retval) {
			this.descriptorHelper = descriptorHelper;
			this.retval = retval;
		}

		@Override
		public void build() {
			CodeGenerator generator = new CodeGenerator();
			Map<String, StringBuilder> files = generator.generateFiles(descriptorHelper);
			System.out.println(files.size());
		}

		@Override
		public Method<_ReturnValue> startBlock(String blockName, String methodSignature) {
			// descriptor builder create block
			Block block = new Block();
			block.blockName = blockName;
			block.constructor = new DescriptorHelper.Method();
			block.constructor.methodSignature = methodSignature;

			this.descriptorHelper.blocks.add(block);
			return new ImplMethod<_ReturnValue>(block.constructor, new ImplBlock<_ReturnValue>(block, retval));
			// is it the case that we don't really need to keep the retval reference, opting to ...
		}


		// TODO for debug mostly
		public @Override String toString() {
			StringBuilder sb = new StringBuilder();
			return "";
		}
	}

	public static class ImplDescriptorBuilder_showLog_anotherOption_setPackage extends ImplDescriptorBuilder<DescriptorBuilder_showLog_anotherOption_setPackage> implements DescriptorBuilder_showLog_anotherOption_setPackage {
		ImplDescriptorBuilder_showLog_anotherOption_setPackage(DescriptorHelper descriptorHelper) {
			setup(descriptorHelper, this);
		}

		@Override
		public DescriptorBuilder_setPackage_anotherOption showLog(boolean neal) {
			this.descriptorHelper.showLog(neal);
			return new ImplDescriptorBuilder_setPackage_anotherOption(this.descriptorHelper);
		}

		@Override
		public DescriptorBuilder_showLog_anotherOption setPackage(String packageName) {
			this.descriptorHelper.setPackage(packageName);
			return new ImplDescriptorBuilder_showLog_anotherOption(this.descriptorHelper);
		}

		@Override
		public DescriptorBuilder_showLog_setPackage anotherOption(String anotherOption) {
			this.descriptorHelper.anotherOption(anotherOption);
			return new ImplDescriptorBuilder_showLog_setPackage(this.descriptorHelper);
		}
	}

	public static class ImplDescriptorBuilder_setPackage_anotherOption extends ImplDescriptorBuilder<DescriptorBuilder_setPackage_anotherOption> implements DescriptorBuilder_setPackage_anotherOption {
		ImplDescriptorBuilder_setPackage_anotherOption(DescriptorHelper descriptorHelper) {
			setup(descriptorHelper, this);
		}

		@Override
		public DescriptorBuilder_anotherOption setPackage(String packageName) {
			this.descriptorHelper.setPackage(packageName);
			return new ImplDescriptorBuilder_anotherOption(this.descriptorHelper);
		}

		@Override
		public DescriptorBuilder_setPackage anotherOption(String anotherOption) {
			this.descriptorHelper.anotherOption(anotherOption);
			return new ImplDescriptorBuilder_setPackage(this.descriptorHelper);
		}
	}

	public static class ImplDescriptorBuilder_showLog_anotherOption extends ImplDescriptorBuilder<DescriptorBuilder_showLog_anotherOption> implements DescriptorBuilder_showLog_anotherOption {
		ImplDescriptorBuilder_showLog_anotherOption(DescriptorHelper descriptorHelper) {
			setup(descriptorHelper, this);
		}

		@Override
		public final DescriptorBuilder_anotherOption showLog(boolean neal) {
			this.descriptorHelper.showLog(neal);
			return new ImplDescriptorBuilder_anotherOption(this.descriptorHelper);
		}

		@Override
		public final DescriptorBuilder_showLog anotherOption(String anotherOption) {
			this.descriptorHelper.anotherOption(anotherOption);
			return new ImplDescriptorBuilder_showLog(this.descriptorHelper);
		}
	}

	public static class ImplDescriptorBuilder_showLog_setPackage extends ImplDescriptorBuilder<DescriptorBuilder_showLog_setPackage> implements DescriptorBuilder_showLog_setPackage {
		ImplDescriptorBuilder_showLog_setPackage(DescriptorHelper descriptorHelper) {
			setup(descriptorHelper, this);
		}

		@Override
		public DescriptorBuilder_setPackage showLog(boolean neal) {
			this.descriptorHelper.showLog(neal);
			return new ImplDescriptorBuilder_setPackage(this.descriptorHelper);
		}

		@Override
		public DescriptorBuilder_showLog setPackage(String packageName) {
			this.descriptorHelper.setPackage(packageName);
			return new ImplDescriptorBuilder_showLog(this.descriptorHelper);
		}
	}

	public static class ImplDescriptorBuilder_showLog extends ImplDescriptorBuilder<DescriptorBuilder_showLog> implements DescriptorBuilder_showLog {
		ImplDescriptorBuilder_showLog(DescriptorHelper descriptorHelper) {
			setup(descriptorHelper, this);
		}

		@Override
		public DescriptorBuilder showLog(boolean neal) {
			this.descriptorHelper.showLog(neal);
			return this;
		}
	}

	public static class ImplDescriptorBuilder_setPackage extends ImplDescriptorBuilder<DescriptorBuilder_setPackage> implements DescriptorBuilder_setPackage {
		ImplDescriptorBuilder_setPackage(DescriptorHelper descriptorHelper) {
			setup(descriptorHelper, this);
		}

		@Override
		public DescriptorBuilder setPackage(String packageName) {
			this.descriptorHelper.setPackage(packageName);
			return this;
		}
	}

	public static class ImplDescriptorBuilder_anotherOption extends ImplDescriptorBuilder<DescriptorBuilder_anotherOption> implements DescriptorBuilder_anotherOption {
		ImplDescriptorBuilder_anotherOption(DescriptorHelper descriptorHelper) {
			setup(descriptorHelper, this);
		}

		@Override
		public DescriptorBuilder anotherOption(String anotherOption) {
			this.descriptorHelper.anotherOption(anotherOption);
			return this;
		}
	}


	public static class ImplMethod<_ReturnType> implements Method<_ReturnType> {
		private ImplBlock<_ReturnType> retval;
		private DescriptorHelper.Method method;

		protected ImplMethod(DescriptorHelper.Method method, ImplBlock<_ReturnType> retval) {
			this.retval = retval;
			this.method = method;
		}

		@Override
		public BlockInterfaces<_ReturnType> once() {
			method.minOccurrances = 1;
			method.maxOccurrances = 1;

			return retval;
		}

		@Override
		public BlockInterfaces<_ReturnType> any() {
			method.minOccurrances = -1;
			method.maxOccurrances = -1;

			return retval;
		}

		@Override
		public BlockInterfaces<_ReturnType> exactly(int num) {
			if (num <= 0) {
				throw new RuntimeException("");
			}

			method.minOccurrances = num;
			method.maxOccurrances = num;
			return retval;
		}

		@Override
		public Method_atMost<_ReturnType> atMost(int num) {
			return new Method_atMost<_ReturnType>() {
				@Override
				public BlockInterfaces<_ReturnType> atMost(int num) {
					method.minOccurrances = -1;
					method.maxOccurrances = num;
					return retval;
				}

				@Override
				public Method<BlockInterfaces<_ReturnType>> startBlock(String blockName, String methodSignature) {
					return retval.startBlock(blockName, methodSignature);
				}

				@Override
				public Method<_ReturnType> addBlockReference(String blockName, String methodSignature) {
					return retval.addBlockReference(blockName, methodSignature);
				}

				@Override
				public Method<_ReturnType> addMethod(String methodSignature) {
					return retval.addMethod(methodSignature);
				}

				@Override
				public _ReturnType endBlock() {
					return retval.endBlock();
				}

				@Override
				public _ReturnType endBlock(String methodSignature) {
					return retval.endBlock(methodSignature);
				}
			};
		}

		@Override
		public Method_atLeast<_ReturnType> atLeast(int num) {
			return new Method_atLeast<_ReturnType>() {
				@Override
				public BlockInterfaces<_ReturnType> atLeast(int num) {
					method.minOccurrances = num;
					method.maxOccurrances = -1;
					return retval;
				}

				@Override
				public Method<BlockInterfaces<_ReturnType>> startBlock(String blockName, String methodSignature) {
					return retval.startBlock(blockName, methodSignature);
				}

				@Override
				public Method<_ReturnType> addBlockReference(String blockName, String methodSignature) {
					return retval.addBlockReference(blockName, methodSignature);
				}

				@Override
				public Method<_ReturnType> addMethod(String methodSignature) {
					return retval.addMethod(methodSignature);
				}

				@Override
				public _ReturnType endBlock() {
					return retval.endBlock();
				}

				@Override
				public _ReturnType endBlock(String methodSignature) {
					return retval.endBlock(methodSignature);
				}
			};
		}
	}

	public static class ImplBlock<_ReturnType> implements BlockInterfaces<_ReturnType> {
		private final DescriptorHelper.Block block;
		private final _ReturnType retval;

		protected ImplBlock(DescriptorHelper.Block block, _ReturnType retval) {
			this.block = block;
			this.retval = retval;
		}


		@Override
		public Method<BlockInterfaces<_ReturnType>> startBlock(String blockName, String methodSignature) {
			// descriptor builder create block
			DescriptorHelper.Block nblock = new DescriptorHelper.Block();
			nblock.blockName = blockName;
			nblock.constructor = new DescriptorHelper.Method();
			nblock.constructor.methodSignature = methodSignature;
			block._addBlock(nblock);

			return new ImplMethod<BlockInterfaces<_ReturnType>>(nblock.constructor, new ImplBlock<BlockInterfaces<_ReturnType>>(nblock, this));
		}

		@Override
		public Method<_ReturnType> addBlockReference(String blockName, String methodSignature) {
			DescriptorHelper.Method method = new DescriptorHelper.Method();
			method.methodSignature = methodSignature;
			block._addMethod(method);

			return new ImplMethod<_ReturnType>(method, new ImplBlock<_ReturnType>(block, retval));
		}


		@Override
		public Method<_ReturnType> addMethod(String methodSignature) {
			DescriptorHelper.Method method = new DescriptorHelper.Method();
			method.methodSignature = methodSignature;
			block._addMethod(method);

			return new ImplMethod<_ReturnType>(method, this);
		}

		@Override
		public _ReturnType endBlock() {
			return retval;
		}

		@Override
		public _ReturnType endBlock(String methodSignature) {
			return retval;
		}
	}


}
