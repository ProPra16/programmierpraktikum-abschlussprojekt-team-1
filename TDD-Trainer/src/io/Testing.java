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
	/**
	 * Gibt Compilierfehler (wenn vorhanden) auf die (programminterne) Konsole aus
	 * 
	 * Wandelt übergebene Compilationunits mit {@link #getJSC(CompilationUnit[])} in JavaStringCompiler um,
	 * und gibt Compilierfehler mit {@link #print_errors_to_console(Collection)} auf der programminternen Konsole aus.

	 */
	public static void compile(CompilationUnit[] comp_uns){ //gibt fehlermeldungen, wenn vorhanden auf die console aus
		JavaStringCompiler comp = getJSC(comp_uns);
		CompilerResult comp_res = comp.getCompilerResult();
		if(comp_res.hasCompileErrors()){
			for(CompilationUnit cu: comp_uns){
				Collection<CompileError> errors = comp_res.getCompilerErrorsForCompilationUnit(cu);
				print_errors_to_console(errors);
			}
		} else console.set_textln("No Compilation errors");
	}
	/**
	 * Gibt übergebene CompileError-Liste auf die programminterne Konsole aus.
	 */
	private static void print_errors_to_console(Collection<CompileError> errors){ //gibt compileerrors auf console aus
		console.set_textln("Compilation-Errors:");
		for(CompileError error: errors){
			console.set_text(error.getCodeLineContainingTheError());
			console.set_textln(": "+error.getMessage());
			}
		console.set_textln("");
	}
	/**
	 * Prüft ob Compilierfehler vorliegen.
	 */
	public static boolean hasCompileErrors(CompilationUnit[] comp_uns){ //fuer next-button
		JavaStringCompiler comp = getJSC(comp_uns);
		CompilerResult comp_res = comp.getCompilerResult();
		compile(comp_uns);
		return comp_res.hasCompileErrors();
	}
	/**
	 * Prüft ob alle Tests bestanden werden.
	 * 
	 * Prüft ob die Compilationunits fehlerlos compilieren und prüft anschließend ob alle enthaltenen Tests erfolgreich sind.
	 */
	public static boolean tests_passed(CompilationUnit[] comp_uns){ //fuer next-button
		if(!hasCompileErrors(comp_uns)){
			JavaStringCompiler comp = getJSC(comp_uns);
			TestResult test_res = comp.getTestResult();
			test(comp_uns);
			return(test_res.getNumberOfFailedTests() == 0);
		} else return false;
	}
	/**
	 * Gibt Testergebnisse auf die (programminterne) Konsole aus.
	 * 
	 * Wandelt übergebene Compilationunits mit {@link #getJSC(CompilationUnit[])} in JavaStringCompiler um,
	 * bestimmt Anzahl der erfolgreichen und fehlgeschlagenen Tests und gibt dies, sowie genauere Fehlermendungen
	 * auf der programminternen Konsole aus.
	 */
	public static void test(CompilationUnit[] comp_uns){ //gibt testergebnisse auf console aus
		JavaStringCompiler comp = getJSC(comp_uns);
		TestResult test_res = comp.getTestResult();
		int tests_ok = test_res.getNumberOfSuccessfulTests();
		int tests_fail = test_res.getNumberOfFailedTests();
		console.set_textln("Testresult:");
		Collection<TestFailure> fails = test_res.getTestFailures();
		console.set_textln("OK: "+tests_ok+" FAIL: "+tests_fail);
		for(TestFailure fail: fails){
			console.set_textln("Testname: "+fail.getTestClassName());
			console.set_textln("Methodname: "+fail.getMethodName());
			console.set_textln(fail.getMessage());
			console.set_textln("");
		}
		console.set_textln("");

	}
	/**
	 * Erzeugt einen JavaStringcompiler 
	 * aus einem Compilationunitarray und führt compileAndRunTests() aus (notwendig um Test/CompileResults zu nutzen)
	 */
	private static JavaStringCompiler getJSC(CompilationUnit[] comp_uns){ //erzeugt JSC und ruftcompileAndRunTests() auf
		JavaStringCompiler comp = new InternalCompiler(comp_uns);
		comp.compileAndRunTests();
		return comp;
	}
}
