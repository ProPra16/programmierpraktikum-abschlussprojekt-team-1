package io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import data.Class;
import data.Code;
import data.Project;
import data.Test;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class XMLHandler {
	public static void writeProject(Project project){
		
	}
	
	private static boolean checkFile(File file){
		if (file != null) {
			try {
				file.createNewFile();
				} catch (IOException e) {
					System.err.println("Error creating " + file.toString());
					}
			if (file.isFile() && file.canWrite() && file.canRead())
				return true;
			}
		return false;
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
