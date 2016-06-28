package data;

import java.util.List;

public class Exercise {
	private List<AllTests> all_tests;
	private List<Class> klasse;
	private String description, name;
	private boolean babysteps;
	private int duration;
	private boolean tracker;

	public Exercise(List<AllTests> all_tests, List<Class> klasse, String description, 
					String name, boolean babysteps, int duration, boolean tracker){
		this.all_tests = all_tests;
		this.klasse = klasse;
		this.description = description;
		this.name = name;
		this.babysteps = babysteps;
		this.tracker = tracker;
	}
	
	public 
}
