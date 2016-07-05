package data;

import java.util.ArrayList;
import java.util.List;

import io.Testing;
import vk.core.api.CompilationUnit;

public class Project {
	private List<Test> all_tests;
	private List<Class> all_class;
	private String description, name;
	private boolean babysteps;
	private int duration;
	private boolean tracking;

	public Project(List<Test> all_tests, List<Class> klasse, String description, 
					String name, boolean babysteps, int duration, boolean tracking){
		this.all_tests = all_tests;
		this.all_class = klasse;
		this.description = description;
		this.name = name;
		this.babysteps = babysteps;
		this.duration = duration;
		this.tracking = tracking;
	}
	
	public Project(){
		all_tests = new ArrayList<Test>();
		all_class = new ArrayList<Class>();
		name = "New exercise";
		description = "A new exercise.";
		babysteps = false;
		duration = 0;
		tracking = false;
	}
	
	public void compile(){
		Testing.compile(getCompilationUnitArray());
	}
	public boolean hasCompileErrors(){
		return Testing.hasCompileErrors(getCompilationUnitArray());
	}
	private CompilationUnit[] getCompilationUnitArray(){
		CompilationUnit[] cus = new CompilationUnit[all_class.size()];
		for(int i = 0; i<all_class.size();i++){
			cus[i] = all_class.get(i).getCompilationUnit();
		}
		return cus;
	}
	public void test(){
		//TODO: array erstellen, dass alle tests und classes enthaelt
		//TODO: zu console wechseln und consolenausgabe ins tab packen
	}
	public boolean tests_ok(){
		boolean test_bestanden = true;
		//TODO: array erstellen, dass alle tests und classes enthaelt
		//TODO: false wenn test ned besteht
		return test_bestanden;
	}
	
	public void addClass(Class klasse){
		all_class.add(klasse);
	}
	
	public List<Class> getClassList(){
		return all_class;
	}
	
	public void addTest(Test test){
		all_tests.add(test);
	}
	
	public List<Test> getTestList(){
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
