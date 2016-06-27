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
				plusTabIndex++;
				addPlus();
			});
		}
	}
	
	private void addClass(String className){
		TextArea text = new TextArea();
		Tab classTab = new Tab(className);
		classTab.setOnClosed(e->{
			plusTabIndex--;
		});
		classTab.setContent(text);
		getTabs().addAll(classTab);
		setOnMouseClicked(e->{
			clickOnPlus();
		});
	}
	private void addPlus(){
		Text text = new Text("+");
		text.setOnMouseClicked(e->{
			clickOnPlus();
		});
		plusTab = new Tab();
		plusTab.setGraphic(text);
		plusTab.setClosable(false);
		getTabs().addAll(plusTab);
		
		
	}
}
