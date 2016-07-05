package gui;

import data.ConstantsManager;
import data.Project;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Gui extends Application{
	private Phase phase;
	private Timer timer;
	private Project project;
	CodePane code_pane;
	TestPane test_pane;
	ConsolePane console_pane;
	Button next, run, test;
	Text phase1, phase2, phase3;
	/* field askForBabysteps: Variable that states if the application should
	 * continue asking for babysteps setting. Is set to false after a correct
	 * answer occurred.
	 */
	private boolean askForBabysteps = true;
	
	public static void main(String[] args){
		launch();
	}
	
	public void start(Stage stage){
		AlertHandler.newProject_alert();
		project = ConstantsManager.getConstants().getProject();
		switch (AlertHandler.returnValue){
		case AlertHandler.NEW_PROJECT:
			new ProjectSettings();
			stage.setScene(main_scene());
			stage.show();
			break;
		case AlertHandler.LOAD_TEMPLATE:
			Catalog catalog = new Catalog();
			catalog.showAndWait();
			//TODO load Exercise
			stage.setScene(main_scene());
			stage.show();
			break;
		case AlertHandler.LOAD_PROJECT:
			break;
		}

		if(ConstantsManager.getConstants().getProject().getBabysteps()) {
			stage.setOnCloseRequest(e->{
			timer.stop();
			});
		}
	}
	
<<<<<<< HEAD
	private void laden_neu(Stage stage){
		Alert loadOrNew = new Alert(AlertType.CONFIRMATION);
		loadOrNew.setHeaderText("Load an exercise?");
		loadOrNew.setContentText("Load an exercise or create a new project?");
		ButtonType load = new ButtonType("Load");
		ButtonType newProgram = new ButtonType("New project");
		loadOrNew.getButtonTypes().setAll(load, newProgram);
		loadOrNew.showAndWait().ifPresent(event-> {
			if(event == load){
				loadOrNew.close();
				Catalog katalog = new Catalog();
				katalog.showAndWait();
				if(katalog.wurdeGeladen()){
					stage.setScene(create_scene());
					stage.show();
				}
			}
			else if(event == newProgram){
				babysteps_alert();
				stage.setScene(create_scene());
				stage.show();
			}
		});
	}
	
	private Scene main_scene(){
>>>>>>> origin/master
		phase = new Phase();
		BorderPane root = new BorderPane();
		TabPane main = create_buttons_top(); //TODO: doofer name
		root.setCenter(main);
		root.setRight(create_right_side());
		Scene scene = new Scene(root,700,700); //TODO: groesse von bildschirmgroesse abhaengig machen
		return scene;
	}
	private TabPane create_buttons_top(){
		TabPane menue = new TabPane();
		menue.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		code_pane = new CodePane();
		test_pane = new TestPane();
		console_pane = new ConsolePane();
		Tab code = new Tab("Code");
		Tab test = new Tab("Tests");
		Tab console = new Tab("Konsole");
		code.setContent(code_pane);
		test.setContent(test_pane);
		console.setContent(console_pane);
		code.setContent(code_pane);
		test.setContent(test_pane);
		console.setContent(console_pane);
		menue.getTabs().addAll(code, test, console);
		return menue;
	}
	private GridPane create_right_side(){
		if (project.getBabysteps()){
			timer= new Timer(project.getDuration(), phase);
			timer.start();
		}
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
<<<<<<< HEAD
		run = new Button("run");
		test = new Button("test");
		next = new Button("next");
		phase1 = new Text("Write failing Test");
		phase2 = new Text("Write passing Code");
		phase3 = new Text("Refactor");
=======
		Button compile = new Button("compile");
		Button test = new Button("test");
		Button next = new Button("next");
		Text phase1 = new Text("Write failing Test");
		Text phase2 = new Text("Write passing Code");
		Text phase3 = new Text("Refactor");
>>>>>>> origin/master
		phase1.setFill(Color.GREEN);
		grid.addColumn(1, phase1, phase2, phase3, compile, test, next);
		if(project.getBabysteps()) grid.add(timer,2, 2);
		
		compile.setOnAction(e->{
			//TODO: run programm & put console output in console tab
		});
		test.setOnAction(e->{
			//TODO: run tests & put console output in console tab
		});
		next.setOnAction(e->{
			if(phase.get()==Phase.TESTS){ //TODO: i-was das false zurückgibt, wenns ned klappt ins if
<<<<<<< HEAD
				setPhaseTest();
			}
			else if(phase.get() == Phase.CODE){ //TODO: i-was das true zurückgibt wenns lauft ins if
				setPhaseCode();
			}
			else if(phase.get() == Phase.REFACTOR){ //TODO: tests muessen laufen
				setPhaseRefactor();
=======
				phase.next_phase();
				phase1.setFill(Color.BLACK);
				phase2.setFill(Color.GREEN);
				code_pane.setEditable(true);
				test_pane.setEditable(false);
			}
			else if(phase.get() == Phase.CODE){ //TODO: i-was das true zurückgibt wenns lauft ins if
				phase.next_phase();
				phase2.setFill(Color.BLACK);
				phase3.setFill(Color.GREEN);
			}
			else if(phase.get() == Phase.REFACTOR){ //TODO: tests muessen laufen
				phase.next_phase();
				phase3.setFill(Color.BLACK);
				phase1.setFill(Color.GREEN);
				code_pane.setEditable(false);
				test_pane.setEditable(true);
>>>>>>> origin/master
			}
		});
		return grid;
	}
<<<<<<< HEAD
	private void babysteps_alert(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Use Babysteps?");
		alert.setContentText("Do you want to use 'babysteps'?"); //TODO: besseren text ausdenken...?
		ButtonType yes = new ButtonType("Yes");
		ButtonType no = new ButtonType("No");
		alert.getButtonTypes().setAll(yes, no);
		alert.showAndWait().ifPresent(event-> {
			if(event == yes) babysteps = true;
			else if(event == no)  babysteps = false; //warum geht das ohne else ned??
			else babysteps = false;
		});
		System.out.println(babysteps);
		askForBabysteps = true;
		do {
			if(babysteps){
				TextInputDialog b_duration = new TextInputDialog();
				b_duration.setContentText("How many seconds for each stage?"); //TODO: besseren text...
				b_duration.setHeaderText(null);
				b_duration.showAndWait().ifPresent(input ->{
					try{
						duration = Integer.parseInt(input);
						askForBabysteps = false;
					} catch(NumberFormatException e){
						System.out.println("Banane!");
						Alert error = new Alert(AlertType.ERROR);
						error.setHeaderText("Please enter an integer!");
						error.setContentText("The duration you supplied couldn't be parsed for an integer. Please try again.");
						error.showAndWait();
					}
				});
			}
		} while (askForBabysteps);
	}
	
	private void setPhaseTest(){
		phase.next_phase(); // TODO: zeug disablen
		phase1.setFill(Color.BLACK);
		phase2.setFill(Color.GREEN);
		code_pane.setDisable(false);
		test_pane.setDisable(true);
	}
	
	private void setPhaseCode(){
		phase.next_phase(); //TODO: zeug disablen
		phase2.setFill(Color.BLACK);
		phase3.setFill(Color.GREEN);
	}
	
	private void setPhaseRefactor(){
		phase.next_phase(); //TODO: zeug disablen
		phase3.setFill(Color.BLACK);
		phase1.setFill(Color.GREEN);
		code_pane.setDisable(true);
		test_pane.setDisable(false);
	}
=======
>>>>>>> origin/master
}