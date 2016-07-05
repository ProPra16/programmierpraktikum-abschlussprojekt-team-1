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
import data.Class;
import data.Project;
import data.Test;
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

public class Catalog extends Stage{
	private Scene scene;
	private BorderPane root = new BorderPane();
	
	private Label exerciseName, description, babysteps, timetracking;
	private TextArea classes;
	private TextArea tests;
	private Button laden;
	
	private String source = "./res/exercise.xml";
	private Document doc = null;
	
	private int currentExercise = 0;
	private int numberOfExercises = 0;
	
	public Catalog(String source){
		super();
		this.source = source;
		setScene(create_scene());
		loadExcercises();
		showExcercise(currentExercise);
	}
	
	public Catalog(){
		super();
		setScene(create_scene());
		loadExcercises();
		showExcercise(currentExercise);
	}
	
	private void clickOnLaden(){
		close();
	}
	
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

	    } catch (Exception e) {
	        Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Übungen konnten nicht geladen werden");
	        alert.setContentText("Konnte daten vom Dokument nicht laden:\n" + input.getAbsolutePath());
	        alert.showAndWait();
	    }
	}
	
	
	private Scene create_scene(){
		root = create_root();
		scene = new Scene(root,600,600);
		return scene;
	}
	
	private BorderPane create_root(){
		root.setTop(create_top());
		root.setCenter(create_center());
		return root;
	}
	
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
	    grid.add(classes, 0, 3, 2, 1);
	    
	    // Text in column 1, row 5
	    Text testsText = new Text("Tests:");
	    testsText.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
	    grid.add(testsText, 0, 4);
	    
	    // TextArea in column 1, row 6
	    tests = new TextArea();
	    tests.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
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

	private void showExcercise(int index) {
		NodeList nList = doc.getElementsByTagName("exercise");
    	Node nNode = nList.item(index);
    	Element eElement = (Element) nNode;
    	if (nNode.getNodeType() == Node.ELEMENT_NODE) {
    		exerciseName.setText(eElement.getAttribute("name")+"");
    		description.setText(eElement.getElementsByTagName("description").item(0).getTextContent());
    	}
    	
    	NodeList classList = eElement.getElementsByTagName("class");
    	String hilfe = "";
    	for(int i = 0; i < classList.getLength(); i++){
    		hilfe = hilfe + ((Element)classList.item(i)).getAttribute("name") + "\n";
    		
    		for(int j = 0; j < classList.item(i).getChildNodes().getLength(); j++){
    			hilfe = hilfe + classList.item(i).getChildNodes().item(j).getNodeValue();
        	}
    		hilfe = hilfe + "\n\n";
    	}
    	classes.setText(hilfe);
    	classes.setEditable(false);
    	hilfe = "";
    	
    	NodeList testList = eElement.getElementsByTagName("test");
    	for(int i = 0; i < testList.getLength(); i++){
    		hilfe = hilfe + ((Element)testList.item(i)).getAttribute("name") + "\n";
    		
    		for(int j = 0; j < testList.item(i).getChildNodes().getLength(); j++){
    			hilfe = hilfe + testList.item(i).getChildNodes().item(j).getNodeValue();
        	}
    		hilfe = hilfe + "\n\n";
    	}
    	tests.setText(hilfe);
    	tests.setEditable(false);
    	hilfe = "";
    	
    	NodeList babyList = eElement.getElementsByTagName("babysteps");
    	if(((Element)babyList.item(0)).getAttribute("value").equals("True")){
    		hilfe = ((Element)babyList.item(0)).getAttribute("value")+ "    time: "+ ((Element)babyList.item(0)).getAttribute("time") ;
    		babysteps.setText(hilfe);
    		
    	} else {
    		babysteps.setText(((Element)babyList.item(0)).getAttribute("value")+ "" );
    	}
    	
    	NodeList trackingList = eElement.getElementsByTagName("timetracking");
    	timetracking.setText(((Element)trackingList.item(0)).getAttribute("value")+ "" );
	}
	
	public Project getProject(){
		NodeList nList = doc.getElementsByTagName("exercise");
    	Node nNode = nList.item(currentExercise);
    	Element eElement = (Element) nNode;
    	
    	
    	NodeList classList = eElement.getElementsByTagName("class");
    	List<Class> klassenListe = new ArrayList<Class>();
    	for(int i = 0; i < classList.getLength(); i++){
    		Class klasse = new Class(classList.item(i).getChildNodes().item(0).getNodeValue(), (String)((Element)classList.item(i)).getAttribute("name"));
    		klassenListe.add(klasse);
    	}
    	
    	NodeList testList = eElement.getElementsByTagName("test");
    	List<Test> tests = new ArrayList<Test>();
    	for(int i = 0; i < testList.getLength(); i++){
    		Test test = new Test((String)(((Element)testList.item(i)).getAttribute("name")),testList.item(i).getChildNodes().item(0).getNodeValue());
    		tests.add(test);
    	}
    	
    	NodeList babyList = eElement.getElementsByTagName("babysteps");
    	boolean babysteps = false;
    	int duration = 0;
    	if(((Element)babyList.item(0)).getAttribute("value").equals("True")){
    		babysteps = true;
    		duration = 0;	//((Element)babyList.item(0)).getAttribute("time") ; 
    						//TODO duration in der xml datei zu einem String machen oder in Project die Zeit als String speichern
    	}
    	
    	NodeList trackingList = eElement.getElementsByTagName("timetracking");
    	boolean tracking = false;
    	if(((Element)trackingList.item(0)).getAttribute("value").equals("True")){
    		tracking = true;
    	}
    	
    	Project project = new Project(tests, klassenListe, eElement.getElementsByTagName("description").item(0).getTextContent(),eElement.getAttribute("name"),babysteps, duration, tracking);
    	
		return project;
	}
}
