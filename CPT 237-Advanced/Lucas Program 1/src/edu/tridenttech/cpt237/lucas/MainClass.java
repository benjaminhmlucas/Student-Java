package edu.tridenttech.cpt237.lucas;
/**
 * @author Ben Lucas
 *@File: MainClass.java
 *@Purpose: Handles user interactions with accounts.  Loads into ArrayLists depending on 
 *account type. Prompts user interact with loaded accounts.  Displays all account numbers, 
 *final balances, and transactions. 
 */
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) throws FileNotFoundException{
		
		String filePath = "Transactions.csv";
		String accountNum;
		double amount;
		char transType;
		Scanner input = new Scanner(System.in);
		Bank bank = new Bank();
		
		
		//welcome screen
		System.out.println("*************************************************");
		System.out.println("*****Welcome to Johnson's Inn Security Bank!*****");
		System.out.println("**********Where Your Needs Come First!***********");
		System.out.println("******(after all of our other fiscal needs)******");
		System.out.println("*************************************************");
		System.out.println("***********Press ENTER Key To Continue***********");
		System.out.println("*************************************************");
		//input.nextLine();
		System.out.println("*************************************************");
		System.out.println("**************Loading Transactions***************");
		System.out.println("**************BEEP BOOP BOOP BEEP***************");
		System.out.println("*************************************************");
		
		try{
			bank.loadTransactions(filePath);
		}
	
		catch(FileNotFoundException fx) {
			System.out.println("*************************************************");
			System.out.println("***********Incorrect File Name/Path**************");
			System.out.println("*************************************************");
		}
		//print loaded accounts
		for(SavingsAccount account: Bank.savingsAccount){
			System.out.printf("*Account Number: %s Account Balance: %7.2f *%n", account.getAccountNumber(), account.getBalance());
		}		
		System.out.print("\n");
		for(CheckingAccount account: Bank.checkingAccount){
			System.out.printf("*Account Number: %s Account Balance: %7.2f *%n", account.getAccountNumber(), account.getBalance());
		}
		
		//gets account input from user
		System.out.println("*************************************************");
		System.out.println("**********Please Enter Account Number************");
		System.out.println("***********Type quit To End Program**************");
		System.out.println("*************************************************");
			
		accountNum = input.nextLine().toUpperCase();		
		
		//main loop begins
		while(!accountNum.equals("QUIT")){
			
			//checks if account is in list, loops until valid account is found
			while(bank.getSavingsAccountByNum(accountNum) == null && bank.getCheckingAccountByNum(accountNum) == null){
				System.out.println("*************************************************");
				System.out.println("****Invalid Entry, Try Another Account Number****");
				System.out.println("*************************************************");
				accountNum = input.next().toUpperCase();
			}
			
			//takes action decision from user, loops until valid entry is input
			System.out.println("*************************************************");
			System.out.println("************What Would You Like To Do?***********");
			System.out.println("*************Type (d) to Deposit Cash************");
			System.out.println("*************Type (w) to Withdraw Cash***********");
			System.out.println("*********Type (s) to Show Account Balance********");
			System.out.println("*********Type (r) to Reset Withdraw Count********");
			System.out.println("*************************************************");
			
			
			transType = input.next().toLowerCase().charAt(0);
			
			//input validation loop
			while(transType != 'd' && transType != 'w' && transType != 's' && transType != 'r'){
				System.out.println("*************************************************");
				System.out.println("***Invalid Entry, Select One Of The Following:***");
				System.out.println("************Type (d) to Deposit Cash*************");
				System.out.println("************Type (w) to Withdraw Cash************");
				System.out.println("*********Type (s) to Show Account Balance********");
				System.out.println("*********Type (r) to Reset Withdraw Count********");
				System.out.println("*************************************************");
				transType = input.next().toLowerCase().charAt(0);
			}
			
			//switch to determine action to take based on user decision
			switch(transType){
			
			//user choice deposit
			case 'd':
				
				//SavingsAccount Deposit Loop
				if(bank.getSavingsAccountByNum(accountNum)!=null){
					
					SavingsAccount currentAccount = bank.getSavingsAccountByNum(accountNum);						
					
					//loop checks for input to be double, loops until input is double
					while (true) {
						System.out.println("*************************************************");
						System.out.printf("*Current Balance: %30.2f*%n", currentAccount.getBalance());
						System.out.println("*********Please Enter Amount To Deposit:*********");
						System.out.println("*************************************************");
					    try {
					        amount = Double.parseDouble(input.next());
					        break; 
					    } catch (NumberFormatException ignore) {
					    	System.out.println("**********************************************************");
							System.out.println("                      Invalid Input!                      ");
							System.out.println("**********************************************************");
					    }
					}
					
					//prints new balance
					currentAccount.deposit(amount);					
					System.out.printf("*Current Balance: %30.2f*%n", currentAccount.getBalance());					
					System.out.println("*************************************************");
				}
				//CheckingAccount deposit loop
				else{
					
					CheckingAccount currentAccount = bank.getCheckingAccountByNum(accountNum);				
					//checks for double input, loops until input is double
					while (true) {
						System.out.println("*************************************************");
						System.out.printf("*Current Balance: %30.2f*%n", currentAccount.getBalance());
						System.out.println("*********Please Enter Amount To Deposit:*********");
						System.out.println("*************************************************");
					    try {
					        amount = Double.parseDouble(input.next());
					        break; 
					    } catch (NumberFormatException ignore) {
					    	System.out.println("**********************************************************");
							System.out.println("                      Invalid Input!                      ");
							System.out.println("**********************************************************");
					    }
					}
										
					currentAccount.deposit(amount);
					System.out.printf("*Current Balance: %30.2f*%n", currentAccount.getBalance());					
					System.out.println("*************************************************");
				}				
				break;
			
			//user choice withdraw
			case 'w':
				
				//SavingsAccount withdraw loop
				if(bank.getSavingsAccountByNum(accountNum)!=null){
					SavingsAccount currentAccount = bank.getSavingsAccountByNum(accountNum);						
					
					//loop checks for double input
					while (true) {
						System.out.println("*************************************************");
						System.out.printf("*Current Balance: %30.2f*%n", currentAccount.getBalance());
						System.out.println("********Please Enter Amount To Withdraw:*********");
						System.out.println("*************************************************");
					    try {
					        amount = Double.parseDouble(input.next());
					        break; 
					    } catch (NumberFormatException ignore) {
					    	System.out.println("**********************************************************");
							System.out.println("                      Invalid Input!                      ");
							System.out.println("**********************************************************");
					    }
					}					
					
					//loop checks to ensure amount to be taken out won't overdraw account
					while(currentAccount.getBalance() - amount < 0){
						System.out.println("**********************************************************");
						System.out.println("Not Enough Money In Account! Please Enter A Lower Amount!*");
						System.out.println("**********************************************************");
						while (!input.hasNextDouble())
						{
							System.out.println("**********************************************************");
							System.out.println("Invalid Input! Please Type A Number:");
							System.out.println("**********************************************************");
							input.nextDouble();
						}
						amount = input.nextDouble();
						
					}
					currentAccount.withdraw(amount);
					
					//display new balance
					System.out.printf("*Current Balance: %30.2f*%n", currentAccount.getBalance());					
					System.out.println("*************************************************");
				}
				
				//CheckingAccount withdraw
				else{
					CheckingAccount currentAccount = bank.getCheckingAccountByNum(accountNum);				
					
					//double input check loop
					while (true) {
						System.out.println("*************************************************");
						System.out.printf("*Current Balance: %30.2f*%n", currentAccount.getBalance());
						System.out.println("********Please Enter Amount To Withdraw:*********");
						System.out.println("*************************************************");
					    try {
					        amount = Double.parseDouble(input.next());
					        break; 
					    } catch (NumberFormatException ignore) {
					    	System.out.println("**********************************************************");
							System.out.println("                      Invalid Input!                      ");
							System.out.println("**********************************************************");
					    }
					}
					
					//overdraw check loop
					while(currentAccount.getBalance() - amount < 0){
						System.out.println("**********************************************************");
						System.out.println("Not Enough Money In Account! Please Enter A Lower Amount!*");
						System.out.println("**********************************************************");
						while (!input.hasNextDouble())
						{
							System.out.println("**********************************************************");
							System.out.println("Invalid Input! Please Type A Number:");
							System.out.println("**********************************************************");
							input.nextDouble();
						}
						amount = input.nextDouble();
						
					}
					currentAccount.withdraw(amount);					
					
					//output new balance
					System.out.printf("*Current Balance: %30.2f*%n", currentAccount.getBalance());					
					System.out.println("*************************************************");
				}				
				break;
			
			//user input choice show balance
			case 's':
				
				//SavingsAccount balance
				if(bank.getSavingsAccountByNum(accountNum)!=null){
					SavingsAccount currentAccount = bank.getSavingsAccountByNum(accountNum);						
					System.out.println("*************************************************");
					System.out.printf("*Current Balance: %30.2f*%n", currentAccount.getBalance());
					System.out.println("*************************************************");					
				}
				
				//checking account balance
				else{
					CheckingAccount currentAccount = bank.getCheckingAccountByNum(accountNum);				
					System.out.println("*************************************************");
					System.out.printf("*Current Balance: %30.2f*%n", currentAccount.getBalance());
					System.out.println("*************************************************");					
				}
				break;
			case 'r':
				//SavingsAccount balance
				if(bank.getSavingsAccountByNum(accountNum)!=null){
					SavingsAccount currentAccount = bank.getSavingsAccountByNum(accountNum);						
					currentAccount.resetWithdrawalCount();
					System.out.println("*************************************************");
					System.out.println("****Bank Savings Account Withdraw Count Reset!***");
					System.out.println("*************************************************");					
				}
				
				//checking account balance
				else{
									
					System.out.println("******************************************************");
					System.out.println("*There Are No Withdraw Counts For A Checking Acocunts*");
					System.out.println("****************Have Fun With That!*******************");
					System.out.println("******************************************************");					
				}
				break;
			}			
			
			//quit loop or continue choice
			System.out.println("*************************************************");
			System.out.println("**********Please Enter Account Number************");
			System.out.println("***********Type quit To End Program**************");
			System.out.println("*************************************************");
			input.nextLine();	
			accountNum = input.nextLine().toUpperCase();
		
		}//<--------main loop ends			
		
		System.out.println("*************************************************");
		System.out.println("*********Thanks you For Your Business!***********");
		System.out.println("******Have Peek At Everyone's Bank Account*******");
		System.out.println("*************************************************");
		displayTransactionsList();		
		input.close();
	}
	//prints all transactions
	public static void displayTransactionsList(){
		
		//prints each SavingsAccount as well as all transactions for account		
		for(SavingsAccount account: Collections.unmodifiableList(Bank.savingsAccount)){
			System.out.printf("Savings Account: %s%nAccount Balance: %7.2f %n%n", account.getAccountNumber(), account.getBalance());
			System.out.print("Transaction Type  Amount  Balance\n");
			for(Transaction trans: Collections.unmodifiableList(account.transList)){
				System.out.printf("%12s %11.2f %8.2f%n", trans.getTransType(), trans.getAmount(), trans.getNewBalance());
			}
			System.out.print("\n");
		}		
		//prints each SavingsAccount as well as all transactions for account		
		for(CheckingAccount account: Collections.unmodifiableList(Bank.checkingAccount)){
			System.out.printf("Checking Account: %s%nAccount Balance: %7.2f %n%n", account.getAccountNumber(), account.getBalance());
			System.out.print("Transaction Type  Amount  Balance\n");
			for(Transaction trans: Collections.unmodifiableList(account.transList)){
				System.out.printf("%12s %11.2f %8.2f%n", trans.getTransType(), trans.getAmount(), trans.getNewBalance());
			}
			System.out.print("\n");
		}		
		return;
	}
}
