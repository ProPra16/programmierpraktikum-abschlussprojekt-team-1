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

/**
 * Repräsentiert ein Fenster, mit dem die Projekteinstellungen bearbeitet werden können.
 *
 */

public class ProjectSettings extends Stage {
	private Project project = ConstantsManager.getConstants().getProject();
	private BorderPane root;
	private HBox bottom;
	private Button ok;
	private GridPane center;
	private Text projectNameText;
	private TextField projectNameBox;
	private Text projectDescriptionText;
	private TextArea projectDescriptionArea;
	private GridPane babysteps_pane;
	private CheckBox babystepsCheckBox;
	private Text durationText;
	private TextField durationField;
	private CheckBox tracking;
	
	public ProjectSettings(){
		super();
		setScene(create_scene());
		showAndWait();
	}
	
	private Scene create_scene(){
		create_root();
		return new Scene(root, 500, 500);
	}
	
	private void create_root(){
		root = new BorderPane();
		root.setPadding(new Insets(10, 10, 10, 10));
		create_center();
		create_bottom();
		root.setBottom(bottom);
		root.setCenter(center);
	
	}
	
	private void create_bottom(){
		bottom = new HBox();
		bottom.setAlignment(Pos.CENTER_RIGHT);
		
		ok = new Button("OK");
		bottom.getChildren().add(ok);
		
		ok.setOnAction(e -> {
			saveValues();
			close();
		});
	}
	
	private void create_center(){
		center = new GridPane();
		center.setHgap(10d);
		center.setVgap(10d);
		
		//Label in 0, 0
		projectNameText = new Text("Project name:");
		center.add(projectNameText, 0, 0);
		
		//TextField in 1, 0
		projectNameBox = new TextField(project.getName());
//		projectNameBox.setMinWidth(Double.MAX_VALUE);
		center.add(projectNameBox, 1, 0);
		center.setFillWidth(projectNameBox, true);
		GridPane.setHgrow(projectNameBox, Priority.ALWAYS);
		
		//Label in 0, 1
		projectDescriptionText = new Text("Project description:");
		center.add(projectDescriptionText, 0, 1);
		
		//TextArea in 0, 2
		projectDescriptionArea = new TextArea(project.getDescription());
		center.add(projectDescriptionArea, 0, 2, 2, 1);
		
		//GridPane in 0, 3
		babysteps_pane = new GridPane();
		babysteps_pane.setVgap(10d);
		babysteps_pane.setHgap(10d);
		babystepsCheckBox = new CheckBox("Use Babysteps");
		
		//Prevents text from overrunning
		babystepsCheckBox.setMinSize(babystepsCheckBox.USE_PREF_SIZE, babystepsCheckBox.USE_PREF_SIZE);
		
		durationText = new Text("Duration per Stage:");
		durationField = new TextField(String.valueOf(project.getDuration()));
		durationField.setPromptText("Banane");
		
		babystepsCheckBox.selectedProperty().addListener((event) -> {
			hide_show_duration_settings();
		});
		
		babystepsCheckBox.setSelected(project.getBabysteps());
		hide_show_duration_settings();
		
		babysteps_pane.add(babystepsCheckBox, 0, 0);
		babysteps_pane.add(durationText, 1, 0);
		babysteps_pane.add(durationField, 2, 0);
		center.add(babysteps_pane, 0, 3, 2, 1);
		
		//CheckBox in 0, 4
		tracking = new CheckBox("Use Tracking");
		tracking.setSelected(project.getTracking());
		center.add(tracking, 0, 4);
	}
	
	/**
	 * Überprüft, ob die {@link #babystepsCheckBox} gerade positiv oder negativ ist,
	 * und zeigt bzw. versteckt die dazugehörigen Elemente, um die Dauer der Babysteps
	 * zu konfigurieren.
	 * 
	 * @see #babystepsCheckBox
	 * @see #durationText
	 * @see #durationField
	 */
	private void hide_show_duration_settings(){
		if (babystepsCheckBox.selectedProperty().getValue()){
			durationText.setVisible(true);
			durationField.setVisible(true);
		} else {
			durationText.setVisible(false);
			durationField.setVisible(false);
		}
	}
	
	private void saveValues(){
		project.setName(projectNameBox.getText());
		project.setDescription(projectDescriptionArea.getText());
		project.setBabysteps(babystepsCheckBox.selectedProperty().getValue());
		try {
			project.setDuration(Integer.parseInt(durationField.getText()));

		} catch (NumberFormatException ex){
			//TODO: Fehlermeldung werfen!
		}
		project.setTracking(tracking.selectedProperty().getValue());
	}
}