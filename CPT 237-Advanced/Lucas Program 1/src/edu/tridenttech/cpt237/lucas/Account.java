package edu.tridenttech.cpt237.lucas;
/**
 * @author Ben Lucas
 *@File: Account.java
 *@Purpose: Creates Account objects, handles deposits/withdrawals, and 
 *tracks transactions with transList
 */
import java.util.ArrayList;

public class Account {
	private double balance;
	private String accountNumber;
	private final static double CHECKING_FEE = 0.13;
	private final static double SAVINGS_FEE = 0.23;
	ArrayList<Transaction> transList;
	
	public Account(double balance, String accountNumber) {
		this.balance = balance;
		this.accountNumber = accountNumber;
		this.transList  = new ArrayList<>();
		Transaction trans = new Transaction("Open",balance,balance);
		transList.add(trans);
	}

	public double getBalance() {
		return balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	
	//withdraws money from account object and adds transactions to transList ArrayList
	public boolean withdraw(double amount)
	{
		if(this.balance - amount >= 0){
			this.balance = this.balance - amount;			
			if(amount != CHECKING_FEE && amount != SAVINGS_FEE ){
				Transaction trans = new Transaction("Withdrawal",amount,balance);
				transList.add(trans);
			}
			else{
				Transaction trans = new Transaction("Service Fee",amount,balance);
				transList.add(trans);
			}
			return true;
		}
		else{
			//Use line below to communicate every time withdraw fails due to insufficient funds
			//System.out.println("Not Enough Money In Account! Try Again!");
			return false;
		}
		
	}
	
	//deposits money in account object and adds transactions to transList ArrayList
	public boolean deposit(double amount){
		this.balance = this.balance + amount;
		Transaction trans = new Transaction("Deposit",amount,balance);
		transList.add(trans);
		return true;
	}
	

}
