package edu.lucas.program4;

import java.util.Scanner;
/**
 * File: LemonadeExpress.java
 * Purpose: Calculates the cost of goods sold at the lemonade stand
 * @author Ben Lucas
 *
 */
public class LemonadeExpress
{

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		char productType = ' ';
		char lemonadeType = ' ';
		char cookieType = ' ';
		char shirtType = ' ';
		String purchaseItem = "";
		double purchasePrice = 0.0;
		double SMLEMONADE = 1.5;
		double LGLEMONADE = 2.0;
		double CHOCCOOKIE = 0.75;
		double OATCOOKIE = 0.75;
		double SHIRT = 8.0;
		double SIGNEDSHIRT = 15.0;
		
		//welcome screen & input productType
		System.out.print("*****************************************************************\n");
		System.out.print("****************Welcome To The Lemonade Express 1.0!*************\n");
		System.out.print("*****************************************************************\n");
		System.out.print("**************************|______________|***********************\n");
		System.out.print("************************** |oooooooooooo|************************\n");
		System.out.print("****************************|!Lemonade!|*************************\n");
		System.out.print("*****************************|oooooooo|**************************\n");
		System.out.print("******************************|______|***************************\n");
		System.out.print("**********************Please Enter Product Type******************\n");
		System.out.print("***********************Enter (1) for Lemondade*******************\n");
		System.out.print("************************Enter (2) for Cookies********************\n");
		System.out.print("***********************Enter (3) fof T-Shirts********************\n");
		System.out.print("*****************************************************************\n");
		System.out.print("***************After entering choice please press ENTER**********\n");
		System.out.print("****************************Choose Wisely************************\n");
		productType = input.next().charAt(0);
		
		//test for correct data entry and loop if not entered correctly
		while (productType != '1' && productType != '2' && productType != '3') 
		{
			System.out.print("****************INVALID ENTRY********************\n");	
			System.out.print("(1) for for Lemonade\n");
			System.out.print("(2) for Cookies\n");
			System.out.print("(3) for Tshirts(press Enter after your choice):");
			productType = input.next().charAt(0);
		}
		//Test for productType 
		if (productType == '1')
		{
			System.out.print("Please Select Size\n");
			System.out.print("Type (1) for 12oz\n");
			System.out.print("Type (2) for 16oz\n");
			System.out.print("(Press ENTER after selection):\n");
			lemonadeType = input.next().charAt(0);
		
			//test for correct data entry and loop if not entered correctly
			while (lemonadeType != '1' && lemonadeType != '2') 
			{
				System.out.print("****************INVALID ENTRY********************\n");	
				System.out.print("Type (1) for 12oz\n");
				System.out.print("Type (2) for 16oz\n");
				System.out.print("(Press ENTER after selection):\n");
				lemonadeType = input.next().charAt(0);
				
			}
			//assign lemonadeType
			if (lemonadeType == '1')
			{
				purchaseItem = "12oz Lemonade";
				purchasePrice = SMLEMONADE;
			}
			else 
			{
				purchaseItem = "16oz Lemonade";
				purchasePrice = LGLEMONADE;
			}
		}
		else 
		{
			//Test for productType
			if (productType =='2') 
			{
				System.out.print("Please Select Cookie Flavor\n");
				System.out.print("Type (1) for Chocolate Chip\n");
				System.out.print("Type (2) for Oatmeal Surprise!\n");
				System.out.print("(Press ENTER after selection):\n");
				cookieType = input.next().charAt(0);
		
				//test for correct data entry and loop if not entered correctly
				while (cookieType != '1' && cookieType != '2') 
				{
					System.out.print("****************INVALID ENTRY********************\n");	
					System.out.print("Type (1) for Chocolate Chip\n");
					System.out.print("Type (2) for Oatmeal Surprise!\n");
					System.out.print("(Press ENTER after selection):\n");
					cookieType = input.next().charAt(0);
				}
			
				//Assign cookieType
				if (cookieType == '1')
				{
					purchaseItem = "Chocoalate Chip";
					purchasePrice = CHOCCOOKIE;
				}
				else 
				{
					purchaseItem = "Oatmeal Surprise!";
					purchasePrice = OATCOOKIE;
				}
			}
			else 
			{
				System.out.print("Please Select T-Shirt Type\n");
				System.out.print("Type (1) for TOH T-Shirt\n");
				System.out.print("Type (2) for TOH T-Shirt Autographed\n");
				System.out.print("(Press ENTER after selection):\n");
				shirtType = input.next().charAt(0);
		
				//test for correct data entry and loop if not entered correctly
				while (shirtType != '1' && shirtType != '2') 
				{
					System.out.print("****************INVALID ENTRY********************\n");	
					System.out.print("Type (1) for TOH T-Shirt\n");
					System.out.print("Type (2) for TOH T-Shirt Autographed\n");
					System.out.print("(Press ENTER after selection):\n");
					shirtType = input.next().charAt(0);
				}
			
				//Assign shirtType
				if (shirtType == '1')
				{
					purchaseItem = "TOH T-Shirt";
					purchasePrice = SHIRT;
				}
				else 
				{
					purchaseItem = "TOH T-Shirt Autographed";
					purchasePrice = SIGNEDSHIRT;
				}	
			}
		
		}
		
		//Display results
		System.out.print("*****************************************************************\n");
		System.out.print("*********************You have made your Choice!******************\n");
		System.out.print("*****************************************************************\n");
		System.out.print("                    Lemonade Express is pleased                  \n");
		System.out.print("*****************************************************************\n");
		System.out.printf("%s %48s%n","You Have Chosen:", purchaseItem);
		System.out.print("*****************************************************************\n");
		System.out.printf("%s %45.2f%n", "This Item Costs($):", purchasePrice);
		System.out.print("*****************************************************************\n");
		System.out.print("****************---------------------------------****************\n");
		System.out.print("****************|  This Old House reserves the  |****************\n");
		System.out.print("****************|  right to eat your snacks and |****************\n");
		System.out.print("****************| wear your clothes at any time!|****************\n");
		System.out.print("****************---------------------------------****************\n");
		System.out.print("*****************************************************************\n");
		
	}

}
