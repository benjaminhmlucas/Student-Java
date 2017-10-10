package edu.tridenttech.cpt237.lucas.model;
/**
 * @file: Check.java
 * @author: Ben Lucas
 * @purpose: Class creates a single check item to be handled in Restaurant, ServerCheckSelectionWindow, and EditCheckWindow
 */
import java.util.ArrayList;

public class Check {
	
	private static int idCounter = 1000;//auto-increments ID's in the constructor for new checks
	private int checkId;//tracking for single checks
	private double checkBalance = 0;//starting balance of a new check is always 0.0
	private ArrayList<MenuItem> checkItemList;//holds MenuItem objects and stores them for records of what guest owes to restaurant
	private int serverId;//allows retrieval of a single servers check from an ArrayList of Check Objects open in the restaurant and used in ServerCheckSelectWindow
	
	public Check(int s_Id) 
	{
		checkId = ++idCounter;
		this.checkItemList = new ArrayList<>();
		this.serverId = s_Id;
	}

	public int getCheckId() {
		return checkId;
	}

	public double getCheckBalance() {
		return checkBalance;
	}
	
	public double addToCheckBalance(double amount) {
		return checkBalance += amount;
	}
	
	public double subtractFromCheckBalance(double amount) {
		return checkBalance -= amount;
	}

	public ArrayList<MenuItem> getCheckItemList() {
		return checkItemList;
	}
	
	public double getServerId() {
		return serverId;
	}

}
