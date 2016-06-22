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
	private TextArea eingabe;
	private ScrollPane all_tests;
	private Label tests_content;
	
	public TestPane(){
		eingabe = new TextArea();
		all_tests = new ScrollPane();
		tests_content = new Label(); // TODO:spaeter html-editor zum syntax-highlighten?
		label_styling();
		all_tests.setContent(tests_content);
		add(eingabe,0,1);
		add(all_tests,0,0);
		super.setVgrow(all_tests, Priority.ALWAYS);
	}
	private void label_styling(){
		tests_content.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
	}

}
