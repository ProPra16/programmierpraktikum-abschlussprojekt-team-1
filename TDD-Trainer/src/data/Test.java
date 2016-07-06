package data;

import vk.core.api.CompilationUnit;

public class Test implements Code{
	private String code;
	private String old_code;
	private String name;
	
	public Test(String name, String code){
		this.name = name;
		this.code = code;
	}
	public String getContent(){
		return code;
	}
	public String getName(){
		return name;
	}
	public CompilationUnit getCompilationUnit(){
		return new CompilationUnit(name, code, true); //TODO: ein besserer name muss her
	}
	public void backToOldCode(){
		code = old_code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
