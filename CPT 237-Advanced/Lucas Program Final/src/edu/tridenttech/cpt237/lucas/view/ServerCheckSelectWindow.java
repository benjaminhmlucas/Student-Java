package edu.tridenttech.cpt237.lucas.view;
/**
 * @file: ServerCheckSelectWindow.java
 * @author: Ben Lucas
 * @purpose: Class creates a GUI for a server to select a specific check to edit, open a new check,
 * or clear closed checks to then turn in cash to manager. There are two views to toggle between.
 * the first is all of the servers opened checks and the other is all of the servers closed checks.
 * The second view allows the server to clear closed checks. edit check button checks for 
 * an item to be selected in opened checks list view.  closed checks cannot be edited. 
 */
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import edu.tridenttech.cpt237.lucas.model.Check;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ServerCheckSelectWindow {
	
	private Stage servSelectWin = new Stage();
	
	private static int loadCountOpnd = 0;//<----uncomment for fake check loader & lines 230-244
	private static int loadCountClsd = 0;//<----uncomment for fake check loader $ lines 258-271
	
	private static Restaurant restaurant;//assigned line 215
	private Server currentServer;//<------can be used for tracking employee activity line 216
	
	private EditCheckWindow edCheckWin;
	private Observer cashOwedObsv = new CashOwedObserver();//tracks cash changes, set line 221

	private Button btnEditCheck = new Button("Edit Selected Check"); //opens an opened check, checks for selected check in openedChecks ListView (action set line 136)   
    private Button btnOpenNewCheck = new Button("Open New Check");//creates new check object and adds to opened check list(action set line 159)
    private Button btnServerChkOut = new Button("Server Checkout");//only appears in closed check view, removes all checks from closed checks list by server ID (set line 167)
    private Button btnClose = new Button("Close Check Selector");//closes check selector window
	
    private Button showOpenedChecks = new Button("Show Opened Checks");
	private Button showClosedChecks = new Button("Show Closed Checks");
    	    
    private ArrayList<Check> openedChecksCurrentServer;//<-----loaded line 237
    private ArrayList<Check> closedChecksCurrentServer;//<----loaded line 265
    
    private ListView<String> openedChecksView = new ListView<>();// load line 234
    private ListView<String> closedChecksView = new ListView<>();//loaded line 262
    
	private ObservableList<String> openedChecksObsv = FXCollections.observableArrayList();
    private ObservableList<String> closedChecksObsv = FXCollections.observableArrayList();
	
	private Alert errorSelectCheck = new Alert(AlertType.ERROR, "You Must Select A Check To Edit!");//line 150
	private Alert errorClosedSelected = new Alert(AlertType.ERROR, "You Cannot Edit A Closed Check!");//line 154	
	private Alert errorCheckOut = new Alert(AlertType.ERROR, "You Must Close All Opened Checks Before Checking Out!");//line 182	
	private Alert serverCheckoutInfo;//line	169
	
	//used to display current cash owed to restaurant for current user
	private NumberFormat formatter = new DecimalFormat("$#0.00");//formats price output	
	private TextField cashOwed = new TextField();//totals cash owed to restaurant by server, cannot be edited

	
	//window constructor
	ServerCheckSelectWindow(){
//v---------------------------------------------Window Positioning------------------------------------------------------------------v
		openedChecksView.setItems(openedChecksObsv);//set observer list to view list
		closedChecksView.setItems(closedChecksObsv);//set observer list to view list

		GridPane outerPane = new GridPane();
		GridPane checkOptionsBox = new GridPane();//
		GridPane cashOwedBox = new GridPane();
		
		HBox toggleBox = new HBox();//switch views buttons
		HBox closeBox = new HBox();//close window button		
		
		VBox listBox = new VBox();//check list view
		
		Scene scene = new Scene(outerPane);
		
		outerPane.setPadding(new Insets(15,15,15,15));
		outerPane.add(listBox,0,0,1,2);
		outerPane.add(toggleBox,0,2);
		outerPane.add(checkOptionsBox,1,0,2,1);		
		outerPane.add(cashOwedBox,1,1,2,1);		
		outerPane.add(closeBox,0,4,2,1);
		
		servSelectWin.setTitle("Select Check To Edit");
		servSelectWin.setWidth(600);
		servSelectWin.setHeight(475);
		servSelectWin.setScene(scene);
		
		//show checks list 
		listBox.getChildren().addAll(new Label("Current Open Checks:"),openedChecksView);
		
		//buttons to open new check or edit existing check		
		checkOptionsBox.setPadding(new Insets(15,15,15,15));		
		final int inset = 20;//<-----------------------------sets vertical button spacing
		checkOptionsBox.add(btnEditCheck,0,2);		
		GridPane.setMargin(btnEditCheck, new Insets(inset,0,inset,15));
		checkOptionsBox.add(btnOpenNewCheck,0,4);
		GridPane.setMargin(btnOpenNewCheck, new Insets(inset,0,inset,15));

		//remove items
		checkOptionsBox.add(btnServerChkOut,0,5,2,1);
		GridPane.setMargin(btnServerChkOut, new Insets(inset,0,inset,15));

		//cash owed view
		cashOwedBox.add(new Label("Cash Owed To Restaurant"),0,0);
		cashOwedBox.add(cashOwed,0,1);
		cashOwedBox.setPadding(new Insets(15,15,15,15));
		
		//toggle check views
		toggleBox.getChildren().addAll(showOpenedChecks,showClosedChecks);
		
		//close window
		closeBox.setPadding(new Insets(15,15,15,15));
		closeBox.getChildren().add(btnClose);
		closeBox.setAlignment(Pos.CENTER);
		
		//v------------------------------Button functionality----------------------------------------------v
		
		/**
		 * @purpose checks for an item to be selected in opened checks, if not
		 * will alert user to select a check.
		 */
		btnEditCheck.setOnAction(e->{
			if (!openedChecksView.isDisabled()) {//check for view to be disabled
				if(openedChecksView.getSelectionModel().getSelectedIndex() >= 0) {	//check for a selection in opened checks view									
					String[] itemLine = openedChecksView.getSelectionModel().getSelectedItem().split(" ");
					int checkId;
					try {
						checkId = Integer.parseInt(itemLine[2]);//ID for check
						edCheckWin.show(currentServer,restaurant, checkId);
						servSelectWin.close();
					} catch (NumberFormatException e1) {
						errorSelectCheck.showAndWait();
					}
					
				} else {
					errorSelectCheck.showAndWait();//<--------------------select a check message
				}
			}
			if (!closedChecksView.isDisabled()) {
				errorClosedSelected.showAndWait();//<-------------cannot edit a close check message
			}
		});
		
		//opens a new check, assigns an auto incremented ID from Check class 
		btnOpenNewCheck.setOnAction(e->{
			
			edCheckWin.show(currentServer, restaurant);
			servSelectWin.close();
			
		});
		
		/**
		 * @purpose checks for empty opened checks list, if not opened displays an alert message to
		 * close all checks.  if opened checks array list is empty it will display a message to turn in 
		 * cash owed amount to manager, clear all checks that match the server ID from the closed checks 
		 * list, reduce the amount of cash owed to zero, and close window. 
		 */
		btnServerChkOut.setOnAction(e->{
			if(openedChecksCurrentServer.size()==0||openedChecksCurrentServer == null) {//check for opened checks 
			serverCheckoutInfo = new Alert(AlertType.INFORMATION, "Please Turn In "+ formatter.format(currentServer.getCashOwedToRestaurant()) +" To Manager");
			serverCheckoutInfo.showAndWait();//<--------------------------------------------------display amount of cash owed to restaurant/manger
			currentServer.addCashOwedToRestaurant(-(currentServer.getCashOwedToRestaurant()));//reduce cash owed to 0.00		
			for(Check check: closedChecksCurrentServer) {
				if(check.getServerId()==currentServer.getEmployeeId()) {
					 restaurant.getAllClosedChecks().remove(check);//remove all closed checks for server
				}
			}
			resetClosedView();//reset closed checks view
						
			servSelectWin.close();
			} else {
				errorCheckOut.showAndWait();//<--------------------------must close all opened checks message
			}
			
		});
		
		//show opened checks in view window
		showOpenedChecks.setOnAction(e->{
			btnServerChkOut.setVisible(false);
			openedChecksView.setDisable(false);
			closedChecksView.setDisable(true);
			listBox.getChildren().clear();
			listBox.getChildren().addAll(new Label("Current Opened Checks:"),openedChecksView);
			resetOpenedView();
		});
		
		//show closed Checks in view window
		showClosedChecks.setOnAction(e->{
			btnServerChkOut.setVisible(true);
			closedChecksView.setDisable(false);
			openedChecksView.setDisable(true);
			listBox.getChildren().clear();
			listBox.getChildren().addAll(new Label("Current Closed Checks:"),closedChecksView);
			resetClosedView();
		});
				
		//close window button
		btnClose.setOnAction(e->{
			servSelectWin.close();
		});
	}//<---------------------------------------------------------------End Constructor

	public TextField getCashOwed() {
		return cashOwed;		
	}
	
	public void show(Server emp, Restaurant rest) {		
		restaurant = rest.getRestaurant();//pass in restaurant		
		currentServer = emp;//pass in employee using system for activity tracking and check retrieval
		edCheckWin = new EditCheckWindow(restaurant);
		resetOpenedView();
		resetClosedView();
		cashOwed.setText(formatter.format(currentServer.getCashOwedToRestaurant()));
		cashOwed.setEditable(false);
		currentServer.addObserver(cashOwedObsv);//assign observer to cashOwed
		openedChecksView.setDisable(false);
		closedChecksView.setDisable(true);
		showOpenedChecks.fire();//ensures that opened check view is displayed when window opens
		servSelectWin.show();		
	}
	
	public boolean isShowing() {
		servSelectWin.toFront();
		return false;
	}
	
	public void toFront() {
		servSelectWin.toFront();
		
	}
	/**
	 * @purpose clears opened checks view, loads all opened checks matching server ID
	 * if fake checks haven't been loaded it calls loadOpenedTestChecks from restaurant, 
	 * then loads checks into opened check list view
	 */
	public void resetOpenedView() {
		
		openedChecksView.getItems().clear();		
		openedChecksCurrentServer  = new ArrayList<>();
		for(Check check: restaurant.getAllOpenedChecks()) {
			if(check.getServerId()==currentServer.getEmployeeId()) {
				openedChecksCurrentServer.add(check);
			}
		}
		if (loadCountOpnd == 0) {
			
			try {//<-------------------uncomment to quickly one identical closed check for each employee & line 31
				restaurant.loadOpenedTestChecks();
			} catch (CloneNotSupportedException e) {
				System.out.println("**************");
				System.out.println("*System Error*");
				System.out.println("**************");
			} catch (NullPointerException en) {
				System.out.println("*********************************");
				System.out.println("*Item Has Been Deleted From Menu*");
				System.out.println("*********************************");
			}
			loadCountOpnd++;//<-------------------uncomment to quickly one identical closed check for each employee
		}
		for(Check chk: openedChecksCurrentServer) {			
			openedChecksObsv.add("Check Id: "+(chk.getCheckId() + " Check Balance: " + formatter.format(chk.getCheckBalance())));			
		}
	}
	/**
	 * @purpose clears closed checks view, loads all closed checks matching server ID
	 * if fake checks haven't been loaded it calls loadClosedTestChecks from restaurant, 
	 * then loads checks into closed check list view
	 */
	public void resetClosedView() {		
		
		closedChecksView.getItems().clear();		
		closedChecksCurrentServer = new ArrayList<>();
		for(Check check: restaurant.getAllClosedChecks()) {
			if(check.getServerId()==currentServer.getEmployeeId()) {
				closedChecksCurrentServer.add(check);
			}
		}
		if (loadCountClsd == 0) {			
			try {//<-------------------uncomment to quickly create one identical closed check for each employee & line 32
				restaurant.loadClosedTestChecks();
			} catch (CloneNotSupportedException e) {				
				System.out.println("**************");
				System.out.println("*System Error*");
				System.out.println("**************");
			} catch (NullPointerException en) {
				System.out.println("*********************************");
				System.out.println("*Item Has Been Deleted From Menu*");
				System.out.println("*********************************");
			}
			loadCountClsd++;//<-------------------uncomment to quickly create one identical closed check for each employee
		}		
		
		for(Check chk: closedChecksCurrentServer) {			
			closedChecksObsv.add("Check Id: "+(chk.getCheckId() + " Check Balance: " + formatter.format(chk.getCheckBalance())));			
		}
	}	
	//updates cashOwed text field when cashOwedToRestaurant changes
	public class CashOwedObserver implements Observer{
		
		@Override
		public void update(Observable serv, Object amount) {			
			cashOwed.setText(formatter.format(currentServer.getCashOwedToRestaurant()));
		}

	}
}
