package io;

import java.util.Collection;
import gui.ConsolePane;
import vk.core.api.CompilationUnit;
import vk.core.api.CompileError;
import vk.core.api.CompilerResult;
import vk.core.api.JavaStringCompiler;
import vk.core.api.TestFailure;
import vk.core.api.TestResult;
import vk.core.internal.InternalCompiler;

public class Testing {
	private static ConsolePane console;
	
	public static void setConsole(ConsolePane console1){//TODO: schoener machen
		console = console1;
	}
	
	public static void compile(CompilationUnit[] comp_uns){ //gibt fehlermeldungen, wenn vorhanden auf die console aus
		JavaStringCompiler comp = getJSC(comp_uns);
		CompilerResult comp_res = comp.getCompilerResult();
		if(comp_res.hasCompileErrors()){
			for(CompilationUnit cu: comp_uns){
				Collection<CompileError> errors = comp_res.getCompilerErrorsForCompilationUnit(cu);
				print_errors_to_console(errors);
			}
		}else console.set_textln("No Compilationerrors");
	}
	
	private static void print_errors_to_console(Collection<CompileError> errors){ //gibt compileerrors auf console aus
		for(CompileError error: errors){
			console.set_text(error.toString());
			//Reb: ueberlegen: .toString()? -  lieber selbst zusammenbasteln?
		}
	}
	
	public static boolean testHasCompileErrors(CompilationUnit[] comp_uns){ //fuer next-button
		JavaStringCompiler comp = getJSC(comp_uns);
		CompilerResult comp_res = comp.getCompilerResult();
		return comp_res.hasCompileErrors();
	}
	
	public static boolean tests_passed(CompilationUnit[] comp_uns){ //fuer next-button
		JavaStringCompiler comp = getJSC(comp_uns);
		TestResult test_res = comp.getTestResult();
		return(test_res.getNumberOfFailedTests()+test_res.getNumberOfIgnoredTests() ==0);
	}
	
	public static void test(CompilationUnit[] comp_uns){ //gibt testergebnisse auf console aus
		JavaStringCompiler comp = getJSC(comp_uns);
		TestResult test_res = comp.getTestResult();
		int tests_ok = test_res.getNumberOfSuccessfulTests();
		int tests_fail = test_res.getNumberOfFailedTests();
		Collection<TestFailure> fails = test_res.getTestFailures();
		console.set_text("OK: "+tests_ok+" FAIL: "+tests_fail);
		for(TestFailure fail: fails){  //TODO: gucken was da ausgegeben wird und dann anpassen (von der formatierung her)
			console.set_textln(fail.getTestClassName());
			console.set_textln(fail.getMethodName());
			console.set_textln(fail.getMessage());
			console.set_textln(fail.getExceptionStackTrace());
		}
	}

	private static JavaStringCompiler getJSC(CompilationUnit[] comp_uns){ //erzeugt JSC und ruftcompileAndRunTests() auf
		JavaStringCompiler comp = new InternalCompiler(comp_uns);
		try{
			comp.compileAndRunTests();
		}catch(RuntimeException e){
			console.set_textln("RuntimeException - Class not found"); //TODO: nicht alle runtimeexcep. abfangen, nur die eine
			//TODO: mach was
		}
		return comp;
	}
}
