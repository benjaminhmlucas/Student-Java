package edu.lucas.program2;

import java.util.Scanner;

/**
 * File: YardCalculator.java
 * Purpose: Calculates dimensions for yard and cost of sod for yard
 * @author Ben Lucas
 *
 */
public class YardCalculator 
{

	public static void main(String[] args) 
	{
		
		Scanner input = new Scanner(System.in);
		String userName = "";
		int yardLength = 0;
		int yardWidth = 0;
		int yardSqft = 0;
		int houseLength = 0;
		int houseWidth = 0;
		int houseSqft = 0;
		double pondMax = 0.0;
		int pondDiameter = 0;
		double pondRadius = 0.0;
		int pondDepth = 0;
		double pondSqft = 0.0;
		double sodSqft = 0.0;
		double sodCost = 0.0;
		
		System.out.println("Good day! Welcome to Yancy's Yard Calculator! Please enter your first name:");
		userName = input.nextLine();
		
		System.out.println("Great " + userName + "! Next, please enter the length and width of your yard in feet.");
		System.out.println("Do not use decimals and press enter after each inputing each number in feet (length, ENTER, width, ENTER):");
		yardLength = input.nextInt();
		yardWidth = input.nextInt();
	
		System.out.println("Super! Now let's enter length then width of your house in the same way you entered");
		System.out.println("yard dimensions in feet(length, ENTER, width, ENTER):");
		houseLength = input.nextInt();
		houseWidth = input.nextInt();
	
		//Calculates maximum pond diameter
		pondMax = yardWidth * 0.85;
		
		System.out.println("Fantastical! Now let's figure out how big you want your pond to be! Please");  
		System.out.println("enter pond width and pond depth the same as you entered previous dimensions in feet.");
	    System.out.println("Don't forget that the maximum pool diameter you can have is " + (int)pondMax + "ft");
	    System.out.println("(Diameter, ENTER, Depth, ENTER");
	    pondDiameter = input.nextInt();
		pondDepth = input.nextInt();
		
		//calculates yard square footage
		yardSqft = yardLength * yardWidth;
	    //calculates house square footage 
		houseSqft = houseLength * houseWidth;
	    //calculates radius of pond
		pondRadius = pondDiameter/2;
	    //calculates pond saquare footage 
		pondSqft = Math.PI * (Math.pow(pondRadius, 2));
	    //calculates square footage of sod that will be needed
		sodSqft = yardSqft - houseSqft - pondSqft;
	    //calculates the cost of the sod that is needed
		sodCost = sodSqft * 1.39;
	    
	    System.out.println("Wow! Looks like we have everything we need to calculate your remaining square footage"); 
	    System.out.println("for sod and how much that sod will cost! Let's summarize your information:");
	    
	    int count = 0;
	    while (count < 60) 
	    	{
	    		System.out.printf("%s", "*");
	    		count = count + 1;	    	    	
	    	}                       
	    System.out.println("\nName: " + userName);
	   
	    count = 0;
	    while (count < 60) 
	    	{
	    		System.out.printf("%s", "*");
	    		count = count + 1;	    	    	
	    	}
	    System.out.printf("\n%12s %43s%n", "Yard Length(ft):", yardLength);
	    System.out.printf("%12s %44s%n", "Yard Width(ft):", yardWidth);
	    System.out.printf("%12s %33s%n", "Yard Square Footage(sqft):" , yardSqft);
	    System.out.printf("%12s %42s%n", "House Length(ft):" , houseLength);
	    System.out.printf("%12s %43s%n", "House Width(ft):" , houseWidth);
	    System.out.printf("%12s %32s%n", "House Square Footage(sqft):" , houseSqft);
	    System.out.printf("%12s %41s%n", "Pond Diameter(ft):" , pondDiameter);
	    System.out.printf("%12s %44s%n", "Pond Depth(ft):" , pondDepth);
	    System.out.printf("Pond Square Footage(sqft):                           %.2f%n" , pondSqft);
	    System.out.printf("Sod Square Footage(sqft):                            %.2f%n" , sodSqft);
	    System.out.printf("Sod Cost($):                                         %.2f%n" , sodCost);
	     
	    count = 0;
	    while (count < 60) 
	    	{
	    		System.out.printf("%s", "*");
	    		count = count + 1;	    	    	
	    	}
	    System.out.println("\nThanks for usings Yancy's Yard Calculator!\n"
	    		+ "Don't forget your free chicken biscuit!");
	}	

}
