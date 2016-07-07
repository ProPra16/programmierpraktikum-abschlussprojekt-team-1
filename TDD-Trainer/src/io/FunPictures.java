package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FunPictures {
	private final Path source = Paths.get("./res/Pic/");
	private List<Image> images;
	
	public FunPictures(){
		try {
			Stream<Path> list = Files.list(source);
			images = new ArrayList<Image>();
			list.forEach(e->{
			images.add(new Image("file:"+e.toString()));
			});
		}catch (IOException e1) {
			e1.printStackTrace();
		}catch (IllegalArgumentException e2){
			e2.printStackTrace();
		}
	}
	public void showRandom(){
		Stage fun = new Stage();
		StackPane pane = new StackPane();
		Image random = images.get(random());
		pane.getChildren().add(new ImageView(random));
		Scene fun2 = new Scene(pane,random.getWidth(),random.getHeight());
		fun.setScene(fun2);
		fun.show();
		fun.setOnCloseRequest(e->{
			showRandom();
		});
	}
	private int random(){
		return (int) (Math.random()*images.size());
	}
}
