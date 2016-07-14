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
	/**
	 * Erzeugt fürs Testen nötige CompilationUnit aus der Klasse.
	 */
	public CompilationUnit getCompilationUnit(){
		return new CompilationUnit(name, code, false);
	}
	public String getName(){
		return name;
	}
	public String getContent(){
		return code;
	}
	
	/**
	 * ersetzt den neu geschriebenen Code durch den alten Code.
	 */
	public void backToOldCode(){
		code = old_code;
	}
	public void setCode(String code){
		this.code=code;
	}
	
	/**
	 * Der neue Code wird nun als alter Code gespeichert und kann nun nicht
	 * mehr überschrieben werden.
	 */
	public void overrideOldCode(){
		old_code = code;
	}
}
