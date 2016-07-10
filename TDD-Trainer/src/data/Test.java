package data;

import vk.core.api.CompilationUnit;

public class Test implements Code{
	private String code;
	private String new_test;
	private String name;
	private final String code_end = "\n}";//TODO: wie ganau sachen dranhaengen? 
	
	public Test(String name, String code){
		code = "import static org.junit.Assert.*\nimport org.junit.Test;";
		this.name = this.name+name;
		this.code = code;
		new_test = "";
		System.out.println(code+code_end);
	}
	public String getContent(){
		return code+new_test+code_end;
	}
	public String getCode(){
		return code;
	}
	public String getName(){
		return name;
	}
	public CompilationUnit getCompilationUnit(){
		return new CompilationUnit(name, code+new_test+code_end, true); //TODO: ein besserer name muss her
	}
	public void backToOldCode(){
		new_test = "";
	}
	public void setCode(String new_test) {
		this.new_test = new_test;
	}
	public void overrideOldCode(){
		code = code+new_test;
		new_test = "";
	}
}
