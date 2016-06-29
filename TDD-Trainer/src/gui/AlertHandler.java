package gui;

import data.Constants;
import data.ConstantsManager;
import data.Exercise;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AlertHandler {
	public static void newProject_alert(){
		Alert loadOrNew = new Alert(AlertType.CONFIRMATION);
		loadOrNew.setHeaderText(null);
		loadOrNew.setContentText("Load an exercise or create a new program?");
		ButtonType load = new ButtonType("Load");
		ButtonType newProgram = new ButtonType("New project");
		loadOrNew.getButtonTypes().setAll(load, newProgram);
		loadOrNew.showAndWait().ifPresent(event-> {
			if(event == load){
				loadOrNew.close();
				Catalog katalog = new Catalog();
				katalog.showAndWait();
				if(katalog.wurdeGeladen()){
//					stage.setScene(create_scene());
//					stage.show();
				}
				
			}
			else if(event == newProgram){
				babysteps_alert();
//				stage.setScene(create_scene());
//				stage.show();
			}
		});
	}
	
	public static void babysteps_alert(){
		Constants cs = ConstantsManager.getConstants();
		Exercise ex = cs.getExercise();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setContentText("Do you want to use 'babysteps'?"); //TODO: besseren text ausdenken...?
		ButtonType yes = new ButtonType("Yes");
		ButtonType no = new ButtonType("No");
		alert.getButtonTypes().setAll(yes, no);
		alert.showAndWait().ifPresent(event-> {
			if(event == yes) ex.setBabysteps(true);
			else if(event == no) ex.setBabysteps(false); //warum geht das ohne else ned??
//			else Constants.getExercise().setBabysteps(false);
		});
		if(ex.getBabysteps()){
			do {
				TextInputDialog b_duration = new TextInputDialog();
				b_duration.setContentText("How many secounds?"); //TODO: besseren text...
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
