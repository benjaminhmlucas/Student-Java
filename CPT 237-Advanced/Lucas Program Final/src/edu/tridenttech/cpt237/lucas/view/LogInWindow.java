package edu.tridenttech.cpt237.lucas.view;
/**
 * @file: LogInWindow.java
 * @author: Ben Lucas
 * @purpose: Class creates a GUI for selecting a specific Employee instance to use.  Depending on whether
 * employee is a server or manager than either the opens the ManagerSelectWindow GUI or the ServerCheckSelectWindow
 * GUI.  
 */
import edu.tridenttech.cpt237.lucas.model.Employee;
import edu.tridenttech.cpt237.lucas.model.Manager;
import edu.tridenttech.cpt237.lucas.model.Restaurant;
import edu.tridenttech.cpt237.lucas.model.Server;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LogInWindow {

	private Stage logIn;
	
	private static Restaurant restaurant = Restaurant.loadRestaurant();//instantiates singleton restaurant object
	private ManagerSelectWindow managerSelectWin = new ManagerSelectWindow();//set line 94
	private ServerCheckSelectWindow serverChecksWindow = new ServerCheckSelectWindow();//set line 99

	private Button btnLogin = new Button("Login");    
    private Button btnClose = new Button("Close Restaurant");    
    private ComboBox<String> employeeSelect = new ComboBox<String>();
	
    private Alert infoBye = new Alert(AlertType.INFORMATION, "Have A Great Day!");
    private Alert errorSelect = new Alert(AlertType.ERROR, "You Must Select An Employee!");
    
    public LogInWindow(Stage stage){
    	//<------------------------------------------------window positioning------------------------------------------->
    	GridPane gPane = new GridPane();
    	HBox hb1 = new HBox();//employee combo box
		HBox hb2 = new HBox();//login button
		HBox hb3 = new HBox();//close window button
		
		Scene scene = new Scene(gPane);
				
		logIn = stage;
		logIn.setTitle("Login");
	    logIn.setWidth(300);
	    logIn.setHeight(300);
	    logIn.setScene(scene);
	    
	    hb1.getChildren().add(employeeSelect);
	    hb2.getChildren().add(btnLogin);
	    hb3.getChildren().add(btnClose);
	    
	    gPane.add(hb1,0,0);
	    gPane.add(hb2,0,1);
	    gPane.add(hb3,0,2);
	    
	    
	    gPane.setAlignment(Pos.TOP_CENTER);
	    hb1.setAlignment(Pos.CENTER);
	    hb2.setAlignment(Pos.CENTER);
	    hb3.setAlignment(Pos.CENTER);
	    final double insetValue = 25;//set vertical button spacing
	    hb1.setPadding(new Insets(insetValue,0,insetValue,0));
	    hb2.setPadding(new Insets(insetValue,0,insetValue,0));
	    hb3.setPadding(new Insets(insetValue,0,insetValue,0));
	    
//<-------------------------------------------button functionality----------------------------------------------->	    
	    
	    /**
	    * @purpose loads employee select combo box from servers and managers array lists in Restaurant class every time
	    *combo box is clicked
	    */
	    employeeSelect.setOnMouseClicked(e ->{
	    	employeeSelect.getItems().clear();
			
			for(Manager manager : restaurant.getManagers()) {
				employeeSelect.getItems().add(manager.getEmployeeId()+ " " + manager.getFirstName() + " (Manager)");
	    	}
			
			for(Server server : restaurant.getServers()) {
				employeeSelect.getItems().add(server.getEmployeeId()+ " " + server.getFirstName() + " (Server)");
	    	} 
	    	
	    });
	    /**
	     * @purpose pulls employee id from line of employeeSelect combo box that is selected
	     * then finds employee object by ID.  Depending on if the employee is an instance of 
	     * manager or server, the appropriate window opens.  The code also checks for the
	     * an open instance of the window to open.
	     */
	    btnLogin.setOnAction(e -> {    	
	    	
	    	if(!(employeeSelect.getValue()==null)){
		    	
	    		String line = employeeSelect.getValue();
				String[] employeeLine = line.split(" ");
		    	Employee emp = restaurant.getEmployeeById(Integer.parseInt(employeeLine[0]));		    	
		    	if(emp instanceof Manager) {		    		
		    		if(!managerSelectWin.isShowing()) {
		    			managerSelectWin.show(restaurant);	
		    		}
		    		else {
		    			managerSelectWin.toFront();
		    		}
		    	}
		    	
		    	if(emp instanceof Server) {
		    		if(!serverChecksWindow.isShowing()) {		    		
		    			serverChecksWindow.show((Server)emp,restaurant);
		    		}
		    		else {
		    			serverChecksWindow.toFront();
		    		}
		    	}
	    	}
	    	else {
	    		errorSelect.showAndWait();//<------------------Inform user to select employee
	    	}
	    	
	    	
	    });
	    
	    btnClose.setOnAction(e->{
	    	infoBye.showAndWait();//<----------------------------------------------Farewell message
	    	logIn.close();
	    });
	}

	public void show() {
		//loads employeeSelect combo box when window is opened
		for(Manager manager : restaurant.getManagers()) {
			employeeSelect.getItems().add(manager.getEmployeeId()+ " " + manager.getFirstName() + " (Manager)");
    	}
		
		for(Server server : restaurant.getServers()) {
			employeeSelect.getItems().add(server.getEmployeeId()+ " " + server.getFirstName() + " (Server)");
    	}
		
		logIn.show();		
	}
}