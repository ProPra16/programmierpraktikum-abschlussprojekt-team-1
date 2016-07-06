package gui;

import data.ConstantsManager;
import data.Project;
//import io.XMLHandler;
import data.Class;
import data.Code;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Repräsentiert die Hauptanwendung. Initialisiert das Hauptfenster und für die Hauptanwendung notwendige Komponenten.
 *
 */

public class Gui extends Application{
	private Phase phase;
	private Timer timer;
	private Project project;
	CodePane code_pane;
	TestPane test_pane;
	ConsolePane console_pane;
	Button next, run, test;
	Text phase1, phase2, phase3, task;
	/* field askForBabysteps: Variable that states if the application should
	 * continue asking for babysteps setting. Is set to false after a correct
	 * answer occurred.
	 */
	private boolean askForBabysteps = true;
	private Button compile;
	
	 /** Startet das Programm.
	  * @param args: Eventuell übergebene Kommandozeilenargumente (werden zumindest bisher nicht weiter beachtet).
	  */
	
	public static void main(String[] args){
		launch();
	}
	/**Ruft {@link AlertHandler#newProject_alert()} und {@link ConstantsManager#getConstants()} auf.
	 * 
	 * 
	 */
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
			project = catalog.getProject();
			ConstantsManager.getConstants().setProject(project);
			stage.setScene(main_scene());
			fillWithContent(ConstantsManager.getConstants().getProject());
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
	/** Erstellt ein Scene-Objekt, das ein Borderpane-Objekt mit einem Tabpane im Zentrum ({@link #create_center()})
	 *  und eimem Gridpane auf der rechten Seite{@link #create_right_side()} erthaelt.
	 * 
	 * @return
	 */
	private Scene main_scene(){
		phase = new Phase();
		BorderPane root = new BorderPane();
		TabPane main = create_center();
		root.setCenter(main);
		root.setRight(create_right_side());
		Scene scene = new Scene(root,700,700); //TODO: groesse von bildschirmgroesse abhaengig machen
		return scene;
	}
	/** Erstellt ein Tabpane, das in drei Tabs ein leeres Test-, Code-, und Konsolenpane enthaelt
	 * Die Tabs koennen nicht geschlossen werden.
	 * @return gibt das ertellte Tabpane zurueck
	 */
	private TabPane create_center(){
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
	/** Erstellt das Gridpane, das die Bedienelemente und Informationen zur aktuellen Phase enthaelt:
	 * - Buttons zum compilieren, testen und uebergehen in die naechste Phase
	 *   (ersten beiden bewirken nur Konsolen-Output)
	 * - eine Grafik, die die aktuelle Phase anzeigt und (bei aktivierten Babysteps) den Timer
	 * @return gibt das erstellte Gridpane zurueck
	 */
	
	private GridPane create_right_side(){
		if (project.getBabysteps()){
			timer= new Timer(project.getDuration(), phase);
			timer.start();
		}
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		run = new Button("run");
		test = new Button("test");
		next = new Button("next");
		compile = new Button("compile");
		phase1 = new Text("Write failing Test");
		phase2 = new Text("Write passing Code");
		phase3 = new Text("Refactor");
		task = new Text("");
		grid.addColumn(1, phase1, phase2, phase3, task, compile, test, next);
		if(project.getBabysteps()) grid.add(timer,2, 2);
		setPhaseTest();
		compile.setOnAction(e->{
			//TODO: run programm & put console output in console tab
		});
		test.setOnAction(e->{
			//TODO: run tests & put console output in console tab
		});
		next.setOnAction(e->{
			if(phase.get()==Phase.TESTS && !project.tests_ok()){ //TODO: i-was das false zurückgibt, wenns ned klappt ins if
				setPhaseCode();
			}
			else if(phase.get() == Phase.CODE && project.tests_ok()){ //TODO: i-was das true zurückgibt wenns lauft ins if
				setPhaseRefactor();
			}
			else if(phase.get() == Phase.REFACTOR && project.tests_ok()){ //TODO: tests muessen laufen
				setPhaseTest();
			}
		});
		return grid;
	}
	
	/** Zeigt die im Project gespeicherten Inhalte im Code- und TestPane an.
	 * @param project: das aktuelle  Projekt
	 */
	private void fillWithContent(Project project){ //TODO: eventuell in test und class aufteilen? projektuebergabe noetig?
		for(Code klasse : project.getClassList()){
			code_pane.addTabWithContent(klasse.getName(), klasse.getContent());
		}
		code_pane.run();
		test_pane.setText(project.getTestList().get(0).getContent());//TODO: nicht dauerhaft 0...
		
	}
	
	/**Fuehrt Handlungen aus, die beim Uebergang in die TestPhase erfolgen
	 * (aendert Infotext & Textfarbe zum anzeigen aktueller Phase, (dis)abelt Textareas)
	 */
	private void setPhaseCode(){
		if(project.getBabysteps()) timer.reset();
		task.setText("Write passing code.");
		phase.next_phase();
		phase1.setFill(Color.BLACK);
		phase2.setFill(Color.GREEN);
		code_pane.setDisable(false);
		test_pane.setDisable(true);
	}
	
	/**Fuehrt Handlungen aus, die beim Uebergang in die Codephase erfolgen
	 * (aendert Infotext & Textfarbe zum anzeigen aktueller Phase, (dis)abelt Textareas)
	 */

	private void setPhaseRefactor(){
		phase.next_phase();
		task.setText("Restucture and simplify your code.");
		phase2.setFill(Color.BLACK);
		phase3.setFill(Color.GREEN);
	}
	
	/**Fuehrt Handlungen aus, die beim Uebergang in die Refactorphase erfolgen
	 * (aendert Infotext & Textfarbe zum anzeigen aktueller Phase, (dis)abelt Textareas)
	 */

	private void setPhaseTest(){
		if(project.getBabysteps()) timer.reset();
		task.setText("Write a failing test.");
		phase.next_phase();
		phase3.setFill(Color.BLACK);
		phase1.setFill(Color.GREEN);
		code_pane.setDisable(true);
		test_pane.setDisable(false);
	}
}