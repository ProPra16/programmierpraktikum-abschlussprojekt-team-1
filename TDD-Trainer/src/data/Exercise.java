package data;

import java.util.ArrayList;
import java.util.List;

public class Exercise {
	private List<Test> all_tests;
	private List<Class> all_class;
	private String description, name;
	private boolean babysteps;
	private int duration;
	private boolean tracker;

	public Exercise(List<Test> all_tests, List<Class> klasse, String description, 
					String name, boolean babysteps, int duration, boolean tracker){
		this.all_tests = all_tests;
		this.all_class = klasse;
		this.description = description;
		this.name = name;
		this.babysteps = babysteps;
		this.duration = duration;
		this.tracker = tracker;
	}
	
	public Exercise(){
		all_tests = new ArrayList<Test>();
		all_class = new ArrayList<Class>();
		name = "New exercise";
		description = "A new exercise.";
		babysteps = false;
		duration = 0;
		tracker = false;
	}
	
	public void compile(){
		//TODO: zu console wechseln und consolenausgabe ins tab packen
	}
	
	public void test(){
		//TODO: zu console wechseln und consolenausgabe ins tab packen
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
}
