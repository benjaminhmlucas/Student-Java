package edu.tridenttech.cpt237.lucas.view;
/**
 * @author Ben Lucas
 *@File: TransferWindow.java
 *@Purpose: Loads account lists into combo boxes and allows user to 
 *select an account to withdraw from, an account to deposit in,
 *and an amount to transfer.
 */
import edu.tridenttech.cpt237.lucas.model.Account;
import edu.tridenttech.cpt237.lucas.model.Bank;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TransferWindow {
	
	private Stage transWindow = new Stage();
	private static Bank bank;//<------------------Bank object passed in and assigned line 37 & 40

	private Button btnTransfer = new Button("Transfer $$$");
	private	Button btnCancel = new Button("Cancel");
	
	private TextField amount = new TextField();
	
    private ComboBox<String> cbFrom = new ComboBox<String>();	
    private ComboBox<String> cbTo = new ComboBox<String>();	
	
	private Alert alert1 = new Alert(AlertType.ERROR, "You Must Select One (From:) Account and One (To:) Account!");
	private Alert alert2 = new Alert(AlertType.ERROR, "Cannot Transfer To Same Account!");
	private Alert alert3 = new Alert(AlertType.ERROR, "You Must Enter An Amount!");
	private Alert alert4 = new Alert(AlertType.ERROR, "Insufficient Funds!");
	private Alert conf1;//<-----------------------------assigned line 62	
	
	public TransferWindow(Bank passedInBank) {
		
		//window positioning
		bank = passedInBank;
		transWindow.setTitle("Transfer $$$ To Another Account!");		
		GridPane gPane = new GridPane();
		Scene scene = new Scene(gPane);			
		transWindow.setWidth(400);
		transWindow.setScene(scene);       		
		gPane.setPadding(new Insets(15,15,15,15));
		
		gPane.add(new Label("Transfer From:"), 0, 0);
        gPane.add(cbFrom, 1, 0);
        gPane.add(new Label("Transfer To:"), 0, 1);
        gPane.add(cbTo, 1, 1);
        gPane.add(new Label("Amount To Transfer:"), 0, 2);
        gPane.add(amount, 1, 2);
        gPane.add(btnTransfer, 0, 3);
        gPane.add(btnCancel, 1, 3);
	
        //checks for duplicate accounts, returns message if true
        //checks for null account values, returns message if true
        //checks for incorrect or null amount TextField value, returns message if true
        //confirms money transfer and amount transfered if successful
        btnTransfer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				String transAmount;
				try {
					transAmount = String.format("$%.2f",Double.parseDouble(amount.getText()));
				} catch (NumberFormatException e) {
					alert3.showAndWait();					
					return;
				}
				conf1 = new Alert(AlertType.INFORMATION, transAmount  + " Transfered!");//<---------------------Inform user of success
				//null account check
				if(cbFrom.getValue() == null || cbTo.getValue() == null) {
					alert1.showAndWait();					
					return;
				}
				//duplicate account check
				if(cbFrom.getValue() == cbTo.getValue()) {
					alert2.showAndWait();
					return;
				}
				//entered amount into TextField check
				if(amount.getText() == null) {
					alert3.showAndWait();					
					return;
				}
				
				//calls transfer from Bank Class, line 189			
				if(bank.transfer(cbFrom.getValue(), cbTo.getValue(), Double.parseDouble(amount.getText()))) {
					conf1.showAndWait();
					transWindow.close();
				}
				else {
					alert4.showAndWait();					
					return;			
				}
				
			}
		});
        //closes TransferWindow
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				transWindow.close();				
			}
		});	
	}

	//every time TransferWindow is shown fields are reset and reloaded
	public void show() {
		
		resetFields();
		cbFrom.getItems().clear();
		cbTo.getItems().clear();
		
		for(Account account: bank.getCheckingAccount()) {
			cbFrom.getItems().add(account.getAccountNumber());
    	} 
        
        for(Account account: bank.getSavingsAccount()) {
        	cbFrom.getItems().add(account.getAccountNumber());
    	}
        for(Account account: bank.getCheckingAccount()) {
        	cbTo.getItems().add(account.getAccountNumber());
    	} 
        
        for(Account account: bank.getSavingsAccount()) {
        	cbTo.getItems().add(account.getAccountNumber());
    	}
		transWindow.show();		
	}
	
	public void resetFields() {
		amount.setText("");
		cbFrom.getSelectionModel().clearSelection();
		cbTo.getSelectionModel().clearSelection();
	}

}
