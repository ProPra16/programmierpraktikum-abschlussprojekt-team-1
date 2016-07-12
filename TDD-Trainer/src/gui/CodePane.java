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
	private boolean editable = false; //TODO später müsste es klappen wenn editable = false
	private boolean first = true;
	
	public CodePane(){
		super();
	}
	
	/**
	 * 
	 */
	private void clickOnPlus(){
		if(editable || first){
			if(plusTab.isSelected()){
				dialog("");
			}
		}
	}
	
	private void dialog(String s){
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Class name Input Dialog");
		dialog.setHeaderText("Select a class name" + s);
		dialog.setContentText("Please enter the class name here:");
		Optional<String> result = dialog.showAndWait();
		result.ifPresent(name -> {
			if(!(name.contains(" ") || name.equals(""))){
				getTabs().remove(plusTabIndex);
				addTab(name);
				ConstantsManager.getConstants().getProject().addClass(new Class("",name));
				addPlus();
			} else {
				if(name.contains(" ")) dialog("\nDon't use whitespace!");
				if(name.equals("")) dialog("");
			}
		});
	}
	
	public void addTab(String name){
		addNewClass(name, "");
		getSelectionModel().select(plusTabIndex);
		plusTabIndex++;
		if(plusTabIndex > 1) getTabs().get(0).setClosable(true);
		if(plusTabIndex == 1) getTabs().get(0).setClosable(false);
	}
	public void addTabWithContent(String name, String content){
		addNewClass(name, content);
		getSelectionModel().select(plusTabIndex);
		plusTabIndex++;
		if(plusTabIndex > 1) getTabs().get(0).setClosable(true);
		if(plusTabIndex == 1) getTabs().get(0).setClosable(false);
	}
	
	/**
	 * 
	 * @param className Der Name der Klasse die hinzugefuegt wird.
	 * @param content 
	 */
	private void addNewClass(String className, String content){ //TODO Klassen Name gross schreiben.
		TextArea text = new TextArea();
		text.setText(content);
		Tab classTab = new Tab(className);
		classTab.setOnClosed(e->{
			plusTabIndex--;
			if(plusTabIndex == 1) getTabs().get(0).setClosable(false);
		});
		classTab.setContent(text);
		getTabs().addAll(classTab);
		setOnMouseClicked(e->{
			clickOnPlus();
		});
		first = false;
	}
	
	private void addPlus(){
		plusTab = new Tab("+");
		plusTab.setClosable(false);
		plusTab.setOnSelectionChanged((event) -> {
			clickOnPlus();
		});
		getTabs().addAll(plusTab);
	}
	
	public void run(){
		addPlus();
	}
	
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
	public void setText(String[] text_neu){
		for(int i = 0;i<getTabs().size()-1; i++){
			((TextArea)getTabs().get(i).getContent()).setText(text_neu[i]);
		}
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
