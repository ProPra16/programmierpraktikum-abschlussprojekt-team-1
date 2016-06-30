package data;

import java.util.ArrayList;
import java.util.List;

import io.Testing;

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
		for(Class klasse:all_class){
			Testing.compile(klasse.getCompilationUnit());
		}
	}
	public void test(){
		//TODO: zu console wechseln und consolenausgabe ins tab packen
	}
	public boolean tests_ok(){
		boolean test_bestanden = true;
		//false wenn test ned besteht
		return test_bestanden;
	}
	
	public void addClass(Class klasse){
		all_class.add(klasse);
	}
	
	public void addTest(Test test){
		all_tests.add(test);
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
