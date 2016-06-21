package gui;

import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;

public class TestPane extends StackPane{ // // TODO: nicht nur test schreiben, sondern auch alte ansehen 
	private TextArea text;
	public TestPane(){
		text = new TextArea();
		getChildren().add(text);
	}

}
