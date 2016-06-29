package data;

import vk.core.api.CompilationUnit;
import vk.core.api.CompilerResult;
import vk.core.internal.InternalResult;

public class Class {
	private String code;
	private String name;
	private CompilerResult y;

	public Class(String code, String name){
		this.name = name;
		this.code = code;
	}
	public void compile(){
		CompilationUnit x = new CompilationUnit(name, code, false); //TODO: ein besserer name muss her
		y = (CompilerResult) new InternalResult().getCompilerErrorsForCompilationUnit(x);
	}
	public boolean hasCompileErrors(){
		return y.hasCompileErrors();
	}
}
