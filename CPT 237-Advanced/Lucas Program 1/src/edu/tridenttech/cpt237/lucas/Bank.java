package edu.tridenttech.cpt237.lucas;
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
	
	static ArrayList<SavingsAccount> savingsAccount;
	static ArrayList<CheckingAccount> checkingAccount;
	
	
	public Bank(){
		savingsAccount = new ArrayList<>();
		checkingAccount = new ArrayList<>();
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
	
	
	static public ArrayList<SavingsAccount> getSavingsAccount() {
		return savingsAccount;
	}

	static public ArrayList<CheckingAccount> getCheckingAccount() {
		return checkingAccount;
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
	
	//creates new SavingsAccount object and adds to savingsAccount ArrayList
	public boolean openSavingsAccount(double balance, String accountNumber){
		SavingsAccount s = new SavingsAccount(balance,accountNumber);
		savingsAccount.add(s);
		return true;
	}
	
	//creates new checkingAccount object and adds to checkingAccount ArrayList
	public boolean openCheckingAccount(double balance, String accountNumber){
		CheckingAccount s = new CheckingAccount(balance,accountNumber);
		checkingAccount.add(s);
		return true;
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
		
		//Finds accounts from account based on type. If withdraw is successful function finds to account and makes deposit.
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
