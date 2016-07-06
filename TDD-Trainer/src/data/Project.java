package data;

import java.util.ArrayList;
import java.util.List;

import io.Testing;
import vk.core.api.CompilationUnit;

public class Project {
	private final int CLASS = 0;
	private final int TEST = 1;
	private List<Code> all_tests;
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
		this.all_tests = all_tests;
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
		all_tests = new ArrayList<Code>();
		all_class = new ArrayList<Code>();
		name = "New exercise";
		description = "A new exercise.";
		babysteps = false;
		duration = 0;
		tracking = false;
	}
	
	public void compile(){
		Testing.compile((CompilationUnit[]) getCompilationUnits(CLASS).toArray());
	}
	public boolean hasCompileErrors(){
		CompilationUnit[] cus = (CompilationUnit[]) getCompilationUnits(CLASS).toArray(); 
		return Testing.hasCompileErrors(cus);
	}
	
	private List<CompilationUnit> getCompilationUnits(int type){
		List<Code> all = (type == TEST ? all_tests : all_class);
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
		List<CompilationUnit> cus = getCompilationUnits(CLASS);
		cus.addAll(getCompilationUnits(TEST));
		Testing.test((CompilationUnit[])cus.toArray());
	}
	public boolean tests_ok(){
		List<CompilationUnit> cus = getCompilationUnits(CLASS);
		cus.addAll(getCompilationUnits(TEST));
		if(Testing.tests_passed((CompilationUnit[])cus.toArray())) return true;
		return false;
	}
	public void addClass(Class klasse){
		all_class.add(klasse);
	}
	
	public List<Code> getClassList(){
		return all_class;
	}
	
	public void addTest(Test test){
		all_tests.add(test);
	}
	
	public List<Code> getTestList(){
		return all_tests;
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
