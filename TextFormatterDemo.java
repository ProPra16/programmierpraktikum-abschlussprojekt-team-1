import javafx.application.Application;

import javafx.geometry.Insets;

import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import javafx.scene.layout.HBox;

import javafx.stage.Stage;

import javafx.util.StringConverter;

public class TextFormatterDemo extends Application
{
   @Override
   public void start(Stage primaryStage)
   {
      Label lblAge = new Label("Age");
      TextField txtAge = new TextField("");
      StringConverter<Integer> formatter;
      formatter = new StringConverter<Integer>()
                  {
                     @Override
                     public Integer fromString(String string)
                     {
                        System.out.println("fromString(): string = " + string);
                        return Integer.parseInt(string);
                     }

                     @Override
                     public String toString(Integer object)
                     {
                        System.out.println("toString(): object = " + object);
                        if (object == null)
                           return "0";
                        System.out.println("object.tostring = " + 
                                           object.toString());
                        return object.toString();
                     }
                  };
      txtAge.setTextFormatter(new TextFormatter<Integer>(formatter));
      HBox hboxForm = new HBox(10);
      hboxForm.setPadding(new Insets(10, 10, 10, 10));
      hboxForm.getChildren().addAll(lblAge, txtAge);
      Scene scene = new Scene(hboxForm);
      primaryStage.setScene(scene);
      primaryStage.setResizable(false);
      primaryStage.setTitle("TextFormatterDemo");
      primaryStage.show();
   }
}
