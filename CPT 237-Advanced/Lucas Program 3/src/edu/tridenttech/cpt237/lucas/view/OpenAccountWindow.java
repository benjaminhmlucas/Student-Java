package edu.tridenttech.cpt237.lucas.view;
/**
 * @author Ben Lucas
 *@File: OpenAccountWindow.java
 *@Purpose: Allows user to open a new account and set a balance.  The new Account is then added to 
 *the appropriate account ArrayList
 */
import edu.tridenttech.cpt237.lucas.model.Bank;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class OpenAccountWindow {
	
	private static Bank bank;//<--------------------------------Bank Object Passed In and assigned line 42 & 44
	//Window and node construction
	private Stage openAccountWindow = new Stage();
	private Button btnOpenAccount = new Button("Open Account");
	private	Button btnCancel = new Button("Cancel");
	
	private TextField newAcctNum = new TextField();
	private TextField amount = new TextField();
	
	private RadioButton savings = new RadioButton("Savings");
	private RadioButton checking = new RadioButton("Checking");
	
	private Alert alert1 = new Alert(AlertType.ERROR, "Incorrect Data Input! Account Not Created!");
	private Alert alert2 = new Alert(AlertType.ERROR, "You Must Select Account Type! Account Not Created!");
	private Alert alert3 = new Alert(AlertType.ERROR, "Account Number Already Used!");
	private Alert conf1 = new Alert(AlertType.INFORMATION, "Congratulations! Account Created!");
	
	public OpenAccountWindow(Bank inBank) {
		
		bank = inBank;		
		//window positioning
		openAccountWindow.setTitle("Open A New Account!");		
		GridPane gPane = new GridPane();
		Scene scene = new Scene(gPane);	   		
		ToggleGroup tGroup = new ToggleGroup();
		savings.setToggleGroup(tGroup);
		checking.setToggleGroup(tGroup);
		openAccountWindow.setWidth(400);
		openAccountWindow.setScene(scene);  
        		
		gPane.setPadding(new Insets(15,15,15,15));
		gPane.add(new Label("New Account Number:"), 0, 0);
        gPane.add(newAcctNum, 1, 0);
        gPane.add(new Label("Deposit Amount:"), 0, 1);
        gPane.add(amount, 1, 1);
        gPane.add(new Label("Choose Account Type:"), 0, 2);
        gPane.add(savings, 1, 2);
        gPane.add(checking, 1, 3);
        gPane.add(btnOpenAccount, 0, 4);
        gPane.add(btnCancel, 1, 4);
        
		//checks for radio button selection, if not selected an error message is returned and fields reset
        //radio selection determine method used to open account
        btnOpenAccount.setOnAction(new EventHandler<ActionEvent>() {
	
			@Override
			public void handle(ActionEvent event) {
				if (savings.isSelected()||checking.isSelected()) {
					try {
						if (savings.isSelected()) {
							if (bank.openSavingsAccount(Double.parseDouble(amount.getText()), newAcctNum.getText())) {
								conf1.showAndWait();//<---------------------------------------------------------------Success Message!
								openAccountWindow.close();
							}
							else {
								alert3.showAndWait();//<---------------------------------------------------------------Account Taken Message!
							}
						}
						else {
							if(bank.openCheckingAccount(Double.parseDouble(amount.getText()), newAcctNum.getText())) 
							{
								conf1.showAndWait();//<----------------------------------------------------------------Success Message!
								openAccountWindow.close();
							}
							else {
								alert3.showAndWait();//<---------------------------------------------------------------AccountTaken Message!
							}
						}
					} catch (NumberFormatException e) {
						alert1.showAndWait();
						
					} 
				}
				else {
					alert2.showAndWait();
					
				}
			}
		});
		//close Account window button
		btnCancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				openAccountWindow.close();				
			}
		});
	}

	public boolean isShowing() {
		openAccountWindow.toFront();
		return false;
	}

	public void show() {
		
		resetFields();
		openAccountWindow.show();
		
	}

	public void toFront() {
		openAccountWindow.toFront();
		
	}
	//clears fields for window
	public void resetFields() {
		newAcctNum.setText("Enter An Account");
		amount.setText("Enter An Amount");
		savings.setSelected(false);
		checking.setSelected(false);
	}
}
