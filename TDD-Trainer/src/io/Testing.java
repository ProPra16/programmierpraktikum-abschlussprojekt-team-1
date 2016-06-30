package io;

import java.util.List;

import org.junit.runner.JUnitCore;

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
				console.set_text(error.toString());
				//TODO: ueberlegen: .toString()? -  lieber selbst zusammenbasteln?
			}
		}
	}
	
	public static void test(){
		//TODO: soll testen
	}
	
	public static boolean hasCompileErrors(CompilationUnit x){
		CompilerResult y = (CompilerResult) new InternalResult().getCompilerErrorsForCompilationUnit(x);
		return y.hasCompileErrors();
	}
	public static CompilerResult getCompileResult(CompilationUnit x){ //TODO: braucht man das?
		return (CompilerResult) new InternalResult().getCompilerErrorsForCompilationUnit(x);
	}
	private static List<CompileError> getCompileErrors(CompilationUnit x){
		CompilerResult y = (CompilerResult) new InternalResult().getCompilerErrorsForCompilationUnit(x);
		return (List<CompileError>) y.getCompilerErrorsForCompilationUnit(x);
	}
}
