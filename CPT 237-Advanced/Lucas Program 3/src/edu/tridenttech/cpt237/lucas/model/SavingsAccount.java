package edu.tridenttech.cpt237.lucas.model;

/**
 * @author Ben Lucas
 *@File: SavingsAccount.java
 *@Purpose: Extends Account class and adds a service fee when max withdraws is reached
 */
public class SavingsAccount extends Account{
	
	private final static int MAX_WITHDRWALS = 7;
	private final static double SERVICE_FEE = 0.23;
	private int numWithdrawals = 0;
	private final String acctType = "Savings";
	
	public SavingsAccount(double balance, String accountNumber) {
		
		super(balance, accountNumber);		
	}	
	
	public void resetWithdrawalCount(){
		this.numWithdrawals = 0;
	}
	
	public String getType() {
		return acctType;
	}
	
	//override adds service fee functionality. Method doesn't count SERVICE_FEE in numWithdraws
	@Override
	public boolean withdraw(double amount)
	{
		if(this.numWithdrawals < MAX_WITHDRWALS){
			if(super.withdraw(amount)){
				this.numWithdrawals++;
				return true;
			};			
		}
		else{
			if(this.getBalance() - amount - SERVICE_FEE >= 0){
				super.withdraw(amount);
				super.serviceFee(SERVICE_FEE);
				this.numWithdrawals++;
				return true;
			}			
		}	
	return false;
	}
}
