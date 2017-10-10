package edu.tridenttech.cpt237.lucas.view;

/**
 * @author Ben Lucas
 *@File: StartUpWindow.java
 *@Purpose: Startup is Launched from MAin Application and provides choices for the 
 *user to interact with the bank.  The user can withdraw/deposit from a selected 
 *account, open a new account, or transfer money from one account to another
 */
import java.util.Collections;
import edu.tridenttech.cpt237.lucas.model.Account;
import edu.tridenttech.cpt237.lucas.model.Bank;
import edu.tridenttech.cpt237.lucas.model.CheckingAccount;
import edu.tridenttech.cpt237.lucas.model.SavingsAccount;
import edu.tridenttech.cpt237.lucas.model.Transaction;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StartUpWindow {
	
	private Stage start;
	//Singleton Bank Instance, also displays account totals when not commented out(in Bank Class getBank() method, lines 38-46)
	private static Bank bank = Bank.loadBank();//--->passed to TransferWindow(line 21) and OpenAccountWindow(line 36)
	
	//child window instances
	private AccountWindow acctWindow = new AccountWindow();
	private OpenAccountWindow openAccount = new OpenAccountWindow(bank);//--->passed to OpenAccountWindow(line 36)	
	private TransferWindow transWindow = new TransferWindow(bank);//--->passed to TransferWindow(line 21)
	
	private Button btnLoad = new Button("Load Account");//opens AccountWindow
    private Button btnOpen = new Button("Open Acount");//opens OpenAccountWindow
    private Button btnTransfer = new Button("Transfer Funds");//opens TransferWindow
    //Closes Bank and displays transaction list to console(when Not Commented out in StartUpWindow line 119)
    private Button btnClose = new Button("Close Bank");
    //cb1 loaded line 73
    private ComboBox<String> cb1 = new ComboBox<String>();	
	
	private Alert conf1 = new Alert(AlertType.INFORMATION, "Thank You! Have A Great Day!");
    
    public StartUpWindow(Stage stage){
		
		
		BorderPane bPane = new BorderPane();
		HBox hb1 = new HBox();//account selection combo box
		HBox hb2 = new HBox();//open other windows buttons
		HBox hb3 = new HBox();//close main window button

		Scene scene = new Scene(bPane);
				
		start = stage;
		start.setTitle("Welcome To The Banking!");
	    start.setWidth(550);
	    start.setHeight(175);
	    start.setScene(scene);  
        
	    //window positioning     
        bPane.setPadding(new Insets(15,15,15,15));
        hb2.setPadding(new Insets(5,5,5,5));
        hb3.setPadding(new Insets(5,5,5,5));
        hb1.getChildren().addAll(new Label("Account Number:"),cb1);
        hb2.getChildren().addAll(btnLoad,btnOpen,btnTransfer);
        hb3.getChildren().addAll(btnClose);
        hb2.setSpacing(10);
        bPane.setTop(hb1);
        bPane.setCenter(hb2);
        bPane.setBottom(hb3);        
        hb1.setAlignment(Pos.CENTER);
        hb2.setAlignment(Pos.CENTER);
        hb3.setAlignment(Pos.CENTER);
        
        //button configuration
        cb1.setOnMouseClicked(new ReloadList());
        btnLoad.setOnAction(new AccountWindowLauncher());
        btnOpen.setOnAction(new OpenAccountLauncher());
        btnTransfer.setOnAction(new TransferWindowLauncher());
        btnClose.setOnAction(new CloseWindow());
	}//<---------------------------------------------------End of Constructor
	//launches AccountWindow, line 75
	public class AccountWindowLauncher implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            if (!acctWindow.isShowing()) {
               acctWindow.show(bank.getAccountByNum(cb1.getValue()));
            } else {
            	acctWindow.toFront();
            }
        }
    }
	//launches OpenAccount Window, line 76
	public class OpenAccountLauncher implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            if (!openAccount.isShowing()) {
            	openAccount.show();
            } else {
            	openAccount.toFront();
            }
        }
    }
	//launches TransferWindow line 77
	public class TransferWindowLauncher implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event){
            transWindow.show();
        }       
    }
	//closes bank and can display all transactions
	public class CloseWindow implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event){
            
        	//displayTransactionsList();//<-----------------------uncomment to display all transactions on exit
        	conf1.showAndWait();//<------------------Inform user of success
        	start.hide();
        }       
    }
	//clears and reloads cb1 ComboBox, implemented line 78
	public class ReloadList implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent event) {
			cb1.getItems().clear();
			for(Account account: bank.getCheckingAccount()) {
	    		cb1.getItems().add(account.getAccountNumber());
	    	} 
	        
	        for(Account account: bank.getSavingsAccount()) {
	    		cb1.getItems().add(account.getAccountNumber());
	    	}			
		}	
	}
	
	public void show() {
		for(Account account: bank.getCheckingAccount()) {
    		cb1.getItems().add(account.getAccountNumber());
    	} 
        
        for(Account account: bank.getSavingsAccount()) {
    		cb1.getItems().add(account.getAccountNumber());
    	}		
		start.show();		
	}
	
	//prints all transactions
	public static void displayTransactionsList(){
		
		//prints each SavingsAccount as well as all transactions for account		
		for(SavingsAccount account: Collections.unmodifiableList(bank.getSavingsAccount())){
			System.out.printf("Savings Account: %s%nAccount Balance: %7.2f %n%n", account.getAccountNumber(), account.getBalance());
			System.out.print("Transaction Type  Amount  Balance\n");
			for(Transaction trans: Collections.unmodifiableList(account.getTransactionList())){			
				if (trans.getAccountNumber().equals(account.getAccountNumber())) {
					System.out.printf("%12s %11.2f %8.2f%n", trans.getTransType(), trans.getAmount(),
							trans.getNewBalance());
				}
			}
		System.out.print("\n");	
		}		
		//prints each CheckingAccount as well as all transactions for account		
		for(CheckingAccount account: Collections.unmodifiableList(bank.getCheckingAccount())){			
				System.out.printf("Checking Account: %s%nAccount Balance: %7.2f %n%n", account.getAccountNumber(),
						account.getBalance());
				System.out.print("Transaction Type  Amount  Balance\n");
				for (Transaction trans : Collections.unmodifiableList(account.getTransactionList())) {
					
					if (trans.getAccountNumber().equals(account.getAccountNumber())) {
						System.out.printf("%12s %11.2f %8.2f%n", trans.getTransType(), trans.getAmount(),
								trans.getNewBalance());
					}				
			}
		System.out.print("\n");	
		}		
		return;
	}
}
