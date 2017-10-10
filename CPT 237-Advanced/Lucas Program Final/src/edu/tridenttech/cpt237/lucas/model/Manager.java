package edu.tridenttech.cpt237.lucas.model;
/**
 * @file: Manager.java
 * @author: Ben Lucas
 * @purpose: Class extends Employee to track id for employee id tracking system.
 * Presently it does not implement a Banker but could be added later to track cash from a
 * server.
 */

public class Manager extends Employee{
			
	public Manager(String firstName, String lastName,int id) {
		super(firstName, lastName,id);		
	}
}
