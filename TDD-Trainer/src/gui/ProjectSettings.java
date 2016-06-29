package gui;

import data.ConstantsManager;
import data.Project;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProjectSettings extends Stage {
	private Project project = ConstantsManager.getConstants().getProject();
	
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
		center.setHgap(10d);
		center.setVgap(10d);
		
		//Label Project Name in 0,0
		Text projectNameText = new Text("Project name:");
		center.add(projectNameText, 0, 0);
		
		//Textfeld Project Name in 1,0
		TextField projectNameBox = new TextField(project.getName());
		center.add(projectNameBox, 1, 0);
		center.setHgrow(projectNameBox, Priority.ALWAYS);
		
		//Label Project Description in 0,1
		Text projectDescriptionText = new Text("Project description:");
		center.add(projectDescriptionText, 0, 1);
		
		//TextArea Project Description in 0,2
		TextArea projectDescriptionArea = new TextArea(project.getDescription());
		center.add(projectDescriptionArea, 0, 2, 2, 1);
		
		//Hbox Babysteps in 0,3
		HBox babysteps_hbox = new HBox();
		CheckBox babystepsCheckBox = new CheckBox("Use Babysteps");
		babystepsCheckBox.setSelected(project.getBabysteps());
		
		Text durationText = new Text("Duration per Stage:");
		TextField durationField = new TextField(String.valueOf(project.getDuration()));
		durationField.setPromptText("Banane");
		
		babysteps_hbox.getChildren().addAll(durationText, durationField);
		center.add(babysteps_hbox, 0, 3);
		
		//Checkbox Tracking in 0,4
		CheckBox tracking = new CheckBox("Use Tracking");
		tracking.setSelected(project.getTracking());
		center.add(tracking, 0, 4);
		
		return center;
	}
}