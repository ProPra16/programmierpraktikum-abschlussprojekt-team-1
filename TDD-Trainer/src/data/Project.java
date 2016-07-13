package data;

import java.util.ArrayList;
import java.util.List;

import io.Testing;
import vk.core.api.CompilationUnit;

public class Project {
	public final int CLASS = 0;
	public final int TEST = 1;
	private List<Code> tests;
	private List<Code> all_class;
	private String description, name;
	private boolean babysteps;
	private int duration;
	private boolean tracking;
	
	/**
	 * 
	 * @param all_tests  Liste aller Test-Objekte, die im Projekt enthalten sein sollen
	 * @param klasse Liste aller Class-Objekte, die im Projekt enthalten sein sollen
	 * @param description Eine Beschreibung des Projekts
	 * @param name Der Name des Projekts
	 * @param babysteps Ein boolean-Wert, der wiedergibt, ob die "Babysteps"-Funktion genutzt werden soll
	 * @param duration Die Dauer der Babysteps in Sekunden
	 * @param tracking Ein boolean-Wert, der anzeigt ob tracking aktiviert sein soll 
	 */
	public Project(List<Code> all_tests, List<Code> klasse, String description, 
					String name, boolean babysteps, int duration, boolean tracking){
		this.tests = all_tests;
		this.all_class = klasse;
		this.description = description;
		this.name = name;
		this.babysteps = babysteps;
		this.duration = duration;
		this.tracking = tracking;
	}
	/**
	 * Erstellt ein leeres Project-Objekt
	 */
	public Project(){
		tests = new ArrayList<Code>();
		all_class = new ArrayList<Code>();
		name = "New exercise";
		description = "A new exercise.";
		babysteps = false;
		duration = 0;
		tracking = false;
	}
	
	private CompilationUnit[] listToArray(int type1, int type2){
		List<CompilationUnit> list = getCompilationUnits(type1);
		if(type1 != type2) list.addAll(getCompilationUnits(type2));
		CompilationUnit[] array = new CompilationUnit[list.size()];
		for(int i=0;i<list.size();i++){
			array[i] = list.get(i);
		}
		return array;
	}
	
	public void compile(){
		Testing.compile(listToArray(CLASS, CLASS));
		
	}
	public boolean testHasCompileErrors(){
		return Testing.hasCompileErrors(listToArray(TEST,TEST));
	}
	
	private List<CompilationUnit> getCompilationUnits(int type){
		List<Code> all = (type == TEST ? tests : all_class);
		List<CompilationUnit> cus = new ArrayList<CompilationUnit>();
		for(int i = 0; i<all.size();i++){
			cus.add(all.get(i).getCompilationUnit());
		}
		return cus;
	}

	/**
	 * 
	 */
	public void test(){
		Testing.test(listToArray(CLASS, TEST));
	}
	
	/**
	 * kontrolliert ob die Tests bestehen.
	 * @return true wenn die tests bestehen und false wenn nicht.
	 */
	public boolean tests_ok(){
		return Testing.tests_passed(listToArray(CLASS, TEST));
	}
	
	/**
	 * Der alte code wird mit dem neuen Code überschrieben{@link Code#overrideOldCode()} 
	 * und kann nun in der Gui nicht mehr geändert werden.
	 * @param type, gibt an ob die Tests oder die Klassen überschrieben werden sollen. (Class = 0, Test = 1)
	 */
	public void overrideOldCode(int type){
		List<Code> code = (type == TEST ? tests : all_class);
		for(Code klasse: code){
			klasse.overrideOldCode();
		}
	}
	
	/**
	 * Der neue Code wird gelöscht{@link Code#backToOldCode()}.
	 * @param type, gibt an ob die Tests oder die Klassen zurückgesetzt werden sollen.
	 */
	public void backToOldCode(int type){
		List<Code> code = (type == TEST ? tests : all_class);
		for(Code klasse: code){
			klasse.backToOldCode();
		}
	}
	
	/**
	 * Speichert neu geschriebenen Code in die Datenstruktur 'Project'.
	 * 
	 * @param index, gibt an welches Klassen oder Test Element geändert werden soll.
	 * @param new_content, gibt an was neu in eingespeichert werden soll.
	 * @param type, gibt an ob der neue Code in Tests oder Klassen gespeichert werden soll.
	 */
	public void setNewTestOrClassCode(int index, String new_content, int type){ //klappt das so?
		(type == TEST ? tests : all_class).get(index).setCode(new_content);
	}
	
	/**
	 * Fügt ein Objekt der Klasse 'Class' der Datenstruktur 'Project' hinzu.
	 * @param klasse
	 */
	public void addClass(Class klasse){
		all_class.add(klasse);
	}
	
	/**
	 * Die Klasse mit dem Namen 'name' wird aus der Datenstruktur 'Project' entfernt.
	 * @param name
	 */
	public void removeClass(String name){
		for(int i = 0; i < all_class.size(); i++){
			Code klasse = all_class.get(i);
			if(klasse.getName().equals(name)){
				all_class.remove(i);
			}
		}
	}
	
	public List<Code> getClassList(){
		return all_class;
	}
	
	public void addTest(Test test){
		tests.add(test);
	}
	
	public List<Code> getTestList(){
		return tests;
	}
	
	public void setBabysteps(boolean babysteps){
		this.babysteps = babysteps;
	}
	
	public boolean getBabysteps(){
		return babysteps;
	}
	
	public void setDuration(int duration){
		this.duration = duration;
	}
	
	public int getDuration(){
		return duration;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setTracking(boolean tracking){
		this.tracking = tracking;
	}
	
	public boolean getTracking(){
		return tracking;
	}
}
