package data;

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
		this.tracker = tracker;
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
	
}
