package edu.tridenttech.cpt237.lucas;
/**
 * @author Ben Lucas
 *@File: SavingsAccount.java
 *@Purpose: Extends Account class and adds a service fee when max withdraws is reached
 */
public class SavingsAccount extends Account{
	
	private final static int MAX_WITHDRWALS = 7;
	private final static double SERVICE_FEE = 0.23;
	private int numWithdrawals = 0;
	
	public SavingsAccount(double balance, String accountNumber) {
		super(balance, accountNumber);		
	}
	
	public void resetWithdrawalCount(){
		this.numWithdrawals = 0;
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
			if(super.withdraw(amount)){
				this.numWithdrawals++;
			}
			super.withdraw(SERVICE_FEE);			
			return true;
		}	
	return false;
	}
}
