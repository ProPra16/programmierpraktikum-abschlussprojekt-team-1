package gui;

import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class ConsolePane extends GridPane{
	private TextArea text;
	public ConsolePane(){
		text = new TextArea();
		getChildren().add(text);
		text.setEditable(false);
		super.setVgrow(text, Priority.ALWAYS);
		super.setHgrow(text, Priority.ALWAYS);
	}
	
	public void set_text(String content){
		text.appendText(content);
	}
	
	public void clear(){
		text.clear();
	}
}
