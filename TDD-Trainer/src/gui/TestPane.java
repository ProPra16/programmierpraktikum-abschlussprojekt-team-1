package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

public class TestPane extends GridPane{ 
	private TextArea eingabe, angenommeneTests;
	private Label tests_content;
	
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
	private void label_styling(){
		tests_content.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
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
