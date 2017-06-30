package edu.tridenttech.cpt187.lucas.program1;
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
	
	public SavingsAccount()
	{
		balance = 0.0;
		rate = .004;
		accountNumber = "007";
	}
	public SavingsAccount(double newBalance,double newRate,String newAccountNumber)
	{
		balance = newBalance;
		rate =  newRate;
		accountNumber = newAccountNumber;
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
