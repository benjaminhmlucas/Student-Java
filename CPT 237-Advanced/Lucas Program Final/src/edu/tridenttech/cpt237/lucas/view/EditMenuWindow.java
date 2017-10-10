package edu.tridenttech.cpt237.lucas.view;
/**
 * @file: EditMenuWindow.java
 * @author: Ben Lucas
 * @purpose: Class creates a GUI for manager to add or remove menu items that
 * are available to the server for checks.  You can add new items with a name 
 * and a double value for cost.  GUI will not let items with a repeated name 
 * be created.  New ID for items is controlled by GUI similarly to employee editor 
 * window.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collections;
import edu.tridenttech.cpt237.lucas.model.MenuItem;
import edu.tridenttech.cpt237.lucas.model.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditMenuWindow {
	
	private Stage editMenu = new Stage();
	private ManagerSelectWindow manSelectWin;
	
	private static Restaurant restaurant;//assigned line 175
	
	private Button btnAddItem = new Button("Add Menu Item");//button functionality added line 115
    private Button btnRemoveItem = new Button("Remove Selected Menu Item");//button functionality added line 139
    private Button btnClose = new Button("Close Menu Editor & Save Changes");//button functionality added line 157 
	
    private TextField idField = new TextField();//edit disabled line 99
    private TextField itemName = new TextField();
    private TextField itemCost = new TextField();
          
    private ListView<String> menuItemsView = new ListView<>();
	ObservableList<String> menuItemsObsv = FXCollections.observableArrayList();	
	
	private Alert changesSaved = new Alert(AlertType.INFORMATION, "All Changes To Menu Have Been Saved");
	private Alert errorNameNull = new Alert(AlertType.ERROR, "You Must An Item Name And Cost(Format: 0.00)!");
	private Alert errorNameSame = new Alert(AlertType.ERROR, "This Menu Item Was Already Created!");
	private Alert errorCost = new Alert(AlertType.ERROR, "You Must Enter A Number For Cost(Format: 0.00)!");
	private Alert errorDelete = new Alert(AlertType.ERROR, "Please Select A Menu Item To Delete!");
    private Alert errorSaveToFile = new Alert(AlertType.ERROR, "Information Not Saved!");

	public EditMenuWindow() {
//<-------------------------------------------------Window Positioning---------------------------------------->		
		GridPane outerPane = new GridPane();
		GridPane editMenuBox = new GridPane();//edit menu item fields, buttons
		HBox closeBox = new HBox();//close window button
		
		menuItemsView.setItems(menuItemsObsv);
		
		VBox listBox = new VBox();
		
		Scene scene = new Scene(outerPane);
		outerPane.setPadding(new Insets(15,15,15,15));
		outerPane.add(listBox,0,0,1,2);
		outerPane.add(editMenuBox,1,0);
		outerPane.add(closeBox,0,2,2,1);

		
		editMenu.setTitle("Edit Menu Items");
		editMenu.setWidth(525);
		editMenu.setHeight(475);
		editMenu.setScene(scene);
		
		//menu item list 
		listBox.getChildren().addAll(new Label("Current MenuItems:"),menuItemsView);
		listBox.setMinWidth(250);
		
		//add items		
		editMenuBox.setPadding(new Insets(15,15,15,15));
		editMenuBox.add(new Label("Add New Menu Item:"),0,0,2,1);
		editMenuBox.add(new Label("New Menu Item Id:"),0,1,2,1);
		editMenuBox.add(idField,1,1);
		editMenuBox.add(new Label("New Item Name:"),0,2,2,1);
		editMenuBox.add(itemName,0,3,2,1);
		editMenuBox.add(new Label("New Item Cost:"),0,4,2,1);
		editMenuBox.add(itemCost,0,5,2,1);
		editMenuBox.add(btnAddItem,0,6,2,1);
		GridPane.setMargin(btnAddItem, new Insets(0, 0, 0, 0));
		idField.setMaxWidth(75);
		idField.setMinWidth(75);
		idField.setEditable(false);
		GridPane.setMargin(idField, new Insets(0, 0, 0, 135));
		
		//remove items
		GridPane.setMargin(btnRemoveItem, new Insets(0, 0, 0, 0));
		editMenuBox.add(btnRemoveItem,0,7,2,1);
		
		
		//close window
		closeBox.setPadding(new Insets(15,15,15,15));
		closeBox.getChildren().add(btnClose);
		closeBox.setAlignment(Pos.CENTER);
		
		//<--------------------------------------set button functionality-------------------------------------------------------->
		
		/**
		 * @purpose add a new menu item to menu list.  If name or cost field are empty, add fails and a message is displayed.  If 
		 * cost field is not in double form, add fails and a message is displayed.  If a menu item name matches the new item's name
		 * add fails and a message is displayed.  ID is auto incremented up from highest value in the used menu item array list.
		 */
		btnAddItem.setOnAction(e->{
			if (!(itemName.getText()==null)&&!(itemCost.getText()==null)) {
				for(MenuItem item: restaurant.getMenuItems()) {
					if(item.getItemName().equals(itemName.getText())) {//checks for repeated menu items names
					errorNameSame.showAndWait();//<-------------------------------------------------Menu item already created message!
					return;
					}
				}
				try {
					restaurant.addNewMenuItem(itemName.getText(),Double.parseDouble(itemCost.getText()),Integer.parseInt(idField.getText()));
					int idTracker = Integer.parseInt(idField.getText())+1;						
					idField.setText(Integer.toString(idTracker));					
					resetMenuList();
				} catch (NumberFormatException e1) {
					errorCost.showAndWait();//<---------------------------------------------------------------Enter both fields message!

				}
			}
			else {
				errorNameNull.showAndWait();//<---------------------------------------------------------------Enter both fields message!
			}
		});
			
		/**
		 * @purpose removes the selected item from the menu array list.  If nothing is selected then a message
		 * is displayed to the user to select an employee.  Reads the selected line and finds the menu item using 
		 * the extrapolated ID.  Removes item from menu item list. Uses resetIdTracker() to set new ID to one higher than
		 * highest value in the list
		 */
		btnRemoveItem.setOnAction(e->{			
  			if (menuItemsView.getSelectionModel().getSelectedIndex() >= 0)//check for a selection in view
  			{
  				String line = menuItemsView.getSelectionModel().getSelectedItem();
  				String[] itemLine = line.split(" ");
  				int itemId = Integer.parseInt(itemLine[0]);//assign item id from view to a variable
  				MenuItem itemToDelete = restaurant.getItemById(itemId);//assign object to delete
  				restaurant.getMenuItems().remove(itemToDelete);//remove object from list
  				menuItemsObsv.remove(menuItemsView.getSelectionModel().getSelectedIndex());// remove object from view 				
  				restaurant.getUsedItemIds().remove((Integer)(itemToDelete.getItemID()));//remove item id
  				resetIdTracker();
  			}
  			else {
  				errorDelete.showAndWait();//<---------------------------------------------------you must select an item message
  			}
		});
		
		/**
		 * @purpose closes window. On close the menu list is saved to the menuItems.csv file 
		 * and a message notifying the user that changes have been displayed is shown.  ManagerSelect GUI is 
		 * reopened.
		 */
		btnClose.setOnAction(e->{
			try (PrintWriter outputMenu = new PrintWriter(new File("menuItems.csv"));)
	    	{
				for(MenuItem item: restaurant.getMenuItems()) {
					outputMenu.println(item.getItemName()+","+item.getItemCost()+","+item.getItemID());//save menu items to file using this format 
				}
	    	} catch (FileNotFoundException e1) {
				errorSaveToFile.showAndWait();				
			}
			changesSaved.showAndWait();
			manSelectWin.show(restaurant);
			editMenu.close();
		});
		
	}
	
	public void show(Restaurant rest) {

		restaurant = rest.getRestaurant();//pass in restaurant
		manSelectWin = new ManagerSelectWindow();		
		resetMenuList();
		editMenu.show();
		
	}
	
	/**
	 * @purpose resets employeeList when window is loaded and when an employee is added
	 */
	public void resetMenuList() {
		resetIdTracker();
		NumberFormat formatter = new DecimalFormat("$#0.00");//formats price output
		menuItemsView.getItems().clear();//clears old menu items ListView
    	
		//loops through menuItem ArrayList to fill menu item ObservableList
		for(MenuItem item: restaurant.getMenuItems()) {
    		menuItemsObsv.add(item.getItemID()+" "+item.getItemName()+" "+ formatter.format(item.getItemCost()));
    	}    	
	}
	/**
	 *@purpose resets new menu item ID text field when a new item is added or an item is removed.
	 *this will make older ID's inaccessible but the main point here is to not repeat ID's not have every
	 *single number used 
	 */
	public void resetIdTracker() {
		Collections.sort(restaurant.getUsedItemIds());//ensures id's are sorted
		int index = restaurant.getUsedItemIds().size();//last index of list
		Integer newId = restaurant.getUsedItemIds().get(index-1);//value of last item
		idField.setText((++newId).toString());//ID for next new menu item is assigned and set to text field
	}
}
