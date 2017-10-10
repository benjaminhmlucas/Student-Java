package edu.tridenttech.cpt237.lucas.view;
/**
 * @file: EditEmployeesWindow.java
 * @author: Ben Lucas
 * @purpose: Class creates a GUI for manager to add or remove employees. Contains a field
 * for first name, last name, a radio box for employee type selection(manger/server), and 
 * a textfield that tells you the id for the new employee. TextField is not editable.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;

import edu.tridenttech.cpt237.lucas.model.Employee;
import edu.tridenttech.cpt237.lucas.model.Manager;
import edu.tridenttech.cpt237.lucas.model.Restaurant;
import edu.tridenttech.cpt237.lucas.model.Server;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditEmployeesWindow {

	private Stage editEmp = new Stage();
	private ManagerSelectWindow manWin;
	private static Restaurant restaurant;//assigned line 213
	
	private Button btnAddEmp = new Button("Add Employee");//functionality assigned line 123    
    private Button btnRemoveEmp = new Button("Remove Selected Employee");//functionality assigned line 166
    private Button btnClose = new Button("Close Employee Editor & Save Changes");//functionality assigned line 192
	
    private RadioButton manRadio = new RadioButton("Manager");
    private RadioButton servRadio = new RadioButton("Server");
    private	ToggleGroup tGroup = new ToggleGroup();    
    
    private TextField idField = new TextField();//not editable set line 108
    private TextField fName = new TextField();
    private TextField lName = new TextField();
          
    private ListView<String> employeeListView = new ListView<>();
	private ObservableList<String> employeeListObsv = FXCollections.observableArrayList();
	
	private Alert changesSaved = new Alert(AlertType.INFORMATION, "All Changes To Employees Have Been Saved");
	private Alert errorTypeSelect = new Alert(AlertType.ERROR, "You Must Select Employee Type! Employee Not Added!");
	private Alert errorNameNull = new Alert(AlertType.ERROR, "You Must Employee First Name And Last Name!");
	private Alert errorNameSameMan = new Alert(AlertType.ERROR, "This Employee Already Has A Management ID!");
	private Alert errorNameSameServ = new Alert(AlertType.ERROR, "This Employee Already Has A Server ID!");
	private Alert errorEmpDelete = new Alert(AlertType.ERROR, "Please Select Employee To Delete!");
    private Alert errorSaveToFile = new Alert(AlertType.ERROR, "Information Not Saved!");
	
	public EditEmployeesWindow() {
//<---------------------------------------------Window Positioning----------------------------------------------------->		
		GridPane outerPane = new GridPane();
		GridPane addEmpBox = new GridPane();//add employee 
		HBox closeBox = new HBox();//close window button
		HBox radioBox = new HBox();//radio button choice
		VBox listBox = new VBox();//ListView and label

		employeeListView.setItems(employeeListObsv);
				
		Scene scene = new Scene(outerPane);
		outerPane.setPadding(new Insets(15,15,15,15));
		outerPane.add(listBox,0,0,1,2);
		outerPane.add(addEmpBox,1,0);
		outerPane.add(closeBox,0,2,2,1);
		
		
		editEmp.setTitle("Edit Employees");
		editEmp.setWidth(600);
		editEmp.setHeight(475);
		editEmp.setScene(scene);
		
		//employee list 
		listBox.getChildren().addAll(new Label("Current Employees:"),employeeListView);
		
		//add employees
		manRadio.setToggleGroup(tGroup);
		manRadio.setPadding(new Insets(5,5,5,5));
		servRadio.setToggleGroup(tGroup);
	    servRadio.setPadding(new Insets(5,5,5,50));
		radioBox.getChildren().addAll(manRadio,servRadio);
		addEmpBox.setPadding(new Insets(75,15,15,15));
		addEmpBox.add(new Label("Add Employee:"),0,0,2,1);
		addEmpBox.add(new Label("New Employee Id:"),0,1,2,1);
		addEmpBox.add(idField,1,1,2,1);
		addEmpBox.add(new Label("First Name:"),0,2);
		addEmpBox.add(fName,1,2);
		addEmpBox.add(new Label("Last Name:"),0,3);
		addEmpBox.add(lName,1,3);
		addEmpBox.add(radioBox,0,4,2,1);
		addEmpBox.add(servRadio,1,4);
		addEmpBox.add(btnAddEmp,0,5,2,1);
		idField.setMaxWidth(100);
		idField.setEditable(false);
		GridPane.setMargin(idField, new Insets(0, 0, 0, 87));

		//remove employees
		GridPane.setMargin(btnRemoveEmp, new Insets(55, 0, 0, 0));
		addEmpBox.add(btnRemoveEmp,0,5,2,1);
		
		//close window
		closeBox.setPadding(new Insets(15,15,15,15));
		closeBox.getChildren().add(btnClose);
		closeBox.setAlignment(Pos.CENTER);
//v--------------------------------------------set button functionality---------------------------------------------------------------------------v
		
		/**
		 * @purpose checks for radio button selection of employee type, depending on selection
		 * either a new manager created or a new server object is created and then added to 
		 * corresponding server or manager array list in restaurant singleton.  If type is not selected, 
		 * first name and last name match that of an employee of the same type, or either first or last name 
		 * fields are empty then add fails and a message is displayed.  Add allows an employee to be
		 * both a manager and a server in case they need dual functionality.
		 */
		btnAddEmp.setOnAction(e->{
			if (manRadio.isSelected()||servRadio.isSelected()) {//check which if either radio button is selected				
				if (manRadio.isSelected()) {//check if manRadio is selected
					if (!(fName.getText()==null)&&!(lName.getText()==null)) {//check for values in name text fields
						for(Manager manager: restaurant.getManagers()) {//check in manager list for employee name duplication
							if(manager.getFirstName().equals(fName.getText())&& manager.getLastName().equals(lName.getText()) ) {
							errorNameSameMan.showAndWait();//<---------------------------employee already created message!
							return;
							}
						}
						restaurant.addNewManager(fName.getText(),lName.getText(),Integer.parseInt(idField.getText()));//add new manager
						resetIdTracker();
						resetEmployeeList();
					}
					else {
						errorNameNull.showAndWait();//<-------------------------------------Enter both names message!
					}
				}
				else {
					if (!(fName.getText()==null)&&!(lName.getText()==null)) {//check for values in name fields
						for(Server server: restaurant.getServers()) {//check for repeat names in server list
							if(server.getFirstName().equals(fName.getText())&& server.getLastName().equals(lName.getText()) ) {
							errorNameSameServ.showAndWait();//<------------------------employee already created message!
							return;
							}
						}
						restaurant.addNewServer(fName.getText(),lName.getText(),Integer.parseInt(idField.getText()));
						resetIdTracker();
						resetEmployeeList();
					}
					else {
						errorNameNull.showAndWait();//<----------------------------------Enter both names message!
					}
				}	
			}
			else {
				errorTypeSelect.showAndWait();//<----------------------------------------Select employee type message!
			}
		});
		
		/**
		 * @purpose employee line that is selected in list view is fed into an array, 
		 * split up and the ID is read.  This ID is used to find the employee object 
		 * in its corresponding array list and remove it. If no employee is selected
		 * an error message is displayed and remove fails. Uses resetIdTracker() to 
		 * set new ID to one higher than highest value in the list
		 */
		btnRemoveEmp.setOnAction(e->{
  			if (employeeListView.getSelectionModel().getSelectedIndex() >= 0)
  			{
  				String line = employeeListView.getSelectionModel().getSelectedItem();//read selected line
  				String[] employeeLine = line.split(" ");//split line into an array
  				int empId = Integer.parseInt(employeeLine[0]);//assign employee id to a variable
  				Employee empToDelete = restaurant.getEmployeeById(empId);//retrieve employee object
  				if(empToDelete instanceof Manager) {
  					restaurant.getManagers().remove(empToDelete);//remove manager from manger list
  					employeeListObsv.remove(employeeListView.getSelectionModel().getSelectedIndex());//remove employee from observer list
  					restaurant.getUsedEmpIds().remove((Integer)(empToDelete.getEmployeeId()));//remove employee ID from used employee ID list
  					resetIdTracker();
  				}
  				if(empToDelete instanceof Server) {
  					restaurant.getServers().remove(empToDelete);//remove server from server list
  					employeeListObsv.remove(employeeListView.getSelectionModel().getSelectedIndex());//remove employee from observer list
  					restaurant.getUsedEmpIds().remove((Integer)(empToDelete.getEmployeeId()));//remove employee ID from used employee ID list
  					resetIdTracker();
  				}
  			}
  			else {
  				errorEmpDelete.showAndWait();
  			}
		});
		
		/**
		 * @purpose on close, employees.csv file is updated with changes and an alert displays 
		 * a changes saved message. ManagerSelect GUI is reopened.
		 */
		btnClose.setOnAction(e->{
			try (PrintWriter outputEmployee = new PrintWriter(new File("employees.csv"));)
	    	{				
				for(Employee emp: restaurant.getManagers()) {//save managers to employee list file
					outputEmployee.println("M,"+emp.getFirstName()+","+emp.getLastName()+","+emp.getEmployeeId());
				}
				for(Employee emp: restaurant.getServers()) {//save servers to employee list file
					outputEmployee.println("S,"+emp.getFirstName()+","+emp.getLastName()+","+emp.getEmployeeId());
				}
	    	} catch (FileNotFoundException e1) {
				errorSaveToFile.showAndWait();//<----------------------save error message
			}
			changesSaved.showAndWait();//<-----------------inform user of save message
			manWin.show(restaurant);
			editEmp.close();
		});
		
	}//<---------------------------------------------------------end of constructor------------------------------------------------------------>
	
	public void show(Restaurant rest) {

		restaurant = rest.getRestaurant();//pass in restaurant
		manWin = new ManagerSelectWindow();		
		resetEmployeeList();
    	editEmp.show();
		
	}
	
	/**
	 * @purpose resets employeeListView when window is loaded and when an employee is added
	 */
	public void resetEmployeeList() {		
		resetIdTracker();
		employeeListView.getItems().clear();    		for(Employee employee: restaurant.getManagers()) {
    		employeeListObsv.add(employee.getEmployeeId()+" "+employee.getFirstName()+" "+employee.getLastName()+" M");
    	}
    	for(Employee employee: restaurant.getServers()) {
    		employeeListObsv.add(employee.getEmployeeId()+" "+employee.getFirstName()+" "+employee.getLastName()+" S");
		}
	}
	/**
	 *@purpose resets new employee ID text field when a new employee is added or an employee is removed.
	 *this will make older ID's inaccessible but the main point here is to not repeat ID's not have every
	 *single number used 
	 */
	public void resetIdTracker() {
		Collections.sort(restaurant.getUsedEmpIds());//sort used ID's
		int index = restaurant.getUsedEmpIds().size();//find last index in array list
		Integer newId = restaurant.getUsedEmpIds().get(index-1);//find value of last id
		idField.setText((++newId).toString());//set idfield to one number higher than last id
	}
}
