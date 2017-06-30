package edu.tridenttech.cpt187.lucas.program2;
/**
 * @file: SavingsAccount.java
 * @author: Ben Lucas
 * @purpose: contains class to interact with savings account 
 */
public class SavingsAccount 
{
	//variable declaration
	private String accountNumber;
	private double balance;
	private double rate;
	
	public SavingsAccount(String acctNo, double startingBalance, double startingRate)
	{
		balance = startingBalance;
		rate = startingRate;
		accountNumber = acctNo;
	}
	public double getBalance()
	{
		return balance;
	}
	public double getRate()
	{
		return rate;
	}
	public String getAccountNumber()
	{
		return accountNumber;
	}
		public void setAccountNumber(String newAccountNumber)
	{
		accountNumber = newAccountNumber;
	}
	public void setRate(double newRate)
	{
		rate = newRate;
	}	
	public void addToBalance(double amount)
	{
		balance += amount;
	}
	public void withdrawFromBalance(double amount)
	{
		balance -= amount;
	}
}//END SavingsAccount
