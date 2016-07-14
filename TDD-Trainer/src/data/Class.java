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

	/**
	 * Konstruktor, erzeugt ein neues Objekt, welches eine Klasse mit dem über-
	 * gebenen Namen und dem übergebenen Code initialisiert.
	 * @param code Der Code der zu speichernden Klasse
	 * @param name Der Name der zu speichernden Klasse
	 */
	public Class(String code, String name){
		this.name = name;
		this.code = code;
	}
	
	/**
	 * Erzeugt fürs Testen nötige CompilationUnit aus der Klasse. Dieses wird
	 * von der zum Kompilieren und Testen verwendeten Bibliothek erfordert.
	 */
	public CompilationUnit getCompilationUnit(){
		return new CompilationUnit(name, code, false);
	}
	
	/**
	 * Gibt den Namen der Klasse zurück, die durch eine Instanz dieser Klasse
	 * verwaltet wird.
	 * 
	 * @return Name der Klasse
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Ein Getter für den in dieser Klasse verwalteten Code. Gibt die zuletzt
	 * akzeptierte Version des Codes (nur Übernommene Änderungen) zurück.
	 * 
	 * @return Der in dieser Klasse verwaltete Code.
	 */
	public String getContent(){
		return code;
	}
	
	/**
	 * Ersetzt den neu geschriebenen Code durch den alten Code. Hiermit werden
	 * Änderungen, die der Nutzer seit der letzten "Akzeptieren" Version des
	 * Codes gemacht hat, widerrufen, und der Code auf seinen ehemaligen Zustand
	 * zurückgesetzt.
	 */
	public void backToOldCode(){
		code = old_code;
	}
	
	/**
	 * Ein Setter für den in dieser Klasse verwalteten Code.
	 * 
	 * @param code Der Code, der gespeichert werden soll.
	 */
	public void setCode(String code){
		this.code=code;
	}
	
	/**
	 * Der neue Code wird nun als alter Code gespeichert und kann nun nicht
	 * mehr überschrieben werden. Dies ist als "Übernehmen" der Änderungen
	 * zu verstehen.
	 */
	public void overrideOldCode(){
		old_code = code;
	}
}
