package edu.lucas.program3;

import java.util.Scanner;

/**
 * File: YardCalculator2.java
 * Purpose: Calculates dimensions for yard, pool, and house as well as cost of sod for yard 
 * and cost of pool installation.  
 * @author Ben Lucas
 *
 */
public class YardCalculator2 
{
	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
	
		int yardWidth = 0;
		int yardLength = 0;
		int yardSqft = 0;
		int houseWidth = 0;
		int houseLength = 0;
		int houseSqft = 0;
		double poolSqft = 0;
		char sodType = ' ';
		String sodName = "";
		double sodSqft = 0.0;
		double sodSqftCost = 0.0;
		double sodInstallCost = 0.0;
		double sodTotalCost = 0.0;
		double poolCubic = 0.0;
		double poolTotalCost = 0.0;
		char poolHeater = ' ';
		double yardTotalCost = 0.0;
	
		System.out.println("Welcome to Yard Calculator 2.0! A fancy program to calculate fancy yard dimensions for fancy consumers!");
		System.out.println("Please enter the width and the length of your yard in feet (press the ENTER key after each value):");
		yardWidth = input.nextInt();
		yardLength = input.nextInt();
		
		System.out.println("Please enter the width and the length of your house in feet (press the ENTER key after each value):");
		houseWidth = input.nextInt();
		houseLength = input.nextInt();
		
		System.out.println("Please enter the type of sod you would like for your yard.");
		System.out.println("Enter (K) for Kentucky Bluegrass ($1.47 per Sqft)");
		System.out.println("Press ANY other key for South Carolina Rye ($0.57 per Sqft):");
		sodType = input.next().charAt(0);
	    sodType = Character.toUpperCase(sodType);
	   
	    poolSqft = (yardWidth * 0.75) * (yardWidth * 0.5);
	    yardSqft = yardWidth * yardLength;
	    houseSqft = houseWidth * houseLength;
	    sodSqft = yardSqft - (houseSqft + poolSqft);
	   	  
	    if (sodType == 'K') 
	    { 
	    	sodSqftCost = sodSqft * 1.47;
	        sodInstallCost = (sodSqft / 20) * 6.95;
	    	sodTotalCost = sodSqftCost + sodInstallCost;
	    }
	    else 
	    {
	    	sodSqftCost = sodSqft * 0.57;
	    	sodInstallCost = (sodSqft / 20) * 6.95;
	    	sodTotalCost = sodSqftCost + sodInstallCost;
	    }
	   
	    poolCubic = poolSqft * 6;                    
	    poolTotalCost = (poolSqft * 18.95) + 1575;
	    yardTotalCost = poolTotalCost + sodTotalCost; 
	    
	    if (poolCubic <= 3100)
	    { 
	    poolHeater = 'A';
	    }
	    else if (poolCubic <= 8000) 
	    {
	    poolHeater = 'B';	
	    }		
	    else if (poolCubic <= 15000) 
	    {
	    poolHeater = 'C';	
	    }
	    else if (poolCubic <= 25000) 
	    {
	    poolHeater = 'D';	
	    }    
	    else 
	    {
	    poolHeater = 'E';	
	    }
	    System.out.println("Great! Now let's see your results:");
	   
	    int count = 0;
	    	while (count < 60) 
	    		{
	    		System.out.printf("%s", "*");
	    		count += 1;
	    		}
	    
	    System.out.printf("\n%12s %43s%n", "Yard Length(ft):", yardLength);
	    System.out.printf("%12s %44s%n", "Yard Width(ft):", yardWidth);
	    System.out.printf("%12s %33s%n", "Yard Square Footage(sqft):" , yardSqft);
	    System.out.printf("%12s %42s%n", "House Length(ft):" , houseLength);
	    System.out.printf("%12s %43s%n", "House Width(ft):" , houseWidth);
	    System.out.printf("%12s %32s%n", "House Square Footage(sqft):" , houseSqft);
		
	    count = 0;
    	while (count < 60) 
    		{
    		System.out.printf("%s", "*");
    		count += 1;
    		}
	    
	    if (sodType == 'K') 
			{
		    sodName = "Kentucky Bluegrass";
			System.out.printf("\n%s %50s%n", "Sod Type:" , sodName);
			}
		else 
			{
			sodName = "South Carolina Rye";
			System.out.printf("\n%s %50s%n", "Sod Type:" , sodName);
			}
		 System.out.printf("%12s %33.2f%n", "Sod Square Footage (sqft):" , sodSqft);
		 System.out.printf("%12s %28.2f%n", "Sod Square Footage Cost (sqft):" , sodSqftCost);
		 System.out.printf("%12s %33.2f%n", "Sod Installation Cost ($):" , sodInstallCost);
		 System.out.printf("%12s %40.2f%n", "Sod Total Cost ($):" , sodTotalCost);
		 
		 count = 0;
	    	while (count < 60) 
	    		{
	    		System.out.printf("%s", "*");
	    		count += 1;
	    		}
		 
		 System.out.printf("\n%12s %31.2f%n", "Pool Saquare Footage (sqft):" , poolSqft);
		 System.out.printf("%12s %26.2f%n", "Pool Maximum Volume (cubic feet):" , poolCubic);
		 System.out.printf("%12s %40s %1s%n", "Pool Heater Type:" , "Model",poolHeater);
		 System.out.printf("%12s %39.2f%n", "Pool Total Cost ($):" , poolTotalCost);
		 
		 count = 0;
	    	while (count < 60) 
	    		{
	    		System.out.printf("%s", "*");
	    		count += 1;
	    		}
		 
		 System.out.printf("\n%12s %39.2f%n", "Yard Total cost ($):" , yardTotalCost);
		 
		 count = 0;
	    	while (count < 60) 
	    		{
	    		System.out.printf("%s", "*");
	    		count += 1;
	    		}
		 System.out.println("\nAsk about Free Gumbo Friday!!!******************************");
	}
}
