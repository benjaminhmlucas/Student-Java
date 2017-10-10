package edu.tridenttech.cpt237.lucas.model;
/**
 * @author Ben Lucas
 *@File: Bank.java
 *@Purpose: Creates checking and savings account array lists, loads transactions 
 *from .csv file, retrieves bank account information, and opens new bank accounts 
 *based on type.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank{	
	
	private ArrayList<SavingsAccount> savingsAccount = new ArrayList<>();
	private ArrayList<CheckingAccount> checkingAccount = new ArrayList<>();	
	private static int instance = 0;
	private static Bank bank;
	
	private Bank(){}
	
	public static Bank loadBank() {
		
		bank = new Bank();		
		if (instance == 0) {
			String filePath = "Transactions.csv";
			try {
				bank.loadTransactions(filePath);
				instance = 1;
			}

			catch (FileNotFoundException fx) {
				System.out.println("*************************************************");
				System.out.println("*************Incorrect File Name/Path************");
				System.out.println("*****************Program Exiting*****************");
				System.out.println("*************************************************");
				System.exit(1);
			} 
		}
		//print loaded accounts for proper transaction loading testing
		/*for(SavingsAccount account: Bank.savingsAccount){
			System.out.printf("*Account Number: %s Account Balance: %7.2f *%n", account.getAccountNumber(), account.getBalance());
		}		
		System.out.print("\n");
		for(CheckingAccount account: Bank.checkingAccount){
			System.out.printf("*Account Number: %s Account Balance: %7.2f *%n", account.getAccountNumber(), account.getBalance());
		}*/
		return bank;
		
	}
	//combines getSavingsAccountByNum() & getCheckingAccountByNum(), returns null if not found
	public Account getAccountByNum(String accountNumber) {
		Account x = null;
		if(!(getSavingsAccountByNum(accountNumber) == null)) {
			x = getSavingsAccountByNum(accountNumber);
		} 
		if(!(getCheckingAccountByNum(accountNumber) == null)) {
			x = getCheckingAccountByNum(accountNumber);
		}		
		return x;
	}
	
	
	
	//returns SavingsAccount object savingsAccount ArrayList, returns null if savings account isn't found
	public SavingsAccount getSavingsAccountByNum(String accountNumber){
		SavingsAccount s = null;
		for(SavingsAccount account: savingsAccount){
			if(account.getAccountNumber().equals(accountNumber)){			
				SavingsAccount x = account;				
				return x;
			}			
		}
		return s;		
	}
	
	//returns CheckingAccount object from checkingAccount ArrayList, returns null if checking account isn't found
		public CheckingAccount getCheckingAccountByNum(String accountNumber){
			CheckingAccount c = null;
			for(CheckingAccount account: checkingAccount){
				if(account.getAccountNumber().equals(accountNumber)){			
					CheckingAccount x = account;				
					return x;
				}			
			}
			return c;
		}
		
	public ArrayList<SavingsAccount> getSavingsAccount() {
		return this.savingsAccount;
	}

	public ArrayList<CheckingAccount> getCheckingAccount() {
		return this.checkingAccount;
	}
	//method is used to open a new account form the Open Account WINDOW
	public boolean openNewAccount(String accountNumber,double balance,String acctType) {
		if(acctType.equals("Savings")) {
			openSavingsAccount(balance,accountNumber);
			return true;
		}
		if(acctType.equals("Checking")) {
			openCheckingAccount(balance,accountNumber);
			return true;
		}
		return false;
	}	
	
	//creates new SavingsAccount object and adds to savingsAccount ArrayList (called from MainClass & OpenNewAccount())
	public boolean openSavingsAccount(double balance, String accountNumber){
		
		if(getAccountByNum(accountNumber.toUpperCase()) == null) 
		{
			SavingsAccount s = new SavingsAccount(balance,accountNumber);
			savingsAccount.add(s);
			return true;
		}
		else return false;
	}
	
	//creates new checkingAccount object and adds to checkingAccount ArrayList (called from MainClass & OpenNewAccount())
	public boolean openCheckingAccount(double balance, String accountNumber){
		
		if(getAccountByNum(accountNumber.toUpperCase()) == null) 
		{
			CheckingAccount c = new CheckingAccount(balance,accountNumber);
			checkingAccount.add(c);
			return true;
		}
		else return false;
	}
	
	//reads.csv file and performs transactions contained in file
	public boolean loadTransactions(String file) throws FileNotFoundException, NullPointerException{			
		
		Scanner input = new Scanner(new File(file));

		while(input.hasNext()){					
			
			String line = input.nextLine();
			String[] fields = line.split(",");
			
			char transType = fields[0].charAt(0);
			String accountNum = fields[1];
			char acctType = fields[2].charAt(0);
			double amount = Double.parseDouble(fields[3]);
						
			switch(transType)
			{
				//Opens accounts based on type
				case 'O':
					if(acctType == 'S'){
						openSavingsAccount(amount, accountNum);
						break;
					}
					else{
						openCheckingAccount(amount, accountNum);
						break;
					}
				
					//Deposits in account based on type
				case 'D':
					if(acctType == 'S'){
						SavingsAccount s = getSavingsAccountByNum(accountNum);
						s.deposit(amount);
						break;
					}
					else{
						CheckingAccount c = getCheckingAccountByNum(accountNum);
						c.deposit(amount);
						break;
					}
				
				//Withdraws from account based on type
				case 'W':
					if(acctType == 'S'){
						SavingsAccount s = getSavingsAccountByNum(accountNum);
						s.withdraw(amount);
						break;
					}
					else{
						CheckingAccount s = getCheckingAccountByNum(accountNum);
						s.withdraw(amount);
						break;
					}
				
				//Transfers money between accounts
				case 'T':
					transfer(fields[1],fields[2],Double.parseDouble(fields[3]));
					break;
				
				default:
					input.close();
					return false;					
			}
		}		
		input.close();
		return true;		
	}//<------End loadTransactions
	
	//Transfers money between accounts
	public boolean transfer(String fromAccount, String toAccount, double amount){
		
		//Finds from account based on type. If withdraw is successful function finds to account and makes deposit.
		if(getSavingsAccountByNum(fromAccount)!=null){
			SavingsAccount from = getSavingsAccountByNum(fromAccount);
			if(from.withdraw(amount)){
				if(getSavingsAccountByNum(toAccount)!=null){
					SavingsAccount to = getSavingsAccountByNum(toAccount);
					to.deposit(amount);
					return true;
				}				
				else{
					CheckingAccount to = getCheckingAccountByNum(toAccount);
					to.deposit(amount);
					return true;
				}
			}
		}
		
		else{
			CheckingAccount from = getCheckingAccountByNum(fromAccount);
			if(from.withdraw(amount)){				
				if(getSavingsAccountByNum(toAccount)!=null){
					SavingsAccount to = getSavingsAccountByNum(toAccount);
					to.deposit(amount);
					return true;
				}				
				else{
					CheckingAccount to = getCheckingAccountByNum(toAccount);
					to.deposit(amount);
					return true;
				}
			}
		}		
		return false;
	}
		
}
