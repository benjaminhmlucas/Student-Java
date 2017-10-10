package edu.tridenttech.cpt237.lucas.model;
/**
 * @author Ben Lucas
 *@File: Transaction.java
 *@Purpose: Creates transaction objects to be stored in ArrayList transList in Account 
 */
public class Transaction {
	
	private String acctNum;
	private String transType;
	private Double amount;
	private Double newBalance;
	
	public Transaction(String acctNumber,String transType,Double amount, Double newBalance) {
		this.acctNum = acctNumber;
		this.transType = transType;
		this.amount = amount;
		this.newBalance = newBalance;
	}

	public String getTransType() {
		return transType;
	}

	public Double getAmount() {
		return amount;
	}

	public Double getNewBalance() {
		return newBalance;
	}

	public String getAccountNumber() {
		return this.acctNum;
	}	
	
}
