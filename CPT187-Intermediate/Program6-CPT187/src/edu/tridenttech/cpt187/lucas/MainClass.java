package edu.tridenttech.cpt187.lucas;
/**
 * @file: MainClass.java
 * @author: Ben Lucas
 * @purpose: calls methods to load an array and then displays its values.  
 * Next it will calculate the average of the values and number of values in 
 * the list above the average as well as display this information. 
 */
public class MainClass {

	public static void main(String[] args) {
		ListOfInts myList = new ListOfInts();
		myList.loadArray("program5.dat");
		myList.displayArray(myList.intList);
		System.out.print("Average of List Values: " + myList.calcAverage() + "\n");
		System.out.print("Number Of Values Above Average: " + myList.numAboveAverage(myList.intList) + "\n"); 
		System.out.print("Smallest Integer: " + myList.findMinInt(myList.intList));
	}

}
