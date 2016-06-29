package gui;

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

public class Gui extends Application{
	private Phase phase;
	private int duration;
	private Timer timer;
	private boolean babysteps;
	CodePane code_pane;
	TestPane test_pane;
	ConsolePane console_pane;
	public static void main(String[] args){
		launch();
	}
	@Override
	public void start(Stage stage){
		AlertHandler.newProject_alert();
		if(babysteps) {
			stage.setOnCloseRequest(e->{
			timer.stop();
			});
		}
	}
	
	private Scene create_scene(){
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
		if (babysteps){
			timer= new Timer(duration, phase);
			timer.start();
		}
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		Button compile = new Button("compile");
		Button test = new Button("test");
		Button next = new Button("next");
		Text phase1 = new Text("Write failing Test");
		Text phase2 = new Text("Write passing Code");
		Text phase3 = new Text("Refactor");
		phase1.setFill(Color.GREEN);
		grid.addColumn(1, phase1, phase2, phase3, compile, test, next);
		if(babysteps) grid.add(timer,2, 2);
		
		compile.setOnAction(e->{
			//TODO: run programm & put console output in console tab
		});
		test.setOnAction(e->{
			//TODO: run tests & put console output in console tab
		});
		next.setOnAction(e->{
			if(phase.get()==Phase.TESTS){ //TODO: i-was das false zurückgibt, wenns ned klappt ins if
				phase.next_phase(); // TODO: zeug disablen
				phase1.setFill(Color.BLACK);
				phase2.setFill(Color.GREEN);
				code_pane.setDisable(false);
				test_pane.setDisable(true);
			}
			else if(phase.get() == Phase.CODE){ //TODO: i-was das true zurückgibt wenns lauft ins if
				phase.next_phase(); //TODO: zeug disablen
				phase2.setFill(Color.BLACK);
				phase3.setFill(Color.GREEN);
			}
			else if(phase.get() == Phase.REFACTOR){ //TODO: tests muessen laufen
				phase.next_phase(); //TODO: zeug disablen
				phase3.setFill(Color.BLACK);
				phase1.setFill(Color.GREEN);
				code_pane.setDisable(true);
				test_pane.setDisable(false);
			}
		});
		return grid;
	}
}