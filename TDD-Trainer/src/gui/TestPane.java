package gui;

import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Repräsentiert ein Panel, mit dem im Programm der Test-Code angesehen und bearbeitet werden kann.
 *
 */

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
	/**
	 * Erlaubt oder Verbietet die bearbeitung des Textareas.
	 * @param edit Zeigt an ob der Text bearbeitet werden darf.
	 */
	public void setEditable(boolean edit){
		eingabe.setEditable(edit);
	}
	/**
	 * Loescht den Text aus dem Eingabefeld.
	 */
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
