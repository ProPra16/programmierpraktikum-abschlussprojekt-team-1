package io;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import data.Class;
import data.Code;
import data.Project;
import data.Test;

public class XMLHandler {
	private static File file;
	private static boolean newFile = false;
	
	
	public static void writeProject(Project project){
		Document doc = null;
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder dBuilder;
    	
    	checkFile("./res/myTasks.xml");
    	
		if(newFile){
			try {
				dBuilder = dbFactory.newDocumentBuilder();
				doc = dBuilder.newDocument();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Element exercises = doc.createElementNS(null,"exercises");
			doc.appendChild(exercises);
			
			exercises.appendChild(createExerciseElement(project,doc,exercises));
			
            
            
            Transformer transformer;
			try {
				transformer = TransformerFactory.newInstance().newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
	            DOMSource source = new DOMSource(doc);
	            StreamResult console = new StreamResult(file);
	            transformer.transform(source, console);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			try {
				dBuilder = dbFactory.newDocumentBuilder();
				doc = dBuilder.parse(file);
				Element exercises = doc.getDocumentElement();
				exercises.appendChild(createExerciseElement(project, doc, exercises));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 Transformer transformer;
				try {
					transformer = TransformerFactory.newInstance().newTransformer();
					transformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
		            DOMSource source = new DOMSource(doc);
		            StreamResult console = new StreamResult(file);
		            transformer.transform(source, console);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
		}
		
	}
	
	/**
	 * Es wird ein neues exercise Element erstellt, indem das gesamte Project gespeichert wird und dieses
	 * Element wird zurück gegeben.
	 * @param project
	 * @param doc
	 * @param exercises
	 * @return
	 */
	private static Element createExerciseElement(Project project, Document doc, Element exercises){
		Element exercise = doc.createElement("exercise");
		exercise.setAttribute("name", project.getName());
		
		// füge description hinzu.
		Element description = doc.createElement("description");
		description.appendChild(doc.createTextNode(project.getDescription()));
		exercise.appendChild(description);
		
		// füge classes hinzu.
		Element classes = doc.createElement("classes");
		exercise.appendChild(classes);
		List<Code> klassen = project.getClassList();
		for(int i = 0; i < klassen.size(); i++ ){
			Element klasse = doc.createElement("class");
			klasse.setAttribute("name",klassen.get(i).getName());
			klasse.appendChild(doc.createTextNode(klassen.get(i).getContent()));
			classes.appendChild(klasse);
		}
		
		// füge tests hinzu.
		Element tests = doc.createElement("tests");
		exercise.appendChild(tests);
		List<Code> testsList = project.getTestList();
		for(int i = 0; i < testsList.size(); i++ ){
			Element test = doc.createElement("test");
			test.setAttribute("name",testsList.get(i).getName());
			test.appendChild(doc.createTextNode(((Test)testsList.get(i)).getCode()));
			tests.appendChild(test);
		}
		
		// fügt config hinzu.
		Element config = doc.createElement("config");
		Element babysteps = doc.createElement("babysteps");
		if(project.getBabysteps()){
			babysteps.setAttribute("time", ""+project.getDuration());
			babysteps.setAttribute("value", "True");
		} else {
			babysteps.setAttribute("value", "False");
		}
		config.appendChild(babysteps);
		
		Element timetracking = doc.createElement("timetracking");
		if(project.getTracking()){
			timetracking.setAttribute("value", "True");

		} else {
			timetracking.setAttribute("value", "False");

		}
		config.appendChild(timetracking);
		
		exercise.appendChild(config);
		
		
		return exercise;
	}
	
	/**
	 * Überprüft ob die Datei mit dem mitgegebenen source, ob diese existiert und wenn sie nicht existiert,
	 * dann wird die datei erstellt.
	 * @param source
	 */
	public static void checkFile(String source){
		file = new File(source);
	      if(!file.exists()){
	        try {
	          PrintWriter creator = new PrintWriter("./res/myTasks.xml");
	          creator.close();
	          newFile = true;
	        }
	        catch (Exception e) {
	          System.out.println("Datei konnte nicht erstellt werden");
	        }
	      }
	}
	
	/**
	 * Liest ein Element einer XML-Datei nach "class", "test", "description" usw aus 
	 * und speichert diese in einem "Project"-Objekt. 
	 * @param element
	 * @return das erstellte "Project"-Objekt wird zurück gegeben.
	 */
	public static Project XMLtoProject(Element element){
    	NodeList classList = element.getElementsByTagName("class");
    	List<Code> klassenListe = new ArrayList<Code>();
    	for(int i = 0; i < classList.getLength(); i++){
    		Class klasse = new Class(classList.item(i).getChildNodes().item(0).getNodeValue(), (String)((Element)classList.item(i)).getAttribute("name"));
    		klassenListe.add(klasse);
    	}
    	
    	NodeList testList = element.getElementsByTagName("test");
    	List<Code> tests = new ArrayList<Code>();
    	for(int i = 0; i < testList.getLength(); i++){
    		Test test = new Test((String)(((Element)testList.item(i)).getAttribute("name")),testList.item(i).getChildNodes().item(0).getNodeValue());
    		tests.add(test);
    	}
    	
    	NodeList babyList = element.getElementsByTagName("babysteps");
    	boolean babysteps = false;
    	int duration = 0;
    	if(((Element)babyList.item(0)).getAttribute("value").equals("True")){
    		babysteps = true;
    		duration = Integer.valueOf(((Element)babyList.item(0)).getAttribute("time")); 
    	}
    	
    	NodeList trackingList = element.getElementsByTagName("timetracking");
    	boolean tracking = false;
    	if(((Element)trackingList.item(0)).getAttribute("value").equals("True")){
    		tracking = true;
    	}
    	
    	Project project = new Project(tests, klassenListe, element.getElementsByTagName("description").item(0).getTextContent(),element.getAttribute("name"),babysteps, duration, tracking);
    	
		return project;
	}

}
