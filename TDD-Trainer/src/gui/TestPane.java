package gui;

import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class TestPane extends GridPane{ 
	private TextArea eingabe, angenommeneTests;
	
	public TestPane(){
		eingabe = new TextArea();
		angenommeneTests = new TextArea();
		add(angenommeneTests,0,0);
		add(eingabe,0,1);
		setGridLinesVisible(true);
		super.setVgrow(angenommeneTests, Priority.ALWAYS);
		super.setHgrow(angenommeneTests, Priority.ALWAYS);
		angenommeneTests.setEditable(false);
	}
	public void setEditable(boolean edit){
		eingabe.setEditable(edit);
	}
	public void clear(){
		eingabe.clear();
	}
	public void setText(String tests){
		angenommeneTests.setText(tests);
	}
	public String getNewTest(){
		return eingabe.getText();
	}

}
