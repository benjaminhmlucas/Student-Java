package edu.tridenttech.cpt237.lucas;
/**
 * @author Ben Lucas
 *@File: CheckingAccount.java
 *@Purpose: Extends Account class and adds a service fee when account
 *amount falls below minimum balance.
 */
public class CheckingAccount extends Account{
	
	private final static double MIN_BALANCE = 100.00;
	private final static double SERVICE_FEE = 0.13;
	
	public CheckingAccount(double balance, String accountNumber) {
		super(balance, accountNumber);
	}	
	
	//
	@Override
	public boolean withdraw(double amount){				
		if(this.getBalance() < MIN_BALANCE){
			if(this.getBalance() - amount > 0);{
				if(super.withdraw(amount)){
					super.withdraw(SERVICE_FEE);
					return true;
				}
			}
		}
		else{
			super.withdraw(amount);
			return true;
		}
	return false;
	}	
}