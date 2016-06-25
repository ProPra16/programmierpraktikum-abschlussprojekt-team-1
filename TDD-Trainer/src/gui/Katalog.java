package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Katalog extends Stage{
	static Scene scene;
	static BorderPane root = new BorderPane();
	public Katalog(){
		super();
		setScene(create_scene());
	}
	
	
	private static Scene create_scene(){
		root = create_root();
		scene = new Scene(root,500,500);
		return scene;
	}
	private static BorderPane create_root(){
		root.setTop(create_top());
		return root;
	}
	
	private static HBox create_top(){
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10, 50, 10, 50));
	    hbox.setSpacing(15);
	    hbox.setStyle("-fx-background-color: #dcdcdc;");

	    Button zurueck = new Button("zurück");
		Button weiter = new Button("weiter");
		Button laden = new Button("laden");
		
		zurueck.setOnAction(e->{
		});
		weiter.setOnAction(e->{
		});
		laden.setOnAction(e->{
		});

	    hbox.getChildren().addAll(zurueck, weiter, laden);

	    return hbox;
	}
}
