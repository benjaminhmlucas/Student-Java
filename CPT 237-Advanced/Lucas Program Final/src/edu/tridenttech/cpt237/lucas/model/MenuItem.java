package edu.tridenttech.cpt237.lucas.model;
/**
 * @file: MenuItem.java
 * @author: Ben Lucas
 * @purpose: Class creates a single menu item to be used in EditMenuWindow and
 * EditCheckWindow.  In EditCheckWindow, MenuItems are cloned from an ArrayList<MenuItem> into a 
 * list of items for each individual check. This means items stay on check even if they are deleted
 * from the available menu items list in EditMenuWindow
 */
public class MenuItem implements Cloneable{
	
	private int foodItemNumber;
	private String name;
	private double cost;	
	
	public MenuItem(String name, double cost, int id) {
		this.name = name;
		this.cost = cost;
		this.foodItemNumber = id;
	}

	public String getItemName() {
		return name;
	}

	public double getItemCost() {
		return cost;
	}
	
	public int getItemID() {
		return foodItemNumber;
	}
	
	public Object clone() throws CloneNotSupportedException{  
		return super.clone();  
		}  
}
