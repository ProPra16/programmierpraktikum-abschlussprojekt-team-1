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
	private boolean first_class = true;
	
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
			System.out.println();
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
	public boolean tests_ok(){
		if(Testing.tests_passed(listToArray(CLASS, TEST))) return true;
		return false;
	}
	public void overrideOldCode(int type){
		List<Code> code = (type == TEST ? tests : all_class);
		for(Code klasse: code){
			klasse.overrideOldCode();
		}
		
	}
	public void backToOldCode(int type){
		List<Code> code = (type == TEST ? tests : all_class);
		for(Code klasse: code){
			klasse.backToOldCode();
		}
	}

	public void setNewTestOrClassCode(int index, String new_content, int type){ //klappt das so?
		System.out.println("classlistsize: "+all_class.size());
		System.out.println("testlistsize: "+tests.size());
		(type == TEST ? tests : all_class).get(index).setCode(new_content);
	}
	public void addClass(Class klasse){
		all_class.add(klasse);
		if(first_class){
			String name = klasse.getName();
			String head = "public class "+name+"Test {";
			tests.add(new Test(klasse.getName()+"Test",head));
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
