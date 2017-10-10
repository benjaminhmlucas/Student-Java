package edu.tridenttech.cpt237.lucas.view;
/**
 * @file: EditCheckWindow.java
 * @author: Ben Lucas
 * @purpose: Class creates a GUI for server to add or remove menu items to an already 
 * opened check or a newly opened check.  Menu item add buttons are created by iterating
 * through each menu item and creating a button for them while also assigning functionality 
 * at the same time.  Since all buttons will only add an item then it made this iteration
 * assignment possible.  Any item added to the menu items array list from the manager edit menu window 
 * will automatically appear in the edit check window.  Window displays a list view of items 
 * on the current check, add item buttons(clones menu items into check), remove items button, 
 * a close check button that adds the check total to cash owed to the restaurant, removes the check from
 * opened checks and adds it to closed checks.  check total text field is uneditable
 */
import java.text.DecimalFormat;
import java.text.NumberFormat;
import edu.tridenttech.cpt237.lucas.model.Check;
import edu.tridenttech.cpt237.lucas.model.MenuItem;
import edu.tridenttech.cpt237.lucas.model.Restaurant;
import edu.tridenttech.cpt237.lucas.model.Server;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditCheckWindow {
	
	private ServerCheckSelectWindow serverCheckSelectWin;
		
	private Stage editCheckWin = new Stage();
	
	private static Restaurant restaurant;//assigned line 189
	private Server currentServer;//<------used for tracking employee activity (assigned line 190)
	private Check check;//<------currently viewed check object, assigned line 191
	
	private ListView<String> fullMenuListView  = new ListView<>();
	private ListView<String> checkItemListView  = new ListView<>();
	private ObservableList<String> checkItemListObsv = FXCollections.observableArrayList();
	private ObservableList<String> fullMenuListObsv = FXCollections.observableArrayList();
	
	private FlowPane buttonPane;//Menu Items Pane
	
	private Button btnDeleteItem = new Button("Delete Selected Item");//assigned line 139
    private Button btnCloseCheck = new Button("Close Check");//assigned line 153
    private Button btnCloseWindow = new Button("Close Window");//assigned line 163
	
	private Alert errorRemoveItem = new Alert(AlertType.ERROR, "You Must Select An Item From The Check Item Box!");
	
	private NumberFormat formatter = new DecimalFormat("$#0.00");//formats price output	
	private TextField checkTotal = new TextField();//set to not editable line 103
	
	public EditCheckWindow(Restaurant rest) {
//<---------------------------------------------------Window Positioning------------------------------------------------>
		checkItemListView.setItems(checkItemListObsv);
		fullMenuListView.setItems(fullMenuListObsv);
		
		GridPane outerPane = new GridPane();
		VBox checkItemListBox = new VBox();//check item list view
		
		buttonPane = new FlowPane();
		
		HBox deleteBtnBox = new HBox();
		HBox closeCheckBox = new HBox();//close window button	
		HBox closeWindowBox = new HBox();//close window button	

		Scene scene = new Scene(outerPane);
		
		outerPane.setPadding(new Insets(15,15,15,15));
		outerPane.add(checkItemListBox,0,0,1,2);
		outerPane.add(buttonPane,1,0);
		outerPane.add(deleteBtnBox,0,2);				
		outerPane.add(closeCheckBox, 1,2,3,1);
		outerPane.add(closeWindowBox,0,4,3,1);
		outerPane.add(new Label("<<<Current Check Total"),1,3,2,1);
		outerPane.add(checkTotal,0,3);
		
		editCheckWin.setTitle("Edit Check");
		editCheckWin.setWidth(550);
		editCheckWin.setHeight(600);
		editCheckWin.setScene(scene);
		
		//show list views 
		checkItemListBox.getChildren().addAll(new Label("Current CheckItems:"),checkItemListView);
		
		btnDeleteItem.setMinWidth(250);
		btnCloseCheck.setMinWidth(250);
		btnCloseWindow.setMinWidth(500);
		buttonPane.setPrefWidth(250);
		checkTotal.setAlignment(Pos.CENTER_RIGHT);
		checkTotal.setEditable(false);
		
		deleteBtnBox.getChildren().add(btnDeleteItem);
		closeCheckBox.getChildren().add(btnCloseCheck);
		closeWindowBox.getChildren().add(btnCloseWindow);
		
		GridPane.setHalignment(checkTotal, HPos.CENTER);
		GridPane.setMargin(buttonPane, new Insets(35,0,15,15));		

		//<----------------------------------------------button functions---------------------------------------------------->
		
		/**
		 * @purpose reads menu items and creates add buttons for any item in the list, functionality is then assigned
		 * to make the item that the button represents get cloned into the check, the item price is then added to the 
		 * check total and the list view is reset 
		 */
		for(MenuItem item: rest.getMenuItems()) {
			Button button = new Button(item.getItemName());
			buttonPane.getChildren().add(button);
			button.setOnAction(e->{				
					
				for(MenuItem itemInner: rest.getMenuItems()) 
				{
					if(itemInner.getItemName().equals(button.getText())) {
						int id = itemInner.getItemID();
						try {
							check.getCheckItemList().add((MenuItem)restaurant.getItemById(id).clone());
						} catch (CloneNotSupportedException e1) {
							System.out.println("**************");
							System.out.println("*System Error*");
							System.out.println("**************");
						}
						double price = itemInner.getItemCost();
						check.addToCheckBalance(price);
						resetCheckListView();
					}					
				}
				
			});
		}
		/**
		 * @purpose selected list item is fed into an array and the price is extrapolated.  The list item is removed
		 * from the check list and the list view.
		 */
		btnDeleteItem.setOnAction(e->{
			if(checkItemListView.getSelectionModel().getSelectedIndex() >= 0) {				
				check.getCheckItemList().remove(checkItemListView.getSelectionModel().getSelectedIndex());
				String[] itemLine = checkItemListView.getSelectionModel().getSelectedItem().split(" ");
				String p = itemLine[0].replace("$", "");
				double price = Double.parseDouble(p);
				check.subtractFromCheckBalance(price);
				resetCheckListView();
			} else {
				errorRemoveItem.showAndWait();
			}
			
		});
		/**
		 * @purpose removes check from opened check list, adds it to closed checks lists and adds
		 * check total to server's cash owed to the restaurant field in the check select window.
		 */
		btnCloseCheck.setOnAction(e->{		
			restaurant.getAllOpenedChecks().remove(check);					
			restaurant.getAllClosedChecks().add(check);
			currentServer.addCashOwedToRestaurant(check.getCheckBalance());
			serverCheckSelectWin.show(currentServer, restaurant);
			editCheckWin.close();
		});
		//closes window without closing check
		btnCloseWindow.setOnAction(e->{			
			if(!restaurant.getAllOpenedChecks().contains(restaurant.getCheckById(check.getCheckId()))) {
				restaurant.getAllOpenedChecks().add(check);
			}			
			serverCheckSelectWin.show(currentServer, restaurant);
			editCheckWin.close();
		});
	}
	/**
	 * @purpose used in ServerCheckSelectWindow to open an already created check in the 
	 * EditCheckWindow.  
	 * @param emp employee object
	 * @param rest restaurant object
	 * @param checkId check to edit ID extrapolated from selected check in list view
	 */
	public void show(Server emp, Restaurant rest, int checkId) {
		restaurant = rest.getRestaurant();
		currentServer = emp;		
		check = restaurant.getCheckById(checkId);
		serverCheckSelectWin = new ServerCheckSelectWindow();
		fullMenuListObsv.clear();
		for(MenuItem item : restaurant.getMenuItems()) {
			fullMenuListObsv.add(item.getItemID()+" "+formatter.format(item.getItemCost())+"  "+item.getItemName());
		}				
		checkTotal.setEditable(false);
		resetCheckListView();
		editCheckWin.show();		
	}
	/**
	 * @purpose open a new check in the check edit window.  Assigns an autoincremented ID to the check 
	 * @param emp employee using check
	 * @param rest singleton restaurant object
	 */
	public void show(Server emp, Restaurant rest) {
		restaurant = rest.getRestaurant();
		currentServer = emp;		
		check = new Check(currentServer.getEmployeeId());
		serverCheckSelectWin = new ServerCheckSelectWindow();		
		fullMenuListObsv.clear();
		for(MenuItem item : rest.getMenuItems()) {
			fullMenuListObsv.add(item.getItemID()+" "+formatter.format(item.getItemCost())+"  "+item.getItemName());
		}		
		resetCheckListView();
		editCheckWin.show();		
	}
	/**
	 * @purpose resets check list view and ensures that if an item was selected then either the same item
	 * or an the item one position before it(in case of deletion) is selected. This assists with having to 
	 * delete multiple objects from the check.
	 */
	public void resetCheckListView() {
		checkTotal.setText(formatter.format(check.getCheckBalance()));
		int index = checkItemListView.getSelectionModel().getSelectedIndex();
		checkItemListObsv.clear();
		for(MenuItem item : check.getCheckItemList()) {
			checkItemListObsv.add(formatter.format(item.getItemCost())+"  "+item.getItemName());
		}
		if(index < check.getCheckItemList().size())
		{
			checkItemListView.getSelectionModel().select(index);
		}
		else 
		{
			checkItemListView.getSelectionModel().select(index-1);
		}
		
	}
}
