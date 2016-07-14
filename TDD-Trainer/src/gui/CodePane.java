package gui;

import java.util.ArrayList;
import data.Class;
import java.util.List;
import java.util.Optional;

import data.ConstantsManager;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

/**
 * Repräsentiert ein Panel, welches im Programm dazu gedacht ist, das Textfelder mit
 * dem Programmcode zu beherbergen.
 *
 */
public class CodePane extends TabPane{
	Tab plusTab;
	int plusTabIndex = 0;
	private boolean editable = false;
	private boolean first = true;
	
	public CodePane(){
		super();
	}
	
	private void clickOnPlus(){
		if(editable || first){
			if(plusTab.isSelected()){
				dialog("");
			}
		}
	}
	/**
	 * Fragt in einem Dialogfenster nach einem Klassennamen und erstellt mit diesem eine neue Klasse, die im Projekt und der GUI hinzugefügt wird
	 * @param s eine zusätzliche Nachricht die im Header des aufgerufenen Dialog-Fensters angezeigt wird
	 */
	private void dialog(String s){
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Class name Input Dialog");
		dialog.setHeaderText("Select a class name" + s);
		dialog.setContentText("Please enter the class name here:");
		Optional<String> result = dialog.showAndWait();
		result.ifPresent(name -> {
			if(!(name.contains(" ") || name.equals("")) && Character.isUpperCase(name.charAt(0))){
				getTabs().remove(plusTabIndex);
				String code = "public class "+ name + "{\n\n}";
				addTabWithContent(name,code);
				Class klasse = new Class(code,name);
				klasse.overrideOldCode();
				ConstantsManager.getConstants().getProject().addClass(klasse);
				addPlus();
			}else{
			}
			if(name.contains(" ")) dialog("\nDon't use whitespace!");
			if(name.equals("")) dialog("");
			if(Character.isLowerCase(name.charAt(0))) dialog("\nwith a large initial letter!");
		});
	}
	
	/**
	 * Ein neuer leerer Tab mit dem Namen 'name' wird hinzugefügt.
	 * Dabei wird addNewClass{@link CodePane#addNewClass(String, String)} genutzt.
	 * @param name
	 */
	public void addTab(String name){
		addNewClass(name, "");
		getSelectionModel().select(plusTabIndex);
		plusTabIndex++;
		if(plusTabIndex > 1) getTabs().get(0).setClosable(true);
		if(plusTabIndex == 1) getTabs().get(0).setClosable(false);
	}
	
	/**
	 * Ein neuer Tab mit dem Namen 'name' und mit dem Inhalt 'content' wird hinzugefügt.
	 * Dabei wird addNewClass{@link CodePane#addNewClass(String, String)} genutzt.
	 * @param name
	 * @param content
	 */
	public void addTabWithContent(String name, String content){
		addNewClass(name, content);
		getSelectionModel().select(plusTabIndex);
		plusTabIndex++;
		if(plusTabIndex > 1) getTabs().get(0).setClosable(true);
		if(plusTabIndex == 1) getTabs().get(0).setClosable(false);
	}
	
	/**
	 * Es wird ein neuer Tab mit einer TextArea erstellt.
	 * Der Tab bekommt als Namen 'className' und in die TextArea wird 'content' geschrieben.
	 * 
	 * @param className
	 * @param content 
	 */
	private void addNewClass(String className, String content){
		TextArea text = new TextArea();
		text.setText(content);
		Tab classTab = new Tab(className);
		classTab.setOnClosed(e->{
			plusTabIndex--;
			ConstantsManager.getConstants().getProject().removeClass(className);
			if(plusTabIndex == 1) getTabs().get(0).setClosable(false);
		});
		classTab.setContent(text);
		getTabs().addAll(classTab);
		setOnMouseClicked(e->{
			clickOnPlus();
		});
		first = false;
	}
	/**
	 * Fügt einen "+"-Tab hinzu
	 * Dieser ist nicht schließbar und ruft wenn er ausgewählt wird {@link #clickOnPlus()} auf
	 */
	private void addPlus(){
		plusTab = new Tab("+");
		plusTab.setClosable(false);
		plusTab.setOnSelectionChanged((event) -> {
			clickOnPlus();
		});
		getTabs().addAll(plusTab);
	}
	
	/**
	 * fügt einen Plus-Tab hinzu. 
	 */
	public void run(){
		addPlus();
	}
	
	/**
	 * Alle Tabs werden auf den boolean 'edit' gesetzt.
	 * @param edit beschreibt ob Tabinhalte editable sein sollen
	 */
	public void setEditable(boolean edit){
		for(int i = 0;i<getTabs().size()-1; i++){
			((TextArea)getTabs().get(i).getContent()).setEditable(edit);
			if(getTabs().size() > 2) getTabs().get(i).setClosable(edit);
			editable = edit;
		}
	}
	
	/**
	 * Setter der TextArea-Inhalte des CodePanes
	 */
	public void setText(int position, String text_neu){
		((TextArea)getTabs().get(position).getContent()).setText(text_neu);
	}
	
	/**
	 * Getter der TextArea-Inhalte des CodePanes
	 * @return Gibt eine String-Liste mit allen Code-Strings zurueck.
	 */
	public List<String> getText(){
		List<String> list = new ArrayList<String>();
		for(int i = 0;i<getTabs().size()-1; i++){
			list.add(((TextArea)getTabs().get(i).getContent()).getText());
		}
		return list;
	}
}
