package edu.tridenttech.cpt237.lucas;

import edu.tridenttech.cpt237.lucas.view.LogInWindow;
import javafx.application.Application;
import javafx.stage.Stage;


public class MainApplication extends Application{

	
	
	public static void main(String[] args) {		
		
		Application.launch(args);
	}


	@Override
	 public void start(Stage primaryStage)
   {
       new LogInWindow(primaryStage).show();
   }

}