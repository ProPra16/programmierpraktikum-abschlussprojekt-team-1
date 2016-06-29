package io;

import java.util.List;

import gui.ConsolePane;
import vk.core.api.CompilationUnit;
import vk.core.api.CompileError;
import vk.core.api.CompilerResult;
import vk.core.internal.InternalResult;

public class Testing {
	private static ConsolePane console;
	
	public static void compile(CompilationUnit comp_un){ //gibt fehlermeldungen, wenn vorhanden auf die console aus
		if(hasCompileErrors(comp_un)){
			List<CompileError> errors = getCompileErrors(comp_un);
			for(CompileError error: errors){
				console.set_text(error.toString());//TODO: .toString() - lieber selbst zusammenbasteln
			}
		}
	}
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
