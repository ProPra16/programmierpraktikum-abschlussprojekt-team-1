package gui;

import io.Testing;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Repräsentiert ein Panel, welches die Console im Programm beherbergt und anzeigt.
 *
 */

public class ConsolePane extends GridPane{
	private TextArea text;
	
	/**
	 * Es wird eine TestArea erstellt und ConsolePane hinzugefügt.
	 */
	public ConsolePane(){
		text = new TextArea();
		getChildren().add(text);
		text.setEditable(false);
		super.setVgrow(text, Priority.ALWAYS);
		super.setHgrow(text, Priority.ALWAYS);
		Testing.setConsole(this);
	}
	
	/**
	 * In der TextArea wird 'content' hinzugefügt.
	 * @param content
	 */
	public void set_text(String content){
		text.appendText(content);
	}
	
	/**
	 * In der TextArea wird 'content' und ein Zeilenumbruch hinzugefügt.
	 * @param content
	 */
	public void set_textln(String content){
		text.appendText(content+"\n");
	}

	/**
	 * Der gesamte Text aus der TextArea wird gelöscht.
	 */
	public void clear(){
		text.clear();
	}
}
