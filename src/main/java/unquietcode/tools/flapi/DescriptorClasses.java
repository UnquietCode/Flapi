package unquietcode.tools.flapi;

import static unquietcode.tools.flapi.DescriptorInterfaces.*;
//import static unquietcode.tools.flapi.DescriptorInterfaces.Method;

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
			generator.generateCodeModel(descriptorHelper);
			//Map<String, StringBuilder> files = generator.generateFiles(descriptorHelper);
			//System.out.println(files.size());
			//TODO
		}

		@Override
		public MethodInterface<_ReturnValue> startBlock(String blockName, String methodSignature) {
			// descriptor builder create block
			BlockData block = new BlockData();
			block.blockName = blockName;
			block.constructor = new MethodData();
			block.constructor.methodSignature = methodSignature;
			block.parent = null;

			this.descriptorHelper.blocks.add(block);
			return new ImplMethod<_ReturnValue>(block.constructor, new ImplBlock<_ReturnValue>(block, retval));
			// is it the case that we don't really need to keep the retval reference, opting to ...
		}

		@Override
		public MethodInterface<_ReturnValue> startBlock(String blockName) {
			return startBlock(blockName, "create()");
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
		public DescriptorBuilder<DescriptorBuilder> showLog(boolean neal) {
			this.descriptorHelper.showLog(neal);
			ImplDescriptorBuilder<DescriptorBuilder> retval = new ImplDescriptorBuilder<DescriptorBuilder>();
			retval.setup(this.descriptorHelper, retval);
			return retval;
		}
	}

	public static class ImplDescriptorBuilder_setPackage extends ImplDescriptorBuilder<DescriptorBuilder_setPackage> implements DescriptorBuilder_setPackage {
		ImplDescriptorBuilder_setPackage(DescriptorHelper descriptorHelper) {
			setup(descriptorHelper, this);
		}

		@Override
		public DescriptorBuilder<DescriptorBuilder> setPackage(String packageName) {
			this.descriptorHelper.setPackage(packageName);
			ImplDescriptorBuilder<DescriptorBuilder> retval = new ImplDescriptorBuilder<DescriptorBuilder>();
			retval.setup(this.descriptorHelper, this);
			return retval;
		}
	}

	public static class ImplDescriptorBuilder_anotherOption extends ImplDescriptorBuilder<DescriptorBuilder_anotherOption> implements DescriptorBuilder_anotherOption {
		ImplDescriptorBuilder_anotherOption(DescriptorHelper descriptorHelper) {
			setup(descriptorHelper, this);
		}

		@Override
		public DescriptorBuilder<DescriptorBuilder> anotherOption(String anotherOption) {
			this.descriptorHelper.anotherOption(anotherOption);
			ImplDescriptorBuilder<DescriptorBuilder> retval = new ImplDescriptorBuilder<DescriptorBuilder>();
			retval.setup(this.descriptorHelper, retval);
			return retval;
		}
	}


	public static class ImplMethod<_ReturnType> implements MethodInterface<_ReturnType> {
		private ImplBlock<_ReturnType> retval;
		private MethodData method;

		protected ImplMethod(MethodData method, ImplBlock<_ReturnType> retval) {
			this.retval = retval;
			this.method = method;
		}

		@Override
		public BlockInterface<_ReturnType> once() {
			method.minOccurrances = 0;
			method.maxOccurrances = 1;

			return retval;
		}

		@Override
		public BlockInterface<_ReturnType> any() {
			method.minOccurrances = 0;
			method.maxOccurrances = -1;

			return retval;
		}

		@Override
		public BlockInterface<_ReturnType> only() {
			method.minOccurrances = 0;
			method.maxOccurrances = 1;
			method.isTerminal = true;
			
			return retval;
		}

		@Override
		public MethodInterface.Method_atLeast<_ReturnType> atMost(int num) {
			method.minOccurrances = -1;
			method.maxOccurrances = num;
			
			return new MethodInterface.Method_atLeast<_ReturnType>() {
				@Override
				public BlockInterface<_ReturnType> atLeast(int num) {
					method.minOccurrances = num;
					return retval;
				}

				@Override
				public MethodInterface<BlockInterface<_ReturnType>> startBlock(String blockName, String methodSignature) {
					return retval.startBlock(blockName, methodSignature);
				}

				@Override
				public MethodInterface<_ReturnType> addBlockReference(String blockName, String methodSignature) {
					return retval.addBlockReference(blockName, methodSignature);
				}

				@Override
				public MethodInterface<_ReturnType> addMethod(String methodSignature) {
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
		public MethodInterface.Method_atMost<_ReturnType> atLeast(int num) {
			method.minOccurrances = num;
			method.maxOccurrances = -1;

			return new MethodInterface.Method_atMost<_ReturnType>() {
				@Override
				public BlockInterface<_ReturnType> atMost(int num) {
					method.maxOccurrances = num;
					return retval;
				}

				@Override
				public MethodInterface<BlockInterface<_ReturnType>> startBlock(String blockName, String methodSignature) {
					return retval.startBlock(blockName, methodSignature);
				}

				@Override
				public MethodInterface<_ReturnType> addBlockReference(String blockName, String methodSignature) {
					return retval.addBlockReference(blockName, methodSignature);
				}

				@Override
				public MethodInterface<_ReturnType> addMethod(String methodSignature) {
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

	public static class ImplBlock<_ReturnType> implements BlockInterface<_ReturnType> {
		private final BlockData block;
		private final _ReturnType retval;

		protected ImplBlock(BlockData block, _ReturnType retval) {
			this.block = block;
			this.retval = retval;
		}


		@Override
		public MethodInterface<BlockInterface<_ReturnType>> startBlock(String blockName, String methodSignature) {
			// descriptor builder create block
			BlockData nblock = new BlockData();
			nblock.blockName = blockName;
			nblock.constructor = new MethodData();
			nblock.constructor.methodSignature = methodSignature;
			nblock.parent = block;
			block._addBlock(nblock);

			return new ImplMethod<BlockInterface<_ReturnType>>(nblock.constructor, new ImplBlock<BlockInterface<_ReturnType>>(nblock, this));
		}

		@Override
		public MethodInterface<_ReturnType> addBlockReference(String blockName, String methodSignature) {
			BlockReference ref = new BlockReference();
			ref.methodSignature = methodSignature;
			ref.blockName = blockName;
			block._addMethod(ref);

			return new ImplMethod<_ReturnType>(ref, new ImplBlock<_ReturnType>(block, retval));
		}


		@Override
		public MethodInterface<_ReturnType> addMethod(String methodSignature) {
			MethodData method = new MethodData();
			method.methodSignature = methodSignature;
			method.parent = block;
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
