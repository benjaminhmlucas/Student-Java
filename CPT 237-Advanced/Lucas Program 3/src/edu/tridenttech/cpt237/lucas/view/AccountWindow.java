package edu.tridenttech.cpt237.lucas.view;
/**
 * @author Ben Lucas
 *@File: AccountWindow.java
 *@Purpose: Opened by Startup Window, allows user to withdraw or deposit money into the account selected in StartUpWindow
 */
import edu.tridenttech.cpt237.lucas.model.Account;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AccountWindow {
	
	private Stage acctWindow = new Stage();
	private Account account;//<-------------------------set line 107
	private Button btnWithdraw = new Button("Withdraw");
	private	Button btnDeposit = new Button("Deposit");
	private Button btnClose = new Button("Close");
	 
	private Label acctType = new Label();
	private Label balance = new Label();
	private TextField amount = new TextField("Enter An Amount");
	
	private Alert alert1 = new Alert(AlertType.ERROR, "Please Enter An Amount!");
	private Alert alert2 = new Alert(AlertType.ERROR, "Insufficent Funds!");
	private Alert alert3 = new Alert(AlertType.ERROR, "Please Choose An Account!");

	private Alert conf1 = new Alert(AlertType.INFORMATION, "Thank You!");
	
	public AccountWindow() {
		
		//Window Construction and Positioning
		acctWindow.setTitle("Deposit and Withdrawals");		
		GridPane gPane = new GridPane();
		HBox hb1 = new HBox();
		Scene scene = new Scene(gPane);	
		acctWindow.setWidth(400);
		acctWindow.setScene(scene);  
                
        gPane.setPadding(new Insets(15,15,15,15));
		gPane.add(new Label("Account Type:"), 0, 0);
        gPane.add(acctType, 1, 0);
        gPane.add(new Label("Balance:"), 0, 1);
        gPane.add(balance, 1, 1);
        gPane.add(new Label("Withdraw/Deposit Amount:"), 0, 2);
        gPane.add(amount, 1, 2);
        hb1.getChildren().addAll(btnWithdraw, btnDeposit);
        gPane.add(hb1, 0, 3);
        gPane.add(btnClose, 1, 3);        
       
        //button assignments-Withdraw button will do nothing if account isn't selected, 
        //returns error messages for improper amount formatting and insufficient funds
        btnWithdraw.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(!amount.getText().equals("Enter Amount")) {
					if(!(account == null)) {
						try {							
							//calls overridden withdraw method based on account type
							if(account.withdraw(Double.parseDouble(amount.getText()))) {								
								balance.setText(String.format("$%1$,.2f", account.getBalance()));
							}
							else {
								alert2.showAndWait();								
							}
						} 
						
						catch (NumberFormatException e) {
							alert1.showAndWait();							
						}
					}
				}
				
			}
		});
        
        //deposits, returns feedback message if amount isn't proper format
        btnDeposit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(!amount.getText().equals("Enter Amount")) {
					
					if(!(account == null)) {
						
						try {
							//calls overridden deposit method based on account type
							account.deposit(Double.parseDouble(amount.getText()));
							balance.setText(String.format("$%1$,.2f", account.getBalance()));
						} 
						
						catch (NumberFormatException e) {
							alert1.showAndWait();							
						}
					}
				}
			}
		});
        
        //displays a thank you message and closes AccountWindow
        btnClose.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				conf1.showAndWait();//<-------------------------------Inform user of success
				acctWindow.hide();				
			}
		});
	}

	public boolean isShowing() {
		acctWindow.toFront();
		return false;
	}
	
	//shows account window and resets fields
	public void show(Account acct) {
		if (!(acct == null)) {
			account = acct;
			acctType.setText(account.getType());
			balance.setText(String.format("$%1$,.2f", account.getBalance()));
			amount.setText("Enter An Amount");
			acctWindow.show();
		}
		else {
			alert3.showAndWait();
		}
	}

	public void toFront() {
		acctWindow.toFront();
		
	}
	
}
