package edu.tridenttech.cpt237.lucas.view;
/**
 * @file: ManagerSelectWindow.java
 * @author: Ben Lucas
 * @purpose: Class creates a GUI for manager to select a choice of editing the employee list 
 * or editing the menu items list.  
 */
import edu.tridenttech.cpt237.lucas.model.Restaurant;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ManagerSelectWindow {

	private Stage managerChoice = new Stage();
	
	private static Restaurant restaurant;

	private EditEmployeesWindow edEmpWin = new EditEmployeesWindow();
	private EditMenuWindow edMenuWin = new EditMenuWindow();
	
	private Button btnEmp = new Button("Edit Employees");    
    private Button btnMenu = new Button("Edit Menu Items");
    private Button btnClose = new Button("Close");

	

    public ManagerSelectWindow() {
//<------------------------------------------window position-------------------------------------------->    	
    	GridPane gPane = new GridPane();
    	HBox hb1 = new HBox();//employee edit button
		HBox hb2 = new HBox();//menu edit button
		HBox hb3 = new HBox();//close window button
		
		Scene scene = new Scene(gPane);				
		
		managerChoice.setTitle("Login");
		managerChoice.setWidth(300);
		managerChoice.setHeight(300);
		managerChoice.setScene(scene);
	    
	    hb1.getChildren().add(btnEmp);
	    hb2.getChildren().add(btnMenu);
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
	    
//<-----------------------------------------button functionality------------------------------------------->    	
	    //shows EditEmployeesWindow GUI, closes ManagerSelectWindow GUI
	    btnEmp.setOnAction(e->{
	    	edEmpWin.show(restaurant);
	    	managerChoice.close();
	    });
	    
	    //shows EditMenuWindow GUI, closes ManagerSelectWindow tGUI
	    btnMenu.setOnAction(e->{
	    	edMenuWin.show(restaurant);
	    	managerChoice.close();
    	});	    
	    //closes ManagerSelect GUI
	    btnClose.setOnAction(e->{
	    	managerChoice.close();
	    });
    	
    }//<--------------------------------------end constructor
    
    
    public void show(Restaurant rest) {
    	restaurant = rest.getRestaurant();
    	managerChoice.show();			
	}
    
    public boolean isShowing() {
    	managerChoice.toFront();
		return false;
	}
	
	public void toFront() {
		managerChoice.toFront();
		
	}
}
