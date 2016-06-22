package data;

import java.util.ArrayList;
import java.util.List;

public class AllTests {
	private List<Test> tests;
	public AllTests(){
		tests = new ArrayList<Test>();
	}
	public void file_read(){ //zum einlesen delimiter?
		// TODO: implementieren 
	}
	public void add(String test){
		tests.add(new Test(test));
	}
	public String toString(){
		String ret = "";
		for(Test test: tests){
			ret = ret + test.toString();
			ret = ret + System.lineSeparator();
		}
		return ret;
	}

	public void run(){
		//TODO: impl.
	}


}
