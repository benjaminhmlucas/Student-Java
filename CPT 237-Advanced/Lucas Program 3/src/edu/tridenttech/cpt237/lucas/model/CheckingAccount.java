package edu.tridenttech.cpt237.lucas.model;

/**
 * @author Ben Lucas
 *@File: CheckingAccount.java
 *@Purpose: Extends Account class and adds a service fee when account
 *amount falls below minimum balance.
 */
public class CheckingAccount extends Account{
	
	private final static double MIN_BALANCE = 100.00;
	private final static double SERVICE_FEE = 0.13;
	private final String acctType = "Checking";
	
	public CheckingAccount(double balance, String accountNumber) {
		super(balance, accountNumber);
	}	
	
	public String getType() {
		return acctType;
	}
	
	//
	@Override
	public boolean withdraw(double amount){				
		if(this.getBalance() < MIN_BALANCE){
			if(this.getBalance() - amount - SERVICE_FEE >= 0){
				super.withdraw(amount);
				super.serviceFee(SERVICE_FEE);
				return true;			
			}
			else {
				return false;
			}
		}
		else{
			if(this.getBalance() - amount >= 0){
				super.withdraw(amount);
				return true;
			}
			else {
				return false;
			}
		}
	}	
}