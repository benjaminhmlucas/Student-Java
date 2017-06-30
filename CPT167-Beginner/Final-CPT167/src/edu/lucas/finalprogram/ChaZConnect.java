package edu.lucas.finalprogram;

import java.util.Scanner;

/**
 * File: ChaZConnect.java
 * Purpose: Calculates monthly cable bill from user input.  Totals option choices and monthly bills when program is quit.
 * @author Ben Lucas
 *
 */
public class ChaZConnect {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		char cableType = ' ';
		String cableName ="";
		double cablePrice = 0.0;
		int cableCount = 0;
		double cableTotal = 0;
		double voipPrice = 0.0;
		String voipChoice = "";
		int voipCount = 0;
		double voipTotal = 0.0;
		double internetPrice = 0.0;
		String internetChoice = "";
		int internetCount = 0;
		double internetTotal = 0.0;
		double discountAmount = 0.0;
		double discountTotal = 0.0;
		double monthlyTotal = 0.0;
		double monthlyTotalDiscount = 0.0;
		double dailyTotal = 0.0;
		
		String[] menu ={"Would you like to add on Voice Over IP to your package for $29.99?\nType (1) for yes\nType (2) for no\n(Press ENTER after selection):\n",
		"Would you like to add High Speed Internet to your package for $29.99?\nType (1) for yes\nType (2) for no\n(Press ENTER after selection):\n",
		"Would you like to have auto-draft from you bank Account\nthat will add a 2% discount on your monthly total?\nType (1) for yes\nType (2) for no\n(Press ENTER after selection):\n",
		"****************INVALID ENTRY********************\nType (1) for yes\nType (2) for no\n(Press ENTER after selection):\n"};
			
		cableType = getCableType(input);
		
		//main program loop
		while (cableType == '1'|| cableType == '2'|| cableType == '3')
		{
			cablePrice = getCablePrice(cableType);
			
			//add cable to daily totals
			cableCount += 1;
			cableTotal += cablePrice;
			
			//assigns cableName
			if (cableType == '1') 
		    {
				cableName = "Eco Plan";
			}
			else if (cableType == '2')
			{
				cableName = "Digital Plan";
			}
			else 
			{
				cableName = "HD Plan";
			}
			
			voipPrice = getOption(input, menu[0], menu[3]);
			
			//assigns voipChoice
			if (voipPrice == 0.0) 
			{
				voipChoice ="Declined";
			}
			else 
			{
				voipChoice ="Accepted";
				voipCount += 1;
			}
			
			//voip daily totals
			voipTotal += voipPrice;
			
			internetPrice = getOption(input, menu[1], menu[3]);
			
			//assigns internetChoice
			if (internetPrice == 0.0) 
			{
				internetChoice ="Declined";
			}
			else 
			{
				internetChoice ="Accepted";
				internetCount += 1;
			}
			//internet daily totals
			internetTotal += internetPrice;
			//customers monthly package total without discount
			monthlyTotal = cablePrice + voipPrice + internetPrice;
			
			discountAmount = getAutoPay(input, menu[2], menu[3], monthlyTotal);
			//calculate monthlyTotalDiscount and 
			//adds Discount Amount to dailyTotal
			monthlyTotalDiscount = (monthlyTotal - discountAmount);
			discountTotal += discountAmount;
			dailyTotal += monthlyTotalDiscount;
		
		//Display results
		System.out.print("*************ChaZConnect Now Knows What You Desire!**************\n");
		System.out.print("*****************************************************************\n");
		System.out.print("*****************Here Is a List of Your Desires******************\n");
		System.out.printf("%s %50s%n","Type of Cable:", cableName);
		System.out.printf("%s %49.2f%n","Cable Price($):", cablePrice);
		System.out.printf("%s %36s%n","Did you want Voice over IP?:", voipChoice);
		System.out.printf("%s %50.2f%n","VoIP Price($):", voipPrice);
		System.out.printf("%s %26s%n","Did you want Internet at High Speeds?:", internetChoice);
		System.out.printf("%s %36.2f%n","High Speed Internet Cost($):", internetPrice);
		System.out.printf("%s %39.2f%n","Monthly Package Total($):", monthlyTotal);
		System.out.printf("%s %43.2f%n","Auto Pay Discount($):", discountAmount);
		System.out.printf("%s %25.2f%n","Monthly Total Package with Discount($):", monthlyTotalDiscount);
		System.out.print("*****************************************************************\n");
		//enter next item
		cableType = getCableType(input);
		}
		//displays daily sales totals//
				System.out.print("********************************************************************\n");
				System.out.print("*************ChaZConnect is sad that you are leaving****************\n");
				System.out.print("*********but will now give you your Daily Program Totals************\n");
				System.out.printf("%s %41d%n", "Total Cable Packages Sold:", cableCount);
				System.out.printf("%s %46.2f%n", "Total Cable Sales($):", cableTotal);
				System.out.printf("%s %43d%n","Total VoIP Add-Ons Sold:", voipCount);
				System.out.printf("%s %47.2f%n","Total VoIP Sales($):", voipTotal);
				System.out.printf("%s %39d%n", "Total Internet Add-Ons Sold:", internetCount);
				System.out.printf("%s %43.2f%n", "Total Internet Sales($):", internetTotal);
				System.out.printf("%s %42.2f%n","Total Discounts Given($):", discountTotal);
				System.out.printf("%s %24.2f%n","Total Monthly Income from Program Sales($):", dailyTotal);
				System.out.print("********************************************************************\n");	
				System.out.print("**************This terminal will now self destruct!*****************\n");
	}
	/*Prompts user to enter cableType and then returns cableType
	 * @param console input object
	 * @return cableType entered by user
	 */
	static char getCableType(Scanner console)
	{
		char cableType = ' ';
		// Welcome & input productType/Quit
		System.out.print("*****************************************************************\n");
		System.out.print("**********************ChaZConnect Cablematic*********************\n");
		System.out.print("************Please Enter Cable Package That You Desire***********\n");
		System.out.print("******Enter (1) for Eco Plan Package(45+ channels) $19.99/mo*****\n");
		System.out.print("******Enter (2) for Digital Package(140+ channels) $49.99/mo*****\n");
		System.out.print("***Enter (3) for Hi-Definition Package(220+ channels) $59.99/mo**\n");
		System.out.print("**************************Press (0) to Quit**********************\n");
		System.out.print("***************After entering choice please press ENTER**********\n");
		System.out.print("****************************Choose Wisely************************\n");
		cableType = console.next().charAt(0);
		while (cableType != '0' && cableType != '1' && cableType != '2'&& cableType != '3')
		{
			System.out.print("*******************INVALID ENTRY***********************\n");	
			System.out.print("(1) for Eco Plan Package(45+ channels) $19.99/mo\n");
			System.out.print("(2) for Digital Package(140+ channels) $49.99/mo\n");
			System.out.print("(3) for Hi-Definition Package(220+ channels) $59.99/mo\n");
			System.out.print("(0) to Quit(press Enter after your choice):");
			cableType = console.next().charAt(0);
		}
		return cableType;
	}
	/*Examines cableType and assigns a value to cablePrice
	 * @param character cableType
	 * @return double cablePrice
	 */
	static double getCablePrice(char cableType)
	{
		double cablePrice = 0.0;
		if (cableType == '1') 
		{
			cablePrice = 19.99;
		}
		else if (cableType == '2') 
		{
			cablePrice = 49.99;
		}
		else 
		{
			cablePrice = 59.99;
		}
		return cablePrice;
	}
	/*Prompts user to enter '1' or '2' based on choices passed in through menu array
	 * @param console input object
	 * @param menu array
	 * @returns price of choice
	 */
	static double getOption(Scanner console, String menuOption, String menuError)
	{
		char choice = ' ';
		double price = 0.0;
		System.out.print(menuOption);
		choice = console.next().charAt(0);
		while (choice != '1' && choice != '2') 
		{
			System.out.print(menuError);
			choice = console.next().charAt(0);
		}
		if (choice == '1') 
		{
			price = 29.99;
		}
		else 
		{
			price = 0.0;
		}
		return price;
	}
	/*Prompts user to choose auto pay option. Then uses 
	 * monthlyTotal calculate discountAmount
	 * @param console input object
	 * @param double monthlyTotal
	 * @returns discountAmount
	 */
	static double getAutoPay(Scanner console, String menuOption, String menuError, double monthlyTotal)
	{
		char choice = ' ';
		double discountAmount = 0.0;
		System.out.print(menuOption);
		choice = console.next().charAt(0);
		while (choice != '1' && choice != '2') 
		{
			System.out.print(menuError);
			choice = console.next().charAt(0);
		}
		if (choice == '1') 
		{
			discountAmount = (monthlyTotal * 0.02);
		}
		else 
		{
			discountAmount = 0.0;
		}
		return discountAmount;
	}
}
