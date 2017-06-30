package edu.lucas.program6;

import java.util.Scanner;

/**
 * File: LemonadeExpress3.java
 * Purpose: Calculates the cost of goods sold at the lemonade stand
 * and totals sales for the duration of the program using multiple methods
 * @author Ben Lucas
 *
 */
public class LemonadeExpress3 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		char productType = ' ';
		char itemType = ' ';
		String purchaseItem = "";
		double purchasePrice = 0.0;
		int totalOz = 0;
		int totalChoc = 0;
		int totalOat = 0;
		int totalShirt = 0;
		int totalSignedShirt = 0;
		int totalTransaction = 0;
		double totalSales = 0.0;
				
		productType = getProductType(input);
		
		while (productType == '1' || productType == '2' || productType == '3') 
		{
			if (productType == '1')
			{
				itemType = getCupType(input);
				purchasePrice = getPrice(itemType, 1.5, 2.0);
				if (purchasePrice == 1.5)
				{
					purchaseItem = "12oz Lemonade";
					totalOz += 12;
					totalTransaction += 1;
					totalSales += purchasePrice;
				}
				else 
				{
					purchaseItem = "16oz Lemonade";
					totalOz += 16;
					totalTransaction += 1;
					totalSales += purchasePrice;
				}
			}
			else if (productType == '2')
			{
				itemType = getCookieType(input);
				if (itemType == '1')
				{
					purchaseItem = "Chocoalate Chip";
					purchasePrice = 0.75;
					totalChoc += 1;
					totalTransaction += 1;
					totalSales += purchasePrice;
				}
				else 
				{
					purchaseItem = "Oatmeal Surprise!";
					purchasePrice = 0.75;
					totalOat += 1;
					totalTransaction += 1;
					totalSales += purchasePrice;
				}
			}
			else if (productType == '3') 
			{
				itemType = getShirtType(input);
				purchasePrice = getPrice(itemType, 8.0, 15.0);
				if (purchasePrice == 8.0)
				{
					purchaseItem = "TOH T-Shirt";
					totalShirt += 1;
					totalTransaction += 1;
					totalSales += purchasePrice;
				}
				else 
				{
					purchaseItem = "TOH T-Shirt Autographed";
					totalSignedShirt += 1;
					totalTransaction += 1;
					totalSales += purchasePrice;
				}
			}
		//while//
			//Display results
			System.out.print("*****************************************************************\n");
			System.out.print("*********************You Have Made Your Choice!******************\n");
			System.out.print("*****************************************************************\n");
			System.out.print("                    Lemonade Express Is Pleased                  \n");
			System.out.print("*****************************************************************\n");
			System.out.printf("%s %48s%n","You Have Chosen:", purchaseItem);
			System.out.print("*****************************************************************\n");
			System.out.printf("%s %45.2f%n", "This Item Costs($):", purchasePrice);
			System.out.print("*****************************************************************\n");
			//enter next item
			productType = getProductType(input);
		}
	//main//
	  	//displays daily sales totals//
		System.out.print("*****************************************************************\n");
		System.out.print("*********************You Have Made Your Choice!******************\n");
		System.out.print("*****************************************************************\n");
		System.out.print("                    Lemonade Express Is Pleased                  \n");
		System.out.print("*****************************************************************\n");
		System.out.printf("%s %45d%n","Total Transactions:", totalTransaction);
		System.out.print("*****************************************************************\n");
		System.out.printf("%s %40d%n", "Total Lemonade Sold(Oz):", totalOz);
		System.out.printf("%s %35d%n", "Total Chocolate Cookies Sold:", totalChoc);
		System.out.printf("%s %36d%n","Total Oatmeal Suprise! Sold:", totalOat);
		System.out.printf("%s %46d%n","Total Shirts Sold:", totalShirt);
		System.out.printf("%s %39d%n","Total Signed Shirts Sold:", totalSignedShirt);
		System.out.print("*****************************************************************\n");
		System.out.printf("%s %43.2f%n", "Total Daily Sales($):", totalSales);
		System.out.print("*****************************************************************\n");	
		System.out.print("*******************Have a marginally good day!*******************\n");
	
	}
	/*Prompts user to enter productType and then returns productType
	 * @param console input object
	 * @return productType entered by user
	 */
	static char getProductType(Scanner console) 
	{
		char productType;
		// Welcome & input productType/Quit
		System.out.print("*****************************************************************\n");
		System.out.print("*******************Welcome To Lemondade Express 3.0!*************\n");
		System.out.print("**********************Please Enter Product Type******************\n");
		System.out.print("***********************Enter (1) for Lemondade*******************\n");
		System.out.print("************************Enter (2) for Cookies********************\n");
		System.out.print("***********************Enter (3) fof T-Shirts********************\n");
		System.out.print("**************************Press (0) to Quit**********************\n");
		System.out.print("***************After entering choice please press ENTER**********\n");
		System.out.print("****************************Choose Wisely************************\n");
		productType = console.next().charAt(0);
		while (productType != '0' && productType != '1' && productType != '2'&& productType != '3')
		{
			System.out.print("****************INVALID ENTRY********************\n");	
			System.out.print("(1) for for Lemonade\n");
			System.out.print("(2) for Cookies\n");
			System.out.print("(3) for Tshirts\n");
			System.out.print("(0) to Quit(press Enter after your choice):");
			productType = console.next().charAt(0);
		}
		return productType;
	}
	/*Prompts user to enter itemType and then returns itemType
	 * @param console input object
	 * @return itemType entered by user
	 */
	static char getCupType(Scanner console) 
	{
		char itemType;
		System.out.print("Please Select Size\n");
		System.out.print("Type (1) for 12oz\n");
		System.out.print("Type (2) for 16oz\n");
		System.out.print("(Press ENTER after selection):\n");
		
		itemType = console.next().charAt(0);
		
			//test for correct data entry and loop if not entered correctly
			while (itemType != '1' && itemType != '2') 
			{
				System.out.print("****************INVALID ENTRY********************\n");	
				System.out.print("Type (1) for 12oz\n");
				System.out.print("Type (2) for 16oz\n");
				System.out.print("(Press ENTER after selection):\n");
				itemType = console.next().charAt(0);
			}
		return itemType;
	}

	/*Prompts user to enter itemType and then returns itemType
	 * @param console input object
	 * @return itemType entered by user
	 */
	static char getCookieType(Scanner console) 
	{
		char itemType;
		System.out.print("Please Select Cookie Flavor\n");
		System.out.print("Type (1) for Chocolate Chip\n");
		System.out.print("Type (2) for Oatmeal Surprise!\n");
		System.out.print("(Press ENTER after selection):\n");
		itemType = console.next().charAt(0);

		//test for correct data entry and loop if not entered correctly
		while (itemType != '1' && itemType != '2') 
		{
			System.out.print("****************INVALID ENTRY********************\n");	
			System.out.print("Type (1) for Chocolate Chip\n");
			System.out.print("Type (2) for Oatmeal Surprise!\n");
			System.out.print("(Press ENTER after selection):\n");
			itemType = console.next().charAt(0);
		}
		return itemType;
	}
	/*Prompts user to enter itemType and then returns itemType
	 * @param console input object
	 * @return itemType entered by user
	 */
	static char getShirtType(Scanner console) 
	{
		char itemType;
		System.out.print("Please Select T-Shirt Type\n");
		System.out.print("Type (1) for TOH T-Shirt\n");
		System.out.print("Type (2) for TOH T-Shirt Autographed\n");
		System.out.print("(Press ENTER after selection):\n");
		itemType = console.next().charAt(0);

		//test for correct data entry and loop if not entered correctly
		while (itemType != '1' && itemType != '2') 
		{
			System.out.print("****************INVALID ENTRY********************\n");	
			System.out.print("Type (1) for TOH T-Shirt\n");
			System.out.print("Type (2) for TOH T-Shirt Autographed\n");
			System.out.print("(Press ENTER after selection):\n");
			itemType = console.next().charAt(0);
		}
		return itemType;
	}
	/*determines ShirtPrice based on ShirtType
	 * @param char shirtType
	 * @return shirtPrice
	 */
	/*determines purchasePrice based on itemType
	 * @param char itemType
	 * @param double price1
	 * @param double price2
	 * @return purchasePrice
	 */
	static double getPrice(char itemType, double price1, double price2) 
	{
		double purchasePrice;
		
		if (itemType == '1')
			purchasePrice = price1;
		else
			purchasePrice = price2;
		return purchasePrice;
	}
	
}
