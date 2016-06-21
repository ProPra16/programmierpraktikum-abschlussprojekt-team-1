package gui;

import javafx.scene.control.TextArea;

public class ConsolePane extends TextArea{
	private TextArea text;
	public ConsolePane(){
		text = new TextArea();
		getChildren().add(text);
	}

}
