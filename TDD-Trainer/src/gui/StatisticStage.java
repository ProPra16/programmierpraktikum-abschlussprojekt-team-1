package gui;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tracking.Event;
import tracking.EventHandler;
import tracking.PhaseStartEvent;

public class StatisticStage extends Stage{
	BorderPane root;
	
	public StatisticStage(){
		root = new BorderPane();
		root.setCenter(root_center());
		Scene scene = new Scene(root,600,600);
		setScene(scene);
	}
	
	public PieChart root_center(){
		List<Event> startEvents= EventHandler.getPhaseStartEvents();
		int test = 0;
		int code = 0;
		int ref  = 0;
		for(Event e : startEvents){
			switch(((PhaseStartEvent)e).getPhase()){
				case 0: test = test+((PhaseStartEvent)e).getDuration();break;
				case 1: code = code+((PhaseStartEvent)e).getDuration();break;
				case 2: ref = ref+((PhaseStartEvent)e).getDuration();break;
			}
		}
		ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Test", test),
                new PieChart.Data("Code", code),
                new PieChart.Data("Refactor", ref));
        PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Time per phase:");
        
		return chart;
	}
	
}
