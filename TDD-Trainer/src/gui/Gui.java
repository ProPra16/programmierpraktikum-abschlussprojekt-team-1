package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Gui extends Application{
	public static void main(String[] args){
		launch();
	}
	@Override
	public void start(Stage stage){
		Scene main_scene = create_scene();
		stage.setScene(main_scene);
		stage.show();
	}
	private Scene create_scene(){
		BorderPane root = new BorderPane();
		TabPane main = create_buttons_top(); //TODO: doofer name
		root.setCenter(main);
		Scene scene = new Scene(root,700,700); //TODO: groesse von bildschirmgroesse abhaengig machen
		return scene;
	}
	private TabPane create_buttons_top(){
		TabPane menue = new TabPane();
		menue.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		Tab code = new Tab("Code");
		Tab test = new Tab("Tests");
		Tab konsole = new Tab("Konsole");
		code.setContent(null); //TODO
		test.setContent(null); //TODO
		konsole.setContent(null); //TODO
		menue.getTabs().addAll(code, test, konsole);
		return menue;
	}
}