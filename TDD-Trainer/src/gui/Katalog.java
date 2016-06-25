package gui;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Katalog extends Stage{
	static Scene scene;
	static BorderPane root = new BorderPane();
	
	static Label exerciseName, description;
	
	static Document doc = null;
	
	static int currentExercise = 0;
	static int numberOfExercises = 0;
	
	public Katalog(){
		super();
		setScene(create_scene());
		loadExcercises();
		showExcercise(currentExercise);
	}
	
	private static void loadExcercises(){
		File input = null;
	    try {
	    	input = new File("./res/exercise.xml");
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	doc = dBuilder.parse(input);
	    	doc.getDocumentElement().normalize();
	    	
	    	
	    	NodeList nList = doc.getElementsByTagName("exercise");
	    	numberOfExercises = nList.getLength();

	    } catch (Exception e) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Übungen konnten nicht geladen werden");
	        alert.setContentText("Konnte daten vom Dokument nicht laden:\n" + input.getAbsolutePath());
	        alert.showAndWait();
	    }
	}
	
	
	private static Scene create_scene(){
		root = create_root();
		scene = new Scene(root,600,600);
		return scene;
	}
	private static BorderPane create_root(){
		root.setTop(create_top());
		root.setCenter(create_center());
		return root;
	}
	
	private static GridPane create_center() {
		GridPane grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(5);
		grid.setPadding(new Insets(0, 10, 0, 10));
		
		// Text in column 1, row 1
	    Text exerciseNameText = new Text("Excercise:");
	    exerciseNameText.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
	    grid.add(exerciseNameText, 0, 0);
	    
	    // Label in column 2, row 1
	    exerciseName = new Label();
	    exerciseName.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
	    grid.add(exerciseName, 1, 0);
		
	    // Text in column 1, row 2
	    Text descriptionText = new Text("Description:");
	    descriptionText.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
	    grid.add(descriptionText, 0, 1);
	    
	 // Label in column 2, row 2
	    description = new Label();
	    description.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
	    grid.add(description, 1, 1);
	    
		return grid;
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
			currentExercise = (currentExercise-1)%numberOfExercises;
			showExcercise(currentExercise);
		});
		weiter.setOnAction(e->{
			currentExercise = (currentExercise+1)%numberOfExercises;
			showExcercise(currentExercise);
		});
		laden.setOnAction(e->{
			//TODO 
		});

	    hbox.getChildren().addAll(zurueck, weiter, laden);

	    return hbox;
	}

	private static void showExcercise(int index) {
		NodeList nList = doc.getElementsByTagName("exercise");
    	Node nNode = nList.item(index);
    	if (nNode.getNodeType() == Node.ELEMENT_NODE) {
    		Element eElement = (Element) nNode;
    		exerciseName.setText(eElement.getAttribute("name")+"");
    		description.setText(eElement.getElementsByTagName("description").item(0).getTextContent());
    	}
    	
    	
    	// Funktionalität unbekannt
    	// TODO Alles soll angezeigt werden
    	if(nNode.getNodeType() == Node.DOCUMENT_NODE){
    		doc = (Document)nNode;
	    	doc.getDocumentElement().normalize();
	    	
	    	
	    	NodeList classesList = doc.getElementsByTagName("class");
	    	
    	}
		
	}
}
