package edu.tridenttech.cpt237.lucas;
 
public class Transaction {
	
	private String transType;
	private Double amount;
	private Double newBalance;
	
	public Transaction(String transType,Double amount, Double newBalance) {
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
	
}
