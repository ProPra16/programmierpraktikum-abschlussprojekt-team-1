package gui;

import java.util.Optional;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.text.Text;

public class CodePane extends TabPane{
	Tab plusTab;
	int plusTabIndex = 0;
	public CodePane(){
		super();
		addPlus();
		
	}
	
	private void clickOnPlus(){
		if(plusTab.isSelected()){
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Class name Input Dialog");
			dialog.setHeaderText("Select a class name");
			dialog.setContentText("Please enter the class name here:");
			Optional<String> result = dialog.showAndWait();
			result.ifPresent(name -> {
				getTabs().remove(plusTabIndex);
				addClass(name);
				getSelectionModel().select(plusTabIndex);
				plusTabIndex++;
				addPlus();
				if(plusTabIndex > 1) getTabs().get(0).setClosable(true);
				if(plusTabIndex == 1) getTabs().get(0).setClosable(false);
			});
		}
	}
	
	private void addClass(String className){ //TODO Klassen Name gross schreiben.
		TextArea text = new TextArea();
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
	}
	private void addPlus(){
		plusTab = new Tab("+");
		plusTab.setClosable(false);
		plusTab.setOnSelectionChanged((event) -> {
			clickOnPlus();
		});
		getTabs().addAll(plusTab);
	}
	public void setEditable(boolean edit){
		for(int i = 0;i<getTabs().size()-1; i++){
			((TextArea)getTabs().get(i).getContent()).setEditable(edit);
			
		}
	}
}
