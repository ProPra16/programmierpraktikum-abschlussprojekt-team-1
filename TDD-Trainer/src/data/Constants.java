package data;

/**
 * Eine Datenstruktur, welche alle für unser Projekt nötigen Daten
 * verwaltet (Das aktuelle Projekt des Nutzers, ob das Projekt aus
 * einer Übungsaufgabe geladen wurde, ob eine Abfrage zu den Baby-
 * steps gezeigt werden muss). Diese Daten werden somit unserem
 * ganzen Projekt zur Verfügung gestellt. Ein Objekt dieses Typs wird
 * vom {@link ConstantsManager} verwaltet.
 * 
 * @author Daniela Prigge, Lukas Rose, Rebecca Wagner
 */
public class Constants {
	/**
	 * Das aktuell verwendete Projekt des Nutzers.
	 */
	private Project project;
	/**
	 * Ob eine Abfrage zu den Babysteps gezeigt werden muss.
	 */
	private boolean showBabystepsAlert;
	/**
	 * Ob dieses Projekt aus einer Übungsaufgabe erzeugt wurde.
	 */
	private boolean loadExercise = false;
	
	/**
	 * Der Konstruktor erstellt ein neues Project.
	 */
	public Constants(){
		project = new Project();
		showBabystepsAlert = false;
	}
	
	/**
	 * Ersetzt das aktuelle Projekt durch ein neues Projekt
	 * @param newProject Das neue Projekt
	 */
	public void setProject(Project newProject){
		project = newProject;
	}
	
	/**
	 * Gibt das Projekt zurück, sodass auf die Daten des Projektes
	 * zugegriffen werden kann. Ist das Projekt null, also wurde bisher
	 * kein Projekt verwendet, so wird ein neues, leeres Projekt erzeugt.
	 * 
	 * @return Das Projekt der aktuellen Laufzeit.
	 */
	public Project getProject(){
		if (project == null) project = new Project();
		return project;
	}
	
	/**
	 * Ein Setter für {@link #showBabystepsAlert}.
	 * @param showBabystepsAlert Der zu setzende Wert.
	 */
	public void setShowBabystepsAlert(boolean showBabystepsAlert){
		this.showBabystepsAlert = showBabystepsAlert;
	}
	
	/**
	 * Ein Getter für {@link #showBabystepsAlert}.
	 * @return Der Wert der Variable {@link #showBabystepsAlert}.
	 */
	public boolean getShowBabystepsAlert(){
		return this.showBabystepsAlert;
	}
	
	/**
	 * Ein Setter für {@link #loadExercise}.
	 * @param loadExercise Der zu setzende Wert.
	 */
	public void setLoadExercise(boolean loadExercise){
		this.loadExercise = loadExercise;
	}
	
	/**
	 * Ein Getter für {@link #loadExercise}.
	 * @return Der aktuelle Wert der Variable.
	 */
	public boolean getLoadExercise(){
		return loadExercise;
	}
}
