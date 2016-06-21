package gui;

import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
public class CodePane extends StackPane{
	private TextArea text;
	public CodePane(){
		text = new TextArea();
		getChildren().add(text);
	}
}
