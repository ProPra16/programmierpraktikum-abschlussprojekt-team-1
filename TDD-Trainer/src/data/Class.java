package data;

import vk.core.api.CompilationUnit;
import vk.core.api.CompilerResult;

public class Class {
	private String code;
	private String name;

	public Class(String code, String name){
		this.name = name;
		this.code = code;
	}
	public void compile(){
		CompilationUnit x = new CompilationUnit(name, code, false); //TODO: ein besserer name muss her
	}
}
