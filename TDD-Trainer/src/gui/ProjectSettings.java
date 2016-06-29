package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProjectSettings extends Stage {
	
	
	public ProjectSettings(){
		super();
		setScene(create_scene());
		showAndWait();
	}
	
	private Scene create_scene(){
		return new Scene(create_root(), 500, 500);
	}
	
	private BorderPane create_root(){
		BorderPane root = new BorderPane();
		root.setBottom(create_bottom());
		root.setCenter(create_center());
		
		return root;
	}
	
	private HBox create_bottom(){
		HBox bottom = new HBox();
		bottom.setAlignment(Pos.CENTER_RIGHT);
		
		Button ok = new Button("OK");
		bottom.getChildren().add(ok);
		
		return bottom;
	}
	
	private GridPane create_center(){
		GridPane center = new GridPane();
		center.setPadding(new Insets(10, 10, 10, 10));
		
		//Label in 0,0
		Text projectNameText = new Text("Project name:");
		center.add(projectNameText, 0, 0);
		
		//Textfeld in 1,0
		TextField projectNameBox = new TextField("");
		center.add(projectNameBox, 1, 0);
		center.setHgrow(projectNameBox, Priority.ALWAYS);
		
		//
		
		return center;
	}
}