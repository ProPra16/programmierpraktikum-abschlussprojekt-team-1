package data;


import vk.core.api.CompilationUnit;

public class Class {
	private String code;
	private String name;

	public Class(String code, String name){
		this.name = name;
		this.code = code;
	}
	public CompilationUnit getCompilationUnit(){
		return new CompilationUnit(name, code, false); //TODO: ein besserer name muss her
	}
	public void setCode(String code){
		this.code=code;
	}
	public String getName(){
		return name;
	}
}
