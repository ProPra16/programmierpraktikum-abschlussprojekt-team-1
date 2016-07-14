package data;

import vk.core.api.CompilationUnit;

/**
 * Datentyp, der den vom Nutzer eingegebenen Java-Code einer Klasse repräsentiert.
 * Der Nutzer kann mehrere Klassen in einem Projekt verwalten. Diese Klasse bietet
 * eine automatische Verwaltung von altem und neuen Code, um Änderungen rückgängig
 * zu machen, falls der Nutzer aus der Code-Phase zurück in die Test-Phase wechselt.
 * Darüber hinaus kann diese Klasse automatisch ein CompilationUnit aus sich erzeu-
 * gen, welches für das Kompilieren und Testen des Codes in {@link io.Testing} er-
 * forderlich ist.
 * 
 * @author Daniela Prigge, Lukas Rose, Rebecca Wagner
 */

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
