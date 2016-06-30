package io;

import java.util.Collection;
import gui.ConsolePane;
import vk.core.api.CompilationUnit;
import vk.core.api.CompileError;
import vk.core.api.CompilerResult;
import vk.core.api.TestFailure;
import vk.core.api.TestResult;
import vk.core.internal.InternalResult;

public class Testing {
	private static ConsolePane console;
	
	public static void compile(CompilationUnit comp_un){ //gibt fehlermeldungen, wenn vorhanden auf die console aus
		if(hasCompileErrors(comp_un)){
			Collection<CompileError> errors = getCompileErrors(comp_un);
			for(CompileError error: errors){
				console.set_text(error.toString());
				//Reb: ueberlegen: .toString()? -  lieber selbst zusammenbasteln?
			}
		}
	}	
	public static boolean hasCompileErrors(CompilationUnit comp_un){
		CompilerResult y = getInternalResult(comp_un);
		return y.hasCompileErrors();
	}
	public static InternalResult getInternalResult(CompilationUnit x){ //TODO: braucht man das?
		return (InternalResult) new InternalResult().getCompilerErrorsForCompilationUnit(x);
	}
	private static Collection<CompileError> getCompileErrors(CompilationUnit comp_un){
		CompilerResult y = getInternalResult(comp_un);
		return y.getCompilerErrorsForCompilationUnit(comp_un);
	}
	public static boolean tests_ok(CompilationUnit comp_un){
		TestResult test_res = getInternalResult(comp_un);
		return(test_res.getNumberOfFailedTests() ==0);
	}
	public static void test(CompilationUnit comp_un){
		InternalResult test_res = getInternalResult(comp_un); //Reb: besser als test_res, aber woher dann test-errors?
		int tests_ok = test_res.getNumberOfSuccessfulTests();
		int tests_fail = test_res.getNumberOfFailedTests();
		Collection<TestFailure> fails = test_res.getTestFailures();
		//TODO: ausgeben was expected war und was rausgekommen ist
		console.set_text("OK: "+tests_ok+" FAIL: "+tests_fail);
		for(TestFailure fail: fails){  //TODO: gucken was da ausgegeben wird und dann anpassen
			console.set_text(fail.getTestClassName());
			console.set_text(fail.getMethodName());
			console.set_text(fail.getMessage());
			console.set_text(fail.getExceptionStackTrace());
		}
	}
}
