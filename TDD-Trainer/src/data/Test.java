package data;

import vk.core.api.CompilationUnit;

public class Test implements Code{
	private String code;
	private String new_test;
	private String name;
	private final String code_end = "\n}"; 
	
	public Test(String name, String code){
		this.name = name;
		this.code = code;
		new_test = "";
	}
	
	public Test(String name){
		this.name = name+"Test";
		code = "import static org.junit.Assert.*;\n"
				+ "import org.junit.Test;"
				+ "\n\npublic class "+name+"Test{";
		new_test = "";

	}
	
	public String getContent(){
		return code+new_test+code_end;
	}
	
	public String getName(){
		return name;
	}
	
	/**
	 * Erzeugt fürs Testen nötige CompilationUnit aus dem Test.
	 */
	public CompilationUnit getCompilationUnit(){
		return new CompilationUnit(name, code+new_test+code_end, true);
	}
	
	/**
	 * Der neue Test wird gelöscht.
	 */
	public void backToOldCode(){
		new_test = "";
	}
	
	public void setCode(String new_test) {
		this.new_test = new_test;
	}
	
	public void overrideOldCode(){
		code = code+"\n"+new_test;
		new_test = "";
	}
	
	public int getNewTestCount(){
		return (new_test.split("@Test").length-1);
	}
}
