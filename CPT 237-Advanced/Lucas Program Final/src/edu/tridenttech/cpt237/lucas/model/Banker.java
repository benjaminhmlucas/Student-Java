package edu.tridenttech.cpt237.lucas.model;
/**
 * @file:Banker.java
 * @author: Ben Lucas
 * @purpose: interface to track employees with cash owed to restaurant 
 */
public interface Banker{		
	
	public double getCashOwedToRestaurant();
	
	public double addCashOwedToRestaurant(double amount);

}
