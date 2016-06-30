package gui;

import data.Constants;
import data.ConstantsManager;
import data.Project;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class AlertHandler {
	public static final int LOAD_PROJECT = 2;
	public static final int LOAD_TEMPLATE = 1;
	public static final int NEW_PROJECT = 0;
	public static int returnValue = NEW_PROJECT;
	
	public static int newProject_alert(){
		Alert loadOrNew = new Alert(AlertType.CONFIRMATION);
		loadOrNew.setHeaderText(null);
		loadOrNew.setContentText("Load an exercise or create a new program?");
		ButtonType loadTemplate = new ButtonType("Load a template");
		ButtonType loadProject = new ButtonType("Load existing project");
		ButtonType newProject = new ButtonType("New project");
		loadOrNew.getButtonTypes().setAll(loadTemplate, loadProject, newProject);
		loadOrNew.showAndWait().ifPresent((event) -> {
			if(event == loadTemplate) returnValue = LOAD_TEMPLATE;
			else if(event == loadProject) returnValue = LOAD_PROJECT;
			else if(event == newProject) returnValue = NEW_PROJECT;
			
		});
		return returnValue;
	}
	
	public static void babysteps_alert(){
		Constants cs = ConstantsManager.getConstants();
		Project ex = cs.getProject();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setContentText("Do you want to use 'babysteps'?"); //TODO: besseren text ausdenken...?
		ButtonType yes = new ButtonType("Yes");
		ButtonType no = new ButtonType("No");
		alert.getButtonTypes().setAll(yes, no);
		alert.showAndWait().ifPresent(event-> {
			if(event == yes) ex.setBabysteps(true);
			else if(event == no) ex.setBabysteps(false);
		});
		if(ex.getBabysteps()){
			do {
				TextInputDialog b_duration = new TextInputDialog();
				b_duration.setContentText("How many seconds?"); //TODO: besseren text...
				b_duration.setHeaderText(null);
				b_duration.showAndWait().ifPresent(input ->{
					try{
						ex.setDuration(Integer.parseInt(input));
						cs.setShowBabystepsAlert(false);
					} catch(NumberFormatException e){
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Please enter an integer!");
						error.setContentText("The duration you supplied couldn't be parsed for an integer. Please try again.");
						error.showAndWait();
					}
				});
			} while (cs.getShowBabystepsAlert());
		}
	}
}
