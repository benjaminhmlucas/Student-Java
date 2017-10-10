package edu.tridenttech.cpt237.lucas.model;
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
	private String acctType = "Account";
	private ArrayList<Transaction> transList = new ArrayList<>();;
	
	public Account(double balance, String accountNumber) {
		this.balance = balance;
		this.accountNumber = accountNumber;
		Transaction trans = new Transaction(accountNumber,"Open",balance,balance);
		transList.add(trans);
	}

	public double getBalance() {
		return balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	
	public String getType() {
		return acctType;
	}
	
	public ArrayList<Transaction> getTransactionList() {
		return transList;
	}
	
	//withdraws money from account object and adds transactions to transList ArrayList
	public boolean withdraw(double amount)
	{
		if(this.balance - amount >= 0)
		{
			this.balance = this.balance - amount;			
			Transaction trans = new Transaction(this.accountNumber,"Withdrawal",amount,balance);
			transList.add(trans);		
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
		Transaction trans = new Transaction(this.accountNumber,"Deposit",amount,balance);
		transList.add(trans);
		return true;
	}
	
	public boolean serviceFee(double amount)
	{
		if (balance >= amount) {
			balance-=amount;
			Transaction t = new Transaction(this.accountNumber,"Service Fee", amount, balance);
			transList.add(t);
		}
		return true;
	}

}
