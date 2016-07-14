package gui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import data.Code;
import data.Project;
import data.Test;
import io.XMLHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Repräsentiert das Katalogfenster, mit dem der Nutzer bestehende oder Beispielprojekte ansehen
 * und laden kann.
 *
 */
public class Catalog extends Stage{
	private Scene scene;
	private BorderPane root = new BorderPane();
	
	private Label exerciseName, description, babysteps, timetracking;
	private TextArea classes;
	private TextArea tests;
	private Button laden;
	
	private String source = "./res/exercise.xml";
	private Document doc = null;
	
	private List<Project> projects;
	private int currentExercise = 0;
	private int numberOfExercises = 0;
	private boolean load = false;
	
	/**
	 * Der Konstruktor zeigt das Katalogfenster an, mit den Aufgaben aus der 
	 * mitgegebenen source.
	 * @param source
	 */
	public Catalog(String source){
		super();
		this.source = source;
		setScene(create_scene());
		loadExcercises();
		showExcercise(currentExercise);
	}
	
	/**
	 * Der Konstruktor zeigt das Katalogfenster an, mit den Aufgaben
	 * aus der source ./res/exercise.xml. 
	 */
	public Catalog(){
		super();
		setScene(create_scene());
		loadExcercises();
		showExcercise(currentExercise);
	}
	
	/**
	 * Das Katalog-Fenster wird geschlossen.
	 */
	private void clickOnLaden(){
		load = true;
		close();
	}
	
	/**
	 * Gibt zuück ob eine Datei geladen werden soll.
	 * @return
	 */
	public boolean load(){
		return load;
	}
	
	/**
	 * Es wird versucht die XML-Datei zu laden. Jedes Projekt von der XML-Datei
	 * wird, mithilfe des XMLHandlers,in ein Projekt Objekt eingelesen und in einer Liste gespeichert.
	 */
	private void loadExcercises(){
		File input = null;
	    try {
	    	input = new File(source);
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	doc = dBuilder.parse(input);
	    	doc.getDocumentElement().normalize();
	    	
	    	
	    	NodeList nList = doc.getElementsByTagName("exercise");
	    	numberOfExercises = nList.getLength();
	    	
		    projects = new ArrayList<Project>();
		    for(int i = 0; i < numberOfExercises; i++){
		    	Node nNode = nList.item(i);
		    	Element element = (Element) nNode;
		    	
		    	projects.add(XMLHandler.XMLtoProject(element));
		    }

	    } catch (Exception e) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Übungen konnten nicht geladen werden");
	        alert.setContentText("Konnte daten vom Dokument nicht laden:\n" + input.getAbsolutePath());
	        alert.showAndWait();
	    }
	}
	
	/**
	 * Erstellt ein Scene-Objekt das ein BorderPane{@link #create_root()} inne hat.
	 * @return gibt die erstelle Scene für den Katalog zurück.
	 */
	private Scene create_scene(){
		root = create_root();
		scene = new Scene(root,600,600);
		return scene;
	}
	
	/**
	 * Erstellt die obere Leiste{@link #create_top()} des Kataloges und erstellt das 
	 * Anzeigefenster{@link #create_center()} des Kataloges und fügt diese einem BorderPane hinzu.
	 * @return das erstellte BorderPane wird zurück gegeben.
	 */
	private BorderPane create_root(){
		BorderPane all = new BorderPane();
		all.setTop(create_top());
		all.setCenter(create_center());
		return all;
	}
	
	/**
	 * Erstellt ein GridPane mit Anzeigemöglichkeiten und Beschriftungen um ein Projekt anzuzeigen.
	 * @return das erstellte GridPane wird zurück gegeben.
	 */
	private GridPane create_center() {
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(15, 50, 0, 50));
		grid.setStyle("-fx-background-color: #f5f5f5;");
		
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
	    description.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
	    grid.add(description, 1, 1);
	    
	    // Text in column 1, row 3
	    Text classesText = new Text("Classes:");
	    classesText.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
	    grid.add(classesText, 0, 2);
	    
	    // TextArea in column 1, row 4
	    classes = new TextArea();
	    classes.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
	    classes.setEditable(false);
	    grid.add(classes, 0, 3, 2, 1);
	    
	    // Text in column 1, row 5
	    Text testsText = new Text("Tests:");
	    testsText.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
	    grid.add(testsText, 0, 4);
	    
	    // TextArea in column 1, row 6
	    tests = new TextArea();
	    tests.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
	    tests.setEditable(false);
	    grid.add(tests, 0, 5, 2, 1);
	    
	    // Text in column 1, row 7
	    Text babystepsText = new Text("Babysteps:");
	    babystepsText.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
	    grid.add(babystepsText, 0, 6);
	    
	    // Label in column 2, row 7
	    babysteps = new Label();
	    babysteps.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
	    grid.add(babysteps, 1, 6);
	    
	    // Text in column 1, row 8
	    Text trackingText = new Text("Tracking:");
	    trackingText.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
	    grid.add(trackingText, 0, 7);
	    
	    // Label in column 2, row 8
	    timetracking = new Label();
	    timetracking.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
	    grid.add(timetracking, 1, 7);
	    
		return grid;
	}

	/**
	 * Eine Leiste mit den Knöpfen "Previous", "Next" und "Load this!" wird erstellt.
	 * "Previous" nimmt das vorherige Projekt aus der Liste und zeigt{@link #showExcercise(int)} dieses an.
	 * "Next" nimmt das nächste Projekt aus der Liste und zeigt{@link #showExcercise(int)} dieses an. 
	 * "Load this" schließt das Programm{@link #clickOnLaden()}.
	 * @return
	 */
	private HBox create_top(){
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10, 50, 10, 50));
	    hbox.setSpacing(15);
	    hbox.setStyle("-fx-background-color: #dcdcdc;");

	    Button zurueck = new Button("Previous");
		Button weiter = new Button("Next");
		laden = new Button("Load this!");
		laden.setOnAction(e->{
			clickOnLaden();
		});
		
		zurueck.setOnAction(e->{
			currentExercise = ((currentExercise-1)+numberOfExercises)%numberOfExercises;
			showExcercise(currentExercise);
		});
		weiter.setOnAction(e->{
			currentExercise = (currentExercise+1)%numberOfExercises;
			showExcercise(currentExercise);
		});

	    hbox.getChildren().addAll(zurueck, weiter, laden);

	    return hbox;
	}

	/**
	 * Zeigt ein Projekt an, indem ein "Project" Objekt ausgelesen wird und die jeweiligen
	 * Informationen in die jeweiligen Labels oder TextAreas geschrieben werden.
	 * @param index des Project aus der Gespeicherten Liste welches angezeigt werden soll.
	 */
	private void showExcercise(int index) {
		Project project = projects.get(index);
		
		exerciseName.setText(project.getName());
		description.setText(project.getDescription());
		
		String hilfe = "";
		List<Code> klasse = project.getClassList();
		for(int i = 0; i < klasse.size(); i++){
    		hilfe = hilfe + klasse.get(i).getName() + "\n";
    		hilfe = hilfe + klasse.get(i).getContent() + "\n\n";
    	}
		classes.setText(hilfe);
    	
		List<Code> test = project.getTestList();
		hilfe = "";
		for(int i = 0; i < test.size(); i++){
    		hilfe = hilfe + test.get(i).getName() + "\n";
    		hilfe = hilfe + test.get(i).getContent() + "\n\n";
    	}
		tests.setText(hilfe);
		
		if(project.getBabysteps()){
    		babysteps.setText(project.getBabysteps() + "    time: "+ project.getDuration());
    		
    	} else {
    		babysteps.setText(project.getBabysteps()+"");
    	}
		timetracking.setText(project.getTracking()+"" );
	}
	
	/**
	 * Gibt das "Project" zurück das beim klicken auf Laden gerade angezeigt wurde.
	 * @return das ausgewählte Projekt.
	 */
	public Project getProject(){    	
		return projects.get(currentExercise);
	}
}
