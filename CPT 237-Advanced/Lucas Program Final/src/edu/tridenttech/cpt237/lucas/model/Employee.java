package edu.tridenttech.cpt237.lucas.model;
/**
 * @file: Employee.java
 * @author: Ben Lucas
 * @purpose: Creates and employee object, extends Observable so that any employee object that needs to be a Banker 
 * can also notify the UI Objects of changes to the cash owed to the restaurant
 */
import java.util.Observable;

public abstract class Employee extends Observable{
		
	private final int employeeId;
	private String firstName;
	private String lastName;
		
	public Employee(String firstName, String lastName,int id) {
		this.employeeId = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	public int getEmployeeId() {
		return employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
	
	
}
