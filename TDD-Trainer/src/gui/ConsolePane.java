package gui;

import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class ConsolePane extends GridPane{
	private TextArea text;
	public ConsolePane(){
		text = new TextArea();
		text.setDisable(true);
		getChildren().add(text);
	}
	
	public void set_text(String content){
		text.appendText(content);
	}

}
