package io;

import java.util.List;

import vk.core.api.CompilationUnit;
import vk.core.api.CompileError;
import vk.core.api.CompilerResult;
import vk.core.internal.InternalResult;

public class Testing {
	public static boolean hasCompileErrors(CompilationUnit x){
		CompilerResult y = (CompilerResult) new InternalResult().getCompilerErrorsForCompilationUnit(x);
		return y.hasCompileErrors();
	}
	public static CompilerResult getCompileResult(CompilationUnit x){
		return (CompilerResult) new InternalResult().getCompilerErrorsForCompilationUnit(x);
	}
	public static List<CompileError> getCompileErrors(CompilationUnit x){
		CompilerResult y = (CompilerResult) new InternalResult().getCompilerErrorsForCompilationUnit(x);
		return (List<CompileError>) y.getCompilerErrorsForCompilationUnit(x);
	}
}
