package gui;

import data.ConstantsManager;
import data.Project;
import data.Test;
import io.FunPictures;

//import io.XMLHandler;
import data.Code;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Repräsentiert die Hauptanwendung. Initialisiert das Hauptfenster und für die Hauptanwendung notwendige Komponenten.
 *
 */

public class Gui extends Application{
	private FunPictures fun; //fun
	private Phase phase;
	private Timer timer;
	private Project project;
	CodePane code_pane;
	TestPane test_pane;
	ConsolePane console_pane;
	Button next, run, test, back, settings, fun_b;
	Text phase1, phase2, phase3;
	/** field askForBabysteps: Variable that states if the application should
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
		fun = new FunPictures();
		AlertHandler.newProject_alert();
		project = ConstantsManager.getConstants().getProject();
		switch (AlertHandler.returnValue){
		case AlertHandler.NEW_PROJECT:
			ProjectSettings projectSettings = new ProjectSettings();//TODO:lukaaas -  muss das dahin? oder wird das auch so gespeichert? :)
			project = projectSettings.getProject();
			ConstantsManager.getConstants().setProject(project);
			stage.setScene(main_scene());
			fillWithContent(ConstantsManager.getConstants().getProject());
			stage.show();
			break;
		case AlertHandler.LOAD_TEMPLATE:
			Catalog catalog = new Catalog();
			catalog.showAndWait();
			if(catalog.load()){
				project = catalog.getProject();
				ConstantsManager.getConstants().setProject(project);
				stage.setScene(main_scene());
				fillWithContent(ConstantsManager.getConstants().getProject());
				stage.show();
			}
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
		root.setBottom(create_bottom());
		root.setRight(create_right_side());
		Scene scene = new Scene(root,700,700); //TODO: groesse von bildschirmgroesse abhaengig machen
		return scene;
	}
	
	private HBox create_bottom(){
		HBox hBox = new HBox();
		Button settings = new Button("Settings...");
		settings.setOnAction((event) -> {
			new ProjectSettings();
		});
		hBox.getChildren().add(settings);
		hBox.setAlignment(Pos.CENTER_RIGHT);
		hBox.setPadding(new Insets(10, 10, 10, 10));
		return hBox;
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
		back = new Button("back");
		fun_b = new Button("fun"); //fun
		compile = new Button("compile");
		phase1 = new Text("Write failing Test");
		phase2 = new Text("Write passing Code");
		phase3 = new Text("Refactor");
		grid.addColumn(1, phase1, phase2, phase3, compile, test, next, fun_b);
		if(project.getBabysteps()) grid.add(timer,2, 2);
		setPhaseTest();
		fun_b.setOnAction(e->{//fun
			fun.showRandom();
		});

		compile.setOnAction(e->{
			updateClassProject();
			project.compile();
		});
		test.setOnAction(e->{
			updateClassProject();
			updateTestProject();
			project.test();
		});
		/**
		 * 
		 */
		back.setOnAction(e->{
			phase.back();
			project.backToOldCode(project.CLASS);
		});
		next.setOnAction(e->{
			System.out.println(phase.get());
			if(phase.get()==Phase.TESTS){
				System.out.println("test-phase");
				updateTestProject();
				if(project.testHasCompileErrors()){
					System.out.println("test-phase-compfail");
					phase.next();
					setPhaseCode();
				}
				else if(!project.tests_ok()) {
					System.out.println("test-phase-fail");
					phase.next();
					setPhaseCode();
				}
			}
			else{
				updateClassProject();
				if(phase.get() == Phase.CODE && project.tests_ok()){
					phase.next();
					setPhaseRefactor();
				}
				else if(phase.get() == Phase.REFACTOR && project.tests_ok()){
					phase.next();
					setPhaseTest();
				}
			}
		});
		return grid;
	}
	private void updateTestProject(){
		String content = "";
		System.out.println("tabzahl "+code_pane.getTabs().size());
		for(int i= 0;i<code_pane.getTabs().size()-1;i++){ //-1, da der letzte der "+"-Tab ist
			System.out.println("updatecounter "+i);
			if(( code_pane.getTabs().get(i).getContent()) != null){
				content = ((TextArea) code_pane.getTabs().get(i).getContent()).getText();
			};
			project.setNewTestOrClassCode(i, content,project.TEST);
		}

	}
	private void updateClassProject(){
		String content = "";
		for(int i= 0;i<code_pane.getTabs().size()-1;i++){ //-1, da der letzte der "+"-Tab ist
			if(( code_pane.getTabs().get(i).getContent()) != null){
				content = ((TextArea) code_pane.getTabs().get(i).getContent()).getText();
			};
			project.setNewTestOrClassCode(i, content,project.CLASS);
		}

	}

	/** Zeigt die im Project gespeicherten Inhalte im Code- und TestPane an.
	 * @param project: das aktuelle  Projekt
	 */
	private void fillWithContent(Project project){ //TODO: eventuell in test und class aufteilen? projektuebergabe noetig?
		for(Code klasse : project.getClassList()){
			code_pane.addTabWithContent(klasse.getName(), klasse.getContent());
		}
		code_pane.run();
		if(project.getTestList().size() == 0){
			project.addTest(new Test(project.getName()));//TODO: macht das katalog einlesen kaputt?
		}
		test_pane.setText(((Test)project.getTestList().get(0)).getContent());//TODO: nicht dauerhaft 0...
		code_pane.setEditable(false);
		System.out.println(project.getTestList().get(0).getContent());
		
	}
	
	/**Fuehrt Handlungen aus, die beim Uebergang in die TestPhase erfolgen
	 * 
	 * (aendert Infotext & Textfarbe zum anzeigen aktueller Phase, (dis)abelt Textareas,
	 * aktualisiert Project)
	 * 
	 */
	private void setPhaseCode(){
		if(project.getBabysteps()) timer.reset();
		phase1.setFill(Color.BLACK);
		phase2.setFill(Color.GREEN);
		code_pane.setEditable(false);
		test_pane.setDisable(true);
		project.overrideOldCode(project.TEST);
		test_pane.clear();
		back.setDisable(false);
	}
	
	/**Fuehrt Handlungen aus, die beim Uebergang in die Codephase erfolgen
	 * (aendert Infotext & Textfarbe zum anzeigen aktueller Phase, (dis)abelt Textareas)
	 */

	private void setPhaseRefactor(){
		phase2.setFill(Color.BLACK);
		phase3.setFill(Color.GREEN);
		project.overrideOldCode(project.CLASS);
		back.setDisable(true);
	}
	
	/**Fuehrt Handlungen aus, die beim Uebergang in die Refactorphase erfolgen
	 * (aendert Infotext & Textfarbe zum anzeigen aktueller Phase, (dis)abelt Textareas)
	 */

	private void setPhaseTest(){
		if(project.getBabysteps()) timer.reset();
		phase3.setFill(Color.BLACK);
		phase1.setFill(Color.GREEN);
		code_pane.setEditable(true);
		test_pane.setDisable(false);
		project.overrideOldCode(project.CLASS);
		back.setDisable(true);

	}
}