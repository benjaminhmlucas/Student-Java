package edu.tridenttech.cpt237.lucas.model;
/**
 * @file: SErver.java
 * @author: Ben Lucas
 * @purpose: Class creates a single Employee Object that can track 
 * cashOWedToRestaurant variable and notify ServerCheckSelectWindow
 */
public class Server extends Employee implements Banker{
	
	private double cashOwedToRestaurant;	
	
	public Server(String firstName, String lastName,int id) {
		super(firstName, lastName,id);
		this.cashOwedToRestaurant = 0;	
	}
	
	@Override
	public double getCashOwedToRestaurant() {		
		return cashOwedToRestaurant;
	}

	/**
	 * @param amount amount of money to be added to server balance
	 * @purpose adds money to servers cash owed to the restaurant and
	 * notifies ServerCheckSelectWindow of balance changes
	 */
	@Override
	public double addCashOwedToRestaurant(double amount) {
		this.setChanged();
		this.notifyObservers(amount);
		return cashOwedToRestaurant += amount;		
	}
	
}
