package data;


import vk.core.api.CompilationUnit;

public class Class implements Code{
	private String code;
	private String old_code;
	private String name;

	public Class(String code, String name){
		this.name = name;
		this.code = code;
	}
	public CompilationUnit getCompilationUnit(){
		return new CompilationUnit(name, code, false); //TODO: ein besserer name muss her
	}
	public String getName(){
		return name;
	}
	public String getContent(){
		return code;
	}
	public void backToOldCode(){
		code = old_code;
	}
	public void setCode(String code){
		this.code=code;
	}
	public void overrideOldCode(){
		old_code = code;
	}
}
